# Block Mechanics

This document is normative together with [catalog.md](catalog.md), [block-states.md](block-states.md), [recipes.md](recipes.md), [drops.md](drops.md), and [memberships.md](memberships.md). Each heading is a contract assigned to individual catalog entries. State domains list all legal values, including values that share a model. Displayed contents, water, light and particles supplement the state atlas. Random-growth probabilities are per random growth opportunity, not per ordinary simulation step. Do not equate mesh geometry with collision geometry.

## Shared Rules

- `facing` means north/east/south/west unless stated otherwise. Rotation and mirroring transform facing and directional connections consistently.
- Waterlogged states are Boolean. Placement into water starts waterlogged; contained water remains a source and participates in ordinary water updates. Waterlogging does not select a different solid model unless the atlas says so.
- Empty/full inventories, item names, wear and effects are item state, not new block appearance values.
- Break yields use the exact drop contracts, including explosion eligibility, chance, enchantment bonuses and stored-state preservation. Inventory drops described here are additional to the block's own drop unless explicitly preserved inside that item.
- A fullness comparator for N inventory slots emits 0 when all slots are empty; otherwise `floor(14 * sum(count / min(slot limit, item stack limit)) / N) + 1`. A custom comparator described below overrides this rule.
- Heat is present when the immediately lower block is a heat-source group member and, if it has a lit state, is lit. Otherwise one heat-conductor layer immediately below may transmit heat from a source exactly two blocks below. No longer conductor chain is supported. Tray eligibility is a different test: membership of the immediately lower block in tray heat sources, irrespective of whether it is lit.
- Exact baseline dependencies are listed in [dependencies.md](dependencies.md). Do not infer missing mechanics from appearance names.

## Stove

**State domain**: `facing` four directions; `lit` false/true. Default north/unlit; normal placement is opposite player facing and LIT. Lit emits light level 13; unlit emits 0. Full-block collision.

- No ongoing fuel consumption is required. Ignition uses flint-and-steel (one wear) or a fire charge (one consumed outside creative). A dousing shovel extinguishes and takes one wear. A water bucket extinguishes and becomes its crafting remainder outside creative, when it has one.
- Six independently displayed cooking positions each hold exactly one ingredient. Insert one item into the first empty position if it has an open-fire-cooking recipe. Creative insertion uses a copy. An unlit stove still accepts ingredients.
- Each lit step advances each occupied position by one. Finish at its recipe's OPEN-FIRE duration, eject the recipe output above the stove, and clear that position. The supplied stove selects open-fire recipes, not smoker recipes. If the recipe disappears, completion ejects the original ingredient instead. A disabled output is not ejected and its ingredient remains.
- When unlit, decrease each position's progress by two per step, clamped to zero. Persist ingredient, progress and total time.
- A block above obstructs cooking if its selection shape intersects the top grilling region `(3,0,3)-(13,1,13)` in that upper block's local coordinates. Obstruction prevents insertion and ejects all stored food on the next processing step. Breaking also drops all stored food.
- A living entity touching that region above a lit stove takes 1 health point of stove-burn damage unless stepping carefully. This is not automatic ignition of the entity.
- Show smoke and flames at the front when lit, crackling, and cooking-item smoke while occupied.

**Acceptance**: Given two ingredients inserted at different times, when one reaches its duration first, then only its slot ejects output. Given a newly placed stove, then it is lit without supplying fuel. Given an obstruction above, when the next step runs, then ingredients are returned rather than cooked invisibly.

## Cooking Pot

**State domain**: `facing` four directions; `support` none/tray/handle; `waterlogged` false/true. Default north/none/dry. Selection bounds `(2,0,2)-(14,10,14)`. Tray collision additionally includes `(0,-1,0)-(16,0,16)`.

- Placement faces opposite the player. Placing against an underside selects handle. Other placement selects tray if eligible immediately below, otherwise none.
- Sneaking empty-hand use switches handle to automatically selected tray/none, or any non-handle to handle. Play a metal placement sound. Vertical neighbor changes recompute tray/none but never override handle.
- Inventory: six ingredient slots, one internal meal reservoir, one serving-container input, one output. Meal reservoir allows at least 64 portions, even for meals whose normal stack limit is 16. It is not a manually extractable ingredient slot.
- Match a cooking-pot recipe using exactly its occupied ingredient multiset; ingredient slot ordering is irrelevant. Require heat and reservoir space for the entire recipe output. Reservoir contents must be the same item as the result.
- Valid processing advances by one per step; cooking duration is the matched recipe's duration. At completion reset progress, set the serving-container identity from the recipe, add result portions, consume one from every nonempty ingredient slot, record experience, and eject ingredient remainders to the pot's left (counterclockwise from facing).
- With missing heat, no input, invalid recipe or insufficient reservoir space, progress retreats by two per step, clamped between zero and the last total. Waterlogging does not explicitly disable pot heat processing.
- Ingredients return their ordinary crafting remainders first. Additional overrides: powder-snow and creature/fish buckets return buckets; suspicious/mushroom/rabbit/beetroot stew return bowls; potion/splash potion/lingering potion/experience bottles return glass bottles.
- A containerless meal transfers automatically to output, bounded by the item's normal stack limit. Container meals consume one matching container per portion from the container input. Output must be empty or the same meal; never overwrite a different output.
- Held-container use serves one reservoir portion and consumes one held container, even though this path has no creative exemption. Add the portion to the player inventory or drop it when full. If serving fails, open the pot inventory. Container validity uses the stored recipe container, not any arbitrary bowl.
- Breaking drops ingredient slots, container input and output separately. Internal meal/container information and name are carried by the dropped pot item; do not also drop the reservoir as loose meals. Persist inventory, meal container, name, progress and recipe-use counts while placed. Picking the block yields its stored pot item.
- Experience equals total recorded completions times recipe experience, rounded down with one probabilistic extra point for the fractional remainder. Manual output collection awards recipes/experience and clears the tracker; breaking releases outstanding experience.
- Fullness comparator uses all nine slots. Heated pots bubble and steam; boiling sound differs when the reservoir contains a meal. Contents are communicated by inventory and pot-item meal tooltips rather than a different pot mesh.

