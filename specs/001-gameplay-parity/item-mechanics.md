# Item Mechanics

Every item has its own row in [catalog.md](catalog.md). A block item also implements its linked block contract. Foods MUST use the catalog's actual food assignment, not values guessed from their name. Unregistered legacy item classes do not establish active gameplay.

## Consumption

- Ordinary food use requires hunger space unless marked usable at full hunger or the consumer has Nourishment. Consumable drinks with no hunger value can still be used; they are not zero-nutrition ingredients accidentally missing an eating action. Default ordinary consumption is the shared baseline, with explicit 0.8/2.4-second overrides in the catalog.
- Hunger gain is the catalog's nutrition. Saturation gain is the catalog's saturation POINTS, capped by resulting hunger under the shared food rules. One heart is two health points. Effects with an amplifier of zero mean level I.
- Apply each stated effect only on completion; interruption does not grant effects or consume the unit. Probabilities are independent where multiple actions exist. Milk bottle removes ONE uniformly chosen active effect outside its ignored group; hot cocoa removes ONE uniformly chosen HARMFUL effect outside its ignored group. With no eligible effects, consume normally and remove nothing. Do not use the legacy milk/hot-cocoa classes' different logic: these drinks are actively registered as ordinary consumable items with the stated effect actions.
- Melon juice heals two health points up to maximum. Melon popsicle extinguishes but grants no fire-resistance duration. Apple cider gives absorption I for 60 seconds, no hunger. Glow-berry custard can be used at full hunger and is a drink action with glowing I for five seconds. Salad regeneration and pastry speed follow catalog durations.
- Custom consumable items return their declared container even from stacked foods: when the final food unit disappears, return the container in hand; with food remaining, give the container to survival inventory or drop it if full. Creative consumption does not shrink the stack or give extra containers through the stacked branch. Ordinary items with declared remainders additionally rely on baseline consumption semantics; verify both classes of item instead of applying the custom rule indiscriminately.
- Bowl meals normally stack to 16. Tomato sauce is an explicit exception: stack 64, bowl remainder. Custard stacks 16, bottle remainder. Slices/cookies/cuts normally stack 64. No recipe ingredient is consumed by merely hovering or looking at its tooltip.
- Onion soup uses shepherd's-pie values: hunger 14, saturation 21, Nourishment 300 seconds. Kelp roll is directly defined with hunger 12 and saturation 12, not a multiplier of 12. These are source facts, not suggested balance changes.

**Acceptance**: Given two milk bottles and three eligible effects, when one is drunk, then exactly one uniformly selected effect is removed and a bottle is returned. Given full hunger and popsicle, then consumption is allowed and extinguishes the consumer. Given tomato sauce, then its stack limit is 64, not the standard meal limit of 16.

## Food Effects

- Nourishment is beneficial and runs each gameplay step for players. If natural regeneration is enabled, the player is hurt and has saturation >0, leave exhaustion untouched so saturation healing can occur. Otherwise set exhaustion to zero, preventing hunger loss through that mechanism. Nourishment does not directly refill hunger or saturation. It permits eating even at full hunger. Display the configured gilded hunger-meter overlay while active.
- Comfort, where externally granted, is beneficial; every 80 remaining-duration steps heal one health point when hurt, unless Regeneration is active or a player still has saturation >0. Amplifier does not change the pace. It is not assigned to an active catalog food by the current food definitions. Keep its optional health overlay separate from food-effect assignment.
- With ordinary-soup extra effects enabled, mushroom stew and beetroot soup gain Nourishment 180 seconds, rabbit stew 300 seconds, except the active rabbit-buff bypass below. With rabbit nutrition buff enabled, rabbit stew has hunger 14 and saturation 21, and the soup-finish extra-effect handler explicitly skips it. Thus current default behavior improves its food values but does not add that handler's Nourishment. If the buff is disabled and soup extra effects remain enabled, rabbit stew gets the 300-second effect.
- Configured ordinary soups (default mushroom stew, beetroot soup, rabbit stew) stack to 16 when enabled. This runtime modification is independent of their recipe quantities.

