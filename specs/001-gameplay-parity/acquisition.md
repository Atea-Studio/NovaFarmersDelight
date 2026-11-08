# Acquisition, Milestones, and Enchantment Data

These exact gameplay records are normative. Numbered children retain source ordering. Membership groups beginning with # mean any eligible member, not a literal item. Recipe kind is a gameplay activity. Probability defaults and base-game referenced groups must follow the shared dependency contract. Main-resource records override generated records at the same identifier. Optional integration records are labeled and are not core requirements.

## farmersdelight/advancement/main/craft_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/root` |
| criteria / diamond knife / Required conditions / items / 1 / items | `farmersdelight:diamond_knife` |
| criteria / diamond knife / trigger | `minecraft:inventory_changed` |
| criteria / flint knife / Required conditions / items / 1 / items | `farmersdelight:flint_knife` |
| criteria / flint knife / trigger | `minecraft:inventory_changed` |
| criteria / golden knife / Required conditions / items / 1 / items | `farmersdelight:golden_knife` |
| criteria / golden knife / trigger | `minecraft:inventory_changed` |
| criteria / iron knife / Required conditions / items / 1 / items | `farmersdelight:iron_knife` |
| criteria / iron knife / trigger | `minecraft:inventory_changed` |
| criteria / netherite knife / Required conditions / items / 1 / items | `farmersdelight:netherite_knife` |
| criteria / netherite knife / trigger | `minecraft:inventory_changed` |
| display / description / translate | `advancements.farmersdelight.craft_knife.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:flint_knife` |
| display / title / translate | `advancements.farmersdelight.craft_knife.title` |
| requirements / 1 / 1 | `flint_knife` |
| requirements / 1 / 2 | `iron_knife` |
| requirements / 1 / 3 | `diamond_knife` |
| requirements / 1 / 4 | `golden_knife` |
| requirements / 1 / 5 | `netherite_knife` |
| sends telemetry event | true |

## farmersdelight/advancement/main/eat_nourishing_food

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/place_cooking_pot` |
| criteria / nourishment / Required conditions / effects / nourishment | No additional conditions |
| criteria / nourishment / trigger | `minecraft:effects_changed` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.eat_nourishing_food.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:steak_and_potatoes` |
| display / title / translate | `advancements.farmersdelight.eat_nourishing_food.title` |
| requirements / 1 / 1 | `nourishment` |
| sends telemetry event | true |

## farmersdelight/advancement/main/get_fd_seed

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/root` |
| criteria / cabbage seeds / Required conditions / items / 1 / items | `farmersdelight:cabbage_seeds` |
| criteria / cabbage seeds / trigger | `minecraft:inventory_changed` |
| criteria / onion / Required conditions / items / 1 / items | `farmersdelight:onion` |
| criteria / onion / trigger | `minecraft:inventory_changed` |
| criteria / rice / Required conditions / items / 1 / items | `farmersdelight:rice` |
| criteria / rice / trigger | `minecraft:inventory_changed` |
| criteria / tomato seeds / Required conditions / items / 1 / items | `farmersdelight:tomato_seeds` |
| criteria / tomato seeds / trigger | `minecraft:inventory_changed` |
| display / description / translate | `advancements.farmersdelight.get_fd_seed.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:wild_onions` |
| display / title / translate | `advancements.farmersdelight.get_fd_seed.title` |
| requirements / 1 / 1 | `cabbage_seeds` |
| requirements / 1 / 2 | `tomato_seeds` |
| requirements / 1 / 3 | `onion` |
| requirements / 1 / 4 | `rice` |
| sends telemetry event | true |

## farmersdelight/advancement/main/get_ham

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/craft_knife` |
| criteria / ham / Required conditions / items / 1 / items | `farmersdelight:ham` |
| criteria / ham / trigger | `minecraft:inventory_changed` |
| criteria / smoked ham / Required conditions / items / 1 / items | `farmersdelight:smoked_ham` |
| criteria / smoked ham / trigger | `minecraft:inventory_changed` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.get_ham.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:ham` |
| display / title / translate | `advancements.farmersdelight.get_ham.title` |
| requirements / 1 / 1 | `ham` |
| requirements / 1 / 2 | `smoked_ham` |
| sends telemetry event | true |

## farmersdelight/advancement/main/get_mushroom_colony

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/get_fd_seed` |
| criteria / brown mushroom colony / Required conditions / items / 1 / items | `farmersdelight:brown_mushroom_colony` |
| criteria / brown mushroom colony / trigger | `minecraft:inventory_changed` |
| criteria / red mushroom colony / Required conditions / items / 1 / items | `farmersdelight:red_mushroom_colony` |
| criteria / red mushroom colony / trigger | `minecraft:inventory_changed` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.get_mushroom_colony.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:red_mushroom_colony` |
| display / title / translate | `advancements.farmersdelight.get_mushroom_colony.title` |
| requirements / 1 / 1 | `brown_mushroom_colony` |
| requirements / 1 / 2 | `red_mushroom_colony` |
| sends telemetry event | true |

## farmersdelight/advancement/main/get_rich_soil

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/place_organic_compost` |
| criteria / get rich soil / Required conditions / items / 1 / items | `farmersdelight:rich_soil` |
| criteria / get rich soil / trigger | `minecraft:inventory_changed` |
| display / description / translate | `advancements.farmersdelight.get_rich_soil.description` |
| display / frame | `goal` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:rich_soil` |
| display / title / translate | `advancements.farmersdelight.get_rich_soil.title` |
| requirements / 1 / 1 | `get_rich_soil` |
| sends telemetry event | true |

## farmersdelight/advancement/main/harvest_ropelogged_tomato

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/get_fd_seed` |
| criteria / harvest ropelogged tomato / Required conditions / location / 1 / Condition kind | `minecraft:location_check` |
| criteria / harvest ropelogged tomato / Required conditions / location / 1 / Eligibility predicate / Block / blocks | `farmersdelight:tomatoes_on_rope` |
| criteria / harvest ropelogged tomato / Required conditions / location / 1 / Eligibility predicate / Block / state / age | `0` |
| criteria / harvest ropelogged tomato / trigger | `minecraft:default_block_use` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.harvest_ropelogged_tomato.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:tomato` |
| display / title / translate | `advancements.farmersdelight.harvest_ropelogged_tomato.title` |
| requirements / 1 / 1 | `harvest_ropelogged_tomato` |
| sends telemetry event | true |

## farmersdelight/advancement/main/harvest_straw

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/craft_knife` |
| criteria / harvest straw / Required conditions / items / 1 / items | `farmersdelight:straw` |
| criteria / harvest straw / trigger | `minecraft:inventory_changed` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.harvest_straw.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:straw` |
| display / title / translate | `advancements.farmersdelight.harvest_straw.title` |
| requirements / 1 / 1 | `harvest_straw` |
| sends telemetry event | true |

## farmersdelight/advancement/main/hit_raider_with_rotten_tomato

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/harvest_ropelogged_tomato` |
| criteria / hit raider with rotten tomato / Required conditions / damage / Behavior kind / direct entity / Behavior kind | `farmersdelight:rotten_tomato` |
| criteria / hit raider with rotten tomato / Required conditions / damage / Behavior kind / tags / 1 / expected | true |
| criteria / hit raider with rotten tomato / Required conditions / damage / Behavior kind / tags / 1 / Identifier | `minecraft:is_projectile` |
| criteria / hit raider with rotten tomato / Required conditions / entity / 1 / Condition kind | `minecraft:entity_properties` |
| criteria / hit raider with rotten tomato / Required conditions / entity / 1 / entity | `this` |
| criteria / hit raider with rotten tomato / Required conditions / entity / 1 / Eligibility predicate / Behavior kind | `#minecraft:raiders` |
| criteria / hit raider with rotten tomato / trigger | `minecraft:player_hurt_entity` |
| display / description / translate | `advancements.farmersdelight.hit_raider_with_rotten_tomato.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:rotten_tomato` |
| display / title / translate | `advancements.farmersdelight.hit_raider_with_rotten_tomato.title` |
| requirements / 1 / 1 | `hit_raider_with_rotten_tomato` |
| sends telemetry event | true |

## farmersdelight/advancement/main/master_chef

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/place_feast` |
| criteria / bacon and eggs / Required conditions / Item / items | `farmersdelight:bacon_and_eggs` |
| criteria / bacon and eggs / trigger | `minecraft:consume_item` |
| criteria / baked cod stew / Required conditions / Item / items | `farmersdelight:baked_cod_stew` |
| criteria / baked cod stew / trigger | `minecraft:consume_item` |
| criteria / beef stew / Required conditions / Item / items | `farmersdelight:beef_stew` |
| criteria / beef stew / trigger | `minecraft:consume_item` |
| criteria / bone broth / Required conditions / Item / items | `farmersdelight:bone_broth` |
| criteria / bone broth / trigger | `minecraft:consume_item` |
| criteria / chicken soup / Required conditions / Item / items | `farmersdelight:chicken_soup` |
| criteria / chicken soup / trigger | `minecraft:consume_item` |
| criteria / cooked rice / Required conditions / Item / items | `farmersdelight:cooked_rice` |
| criteria / cooked rice / trigger | `minecraft:consume_item` |
| criteria / fish stew / Required conditions / Item / items | `farmersdelight:fish_stew` |
| criteria / fish stew / trigger | `minecraft:consume_item` |
| criteria / fried rice / Required conditions / Item / items | `farmersdelight:fried_rice` |
| criteria / fried rice / trigger | `minecraft:consume_item` |
| criteria / gleaming salad / Required conditions / Item / items | `farmersdelight:gleaming_salad` |
| criteria / gleaming salad / trigger | `minecraft:consume_item` |
| criteria / grilled salmon / Required conditions / Item / items | `farmersdelight:grilled_salmon` |
| criteria / grilled salmon / trigger | `minecraft:consume_item` |
| criteria / honey glazed ham / Required conditions / Item / items | `farmersdelight:honey_glazed_ham` |
| criteria / honey glazed ham / trigger | `minecraft:consume_item` |
| criteria / mixed salad / Required conditions / Item / items | `farmersdelight:mixed_salad` |
| criteria / mixed salad / trigger | `minecraft:consume_item` |
| criteria / mushroom rice / Required conditions / Item / items | `farmersdelight:mushroom_rice` |
| criteria / mushroom rice / trigger | `minecraft:consume_item` |
| criteria / noodle soup / Required conditions / Item / items | `farmersdelight:noodle_soup` |
| criteria / noodle soup / trigger | `minecraft:consume_item` |
| criteria / onion soup / Required conditions / Item / items | `farmersdelight:onion_soup` |
| criteria / onion soup / trigger | `minecraft:consume_item` |
| criteria / pasta with meatballs / Required conditions / Item / items | `farmersdelight:pasta_with_meatballs` |
| criteria / pasta with meatballs / trigger | `minecraft:consume_item` |
| criteria / pasta with mutton chop / Required conditions / Item / items | `farmersdelight:pasta_with_mutton_chop` |
| criteria / pasta with mutton chop / trigger | `minecraft:consume_item` |
| criteria / pumpkin soup / Required conditions / Item / items | `farmersdelight:pumpkin_soup` |
| criteria / pumpkin soup / trigger | `minecraft:consume_item` |
| criteria / ratatouille / Required conditions / Item / items | `farmersdelight:ratatouille` |
| criteria / ratatouille / trigger | `minecraft:consume_item` |
| criteria / roast chicken / Required conditions / Item / items | `farmersdelight:roast_chicken` |
| criteria / roast chicken / trigger | `minecraft:consume_item` |
| criteria / roasted mutton chops / Required conditions / Item / items | `farmersdelight:roasted_mutton_chops` |
| criteria / roasted mutton chops / trigger | `minecraft:consume_item` |
| criteria / shepherds pie / Required conditions / Item / items | `farmersdelight:shepherds_pie` |
| criteria / shepherds pie / trigger | `minecraft:consume_item` |
| criteria / squid ink pasta / Required conditions / Item / items | `farmersdelight:squid_ink_pasta` |
| criteria / squid ink pasta / trigger | `minecraft:consume_item` |
| criteria / steak and potatoes / Required conditions / Item / items | `farmersdelight:steak_and_potatoes` |
| criteria / steak and potatoes / trigger | `minecraft:consume_item` |
| criteria / stuffed pumpkin / Required conditions / Item / items | `farmersdelight:stuffed_pumpkin` |
| criteria / stuffed pumpkin / trigger | `minecraft:consume_item` |
| criteria / vegetable noodles / Required conditions / Item / items | `farmersdelight:vegetable_noodles` |
| criteria / vegetable noodles / trigger | `minecraft:consume_item` |
| criteria / vegetable soup / Required conditions / Item / items | `farmersdelight:vegetable_soup` |
| criteria / vegetable soup / trigger | `minecraft:consume_item` |
| display / description / translate | `advancements.farmersdelight.master_chef.description` |
| display / frame | `challenge` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:honey_glazed_ham` |
| display / title / translate | `advancements.farmersdelight.master_chef.title` |
| requirements / 1 / 1 | `mixed_salad` |
| requirements / 2 / 1 | `cooked_rice` |
| requirements / 3 / 1 | `bone_broth` |
| requirements / 4 / 1 | `beef_stew` |
| requirements / 5 / 1 | `vegetable_soup` |
| requirements / 6 / 1 | `fish_stew` |
| requirements / 7 / 1 | `chicken_soup` |
| requirements / 8 / 1 | `fried_rice` |
| requirements / 9 / 1 | `pumpkin_soup` |
| requirements / 10 / 1 | `baked_cod_stew` |
| requirements / 11 / 1 | `noodle_soup` |
| requirements / 12 / 1 | `onion_soup` |
| requirements / 13 / 1 | `bacon_and_eggs` |
| requirements / 14 / 1 | `ratatouille` |
| requirements / 15 / 1 | `steak_and_potatoes` |
| requirements / 16 / 1 | `pasta_with_meatballs` |
| requirements / 17 / 1 | `pasta_with_mutton_chop` |
| requirements / 18 / 1 | `mushroom_rice` |
| requirements / 19 / 1 | `roasted_mutton_chops` |
| requirements / 20 / 1 | `vegetable_noodles` |
| requirements / 21 / 1 | `squid_ink_pasta` |
| requirements / 22 / 1 | `grilled_salmon` |
| requirements / 23 / 1 | `roast_chicken` |
| requirements / 24 / 1 | `stuffed_pumpkin` |
| requirements / 25 / 1 | `honey_glazed_ham` |
| requirements / 26 / 1 | `shepherds_pie` |
| requirements / 27 / 1 | `gleaming_salad` |
| rewards / Experience per operation | 200 |
| sends telemetry event | true |

