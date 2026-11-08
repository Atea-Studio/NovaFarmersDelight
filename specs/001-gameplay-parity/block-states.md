# Exhaustive Block Appearance Atlas

Use the transition rules in [block-mechanics.md](block-mechanics.md) to determine state values FIRST. Then use this atlas to render them. A selector with omitted properties ignores those properties. Variant selectors select one appearance; an array selects one weighted alternative, not several overlapping shapes. Multipart rules add EVERY matching part. `OR` and `AND` have their ordinary logical meaning; alternatives within a property are OR. Rotation is relative to the unrotated appearance. Shape dimensions and texture bindings are listed in [visual-assets.md](visual-assets.md). Water, displayed inventory items, sign writing, particles and light are independent overlays. State domains stated in the behavior contracts include values not distinguished by a model selector.

## stove

[Decision rules](block-mechanics.md#stove)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `lit`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,lit=false` | stove (`farmersdelight:block/stove`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,lit=true` | stove on (`farmersdelight:block/stove_on`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,lit=false` | stove (`farmersdelight:block/stove`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,lit=true` | stove on (`farmersdelight:block/stove_on`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,lit=false` | stove (`farmersdelight:block/stove`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,lit=true` | stove on (`farmersdelight:block/stove_on`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,lit=false` | stove (`farmersdelight:block/stove`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,lit=true` | stove on (`farmersdelight:block/stove_on`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## cooking_pot

[Decision rules](block-mechanics.md#cooking-pot)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `support`: `none`, `tray`, `handle`; `waterlogged`: `false`, `true`.

24 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,support=handle` | cooking pot handle (`farmersdelight:block/cooking_pot_handle`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,support=none` | cooking pot (`farmersdelight:block/cooking_pot`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,support=tray` | cooking pot tray (`farmersdelight:block/cooking_pot_tray`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,support=handle` | cooking pot handle (`farmersdelight:block/cooking_pot_handle`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,support=none` | cooking pot (`farmersdelight:block/cooking_pot`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,support=tray` | cooking pot tray (`farmersdelight:block/cooking_pot_tray`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,support=handle` | cooking pot handle (`farmersdelight:block/cooking_pot_handle`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,support=none` | cooking pot (`farmersdelight:block/cooking_pot`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,support=tray` | cooking pot tray (`farmersdelight:block/cooking_pot_tray`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,support=handle` | cooking pot handle (`farmersdelight:block/cooking_pot_handle`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,support=none` | cooking pot (`farmersdelight:block/cooking_pot`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,support=tray` | cooking pot tray (`farmersdelight:block/cooking_pot_tray`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## skillet

[Decision rules](block-mechanics.md#skillet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `support`: `false`, `true`; `waterlogged`: `false`, `true`.

16 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,support=false` | skillet (`farmersdelight:block/skillet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,support=true` | skillet tray (`farmersdelight:block/skillet_tray`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,support=false` | skillet (`farmersdelight:block/skillet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,support=true` | skillet tray (`farmersdelight:block/skillet_tray`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,support=false` | skillet (`farmersdelight:block/skillet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,support=true` | skillet tray (`farmersdelight:block/skillet_tray`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,support=false` | skillet (`farmersdelight:block/skillet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,support=true` | skillet tray (`farmersdelight:block/skillet_tray`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## wooden_basket

[Decision rules](block-mechanics.md#basket)

Legal state domain: `facing`: `north`, `east`, `south`, `west`, `up`, `down`; `enabled`: `false`, `true`; `waterlogged`: `false`, `true`.

24 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=down` | wooden basket (`farmersdelight:block/wooden_basket`); pitch 180 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=east` | wooden basket (`farmersdelight:block/wooden_basket`); pitch 90 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north` | wooden basket (`farmersdelight:block/wooden_basket`); pitch 90 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south` | wooden basket (`farmersdelight:block/wooden_basket`); pitch 90 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=up` | wooden basket (`farmersdelight:block/wooden_basket`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=west` | wooden basket (`farmersdelight:block/wooden_basket`); pitch 90 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## bamboo_basket

[Decision rules](block-mechanics.md#basket)

Legal state domain: `facing`: `north`, `east`, `south`, `west`, `up`, `down`; `enabled`: `false`, `true`; `waterlogged`: `false`, `true`.

24 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=down` | bamboo basket (`farmersdelight:block/bamboo_basket`); pitch 180 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=east` | bamboo basket (`farmersdelight:block/bamboo_basket`); pitch 90 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north` | bamboo basket (`farmersdelight:block/bamboo_basket`); pitch 90 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south` | bamboo basket (`farmersdelight:block/bamboo_basket`); pitch 90 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=up` | bamboo basket (`farmersdelight:block/bamboo_basket`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=west` | bamboo basket (`farmersdelight:block/bamboo_basket`); pitch 90 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## cutting_board

[Decision rules](block-mechanics.md#cutting-board)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east` | cutting board (`farmersdelight:block/cutting_board`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north` | cutting board (`farmersdelight:block/cutting_board`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south` | cutting board (`farmersdelight:block/cutting_board`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west` | cutting board (`farmersdelight:block/cutting_board`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## carrot_crate

[Decision rules](block-mechanics.md#storage-blocks)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | carrot crate (`farmersdelight:block/carrot_crate`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## potato_crate

[Decision rules](block-mechanics.md#storage-blocks)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | potato crate (`farmersdelight:block/potato_crate`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## beetroot_crate

[Decision rules](block-mechanics.md#storage-blocks)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | beetroot crate (`farmersdelight:block/beetroot_crate`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## cabbage_crate

[Decision rules](block-mechanics.md#storage-blocks)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | cabbage crate (`farmersdelight:block/cabbage_crate`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## tomato_crate

[Decision rules](block-mechanics.md#storage-blocks)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | tomato crate (`farmersdelight:block/tomato_crate`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## onion_crate

[Decision rules](block-mechanics.md#storage-blocks)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | onion crate (`farmersdelight:block/onion_crate`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## rice_bale

[Decision rules](block-mechanics.md#storage-blocks)

Legal state domain: `facing`: `north`, `east`, `south`, `west`, `up`, `down`.

6 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=down` | rice bale (`farmersdelight:block/rice_bale`); pitch 180 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=east` | rice bale (`farmersdelight:block/rice_bale`); pitch 90 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north` | rice bale (`farmersdelight:block/rice_bale`); pitch 90 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south` | rice bale (`farmersdelight:block/rice_bale`); pitch 90 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=up` | rice bale (`farmersdelight:block/rice_bale`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=west` | rice bale (`farmersdelight:block/rice_bale`); pitch 90 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## rice_bag

[Decision rules](block-mechanics.md#storage-blocks)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | rice bag (`farmersdelight:block/rice_bag`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## straw_bale

[Decision rules](block-mechanics.md#storage-blocks)

Legal state domain: `axis`: `x`, `y`, `z`.

3 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `axis=x` | straw bale horizontal (`farmersdelight:block/straw_bale_horizontal`); pitch 90 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `axis=y` | straw bale (`farmersdelight:block/straw_bale`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `axis=z` | straw bale horizontal (`farmersdelight:block/straw_bale_horizontal`); pitch 90 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## rope

[Decision rules](block-mechanics.md#rope)

Legal state domain: `north`: `false`, `true`; `east`: `false`, `true`; `south`: `false`, `true`; `west`: `false`, `true`; `tied_to_bell`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Always | rope post (`farmersdelight:block/rope_post`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| tied_to_bell = true | rope bell tie (`farmersdelight:block/rope_bell_tie`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| north = true | rope side (`farmersdelight:block/rope_side`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| east = true | rope side (`farmersdelight:block/rope_side`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| south = true | rope side alt (`farmersdelight:block/rope_side_alt`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| west = true | rope side alt (`farmersdelight:block/rope_side_alt`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |

## safety_net

[Decision rules](block-mechanics.md#safety-net)

Legal state domain: `waterlogged`: `false`, `true`.

2 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | safety net (`farmersdelight:block/safety_net`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## rope_fence

[Decision rules](block-mechanics.md#rope-fences)

Legal state domain: `north`: `false`, `true`; `east`: `false`, `true`; `south`: `false`, `true`; `west`: `false`, `true`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Always | rope fence post (`farmersdelight:block/rope_fence_post`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| north = true | rope fence side (`farmersdelight:block/rope_fence_side`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| east = true | rope fence side (`farmersdelight:block/rope_fence_side`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| south = true | rope fence side alt (`farmersdelight:block/rope_fence_side_alt`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| west = true | rope fence side alt (`farmersdelight:block/rope_fence_side_alt`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |

## rope_fence_gate

[Decision rules](block-mechanics.md#rope-fences)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`; `powered`: `false`, `true`; `in_wall`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,in_wall=false,open=false` | rope fence gate (`farmersdelight:block/rope_fence_gate`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=east,in_wall=false,open=true` | rope fence gate open (`farmersdelight:block/rope_fence_gate_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=east,in_wall=true,open=false` | rope fence gate wall (`farmersdelight:block/rope_fence_gate_wall`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=east,in_wall=true,open=true` | rope fence gate wall open (`farmersdelight:block/rope_fence_gate_wall_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=north,in_wall=false,open=false` | rope fence gate (`farmersdelight:block/rope_fence_gate`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=north,in_wall=false,open=true` | rope fence gate open (`farmersdelight:block/rope_fence_gate_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=north,in_wall=true,open=false` | rope fence gate wall (`farmersdelight:block/rope_fence_gate_wall`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=north,in_wall=true,open=true` | rope fence gate wall open (`farmersdelight:block/rope_fence_gate_wall_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,in_wall=false,open=false` | rope fence gate (`farmersdelight:block/rope_fence_gate`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,in_wall=false,open=true` | rope fence gate open (`farmersdelight:block/rope_fence_gate_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,in_wall=true,open=false` | rope fence gate wall (`farmersdelight:block/rope_fence_gate_wall`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,in_wall=true,open=true` | rope fence gate wall open (`farmersdelight:block/rope_fence_gate_wall_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=west,in_wall=false,open=false` | rope fence gate (`farmersdelight:block/rope_fence_gate`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=west,in_wall=false,open=true` | rope fence gate open (`farmersdelight:block/rope_fence_gate_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=west,in_wall=true,open=false` | rope fence gate wall (`farmersdelight:block/rope_fence_gate_wall`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=west,in_wall=true,open=true` | rope fence gate wall open (`farmersdelight:block/rope_fence_gate_wall_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |

## oak_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | oak cabinet (`farmersdelight:block/oak_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | oak cabinet open (`farmersdelight:block/oak_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | oak cabinet (`farmersdelight:block/oak_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | oak cabinet open (`farmersdelight:block/oak_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | oak cabinet (`farmersdelight:block/oak_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | oak cabinet open (`farmersdelight:block/oak_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | oak cabinet (`farmersdelight:block/oak_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | oak cabinet open (`farmersdelight:block/oak_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## spruce_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | spruce cabinet (`farmersdelight:block/spruce_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | spruce cabinet open (`farmersdelight:block/spruce_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | spruce cabinet (`farmersdelight:block/spruce_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | spruce cabinet open (`farmersdelight:block/spruce_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | spruce cabinet (`farmersdelight:block/spruce_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | spruce cabinet open (`farmersdelight:block/spruce_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | spruce cabinet (`farmersdelight:block/spruce_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | spruce cabinet open (`farmersdelight:block/spruce_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## birch_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | birch cabinet (`farmersdelight:block/birch_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | birch cabinet open (`farmersdelight:block/birch_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | birch cabinet (`farmersdelight:block/birch_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | birch cabinet open (`farmersdelight:block/birch_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | birch cabinet (`farmersdelight:block/birch_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | birch cabinet open (`farmersdelight:block/birch_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | birch cabinet (`farmersdelight:block/birch_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | birch cabinet open (`farmersdelight:block/birch_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## jungle_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | jungle cabinet (`farmersdelight:block/jungle_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | jungle cabinet open (`farmersdelight:block/jungle_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | jungle cabinet (`farmersdelight:block/jungle_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | jungle cabinet open (`farmersdelight:block/jungle_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | jungle cabinet (`farmersdelight:block/jungle_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | jungle cabinet open (`farmersdelight:block/jungle_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | jungle cabinet (`farmersdelight:block/jungle_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | jungle cabinet open (`farmersdelight:block/jungle_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## acacia_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | acacia cabinet (`farmersdelight:block/acacia_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | acacia cabinet open (`farmersdelight:block/acacia_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | acacia cabinet (`farmersdelight:block/acacia_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | acacia cabinet open (`farmersdelight:block/acacia_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | acacia cabinet (`farmersdelight:block/acacia_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | acacia cabinet open (`farmersdelight:block/acacia_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | acacia cabinet (`farmersdelight:block/acacia_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | acacia cabinet open (`farmersdelight:block/acacia_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## dark_oak_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | dark oak cabinet (`farmersdelight:block/dark_oak_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | dark oak cabinet open (`farmersdelight:block/dark_oak_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | dark oak cabinet (`farmersdelight:block/dark_oak_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | dark oak cabinet open (`farmersdelight:block/dark_oak_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | dark oak cabinet (`farmersdelight:block/dark_oak_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | dark oak cabinet open (`farmersdelight:block/dark_oak_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | dark oak cabinet (`farmersdelight:block/dark_oak_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | dark oak cabinet open (`farmersdelight:block/dark_oak_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## mangrove_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | mangrove cabinet (`farmersdelight:block/mangrove_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | mangrove cabinet open (`farmersdelight:block/mangrove_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | mangrove cabinet (`farmersdelight:block/mangrove_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | mangrove cabinet open (`farmersdelight:block/mangrove_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | mangrove cabinet (`farmersdelight:block/mangrove_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | mangrove cabinet open (`farmersdelight:block/mangrove_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | mangrove cabinet (`farmersdelight:block/mangrove_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | mangrove cabinet open (`farmersdelight:block/mangrove_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## cherry_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | cherry cabinet (`farmersdelight:block/cherry_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | cherry cabinet open (`farmersdelight:block/cherry_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | cherry cabinet (`farmersdelight:block/cherry_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | cherry cabinet open (`farmersdelight:block/cherry_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | cherry cabinet (`farmersdelight:block/cherry_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | cherry cabinet open (`farmersdelight:block/cherry_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | cherry cabinet (`farmersdelight:block/cherry_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | cherry cabinet open (`farmersdelight:block/cherry_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## bamboo_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | bamboo cabinet (`farmersdelight:block/bamboo_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | bamboo cabinet open (`farmersdelight:block/bamboo_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | bamboo cabinet (`farmersdelight:block/bamboo_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | bamboo cabinet open (`farmersdelight:block/bamboo_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | bamboo cabinet (`farmersdelight:block/bamboo_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | bamboo cabinet open (`farmersdelight:block/bamboo_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | bamboo cabinet (`farmersdelight:block/bamboo_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | bamboo cabinet open (`farmersdelight:block/bamboo_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## crimson_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | crimson cabinet (`farmersdelight:block/crimson_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | crimson cabinet open (`farmersdelight:block/crimson_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | crimson cabinet (`farmersdelight:block/crimson_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | crimson cabinet open (`farmersdelight:block/crimson_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | crimson cabinet (`farmersdelight:block/crimson_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | crimson cabinet open (`farmersdelight:block/crimson_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | crimson cabinet (`farmersdelight:block/crimson_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | crimson cabinet open (`farmersdelight:block/crimson_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## warped_cabinet

[Decision rules](block-mechanics.md#cabinet)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `open`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,open=false` | warped cabinet (`farmersdelight:block/warped_cabinet`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,open=true` | warped cabinet open (`farmersdelight:block/warped_cabinet_open`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=false` | warped cabinet (`farmersdelight:block/warped_cabinet`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,open=true` | warped cabinet open (`farmersdelight:block/warped_cabinet_open`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=false` | warped cabinet (`farmersdelight:block/warped_cabinet`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,open=true` | warped cabinet open (`farmersdelight:block/warped_cabinet_open`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=false` | warped cabinet (`farmersdelight:block/warped_cabinet`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,open=true` | warped cabinet open (`farmersdelight:block/warped_cabinet_open`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## canvas_rug

[Decision rules](block-mechanics.md#canvas-rug)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas rug (`farmersdelight:block/canvas_rug`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## tatami

[Decision rules](block-mechanics.md#tatami)

Legal state domain: `facing`: `north`, `east`, `south`, `west`, `up`, `down`; `paired`: `false`, `true`.

12 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=down,paired=false` | tatami half (`farmersdelight:block/tatami_half`); pitch 180 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=down,paired=true` | tatami even (`farmersdelight:block/tatami_even`); pitch 180 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=east,paired=false` | tatami half (`farmersdelight:block/tatami_half`); pitch 90 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,paired=true` | tatami odd (`farmersdelight:block/tatami_odd`); pitch 90 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,paired=false` | tatami half (`farmersdelight:block/tatami_half`); pitch 90 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,paired=true` | tatami even (`farmersdelight:block/tatami_even`); pitch 90 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,paired=false` | tatami half (`farmersdelight:block/tatami_half`); pitch 90 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,paired=true` | tatami odd (`farmersdelight:block/tatami_odd`); pitch 90 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=up,paired=false` | tatami half (`farmersdelight:block/tatami_half`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=up,paired=true` | tatami odd (`farmersdelight:block/tatami_odd`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=west,paired=false` | tatami half (`farmersdelight:block/tatami_half`); pitch 90 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,paired=true` | tatami even (`farmersdelight:block/tatami_even`); pitch 90 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## full_tatami_mat

[Decision rules](block-mechanics.md#tatami)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `part`: `foot`, `head`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,part=foot` | tatami mat foot (`farmersdelight:block/tatami_mat_foot`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=east,part=head` | tatami mat head (`farmersdelight:block/tatami_mat_head`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=north,part=foot` | tatami mat foot (`farmersdelight:block/tatami_mat_foot`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=north,part=head` | tatami mat head (`farmersdelight:block/tatami_mat_head`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,part=foot` | tatami mat foot (`farmersdelight:block/tatami_mat_foot`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,part=head` | tatami mat head (`farmersdelight:block/tatami_mat_head`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=west,part=foot` | tatami mat foot (`farmersdelight:block/tatami_mat_foot`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=west,part=head` | tatami mat head (`farmersdelight:block/tatami_mat_head`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |

## half_tatami_mat

[Decision rules](block-mechanics.md#tatami)

Legal state domain: `facing`: `north`, `east`, `south`, `west`.

4 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east` | tatami mat half (`farmersdelight:block/tatami_mat_half`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north` | tatami mat half (`farmersdelight:block/tatami_mat_half`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south` | tatami mat half (`farmersdelight:block/tatami_mat_half`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west` | tatami mat half (`farmersdelight:block/tatami_mat_half`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## white_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## orange_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## magenta_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## light_blue_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## yellow_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## lime_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## pink_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## gray_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## light_gray_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## cyan_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## purple_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## blue_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## brown_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## green_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## red_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## black_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `waterlogged`: `false`, `true`.

32 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## white_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## orange_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## magenta_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## light_blue_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## yellow_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## lime_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## pink_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## gray_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## light_gray_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## cyan_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## purple_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## blue_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## brown_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## green_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## red_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## black_canvas_wall_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## white_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## orange_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## magenta_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## light_blue_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## yellow_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## lime_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## pink_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## gray_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## light_gray_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## cyan_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## purple_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## blue_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## brown_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## green_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## red_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## black_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `rotation`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`; `attached`: `false`, `true`; `waterlogged`: `false`, `true`.

64 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## white_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## orange_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## magenta_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## light_blue_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## yellow_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## lime_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## pink_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## gray_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## light_gray_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## cyan_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## purple_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## blue_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## brown_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## green_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## red_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## black_wall_hanging_canvas_sign

[Decision rules](block-mechanics.md#canvas-signs)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `waterlogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | canvas sign (`farmersdelight:block/canvas_sign`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## brown_mushroom_colony

[Decision rules](block-mechanics.md#mushroom-colonies)

Legal state domain: `age`: `0`, `1`, `2`, `3`.

4 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `age=0` | brown mushroom colony stage0 (`farmersdelight:block/brown_mushroom_colony_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1` | brown mushroom colony stage1 (`farmersdelight:block/brown_mushroom_colony_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2` | brown mushroom colony stage2 (`farmersdelight:block/brown_mushroom_colony_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3` | brown mushroom colony stage3 (`farmersdelight:block/brown_mushroom_colony_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## red_mushroom_colony

[Decision rules](block-mechanics.md#mushroom-colonies)

Legal state domain: `age`: `0`, `1`, `2`, `3`.

4 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `age=0` | red mushroom colony stage0 (`farmersdelight:block/red_mushroom_colony_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1` | red mushroom colony stage1 (`farmersdelight:block/red_mushroom_colony_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2` | red mushroom colony stage2 (`farmersdelight:block/red_mushroom_colony_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3` | red mushroom colony stage3 (`farmersdelight:block/red_mushroom_colony_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## organic_compost

[Decision rules](block-mechanics.md#organic-compost)

Legal state domain: `composting`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `composting=0` | organic compost stage0 (`farmersdelight:block/organic_compost_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage0 (`farmersdelight:block/organic_compost_stage0`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage0 (`farmersdelight:block/organic_compost_stage0`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage0 (`farmersdelight:block/organic_compost_stage0`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `composting=1` | organic compost stage0 (`farmersdelight:block/organic_compost_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage0 (`farmersdelight:block/organic_compost_stage0`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage0 (`farmersdelight:block/organic_compost_stage0`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage0 (`farmersdelight:block/organic_compost_stage0`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `composting=2` | organic compost stage1 (`farmersdelight:block/organic_compost_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage1 (`farmersdelight:block/organic_compost_stage1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage1 (`farmersdelight:block/organic_compost_stage1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage1 (`farmersdelight:block/organic_compost_stage1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `composting=3` | organic compost stage1 (`farmersdelight:block/organic_compost_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage1 (`farmersdelight:block/organic_compost_stage1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage1 (`farmersdelight:block/organic_compost_stage1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage1 (`farmersdelight:block/organic_compost_stage1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `composting=4` | organic compost stage2 (`farmersdelight:block/organic_compost_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage2 (`farmersdelight:block/organic_compost_stage2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage2 (`farmersdelight:block/organic_compost_stage2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage2 (`farmersdelight:block/organic_compost_stage2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `composting=5` | organic compost stage2 (`farmersdelight:block/organic_compost_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage2 (`farmersdelight:block/organic_compost_stage2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage2 (`farmersdelight:block/organic_compost_stage2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage2 (`farmersdelight:block/organic_compost_stage2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `composting=6` | organic compost stage3 (`farmersdelight:block/organic_compost_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage3 (`farmersdelight:block/organic_compost_stage3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage3 (`farmersdelight:block/organic_compost_stage3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage3 (`farmersdelight:block/organic_compost_stage3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `composting=7` | organic compost stage3 (`farmersdelight:block/organic_compost_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage3 (`farmersdelight:block/organic_compost_stage3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage3 (`farmersdelight:block/organic_compost_stage3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1; ALTERNATIVE: organic compost stage3 (`farmersdelight:block/organic_compost_stage3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## rich_soil

[Decision rules](block-mechanics.md#rich-soil)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | rich soil (`farmersdelight:block/rich_soil`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1; ALTERNATIVE: rich soil (`farmersdelight:block/rich_soil`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1; ALTERNATIVE: rich soil (`farmersdelight:block/rich_soil`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1; ALTERNATIVE: rich soil (`farmersdelight:block/rich_soil`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## rich_soil_farmland

[Decision rules](block-mechanics.md#rich-soil)

Legal state domain: `moisture`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `moisture=0` | rich soil farmland (`farmersdelight:block/rich_soil_farmland`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `moisture=1` | rich soil farmland (`farmersdelight:block/rich_soil_farmland`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `moisture=2` | rich soil farmland (`farmersdelight:block/rich_soil_farmland`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `moisture=3` | rich soil farmland (`farmersdelight:block/rich_soil_farmland`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `moisture=4` | rich soil farmland (`farmersdelight:block/rich_soil_farmland`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `moisture=5` | rich soil farmland (`farmersdelight:block/rich_soil_farmland`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `moisture=6` | rich soil farmland (`farmersdelight:block/rich_soil_farmland`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `moisture=7` | rich soil farmland moist (`farmersdelight:block/rich_soil_farmland_moist`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## apple_pie

[Decision rules](block-mechanics.md#pies)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `bites`: `0`, `1`, `2`, `3`.

16 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `bites=0,facing=east` | apple pie (`farmersdelight:block/apple_pie`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=north` | apple pie (`farmersdelight:block/apple_pie`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=south` | apple pie (`farmersdelight:block/apple_pie`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=west` | apple pie (`farmersdelight:block/apple_pie`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=east` | apple pie slice1 (`farmersdelight:block/apple_pie_slice1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=north` | apple pie slice1 (`farmersdelight:block/apple_pie_slice1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=south` | apple pie slice1 (`farmersdelight:block/apple_pie_slice1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=west` | apple pie slice1 (`farmersdelight:block/apple_pie_slice1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=east` | apple pie slice2 (`farmersdelight:block/apple_pie_slice2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=north` | apple pie slice2 (`farmersdelight:block/apple_pie_slice2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=south` | apple pie slice2 (`farmersdelight:block/apple_pie_slice2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=west` | apple pie slice2 (`farmersdelight:block/apple_pie_slice2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=east` | apple pie slice3 (`farmersdelight:block/apple_pie_slice3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=north` | apple pie slice3 (`farmersdelight:block/apple_pie_slice3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=south` | apple pie slice3 (`farmersdelight:block/apple_pie_slice3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=west` | apple pie slice3 (`farmersdelight:block/apple_pie_slice3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## sweet_berry_cheesecake

[Decision rules](block-mechanics.md#pies)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `bites`: `0`, `1`, `2`, `3`.

16 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `bites=0,facing=east` | sweet berry cheesecake (`farmersdelight:block/sweet_berry_cheesecake`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=north` | sweet berry cheesecake (`farmersdelight:block/sweet_berry_cheesecake`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=south` | sweet berry cheesecake (`farmersdelight:block/sweet_berry_cheesecake`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=west` | sweet berry cheesecake (`farmersdelight:block/sweet_berry_cheesecake`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=east` | sweet berry cheesecake slice1 (`farmersdelight:block/sweet_berry_cheesecake_slice1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=north` | sweet berry cheesecake slice1 (`farmersdelight:block/sweet_berry_cheesecake_slice1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=south` | sweet berry cheesecake slice1 (`farmersdelight:block/sweet_berry_cheesecake_slice1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=west` | sweet berry cheesecake slice1 (`farmersdelight:block/sweet_berry_cheesecake_slice1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=east` | sweet berry cheesecake slice2 (`farmersdelight:block/sweet_berry_cheesecake_slice2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=north` | sweet berry cheesecake slice2 (`farmersdelight:block/sweet_berry_cheesecake_slice2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=south` | sweet berry cheesecake slice2 (`farmersdelight:block/sweet_berry_cheesecake_slice2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=west` | sweet berry cheesecake slice2 (`farmersdelight:block/sweet_berry_cheesecake_slice2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=east` | sweet berry cheesecake slice3 (`farmersdelight:block/sweet_berry_cheesecake_slice3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=north` | sweet berry cheesecake slice3 (`farmersdelight:block/sweet_berry_cheesecake_slice3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=south` | sweet berry cheesecake slice3 (`farmersdelight:block/sweet_berry_cheesecake_slice3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=west` | sweet berry cheesecake slice3 (`farmersdelight:block/sweet_berry_cheesecake_slice3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## chocolate_pie

[Decision rules](block-mechanics.md#pies)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `bites`: `0`, `1`, `2`, `3`.

16 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `bites=0,facing=east` | chocolate pie (`farmersdelight:block/chocolate_pie`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=north` | chocolate pie (`farmersdelight:block/chocolate_pie`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=south` | chocolate pie (`farmersdelight:block/chocolate_pie`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=west` | chocolate pie (`farmersdelight:block/chocolate_pie`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=east` | chocolate pie slice1 (`farmersdelight:block/chocolate_pie_slice1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=north` | chocolate pie slice1 (`farmersdelight:block/chocolate_pie_slice1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=south` | chocolate pie slice1 (`farmersdelight:block/chocolate_pie_slice1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=west` | chocolate pie slice1 (`farmersdelight:block/chocolate_pie_slice1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=east` | chocolate pie slice2 (`farmersdelight:block/chocolate_pie_slice2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=north` | chocolate pie slice2 (`farmersdelight:block/chocolate_pie_slice2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=south` | chocolate pie slice2 (`farmersdelight:block/chocolate_pie_slice2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=west` | chocolate pie slice2 (`farmersdelight:block/chocolate_pie_slice2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=east` | chocolate pie slice3 (`farmersdelight:block/chocolate_pie_slice3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=north` | chocolate pie slice3 (`farmersdelight:block/chocolate_pie_slice3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=south` | chocolate pie slice3 (`farmersdelight:block/chocolate_pie_slice3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=west` | chocolate pie slice3 (`farmersdelight:block/chocolate_pie_slice3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## pumpkin_pie

[Decision rules](block-mechanics.md#pies)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `bites`: `0`, `1`, `2`, `3`.

16 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `bites=0,facing=east` | pumpkin pie (`farmersdelight:block/pumpkin_pie`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=north` | pumpkin pie (`farmersdelight:block/pumpkin_pie`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=south` | pumpkin pie (`farmersdelight:block/pumpkin_pie`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=0,facing=west` | pumpkin pie (`farmersdelight:block/pumpkin_pie`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=east` | pumpkin pie slice1 (`farmersdelight:block/pumpkin_pie_slice1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=north` | pumpkin pie slice1 (`farmersdelight:block/pumpkin_pie_slice1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=south` | pumpkin pie slice1 (`farmersdelight:block/pumpkin_pie_slice1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=1,facing=west` | pumpkin pie slice1 (`farmersdelight:block/pumpkin_pie_slice1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=east` | pumpkin pie slice2 (`farmersdelight:block/pumpkin_pie_slice2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=north` | pumpkin pie slice2 (`farmersdelight:block/pumpkin_pie_slice2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=south` | pumpkin pie slice2 (`farmersdelight:block/pumpkin_pie_slice2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=2,facing=west` | pumpkin pie slice2 (`farmersdelight:block/pumpkin_pie_slice2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=east` | pumpkin pie slice3 (`farmersdelight:block/pumpkin_pie_slice3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=north` | pumpkin pie slice3 (`farmersdelight:block/pumpkin_pie_slice3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=south` | pumpkin pie slice3 (`farmersdelight:block/pumpkin_pie_slice3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `bites=3,facing=west` | pumpkin pie slice3 (`farmersdelight:block/pumpkin_pie_slice3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## sandy_shrub

[Decision rules](block-mechanics.md#wild-plants)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | sandy shrub (`farmersdelight:block/sandy_shrub`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## wild_cabbages

[Decision rules](block-mechanics.md#wild-plants)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | wild cabbages (`farmersdelight:block/wild_cabbages`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## wild_onions

[Decision rules](block-mechanics.md#wild-plants)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | wild onions (`farmersdelight:block/wild_onions`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## wild_tomatoes

[Decision rules](block-mechanics.md#wild-plants)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | wild tomatoes (`farmersdelight:block/wild_tomatoes`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## wild_carrots

[Decision rules](block-mechanics.md#wild-plants)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | wild carrots (`farmersdelight:block/wild_carrots`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## wild_potatoes

[Decision rules](block-mechanics.md#wild-plants)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | wild potatoes (`farmersdelight:block/wild_potatoes`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## wild_beetroots

[Decision rules](block-mechanics.md#wild-plants)

Legal state domain: No state properties.

1 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| Any state | wild beetroots (`farmersdelight:block/wild_beetroots`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## wild_rice

[Decision rules](block-mechanics.md#wild-plants)

Legal state domain: `half`: `lower`, `upper`; `waterlogged`: `false`, `true`.

4 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `half=lower` | wild rice bottom (`farmersdelight:block/wild_rice_bottom`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `half=upper` | wild rice top (`farmersdelight:block/wild_rice_top`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## cabbages

[Decision rules](block-mechanics.md#cabbage-and-onion)

Legal state domain: `age`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `age=0` | cabbages stage0 (`farmersdelight:block/cabbages_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1` | cabbages stage1 (`farmersdelight:block/cabbages_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2` | cabbages stage2 (`farmersdelight:block/cabbages_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3` | cabbages stage3 (`farmersdelight:block/cabbages_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=4` | cabbages stage4 (`farmersdelight:block/cabbages_stage4`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=5` | cabbages stage5 (`farmersdelight:block/cabbages_stage5`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=6` | cabbages stage6 (`farmersdelight:block/cabbages_stage6`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=7` | cabbages stage7 (`farmersdelight:block/cabbages_stage7`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## onions

[Decision rules](block-mechanics.md#cabbage-and-onion)

Legal state domain: `age`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `age=0` | onions stage0 (`farmersdelight:block/onions_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1` | onions stage0 (`farmersdelight:block/onions_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2` | onions stage1 (`farmersdelight:block/onions_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3` | onions stage1 (`farmersdelight:block/onions_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=4` | onions stage2 (`farmersdelight:block/onions_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=5` | onions stage2 (`farmersdelight:block/onions_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=6` | onions stage2 (`farmersdelight:block/onions_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=7` | onions stage3 (`farmersdelight:block/onions_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## budding_tomatoes

[Decision rules](block-mechanics.md#tomatoes)

Legal state domain: `age`: `0`, `1`, `2`, `3`, `4`.

5 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `age=0` | budding tomatoes stage0 (`farmersdelight:block/budding_tomatoes_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1` | budding tomatoes stage1 (`farmersdelight:block/budding_tomatoes_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2` | budding tomatoes stage2 (`farmersdelight:block/budding_tomatoes_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3` | budding tomatoes stage3 (`farmersdelight:block/budding_tomatoes_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=4` | budding tomatoes stage3 (`farmersdelight:block/budding_tomatoes_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## tomatoes

[Decision rules](block-mechanics.md#tomatoes)

Legal state domain: `age`: `0`, `1`, `2`, `3`; `ropelogged`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `age=0,ropelogged=false` | tomatoes stage0 (`farmersdelight:block/tomatoes_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=0,ropelogged=true` | tomatoes old stage0 (`farmersdelight:block/tomatoes_old_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1,ropelogged=false` | tomatoes stage1 (`farmersdelight:block/tomatoes_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1,ropelogged=true` | tomatoes old stage1 (`farmersdelight:block/tomatoes_old_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2,ropelogged=false` | tomatoes stage2 (`farmersdelight:block/tomatoes_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2,ropelogged=true` | tomatoes old stage2 (`farmersdelight:block/tomatoes_old_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3,ropelogged=false` | tomatoes stage3 (`farmersdelight:block/tomatoes_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3,ropelogged=true` | tomatoes old stage3 (`farmersdelight:block/tomatoes_old_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## tomatoes_on_rope

[Decision rules](block-mechanics.md#tomatoes)

Legal state domain: `age`: `0`, `1`, `2`, `3`.

4 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `age=0` | tomatoes on rope stage0 (`farmersdelight:block/tomatoes_on_rope_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1` | tomatoes on rope stage1 (`farmersdelight:block/tomatoes_on_rope_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2` | tomatoes on rope stage2 (`farmersdelight:block/tomatoes_on_rope_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3` | tomatoes on rope stage3 (`farmersdelight:block/tomatoes_on_rope_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## rice

[Decision rules](block-mechanics.md#rice)

Legal state domain: `age`: `0`, `1`, `2`, `3`; `supporting`: `false`, `true`.

8 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `age=0,supporting=false` | rice stage0 (`farmersdelight:block/rice_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=0,supporting=true` | rice stage0 (`farmersdelight:block/rice_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1,supporting=false` | rice stage1 (`farmersdelight:block/rice_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1,supporting=true` | rice stage1 (`farmersdelight:block/rice_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2,supporting=false` | rice stage2 (`farmersdelight:block/rice_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2,supporting=true` | rice stage2 (`farmersdelight:block/rice_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3,supporting=false` | rice stage3 (`farmersdelight:block/rice_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3,supporting=true` | rice supporting (`farmersdelight:block/rice_supporting`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## rice_panicles

[Decision rules](block-mechanics.md#rice)

Legal state domain: `age`: `0`, `1`, `2`, `3`.

4 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `age=0` | rice panicles stage0 (`farmersdelight:block/rice_panicles_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=1` | rice panicles stage1 (`farmersdelight:block/rice_panicles_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=2` | rice panicles stage2 (`farmersdelight:block/rice_panicles_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `age=3` | rice panicles stage3 (`farmersdelight:block/rice_panicles_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |

## roast_chicken_block

[Decision rules](block-mechanics.md#feasts)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `servings`: `0`, `1`, `2`, `3`, `4`.

20 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,servings=0` | roast chicken block leftovers (`farmersdelight:block/roast_chicken_block_leftovers`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=1` | roast chicken block stage3 (`farmersdelight:block/roast_chicken_block_stage3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=2` | roast chicken block stage2 (`farmersdelight:block/roast_chicken_block_stage2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=3` | roast chicken block stage1 (`farmersdelight:block/roast_chicken_block_stage1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=4` | roast chicken block stage0 (`farmersdelight:block/roast_chicken_block_stage0`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=0` | roast chicken block leftovers (`farmersdelight:block/roast_chicken_block_leftovers`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=1` | roast chicken block stage3 (`farmersdelight:block/roast_chicken_block_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=2` | roast chicken block stage2 (`farmersdelight:block/roast_chicken_block_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=3` | roast chicken block stage1 (`farmersdelight:block/roast_chicken_block_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=4` | roast chicken block stage0 (`farmersdelight:block/roast_chicken_block_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=0` | roast chicken block leftovers (`farmersdelight:block/roast_chicken_block_leftovers`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=1` | roast chicken block stage3 (`farmersdelight:block/roast_chicken_block_stage3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=2` | roast chicken block stage2 (`farmersdelight:block/roast_chicken_block_stage2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=3` | roast chicken block stage1 (`farmersdelight:block/roast_chicken_block_stage1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=4` | roast chicken block stage0 (`farmersdelight:block/roast_chicken_block_stage0`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=0` | roast chicken block leftovers (`farmersdelight:block/roast_chicken_block_leftovers`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=1` | roast chicken block stage3 (`farmersdelight:block/roast_chicken_block_stage3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=2` | roast chicken block stage2 (`farmersdelight:block/roast_chicken_block_stage2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=3` | roast chicken block stage1 (`farmersdelight:block/roast_chicken_block_stage1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=4` | roast chicken block stage0 (`farmersdelight:block/roast_chicken_block_stage0`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## stuffed_pumpkin_block

[Decision rules](block-mechanics.md#feasts)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `servings`: `0`, `1`, `2`, `3`, `4`.

20 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,servings=0` | stuffed pumpkin block stage3 (`farmersdelight:block/stuffed_pumpkin_block_stage3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=1` | stuffed pumpkin block stage3 (`farmersdelight:block/stuffed_pumpkin_block_stage3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=2` | stuffed pumpkin block stage2 (`farmersdelight:block/stuffed_pumpkin_block_stage2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=3` | stuffed pumpkin block stage1 (`farmersdelight:block/stuffed_pumpkin_block_stage1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=4` | stuffed pumpkin block stage0 (`farmersdelight:block/stuffed_pumpkin_block_stage0`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=0` | stuffed pumpkin block stage3 (`farmersdelight:block/stuffed_pumpkin_block_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=1` | stuffed pumpkin block stage3 (`farmersdelight:block/stuffed_pumpkin_block_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=2` | stuffed pumpkin block stage2 (`farmersdelight:block/stuffed_pumpkin_block_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=3` | stuffed pumpkin block stage1 (`farmersdelight:block/stuffed_pumpkin_block_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=4` | stuffed pumpkin block stage0 (`farmersdelight:block/stuffed_pumpkin_block_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=0` | stuffed pumpkin block stage3 (`farmersdelight:block/stuffed_pumpkin_block_stage3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=1` | stuffed pumpkin block stage3 (`farmersdelight:block/stuffed_pumpkin_block_stage3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=2` | stuffed pumpkin block stage2 (`farmersdelight:block/stuffed_pumpkin_block_stage2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=3` | stuffed pumpkin block stage1 (`farmersdelight:block/stuffed_pumpkin_block_stage1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=4` | stuffed pumpkin block stage0 (`farmersdelight:block/stuffed_pumpkin_block_stage0`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=0` | stuffed pumpkin block stage3 (`farmersdelight:block/stuffed_pumpkin_block_stage3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=1` | stuffed pumpkin block stage3 (`farmersdelight:block/stuffed_pumpkin_block_stage3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=2` | stuffed pumpkin block stage2 (`farmersdelight:block/stuffed_pumpkin_block_stage2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=3` | stuffed pumpkin block stage1 (`farmersdelight:block/stuffed_pumpkin_block_stage1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=4` | stuffed pumpkin block stage0 (`farmersdelight:block/stuffed_pumpkin_block_stage0`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## honey_glazed_ham_block

[Decision rules](block-mechanics.md#feasts)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `servings`: `0`, `1`, `2`, `3`, `4`.

20 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,servings=0` | honey glazed ham block leftovers (`farmersdelight:block/honey_glazed_ham_block_leftovers`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=1` | honey glazed ham block stage3 (`farmersdelight:block/honey_glazed_ham_block_stage3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=2` | honey glazed ham block stage2 (`farmersdelight:block/honey_glazed_ham_block_stage2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=3` | honey glazed ham block stage1 (`farmersdelight:block/honey_glazed_ham_block_stage1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=4` | honey glazed ham block stage0 (`farmersdelight:block/honey_glazed_ham_block_stage0`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=0` | honey glazed ham block leftovers (`farmersdelight:block/honey_glazed_ham_block_leftovers`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=1` | honey glazed ham block stage3 (`farmersdelight:block/honey_glazed_ham_block_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=2` | honey glazed ham block stage2 (`farmersdelight:block/honey_glazed_ham_block_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=3` | honey glazed ham block stage1 (`farmersdelight:block/honey_glazed_ham_block_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=4` | honey glazed ham block stage0 (`farmersdelight:block/honey_glazed_ham_block_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=0` | honey glazed ham block leftovers (`farmersdelight:block/honey_glazed_ham_block_leftovers`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=1` | honey glazed ham block stage3 (`farmersdelight:block/honey_glazed_ham_block_stage3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=2` | honey glazed ham block stage2 (`farmersdelight:block/honey_glazed_ham_block_stage2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=3` | honey glazed ham block stage1 (`farmersdelight:block/honey_glazed_ham_block_stage1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=4` | honey glazed ham block stage0 (`farmersdelight:block/honey_glazed_ham_block_stage0`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=0` | honey glazed ham block leftovers (`farmersdelight:block/honey_glazed_ham_block_leftovers`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=1` | honey glazed ham block stage3 (`farmersdelight:block/honey_glazed_ham_block_stage3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=2` | honey glazed ham block stage2 (`farmersdelight:block/honey_glazed_ham_block_stage2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=3` | honey glazed ham block stage1 (`farmersdelight:block/honey_glazed_ham_block_stage1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=4` | honey glazed ham block stage0 (`farmersdelight:block/honey_glazed_ham_block_stage0`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## shepherds_pie_block

[Decision rules](block-mechanics.md#feasts)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `servings`: `0`, `1`, `2`, `3`, `4`.

20 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,servings=0` | shepherds pie block leftovers (`farmersdelight:block/shepherds_pie_block_leftovers`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=1` | shepherds pie block stage3 (`farmersdelight:block/shepherds_pie_block_stage3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=2` | shepherds pie block stage2 (`farmersdelight:block/shepherds_pie_block_stage2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=3` | shepherds pie block stage1 (`farmersdelight:block/shepherds_pie_block_stage1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=4` | shepherds pie block stage0 (`farmersdelight:block/shepherds_pie_block_stage0`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=0` | shepherds pie block leftovers (`farmersdelight:block/shepherds_pie_block_leftovers`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=1` | shepherds pie block stage3 (`farmersdelight:block/shepherds_pie_block_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=2` | shepherds pie block stage2 (`farmersdelight:block/shepherds_pie_block_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=3` | shepherds pie block stage1 (`farmersdelight:block/shepherds_pie_block_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=4` | shepherds pie block stage0 (`farmersdelight:block/shepherds_pie_block_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=0` | shepherds pie block leftovers (`farmersdelight:block/shepherds_pie_block_leftovers`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=1` | shepherds pie block stage3 (`farmersdelight:block/shepherds_pie_block_stage3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=2` | shepherds pie block stage2 (`farmersdelight:block/shepherds_pie_block_stage2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=3` | shepherds pie block stage1 (`farmersdelight:block/shepherds_pie_block_stage1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=4` | shepherds pie block stage0 (`farmersdelight:block/shepherds_pie_block_stage0`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=0` | shepherds pie block leftovers (`farmersdelight:block/shepherds_pie_block_leftovers`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=1` | shepherds pie block stage3 (`farmersdelight:block/shepherds_pie_block_stage3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=2` | shepherds pie block stage2 (`farmersdelight:block/shepherds_pie_block_stage2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=3` | shepherds pie block stage1 (`farmersdelight:block/shepherds_pie_block_stage1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=4` | shepherds pie block stage0 (`farmersdelight:block/shepherds_pie_block_stage0`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## gleaming_salad_block

[Decision rules](block-mechanics.md#gleaming-salad)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `servings`: `0`, `1`, `2`, `3`, `4`.

20 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,servings=0` | gleaming salad block leftovers (`farmersdelight:block/gleaming_salad_block_leftovers`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=1` | gleaming salad block stage3 (`farmersdelight:block/gleaming_salad_block_stage3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=2` | gleaming salad block stage2 (`farmersdelight:block/gleaming_salad_block_stage2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=3` | gleaming salad block stage1 (`farmersdelight:block/gleaming_salad_block_stage1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=4` | gleaming salad block stage0 (`farmersdelight:block/gleaming_salad_block_stage0`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=0` | gleaming salad block leftovers (`farmersdelight:block/gleaming_salad_block_leftovers`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=1` | gleaming salad block stage3 (`farmersdelight:block/gleaming_salad_block_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=2` | gleaming salad block stage2 (`farmersdelight:block/gleaming_salad_block_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=3` | gleaming salad block stage1 (`farmersdelight:block/gleaming_salad_block_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=4` | gleaming salad block stage0 (`farmersdelight:block/gleaming_salad_block_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=0` | gleaming salad block leftovers (`farmersdelight:block/gleaming_salad_block_leftovers`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=1` | gleaming salad block stage3 (`farmersdelight:block/gleaming_salad_block_stage3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=2` | gleaming salad block stage2 (`farmersdelight:block/gleaming_salad_block_stage2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=3` | gleaming salad block stage1 (`farmersdelight:block/gleaming_salad_block_stage1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=4` | gleaming salad block stage0 (`farmersdelight:block/gleaming_salad_block_stage0`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=0` | gleaming salad block leftovers (`farmersdelight:block/gleaming_salad_block_leftovers`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=1` | gleaming salad block stage3 (`farmersdelight:block/gleaming_salad_block_stage3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=2` | gleaming salad block stage2 (`farmersdelight:block/gleaming_salad_block_stage2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=3` | gleaming salad block stage1 (`farmersdelight:block/gleaming_salad_block_stage1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=4` | gleaming salad block stage0 (`farmersdelight:block/gleaming_salad_block_stage0`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |

## rice_roll_medley_block

[Decision rules](block-mechanics.md#rice-roll-medley)

Legal state domain: `facing`: `north`, `east`, `south`, `west`; `servings`: `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`.

36 legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.

| State condition | Displayed appearance |
| --- | --- |
| `facing=east,servings=0` | rice roll medley block leftovers (`farmersdelight:block/rice_roll_medley_block_leftovers`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=1` | rice roll medley block stage7 (`farmersdelight:block/rice_roll_medley_block_stage7`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=2` | rice roll medley block stage6 (`farmersdelight:block/rice_roll_medley_block_stage6`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=3` | rice roll medley block stage5 (`farmersdelight:block/rice_roll_medley_block_stage5`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=4` | rice roll medley block stage4 (`farmersdelight:block/rice_roll_medley_block_stage4`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=5` | rice roll medley block stage3 (`farmersdelight:block/rice_roll_medley_block_stage3`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=6` | rice roll medley block stage2 (`farmersdelight:block/rice_roll_medley_block_stage2`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=7` | rice roll medley block stage1 (`farmersdelight:block/rice_roll_medley_block_stage1`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=east,servings=8` | rice roll medley block stage0 (`farmersdelight:block/rice_roll_medley_block_stage0`); pitch 0 degrees; yaw 90 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=0` | rice roll medley block leftovers (`farmersdelight:block/rice_roll_medley_block_leftovers`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=1` | rice roll medley block stage7 (`farmersdelight:block/rice_roll_medley_block_stage7`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=2` | rice roll medley block stage6 (`farmersdelight:block/rice_roll_medley_block_stage6`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=3` | rice roll medley block stage5 (`farmersdelight:block/rice_roll_medley_block_stage5`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=4` | rice roll medley block stage4 (`farmersdelight:block/rice_roll_medley_block_stage4`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=5` | rice roll medley block stage3 (`farmersdelight:block/rice_roll_medley_block_stage3`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=6` | rice roll medley block stage2 (`farmersdelight:block/rice_roll_medley_block_stage2`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=7` | rice roll medley block stage1 (`farmersdelight:block/rice_roll_medley_block_stage1`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=north,servings=8` | rice roll medley block stage0 (`farmersdelight:block/rice_roll_medley_block_stage0`); pitch 0 degrees; yaw 0 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=0` | rice roll medley block leftovers (`farmersdelight:block/rice_roll_medley_block_leftovers`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=1` | rice roll medley block stage7 (`farmersdelight:block/rice_roll_medley_block_stage7`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=2` | rice roll medley block stage6 (`farmersdelight:block/rice_roll_medley_block_stage6`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=3` | rice roll medley block stage5 (`farmersdelight:block/rice_roll_medley_block_stage5`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=4` | rice roll medley block stage4 (`farmersdelight:block/rice_roll_medley_block_stage4`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=5` | rice roll medley block stage3 (`farmersdelight:block/rice_roll_medley_block_stage3`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=6` | rice roll medley block stage2 (`farmersdelight:block/rice_roll_medley_block_stage2`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=7` | rice roll medley block stage1 (`farmersdelight:block/rice_roll_medley_block_stage1`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=south,servings=8` | rice roll medley block stage0 (`farmersdelight:block/rice_roll_medley_block_stage0`); pitch 0 degrees; yaw 180 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=0` | rice roll medley block leftovers (`farmersdelight:block/rice_roll_medley_block_leftovers`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=1` | rice roll medley block stage7 (`farmersdelight:block/rice_roll_medley_block_stage7`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=2` | rice roll medley block stage6 (`farmersdelight:block/rice_roll_medley_block_stage6`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=3` | rice roll medley block stage5 (`farmersdelight:block/rice_roll_medley_block_stage5`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=4` | rice roll medley block stage4 (`farmersdelight:block/rice_roll_medley_block_stage4`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=5` | rice roll medley block stage3 (`farmersdelight:block/rice_roll_medley_block_stage3`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=6` | rice roll medley block stage2 (`farmersdelight:block/rice_roll_medley_block_stage2`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=7` | rice roll medley block stage1 (`farmersdelight:block/rice_roll_medley_block_stage1`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
| `facing=west,servings=8` | rice roll medley block stage0 (`farmersdelight:block/rice_roll_medley_block_stage0`); pitch 0 degrees; yaw 270 degrees; rotate texture with shape; weight 1 |