**Acceptance**: Given Nourishment and no saturation healing, then exhaustion returns to zero each step. Given Nourishment, injury, positive saturation and enabled natural regeneration, then exhaustion is not reset by the effect. Given default rabbit settings, then use buffed nutrition without assuming an added Nourishment effect.

## Knives

Six variants: flint, copper, iron, diamond, netherite, golden. All stack to one. Use material durability, repair ingredient, mining speed, enchantability and damage bonus; exact inherited material values must be pinned by the baseline contract. Flint is defined here: durability 131, mining speed 4, damage bonus 1, enchantability 5; repair uses flint-tool-materials membership; incorrect-drop eligibility follows stone-tier tools.

- Main-hand attack damage modifier = material damage bonus +0.5; attack-speed modifier = -2 (ordinary player baseline 4 gives attack speed 2). Mining knife-eligible blocks uses material speed and correct-drop eligibility; otherwise mining speed 1. Configured tool mining wear 1 and weapon declaration wear 2 coexist with a custom post-hit action taking one wear; resolve exact combined combat wear using the baseline consumption-of-weapon-wear rule, not by blindly adding both.
- Support knife digging/harvesting and pumpkin-carving abilities. Exclude Sweeping Edge; other enchantment eligibility follows the knife enchantable groups. Netherite knife is fire-resistant.
- Knockback event strength is original minus 0.1 when the credited living attacker currently holds a knife in main hand; this uses credited-attacker state, not just an item's damage-source name.
- On ordinary pumpkin use with a knife-group item, carve pumpkin; side clicks face the carving toward that clicked direction, vertical clicks opposite player facing. Drop four pumpkin seeds outward, play carving sound, take one wear. Knife slicing placed pies/cake does NOT use this wear action.
- Knife-use ordinary cake advances bites 0..6 and drops one cake slice; at bites 6 remove cake. Cake-slice-drop-group blocks convert to ordinary cake at bites 1, drop their old block's resources, and drop one cake slice. Apply this replacement only to the eligible group. Pastry slicing loot adds one slice per remaining bite: cake `7-bites`, pie `4-bites`, under the drop modifier's tool/other conditions.
- Knife grass/crop straw drops and pig/hoglin ham additions are governed by exact drop modifiers; do not replace ordinary loot when an additive modifier is specified. Ham replacement modifiers remove only their specified original output and add configured replacement quantities.
- Backstabbing levels I..III: determine source-position direction from target position, normalize the FULL 3D vector, then zero its Y component WITHOUT renormalizing. Dot with the target's full look vector; qualify only when dot < -0.5. Missing position does not qualify. Multiply eligible incoming damage by 1.4/1.6/1.8; play critical-hit sound only if damage changes. Supported items use knife-enchantable group, main hand. Acquisition weight 5, anvil cost 2; minimum enchantment costs 15/24/33, maximum 50/58/66.

**Acceptance**: Given exact dot -0.5, then no backstab bonus; at -0.5001 it qualifies. Given behind-target level III hit, then qualifying damage is multiplied by 1.8. Given pie slicing, then the knife's durability does not decrease through the pie interaction.

## Handheld Skillet

