# Gameplay Defaults, Dependencies, and Clarifications

This is a gameplay parity contract, not a target implementation design. No engine, framework or release is prescribed. Baseline names refer to observable rules of the original game. Different targets MUST reproduce them explicitly rather than rely on their own defaults.

## Configurable Defaults

| Gameplay setting | Supplied default | Decision rule |
| --- | --- | --- |
| Rope reeling | Enabled | Sneaking empty-hand rope use selects reeling instead of bell search |
| Dark canvas backgrounds | gray, purple, blue, brown, green, red, black | Default white sign text on the sides specified in the sign contract |
| Rich soil growth boost | 0.2 | Chance per eligible random opportunity; zero disables |
| Climb all tagged ropes | Enabled | Use rope eligibility group; otherwise standard rope only |
| Rope restored by tomatoes | farmersdelight:rope | Unknown configured identity falls back to standard rope |
| Rope permanence on nonplayer removal | Enabled | Restore except piston movement |
| Pot recipe book | Enabled | Provide discoverable pot recipes in station interface |
| Ordinary crop-crate crafting | Enabled | Apply recipe feature gates, not just existence of crate items |
| Cutting Fortune bonus | 0.1 | Added probability PER LEVEL, per output unit |
| Extra effects for ordinary soups | Enabled | Follow soup-finish exception for rabbit buff |
| Rabbit stew nutrition buff | Enabled | Hunger 14, saturation 21; extra-effect handler skips it |
| Ordinary pumpkin pie requires sneak placement | Disabled | When enabled only sneaking block-use places it |
| Dispenser cutting tools | Enabled | Use held dispenser tool against adjacent front board |
| Ordinary soups stackable | Enabled | Stack 16 for mushroom stew, beetroot soup, rabbit stew by default |
| Additional chest loot | Enabled | Apply gated drop additions |
| Village compost heaps and replacement farm crops | Enabled settings | Source activation must be verified separately from default flags |
| Farmer buying and wandering-trader selling | Enabled settings | Actual trade handlers in this snapshot are commented out; do NOT add trades just because settings exist |
| Nourishment/Comfort meter overlays | Enabled | Visual-only options, not effect logic |
| Food-effect tooltips | Enabled | Expose consumption effects and pet-feeding effects appropriately |

## Baseline Gameplay Contract

- Hunger meter maximum 20; saturation capped at resulting hunger; ordinary food gain `2 * nutrition * saturation multiplier`. Ordinary eating/drinking defaults, natural healing/exhaustion, hunger loss and effect stacking are inherited. The custom catalog expresses saturation points to avoid multiplier confusion. Ordinary consumption is commonly 1.6 seconds, but its exact inherited default must be verified for this snapshot before claiming parity.
- Ordinary farmland-crop growth uses age progression, light >=9 for growth, farmland viability with light >=8 or sky visibility, fertile-neighborhood speed, crop crowding penalties and fertilizer increments. Resolve exact ordinary crop increments/probabilities and inherited soil eligibility before choosing a target's crop implementation. These apply to cabbage, onion and upper rice; custom tomato/rice probabilities override them where specified.
- Base tool material values, enchantment acquisition, durability consumption, repair/enchantability, incorrect-drop sets and default attack attributes must be fixed independently of the target. Only flint's complete custom numeric material is supplied here. A cross-target build cannot assume its iron/copper/gold materials match automatically.
- Copied block materials inherit breaking speed, blast resistance, sounds, fire behavior, piston response and occlusion properties. Crates/custom overrides have explicit values in contracts. Baselines to pin: brick stove, metal pot/skillet, wooden board/baskets, barrel-like cabinets, dirt/ordinary farmland, cake-like pastries/feasts, hay-like bales, wool-like bags/tatami, spruce-like sign forms. Copied MATERIAL properties do not imply inherited CLASS behavior: canvas rug is not a carpet class; stuffed-pumpkin feast is not an ordinary full pumpkin collision.
- Exact base membership sets (dirt, sand, walls, knife-mineable references, enchantables, repair materials, crop loot bonuses, effect exclusions) must be expanded recursively from [memberships.md](memberships.md). Optional external members are optional extensions. `required=false` members may be absent without breaking core gameplay. Match-tool predicates include stored-state/enchantment requirements, not just names.
- Drop semantics: independent pools; ordered first-matching alternatives; candidate weights; per-roll random counts; item-count modifiers in order; inverted/all-of/any-of conditions; crop Fortune formulas; explosion decay; stored item/name preservation. Honor ordering. Dropping contents must not run the same inventory preservation twice. Add-item appends its configured count split into valid stacks; replacement removes the specified item entries and appends replacement; pastry slicing appends remaining-bite slices. Additional-table loot is gated by its setting.
- Ordinary sign text editing, support, attached hanging chains, source-water behavior, power propagation, gate toggles, leash binding, comparator rounding and projectile contact remain explicit baseline dependencies. The supplied custom state atlas is not sufficient to reconstruct sign writing, external textures or parent geometry on its own.
- Campfires above campfire-signal-smoke-group blocks emit signal smoke. Tree and giant-tree growth must preserve rich soil instead of substituting ordinary dirt. Villager farmland targeting and trampling protection must include rich-soil farmland; check activation, not just helper existence.

## Clarification Register

| ID | Source-established conflict or gap | Required decision before complete parity |
| --- | --- | --- |
| CL-001 | Rice-roll medley inherits container-only serving, but rolls have no crafting remainder | Specify intended containerless serving input or explicitly retain current unusable behavior; do not invent a bowl requirement |
| CL-002 | Direct pie eating both feeds slice nutrition explicitly and calls slice completion; source comments question equivalence | Verify whether nutrition/effects are applied once or twice through the inherited consumption path; document observed result |
| CL-003 | Player rotten-tomato throw constructs ordinary snowball; dispenser path constructs custom tomato | Confirm preservation of divergent behavior versus intended custom projectile, including exceptional snowball targets |
| CL-004 | Several persistence, recipe and registration paths are unfinished in supplied source | Establish observed persistence/activation in a runnable reference; stated gameplay intent must not be mistaken for successfully verified runtime behavior |
| CL-005 | Rich-soil plant-compatibility filtering returns default eligibility with an unfinished note | Resolve permitted noncrop substrates/plants through the reference baseline |
| CL-006 | Skillet duration clamps to original time even when original is <=0 or <60 | Pin boundary behavior; supplied positive normal recipes use the stated formula |
| CL-007 | Reeling grants inventory rope before finding bottom and may exit at world minimum without destruction | Decide boundary behavior explicitly; test no rope duplication at minimum height |
| CL-008 | Knife declares weapon wear 2 and custom post-hit wear 1 | Resolve inherited weapon wear participation instead of assuming one or three |
| CL-009 | Full-inventory board removal and pet-feeding remainder paths lack explicit overflow drops | Retain observed loss behavior for literal parity or authorize a deliberate correction |
| CL-010 | External base-game assets, material constants and inherited crop/sign behavior are not defined by this repository | Supply/pin these baseline dependencies before declaring standalone complete recreation |

These are real, explicit open decisions, not template placeholders. The specification is intentionally Draft while they remain open. Reproduction of source bugs and restoration of intended gameplay are different products; choose one explicitly for each conflict.