## farmersdelight/advancement/main/obtain_netherite_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/use_cutting_board` |
| criteria / obtain netherite knife / Required conditions / items / 1 / items | `farmersdelight:netherite_knife` |
| criteria / obtain netherite knife / trigger | `minecraft:inventory_changed` |
| display / description / translate | `advancements.farmersdelight.obtain_netherite_knife.description` |
| display / frame | `challenge` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:netherite_knife` |
| display / title / translate | `advancements.farmersdelight.obtain_netherite_knife.title` |
| requirements / 1 / 1 | `obtain_netherite_knife` |
| rewards / Experience per operation | 200 |
| sends telemetry event | true |

## farmersdelight/advancement/main/place_campfire

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/root` |
| criteria / campfire / Required conditions / location / 1 / Block | `minecraft:campfire` |
| criteria / campfire / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / campfire / trigger | `minecraft:placed_block` |
| criteria / soul campfire / Required conditions / location / 1 / Block | `minecraft:soul_campfire` |
| criteria / soul campfire / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / soul campfire / trigger | `minecraft:placed_block` |
| display / description / translate | `advancements.farmersdelight.place_campfire.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `minecraft:campfire` |
| display / title / translate | `advancements.farmersdelight.place_campfire.title` |
| requirements / 1 / 1 | `campfire` |
| requirements / 1 / 2 | `soul_campfire` |
| sends telemetry event | true |

## farmersdelight/advancement/main/place_cooking_pot

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/place_campfire` |
| criteria / cooking pot / Required conditions / location / 1 / Block | `farmersdelight:cooking_pot` |
| criteria / cooking pot / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / cooking pot / trigger | `minecraft:placed_block` |
| display / description / translate | `advancements.farmersdelight.place_cooking_pot.description` |
| display / frame | `goal` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:cooking_pot` |
| display / title / translate | `advancements.farmersdelight.place_cooking_pot.title` |
| requirements / 1 / 1 | `cooking_pot` |
| sends telemetry event | true |

## farmersdelight/advancement/main/place_feast

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/eat_nourishing_food` |
| criteria / gleaming salad / Required conditions / location / 1 / Block | `farmersdelight:gleaming_salad_block` |
| criteria / gleaming salad / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / gleaming salad / trigger | `minecraft:placed_block` |
| criteria / honey glazed ham / Required conditions / location / 1 / Block | `farmersdelight:honey_glazed_ham_block` |
| criteria / honey glazed ham / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / honey glazed ham / trigger | `minecraft:placed_block` |
| criteria / rice roll medley / Required conditions / location / 1 / Block | `farmersdelight:rice_roll_medley_block` |
| criteria / rice roll medley / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / rice roll medley / trigger | `minecraft:placed_block` |
| criteria / roast chicken / Required conditions / location / 1 / Block | `farmersdelight:roast_chicken_block` |
| criteria / roast chicken / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / roast chicken / trigger | `minecraft:placed_block` |
| criteria / shepherds pie / Required conditions / location / 1 / Block | `farmersdelight:shepherds_pie_block` |
| criteria / shepherds pie / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / shepherds pie / trigger | `minecraft:placed_block` |
| criteria / stuffed pumpkin / Required conditions / location / 1 / Block | `farmersdelight:stuffed_pumpkin_block` |
| criteria / stuffed pumpkin / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / stuffed pumpkin / trigger | `minecraft:placed_block` |
| display / description / translate | `advancements.farmersdelight.place_feast.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:roast_chicken_block` |
| display / title / translate | `advancements.farmersdelight.place_feast.title` |
| requirements / 1 / 1 | `roast_chicken` |
| requirements / 1 / 2 | `stuffed_pumpkin` |
| requirements / 1 / 3 | `honey_glazed_ham` |
| requirements / 1 / 4 | `shepherds_pie` |
| requirements / 1 / 5 | `gleaming_salad` |
| requirements / 1 / 6 | `rice_roll_medley` |
| sends telemetry event | true |

## farmersdelight/advancement/main/place_organic_compost

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/harvest_straw` |
| criteria / place organic compost / Required conditions / location / 1 / Block | `farmersdelight:organic_compost` |
| criteria / place organic compost / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / place organic compost / trigger | `minecraft:placed_block` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.place_organic_compost.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:organic_compost` |
| display / title / translate | `advancements.farmersdelight.place_organic_compost.title` |
| requirements / 1 / 1 | `place_organic_compost` |
| sends telemetry event | true |

## farmersdelight/advancement/main/place_skillet

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/use_skillet` |
| criteria / skillet / Required conditions / location / 1 / Block | `farmersdelight:skillet` |
| criteria / skillet / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / skillet / trigger | `minecraft:placed_block` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.place_skillet.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:skillet` |
| display / title / translate | `advancements.farmersdelight.place_skillet.title` |
| requirements / 1 / 1 | `skillet` |
| sends telemetry event | true |

## farmersdelight/advancement/main/plant_all_crops

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/plant_rice` |
| criteria / beetroot / Required conditions / location / 1 / Block | `minecraft:beetroots` |
| criteria / beetroot / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / beetroot / trigger | `minecraft:placed_block` |
| criteria / brown mushroom / Required conditions / location / 1 / Block | `minecraft:brown_mushroom` |
| criteria / brown mushroom / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / brown mushroom / trigger | `minecraft:placed_block` |
| criteria / cabbage / Required conditions / location / 1 / Block | `farmersdelight:cabbages` |
| criteria / cabbage / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / cabbage / trigger | `minecraft:placed_block` |
| criteria / carrot / Required conditions / location / 1 / Block | `minecraft:carrots` |
| criteria / carrot / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / carrot / trigger | `minecraft:placed_block` |
| criteria / chorus flower / Required conditions / location / 1 / Block | `minecraft:chorus_flower` |
| criteria / chorus flower / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / chorus flower / trigger | `minecraft:placed_block` |
| criteria / cocoa / Required conditions / location / 1 / Block | `minecraft:cocoa` |
| criteria / cocoa / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / cocoa / trigger | `minecraft:placed_block` |
| criteria / glow berries / Required conditions / location / 1 / Block | `minecraft:cave_vines` |
| criteria / glow berries / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / glow berries / trigger | `minecraft:placed_block` |
| criteria / kelp / Required conditions / location / 1 / Block | `minecraft:kelp` |
| criteria / kelp / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / kelp / trigger | `minecraft:placed_block` |
| criteria / melon / Required conditions / location / 1 / Block | `minecraft:melon_stem` |
| criteria / melon / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / melon / trigger | `minecraft:placed_block` |
| criteria / nether wart / Required conditions / location / 1 / Block | `minecraft:nether_wart` |
| criteria / nether wart / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / nether wart / trigger | `minecraft:placed_block` |
| criteria / onion / Required conditions / location / 1 / Block | `farmersdelight:onions` |
| criteria / onion / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / onion / trigger | `minecraft:placed_block` |
| criteria / potato / Required conditions / location / 1 / Block | `minecraft:potatoes` |
| criteria / potato / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / potato / trigger | `minecraft:placed_block` |
| criteria / pumpkin / Required conditions / location / 1 / Block | `minecraft:pumpkin_stem` |
| criteria / pumpkin / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / pumpkin / trigger | `minecraft:placed_block` |
| criteria / red mushroom / Required conditions / location / 1 / Block | `minecraft:red_mushroom` |
| criteria / red mushroom / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / red mushroom / trigger | `minecraft:placed_block` |
| criteria / rice / Required conditions / location / 1 / Block | `farmersdelight:rice` |
| criteria / rice / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / rice / trigger | `minecraft:placed_block` |
| criteria / sugar cane / Required conditions / location / 1 / Block | `minecraft:sugar_cane` |
| criteria / sugar cane / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / sugar cane / trigger | `minecraft:placed_block` |
| criteria / sweet berries / Required conditions / location / 1 / Block | `minecraft:sweet_berry_bush` |
| criteria / sweet berries / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / sweet berries / trigger | `minecraft:placed_block` |
| criteria / tomato / Required conditions / location / 1 / Block | `farmersdelight:budding_tomatoes` |
| criteria / tomato / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / tomato / trigger | `minecraft:placed_block` |
| criteria / wheat / Required conditions / location / 1 / Block | `minecraft:wheat` |
| criteria / wheat / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / wheat / trigger | `minecraft:placed_block` |
| display / description / translate | `advancements.farmersdelight.plant_all_crops.description` |
| display / frame | `challenge` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:cabbage` |
| display / title / translate | `advancements.farmersdelight.plant_all_crops.title` |
| requirements / 1 / 1 | `wheat` |
| requirements / 2 / 1 | `beetroot` |
| requirements / 3 / 1 | `carrot` |
| requirements / 4 / 1 | `potato` |
| requirements / 5 / 1 | `cabbage` |
| requirements / 6 / 1 | `tomato` |
| requirements / 7 / 1 | `onion` |
| requirements / 8 / 1 | `rice` |
| requirements / 9 / 1 | `melon` |
| requirements / 10 / 1 | `pumpkin` |
| requirements / 11 / 1 | `sweet_berries` |
| requirements / 12 / 1 | `sugar_cane` |
| requirements / 13 / 1 | `kelp` |
| requirements / 14 / 1 | `cocoa` |
| requirements / 15 / 1 | `nether_wart` |
| requirements / 16 / 1 | `chorus_flower` |
| requirements / 17 / 1 | `brown_mushroom` |
| requirements / 18 / 1 | `red_mushroom` |
| requirements / 19 / 1 | `glow_berries` |
| rewards / Experience per operation | 100 |
| sends telemetry event | true |