**Acceptance**: Given 63 reservoir portions and a four-portion result, then cooking cannot consume ingredients. Given a cooled half-cooked pot, then progress retreats by two per step. Given a stored meal and correct held bowl, then one serving and one bowl are transferred. Given handle support and a changed heat source below, then the handle remains visible.

## Skillet

**State domain**: `facing` four directions; `support` false/true; `waterlogged` false/true. Default north/no tray/dry. Placement faces WITH the player, unlike the pot. Tray Boolean is the eligibility test immediately below, recalculated on vertical neighbor changes. Selection `(1,0,1)-(15,4,15)`; tray adds `(0,-1,0)-(16,0,16)` to collision.

- Place the item only while sneaking. Preserve the skillet item's wear, name and enchantments in the placed block and restore that item when picked/dropped.
- One inventory slot holds a normal stack of a single open-fire-cookable ingredient. A nonempty skillet rejects further insertion, even of the same ingredient. New insertion resets progress and sets total cooking duration. Creative insertion leaves the held stack intact.
- Cooking time for positive recipe time T and Fire Aspect level L: let S be integer `T / 20`; let R = `0.2 - 0.05 * L`; duration is `truncate(S * R) * 20`, clamped to a minimum 60 and maximum T. Normal 600-step recipes take 120 steps without the enchantment, 80 at level I, 60 at level II. Nonpositive or shorter-than-minimum recipe durations are an unresolved boundary in the supplied formula; do not invent a new recipe default.
- When heated, advance one step; finish one ingredient, eject its recipe result to the right (clockwise from facing), reset progress and continue through the stack. No recipe at completion means no output and no consumption. Missing heat retreats progress by two steps. Cooking uses shared direct/conducted heat.
- Waterlogging ejects all ingredients on the next step; underwater insertion fails with feedback. Empty-hand use removes the entire ingredient stack; in creative it is removed without being returned to the hand. Breaking drops stored ingredients separately from the skillet item.
- Display the ingredient in the pan, emit steam and sizzling when heated/occupied; Fire Aspect adds enchanted-hit particles. The item also supports handheld cooking and combat described in [item-mechanics.md](item-mechanics.md#handheld-skillet).

**Acceptance**: Given a 600-step recipe, when cooked without enchantment, then each unit takes 120 steps. Given waterlogging during cooking, then uncooked contents are ejected. Given a loaded skillet, when more ingredients are offered, then none are inserted.

## Cutting Board

**State domain**: `facing` four directions; `waterlogged` Boolean. Default north/dry; place opposite player facing. Selection/collision `(1,0,1)-(15,1,15)`. Needs rigid support or upper-face center support below; removing support removes the board.

- One ordinary stack-sized inventory slot. ALL interactions inspect the player's MAIN HAND. With empty main hand remove the entire stored stack; survival attempts to add it to inventory, creative clears it without returning it. The supplied path does not explicitly drop an overflow on inventory-add failure; this is a documented loss edge case, not permission to duplicate.
- With a held stack that can fit, insert as much as fits, preserving creative hand contents. Insertion takes priority over recipe processing. Otherwise try a cutting recipe using stored ingredient and held tool.
- Each successful processing action rolls every result according to recipe chance and Fortune, ejects results left of the board, consumes one stored ingredient, and takes one tool wear. It is instantaneous, not a timed cooking process. Invalid tool/item feedback must not consume ingredients.
- Sneak-use can lodge a tiered tool, trident or shears in an empty compatible board. This marks the stored stack as a carved display; carved boards reject insertion and processing. Removing the tool clears carved status. Persist both inventory and carved status.
- Display stored food/items on top; carved tools are shown lodged into the board rather than lying flat. Recipe sound overrides defaults; otherwise use shearing, knife chopping, held-block breaking, or wood breaking in that order. Successful processing produces five ingredient particles.
- Inventory access is exposed on every side. Breaking drops the stored stack. Comparator uses the one-slot fullness formula.

**Acceptance**: Given food on a board and a matching tool in main hand, when processing is used, then one ingredient and one wear produce the recipe rolls. Given a carved knife, when food is offered, then the knife remains and no processing occurs. Given an offhand tool but empty main hand, then the stored item is removed, not cut.

## Cabbage And Onion

**State domain**: `age` 0..7. Cabbage seeds place cabbages; onion item places onions and can also be eaten. Both inherit the ordinary farmland-crop growth, light, fertilization and survival contract; exact mature/immature drops are in the drop tables. No custom right-click regrowing harvest is defined here.

- Cabbage selection heights by age are 2, 3, 5, 7, 8, 9, 9, 10 sixteenths; onion heights are 2, 3, 4, 5, 6, 7, 8, 9; both have full-block horizontal extent.
- Render ALL atlas ages even where two ages share geometry. Mature is age 7, not the last distinct model name.

**Acceptance**: Given age 6, then mature-only loot conditions do not match; given age 7 they do. Replanting starts age 0.

## Tomatoes

**State domains**: `budding_tomatoes`: age 0..4; ordinary `tomatoes`: age 0..3 and `ropelogged` Boolean; `tomatoes_on_rope`: age 0..3 only. The old ropelogged appearance is still a legal supplied state and MUST be included.

- Budding tomatoes grow on farmland. Survive at raw light >=8 unless an explicit soil eligibility override decides otherwise. On random opportunities with light >=9, increase age using probability `1 / (floor(25 / growth speed) + 1)`. Growth speed starts 1; inspect the 3x3 soil area, adding 1 for default soil eligibility or 3 if fertile, quartering noncentral contributions. Same plants in both cardinal rows, or any diagonal when not both rows, halve speed. Preserve the supplied soil-eligibility behavior, including its default-decision distinction.
- Normal seedling progression 0->1->2->3; the next successful growth replaces it with age-0 ordinary tomatoes. Age 4 is a transitional state: a neighbor update schedules conversion after three steps. Fertilizer adds uniform 1..4, capped at 7; if beyond age 3, replace with ordinary tomatoes at `new age - 4`.
- Ordinary and rope tomato vines grow fruit ages 0..3 with 1/6 probability per growth opportunity at light >=9. At age 3, empty-hand harvest drops uniform 1..2 tomatoes PLUS an independent 5% rotten tomato, then resets fruit age to 0 and plays picking sound. Breaking uses separate loot tables.
- At light >=9, vines also attempt to climb eligible rope immediately above, replacing it with age-0 rope tomatoes. Count consecutive blocks of the SAME vine block type below; allow climbing when that count including this block is <3. Do not conflate this implementation with a global mixed-type column-height test.
- Fertilizer age increase is half the ordinary crop fertilizer increment, using integer division. Within maximum age, set the new age and independently attempt climbing with 30% probability. Overflow attempts to climb rope above or recursively fertilize an eligible rope-vine above. A mature vine is fertilizer-eligible if either of the next two spaces is climbable rope or an immature rope vine; encountering another type blocks the search.
- Ordinary ropelogged tomatoes convert to `tomatoes_on_rope`, preserving age, on their next random opportunity or fertilizer action. No new ropelogged states need be introduced by normal seed placement.
- Vines on another ordinary/rope tomato need raw light >=8 OR sky visibility; base vines use ordinary crop support. Invalid survival schedules a check one step later. Legacy ropelogged destruction restores configured rope. Player removal of rope tomatoes restores rope; other removal restores it when rope-permanence is enabled, except piston movement. Use connected rope state for the standard rope, default state for a configured alternative.
- Vine selection `(2,0,2)-(14,16,14)`; seedlings have heights 2,6,10,14,14. Seed/clone item is tomato seeds for all forms. Configurable rope eligibility and permanence defaults are in the dependency contract.

**Acceptance**: Given a mature vine, when harvested without an item, then it remains planted at age 0. Given an age-2 legacy ropelogged vine, when conversion occurs, then the rope-vine is age 2. Given rope tomatoes removed by a player, then a connected rope remains.

## Rice

**State domains**: lower `rice`: age 0..3, supporting Boolean; upper `rice_panicles`: age 0..3. Lower is ALWAYS source-water-filled, not optionally waterlogged. Supporting is true exactly when rice panicles occupy the block above and is recomputed on an upper-neighbor update.

- Rice item plants only in full source water (amount 8) over acceptable bush substrate or dirt-group substrate. Survival also requires source water. Flowing/shallow water fails placement. Do not permit adding another fluid into this plant.
- Lower random growth requires brightness of the block above >=6. Probability 1/3 per opportunity. Ages below 3 advance one; at age 3 a further successful opportunity creates age-0 panicles above if empty and viable.
- Upper panicles survive only on lower rice and with light >=8 OR sky visibility. Upper growth follows ordinary crop random growth; upper fertilizer increment is one third of the ordinary crop increment using integer division.
- Lower fertilizer adds uniform 1..4, capped at 7. At totals <=3 update the lower age. At higher totals, fertilize existing eligible panicles, or if upper space is empty/viable set lower age 3 and create upper age `total - 4`. Mature panicles prevent lower fertilizer eligibility.
- Lower and upper age-selection bounds are `(3,0,3)-(13,8,13)`, `(3,0,3)-(13,10,13)`, `(2,0,2)-(14,12,14)`, `(1,0,1)-(15,16,15)` respectively. Supporting changes lower appearance as in the atlas. Harvest upper portions to leave submerged rice growing; break drops are separate for lower and upper.

**Acceptance**: Given flowing water, when rice is planted, then placement fails. Given lower age 3 with empty space above, when natural growth succeeds, then panicles age 0 appear and supporting becomes true. Given panicles removed, then lower supporting becomes false.

## Organic Compost

**State domain**: composting 0..7; default 0. Full dirt-like block, hardness 1.2.

- Each random opportunity examines the entire 3x3x3 neighborhood INCLUDING the compost position. Add 0.02 probability per compost-activator block. Add 0.1 if any examined block contains water. Find maximum sky light at each examined position's block ABOVE: add 0.1 when maximum >12, otherwise 0.05.
- Advance when uniform random float <= computed probability. Ages 0..6 advance by one; a successful opportunity at 7 replaces the block with rich soil. Thus reaching 7 is not itself completion.
- Comparator emits `8 - composting`, decreasing from 8 to 1. Emit occasional mycelium particles. All eight stages and their shared/distinct appearances are in the atlas.

**Acceptance**: Given stage 6 and a successful roll, then stage becomes 7, not soil. Given stage 7 and a successful roll, then rich soil replaces it. Given dry, dark compost without activators, then the roll threshold is 0.05.

## Rich Soil

**State domains**: rich soil has no custom state; rich-soil farmland moisture 0..7.

- Rich soil converts an ordinary brown/red mushroom directly above into an age-0 colony on a random opportunity, before rolling any growth boost.
- Otherwise roll configured growth-boost chance; zero disables boosting. First attempt eligible plant above unless it belongs to planted-from-below; if that succeeds stop. Otherwise attempt the plant below only if it belongs to planted-from-below. Excluded plants never receive boosts. Eligible fertilizer-responsive plants receive their ordinary fertilizer action and boost particles.
- Hoe use on rich soil with AIR above converts to rich-soil farmland. Rich soil's exact allowed noncrop plant compatibility is unresolved in the supplied source; see dependencies rather than assuming universal support.
- Farmland checks hydration in horizontal offsets -4..4 and vertical offsets 0..1, or rain immediately above, or an explicit hydration ticket. Wet farmland jumps to moisture 7. Already moisture-7 hydrated farmland may boost plants. Without hydration reduce moisture by one, never revert to soil merely because moisture reaches zero.
- Moisture >0 counts as fertile. Farmland supports ordinary crops explicitly. It can remain under pumpkins/melons. An invalid solid obstruction converts it to rich soil on the scheduled check and raises intersecting entities to the new full surface. Invalid placement yields rich soil instead.
- Falling causes ordinary fall damage but does not trample rich-soil farmland. Persist moisture. Dry/wet appearance uses the atlas, even when several moisture values share the same appearance.

**Acceptance**: Given moisture 1 and no hydration, when a random opportunity occurs, then moisture becomes 0 but farmland remains. Given a mushroom above rich soil, then colony conversion takes priority over other boosting. Given a falling player, then soil is not trampled.

## Mushroom Colonies

**State domain**: age 0..3; default 0. Brown and red use their corresponding mushroom item. Bounds by age `(4,0,4)-(12,8,12)`, `(3,0,3)-(13,10,13)`, `(2,0,2)-(14,12,14)`, `(1,0,1)-(15,14,15)`.

- Survive on a mushroom-light-override substrate regardless of light; otherwise require raw light <13 and solid-render substrate, unless explicit plant-support eligibility overrides the latter decision.
- Natural growth only on colony-growable substrates, age <3, probability 1/4. Fertilizer works at age <3 on any viable substrate, adds uniform 1..2 capped at 3, and always succeeds.
- At age >0, shears harvest one ordinary mushroom and reduce age by one; knife harvests `age` ordinary mushrooms and resets to 0. Both take one wear, play matching sound and emit block particles. Age 0 cannot be harvested through these use actions.
- Picking produces an ordinary mushroom, not a colony item. Silk-preserved/broken colony quantities follow the drop contracts. Colony ITEM placement starts at age 3; only soil conversion starts at age 0. Item placement still checks the mature state's viability.

**Acceptance**: Given age 3 and a knife, then three mushrooms drop and age becomes 0. Given age 3 and shears, then one drops and age becomes 2. Given age 0, then shearing has no harvesting effect.

## Basket

**State domain**: facing all six directions; enabled Boolean; waterlogged Boolean. Default facing up/dry; placement uses opposite nearest look direction. Enabled is recomputed as NOT receiving neighboring power when power changes; placement itself does not explicitly sample power.

- Wooden and bamboo baskets have identical mechanics and distinct material appearance/sound. Inventory has 27 slots in three rows. Use opens storage; contents and collection cooldown persist. Breaking drops contents. External insertion/extraction works on every side. No automatic pushing to a neighboring inventory is defined.
- Every step decreases cooldown. At zero, if enabled and not full, inspect loose living item entities inside the basket's own full cube PLUS the full cube immediately along facing. Try candidates in world enumeration order. Insert through slots in ascending order, merging only identical item and stored-state stacks.
- Fully absorbing a loose stack removes that entity, counts as success, stops collection for that step, and sets cooldown to eight steps. Partial absorption updates the loose remainder but does NOT report full collection or trigger that cooldown; more candidates may be tried the same step.
- Collision is a hollow cube with 2/16-thick walls and a 2/16-thick back, opening towards facing. The cavity extends to the opening face; interaction targeting still uses the outer full cube. Water does not disable collection. Fullness comparator uses all 27 slots. Enabled/water states do not independently change the basket mesh.

**Acceptance**: Given an up-facing enabled basket and a loose stack one block above, then it can collect the stack. Given power, then collection stops after enabled updates. Given partial space, then the remaining loose stack retains only the uninserted items.

## Cabinet

**State domain**: facing four directions; open Boolean. Default north/closed; place opposite player facing.

- All eleven wood variants use 27 storage slots and the same behavior. Open storage on use even if another block is adjacent to the front/top; there is no chest-style obstruction check here. Opening angers nearby piglins under their ordinary container-interaction rules.
- The first nonspectator viewer opens the doors and plays opening sound; the last viewer closing makes them closed and plays closing sound. Periodic viewer rechecks correct stale counts. Spectators do not change the door state. With multiple viewers, one closing must not close the doors for the others.
- Contents/name persist; contents drop on destruction. Every side supports external item transfer. Fullness comparator uses all 27 slots. Solid cabinet collision does not become hollow/open when the door model changes.

**Acceptance**: Given two viewers, when one closes, then open stays true; when the last closes, it becomes false. Given a cabinet against a wall, then use can still open its inventory.

## Pies

**State domain**: facing four directions; bites 0..3. Place WITH player facing, bites 0. Require rigid support below. Four servings total; there is no persistent bites=4 block.

- Apple pie, berry cheesecake, chocolate pie and placed pumpkin pie produce their respective slice. With a knife, immediately cut and drop one slice toward the player, increasing bites or removing the fourth/final portion. The supplied cutting action does NOT damage the knife. It produces slice sound and three food particles.
- Empty-hand/default eating requires the player to be able to eat. It is an instantaneous block interaction rather than the handheld eating duration. Add the slice's food values and run its consumption behavior, increase bites or remove the final portion, play eating sound and particles. The source currently both directly feeds and calls slice consumption; see the double-feeding ambiguity in dependencies before asserting an exact in-world nutrition total.
- Comparator emits 4,3,2,1 for bites 0,1,2,3. Rotated selection/collision from north: whole `(2,0,2)-(14,4,14)`; bites 1 union of `(2,0,8)-(8,4,14)` and `(2,0,2)-(14,4,8)`; bites 2 `(2,0,2)-(14,4,8)`; bites 3 `(8,0,2)-(14,4,8)`.
- Handheld pie items use ordinary block-item placement; the shared placeable-food item adds a placement tooltip, not a sneak restriction. Ordinary pumpkin pie additionally has placement behavior added by a global interaction rule; by default it does NOT require sneaking, with a configurable sneak-only option. The placed pumpkin-pie clone is the ordinary pumpkin pie; a hidden diagnostic item also places this block but is not normal progression. Break yields depend on remaining portions and tool/drop conditions, not on the fourth-consumption behavior.

**Acceptance**: Given bites 3, when a knife is used, then one slice drops and the block disappears without knife wear. Given full hunger, then direct eating fails and bites remain unchanged.

## Feasts

**State domain**: facing four directions; servings 0..4. Default north/4; place opposite player facing. Require rigid support below. Each feast item stack limit is one; ordinary block-item placement applies without an added sneak restriction.

| Block | Portion | Empty state |
| --- | --- | --- |
| roast_chicken_block | roast_chicken | Tray remains at servings 0 |
| honey_glazed_ham_block | honey_glazed_ham | Tray remains at servings 0 |
| shepherds_pie_block | shepherds_pie | Tray remains at servings 0 |
| stuffed_pumpkin_block | stuffed_pumpkin | Block vanishes after final portion |

- Use the serving's required crafting container (these portions use bowls). On success reduce servings by one, consume one bowl outside creative, give one portion to inventory or drop it if full, and play portion sound/three food particles. Wrong container gives feedback and no state change. Players need not be hungry to take a portion.
- At servings 0, use destroys leftovers with drops and wood-breaking sound. Empty trays are distinct persistent visible states, not an invisible consumed block. Comparator equals servings.
- Chicken, ham and shepherd's pie have serving-dependent collision shapes rotated with facing, including tray geometry; exact sixteenth-coordinate shape unions are in the collision appendix. Stuffed pumpkin uses the general feast age-shape heights 2,2,4,6,8 for servings 0..4; copied material properties do NOT make its collision a full pumpkin cube.

**Acceptance**: Given servings 1 chicken and a bowl, then a portion is given and an empty tray remains. Given servings 1 stuffed pumpkin, then the block disappears after serving. Given the wrong held item, then servings are unchanged.

## Gleaming Salad

Uses the feast contract, facing four directions and servings 0..4; four portions of gleaming salad, empty bowl remains. Constant collision union `(3,0,3)-(13,2,13)` and `(2,2,2)-(14,6,14)`. Light level is `3 * servings`: 12,9,6,3,0. Sparkle probability per visual opportunity is `0.05 * servings`; none at zero. Its food and empty-bowl states are distinct appearance states even though collision is constant.

**Acceptance**: Given servings 4, when one portion is taken, then light drops from 12 to 9 and the three-serving appearance is selected.

## Rice Roll Medley

**State domain**: facing four directions; servings 0..8. Default eight; retains board at zero. Models MUST show each remaining count, not just empty/full.

- Portion identity is determined by remaining servings BEFORE decrement: 8,7,6 -> kelp roll slice; 5,4,3 -> salmon roll; 2,1 -> cod roll. Thus there are three kelp slices, three salmon rolls and two cod rolls in serving order. No serving particles.
- Selection/collision stays the board shape even as rolls disappear: axis-X facing uses union `(2,1,1)-(14,3,15)`, `(2,0,3)-(14,2,5)`, `(2,0,11)-(14,2,13)`; axis-Z swaps X/Z. Comparator equals remaining servings.
- **NEEDS CLARIFICATION**: The supplied inherited serving operation requires a non-null crafting container, but none of the listed rolls has one. It therefore does not establish a working normal take-portion interaction and can dereference a missing remainder in feedback. The ordered portion/state contract is established; a corrected containerless interaction must be explicitly decided before claiming playable identical serving behavior.

**Acceptance after clarification**: Given servings 6, then the selected portion is a kelp roll slice; at 5 it is salmon; at 2 it is cod. Given zero, use removes the leftover board according to its drop contract.

## Rope

**State domain**: north/east/south/west connection Booleans, tied_to_bell Boolean, waterlogged Boolean. Default all false. No collision; climbable under the climbable group; selection is inherited connected cross-shape, with a small bottom-center support `(7,0,7)-(9,1,9)`.

- Initial horizontal-face placement connects to sturdy neighbor faces except connection-exception blocks, OR to iron-bar-like connected blocks and walls. Top/bottom placement connects only to those connected blocks and walls. Neighbor updates subsequently recompute each horizontal connection using ONLY the connected-block/wall rule, so an initial solid-face tie can disappear after an update. Do not assume every adjacent rope-shaped block is eligible; use the membership/type rule actually specified.
- A bell immediately above sets tied_to_bell true; upper-neighbor change recomputes it. The atlas combines all matching connections and bell attachment pieces. There is no vertical-connection state property.
- Rope item use extends downward along an existing continuous rope column to a valid empty/replaceable destination; see the item contract. The block is replaceable by its own item.
- Empty-hand use, unless taking the sneaking reeling path, searches upward at most 24 examined positions through standard ropes for a bell; ring the first bell, stop at any other block.
- With reeling enabled, sneaking empty-hand use requires build permission and creative or space for one rope in inventory. Traverse down the continuous rope column, destroy the bottommost rope without drops, and return one rope in survival. The world-minimum boundary loop and the early inventory grant have a source edge case; see dependencies.

**Acceptance**: Given a rope with a bell directly above, then tied_to_bell is true. Given a horizontal placement tie to a solid wall face, when a horizontal neighbor update occurs, then it is recomputed with the narrower rule. Given a bell beyond a nonrope block, then use does not ring it.

## Rope Fences

Fence state: four horizontal connection Booleans plus waterlogged. Gate state: facing four directions, open Boolean, powered Boolean, in_wall Boolean. All state combinations must follow atlas selectors even where powered/in_wall share models.

- Fence connects to solid nonexception faces, the same rope fence, or a rope fence gate whose orientation admits a fence connection on that side. Recompute on corresponding neighbor changes. It can bind the player's leashed creatures under ordinary fence/lead rules. Post visual/occlusion bounds `(7,0,7)-(9,16,9)`, connected collision reaches height 24/16; selection height 16/16.
- Gate inherits ordinary gate placement, manual toggle and power-change behavior. Open has empty collision; closed axis-Z-facing collision `(0,0,7)-(16,24,9)`, axis-X `(7,0,0)-(9,24,16)`. Selection remains height 14/16 with thickness 2/16 even while open. Use rope-gate opening/closing sounds.
- For side-neighbor updates along the gate's perpendicular axis, in_wall becomes true only when BOTH bordering neighbors are wall-group blocks. Initial inherited placement may use the ordinary gate's wall rule; that difference is an external baseline dependency. No waterlogged gate property is defined.

**Acceptance**: Given a powered gate, then inherited gate rules open it. Given one bordering wall after a relevant neighbor update, then in_wall is false; given two, true. Given an open gate, then entities can pass through it.

## Tatami

- Full cube `tatami`: facing all six directions, paired Boolean; default down/unpaired. Face points opposite the clicked face, toward the block placed against. If that block is unpaired tatami and the player is not sneaking, mark the new block paired and update the existing block to face back towards the new block and become paired. Sneaking avoids pairing. When a paired block loses the tatami neighbor in its facing direction, clear paired; keep the cube, do not destroy it.
- `full_tatami_mat`: facing four directions, part foot/head. One item places two floor pieces; foot faces with the player and head is one block along facing. Require replaceable head space and nonempty support below both pieces. Both are height 2/16. Matching counterpart must be the same block with opposite part in the expected adjacent direction. Missing counterpart or floor removes the affected piece. Creative destruction of foot explicitly removes matching head without an extra item. Only the loot-authorized half yields the item; do not double the drop.
- `half_tatami_mat`: facing four directions, placed opposite player facing, single block height 2/16, needs nonempty floor. No pairing property; piston behavior destroys this thin mat. Neither mat is a bed or provides sleeping despite head/foot vocabulary.

**Acceptance**: Given unpaired tatami, when another is placed against it without sneaking, then both face one another and are paired. Given a full mat with blocked second space, then placement fails. Given a removed full-mat counterpart, then the orphan vanishes without duplicating the item.

## Canvas Rug

No custom state. Grass-sounding carpet-like block, hardness 0.2, passes skylight. Collision/selection height 1/16 across the full horizontal block. It inherits ordinary BLOCK survival, not carpet survival; no floor check is added, so unsupported placement is not prevented by this class. It is not dyeable through a block interaction in this source; no color-state family is registered.

**Acceptance**: Given floor support removal, then no custom survival transition removes the rug. Placing does not change neighbors' rug shapes.

## Displayed Contents And Collision Appendix

- Stove displays one model for each occupied slot, flat, scale 0.375, centered at height 1.02 with offsets in its rotated top plane: `(0.3,0.2)`, `(0,0.2)`, `(-0.3,0.2)`, `(0.3,-0.2)`, `(0,-0.2)`, `(-0.3,-0.2)`. Rotate opposite facing. Inventory changes, not block states, decide which models appear.
- Board/skillet displayed-model count: empty ->0; stack of one ->1; larger stack ->`1 + ceil(4 * count / normal item stack limit)`, up to five. Board uses deterministic item/wear-seeded horizontal scatter in [-0.075,0.075] for multiple models; skillet uses it even for one. These positions must be stable between frames.
- Board flat items: center X/Z 0.5 plus scatter, height `0.08 + 0.03*(index+1)`, pitch 90 degrees, scale 0.6. Volumetric block-items not in flat-on-board group: height `0.27 + 0.03*(index+1)`, upright, scale 0.8. Orient toward the opposite of board facing. Carved tool: centered height 0.23, scale 0.6; reverse facing and rotate around Z by 225 degrees for pickaxes/hoes, 135 for tridents, 180 otherwise.
- Skillet ingredients: center X/Z 0.5 plus scatter, height `0.1 + 0.03*(index+1)`, pitch 90 degrees, scale 0.5, orient with skillet facing.
- Pot automation from TOP inserts/extracts the six ingredient slots only. Every other supplied side inserts serving containers into container input and extracts output only. Never expose meal reservoir extraction. A direct unsided handler, when present, follows top rules; the supplied registration selects the output-side wrapper for any context other than top.
- Ordinary fences are explicitly prevented from connecting to rope fence gates and from treating rope fences as their own fence type. This overrides inherited ordinary-fence decisions.
- Rotated-feast tray is outer `(1,0,1)-(15,2,15)` MINUS inner `(2,1,2)-(14,2,14)`. At servings 0 use tray only; at 1..4 union tray with the corresponding shape below, rotated north/east/south/west around the block center.

| Servings | Chicken food shape | Ham food shape | Shepherd's pie food shape |
| --- | --- | --- | --- |
| 1 | (4,1,10)-(12,8,12) | (4,1,3)-(12,3,11) | (2,1,8)-(8,8,14) |
| 2 | (4,1,8)-(12,8,12) | (4,1,6)-(12,9,10) | (2,1,8)-(14,8,14) |
| 3 | (4,1,6)-(12,8,12) | (4,2,4)-(12,10,10) | Union (8,1,2)-(14,8,8) and (2,1,8)-(14,8,14) |
| 4 | (4,1,4)-(12,8,12) | (4,2,2)-(12,10,10) | (2,1,2)-(14,8,14) |

## Safety Net

**State domain**: waterlogged Boolean, default dry; water sampled on placement. Collision/selection `(0,7,0)-(16,9,16)` across the middle of the block; no floor support requirement is added.

- A fall with bounce suppression uses ordinary fall-damage and landing behavior. Otherwise take zero fall damage. If descending, reverse vertical velocity and multiply by 0.6 for living entities or 0.8 for other entities; horizontal velocity unchanged. Non-descending entities are not bounced.

**Acceptance**: Given living downward speed -1 without suppression, then landing produces +0.6 upward speed and no fall damage. Given suppression, then normal fall damage applies.

## Storage Blocks

- Carrot/potato/beetroot/cabbage/tomato/onion crates and rice bag have no custom states or inventories. They are reversible crafting storage, not containers. Compression and decompression quantities come from recipes; do not implement storage menus.
- Crates use full-cube wooden collision, hardness 2 and blast resistance 3. Rice bag uses wool-like full-cube material properties. Items have no explicit fuel override unless the catalog lists one; do not derive fuel solely from visual wood.
- Rice bale: facing all six directions, default up, placed facing the clicked face; fall damage multiplier 0.2, fire spread 60, flammability 20. All six facing states exist even where the appearance shares an axis.
- Straw bale: axis x/y/z, placed along clicked-face axis; hay-like fall-damage multiplier 0.2, fire spread 60, flammability 20. State is AXIS, not six-way facing.

**Acceptance**: Given a crate, when used, then no inventory opens. Given a bale landing, then apply 20% of ordinary fall damage. Given straw bale placed on a side, then its axis follows that face's axis.

## Wild Plants

- Wild cabbage/onion/tomato/carrot/potato/beetroot have no custom states, selection `(2,0,2)-(14,13,14)`, viable dirt/sand substrate, and cannot be replaced by normal placement. Wild-crop fire spread 60 and flammability 100. They behave as flowers for suspicious-stew ingredients: cabbage strength 6 s; onion fire resistance 6 s; tomato poison 10 s; carrot mining fatigue 6 s; potato nausea 8 s; beetroot water breathing 8 s.
- Wild-crop fertilizer success probability 80%. Before spreading, count same crop in offsets X/Z -4..4 and Y -1..1, including self; at 10 or more abort. Perform four random-walk trials: X/Z offset uniform -1..1, Y offset independent draw 0..1 minus draw 0..1; keep viable empty candidates as the next walk origin, then make one final trial and place at most one new crop if viable/empty. This is propagation, not maturation or direct crop-item dispensing.
- Sandy shrub: no custom states; same selection bounds, only sand substrate, shearable under base shearing/drop rules. Fertilizer is always eligible/successful and attempts its defined nearby shrub-patch generation; terrain restrictions and placement counts are in the acquisition appendix.
- Wild rice: half lower/upper and waterlogged Boolean, default lower/wet. Placement requires source water, dirt/sand floor, empty upper space and world-height room; creates dry upper half. Fluid output depends on HALF (lower source water, upper empty), irrespective of stored waterlogged value. Only lower permits liquid-placement eligibility. Matching counterpart loss removes the orphan. Never treat it as cultivated rice's age states.
- Wild-rice fertilizer is always eligible with 30% success; on success drops one wild-rice block item rather than creating an age or automatically planting a neighbor.

**Acceptance**: Given nine wild crops including the target, then a successful fertilizer action may add at most one. Given ten, then none is added. Given wild-rice upper-half removal, then counterpart rules remove the orphan lower half. Given successful wild-rice fertilizer, then one item drops.

## Canvas Signs

Seventeen backgrounds: undyed plus white, orange, magenta, light blue, yellow, lime, pink, gray, light gray, cyan, purple, blue, brown, green, red, black. Each has standing, wall, ceiling-hanging and wall-hanging world forms; two inventory items choose ordinary vs hanging placement. Wall forms drop their corresponding ordinary/hanging inventory item, not a nonexistent wall-form item.

- Standing state domain: rotation 0..15 (22.5-degree yaw increments), waterlogged Boolean. Wall: facing four directions, waterlogged Boolean. Ceiling-hanging: rotation 0..15, attached Boolean, waterlogged Boolean. Wall-hanging: facing four directions, waterlogged Boolean. State atlas is supplemented by sign renderer geometry and text: a minimal block model is NOT the complete visible sign.
- Normal sign placement quantizes player yaw into 16 orientations; wall forms point away from the supporting face. Hanging placement selects ceiling vs wall attachment through ordinary hanging-sign eligibility, including attached-chain behavior for the supporting underside and sneaking intent. Exact support, rotation quantization and linked hanging-chain decisions are baseline dependencies, not free design choices.
- Support loss removes unsupported signs. Preserve separate front/back text, four lines per side, text dye, glow and wax state. Ordinary inherited sign interactions provide editing of the selected side, dye coloring, glow-ink enhancement, ink removal of glow and honeycomb waxing; waxed text cannot be edited normally. Background color belongs to the block identity, not the text dye or an automatic neighbor effect.
- On placement, configured dark backgrounds default FRONT text to white for standing/wall forms; hanging forms default BOTH sides white. Other backgrounds retain ordinary default text color. Existing placed text is not recolored merely by changing configuration. Undyed canvas is the original beige asset, not white dye.
- Sign appearance must include canvas panels, supports/chains, side-specific writing and glow; texture references and geometry are in the visual appendix. External inherited sign sound, support and editing behavior must be pinned before parity completion.

**Acceptance**: Given a new dark ordinary canvas sign, then front text is white and back keeps its ordinary default. Given a dark hanging sign, then both default white. Given a wall variant broken, then it returns the matching inventory sign. Given rotation values 0..15, then each rotates the complete panel and text together.