# Feature Specification: Farmer's Delight Gameplay Parity

**Feature Branch**: `001-gameplay-parity` (logical feature name; no branch required)
**Created**: 2026-11-06
**Status**: Draft, source-derived
**Input**: Recreate every block and item mechanic, including visible states and their selection rules, without coupling gameplay to an implementation platform.

## User Scenarios & Testing

### User Story 1 - Prepare and Serve Food (Priority: P1)

A player grows or obtains ingredients, cuts them, cooks them, and consumes or serves the resulting food.

**Why this priority**: This is the central gameplay loop.
**Independent Test**: Prepare a meal using only the declared ingredients, tools, heat, and serving containers; compare consumption and leftovers to the specified results.

**Acceptance Scenarios**:

1. **Given** a cooking pot placed above a tray-eligible heat source, **When** placement completes, **Then** its support appearance is `tray` unless it was placed against an underside.
2. **Given** a pot placed against an underside, **When** placement completes, **Then** its support appearance is `handle` regardless of the block beneath it.
3. **Given** a pot with an empty-hand sneaking player, **When** the player uses it, **Then** non-handle support becomes `handle`, and handle support becomes `tray` if eligible below or `none` otherwise.
4. **Given** any catalog food, **When** consumption completes, **Then** nutrition, saturation, effects, and returned containers match its item contract.

### User Story 2 - Build and Inspect Every State (Priority: P1)

A player places, rotates, connects, wets, opens, lights, harvests, and consumes blocks and sees the corresponding appearance.

**Why this priority**: Incorrect state selection changes both construction and interaction feedback.
**Independent Test**: Exercise each state domain and transition in the state atlas and behavior contracts.

**Acceptance Scenarios**:

1. **Given** any listed block, **When** a state changes, **Then** select the corresponding appearance using the state atlas predicates; combine all matching parts for multipart appearances.
2. **Given** a waterlogged block, **When** no water-specific model predicate exists, **Then** retain its solid appearance while displaying contained water independently.

### User Story 3 - Preserve Resources and Automation (Priority: P2)

A player stores items, automates cooking and collection, and moves or breaks blocks without unexpected losses or duplication.

**Why this priority**: Persistence and automation are part of gameplay parity.
**Independent Test**: Repeat each inventory operation manually and through each permitted side, then unload, reload, and break the block.

**Acceptance Scenarios**:

1. **Given** a populated block, **When** it is moved or broken, **Then** preserve or drop contents exactly as its contract requires, never both.
2. **Given** a failed recipe or unavailable output capacity, **When** processing is attempted, **Then** apply the specified progress behavior without consuming ingredients prematurely.

### Edge Cases

- Creative-mode item consumption, full inventories, wrong tools, invalid support, loss of heat, missing recipe, mixed ingredients, full outputs, and water must each follow the relevant contract.
- Structural destruction and player interaction are distinct transitions; do not infer drops from the visible model alone.
- Random outcomes must preserve their probabilities, conditions, and independent rolls, not merely their average yield.

## Requirements

### Functional Requirements

- **FR-001**: The experience MUST include every block and item in the catalog, including unobtainable-in-inventory block forms, but excluding commented-out registrations.
- **FR-002**: Every catalog entry MUST implement its assigned behavior contract and all applicable shared rules.
- **FR-003**: Block appearance MUST match the state atlas for every listed predicate, orientation, alternative, and multipart component.
- **FR-004**: State transitions MUST follow their documented decision rules, not random appearance selection or guesses based on names.
- **FR-005**: Recipes, harvests, containers, effects, tool wear, storage, automation, heat, and timing MUST match the gameplay data and behavior contracts.
- **FR-006**: Save/load MUST preserve all player-observable state, inventories, names, meal contents, progress where persisted, and written sign text.
- **FR-007**: Implementations MUST preserve survival and creative-mode differences explicitly documented by each interaction.
- **FR-008**: Shared base-game behavior MUST be treated as a gameplay dependency, not silently replaced by target-platform defaults.
- **FR-009**: Optional integrations MUST NOT become mandatory ingredients or dependencies in the core experience.
- **FR-010**: A parity claim MUST resolve all explicitly identified external behavior dependencies and pass every acceptance scenario and catalog/state coverage check.

### Key Entities

- **Block instance**: Identifier, location, state values, appearance, collision, inventory and persistent contents where applicable.
- **Item stack**: Identifier, count, wear, name, effects, stored food or contents where applicable.
- **Recipe**: Ingredient predicates, counts, tool or processing station, time, output rolls, containers and experience.
- **Player**: Inventory, hunger, saturation, health, effects, creative status, position, facing and interaction intent.
- **Environment**: Support faces, neighbors, water, rain, light, heat, growth conditions, power and configurable gameplay rules.

## Success Criteria

### Measurable Outcomes

- **SC-001**: 100% of active registered blocks and items have catalog entries and assigned contracts.
- **SC-002**: 100% of supplied block appearance predicates and model alternatives are represented in the state atlas.
- **SC-003**: All acceptance scenarios pass without item loss, duplication, or differing visible states.
- **SC-004**: Exact quantities, durations, probabilities and effects agree with gameplay data; random distributions are checked with deterministic controlled draws and boundary cases.
- **SC-005**: No implementation platform, loader, language, or game release is prescribed by these requirements.

## Assumptions

- One gameplay step is 1/20 second. Times expressed in steps describe observable simulation timing, not an engine API.
- Distances use one full block as one unit; model-coordinate bounds use sixteenths of a block.
- Identifiers are stable gameplay vocabulary, not implementation requirements.
- This is a specification of the supplied source snapshot, not a promise that an unfinished port already runs correctly.
- Source-inherited behavior not defined in this repository is an explicit external dependency. It must be resolved before asserting complete parity.