## farmersdelight/advancement/main/plant_rice

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/get_fd_seed` |
| criteria / plant rice / Required conditions / location / 1 / Block | `farmersdelight:rice` |
| criteria / plant rice / Required conditions / location / 1 / Condition kind | Block-state eligibility |
| criteria / plant rice / trigger | `minecraft:placed_block` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.plant_rice.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:rice` |
| display / title / translate | `advancements.farmersdelight.plant_rice.title` |
| requirements / 1 / 1 | `plant_rice` |
| sends telemetry event | true |

## farmersdelight/advancement/main/root

| Gameplay field | Exact value |
| --- | --- |
| criteria / seeds / trigger | `minecraft:inventory_changed` |
| display / announce to chat | false |
| display / background | `minecraft:textures/block/bricks.png` |
| display / description / translate | `advancements.farmersdelight.root.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:cooking_pot` |
| display / show toast | false |
| display / title / translate | `advancements.farmersdelight.root.title` |
| requirements / 1 / 1 | `seeds` |
| sends telemetry event | true |

## farmersdelight/advancement/main/use_cutting_board

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/craft_knife` |
| criteria / cutting board / trigger | `farmersdelight:use_cutting_board` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.use_cutting_board.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:cutting_board` |
| display / title / translate | `advancements.farmersdelight.use_cutting_board.title` |
| requirements / 1 / 1 | `cutting_board` |
| sends telemetry event | true |

## farmersdelight/advancement/main/use_skillet

| Gameplay field | Exact value |
| --- | --- |
| parent | `farmersdelight:main/place_campfire` |
| criteria / skillet / Required conditions / Item / items | `farmersdelight:skillet` |
| criteria / skillet / trigger | `minecraft:consume_item` |
| display / announce to chat | false |
| display / description / translate | `advancements.farmersdelight.use_skillet.description` |
| display / icon / Quantity | 1 |
| display / icon / Identifier | `farmersdelight:skillet` |
| display / title / translate | `advancements.farmersdelight.use_skillet.title` |
| requirements / 1 / 1 | `skillet` |
| sends telemetry event | true |

## farmersdelight/advancement/recipes/building_blocks/beetroot_crate

| Gameplay field | Exact value |
| --- | --- |
| Feature eligibility gates / 1 / Behavior kind | Ordinary crop-crate crafting enabled |
| parent | `minecraft:recipes/root` |
| criteria / has beetroot / Required conditions / items / 1 / items | `minecraft:beetroot` |
| criteria / has beetroot / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:beetroot_crate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_beetroot` |
| rewards / recipes / 1 | `farmersdelight:beetroot_crate` |

## farmersdelight/advancement/recipes/building_blocks/cabbage_crate

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has cabbage / Required conditions / items / 1 / items | `farmersdelight:cabbage` |
| criteria / has cabbage / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cabbage_crate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_cabbage` |
| rewards / recipes / 1 | `farmersdelight:cabbage_crate` |

## farmersdelight/advancement/recipes/building_blocks/carrot_crate

| Gameplay field | Exact value |
| --- | --- |
| Feature eligibility gates / 1 / Behavior kind | Ordinary crop-crate crafting enabled |
| parent | `minecraft:recipes/root` |
| criteria / has carrot / Required conditions / items / 1 / items | `minecraft:carrot` |
| criteria / has carrot / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:carrot_crate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_carrot` |
| rewards / recipes / 1 | `farmersdelight:carrot_crate` |

## farmersdelight/advancement/recipes/building_blocks/onion_crate

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has onion / Required conditions / items / 1 / items | `farmersdelight:onion` |
| criteria / has onion / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:onion_crate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_onion` |
| rewards / recipes / 1 | `farmersdelight:onion_crate` |

## farmersdelight/advancement/recipes/building_blocks/organic_compost_from_rotten_flesh

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rotten flesh / Required conditions / items / 1 / items | `minecraft:rotten_flesh` |
| criteria / has rotten flesh / trigger | `minecraft:inventory_changed` |
| criteria / has straw / Required conditions / items / 1 / items | `farmersdelight:straw` |
| criteria / has straw / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:organic_compost_from_rotten_flesh` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rotten_flesh` |
| requirements / 1 / 3 | `has_straw` |
| rewards / recipes / 1 | `farmersdelight:organic_compost_from_rotten_flesh` |

## farmersdelight/advancement/recipes/building_blocks/organic_compost_from_tree_bark

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has straw / Required conditions / items / 1 / items | `farmersdelight:straw` |
| criteria / has straw / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:organic_compost_from_tree_bark` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has tree bark / Required conditions / items / 1 / items | `farmersdelight:tree_bark` |
| criteria / has tree bark / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_tree_bark` |
| requirements / 1 / 3 | `has_straw` |
| rewards / recipes / 1 | `farmersdelight:organic_compost_from_tree_bark` |

## farmersdelight/advancement/recipes/building_blocks/packed_mud_from_straw

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has straw / Required conditions / items / 1 / items | `farmersdelight:straw` |
| criteria / has straw / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:packed_mud_from_straw` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_straw` |
| rewards / recipes / 1 | `farmersdelight:packed_mud_from_straw` |

## farmersdelight/advancement/recipes/building_blocks/potato_crate

| Gameplay field | Exact value |
| --- | --- |
| Feature eligibility gates / 1 / Behavior kind | Ordinary crop-crate crafting enabled |
| parent | `minecraft:recipes/root` |
| criteria / has potato / Required conditions / items / 1 / items | `minecraft:potato` |
| criteria / has potato / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:potato_crate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_potato` |
| rewards / recipes / 1 | `farmersdelight:potato_crate` |

## farmersdelight/advancement/recipes/building_blocks/pumpkin_from_slices

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has pumpkin slice / Required conditions / items / 1 / items | `farmersdelight:pumpkin_slice` |
| criteria / has pumpkin slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:pumpkin_from_slices` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_pumpkin_slice` |
| rewards / recipes / 1 | `farmersdelight:pumpkin_from_slices` |

## farmersdelight/advancement/recipes/building_blocks/rice_bag

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rice / Required conditions / items / 1 / items | `farmersdelight:rice` |
| criteria / has rice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rice_bag` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rice` |
| rewards / recipes / 1 | `farmersdelight:rice_bag` |

## farmersdelight/advancement/recipes/building_blocks/rice_bale

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rice panicle / Required conditions / items / 1 / items | `farmersdelight:rice_panicle` |
| criteria / has rice panicle / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rice_bale` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rice_panicle` |
| rewards / recipes / 1 | `farmersdelight:rice_bale` |

## farmersdelight/advancement/recipes/building_blocks/straw_bale

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has straw / Required conditions / items / 1 / items | `farmersdelight:straw` |
| criteria / has straw / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:straw_bale` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_straw` |
| rewards / recipes / 1 | `farmersdelight:straw_bale` |

## farmersdelight/advancement/recipes/building_blocks/tatami

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:tatami` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:tatami` |

## farmersdelight/advancement/recipes/building_blocks/tatami_block_from_full

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:tatami_block_from_full` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:tatami_block_from_full` |

## farmersdelight/advancement/recipes/building_blocks/tomato_crate

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:tomato_crate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has tomato / Required conditions / items / 1 / items | `farmersdelight:tomato` |
| criteria / has tomato / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_tomato` |
| rewards / recipes / 1 | `farmersdelight:tomato_crate` |

## farmersdelight/advancement/recipes/combat/diamond_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has diamond / Required conditions / items / 1 / items | `minecraft:diamond` |
| criteria / has diamond / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:diamond_knife` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_diamond` |
| rewards / recipes / 1 | `farmersdelight:diamond_knife` |

## farmersdelight/advancement/recipes/combat/flint_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has stick / Required conditions / items / 1 / items | `minecraft:stick` |
| criteria / has stick / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:flint_knife` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_stick` |
| rewards / recipes / 1 | `farmersdelight:flint_knife` |

## farmersdelight/advancement/recipes/combat/golden_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has gold ingot / Required conditions / items / 1 / items | `minecraft:gold_ingot` |
| criteria / has gold ingot / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:golden_knife` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_gold_ingot` |
| rewards / recipes / 1 | `farmersdelight:golden_knife` |

## farmersdelight/advancement/recipes/combat/iron_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has iron ingot / Required conditions / items / 1 / items | `minecraft:iron_ingot` |
| criteria / has iron ingot / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:iron_knife` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_iron_ingot` |
| rewards / recipes / 1 | `farmersdelight:iron_knife` |

## farmersdelight/advancement/recipes/combat/netherite_knife_smithing

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has netherite ingot / Required conditions / items / 1 / items | `minecraft:netherite_ingot` |
| criteria / has netherite ingot / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:netherite_knife_smithing` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_netherite_ingot` |
| rewards / recipes / 1 | `farmersdelight:netherite_knife_smithing` |

## farmersdelight/advancement/recipes/cooking/apple_cider

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has apple / Required conditions / items / 1 / items | `minecraft:apple` |
| criteria / has apple / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/apple_cider` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_apple` |
| rewards / recipes / 1 | `farmersdelight:cooking/apple_cider` |

## farmersdelight/advancement/recipes/cooking/baked_cod_stew

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:cod` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:potato` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `farmersdelight:tomato` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 4 | `minecraft:egg` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/baked_cod_stew` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/baked_cod_stew` |

## farmersdelight/advancement/recipes/cooking/beef_stew

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:beef` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:carrot` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:potato` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/beef_stew` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/beef_stew` |

## farmersdelight/advancement/recipes/cooking/beetroot_soup

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has beetroot / Required conditions / items / 1 / items | `minecraft:beetroot` |
| criteria / has beetroot / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/beetroot_soup` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_beetroot` |
| rewards / recipes / 1 | `farmersdelight:cooking/beetroot_soup` |

## farmersdelight/advancement/recipes/cooking/bone_broth

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has bone / Required conditions / items / 1 / items | `minecraft:bone` |
| criteria / has bone / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/bone_broth` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_bone` |
| rewards / recipes / 1 | `farmersdelight:cooking/bone_broth` |

## farmersdelight/advancement/recipes/cooking/cabbage_rolls

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:cabbage` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `farmersdelight:cabbage_leaf` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/cabbage_rolls` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/cabbage_rolls` |

## farmersdelight/advancement/recipes/cooking/chicken_soup

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:chicken` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:carrot` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/chicken_soup` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/chicken_soup` |

## farmersdelight/advancement/recipes/cooking/cooked_rice

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rice / Required conditions / items / 1 / items | `farmersdelight:rice` |
| criteria / has rice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/cooked_rice` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rice` |
| rewards / recipes / 1 | `farmersdelight:cooking/cooked_rice` |

## farmersdelight/advancement/recipes/cooking/dog_food

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:rotten_flesh` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:bone_meal` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `farmersdelight:rice` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/dog_food` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/dog_food` |

## farmersdelight/advancement/recipes/cooking/dumplings

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:wheat_dough` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `farmersdelight:cabbage` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `farmersdelight:onion` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/dumplings` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/dumplings` |

## farmersdelight/advancement/recipes/cooking/fish_stew

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:salmon` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:cod` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:tropical_fish` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 4 | `farmersdelight:tomato_sauce` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 5 | `farmersdelight:onion` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/fish_stew` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/fish_stew` |