- Stack one; iron material durability/repair/enchantability; main-hand damage modifier = iron bonus +5; attack-speed modifier -3.1; attack-knockback modifier +1. Custom post-hit wear is one, mining a nonzero-hardness block takes one wear. Sweeping Edge is excluded. Strong impact sound for player attack strength >0.8, weak sound otherwise; living nonplayer wielders use strong sound.
- Handheld cooking may start when the player is on fire OR any block in the inclusive 3x3x3 neighborhood around the player's block position belongs to heat sources. Unlike placed cooking this proximity test does NOT check whether the source is lit and does NOT require a conductor.
- Take one open-fire-cookable ingredient from the OTHER hand, store it in the skillet, set its original recipe time, and start held-use cooking. Underwater prevents starting a new ingredient. This path consumes the other-hand ingredient even in creative because no creative copy exemption is implemented.
- Cook using the same Fire Aspect duration formula as placed skillet. The supplied ongoing use action does not recheck proximity or underwater each step; start conditions are not a continuing heat requirement. While stored ingredient exists, reuse resumes the action if the start heat-proximity check passes.
- Releasing before completion returns the stored raw ingredient to player inventory through ordinary return-to-inventory behavior and clears ingredient/time/flip state; no partial progress persists as cooked output. Completion gives one recipe result or drops overflow, then clears transient state. If no matching recipe exists at completion, the stored ingredient is cleared without producing a result; record this loss boundary.
- Show the ingredient in the held skillet, an orange cooking-progress bar, sizzling and flip state when an external flip action sets it. Flip timestamp is completed only after >12 steps; it toggles flipped orientation and clears timestamp. The repository's provided item path does not itself define a full player flip command; do not invent a required input gesture.
- Sneak-place switches to the placed skillet contract while preserving skillet wear/enchantments. Ordinary non-sneaking block placement returns no placement action.

**Acceptance**: Given an unlit but heat-tagged nearby stove, then handheld cooking can start. Given early release, then raw ingredient returns and no cooked output appears. Given creative handheld cooking, then the source ingredient still decreases by one.

## Carried Cooking Pot

- Stack one; store meal identity/count, container identity, and name. Meal-tooltip shows exact contained meal and portions. Portion bar appears only when count>0, blue-purple `(0.4,0.4,1)`; width `min(1 + floor(12 * portions / 64), 13)` out of 13.
- Shapeless food-serving recipe requires exactly one nonempty filled pot and exactly one other nonempty slot containing the stored meal's matching container. Craft one meal portion; return the pot with portion count decreased by one, consuming one container. Extra occupied slots invalidate this recipe. Slot order is immaterial to matching. The supplied remainder traversal stops at the pot, so crafting-remainder-bearing serving containers after that slot are a separately documented edge case.
- Placing restores stored meal/container state. Loose ingredients and output dropped by breaking a pot are not duplicated into its carried item.

**Acceptance**: Given filled pot plus correct bowl in either order, then craft one portion and retain pot with one less. Given a third occupied crafting slot, then serving recipe fails.

## Planting And Placeable Items

- Cabbage seeds place cabbage age 0. Tomato seeds place budding tomatoes age 0; tomato mature forms clone to seeds. Onion item places onion age 0 and is also edible. Rice item is plantable but not edible; source-water restrictions are mandatory, with invalid-placement feedback for top-face attempts on dirt/farmland.
- Colony items place mature age 3 after mature-state survival validation. They do not simply set age 0 like soil-created colonies. Wild rice item places both halves; no normal upper-half inventory item exists.
- Block-food placement uses ordinary placement and a placeability tooltip. Only skillet explicitly requires sneaking. Ordinary pumpkin pie uses its configurable placement restriction (default unrestricted) and remains ordinarily edible when used as food.
- Rope is fuel for 200 steps. On use against existing rope, normally find the bottom of a continuous downward rope run and place one at the first replaceable destination. Sneaking instead uses the clicked direction; if the immediately next space is already rope and direction is not down, fail rather than extend sideways through a rope chain. Reject nonwater/nonempty fluids. Placement does not require structural survival support.
- Static blocks, crates, bags, signs and baskets have their catalog stack/fuel properties and linked block behaviors; ordinary placement consumes one outside creative. Fuel durations are not automatically translated into additional stove fueling requirements.

**Acceptance**: Given colony item on viable floor, then placed age is 3. Given rope used normally on a column, then extend the bottom by one. Given sneak-side-use with rope already beside it, then placement fails.

## Pet Food

- Dog food: player food values are hunger 4/saturation 1.6, bowl remainder, stack 16; eating does not apply the pet effects. Feeding an alive dog-food-user entity requires tame status only when it is a tameable animal; it does NOT explicitly require the feeder to own it. Fully heal and apply Speed I, Strength I and Resistance I for 300 seconds each, play eating sound and five star particles. In survival consume one and attempt to add one bowl; the pet path lacks an explicit overflow-drop fallback. Creative does not consume or return bowl. Untamed eligible pets do not receive this action.
- Horse feed: not edible to players, stack 16. Alive horse-feed-user entities require tame status when equine, with no explicit ownership check. Fully heal, Speed II and Jump Boost I for 300 seconds, horse-eating sound and five stars. Consume one in survival, none in creative; no returned container.

