# Block and Item Catalog

Every row is normative together with its linked behavior contract. All identifiers below belong to Farmer's Delight unless a different namespace is given. Default item stack limit is 64 unless overridden below. Fuel durations are gameplay steps. Foods show saturation points, not a multiplier. Ordinary food/drink duration is an external baseline dependency unless explicitly overridden. Material baselines refer to properties only, not an inherited interaction class; explicit overrides below take precedence.

## Blocks

| Identifier | Display name | Behavior contract | Inventory form | Material behavior and overrides |
| --- | --- | --- | --- | --- |
| `stove` | Stove | [stove](block-mechanics.md#stove) | `stove` | bricks baseline material; light 13 when lit, otherwise 0 |
| `cooking_pot` | Cooking Pot | [cooking-pot](block-mechanics.md#cooking-pot) | `cooking_pot` | break strength 0.5; blast resistance 6.0; lantern sound family |
| `skillet` | Skillet | [skillet](block-mechanics.md#skillet) | `skillet` | break strength 0.5; blast resistance 6.0; lantern sound family |
| `wooden_basket` | Basket | [basket](block-mechanics.md#basket) | `wooden_basket` | break strength 1.5; blast resistance 1.5; wood sound family |
| `bamboo_basket` | Bamboo Basket | [basket](block-mechanics.md#basket) | `bamboo_basket` | break strength 1.5; blast resistance 1.5; bamboo wood sound family |
| `cutting_board` | Cutting Board | [cutting-board](block-mechanics.md#cutting-board) | `cutting_board` | oak planks baseline material; break strength 2.0; blast resistance 2.0; wood sound family |
| `carrot_crate` | Carrot Crate | [storage-blocks](block-mechanics.md#storage-blocks) | `carrot_crate` | oak planks baseline material; break strength 2.0; blast resistance 3.0; wood sound family |
| `potato_crate` | Potato Crate | [storage-blocks](block-mechanics.md#storage-blocks) | `potato_crate` | oak planks baseline material; break strength 2.0; blast resistance 3.0; wood sound family |
| `beetroot_crate` | Beetroot Crate | [storage-blocks](block-mechanics.md#storage-blocks) | `beetroot_crate` | oak planks baseline material; break strength 2.0; blast resistance 3.0; wood sound family |
| `cabbage_crate` | Cabbage Crate | [storage-blocks](block-mechanics.md#storage-blocks) | `cabbage_crate` | oak planks baseline material; break strength 2.0; blast resistance 3.0; wood sound family |
| `tomato_crate` | Tomato Crate | [storage-blocks](block-mechanics.md#storage-blocks) | `tomato_crate` | oak planks baseline material; break strength 2.0; blast resistance 3.0; wood sound family |
| `onion_crate` | Onion Crate | [storage-blocks](block-mechanics.md#storage-blocks) | `onion_crate` | oak planks baseline material; break strength 2.0; blast resistance 3.0; wood sound family |
| `rice_bale` | Rice Bale | [storage-blocks](block-mechanics.md#storage-blocks) | `rice_bale` | hay block baseline material |
| `rice_bag` | Bag of Rice | [storage-blocks](block-mechanics.md#storage-blocks) | `rice_bag` | white wool baseline material |
| `straw_bale` | Straw Bale | [storage-blocks](block-mechanics.md#storage-blocks) | `straw_bale` | hay block baseline material |
| `rope` | Rope | [rope](block-mechanics.md#rope) | `rope` | brown carpet baseline material; break strength 0.2; blast resistance 0.2; wool sound family; no entity collision; non-occluding material |
| `safety_net` | Safety Net | [safety-net](block-mechanics.md#safety-net) | `safety_net` | brown carpet baseline material; break strength 0.2; blast resistance 0.2; wool sound family |
| `rope_fence` | Rope Fence | [rope-fences](block-mechanics.md#rope-fences) | `rope_fence` | oak fence baseline material; break strength 1.0; blast resistance 1.0 |
| `rope_fence_gate` | Rope Fence Gate | [rope-fences](block-mechanics.md#rope-fences) | `rope_fence_gate` | oak fence baseline material; break strength 1.0; blast resistance 1.0 |
| `oak_cabinet` | Oak Cabinet | [cabinet](block-mechanics.md#cabinet) | `oak_cabinet` | barrel baseline material |
| `spruce_cabinet` | Spruce Cabinet | [cabinet](block-mechanics.md#cabinet) | `spruce_cabinet` | barrel baseline material |
| `birch_cabinet` | Birch Cabinet | [cabinet](block-mechanics.md#cabinet) | `birch_cabinet` | barrel baseline material |
| `jungle_cabinet` | Jungle Cabinet | [cabinet](block-mechanics.md#cabinet) | `jungle_cabinet` | barrel baseline material |
| `acacia_cabinet` | Acacia Cabinet | [cabinet](block-mechanics.md#cabinet) | `acacia_cabinet` | barrel baseline material |
| `dark_oak_cabinet` | Dark Oak Cabinet | [cabinet](block-mechanics.md#cabinet) | `dark_oak_cabinet` | barrel baseline material |
| `mangrove_cabinet` | Mangrove Cabinet | [cabinet](block-mechanics.md#cabinet) | `mangrove_cabinet` | barrel baseline material |
| `cherry_cabinet` | Cherry Cabinet | [cabinet](block-mechanics.md#cabinet) | `cherry_cabinet` | barrel baseline material; cherry wood sound family |
| `bamboo_cabinet` | Bamboo Cabinet | [cabinet](block-mechanics.md#cabinet) | `bamboo_cabinet` | barrel baseline material; bamboo wood sound family |
| `crimson_cabinet` | Crimson Cabinet | [cabinet](block-mechanics.md#cabinet) | `crimson_cabinet` | barrel baseline material; nether wood sound family |
| `warped_cabinet` | Warped Cabinet | [cabinet](block-mechanics.md#cabinet) | `warped_cabinet` | barrel baseline material; nether wood sound family |
| `canvas_rug` | Canvas Rug | [canvas-rug](block-mechanics.md#canvas-rug) | `canvas_rug` | white carpet baseline material; break strength 0.2; blast resistance 0.2; grass sound family |
| `tatami` | Tatami Block | [tatami](block-mechanics.md#tatami) | `tatami` | white wool baseline material |
| `full_tatami_mat` | Full Tatami Mat | [tatami](block-mechanics.md#tatami) | `full_tatami_mat` | white wool baseline material; break strength 0.3; blast resistance 0.3 |
| `half_tatami_mat` | Half Tatami Mat | [tatami](block-mechanics.md#tatami) | `half_tatami_mat` | white wool baseline material; break strength 0.3; blast resistance 0.3; piston response: destroy |
| `canvas_sign` | Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `canvas_sign` | Custom/default material; see behavior and baseline contract |
| `white_canvas_sign` | White Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `white_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `orange_canvas_sign` | Orange Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `orange_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `magenta_canvas_sign` | Magenta Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `magenta_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `light_blue_canvas_sign` | Light Blue Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `light_blue_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `yellow_canvas_sign` | Yellow Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `yellow_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `lime_canvas_sign` | Lime Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `lime_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `pink_canvas_sign` | Pink Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `pink_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `gray_canvas_sign` | Gray Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `gray_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `light_gray_canvas_sign` | Light Gray Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `light_gray_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `cyan_canvas_sign` | Cyan Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `cyan_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `purple_canvas_sign` | Purple Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `purple_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `blue_canvas_sign` | Blue Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `blue_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `brown_canvas_sign` | Brown Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `brown_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `green_canvas_sign` | Green Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `green_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `red_canvas_sign` | Red Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `red_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `black_canvas_sign` | Black Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `black_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `canvas_wall_sign` | Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `canvas_sign` | spruce sign baseline material |
| `white_canvas_wall_sign` | White Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `white_canvas_sign` | spruce sign baseline material |
| `orange_canvas_wall_sign` | Orange Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `orange_canvas_sign` | spruce sign baseline material |
| `magenta_canvas_wall_sign` | Magenta Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `magenta_canvas_sign` | spruce sign baseline material |
| `light_blue_canvas_wall_sign` | Light Blue Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `light_blue_canvas_sign` | spruce sign baseline material |
| `yellow_canvas_wall_sign` | Yellow Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `yellow_canvas_sign` | spruce sign baseline material |
| `lime_canvas_wall_sign` | Lime Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `lime_canvas_sign` | spruce sign baseline material |
| `pink_canvas_wall_sign` | Pink Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `pink_canvas_sign` | spruce sign baseline material |
| `gray_canvas_wall_sign` | Gray Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `gray_canvas_sign` | spruce sign baseline material |
| `light_gray_canvas_wall_sign` | Light Gray Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `light_gray_canvas_sign` | spruce sign baseline material |
| `cyan_canvas_wall_sign` | Cyan Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `cyan_canvas_sign` | spruce sign baseline material |
| `purple_canvas_wall_sign` | Purple Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `purple_canvas_sign` | spruce sign baseline material |
| `blue_canvas_wall_sign` | Blue Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `blue_canvas_sign` | spruce sign baseline material |
| `brown_canvas_wall_sign` | Brown Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `brown_canvas_sign` | spruce sign baseline material |
| `green_canvas_wall_sign` | Green Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `green_canvas_sign` | spruce sign baseline material |
| `red_canvas_wall_sign` | Red Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `red_canvas_sign` | spruce sign baseline material |
| `black_canvas_wall_sign` | Black Canvas Wall Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `black_canvas_sign` | spruce sign baseline material |
| `hanging_canvas_sign` | Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `white_hanging_canvas_sign` | White Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `white_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `orange_hanging_canvas_sign` | Orange Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `orange_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `magenta_hanging_canvas_sign` | Magenta Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `magenta_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `light_blue_hanging_canvas_sign` | Light Blue Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `light_blue_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `yellow_hanging_canvas_sign` | Yellow Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `yellow_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `lime_hanging_canvas_sign` | Lime Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `lime_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `pink_hanging_canvas_sign` | Pink Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `pink_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `gray_hanging_canvas_sign` | Gray Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `gray_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `light_gray_hanging_canvas_sign` | Light Gray Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `light_gray_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `cyan_hanging_canvas_sign` | Cyan Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `cyan_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `purple_hanging_canvas_sign` | Purple Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `purple_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `blue_hanging_canvas_sign` | Blue Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `blue_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `brown_hanging_canvas_sign` | Brown Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `brown_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `green_hanging_canvas_sign` | Green Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `green_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `red_hanging_canvas_sign` | Red Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `red_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `black_hanging_canvas_sign` | Black Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `black_hanging_canvas_sign` | Custom/default material; see behavior and baseline contract |
| `wall_hanging_canvas_sign` | Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `white_wall_hanging_canvas_sign` | White Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `white_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `orange_wall_hanging_canvas_sign` | Orange Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `orange_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `magenta_wall_hanging_canvas_sign` | Magenta Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `magenta_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `light_blue_wall_hanging_canvas_sign` | Light Blue Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `light_blue_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `yellow_wall_hanging_canvas_sign` | Yellow Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `yellow_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `lime_wall_hanging_canvas_sign` | Lime Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `lime_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `pink_wall_hanging_canvas_sign` | Pink Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `pink_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `gray_wall_hanging_canvas_sign` | Gray Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `gray_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `light_gray_wall_hanging_canvas_sign` | Light Gray Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `light_gray_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `cyan_wall_hanging_canvas_sign` | Cyan Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `cyan_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `purple_wall_hanging_canvas_sign` | Purple Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `purple_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `blue_wall_hanging_canvas_sign` | Blue Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `blue_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `brown_wall_hanging_canvas_sign` | Brown Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `brown_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `green_wall_hanging_canvas_sign` | Green Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `green_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `red_wall_hanging_canvas_sign` | Red Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `red_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `black_wall_hanging_canvas_sign` | Black Wall Hanging Canvas Sign | [canvas-signs](block-mechanics.md#canvas-signs) | `black_hanging_canvas_sign` | spruce wall hanging sign baseline material |
| `brown_mushroom_colony` | Brown Mushroom Colony | [mushroom-colonies](block-mechanics.md#mushroom-colonies) | `brown_mushroom_colony` | brown mushroom baseline material |
| `red_mushroom_colony` | Red Mushroom Colony | [mushroom-colonies](block-mechanics.md#mushroom-colonies) | `red_mushroom_colony` | red mushroom baseline material |
| `organic_compost` | Organic Compost | [organic-compost](block-mechanics.md#organic-compost) | `organic_compost` | dirt baseline material; break strength 1.2; blast resistance 1.2; crop sound family |
| `rich_soil` | Rich Soil | [rich-soil](block-mechanics.md#rich-soil) | `rich_soil` | dirt baseline material |
| `rich_soil_farmland` | Rich Soil Farmland | [rich-soil](block-mechanics.md#rich-soil) | `rich_soil_farmland` | farmland baseline material |
| `apple_pie` | Apple Pie | [pies](block-mechanics.md#pies) | `apple_pie` | cake baseline material |
| `sweet_berry_cheesecake` | Sweet Berry Cheesecake | [pies](block-mechanics.md#pies) | `sweet_berry_cheesecake` | cake baseline material |
| `chocolate_pie` | Chocolate Pie | [pies](block-mechanics.md#pies) | `chocolate_pie` | cake baseline material |
| `pumpkin_pie` | Pumpkin Pie | [pies](block-mechanics.md#pies) | `debug_pumpkin_pie` | cake baseline material |
| `sandy_shrub` | Sandy Shrub | [wild-plants](block-mechanics.md#wild-plants) | `sandy_shrub` | tall grass baseline material |
| `wild_cabbages` | Wild Cabbage | [wild-plants](block-mechanics.md#wild-plants) | `wild_cabbages` | tall grass baseline material |
| `wild_onions` | Wild Onion | [wild-plants](block-mechanics.md#wild-plants) | `wild_onions` | tall grass baseline material |
| `wild_tomatoes` | Tomato Shrub | [wild-plants](block-mechanics.md#wild-plants) | `wild_tomatoes` | tall grass baseline material |
| `wild_carrots` | Wild Carrot | [wild-plants](block-mechanics.md#wild-plants) | `wild_carrots` | tall grass baseline material |
| `wild_potatoes` | Wild Potato | [wild-plants](block-mechanics.md#wild-plants) | `wild_potatoes` | tall grass baseline material |
| `wild_beetroots` | Sea Beet | [wild-plants](block-mechanics.md#wild-plants) | `wild_beetroots` | tall grass baseline material |
| `wild_rice` | Wild Rice | [wild-plants](block-mechanics.md#wild-plants) | `wild_rice` | tall grass baseline material |
| `cabbages` | Cabbage | [cabbage-and-onion](block-mechanics.md#cabbage-and-onion) | `cabbage_seeds` | wheat baseline material |
| `onions` | Onions | [cabbage-and-onion](block-mechanics.md#cabbage-and-onion) | `onion` | wheat baseline material |
| `budding_tomatoes` | Budding Tomato Vine | [tomatoes](block-mechanics.md#tomatoes) | `tomato_seeds` | wheat baseline material |
| `tomatoes` | Tomato Vine | [tomatoes](block-mechanics.md#tomatoes) | `tomato_seeds` | crop sound family; no entity collision; instant break |
| `tomatoes_on_rope` | Tomato Vine on Rope | [tomatoes](block-mechanics.md#tomatoes) | World form only; see harvesting/placement contract | tomatoes baseline material; piston response: normal |
| `rice` | Rice Crops | [rice](block-mechanics.md#rice) | `rice` | wheat baseline material; break strength 0.2; blast resistance 0.2 |
| `rice_panicles` | Rice Panicles | [rice](block-mechanics.md#rice) | World form only; see harvesting/placement contract | wheat baseline material |
| `roast_chicken_block` | Roast Chicken | [feasts](block-mechanics.md#feasts) | `roast_chicken_block` | cake baseline material |
| `stuffed_pumpkin_block` | Stuffed Pumpkin | [feasts](block-mechanics.md#feasts) | `stuffed_pumpkin_block` | pumpkin baseline material |
| `honey_glazed_ham_block` | Honey Glazed Ham | [feasts](block-mechanics.md#feasts) | `honey_glazed_ham_block` | cake baseline material |
| `shepherds_pie_block` | Shepherd's Pie | [feasts](block-mechanics.md#feasts) | `shepherds_pie_block` | cake baseline material |
| `gleaming_salad_block` | Gleaming Salad | [gleaming-salad](block-mechanics.md#gleaming-salad) | `gleaming_salad_block` | oak planks baseline material; light = 3 * servings |
| `rice_roll_medley_block` | Rice Roll Medley | [rice-roll-medley](block-mechanics.md#rice-roll-medley) | `rice_roll_medley_block` | cake baseline material |

## Items

| Identifier | Display name | Role / placement | Stack limit | Hunger / saturation | Consumption and effect | Remainder | Fuel steps |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `stove` | Stove | Places `stove`; [stove](block-mechanics.md#stove) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `cooking_pot` | Cooking Pot | Places `cooking_pot`; [cooking-pot](block-mechanics.md#cooking-pot) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `skillet` | Skillet | Places `skillet`; [skillet](block-mechanics.md#skillet) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `cutting_board` | Cutting Board | Places `cutting_board`; [cutting-board](block-mechanics.md#cutting-board) | 64 | Not food | Not consumed as food | none | 200 |
| `wooden_basket` | Basket | Places `wooden_basket`; [basket](block-mechanics.md#basket) | 64 | Not food | Not consumed as food | none | 300 |
| `bamboo_basket` | Bamboo Basket | Places `bamboo_basket`; [basket](block-mechanics.md#basket) | 64 | Not food | Not consumed as food | none | 300 |
| `carrot_crate` | Carrot Crate | Places `carrot_crate`; [storage-blocks](block-mechanics.md#storage-blocks) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `potato_crate` | Potato Crate | Places `potato_crate`; [storage-blocks](block-mechanics.md#storage-blocks) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `beetroot_crate` | Beetroot Crate | Places `beetroot_crate`; [storage-blocks](block-mechanics.md#storage-blocks) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `cabbage_crate` | Cabbage Crate | Places `cabbage_crate`; [storage-blocks](block-mechanics.md#storage-blocks) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `tomato_crate` | Tomato Crate | Places `tomato_crate`; [storage-blocks](block-mechanics.md#storage-blocks) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `onion_crate` | Onion Crate | Places `onion_crate`; [storage-blocks](block-mechanics.md#storage-blocks) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `rice_bale` | Rice Bale | Places `rice_bale`; [storage-blocks](block-mechanics.md#storage-blocks) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `rice_bag` | Bag of Rice | Places `rice_bag`; [storage-blocks](block-mechanics.md#storage-blocks) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `straw_bale` | Straw Bale | Places `straw_bale`; [storage-blocks](block-mechanics.md#storage-blocks) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `safety_net` | Safety Net | Places `safety_net`; [safety-net](block-mechanics.md#safety-net) | 64 | Not food | Not consumed as food | none | 200 |
| `oak_cabinet` | Oak Cabinet | Places `oak_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | 300 |
| `spruce_cabinet` | Spruce Cabinet | Places `spruce_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | 300 |
| `birch_cabinet` | Birch Cabinet | Places `birch_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | 300 |
| `jungle_cabinet` | Jungle Cabinet | Places `jungle_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | 300 |
| `acacia_cabinet` | Acacia Cabinet | Places `acacia_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | 300 |
| `dark_oak_cabinet` | Dark Oak Cabinet | Places `dark_oak_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | 300 |
| `mangrove_cabinet` | Mangrove Cabinet | Places `mangrove_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | 300 |
| `cherry_cabinet` | Cherry Cabinet | Places `cherry_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | 300 |
| `bamboo_cabinet` | Bamboo Cabinet | Places `bamboo_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | 300 |
| `crimson_cabinet` | Crimson Cabinet | Places `crimson_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `warped_cabinet` | Warped Cabinet | Places `warped_cabinet`; [cabinet](block-mechanics.md#cabinet) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `tatami` | Tatami Block | Places `tatami`; [tatami](block-mechanics.md#tatami) | 64 | Not food | Not consumed as food | none | 400 |
| `full_tatami_mat` | Full Tatami Mat | Places `full_tatami_mat`; [tatami](block-mechanics.md#tatami) | 64 | Not food | Not consumed as food | none | 200 |
| `half_tatami_mat` | Half Tatami Mat | Places `half_tatami_mat`; [tatami](block-mechanics.md#tatami) | 64 | Not food | Not consumed as food | none | 100 |
| `canvas_rug` | Canvas Rug | Places `canvas_rug`; [canvas-rug](block-mechanics.md#canvas-rug) | 64 | Not food | Not consumed as food | none | 200 |
| `rope_fence` | Rope Fence | Places `rope_fence`; [rope-fences](block-mechanics.md#rope-fences) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `rope_fence_gate` | Rope Fence Gate | Places `rope_fence_gate`; [rope-fences](block-mechanics.md#rope-fences) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `organic_compost` | Organic Compost | Places `organic_compost`; [organic-compost](block-mechanics.md#organic-compost) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `rich_soil` | Rich Soil | Places `rich_soil`; [rich-soil](block-mechanics.md#rich-soil) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `rich_soil_farmland` | Rich Soil Farmland | Places `rich_soil_farmland`; [rich-soil](block-mechanics.md#rich-soil) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `rope` | Rope | Places `rope`; [rope](block-mechanics.md#rope) | 64 | Not food | Not consumed as food | none | 200 |
| `canvas_sign` | Canvas Sign | Places `canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `hanging_canvas_sign` | Hanging Canvas Sign | Places `hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `white_canvas_sign` | White Canvas Sign | Places `white_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `white_hanging_canvas_sign` | White Hanging Canvas Sign | Places `white_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `light_gray_canvas_sign` | Light Gray Canvas Sign | Places `light_gray_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `light_gray_hanging_canvas_sign` | Light Gray Hanging Canvas Sign | Places `light_gray_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `gray_canvas_sign` | Gray Canvas Sign | Places `gray_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `gray_hanging_canvas_sign` | Gray Hanging Canvas Sign | Places `gray_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `black_canvas_sign` | Black Canvas Sign | Places `black_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `black_hanging_canvas_sign` | Black Hanging Canvas Sign | Places `black_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `brown_canvas_sign` | Brown Canvas Sign | Places `brown_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `brown_hanging_canvas_sign` | Brown Hanging Canvas Sign | Places `brown_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `red_canvas_sign` | Red Canvas Sign | Places `red_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `red_hanging_canvas_sign` | Red Hanging Canvas Sign | Places `red_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `orange_canvas_sign` | Orange Canvas Sign | Places `orange_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `orange_hanging_canvas_sign` | Orange Hanging Canvas Sign | Places `orange_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `yellow_canvas_sign` | Yellow Canvas Sign | Places `yellow_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `yellow_hanging_canvas_sign` | Yellow Hanging Canvas Sign | Places `yellow_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `lime_canvas_sign` | Lime Canvas Sign | Places `lime_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `lime_hanging_canvas_sign` | Lime Hanging Canvas Sign | Places `lime_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `green_canvas_sign` | Green Canvas Sign | Places `green_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `green_hanging_canvas_sign` | Green Hanging Canvas Sign | Places `green_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `cyan_canvas_sign` | Cyan Canvas Sign | Places `cyan_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `cyan_hanging_canvas_sign` | Cyan Hanging Canvas Sign | Places `cyan_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `light_blue_canvas_sign` | Light Blue Canvas Sign | Places `light_blue_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `light_blue_hanging_canvas_sign` | Light Blue Hanging Canvas Sign | Places `light_blue_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `blue_canvas_sign` | Blue Canvas Sign | Places `blue_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `blue_hanging_canvas_sign` | Blue Hanging Canvas Sign | Places `blue_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `purple_canvas_sign` | Purple Canvas Sign | Places `purple_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `purple_hanging_canvas_sign` | Purple Hanging Canvas Sign | Places `purple_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `magenta_canvas_sign` | Magenta Canvas Sign | Places `magenta_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `magenta_hanging_canvas_sign` | Magenta Hanging Canvas Sign | Places `magenta_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `pink_canvas_sign` | Pink Canvas Sign | Places `pink_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `pink_hanging_canvas_sign` | Pink Hanging Canvas Sign | Places `pink_hanging_canvas_sign`; [canvas-signs](block-mechanics.md#canvas-signs) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `flint_knife` | Flint Knife | [knives](item-mechanics.md#knives) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `copper_knife` | copper knife | [knives](item-mechanics.md#knives) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `iron_knife` | Iron Knife | [knives](item-mechanics.md#knives) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `diamond_knife` | Diamond Knife | [knives](item-mechanics.md#knives) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `netherite_knife` | Netherite Knife | [knives](item-mechanics.md#knives) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `golden_knife` | Golden Knife | [knives](item-mechanics.md#knives) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `straw` | Straw | [materials](item-mechanics.md#materials) | 64 | Not food | Not consumed as food | none | 100 |
| `canvas` | Canvas | [materials](item-mechanics.md#materials) | 64 | Not food | Not consumed as food | none | 400 |
| `tree_bark` | Tree Bark | [materials](item-mechanics.md#materials) | 64 | Not food | Not consumed as food | none | 200 |
| `sandy_shrub` | Sandy Shrub | Places `sandy_shrub`; [wild-plants](block-mechanics.md#wild-plants) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `wild_cabbages` | Wild Cabbage | Places `wild_cabbages`; [wild-plants](block-mechanics.md#wild-plants) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `wild_onions` | Wild Onion | Places `wild_onions`; [wild-plants](block-mechanics.md#wild-plants) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `wild_tomatoes` | Tomato Shrub | Places `wild_tomatoes`; [wild-plants](block-mechanics.md#wild-plants) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `wild_carrots` | Wild Carrot | Places `wild_carrots`; [wild-plants](block-mechanics.md#wild-plants) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `wild_potatoes` | Wild Potato | Places `wild_potatoes`; [wild-plants](block-mechanics.md#wild-plants) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `wild_beetroots` | Sea Beet | Places `wild_beetroots`; [wild-plants](block-mechanics.md#wild-plants) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `wild_rice` | Wild Rice | Places `wild_rice`; [wild-plants](block-mechanics.md#wild-plants) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `brown_mushroom_colony` | Brown Mushroom Colony | Places `brown_mushroom_colony`; [mushroom-colonies](block-mechanics.md#mushroom-colonies) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `red_mushroom_colony` | Red Mushroom Colony | Places `red_mushroom_colony`; [mushroom-colonies](block-mechanics.md#mushroom-colonies) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `cabbage` | Cabbage | [consumption](item-mechanics.md#consumption) | 64 | 2 / 1.6 | Ordinary eating; no added effect | none | No explicit fuel override |
| `tomato` | Tomato | [consumption](item-mechanics.md#consumption) | 64 | 1 / 0.6 | Ordinary eating; no added effect | none | No explicit fuel override |
| `onion` | Onion | Places `onions`; [cabbage-and-onion](block-mechanics.md#cabbage-and-onion) | 64 | 2 / 1.6 | Ordinary eating; no added effect | none | No explicit fuel override |
| `rice_panicle` | Rice Panicle | [materials](item-mechanics.md#materials) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `rice` | Rice | Places `rice`; [rice](block-mechanics.md#rice) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `cabbage_seeds` | Cabbage Seeds | Places `cabbages`; [cabbage-and-onion](block-mechanics.md#cabbage-and-onion) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `tomato_seeds` | Tomato Seeds | Places `tomatoes`; [tomatoes](block-mechanics.md#tomatoes) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `rotten_tomato` | Rotten Tomato | [rotten-tomato](item-mechanics.md#rotten-tomato) | 16 | Not food | Not consumed as food | none | No explicit fuel override |
| `fried_egg` | Fried Egg | [consumption](item-mechanics.md#consumption) | 64 | 4 / 3.2 | Ordinary eating; no added effect | none | No explicit fuel override |
| `milk_bottle` | Milk Bottle | [consumption](item-mechanics.md#consumption) | 16 | Not food | Drink; remove one random eligible effect | glass_bottle | No explicit fuel override |
| `hot_cocoa` | Hot Cocoa | [consumption](item-mechanics.md#consumption) | 16 | Not food | Drink; remove one random eligible harmful effect | glass_bottle | No explicit fuel override |
| `apple_cider` | Apple Cider | [consumption](item-mechanics.md#consumption) | 16 | 0 / 0; usable at full hunger | Drink; absorption I for 60 s | glass_bottle | No explicit fuel override |
| `melon_juice` | Melon Juice | [consumption](item-mechanics.md#consumption) | 16 | Not food | Drink; heal 2 health points | glass_bottle | No explicit fuel override |
| `tomato_sauce` | Tomato Sauce | [consumption](item-mechanics.md#consumption) | 64 | 4 / 3.2 | Ordinary eating; no added effect | bowl | No explicit fuel override |
| `wheat_dough` | Wheat Dough | [consumption](item-mechanics.md#consumption) | 64 | 2 / 1.2 | 30% hunger I for 30 s | none | No explicit fuel override |
| `raw_pasta` | Raw Pasta | [consumption](item-mechanics.md#consumption) | 64 | 2 / 1.2 | 30% hunger I for 30 s | none | No explicit fuel override |
| `pumpkin_slice` | Pumpkin Slice | [consumption](item-mechanics.md#consumption) | 64 | 3 / 1.8 | Ordinary eating; no added effect | none | No explicit fuel override |
| `cabbage_leaf` | Cabbage Leaf | [consumption](item-mechanics.md#consumption) | 64 | 1 / 0.8 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `minced_beef` | Minced Beef | [consumption](item-mechanics.md#consumption) | 64 | 2 / 1.2 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `beef_patty` | Beef Patty | [consumption](item-mechanics.md#consumption) | 64 | 4 / 6.4 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `chicken_cuts` | Raw Chicken Cuts | [consumption](item-mechanics.md#consumption) | 64 | 1 / 0.6 | Eat in 0.8 s; 30% hunger I for 30 s | none | No explicit fuel override |
| `cooked_chicken_cuts` | Cooked Chicken Cuts | [consumption](item-mechanics.md#consumption) | 64 | 3 / 3.6 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `bacon` | Raw Bacon | [consumption](item-mechanics.md#consumption) | 64 | 2 / 1.2 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `cooked_bacon` | Cooked Bacon | [consumption](item-mechanics.md#consumption) | 64 | 4 / 6.4 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `cod_slice` | Raw Cod Slice | [consumption](item-mechanics.md#consumption) | 64 | 1 / 0.2 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `cooked_cod_slice` | Cooked Cod Slice | [consumption](item-mechanics.md#consumption) | 64 | 3 / 3 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `salmon_slice` | Raw Salmon Slice | [consumption](item-mechanics.md#consumption) | 64 | 1 / 0.2 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `cooked_salmon_slice` | Cooked Salmon Slice | [consumption](item-mechanics.md#consumption) | 64 | 3 / 4.8 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `mutton_chops` | Raw Mutton Chops | [consumption](item-mechanics.md#consumption) | 64 | 1 / 0.6 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `cooked_mutton_chops` | Cooked Mutton Chops | [consumption](item-mechanics.md#consumption) | 64 | 3 / 4.8 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `ham` | Ham | [consumption](item-mechanics.md#consumption) | 64 | 5 / 3 | Ordinary eating; no added effect | none | No explicit fuel override |
| `smoked_ham` | Smoked Ham | [consumption](item-mechanics.md#consumption) | 64 | 10 / 16 | Ordinary eating; no added effect | none | No explicit fuel override |
| `pie_crust` | Pie Crust | [consumption](item-mechanics.md#consumption) | 64 | 2 / 0.8 | Ordinary eating; no added effect | none | No explicit fuel override |
| `apple_pie` | Apple Pie | Places `apple_pie`; [pies](block-mechanics.md#pies) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `sweet_berry_cheesecake` | Sweet Berry Cheesecake | Places `sweet_berry_cheesecake`; [pies](block-mechanics.md#pies) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `chocolate_pie` | Chocolate Pie | Places `chocolate_pie`; [pies](block-mechanics.md#pies) | 64 | Not food | Not consumed as food | none | No explicit fuel override |
| `cake_slice` | Slice of Cake | [consumption](item-mechanics.md#consumption) | 64 | 2 / 0.4 | Speed I for 20 s | none | No explicit fuel override |
| `apple_pie_slice` | Slice of Apple Pie | [consumption](item-mechanics.md#consumption) | 64 | 3 / 1.8 | Speed I for 30 s | none | No explicit fuel override |
| `sweet_berry_cheesecake_slice` | Slice of Sweet Berry Cheesecake | [consumption](item-mechanics.md#consumption) | 64 | 3 / 1.8 | Speed I for 30 s | none | No explicit fuel override |
| `chocolate_pie_slice` | Slice of Chocolate Pie | [consumption](item-mechanics.md#consumption) | 64 | 3 / 1.8 | Speed I for 30 s | none | No explicit fuel override |
| `pumpkin_pie_slice` | Slice of Pumpkin Pie | [consumption](item-mechanics.md#consumption) | 64 | 3 / 1.8 | Speed I for 30 s | none | No explicit fuel override |
| `sweet_berry_cookie` | Sweet Berry Cookie | [consumption](item-mechanics.md#consumption) | 64 | 2 / 0.4 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `honey_cookie` | Honey Cookie | [consumption](item-mechanics.md#consumption) | 64 | 2 / 0.4 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `melon_popsicle` | Melon Popsicle | [consumption](item-mechanics.md#consumption) | 64 | 3 / 1.2; usable at full hunger | Eat in 0.8 s; extinguish consumer | none | No explicit fuel override |
| `glow_berry_custard` | Glow Berry Custard | [consumption](item-mechanics.md#consumption) | 16 | 7 / 8.4; usable at full hunger | Drink; glowing I for 5 s | glass_bottle | No explicit fuel override |
| `fruit_salad` | Fruit Salad | [consumption](item-mechanics.md#consumption) | 16 | 6 / 7.2 | Regeneration I for 5 s | bowl | No explicit fuel override |
| `mixed_salad` | Mixed Salad | [consumption](item-mechanics.md#consumption) | 16 | 6 / 7.2 | Regeneration I for 5 s | bowl | No explicit fuel override |
| `nether_salad` | Nether Salad | [consumption](item-mechanics.md#consumption) | 16 | 5 / 4 | 30% nausea I for 12 s | bowl | No explicit fuel override |
| `barbecue_stick` | Barbecue on a Stick | [consumption](item-mechanics.md#consumption) | 64 | 8 / 14.4 | Ordinary eating; no added effect | none | No explicit fuel override |
| `egg_sandwich` | Egg Sandwich | [consumption](item-mechanics.md#consumption) | 64 | 8 / 12.8 | Ordinary eating; no added effect | none | No explicit fuel override |
| `chicken_sandwich` | Chicken Sandwich | [consumption](item-mechanics.md#consumption) | 64 | 10 / 16 | Ordinary eating; no added effect | none | No explicit fuel override |
| `hamburger` | Hamburger | [consumption](item-mechanics.md#consumption) | 64 | 11 / 17.6 | Ordinary eating; no added effect | none | No explicit fuel override |
| `bacon_sandwich` | Bacon Sandwich | [consumption](item-mechanics.md#consumption) | 64 | 10 / 16 | Ordinary eating; no added effect | none | No explicit fuel override |
| `mutton_wrap` | Mutton Wrap | [consumption](item-mechanics.md#consumption) | 64 | 10 / 16 | Ordinary eating; no added effect | none | No explicit fuel override |
| `dumplings` | Dumplings | [consumption](item-mechanics.md#consumption) | 64 | 8 / 12.8 | Ordinary eating; no added effect | none | No explicit fuel override |
| `stuffed_potato` | Stuffed Potato | [consumption](item-mechanics.md#consumption) | 64 | 10 / 14 | Ordinary eating; no added effect | none | No explicit fuel override |
| `cabbage_rolls` | Cabbage Rolls | [consumption](item-mechanics.md#consumption) | 64 | 5 / 5 | Ordinary eating; no added effect | none | No explicit fuel override |
| `salmon_roll` | Salmon Roll | [consumption](item-mechanics.md#consumption) | 64 | 7 / 8.4 | Ordinary eating; no added effect | none | No explicit fuel override |
| `cod_roll` | Cod Roll | [consumption](item-mechanics.md#consumption) | 64 | 7 / 8.4 | Ordinary eating; no added effect | none | No explicit fuel override |
| `kelp_roll` | Kelp Roll | [consumption](item-mechanics.md#consumption) | 64 | 12 / 12 | Eat in 2.4 s; no added effect | none | No explicit fuel override |
| `kelp_roll_slice` | Kelp Roll Slice | [consumption](item-mechanics.md#consumption) | 64 | 6 / 6 | Eat in 0.8 s; no added effect | none | No explicit fuel override |
| `cooked_rice` | Cooked Rice | [consumption](item-mechanics.md#consumption) | 16 | 6 / 4.8 | Nourishment I for 30 s | bowl | No explicit fuel override |
| `bone_broth` | Bone Broth | [consumption](item-mechanics.md#consumption) | 16 | 8 / 11.2 | Drink; Nourishment I for 60 s | bowl | No explicit fuel override |
| `beef_stew` | Beef Stew | [consumption](item-mechanics.md#consumption) | 16 | 12 / 19.2 | Nourishment I for 180 s | bowl | No explicit fuel override |
| `chicken_soup` | Chicken Soup | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `vegetable_soup` | Vegetable Soup | [consumption](item-mechanics.md#consumption) | 16 | 12 / 19.2 | Nourishment I for 180 s | bowl | No explicit fuel override |
| `fish_stew` | Fish Stew | [consumption](item-mechanics.md#consumption) | 16 | 12 / 19.2 | Nourishment I for 180 s | bowl | No explicit fuel override |
| `fried_rice` | Fried Rice | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `pumpkin_soup` | Pumpkin Soup | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `baked_cod_stew` | Baked Cod Stew | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `noodle_soup` | Noodle Soup | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `onion_soup` | Onion Soup | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `bacon_and_eggs` | Bacon and Eggs | [consumption](item-mechanics.md#consumption) | 16 | 10 / 12 | Nourishment I for 60 s | bowl | No explicit fuel override |
| `pasta_with_meatballs` | Pasta with Meatballs | [consumption](item-mechanics.md#consumption) | 16 | 12 / 19.2 | Nourishment I for 180 s | bowl | No explicit fuel override |
| `pasta_with_mutton_chop` | Pasta with Mutton Chop | [consumption](item-mechanics.md#consumption) | 16 | 12 / 19.2 | Nourishment I for 180 s | bowl | No explicit fuel override |
| `mushroom_rice` | Mushroom Rice | [consumption](item-mechanics.md#consumption) | 16 | 12 / 19.2 | Nourishment I for 180 s | bowl | No explicit fuel override |
| `roasted_mutton_chops` | Roasted Mutton Chops | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `vegetable_noodles` | Vegetable Noodles | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `steak_and_potatoes` | Steak and Potatoes | [consumption](item-mechanics.md#consumption) | 16 | 12 / 19.2 | Nourishment I for 180 s | bowl | No explicit fuel override |
| `ratatouille` | Ratatouille | [consumption](item-mechanics.md#consumption) | 16 | 10 / 12 | Nourishment I for 60 s | bowl | No explicit fuel override |
| `squid_ink_pasta` | Squid Ink Pasta | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `grilled_salmon` | Grilled Salmon | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `roast_chicken_block` | Roast Chicken | Places `roast_chicken_block`; [feasts](block-mechanics.md#feasts) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `roast_chicken` | Plate of Roast Chicken | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `stuffed_pumpkin_block` | Stuffed Pumpkin | Places `stuffed_pumpkin_block`; [feasts](block-mechanics.md#feasts) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `stuffed_pumpkin` | Bowl of Stuffed Pumpkin | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `honey_glazed_ham_block` | Honey Glazed Ham | Places `honey_glazed_ham_block`; [feasts](block-mechanics.md#feasts) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `honey_glazed_ham` | Plate of Honey Glazed Ham | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `shepherds_pie_block` | Shepherd's Pie | Places `shepherds_pie_block`; [feasts](block-mechanics.md#feasts) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `shepherds_pie` | Plate of Shepherd's Pie | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `gleaming_salad_block` | Gleaming Salad | Places `gleaming_salad_block`; [gleaming-salad](block-mechanics.md#gleaming-salad) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `gleaming_salad` | Bowl of Gleaming Salad | [consumption](item-mechanics.md#consumption) | 16 | 14 / 21 | Nourishment I for 300 s | bowl | No explicit fuel override |
| `rice_roll_medley_block` | Rice Roll Medley | Places `rice_roll_medley_block`; [rice-roll-medley](block-mechanics.md#rice-roll-medley) | 1 | Not food | Not consumed as food | none | No explicit fuel override |
| `dog_food` | Dog Food | [pet-food](item-mechanics.md#pet-food) | 16 | 4 / 1.6 | Ordinary eating; no added effect | bowl | No explicit fuel override |
| `horse_feed` | Horse Feed | [pet-food](item-mechanics.md#pet-food) | 16 | Not food | Not consumed as food | none | No explicit fuel override |
| `debug_pumpkin_pie` | debug pumpkin pie | Places `pumpkin_pie`; [pies](block-mechanics.md#pies); hidden diagnostic item, not normal progression | 64 | Not food | Not consumed as food | none | No explicit fuel override |