## farmersdelight/advancement/recipes/cooking/fried_rice

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:rice` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:egg` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:carrot` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 4 | `farmersdelight:onion` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/fried_rice` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/fried_rice` |

## farmersdelight/advancement/recipes/cooking/glow_berry_custard

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:glow_berries` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:milk_bucket` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `farmersdelight:milk_bottle` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/glow_berry_custard` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/glow_berry_custard` |

## farmersdelight/advancement/recipes/cooking/hot_cocoa

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:cocoa_beans` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:milk_bucket` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `farmersdelight:milk_bottle` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/hot_cocoa` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/hot_cocoa` |

## farmersdelight/advancement/recipes/cooking/mushroom_rice

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:brown_mushroom` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:red_mushroom` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `farmersdelight:rice` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/mushroom_rice` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/mushroom_rice` |

## farmersdelight/advancement/recipes/cooking/mushroom_stew

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:brown_mushroom` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:red_mushroom` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/mushroom_stew` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/mushroom_stew` |

## farmersdelight/advancement/recipes/cooking/noodle_soup

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:raw_pasta` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:dried_kelp` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:porkchop` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/noodle_soup` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/noodle_soup` |

## farmersdelight/advancement/recipes/cooking/onion_soup

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:onion` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:bread` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:milk_bucket` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 4 | `farmersdelight:milk_bottle` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/onion_soup` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/onion_soup` |

## farmersdelight/advancement/recipes/cooking/pasta_with_meatballs

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:raw_pasta` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:beef` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `farmersdelight:tomato_sauce` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/pasta_with_meatballs` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/pasta_with_meatballs` |

## farmersdelight/advancement/recipes/cooking/pasta_with_mutton_chop

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:raw_pasta` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:mutton` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `farmersdelight:tomato_sauce` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/pasta_with_mutton_chop` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/pasta_with_mutton_chop` |

## farmersdelight/advancement/recipes/cooking/pumpkin_soup

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:pumpkin` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `farmersdelight:pumpkin_slice` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:porkchop` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 4 | `minecraft:milk_bucket` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 5 | `farmersdelight:milk_bottle` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/pumpkin_soup` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/pumpkin_soup` |

## farmersdelight/advancement/recipes/cooking/rabbit_stew

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:rabbit` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:brown_mushroom` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:red_mushroom` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 4 | `minecraft:carrot` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 5 | `minecraft:potato` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/rabbit_stew` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/rabbit_stew` |

## farmersdelight/advancement/recipes/cooking/ratatouille

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:tomato` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `farmersdelight:onion` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:beetroot` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/ratatouille` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/ratatouille` |

## farmersdelight/advancement/recipes/cooking/squid_ink_pasta

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:raw_pasta` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:ink_sac` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `farmersdelight:tomato` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/squid_ink_pasta` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/squid_ink_pasta` |

## farmersdelight/advancement/recipes/cooking/stuffed_pumpkin_block

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has pumpkin / Required conditions / items / 1 / items | `minecraft:pumpkin` |
| criteria / has pumpkin / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/stuffed_pumpkin_block` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_pumpkin` |
| rewards / recipes / 1 | `farmersdelight:cooking/stuffed_pumpkin_block` |

## farmersdelight/advancement/recipes/cooking/tomato_sauce

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/tomato_sauce` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has tomato / Required conditions / items / 1 / items | `farmersdelight:tomato` |
| criteria / has tomato / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_tomato` |
| rewards / recipes / 1 | `farmersdelight:cooking/tomato_sauce` |

## farmersdelight/advancement/recipes/cooking/vegetable_noodles

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `farmersdelight:raw_pasta` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `minecraft:brown_mushroom` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:carrot` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/vegetable_noodles` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/vegetable_noodles` |

## farmersdelight/advancement/recipes/cooking/vegetable_soup

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 1 | `minecraft:carrot` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 2 | `farmersdelight:onion` |
| criteria / has any ingredient / Required conditions / items / 1 / items / 3 | `minecraft:beetroot` |
| criteria / has any ingredient / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking/vegetable_soup` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_any_ingredient` |
| rewards / recipes / 1 | `farmersdelight:cooking/vegetable_soup` |

## farmersdelight/advancement/recipes/decorations/acacia_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has acacia trapdoor / Required conditions / items / 1 / items | `minecraft:acacia_trapdoor` |
| criteria / has acacia trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:acacia_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_acacia_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:acacia_cabinet` |

## farmersdelight/advancement/recipes/decorations/bamboo_basket

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:bamboo_basket` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:bamboo_basket` |

## farmersdelight/advancement/recipes/decorations/bamboo_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has bamboo trapdoor / Required conditions / items / 1 / items | `minecraft:bamboo_trapdoor` |
| criteria / has bamboo trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:bamboo_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_bamboo_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:bamboo_cabinet` |

## farmersdelight/advancement/recipes/decorations/birch_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has birch trapdoor / Required conditions / items / 1 / items | `minecraft:birch_trapdoor` |
| criteria / has birch trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:birch_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_birch_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:birch_cabinet` |

## farmersdelight/advancement/recipes/decorations/black_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:black_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:black_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/black_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:black_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:black_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/blue_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:blue_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:blue_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/blue_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:blue_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:blue_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/brown_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:brown_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:brown_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/brown_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:brown_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:brown_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/canvas_rug

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:canvas_rug` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:canvas_rug` |

## farmersdelight/advancement/recipes/decorations/canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:canvas_sign` |

## farmersdelight/advancement/recipes/decorations/cherry_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has cherry trapdoor / Required conditions / items / 1 / items | `minecraft:cherry_trapdoor` |
| criteria / has cherry trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cherry_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_cherry_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:cherry_cabinet` |

## farmersdelight/advancement/recipes/decorations/cooking_pot

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has iron ingot / Required conditions / items / 1 / items | `minecraft:iron_ingot` |
| criteria / has iron ingot / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooking_pot` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_iron_ingot` |
| rewards / recipes / 1 | `farmersdelight:cooking_pot` |

## farmersdelight/advancement/recipes/decorations/crimson_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has crimson trapdoor / Required conditions / items / 1 / items | `minecraft:crimson_trapdoor` |
| criteria / has crimson trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:crimson_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_crimson_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:crimson_cabinet` |

## farmersdelight/advancement/recipes/decorations/cutting_board

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has stick / Required conditions / items / 1 / items | `minecraft:stick` |
| criteria / has stick / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cutting_board` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_stick` |
| rewards / recipes / 1 | `farmersdelight:cutting_board` |

## farmersdelight/advancement/recipes/decorations/cyan_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cyan_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:cyan_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/cyan_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cyan_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:cyan_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/dark_oak_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has dark oak trapdoor / Required conditions / items / 1 / items | `minecraft:dark_oak_trapdoor` |
| criteria / has dark oak trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:dark_oak_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_dark_oak_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:dark_oak_cabinet` |

## farmersdelight/advancement/recipes/decorations/full_tatami_mat

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:full_tatami_mat` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:full_tatami_mat` |

## farmersdelight/advancement/recipes/decorations/full_tatami_mat_from_halves

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:full_tatami_mat_from_halves` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:full_tatami_mat_from_halves` |

## farmersdelight/advancement/recipes/decorations/gray_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:gray_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:gray_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/gray_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:gray_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:gray_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/green_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:green_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:green_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/green_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:green_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:green_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/half_tatami_mat

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:half_tatami_mat` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:half_tatami_mat` |

## farmersdelight/advancement/recipes/decorations/hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/jungle_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has jungle trapdoor / Required conditions / items / 1 / items | `minecraft:jungle_trapdoor` |
| criteria / has jungle trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:jungle_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_jungle_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:jungle_cabinet` |

## farmersdelight/advancement/recipes/decorations/light_blue_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:light_blue_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:light_blue_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/light_blue_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:light_blue_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:light_blue_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/light_gray_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:light_gray_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:light_gray_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/light_gray_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:light_gray_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:light_gray_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/lime_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:lime_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:lime_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/lime_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:lime_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:lime_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/magenta_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:magenta_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:magenta_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/magenta_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:magenta_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:magenta_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/mangrove_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has mangrove trapdoor / Required conditions / items / 1 / items | `minecraft:mangrove_trapdoor` |
| criteria / has mangrove trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:mangrove_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_mangrove_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:mangrove_cabinet` |

## farmersdelight/advancement/recipes/decorations/oak_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has oak trapdoor / Required conditions / items / 1 / items | `minecraft:oak_trapdoor` |
| criteria / has oak trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:oak_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_oak_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:oak_cabinet` |

## farmersdelight/advancement/recipes/decorations/orange_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:orange_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:orange_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/orange_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:orange_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:orange_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/painting_from_canvas

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:painting_from_canvas` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:painting_from_canvas` |

## farmersdelight/advancement/recipes/decorations/pink_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:pink_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:pink_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/pink_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:pink_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:pink_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/purple_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:purple_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:purple_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/purple_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:purple_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:purple_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/red_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:red_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:red_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/red_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:red_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:red_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/rope

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has straw / Required conditions / items / 1 / items | `farmersdelight:straw` |
| criteria / has straw / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rope` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_straw` |
| rewards / recipes / 1 | `farmersdelight:rope` |

## farmersdelight/advancement/recipes/decorations/rope_fence

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rope / Required conditions / items / 1 / items | `farmersdelight:rope` |
| criteria / has rope / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rope_fence` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rope` |
| rewards / recipes / 1 | `farmersdelight:rope_fence` |

## farmersdelight/advancement/recipes/decorations/rope_fence_gate

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rope / Required conditions / items / 1 / items | `farmersdelight:rope` |
| criteria / has rope / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rope_fence_gate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rope` |
| rewards / recipes / 1 | `farmersdelight:rope_fence_gate` |

## farmersdelight/advancement/recipes/decorations/rope_from_safety_net

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has safety net / Required conditions / items / 1 / items | `farmersdelight:safety_net` |
| criteria / has safety net / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rope_from_safety_net` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_safety_net` |
| rewards / recipes / 1 | `farmersdelight:rope_from_safety_net` |

## farmersdelight/advancement/recipes/decorations/safety_net

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rope / Required conditions / items / 1 / items | `farmersdelight:rope` |
| criteria / has rope / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:safety_net` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rope` |
| rewards / recipes / 1 | `farmersdelight:safety_net` |

## farmersdelight/advancement/recipes/decorations/scaffolding_from_canvas

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:scaffolding_from_canvas` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:scaffolding_from_canvas` |

## farmersdelight/advancement/recipes/decorations/skillet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has brick / Required conditions / items / 1 / items | `minecraft:brick` |
| criteria / has brick / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:skillet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_brick` |
| rewards / recipes / 1 | `farmersdelight:skillet` |

## farmersdelight/advancement/recipes/decorations/spruce_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has spruce trapdoor / Required conditions / items / 1 / items | `minecraft:spruce_trapdoor` |
| criteria / has spruce trapdoor / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:spruce_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_spruce_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:spruce_cabinet` |

## farmersdelight/advancement/recipes/decorations/stove

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has campfire / Required conditions / items / 1 / items | `minecraft:campfire` |
| criteria / has campfire / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:stove` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_campfire` |
| rewards / recipes / 1 | `farmersdelight:stove` |

## farmersdelight/advancement/recipes/decorations/warped_cabinet

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:warped_cabinet` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has warped trapdoor / Required conditions / items / 1 / items | `minecraft:warped_trapdoor` |
| criteria / has warped trapdoor / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_warped_trapdoor` |
| rewards / recipes / 1 | `farmersdelight:warped_cabinet` |

## farmersdelight/advancement/recipes/decorations/white_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:white_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:white_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/white_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:white_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:white_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/wooden_basket

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:wooden_basket` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:wooden_basket` |

## farmersdelight/advancement/recipes/decorations/yellow_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas sign / Required conditions / items / 1 / items | `farmersdelight:canvas_sign` |
| criteria / has canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:yellow_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:yellow_canvas_sign` |

## farmersdelight/advancement/recipes/decorations/yellow_hanging_canvas_sign

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has hanging canvas sign / Required conditions / items / 1 / items | `farmersdelight:hanging_canvas_sign` |
| criteria / has hanging canvas sign / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:yellow_hanging_canvas_sign` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_hanging_canvas_sign` |
| rewards / recipes / 1 | `farmersdelight:yellow_hanging_canvas_sign` |

## farmersdelight/advancement/recipes/food/apple_pie

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has pie crust / Required conditions / items / 1 / items | `farmersdelight:pie_crust` |
| criteria / has pie crust / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:apple_pie` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_pie_crust` |
| rewards / recipes / 1 | `farmersdelight:apple_pie` |

