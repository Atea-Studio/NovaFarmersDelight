# Extraction Coverage

| Coverage dimension | Count |
| --- | --- |
| blocks | 132 |
| items | 186 |
| appearancePredicates | 548 |
| modelAlternatives | 575 |
| recipes | 333 |
| dropTablesAndModifiers | 150 |
| membershipsAndDataMaps | 177 |
| blockModels | 182 |
| acquisitionRecords | 253 |

These counts validate enumeration, not the correctness of all hand-written mechanics or external baseline dependencies. Run `node specs/001-gameplay-parity/tools/extract-gameplay.mjs --check` to detect drift without rewriting files. Run without `--check` to regenerate these reference tables after an intentional source change. The extractor itself is documentation tooling, not part of the gameplay specification.
