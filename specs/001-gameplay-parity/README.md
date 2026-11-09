# Farmer's Delight Gameplay Specification

Start with [spec.md](spec.md). This feature uses Spec Kit's mandatory specification sections: prioritized user scenarios and acceptance tests, functional requirements, key entities, measurable success criteria, and assumptions. All gameplay requirements are independent of a target platform.

## Reading Order

1. [spec.md](spec.md): feature scope, requirements and completion gates.
2. [catalog.md](catalog.md): every active block/item identifier, food values, limits, fuel and assigned behavior.
3. [block-mechanics.md](block-mechanics.md): state selection, transitions, placement, growth, serving, collision, storage, automation and displayed contents.
4. [item-mechanics.md](item-mechanics.md): consuming, planting, throwing, feeding, tools, carried stations, effects and recipe algorithms.
5. [block-states.md](block-states.md): every block's legal property domains, appearance predicates, model alternatives and multipart decisions.
6. [recipes.md](recipes.md), [drops.md](drops.md), [memberships.md](memberships.md), [acquisition.md](acquisition.md): exact quantities, ingredients, conditions, probabilities, eligibility and acquisition data.
7. [visual-assets.md](visual-assets.md): geometry, texture bindings and inherited visual references.
8. [dependencies.md](dependencies.md): configurable defaults, external gameplay baselines and source conflicts requiring explicit decisions.

## Spec Kit Workflow

Use this entire directory as the feature input, not only the top-level specification. In a Spec Kit-initialized target repository, retain the `specs/001-gameplay-parity/` feature layout. No new branch has been created here.

1. Review [checklists/requirements.md](checklists/requirements.md) and resolve the clarification register using `/speckit.clarify`. Answers MUST be reflected in the affected contracts and acceptance scenarios.
2. Use `/speckit.plan` with [plan.md](plan.md) as the platform-neutral baseline; only the target project's plan may select implementation technologies.
3. Use `/speckit.tasks` to refine [tasks.md](tasks.md) into implementation tasks for that target, maintaining story IDs and contract references.
4. Use `/speckit.analyze` to check requirement/task consistency, then `/speckit.implement` to execute the target implementation tasks.

These documents do not install Spec Kit, prescribe an engine, or certify that the supplied unfinished source already runs. The draft status and open clarifications are deliberate. Source-inherited behavior and original image assets must be supplied/pinned for standalone pixel and gameplay parity.

## Validation

From the repository root:

```text
node specs/001-gameplay-parity/tools/extract-gameplay.mjs --check
```

The check verifies exhaustive extraction, duplicate IDs, required specification headings, each block's state coverage, assigned block/item contract links, all local Markdown links, and source-evidence drift. It does NOT execute gameplay or prove hand-written semantic equivalence.

Run without `--check` only after reviewing an intentional source change and updating the affected hand-written mechanics. [coverage.md](coverage.md) records counts; [source-map.md](source-map.md) records evidence hashes. The script is documentation maintenance tooling, not a requirement for the recreated game.

## Fidelity Boundary

The repository yields 132 blocks and 186 items, with shared contracts avoiding duplicated rules across colors and wood types. The atlas covers 548 appearance predicates and 575 weighted/model alternatives; legal combination counts include properties not distinguished by those predicates. Keeping one predicate row for several equivalent states is an exact compressed representation, not an omission.

All provided recipes/drop/membership/acquisition records are reproduced, with main resources taking precedence over generated resources. A data record's existence does not prove its feature is active; preserve its feature gates and the activation limitations in the dependency contract. Commented-out registrations and inactive legacy item behavior are excluded from normative mechanics.