## farmersdelight/advancement/recipes/food/apple_pie_from_slices

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has apple pie slice / Required conditions / items / 1 / items | `farmersdelight:apple_pie_slice` |
| criteria / has apple pie slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:apple_pie_from_slices` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_apple_pie_slice` |
| rewards / recipes / 1 | `farmersdelight:apple_pie_from_slices` |

## farmersdelight/advancement/recipes/food/bacon_and_eggs

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has bacon / Required conditions / items / 1 / items | `farmersdelight:cooked_bacon` |
| criteria / has bacon / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:bacon_and_eggs` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_bacon` |
| rewards / recipes / 1 | `farmersdelight:bacon_and_eggs` |

## farmersdelight/advancement/recipes/food/bacon_sandwich

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has bacon / Required conditions / items / 1 / items | `farmersdelight:cooked_bacon` |
| criteria / has bacon / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:bacon_sandwich` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_bacon` |
| rewards / recipes / 1 | `farmersdelight:bacon_sandwich` |

## farmersdelight/advancement/recipes/food/barbecue_stick

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has onion / Required conditions / items / 1 / items | `farmersdelight:onion` |
| criteria / has onion / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:barbecue_stick` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has tomato / Required conditions / items / 1 / items | `farmersdelight:tomato` |
| criteria / has tomato / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_tomato` |
| requirements / 1 / 3 | `has_onion` |
| rewards / recipes / 1 | `farmersdelight:barbecue_stick` |

## farmersdelight/advancement/recipes/food/beef_patty

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / beef patty / Required conditions / items / 1 / items | `farmersdelight:minced_beef` |
| criteria / beef patty / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:beef_patty` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `beef_patty` |
| rewards / recipes / 1 | `farmersdelight:beef_patty` |

## farmersdelight/advancement/recipes/food/beef_patty_from_campfire_cooking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / beef patty / Required conditions / items / 1 / items | `farmersdelight:minced_beef` |
| criteria / beef patty / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:beef_patty_from_campfire_cooking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `beef_patty` |
| rewards / recipes / 1 | `farmersdelight:beef_patty_from_campfire_cooking` |

## farmersdelight/advancement/recipes/food/beef_patty_from_smoking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / beef patty / Required conditions / items / 1 / items | `farmersdelight:minced_beef` |
| criteria / beef patty / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:beef_patty_from_smoking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `beef_patty` |
| rewards / recipes / 1 | `farmersdelight:beef_patty_from_smoking` |

## farmersdelight/advancement/recipes/food/beetroot_from_crate

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has beetroot crate / Required conditions / items / 1 / items | `farmersdelight:beetroot_crate` |
| criteria / has beetroot crate / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:beetroot_from_crate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_beetroot_crate` |
| rewards / recipes / 1 | `farmersdelight:beetroot_from_crate` |

## farmersdelight/advancement/recipes/food/bread_from_smelting

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has dough / Required conditions / items / 1 / items | `farmersdelight:wheat_dough` |
| criteria / has dough / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:bread_from_smelting` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_dough` |
| rewards / recipes / 1 | `farmersdelight:bread_from_smelting` |

## farmersdelight/advancement/recipes/food/bread_from_smoking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has dough / Required conditions / items / 1 / items | `farmersdelight:wheat_dough` |
| criteria / has dough / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:bread_from_smoking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_dough` |
| rewards / recipes / 1 | `farmersdelight:bread_from_smoking` |

## farmersdelight/advancement/recipes/food/cabbage

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has cabbage crate / Required conditions / items / 1 / items | `farmersdelight:cabbage_crate` |
| criteria / has cabbage crate / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cabbage` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_cabbage_crate` |
| rewards / recipes / 1 | `farmersdelight:cabbage` |

## farmersdelight/advancement/recipes/food/cabbage_from_leaves

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has cabbage leaf / Required conditions / items / 1 / items | `farmersdelight:cabbage_leaf` |
| criteria / has cabbage leaf / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cabbage_from_leaves` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_cabbage_leaf` |
| rewards / recipes / 1 | `farmersdelight:cabbage_from_leaves` |

## farmersdelight/advancement/recipes/food/cake_from_milk_bottle

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has milk bottle / Required conditions / items / 1 / items | `farmersdelight:milk_bottle` |
| criteria / has milk bottle / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cake_from_milk_bottle` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_milk_bottle` |
| rewards / recipes / 1 | `farmersdelight:cake_from_milk_bottle` |

## farmersdelight/advancement/recipes/food/cake_from_slices

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has cake slice / Required conditions / items / 1 / items | `farmersdelight:cake_slice` |
| criteria / has cake slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cake_from_slices` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_cake_slice` |
| rewards / recipes / 1 | `farmersdelight:cake_from_slices` |

## farmersdelight/advancement/recipes/food/carrot_from_crate

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has carrot crate / Required conditions / items / 1 / items | `farmersdelight:carrot_crate` |
| criteria / has carrot crate / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:carrot_from_crate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_carrot_crate` |
| rewards / recipes / 1 | `farmersdelight:carrot_from_crate` |

## farmersdelight/advancement/recipes/food/chicken_sandwich

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has cooked chicken / Required conditions / items / 1 / items | `minecraft:cooked_chicken` |
| criteria / has cooked chicken / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:chicken_sandwich` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_cooked_chicken` |
| rewards / recipes / 1 | `farmersdelight:chicken_sandwich` |

## farmersdelight/advancement/recipes/food/chocolate_pie

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has pie crust / Required conditions / items / 1 / items | `farmersdelight:pie_crust` |
| criteria / has pie crust / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:chocolate_pie` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_pie_crust` |
| rewards / recipes / 1 | `farmersdelight:chocolate_pie` |

## farmersdelight/advancement/recipes/food/chocolate_pie_from_slices

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has chocolate pie slice / Required conditions / items / 1 / items | `farmersdelight:chocolate_pie_slice` |
| criteria / has chocolate pie slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:chocolate_pie_from_slices` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_chocolate_pie_slice` |
| rewards / recipes / 1 | `farmersdelight:chocolate_pie_from_slices` |

## farmersdelight/advancement/recipes/food/cod_roll

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has cod slice / Required conditions / items / 1 / items | `farmersdelight:cod_slice` |
| criteria / has cod slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cod_roll` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_cod_slice` |
| rewards / recipes / 1 | `farmersdelight:cod_roll` |

## farmersdelight/advancement/recipes/food/cooked_bacon

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked bacon / Required conditions / items / 1 / items | `farmersdelight:bacon` |
| criteria / cooked bacon / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_bacon` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_bacon` |
| rewards / recipes / 1 | `farmersdelight:cooked_bacon` |

## farmersdelight/advancement/recipes/food/cooked_bacon_from_campfire_cooking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked bacon / Required conditions / items / 1 / items | `farmersdelight:bacon` |
| criteria / cooked bacon / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_bacon_from_campfire_cooking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_bacon` |
| rewards / recipes / 1 | `farmersdelight:cooked_bacon_from_campfire_cooking` |

## farmersdelight/advancement/recipes/food/cooked_bacon_from_smoking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked bacon / Required conditions / items / 1 / items | `farmersdelight:bacon` |
| criteria / cooked bacon / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_bacon_from_smoking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_bacon` |
| rewards / recipes / 1 | `farmersdelight:cooked_bacon_from_smoking` |

## farmersdelight/advancement/recipes/food/cooked_chicken_cuts

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked chicken cuts / Required conditions / items / 1 / items | `farmersdelight:chicken_cuts` |
| criteria / cooked chicken cuts / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_chicken_cuts` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_chicken_cuts` |
| rewards / recipes / 1 | `farmersdelight:cooked_chicken_cuts` |

## farmersdelight/advancement/recipes/food/cooked_chicken_cuts_from_campfire_cooking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked chicken cuts / Required conditions / items / 1 / items | `farmersdelight:chicken_cuts` |
| criteria / cooked chicken cuts / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_chicken_cuts_from_campfire_cooking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_chicken_cuts` |
| rewards / recipes / 1 | `farmersdelight:cooked_chicken_cuts_from_campfire_cooking` |

## farmersdelight/advancement/recipes/food/cooked_chicken_cuts_from_smoking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked chicken cuts / Required conditions / items / 1 / items | `farmersdelight:chicken_cuts` |
| criteria / cooked chicken cuts / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_chicken_cuts_from_smoking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_chicken_cuts` |
| rewards / recipes / 1 | `farmersdelight:cooked_chicken_cuts_from_smoking` |

## farmersdelight/advancement/recipes/food/cooked_cod_slice

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked cod slice / Required conditions / items / 1 / items | `farmersdelight:cod_slice` |
| criteria / cooked cod slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_cod_slice` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_cod_slice` |
| rewards / recipes / 1 | `farmersdelight:cooked_cod_slice` |

## farmersdelight/advancement/recipes/food/cooked_cod_slice_from_campfire_cooking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked cod slice / Required conditions / items / 1 / items | `farmersdelight:cod_slice` |
| criteria / cooked cod slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_cod_slice_from_campfire_cooking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_cod_slice` |
| rewards / recipes / 1 | `farmersdelight:cooked_cod_slice_from_campfire_cooking` |

## farmersdelight/advancement/recipes/food/cooked_cod_slice_from_smoking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked cod slice / Required conditions / items / 1 / items | `farmersdelight:cod_slice` |
| criteria / cooked cod slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_cod_slice_from_smoking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_cod_slice` |
| rewards / recipes / 1 | `farmersdelight:cooked_cod_slice_from_smoking` |

## farmersdelight/advancement/recipes/food/cooked_mutton_chops

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked mutton chops / Required conditions / items / 1 / items | `farmersdelight:mutton_chops` |
| criteria / cooked mutton chops / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_mutton_chops` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_mutton_chops` |
| rewards / recipes / 1 | `farmersdelight:cooked_mutton_chops` |

## farmersdelight/advancement/recipes/food/cooked_mutton_chops_from_campfire_cooking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked mutton chops / Required conditions / items / 1 / items | `farmersdelight:mutton_chops` |
| criteria / cooked mutton chops / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_mutton_chops_from_campfire_cooking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_mutton_chops` |
| rewards / recipes / 1 | `farmersdelight:cooked_mutton_chops_from_campfire_cooking` |

## farmersdelight/advancement/recipes/food/cooked_mutton_chops_from_smoking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked mutton chops / Required conditions / items / 1 / items | `farmersdelight:mutton_chops` |
| criteria / cooked mutton chops / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_mutton_chops_from_smoking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_mutton_chops` |
| rewards / recipes / 1 | `farmersdelight:cooked_mutton_chops_from_smoking` |

## farmersdelight/advancement/recipes/food/cooked_salmon_slice

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked salmon slice / Required conditions / items / 1 / items | `farmersdelight:salmon_slice` |
| criteria / cooked salmon slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_salmon_slice` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_salmon_slice` |
| rewards / recipes / 1 | `farmersdelight:cooked_salmon_slice` |

## farmersdelight/advancement/recipes/food/cooked_salmon_slice_from_campfire_cooking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked salmon slice / Required conditions / items / 1 / items | `farmersdelight:salmon_slice` |
| criteria / cooked salmon slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_salmon_slice_from_campfire_cooking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_salmon_slice` |
| rewards / recipes / 1 | `farmersdelight:cooked_salmon_slice_from_campfire_cooking` |

## farmersdelight/advancement/recipes/food/cooked_salmon_slice_from_smoking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / cooked salmon slice / Required conditions / items / 1 / items | `farmersdelight:salmon_slice` |
| criteria / cooked salmon slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:cooked_salmon_slice_from_smoking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `cooked_salmon_slice` |
| rewards / recipes / 1 | `farmersdelight:cooked_salmon_slice_from_smoking` |

## farmersdelight/advancement/recipes/food/egg_sandwich

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has fried egg / Required conditions / items / 1 / items | `farmersdelight:fried_egg` |
| criteria / has fried egg / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:egg_sandwich` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_fried_egg` |
| rewards / recipes / 1 | `farmersdelight:egg_sandwich` |

## farmersdelight/advancement/recipes/food/fried_egg

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / fried egg / Required conditions / items / 1 / items | `minecraft:egg` |
| criteria / fried egg / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:fried_egg` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `fried_egg` |
| rewards / recipes / 1 | `farmersdelight:fried_egg` |

