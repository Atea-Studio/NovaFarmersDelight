# Specification Quality Checklist: Gameplay Parity

**Purpose**: Separate documentation completeness from unresolved semantic/runtime parity.
**Feature**: [spec.md](../spec.md)

## Content Quality

- [x] Requirements describe player-observable behavior without selecting a target implementation technology.
- [x] Prioritized user stories, independent tests, Given/When/Then scenarios, functional requirements and measurable success criteria are present.
- [x] Every active registered block/item is cataloged and assigned a behavior contract.
- [x] All supplied block appearance predicates, alternatives and legal state domains are represented.
- [x] Exact recipe/drop/membership/acquisition quantities and conditions are extracted into Markdown.
- [x] Default gameplay settings and inactive-source limitations are documented separately.
- [x] A reproducible coverage/drift/local-link check is provided.

## Fidelity Readiness

- [ ] CL-001..CL-010 resolved with source-observed or explicitly authorized gameplay decisions.
- [ ] External inherited gameplay and visual baselines pinned for standalone recreation.
- [ ] Dynamic sign text/attachment and displayed item geometry checked against a runnable reference.
- [ ] Persistence and feature activation verified against runnable reference behavior.
- [ ] Full item/block acceptance tests executed in the recreated experience.
- [ ] All success criteria verified; no inferred engine default or unauthorized bug fix changes gameplay.

## Notes

Documentation coverage checks cannot prove gameplay equivalence. Unchecked readiness items are deliberate open gates. The supplied source has unfinished/conflicting paths; marking this feature complete without resolving them would overstate the evidence.