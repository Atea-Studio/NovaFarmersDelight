# Implementation Plan: Gameplay Parity

**Feature**: [spec.md](spec.md)
**Status**: Platform-neutral planning input; target design not selected

## Summary

Implement the feature as independently testable gameplay journeys while preserving the complete catalog, state-decision rules, recipes, drops, timing, effects and persistence. Appearance matching is downstream of state selection; inventory and text overlays are not additional random block variants.

## Constitution Check

- Gameplay contracts are authoritative; target defaults cannot silently override them.
- No undocumented balance adjustment or bug correction is allowed. Clarification decisions must explicitly authorize departures from literal supplied-source behavior.
- Shared family mechanics must produce identical behavior for every assigned identifier.
- Use exact quantities and deterministic boundary tests; use statistical tests only for probability distribution checks.
- Keep implementation choices in the target plan, not the technology-neutral specification.
- Complete catalog coverage is a release gate. Passing one user journey is not proof of whole-feature parity.

## Phase 0: Resolve Evidence

Resolve CL-001..CL-010 in [dependencies.md](dependencies.md). Pin external baseline rules/assets. Establish whether the target recreates literal supplied behavior or corrected intended gameplay for each source conflict. Compare representative interactions with a runnable reference where available; retain unresolved entries as blockers, not fabricated facts.

## Phase 1: Define Gameplay Data

Use catalog identities, item-stack attributes, legal state domains, input/output inventories and the key entities from the feature specification. Import recipes, drops and membership groups with exact ordering, counts and feature gates. Treat item transformations, same-item matching and same-item-plus-stored-state matching as distinct predicates where the contracts distinguish them.

## Phase 2: Implement Stories

US1 covers farming, ingredients, cutting, heat processing, serving and consumption. US2 covers placement, state changes, connections, sign editing, multipart visuals and collision. US3 covers storage, collection, dispenser tools, sided pot access and save/load/drop invariants. Each story must have independent acceptance tests before broad integration.

## Phase 3: Validate Parity

- Enumerate the Cartesian product of each block's legal state domains. Assert each has a valid appearance selection; all multipart conditions and alternatives must be represented.
- Test every reachable transition with support/heat/light/water/power boundary conditions. Test legal but unreachable states by fixture injection for display coverage.
- Test each recipe with valid inputs, wrong member, wrong quantity, extra input, wrong tool, output saturation and relevant container behavior.
- Test every item with survival/creative, full inventory, interrupted use, effect eligibility and durability boundaries.
- Reload populated blocks and compare persisted attributes; break them and reconcile total items between loose drops, carried contents and inventories.
- Resolve external assets and compare complete meshes, texture orientation, displayed food counts, sign text, particles and lighting.

## Deliverable Boundary

This plan deliberately omits language, project layout, networking technology, renderer choice and storage format. The target project's `/speckit.plan` fills those choices in after clarifications; none may change the observable contracts.