## farmersdelight/advancement/recipes/food/fried_egg_from_campfire_cooking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / fried egg / Required conditions / items / 1 / items | `minecraft:egg` |
| criteria / fried egg / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:fried_egg_from_campfire_cooking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `fried_egg` |
| rewards / recipes / 1 | `farmersdelight:fried_egg_from_campfire_cooking` |

## farmersdelight/advancement/recipes/food/fried_egg_from_smoking

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / fried egg / Required conditions / items / 1 / items | `minecraft:egg` |
| criteria / fried egg / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:fried_egg_from_smoking` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `fried_egg` |
| rewards / recipes / 1 | `farmersdelight:fried_egg_from_smoking` |

## farmersdelight/advancement/recipes/food/fruit_salad

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has fruits / Required conditions / items / 1 / items | `minecraft:melon_slice` |
| criteria / has fruits / Required conditions / items / 2 / items | `minecraft:sweet_berries` |
| criteria / has fruits / Required conditions / items / 3 / items | `minecraft:apple` |
| criteria / has fruits / Required conditions / items / 4 / items | `farmersdelight:pumpkin_slice` |
| criteria / has fruits / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:fruit_salad` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_fruits` |
| rewards / recipes / 1 | `farmersdelight:fruit_salad` |

## farmersdelight/advancement/recipes/food/gleaming_salad_block

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has glow berries / Required conditions / items / 1 / items | `farmersdelight:glow_berry_custard` |
| criteria / has glow berries / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:gleaming_salad_block` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_glow_berries` |
| rewards / recipes / 1 | `farmersdelight:gleaming_salad_block` |

## farmersdelight/advancement/recipes/food/grilled_salmon

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has salmon / Required conditions / items / 1 / items | `minecraft:salmon` |
| criteria / has salmon / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:grilled_salmon` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_salmon` |
| rewards / recipes / 1 | `farmersdelight:grilled_salmon` |

## farmersdelight/advancement/recipes/food/hamburger

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has beef patty / Required conditions / items / 1 / items | `farmersdelight:beef_patty` |
| criteria / has beef patty / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:hamburger` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_beef_patty` |
| rewards / recipes / 1 | `farmersdelight:hamburger` |

## farmersdelight/advancement/recipes/food/honey_cookie

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has honey bottle / Required conditions / items / 1 / items | `minecraft:honey_bottle` |
| criteria / has honey bottle / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:honey_cookie` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_honey_bottle` |
| rewards / recipes / 1 | `farmersdelight:honey_cookie` |

## farmersdelight/advancement/recipes/food/honey_glazed_ham_block

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has smoked ham / Required conditions / items / 1 / items | `farmersdelight:smoked_ham` |
| criteria / has smoked ham / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:honey_glazed_ham_block` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_smoked_ham` |
| rewards / recipes / 1 | `farmersdelight:honey_glazed_ham_block` |

## farmersdelight/advancement/recipes/food/horse_feed

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has golden carrot / Required conditions / items / 1 / items | `minecraft:golden_carrot` |
| criteria / has golden carrot / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:horse_feed` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_golden_carrot` |
| rewards / recipes / 1 | `farmersdelight:horse_feed` |

## farmersdelight/advancement/recipes/food/kelp_roll

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has dried kelp / Required conditions / items / 1 / items | `minecraft:dried_kelp` |
| criteria / has dried kelp / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:kelp_roll` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_dried_kelp` |
| rewards / recipes / 1 | `farmersdelight:kelp_roll` |

## farmersdelight/advancement/recipes/food/melon_juice

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has melon slice / Required conditions / items / 1 / items | `minecraft:melon_slice` |
| criteria / has melon slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:melon_juice` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_melon_slice` |
| rewards / recipes / 1 | `farmersdelight:melon_juice` |

## farmersdelight/advancement/recipes/food/melon_popsicle

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has melon / Required conditions / items / 1 / items | `minecraft:melon_slice` |
| criteria / has melon / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:melon_popsicle` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_melon` |
| rewards / recipes / 1 | `farmersdelight:melon_popsicle` |

## farmersdelight/advancement/recipes/food/milk_bottle

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has milk bucket / Required conditions / items / 1 / items | `minecraft:milk_bucket` |
| criteria / has milk bucket / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:milk_bottle` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_milk_bucket` |
| rewards / recipes / 1 | `farmersdelight:milk_bottle` |

## farmersdelight/advancement/recipes/food/mixed_salad

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has bowl / Required conditions / items / 1 / items | `minecraft:bowl` |
| criteria / has bowl / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:mixed_salad` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_bowl` |
| rewards / recipes / 1 | `farmersdelight:mixed_salad` |

## farmersdelight/advancement/recipes/food/mutton_wrap

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has mutton / Required conditions / items / 1 / items | `minecraft:cooked_mutton` |
| criteria / has mutton / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:mutton_wrap` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_mutton` |
| rewards / recipes / 1 | `farmersdelight:mutton_wrap` |

## farmersdelight/advancement/recipes/food/nether_salad

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has bowl / Required conditions / items / 1 / items | `minecraft:bowl` |
| criteria / has bowl / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:nether_salad` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_bowl` |
| rewards / recipes / 1 | `farmersdelight:nether_salad` |

## farmersdelight/advancement/recipes/food/onion

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has onion crate / Required conditions / items / 1 / items | `farmersdelight:onion_crate` |
| criteria / has onion crate / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:onion` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_onion_crate` |
| rewards / recipes / 1 | `farmersdelight:onion` |

## farmersdelight/advancement/recipes/food/pie_crust

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:pie_crust` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has wheat / Required conditions / items / 1 / items | `minecraft:wheat` |
| criteria / has wheat / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_wheat` |
| rewards / recipes / 1 | `farmersdelight:pie_crust` |

## farmersdelight/advancement/recipes/food/potato_from_crate

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has potato crate / Required conditions / items / 1 / items | `farmersdelight:potato_crate` |
| criteria / has potato crate / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:potato_from_crate` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_potato_crate` |
| rewards / recipes / 1 | `farmersdelight:potato_from_crate` |

## farmersdelight/advancement/recipes/food/pumpkin_pie_from_pie_crust

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has pie crust / Required conditions / items / 1 / items | `farmersdelight:pie_crust` |
| criteria / has pie crust / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:pumpkin_pie_from_pie_crust` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_pie_crust` |
| rewards / recipes / 1 | `farmersdelight:pumpkin_pie_from_pie_crust` |

## farmersdelight/advancement/recipes/food/pumpkin_pie_from_slices

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has pumpkin pie slice / Required conditions / items / 1 / items | `farmersdelight:pumpkin_pie_slice` |
| criteria / has pumpkin pie slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:pumpkin_pie_from_slices` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_pumpkin_pie_slice` |
| rewards / recipes / 1 | `farmersdelight:pumpkin_pie_from_slices` |

## farmersdelight/advancement/recipes/food/rice

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rice panicle / Required conditions / items / 1 / items | `farmersdelight:rice_panicle` |
| criteria / has rice panicle / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rice` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rice_panicle` |
| rewards / recipes / 1 | `farmersdelight:rice` |

## farmersdelight/advancement/recipes/food/rice_from_bag

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rice bag / Required conditions / items / 1 / items | `farmersdelight:rice_bag` |
| criteria / has rice bag / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rice_from_bag` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rice_bag` |
| rewards / recipes / 1 | `farmersdelight:rice_from_bag` |

## farmersdelight/advancement/recipes/food/rice_panicle

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rice bale / Required conditions / items / 1 / items | `farmersdelight:rice_bale` |
| criteria / has rice bale / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rice_panicle` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rice_bale` |
| rewards / recipes / 1 | `farmersdelight:rice_panicle` |

## farmersdelight/advancement/recipes/food/rice_roll_medley_block

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has rice roll / Required conditions / items / 1 / items | `farmersdelight:salmon_roll` |
| criteria / has rice roll / Required conditions / items / 2 / items | `farmersdelight:cod_roll` |
| criteria / has rice roll / Required conditions / items / 3 / items | `farmersdelight:kelp_roll_slice` |
| criteria / has rice roll / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:rice_roll_medley_block` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_rice_roll` |
| rewards / recipes / 1 | `farmersdelight:rice_roll_medley_block` |

## farmersdelight/advancement/recipes/food/roast_chicken_block

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has cooked chicken / Required conditions / items / 1 / items | `minecraft:cooked_chicken` |
| criteria / has cooked chicken / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:roast_chicken_block` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_cooked_chicken` |
| rewards / recipes / 1 | `farmersdelight:roast_chicken_block` |

## farmersdelight/advancement/recipes/food/roasted_mutton_chops

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has mutton / Required conditions / items / 1 / items | `minecraft:cooked_mutton` |
| criteria / has mutton / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:roasted_mutton_chops` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_mutton` |
| rewards / recipes / 1 | `farmersdelight:roasted_mutton_chops` |

## farmersdelight/advancement/recipes/food/salmon_roll

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has salmon slice / Required conditions / items / 1 / items | `farmersdelight:salmon_slice` |
| criteria / has salmon slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:salmon_roll` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_salmon_slice` |
| rewards / recipes / 1 | `farmersdelight:salmon_roll` |

## farmersdelight/advancement/recipes/food/shepherds_pie_block

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has cooked mutton / Required conditions / items / 1 / items | `minecraft:cooked_mutton` |
| criteria / has cooked mutton / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:shepherds_pie_block` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_cooked_mutton` |
| rewards / recipes / 1 | `farmersdelight:shepherds_pie_block` |

## farmersdelight/advancement/recipes/food/smoked_ham

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has ham / Required conditions / items / 1 / items | `farmersdelight:ham` |
| criteria / has ham / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:smoked_ham` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_ham` |
| rewards / recipes / 1 | `farmersdelight:smoked_ham` |

## farmersdelight/advancement/recipes/food/steak_and_potatoes

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has baked potato / Required conditions / items / 1 / items | `minecraft:baked_potato` |
| criteria / has baked potato / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:steak_and_potatoes` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_baked_potato` |
| rewards / recipes / 1 | `farmersdelight:steak_and_potatoes` |

## farmersdelight/advancement/recipes/food/stuffed_potato

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has baked potato / Required conditions / items / 1 / items | `minecraft:baked_potato` |
| criteria / has baked potato / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:stuffed_potato` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_baked_potato` |
| rewards / recipes / 1 | `farmersdelight:stuffed_potato` |

## farmersdelight/advancement/recipes/food/sweet_berry_cheesecake

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has pie crust / Required conditions / items / 1 / items | `farmersdelight:pie_crust` |
| criteria / has pie crust / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:sweet_berry_cheesecake` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_pie_crust` |
| rewards / recipes / 1 | `farmersdelight:sweet_berry_cheesecake` |

## farmersdelight/advancement/recipes/food/sweet_berry_cheesecake_from_slices

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has sweet berry cheesecake slice / Required conditions / items / 1 / items | `farmersdelight:sweet_berry_cheesecake_slice` |
| criteria / has sweet berry cheesecake slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:sweet_berry_cheesecake_from_slices` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_sweet_berry_cheesecake_slice` |
| rewards / recipes / 1 | `farmersdelight:sweet_berry_cheesecake_from_slices` |

## farmersdelight/advancement/recipes/food/sweet_berry_cookie

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has sweet berries / Required conditions / items / 1 / items | `minecraft:sweet_berries` |
| criteria / has sweet berries / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:sweet_berry_cookie` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_sweet_berries` |
| rewards / recipes / 1 | `farmersdelight:sweet_berry_cookie` |

## farmersdelight/advancement/recipes/food/tomato

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:tomato` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has tomato crate / Required conditions / items / 1 / items | `farmersdelight:tomato_crate` |
| criteria / has tomato crate / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_tomato_crate` |
| rewards / recipes / 1 | `farmersdelight:tomato` |

## farmersdelight/advancement/recipes/food/wheat_dough_from_egg

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:wheat_dough_from_egg` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has wheat / Required conditions / items / 1 / items | `minecraft:wheat` |
| criteria / has wheat / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_wheat` |
| rewards / recipes / 1 | `farmersdelight:wheat_dough_from_egg` |

## farmersdelight/advancement/recipes/misc/book_from_canvas

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas / Required conditions / items / 1 / items | `farmersdelight:canvas` |
| criteria / has canvas / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:book_from_canvas` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas` |
| rewards / recipes / 1 | `farmersdelight:book_from_canvas` |

## farmersdelight/advancement/recipes/misc/canvas

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has straw / Required conditions / items / 1 / items | `farmersdelight:straw` |
| criteria / has straw / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:canvas` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_straw` |
| rewards / recipes / 1 | `farmersdelight:canvas` |

## farmersdelight/advancement/recipes/misc/canvas_from_canvas_rug

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has canvas rug / Required conditions / items / 1 / items | `farmersdelight:canvas_rug` |
| criteria / has canvas rug / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:canvas_from_canvas_rug` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_canvas_rug` |
| rewards / recipes / 1 | `farmersdelight:canvas_from_canvas_rug` |

## farmersdelight/advancement/recipes/misc/gold_nugget_from_blasting_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has golden knife / Required conditions / items / 1 / items | `farmersdelight:golden_knife` |
| criteria / has golden knife / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:gold_nugget_from_blasting_knife` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_golden_knife` |
| rewards / recipes / 1 | `farmersdelight:gold_nugget_from_blasting_knife` |

## farmersdelight/advancement/recipes/misc/gold_nugget_from_smelting_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has golden knife / Required conditions / items / 1 / items | `farmersdelight:golden_knife` |
| criteria / has golden knife / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:gold_nugget_from_smelting_knife` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_golden_knife` |
| rewards / recipes / 1 | `farmersdelight:gold_nugget_from_smelting_knife` |

## farmersdelight/advancement/recipes/misc/iron_nugget_from_blasting_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has iron knife / Required conditions / items / 1 / items | `farmersdelight:iron_knife` |
| criteria / has iron knife / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:iron_nugget_from_blasting_knife` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_iron_knife` |
| rewards / recipes / 1 | `farmersdelight:iron_nugget_from_blasting_knife` |

## farmersdelight/advancement/recipes/misc/iron_nugget_from_smelting_knife

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has iron knife / Required conditions / items / 1 / items | `farmersdelight:iron_knife` |
| criteria / has iron knife / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:iron_nugget_from_smelting_knife` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_iron_knife` |
| rewards / recipes / 1 | `farmersdelight:iron_nugget_from_smelting_knife` |

## farmersdelight/advancement/recipes/misc/milk_bucket_from_bottles

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has milk bottle / Required conditions / items / 1 / items | `farmersdelight:milk_bottle` |
| criteria / has milk bottle / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:milk_bucket_from_bottles` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_milk_bottle` |
| rewards / recipes / 1 | `farmersdelight:milk_bucket_from_bottles` |

## farmersdelight/advancement/recipes/misc/paper_from_tree_bark

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:paper_from_tree_bark` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has tree bark / Required conditions / items / 1 / items | `farmersdelight:tree_bark` |
| criteria / has tree bark / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_tree_bark` |
| rewards / recipes / 1 | `farmersdelight:paper_from_tree_bark` |

## farmersdelight/advancement/recipes/misc/pumpkin_seeds_from_slice

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has pumpkin slice / Required conditions / items / 1 / items | `farmersdelight:pumpkin_slice` |
| criteria / has pumpkin slice / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:pumpkin_seeds_from_slice` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_pumpkin_slice` |
| rewards / recipes / 1 | `farmersdelight:pumpkin_seeds_from_slice` |

## farmersdelight/advancement/recipes/misc/straw

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has straw bale / Required conditions / items / 1 / items | `farmersdelight:straw_bale` |
| criteria / has straw bale / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:straw` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_straw_bale` |
| rewards / recipes / 1 | `farmersdelight:straw` |

## farmersdelight/advancement/recipes/misc/tomato_seeds

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:tomato_seeds` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| criteria / has tomato / Required conditions / items / 1 / items | `farmersdelight:tomato` |
| criteria / has tomato / trigger | `minecraft:inventory_changed` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_tomato` |
| rewards / recipes / 1 | `farmersdelight:tomato_seeds` |

## farmersdelight/advancement/recipes/tools/lead_from_straw

| Gameplay field | Exact value |
| --- | --- |
| parent | `minecraft:recipes/root` |
| criteria / has straw / Required conditions / items / 1 / items | `farmersdelight:straw` |
| criteria / has straw / trigger | `minecraft:inventory_changed` |
| criteria / has the recipe / Required conditions / recipe | `farmersdelight:lead_from_straw` |
| criteria / has the recipe / trigger | `minecraft:recipe_unlocked` |
| requirements / 1 / 1 | `has_the_recipe` |
| requirements / 1 / 2 | `has_straw` |
| rewards / recipes / 1 | `farmersdelight:lead_from_straw` |

## farmersdelight/damage_type/stove_burn

| Gameplay field | Exact value |
| --- | --- |
| effects | `burning` |
| exhaustion | 0.1 |
| message id | `farmersdelight.stove` |
| scaling | `when_caused_by_living_non_player` |

## farmersdelight/enchantment/backstabbing

| Gameplay field | Exact value |
| --- | --- |
| anvil cost | 2 |
| description / translate | `enchantment.farmersdelight.backstabbing` |
| effects / backstabbing / 1 / effect / Behavior kind | `minecraft:multiply` |
| effects / backstabbing / 1 / effect / factor / Behavior kind | `minecraft:linear` |
| effects / backstabbing / 1 / effect / factor / base | 1.4 |
| effects / backstabbing / 1 / effect / factor / per level above first | 0.2 |
| max cost / base | 50 |
| max cost / per level above first | 8 |
| max level | 3 |
| min cost / base | 15 |
| min cost / per level above first | 9 |
| slots / 1 | `mainhand` |
| supported items | `#farmersdelight:enchantable/knife` |
| Relative weight | 5 |

