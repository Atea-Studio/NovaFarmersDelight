---
icon: lucide/history
---

# Migration Guide 0.24 ➝ 0.25

## Item and Block Types

Nova [items](../items/overview.md) and [blocks](../blocks/creating-blocks.md) now use Bukkit's `#!kotlin ItemType` and `#!kotlin BlockType`, just like vanilla types.

!!! warning "Replace Material-Based Type Access"

    `#!kotlin ItemStack.type` and `#!kotlin Block.type` return a `#!kotlin Material`, which cannot represent Nova's custom types. Use these extension properties provided by Nova instead:

    * `#!kotlin ItemStack.itemType: ItemType` gets an item stack's type.
    * `#!kotlin Block.blockType: BlockType` gets a block's type.

    Both work for vanilla and Nova types. Update existing type checks too, so they do not mistake custom items or blocks for vanilla ones.

    Nova now includes a Kotlin compiler plugin that warns about `#!kotlin Material` usage and other common migration mistakes. Nova's Gradle plugin enables it automatically.

=== "0.24"
    ```kotlin
    val itemType: Material = stack.type
    val blockType: Material = block.type
    ```

=== "0.25"
    ```kotlin
    val itemType: ItemType = stack.itemType
    val blockType: BlockType = block.blockType
    ```

## Registries

