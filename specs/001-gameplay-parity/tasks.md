# Tasks: Farmer's Delight Gameplay Parity

**Input**: [spec.md](spec.md), [plan.md](plan.md), and all linked contracts
**Status**: Implementation backlog for a target recreation, NOT a claim that gameplay is implemented here
**Format**: `[ID] [P?] [Story] Task with contract reference`. `[P]` marks independent work after dependencies are met. Story markers refer to the three user stories in the feature specification.

## Phase 1: Clarification and Baselines

- [ ] T001 Resolve CL-001..CL-010 in [dependencies.md](dependencies.md) and update acceptance scenarios.
- [ ] T002 Pin external crop, material, sign, food, projectile and drop baselines.
- [ ] T003 Obtain/pin visual references, parent geometry and original texture assets from [visual-assets.md](visual-assets.md).
- [ ] T004 Run the extraction check and freeze reviewed source evidence from [source-map.md](source-map.md).

## Phase 2: Shared Gameplay Foundations

- [ ] T005 Define all identities and item-stack properties in [catalog.md](catalog.md); verify 132 block and 186 item coverage.
- [ ] T006 Expand and validate membership groups and feature gates from [memberships.md](memberships.md).
- [ ] T007 Implement exact shaped, shapeless, special, cutting and cooking recipe matching from [item-mechanics.md](item-mechanics.md#recipe-and-automation-rules) and [recipes.md](recipes.md).
- [ ] T008 Implement ordered drop pools/modifiers, random draws, stored-state preservation and experience from [drops.md](drops.md).
- [ ] T009 Build state-domain/appearance fixtures from [block-states.md](block-states.md), including non-mesh properties and independent overlays.

## Phase 3: User Story 1 - Food Journey (P1)

**Independent acceptance**: Plant, grow, harvest, cut, cook, serve and consume a meal with specified quantities/effects.

- [ ] T010 [P] [US1] Implement cabbage/onion, all tomato forms and both cultivated rice portions from [block-mechanics.md](block-mechanics.md).
- [ ] T011 [P] [US1] Implement wild plants, compost, rich soil/farmland and mushroom colonies.
- [ ] T012 [US1] Implement stove, pot, skillet and cutting board, including cooling and output-capacity behavior.
- [ ] T013 [US1] Implement pies, feasts, gleaming salad and clarified rice-roll-medley serving states.
- [ ] T014 [US1] Implement food/drink values, containers and effects from [catalog.md](catalog.md) and [item-mechanics.md](item-mechanics.md#consumption).
- [ ] T015 [US1] Implement knives, carried-pot serving, handheld skillet cooking, pet feeding and both projectile paths.
- [ ] T016 [US1] Test recipe and consumption boundaries with controlled random draws, interrupted use, full inventory and creative mode.

## Phase 4: User Story 2 - Construction and Display (P1)

**Independent acceptance**: Place each block family, trigger its transitions and inspect exact geometry/orientation/state feedback.

- [ ] T017 [P] [US2] Implement rope placement, ties, bell ringing, climbing and clarified reeling boundaries.
- [ ] T018 [P] [US2] Implement rope fences/gates, safety net, tatami pairing/mats, rug and storage-block rules.
- [ ] T019 [P] [US2] Implement all 68 sign world forms, 34 sign inventory forms, side-specific writing and attachment rules.
- [ ] T020 [US2] Render every atlas predicate/alternative and multipart rule, plus water, displayed contents, writing, lighting and particles.
- [ ] T021 [US2] Validate collision independently of meshes, including trays, hollow baskets, thin blocks and opened gates.
- [ ] T022 [US2] Exercise every legal state-domain combination and every normally reachable transition, including inherited states omitted by minimal models.

## Phase 5: User Story 3 - Storage and Automation (P2)

**Independent acceptance**: Store and collect items, automate a meal, reload blocks, then break them without unauthorized duplication/loss.

- [ ] T023 [P] [US3] Implement cabinet viewer counts, all wood variants, inventories and comparator outputs.
- [ ] T024 [P] [US3] Implement six-direction baskets, powered disabling, partial collection and eight-step cooldown.
- [ ] T025 [US3] Implement pot sided transfer and dispenser cutting; verify unsupported faces/tools cannot bypass contracts.
- [ ] T026 [US3] Persist placed inventories, names, progress, meal/container state, tool wear/enchantments and sign text.
- [ ] T027 [US3] Validate destruction/loot reconciliation, pot/skillet carried-state recovery and no duplicate two-piece mat drops.

## Phase 6: Whole-Feature Gates

- [ ] T028 Reconcile acquisition/milestone activation and all configurable defaults with [acquisition.md](acquisition.md) and [dependencies.md](dependencies.md).
- [ ] T029 Pass all feature and per-contract acceptance scenarios and maintain requirement-to-test traceability.
- [ ] T030 Verify SC-001..SC-005 from [spec.md](spec.md); document accepted differences explicitly.
- [ ] T031 Complete [checklists/requirements.md](checklists/requirements.md) only after all parity blockers are resolved.