## farmersdelight/neoforge/biome_modifier/brown_mushroom_colony

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:add_features_by_filter` |
| allowed biomes | `#c:is_mushroom` |
| features | `farmersdelight:patch_brown_mushroom_colony` |
| step | `vegetal_decoration` |

## farmersdelight/neoforge/biome_modifier/red_mushroom_colony

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:add_features_by_filter` |
| allowed biomes | `#c:is_mushroom` |
| features | `farmersdelight:patch_red_mushroom_colony` |
| step | `vegetal_decoration` |

## farmersdelight/neoforge/biome_modifier/wild_beetroots

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:add_features_by_filter` |
| allowed biomes | `#minecraft:is_beach` |
| features | `farmersdelight:patch_wild_beetroots` |
| step | `vegetal_decoration` |

## farmersdelight/neoforge/biome_modifier/wild_cabbages

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:add_features_by_filter` |
| allowed biomes | `#minecraft:is_beach` |
| features | `farmersdelight:patch_wild_cabbages` |
| step | `vegetal_decoration` |

## farmersdelight/neoforge/biome_modifier/wild_carrots

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:add_features_by_filter` |
| allowed biomes | `#minecraft:is_overworld` |
| denied biomes / 1 | `minecraft:lush_caves` |
| denied biomes / 2 | `minecraft:mushroom_fields` |
| features | `farmersdelight:patch_wild_carrots` |
| max temperature | 0.9 |
| min temperature | 0.4 |
| step | `vegetal_decoration` |

## farmersdelight/neoforge/biome_modifier/wild_onions

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:add_features_by_filter` |
| allowed biomes | `#minecraft:is_overworld` |
| denied biomes / 1 | `minecraft:lush_caves` |
| denied biomes / 2 | `minecraft:mushroom_fields` |
| features | `farmersdelight:patch_wild_onions` |
| max temperature | 0.9 |
| min temperature | 0.4 |
| step | `vegetal_decoration` |

## farmersdelight/neoforge/biome_modifier/wild_potatoes

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:add_features_by_filter` |
| allowed biomes | `#minecraft:is_overworld` |
| denied biomes | `#c:is_underground` |
| features | `farmersdelight:patch_wild_potatoes` |
| max temperature | 0.3 |
| min temperature | 0.1 |
| step | `vegetal_decoration` |

## farmersdelight/neoforge/biome_modifier/wild_rice

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:add_features_by_filter` |
| allowed biomes | `#c:is_wet/overworld` |
| denied biomes | `#c:is_underground` |
| features | `farmersdelight:patch_wild_rice` |
| step | `vegetal_decoration` |

## farmersdelight/neoforge/biome_modifier/wild_tomatoes

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:add_features_by_filter` |
| allowed biomes | `#c:is_hot/overworld` |
| denied biomes | `#c:is_wet` |
| features | `farmersdelight:patch_wild_tomatoes` |
| step | `vegetal_decoration` |

## farmersdelight/worldgen/configured_feature/patch_brown_mushroom_colony

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:wild_crop` |
| config / primary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / primary feature / feature / config / to place / Behavior kind | `minecraft:randomized_int_state_provider` |
| config / primary feature / feature / config / to place / property | `age` |
| config / primary feature / feature / config / to place / source / Behavior kind | `minecraft:simple_state_provider` |
| config / primary feature / feature / config / to place / source / state / Name | `farmersdelight:brown_mushroom_colony` |
| config / primary feature / feature / config / to place / source / state / Properties / age | `0` |
| config / primary feature / feature / config / to place / Members / Behavior kind | Uniform draw |
| config / primary feature / feature / config / to place / Members / max inclusive | 3 |
| config / primary feature / feature / config / to place / Members / min inclusive | 0 |
| config / primary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / primary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / blocks | `minecraft:mycelium` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / secondary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / secondary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / secondary feature / feature / config / to place / state / Name | `minecraft:brown_mushroom` |
| config / secondary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / secondary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / blocks | `minecraft:mycelium` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / tries | 64 |
| config / xz spread | 6 |
| config / y spread | 3 |

## farmersdelight/worldgen/configured_feature/patch_red_mushroom_colony

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:wild_crop` |
| config / primary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / primary feature / feature / config / to place / Behavior kind | `minecraft:randomized_int_state_provider` |
| config / primary feature / feature / config / to place / property | `age` |
| config / primary feature / feature / config / to place / source / Behavior kind | `minecraft:simple_state_provider` |
| config / primary feature / feature / config / to place / source / state / Name | `farmersdelight:red_mushroom_colony` |
| config / primary feature / feature / config / to place / source / state / Properties / age | `0` |
| config / primary feature / feature / config / to place / Members / Behavior kind | Uniform draw |
| config / primary feature / feature / config / to place / Members / max inclusive | 3 |
| config / primary feature / feature / config / to place / Members / min inclusive | 0 |
| config / primary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / primary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / blocks | `minecraft:mycelium` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / secondary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / secondary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / secondary feature / feature / config / to place / state / Name | `minecraft:red_mushroom` |
| config / secondary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / secondary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / blocks | `minecraft:mycelium` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / tries | 64 |
| config / xz spread | 6 |
| config / y spread | 3 |

## farmersdelight/worldgen/configured_feature/patch_sandy_shrub

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `minecraft:random_patch` |
| config / feature / feature / Behavior kind | `minecraft:simple_block` |
| config / feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / feature / feature / config / to place / state / Name | `farmersdelight:sandy_shrub` |
| config / feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:sand` |
| config / tries | 32 |
| config / xz spread | 2 |
| config / y spread | 3 |

## farmersdelight/worldgen/configured_feature/patch_wild_beetroots

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:wild_crop` |
| config / primary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / primary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / primary feature / feature / config / to place / state / Name | `farmersdelight:wild_beetroots` |
| config / primary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / primary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:sand` |
| config / secondary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / secondary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / secondary feature / feature / config / to place / state / Name | `farmersdelight:sandy_shrub` |
| config / secondary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / secondary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:sand` |
| config / tries | 64 |
| config / xz spread | 6 |
| config / y spread | 3 |