**Acceptance**: Given another player's tame eligible wolf, then feeding is not blocked by an ownership check. Given untamed horse, then feed buffs do not apply. Given player eating dog food, then pet buffs are not granted.

## Rotten Tomato

- Stack 16, not food. Player use plays throw sound, increments use count and consumes one outside creative; projectile launch speed 1.5, spread 1, zero added pitch.
- **Source divergence**: Player use currently launches an ordinary snowball carrying the tomato stack, while projectile/dispenser construction returns the custom rotten-tomato projectile. Do not silently unify their hit rules.
- Custom projectile does zero health damage on entity hit, uses thrown-item contact, plays splat sound, emits twelve tomato-item particles on impact, and disappears. Each particle X/Z speed uniform [-0.1,0.1], Y uniform [0,0.2]. Entity-hit and generic-hit paths both play their hit sound, so a custom entity hit may produce two sound calls. Snowball-specific damage/contact behavior on the player path is a baseline dependency needing confirmation.

**Acceptance**: Given custom projectile hits an ordinary entity, then no positive health damage is applied and tomato particles appear. Given player launch, then select the actual supplied ordinary-projectile path unless an explicit parity clarification changes it.

## Materials

Straw, canvas, tree bark and rice panicle have no custom use action. Straw fuel 100 steps; canvas 400; tree bark 200. Rice panicle is not rice food and has no explicit fuel override. Their recipes, cutting conversions, straw harvest conditions and compost eligibility are normative data. All other ordinary ingredients/foods inherit only the catalog's stated properties; the presence of an unused legacy behavior class does not add effects.

**Acceptance**: Given rice panicle used as food, then it is not consumed. Given tree bark as fuel, then it burns for 200 gameplay steps.

## Recipe And Automation Rules

- Shaped crafting preserves row positions, allows baseline translations/mirroring, requires each symbol's eligible item, consumes one per occupied symbol and returns ordinary remainders. Shapeless crafting matches exactly the occupied ingredient multiset. Output count defaults to one unless stated. An ingredient # group accepts one member of its expanded eligible set. Extra inputs are not ignored.
- Cooking pot accepts one..six occupied ingredient slots, matching recipe count exactly. Explicit serving container overrides output remainder; otherwise use the result's ordinary crafting remainder; otherwise containerless.
- Cutting results: for each declared output of count N, perform N independent uniform-float rolls; each unit survives when draw <= `chance + Fortune level * configured bonus`. Default chance is 1, default bonus 0.1 per Fortune level. Threshold >=1 guarantees all units. This is per-unit binomial output, NOT one all-or-nothing stack roll.
- Custom dough recipe requires exactly one wheat-containing slot and one or MORE water-bucket-group slots, no other items. It yields one wheat dough. Return each water bucket UNCHANGED; water is not used up. Multiple wheat slots are invalid. The source does not explicitly disallow multiple water buckets.
- When dispenser tools are enabled, a dispenser facing a cutting board uses its selected stack as a tool. Successful processing applies the same recipe/roll/wear rule without a player. Facing a board suppresses ordinary dispensing even if board is empty or the tool is invalid; output stays on the board's left. A dispenser not facing a board follows its ordinary behavior.
- Cooking experience, ordinary furnace/open-fire/fast-food recipe durations, recipe unlock conditions, and acquisition drops are listed in their data references. Display categories are informational, never alternative ingredient requirements.

**Acceptance**: Given count-3 cutting output at chance 0.5, then independently controlled draws 0.1,0.8,0.2 yield count 2. Given wheat plus two water buckets, then the supplied dough matcher accepts and returns both unchanged. Given dispenser facing empty board, then the tool is retained, not launched.