Registration functions now return a [RegistryEntry](../registries.md#registry-entries), a reference to the registered object, instead of the object itself. Keep this reference in your registration singleton. If a function needs the actual object, call `#!kotlin entry.get()` after the registry has loaded.

Do not resolve entries while initializing your registration singleton: the objects have not been created yet. Builder functions run later and may run again during [registry reloads](../registries.md#registry-reloading).

When comparing types, compare entries with entries or resolved values with values. Nova's `#!kotlin ItemStack.itemTypeEntry: RegistryEntry.Paper<ItemType>` extension property returns the entry for an item stack:

=== "0.24"
    ```kotlin
    val isExampleItem = stack.novaItem == Items.EXAMPLE_ITEM
    ```

=== "0.25"
    ```kotlin
    val isExampleItem = stack.itemTypeEntry == Items.EXAMPLE_ITEM
    ```

## Configs

Configs are now read through `#!kotlin ConfigProvider` instead of Configurate's configuration nodes. Files remain YAML, but values are deserialized using `kotlinx.serialization`. See [Accessing Configs](../configs.md#accessing-configs).

If you have custom Configurate serializers, replace them with `#!kotlin KSerializer`s and register them via `#!kotlin CONFIGS.setSerializers(addon, serializersModule)`. See [Custom Serializers](../configs.md#custom-serializers).

Calling `#!kotlin entry("path")` without a default value is deprecated because a missing or invalid config value can throw an exception at runtime and break addon functionality. Pass a default with `#!kotlin entry(default, "path")` so Nova can fall back to it instead.

## Behavior Factories

For [ItemBehaviorFactory](../items/item-behaviors.md#itembehaviorfactory):

=== "0.24"
    ```kotlin
    override fun create(item: NovaItem): MyBehavior =
        MyBehavior(item.config.entry<Int>("value"))
    ```

=== "0.25"
    ```kotlin
    override fun create(entry: RegistryEntry.Paper<ItemType>, config: ConfigProvider): MyBehavior =
        MyBehavior(config.entry<Int>(1, "value"))
    ```

For `#!kotlin BlockBehaviorFactory`:

=== "0.24"
    ```kotlin
    override fun create(block: NovaBlock): MyBlockBehavior =
        MyBlockBehavior(block.config.entry<Int>("value"))
    ```

=== "0.25"
    ```kotlin
    override fun create(entry: RegistryEntry.Paper<BlockType>, config: ConfigProvider): MyBlockBehavior =
        MyBlockBehavior(config.entry<Int>(1, "value"))
    ```

## Item Behaviors

* Replace `#!kotlin vanillaMaterialProperties` with an override of [modifyClientSideItemType](../items/item-behaviors.md#client-side-item-stack) that selects the client-side `#!kotlin ItemType`.
* `#!kotlin handleUseTick` and `#!kotlin handleUseStopped` now return `#!kotlin ItemAction?`. Return `#!kotlin null` to skip cooldowns and other [post-use effects](../items/item-behaviors.md#item-using-holding-right-click). Return `#!kotlin ItemAction.None` to trigger those effects without changing the stack, or another `#!kotlin ItemAction` to also modify the stack.

### Built-In Behaviors

* The [Enchantable](../items/enchantments.md#enchantable-items) factory takes registry entry sets, e.g. `#!kotlin supportedEnchantments = registryEntrySetOf(EnchantmentEntries.SHARPNESS)`, instead of providers of enchantment sets.
* `#!kotlin Damageable.repairIngredient` now takes a registry entry set instead of a `#!kotlin RecipeChoice`, e.g. `#!kotlin registryEntrySetOf(ItemTypeEntries.IRON_INGOT)`.
* Add `#!kotlin AttributeModifiers()` explicitly if your item uses the `attribute_modifiers` config entry.

## Item Data

`#!kotlin NamespacedCompound` and the `#!kotlin ItemStack.novaCompound` extension property have been removed. Item data is now stored in Bukkit's persistent data container (PDC).

!!! note "Existing Item Data Is Migrated Automatically"

    Nova automatically merges data from old item compounds into the item's PDC, keeping the same namespaced keys. You do not need to write a converter for this storage change.

Nova's [item-data helper functions](../items/item-behaviors.md#item-data) now read and write PDC values using CBF serialization. `#!kotlin ItemBehavior.defaultCompound` has also been removed, so supply defaults when reading missing values:

```kotlin
val key = Key.key(ExampleAddon, "value")
val value = itemStack.retrieveData<Int>(key) ?: 0
```

## Tools and Weapons

`#!kotlin Tool` now controls mining through vanilla tool rules. Attack damage, attack speed, sweeping attacks, and durability loss on attack are handled by `#!kotlin Weapon`. Add it to tools that previously relied on `#!kotlin Tool` for these settings:

=== "0.24"
    ```kotlin
    behaviors(Tool(), Damageable(), Enchantable())
    ```

=== "0.25"
    ```kotlin
    behaviors(Tool(), Weapon(), Damageable(), Enchantable())
    ```

Move durability loss on block breaking from `#!kotlin Damageable` to `#!kotlin Tool`, and durability loss on attacking from `#!kotlin Damageable` to `#!kotlin Weapon`.

### Custom Tool Categories

`#!kotlin ToolCategory` objects are no longer registered. Pass the category's name as a `#!kotlin Key` to `#!kotlin Tool` instead. Nova uses that name to find [tags](../registries.md#registry-entry-sets), which are named groups of items or blocks.

To migrate a category named `example_addon:hammer`, create two tags:

* A block tag, `example_addon:mineable/hammer`, containing the blocks that hammers should mine efficiently.
* An item tag, `example_addon:hammers`, for the hammer items. `#!kotlin Tool` adds its items to this tag automatically, but the tag must already be registered.

### Custom Tool Tiers

`#!kotlin ToolTier` registration and the numeric tier hierarchy in `tool_levels.yml` have been removed. Tiers are now identified by a `#!kotlin Key`, and block tags determine which blocks each tier is too weak to collect drops from.

For a tier named `example_addon:emerald`, this tag is `example_addon:incorrect_for_emerald_tool`. Put blocks in it if their drops should require a stronger tool than emerald.

For an existing emerald tier equivalent to diamond, reuse diamond's restrictions:

=== "0.24"
    ```kotlin title="ToolTiers.kt"
    @Init(stage = InitStage.PRE_PACK)
    object ToolTiers {
        val EMERALD = ExampleAddon.registerToolTier("emerald")
    }
    ```

=== "0.25"
    ```kotlin title="BlockTags.kt"
    @Init(stage = InitStage.PRE_PACK)
    object BlockTags {
        val INCORRECT_FOR_EMERALD_TOOL = ExampleAddon.blockTag("incorrect_for_emerald_tool") {
            add(BlockTypeTags.INCORRECT_FOR_DIAMOND_TOOL)
        }
    }
    ```

The `tool_tier: example_addon:emerald` config setting can stay the same; it now selects the tier's block tag by name.

When configuring the tier in Kotlin:

```kotlin title='ExampleAddon.item("emerald_pickaxe") { }'
behaviors(Tool(toolTier = Key.key(ExampleAddon, "emerald")))
```

See [Custom Tool Categories and Tiers](../items/tools.md#custom-tool-categories-and-tiers) for the tag setup, or [Tool Behavior](../items/tools.md#tool-behavior) for explicit rules and the `tool_rules` config format.

## Blocks

`#!kotlin BlockPos` has been replaced by Bukkit's `#!kotlin Block` throughout the addon API.

[Block access](../blocks/creating-blocks.md#placing-destroying-nova-blocks) no longer goes through `#!kotlin WorldDataManager`. Nova provides these extension properties on Bukkit's `#!kotlin Block`:

* `#!kotlin Block.blockType: BlockType` returns the block's type, whether vanilla or custom.
* `#!kotlin Block.novaBlockState: NovaBlockState?` returns the custom block's state, or `#!kotlin null` for a vanilla block.
* `#!kotlin Block.novaTileEntity: TileEntity?` returns the Nova tile-entity at that block, or `#!kotlin null` if there is none.

### Block Registration

Move breaking properties and sounds out of the behavior list and configure them directly in the block builder:

=== "0.24"
    ```kotlin title="ExampleAddon.block(...) { }"
    behaviors(
        Breakable(3.0, setOf(VanillaToolCategories.PICKAXE), VanillaToolTiers.STONE, true, Material.STONE),
        BlockSounds(SoundGroup.STONE)
    )
    ```

=== "0.25"
    ```kotlin title="ExampleAddon.block(...) { }"
    breakable(
        hardness = 3.0,
        toolCategories = setOf(Key.key("pickaxe")),
        toolTier = Key.key("stone"),
        requiresToolForDrops = true,
        hitParticles = ItemTypeEntries.STONE,
        breakParticles = BlockTypeEntries.STONE
    )
    sounds(SoundGroup.STONE)
    ```

The `#!kotlin TileEntityLimited` block behavior has been removed; tile-entity limit tracking is now enabled by default.

The `#!kotlin stateSelector` passed to `#!kotlin entityBacked`, `#!kotlin entityItemBacked`, or `#!kotlin modelLess` must now return Bukkit `#!kotlin BlockData` instead of an NMS block state. For example, return `#!kotlin BlockType.BARRIER.createBlockData()` for a barrier backing block.

### Block State Properties

The split between scoped and unscoped [block state properties](../blocks/creating-blocks.md#block-state-properties) has been removed. Specify allowed values when creating the property:

=== "0.24"
    ```kotlin title="BlockStateProperties.kt"
    object BlockStateProperties {
        val LEVEL = IntProperty(Key.key("example_addon", "level"))
    }

    object ScopedBlockStateProperties {
        val LEVEL = BlockStateProperties.LEVEL.scope(0..2)
    }
    ```

=== "0.25"
    ```kotlin title="BlockStateProperties.kt"
    object BlockStateProperties {
        val LEVEL = IntProperty(Key.key("example_addon", "level"), 0..2)
    }
    ```

Use `#!kotlin DefaultBlockStateProperties` for built-in properties. The function that chooses a property's value when a block is placed is now an `#!kotlin Autofiller`; read placement parameters with `#!kotlin ctx.resolve(...)` instead of `#!kotlin ctx[...]`.

The `#!kotlin Waterloggable` behavior has been removed. Keep the `#!kotlin DefaultBlockStateProperties.WATERLOGGED` property; it now enables waterlogging on its own.

### Block States

!!! warning "Block States Are Now Mutable"

    `#!kotlin NovaBlockState` now implements Bukkit's `#!kotlin BlockData`. Changing a property modifies the existing state object instead of creating a different one. This does not update the block in the world automatically.

Outside a tile-entity, apply the state with `#!kotlin block.blockData = state`. Inside a tile-entity:

=== "0.24"
    ```kotlin
    updateBlockState(blockState.with(BlockStateProperties.ACTIVE, active))
    ```

=== "0.25"
    ```kotlin
    val state = blockState
    state[BlockStateProperties.ACTIVE] = active
    updateBlockState(state)
    ```

### Block Contexts

Block parameters now use Bukkit types. `#!kotlin BLOCK_POS` is replaced by `#!kotlin BLOCK`, and the separate vanilla / Nova type and state parameters are replaced by `#!kotlin BLOCK_TYPE` (`#!kotlin BlockType`) and `#!kotlin BLOCK_STATE` (`#!kotlin BlockData`).

=== "0.24"
    ```kotlin
    val ctx = Context.intention(BlockPlace)
        .param(BlockPlace.BLOCK_POS, pos)
        .param(BlockPlace.BLOCK_TYPE_NOVA, Blocks.EXAMPLE_BLOCK)
        .build()
    ```

=== "0.25"
    ```kotlin
    val ctx = Context.intention(BlockPlace)
        .param(BlockPlace.BLOCK, block)
        .param(BlockPlace.BLOCK_TYPE, Blocks.EXAMPLE_BLOCK.get())
        .build()
    ```

## Contexts

Context [autofillers](../contexts.md#custom-autofiller) now run when `#!kotlin build()` is called, instead of lazily when a parameter is read.

### Custom Parameter Registration

[Custom parameter types](../contexts.md#custom-contextparametertype) are now registered on the intention itself:

=== "0.24"
    ```kotlin title="ContextParamTypes.kt"
    object ContextParamTypes {
        val RECURSIVE_BREAK = DefaultingContextParamType<Boolean, BlockBreak>(
            Key.key("example_addon", "recursive_break"),
            default = false
        )
    }
    ```

=== "0.25"
    ```kotlin title="ContextParamTypes.kt"
    object ContextParamTypes {
        val RECURSIVE_BREAK = BlockBreak.addDefaultingParamType(false)
    }
    ```

For optional or required parameters, use `#!kotlin addOptionalParamType` or `#!kotlin addRequiredParamType`, respectively.

## Tile Entities

[Tile-entity constructors](../blocks/tile-entities.md#creating-a-tile-entity) now receive a Bukkit `#!kotlin Block`, and the `#!kotlin pos` property is now `#!kotlin block`:

=== "0.24"
    ```kotlin
    class ExampleTileEntity(pos: BlockPos, blockState: NovaBlockState, data: Compound)
        : NetworkedTileEntity(pos, blockState, data)
    ```

=== "0.25"
    ```kotlin
    class ExampleTileEntity(block: Block, blockState: NovaBlockState, data: Compound)
        : NetworkedTileEntity(block, blockState, data)
    ```

`#!kotlin TileEntity.coroutineSupervisor` is now nullable instead of `#!kotlin lateinit`.

## Tile Entity Networks

In [network side configurations](../tile-entity-networks/networked-tile-entity.md#side-configuration-menu), replace `#!kotlin Map<BlockFace, ...>` with `#!kotlin CubeFaceMap` and `#!kotlin Map<BlockSide, ...>` with `#!kotlin BlockSideMap` (likewise `#!kotlin CubeFaceSet` / `#!kotlin BlockSideSet` for sets).

For [custom network types](../tile-entity-networks/custom-network-types.md), replace `#!kotlin holderTypes` with `#!kotlin extractHolders`. Nova calls this function with a `#!kotlin NetworkEndPoint`. Return the data holders your network needs from that end point, such as its `#!kotlin ItemHolder`, or `#!kotlin null` if a required holder is missing. Pass an `#!kotlin Int` for `#!kotlin tickDelay` instead of a provider.

## GUIs

InvUI's [reactive Kotlin DSL](../../../../invui/reactive-menus/) is now preferred over the previous builder-based approach with manual GUI updates. All Nova functions built around this DSL now use items that expect a GUI texture to provide the background, instead of items with the inventory background built into their textures.

Client-side items are no longer directly exposed to addons. Use regular server-side item stacks and item providers, including in GUIs.

Replace `#!kotlin item.model.clientsideProvider` with `#!kotlin item.guiItemProvider` for GUI icons. `#!kotlin guiItemProvider` only differs from `#!kotlin itemProvider` by hiding Nova's advanced tooltip; it does not create a separate client-side item.

`#!kotlin EnergyBar`, `#!kotlin FluidBar`, and direct `#!kotlin SideConfigMenu` construction are deprecated in favor of `#!kotlin energyBar`, `#!kotlin fluidBar`, and [openSideConfigItem](../tile-entity-networks/networked-tile-entity.md#side-configuration-menu).

### GUI Textures

Update existing [GUI texture registrations](../guis/guitextures.md#creating-and-using-gui-textures) like this:

=== "0.24"
    ```kotlin title="GuiTextures.kt"
    @Init(stage = InitStage.PRE_PACK)
    object GuiTextures {
        val EXAMPLE = ExampleAddon.guiTexture("example") {
            path("gui/custom_example")
        }
    }
    ```

=== "0.25"
    ```kotlin title="GuiTextures.kt"
    @Init(stage = InitStage.PRE_PACK)
    object GuiTextures {
        val EXAMPLE = ExampleAddon.guiTexture("example") {
            texture { path("gui/custom_example") }
        }
    }
    ```

`#!kotlin getTitle` now returns a `#!kotlin Provider<Component>` instead of a `#!kotlin Component`:

```kotlin title="window(player) { }"
title by GuiTextures.EXAMPLE.getTitle(Component.text("Example"))
```

## Tile Entity Menus

Replace annotated inner menu classes with an overridden `#!kotlin menu` property:

=== "0.24"
    ```kotlin title="Inside ExampleTileEntity"
    @TileEntityMenuClass
    inner class ExampleMenu : GlobalTileEntityMenu() {
        override val gui = Gui.builder()
            .setStructure("i i i i i i i i i")
            .addIngredient('i', inventory)
            .build()
    }
    ```

=== "0.25"
    ```kotlin title="Inside ExampleTileEntity"
    override val menu = TileEntityMenu.cachedWindow {
        upperGui by gui("i i i i i i i i i") {
            'i' by inventory
        }
    }
    ```

Each player gets their own window. If your menu opens additional windows, call `#!kotlin menu.register(window)` so Nova can close them when the tile-entity is removed or the player moves out of range. See [Tile Entity Menu](../guis/tile-entity-menu.md).

## Recipes

Register custom recipe types during `#!kotlin BOOTSTRAP` instead of `#!kotlin POST_WORLD`. See [Creating a custom Recipe Type](../items/recipes.md#creating-a-custom-recipe-type).

## Packet Entities

Fake entities have been replaced by packet entities:

=== "0.24"
    ```kotlin
    val entity = FakeItem(spawnLocation, false) { _, data ->
        data.item = itemStack
        data.hasNoGravity = true
    }
    entity.register()
    entity.updateEntityData { item = newItemStack }
    entity.remove()
    ```

=== "0.25"
    ```kotlin
    val entity = packetItemEntity {
        location by spawnLocation
        metadata {
            item by itemStack
            noGravity by true
        }
    }
    entity.spawn()
    entity.metadata.item = newItemStack
    entity.despawn()
    ```

## Notable Dependency Updates

* Minecraft: `26.2` ➝ `26.3`
    * [Minecraft wiki](https://minecraft.wiki/w/Java_Edition_26.3)
* InvUI: `2.2.0` ➝ `2.5.1`
    * [InvUI changelogs](https://github.com/NichtStudioCode/InvUI/releases)
* commons-provider: `2.0.0-alpha.10` ➝ `2.0.0-alpha.11`
    * [commons-provider changelogs](https://github.com/xenondevs/commons-provider/releases)