## farmersdelight/worldgen/configured_feature/patch_wild_cabbages

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:wild_crop` |
| config / primary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / primary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / primary feature / feature / config / to place / state / Name | `farmersdelight:wild_cabbages` |
| config / primary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / primary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:sand` |
| config / secondary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / secondary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / secondary feature / feature / config / to place / state / Name | `farmersdelight:sandy_shrub` |
| config / secondary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / secondary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:sand` |
| config / tries | 64 |
| config / xz spread | 6 |
| config / y spread | 3 |

## farmersdelight/worldgen/configured_feature/patch_wild_carrots

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:wild_crop` |
| config / floor feature / feature / Behavior kind | `minecraft:simple_block` |
| config / floor feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / floor feature / feature / config / to place / state / Name | `minecraft:coarse_dirt` |
| config / floor feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / floor feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / floor feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:replaceable` |
| config / floor feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / offset / 1 | 0 |
| config / floor feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / offset / 2 | 1 |
| config / floor feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / offset / 3 | 0 |
| config / floor feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / floor feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:dirt` |
| config / primary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / primary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / primary feature / feature / config / to place / state / Name | `farmersdelight:wild_carrots` |
| config / primary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / primary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:dirt` |
| config / secondary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / secondary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / secondary feature / feature / config / to place / state / Name | `minecraft:short_grass` |
| config / secondary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / secondary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:dirt` |
| config / tries | 64 |
| config / xz spread | 6 |
| config / y spread | 3 |

## farmersdelight/worldgen/configured_feature/patch_wild_onions

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:wild_crop` |
| config / primary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / primary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / primary feature / feature / config / to place / state / Name | `farmersdelight:wild_onions` |
| config / primary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / primary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:dirt` |
| config / secondary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / secondary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / secondary feature / feature / config / to place / state / Name | `minecraft:allium` |
| config / secondary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / secondary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:dirt` |
| config / tries | 64 |
| config / xz spread | 6 |
| config / y spread | 3 |

## farmersdelight/worldgen/configured_feature/patch_wild_potatoes

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:wild_crop` |
| config / primary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / primary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / primary feature / feature / config / to place / state / Name | `farmersdelight:wild_potatoes` |
| config / primary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / primary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:dirt` |
| config / secondary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / secondary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / secondary feature / feature / config / to place / state / Name | `minecraft:fern` |
| config / secondary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / secondary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:dirt` |
| config / tries | 64 |
| config / xz spread | 6 |
| config / y spread | 3 |

## farmersdelight/worldgen/configured_feature/patch_wild_rice

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:wild_rice` |
| config / feature / feature / Behavior kind | `minecraft:simple_block` |
| config / feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / feature / feature / config / to place / state / Name | `farmersdelight:wild_rice` |
| config / feature / feature / config / to place / state / Properties / half | `lower` |
| config / feature / feature / config / to place / state / Properties / waterlogged | `true` |
| config / feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `minecraft:dirt` |
| config / tries | 96 |
| config / xz spread | 7 |
| config / y spread | 3 |

## farmersdelight/worldgen/configured_feature/patch_wild_tomatoes

| Gameplay field | Exact value |
| --- | --- |
| Behavior kind | `farmersdelight:wild_crop` |
| config / primary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / primary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / primary feature / feature / config / to place / state / Name | `farmersdelight:wild_tomatoes` |
| config / primary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / primary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / primary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `farmersdelight:terrain` |
| config / secondary feature / feature / Behavior kind | `minecraft:simple_block` |
| config / secondary feature / feature / config / to place / Behavior kind | `minecraft:simple_state_provider` |
| config / secondary feature / feature / config / to place / state / Name | `minecraft:dead_bush` |
| config / secondary feature / placement / 1 / Behavior kind | `minecraft:block_predicate_filter` |
| config / secondary feature / placement / 1 / Eligibility predicate / Behavior kind | `minecraft:all_of` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / Behavior kind | `minecraft:matching_blocks` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 1 / blocks | `minecraft:air` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Behavior kind | `minecraft:matching_block_tag` |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 1 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 2 | -1 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / offset / 3 | 0 |
| config / secondary feature / placement / 1 / Eligibility predicate / Eligibility predicates / 2 / Membership group | `farmersdelight:terrain` |
| config / tries | 64 |
| config / xz spread | 6 |
| config / y spread | 3 |

## farmersdelight/worldgen/placed_feature/patch_brown_mushroom_colony

| Gameplay field | Exact value |
| --- | --- |
| feature | `farmersdelight:patch_brown_mushroom_colony` |
| placement / 1 / Behavior kind | `minecraft:rarity_filter` |
| placement / 1 / Probability | 15 |
| placement / 2 / Behavior kind | `minecraft:in_square` |
| placement / 3 / Behavior kind | `minecraft:heightmap` |
| placement / 3 / heightmap | `MOTION_BLOCKING` |
| placement / 4 / Behavior kind | `minecraft:biome` |
| placement / 5 / Behavior kind | `farmersdelight:biome_tag` |
| placement / 5 / Membership group | `minecraft:is_overworld` |

## farmersdelight/worldgen/placed_feature/patch_red_mushroom_colony

| Gameplay field | Exact value |
| --- | --- |
| feature | `farmersdelight:patch_red_mushroom_colony` |
| placement / 1 / Behavior kind | `minecraft:rarity_filter` |
| placement / 1 / Probability | 15 |
| placement / 2 / Behavior kind | `minecraft:in_square` |
| placement / 3 / Behavior kind | `minecraft:heightmap` |
| placement / 3 / heightmap | `MOTION_BLOCKING` |
| placement / 4 / Behavior kind | `minecraft:biome` |
| placement / 5 / Behavior kind | `farmersdelight:biome_tag` |
| placement / 5 / Membership group | `minecraft:is_overworld` |

## farmersdelight/worldgen/placed_feature/patch_wild_beetroots

| Gameplay field | Exact value |
| --- | --- |
| feature | `farmersdelight:patch_wild_beetroots` |
| placement / 1 / Behavior kind | `minecraft:rarity_filter` |
| placement / 1 / Probability | 30 |
| placement / 2 / Behavior kind | `minecraft:in_square` |
| placement / 3 / Behavior kind | `minecraft:heightmap` |
| placement / 3 / heightmap | `MOTION_BLOCKING` |
| placement / 4 / Behavior kind | `minecraft:biome` |
| placement / 5 / Behavior kind | `farmersdelight:biome_tag` |
| placement / 5 / Membership group | `minecraft:is_overworld` |

## farmersdelight/worldgen/placed_feature/patch_wild_cabbages

| Gameplay field | Exact value |
| --- | --- |
| feature | `farmersdelight:patch_wild_cabbages` |
| placement / 1 / Behavior kind | `minecraft:rarity_filter` |
| placement / 1 / Probability | 30 |
| placement / 2 / Behavior kind | `minecraft:in_square` |
| placement / 3 / Behavior kind | `minecraft:heightmap` |
| placement / 3 / heightmap | `MOTION_BLOCKING` |
| placement / 4 / Behavior kind | `minecraft:biome` |
| placement / 5 / Behavior kind | `farmersdelight:biome_tag` |
| placement / 5 / Membership group | `minecraft:is_overworld` |

## farmersdelight/worldgen/placed_feature/patch_wild_carrots

| Gameplay field | Exact value |
| --- | --- |
| feature | `farmersdelight:patch_wild_carrots` |
| placement / 1 / Behavior kind | `minecraft:rarity_filter` |
| placement / 1 / Probability | 120 |
| placement / 2 / Behavior kind | `minecraft:in_square` |
| placement / 3 / Behavior kind | `minecraft:heightmap` |
| placement / 3 / heightmap | `MOTION_BLOCKING` |
| placement / 4 / Behavior kind | `minecraft:biome` |
| placement / 5 / Behavior kind | `farmersdelight:biome_tag` |
| placement / 5 / Membership group | `minecraft:is_overworld` |

## farmersdelight/worldgen/placed_feature/patch_wild_onions

| Gameplay field | Exact value |
| --- | --- |
| feature | `farmersdelight:patch_wild_onions` |
| placement / 1 / Behavior kind | `minecraft:rarity_filter` |
| placement / 1 / Probability | 120 |
| placement / 2 / Behavior kind | `minecraft:in_square` |
| placement / 3 / Behavior kind | `minecraft:heightmap` |
| placement / 3 / heightmap | `MOTION_BLOCKING` |
| placement / 4 / Behavior kind | `minecraft:biome` |
| placement / 5 / Behavior kind | `farmersdelight:biome_tag` |
| placement / 5 / Membership group | `minecraft:is_overworld` |

## farmersdelight/worldgen/placed_feature/patch_wild_potatoes

| Gameplay field | Exact value |
| --- | --- |
| feature | `farmersdelight:patch_wild_potatoes` |
| placement / 1 / Behavior kind | `minecraft:rarity_filter` |
| placement / 1 / Probability | 100 |
| placement / 2 / Behavior kind | `minecraft:in_square` |
| placement / 3 / Behavior kind | `minecraft:heightmap` |
| placement / 3 / heightmap | `MOTION_BLOCKING` |
| placement / 4 / Behavior kind | `minecraft:biome` |
| placement / 5 / Behavior kind | `farmersdelight:biome_tag` |
| placement / 5 / Membership group | `minecraft:is_overworld` |

## farmersdelight/worldgen/placed_feature/patch_wild_rice

| Gameplay field | Exact value |
| --- | --- |
| feature | `farmersdelight:patch_wild_rice` |
| placement / 1 / Behavior kind | `minecraft:rarity_filter` |
| placement / 1 / Probability | 20 |
| placement / 2 / Behavior kind | `minecraft:in_square` |
| placement / 3 / Behavior kind | `minecraft:heightmap` |
| placement / 3 / heightmap | `MOTION_BLOCKING` |
| placement / 4 / Behavior kind | `minecraft:biome` |
| placement / 5 / Behavior kind | `farmersdelight:biome_tag` |
| placement / 5 / Membership group | `minecraft:is_overworld` |

## farmersdelight/worldgen/placed_feature/patch_wild_tomatoes

| Gameplay field | Exact value |
| --- | --- |
| feature | `farmersdelight:patch_wild_tomatoes` |
| placement / 1 / Behavior kind | `minecraft:rarity_filter` |
| placement / 1 / Probability | 100 |
| placement / 2 / Behavior kind | `minecraft:in_square` |
| placement / 3 / Behavior kind | `minecraft:heightmap` |
| placement / 3 / heightmap | `MOTION_BLOCKING` |
| placement / 4 / Behavior kind | `minecraft:biome` |
| placement / 5 / Behavior kind | `farmersdelight:biome_tag` |
| placement / 5 / Membership group | `minecraft:is_overworld` |

## minecraft/tags/damage_type/burn_from_stepping

| Gameplay field | Exact value |
| --- | --- |
| Members / 1 | `farmersdelight:stove_burn` |

## minecraft/tags/damage_type/is_fire

| Gameplay field | Exact value |
| --- | --- |
| Members / 1 | `farmersdelight:stove_burn` |

## minecraft/tags/damage_type/no_knockback

| Gameplay field | Exact value |
| --- | --- |
| Members / 1 | `farmersdelight:stove_burn` |

## minecraft/tags/damage_type/panic_environmental_causes

| Gameplay field | Exact value |
| --- | --- |
| Members / 1 | `farmersdelight:stove_burn` |

## minecraft/tags/enchantment/non_treasure

| Gameplay field | Exact value |
| --- | --- |
| Members / 1 | `farmersdelight:backstabbing` |
