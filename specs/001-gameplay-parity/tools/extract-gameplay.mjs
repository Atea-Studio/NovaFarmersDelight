import fs from 'node:fs';
import path from 'node:path';
import assert from 'node:assert/strict';
import { fileURLToPath } from 'node:url';
import { createHash } from 'node:crypto';

const feature = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const root = path.resolve(feature, '../..');
const java = path.join(root, 'src/main/java/vectorwing/farmersdelight/common');
const read = filename => fs.readFileSync(filename, 'utf8');
const clean = source => source.replace(/\/\*[\s\S]*?\*\//g, '').replace(/^\s*\/\/.*$/gm, '');
const cell = value => String(value ?? '').replace(/\|/g, '&#124;').replace(/\r?\n/g, ' ');
const code = value => '`' + cell(value) + '`';
const title = value => value.replace(/^farmersdelight:/, '').replace(/_/g, ' ');
const files = directory => fs.existsSync(directory) ? fs.readdirSync(directory, { withFileTypes: true }).flatMap(entry => entry.isDirectory() ? files(path.join(directory, entry.name)) : [path.join(directory, entry.name)]).sort() : [];
const json = filename => JSON.parse(read(filename));
const table = (headers, rows) => ['| ' + headers.join(' | ') + ' |', '| ' + headers.map(() => '---').join(' | ') + ' |', ...rows.map(row => '| ' + row.map(cell).join(' | ') + ' |')].join('\n');
const registry = (filename, category) => {
  const source = clean(read(path.join(java, 'registry', filename)));
  const pattern = category === 'blocks' ? /public static final (?:Supplier<Block>|DeferredHolder<Block, \w+>)\s+(\w+)\s*=\s*BLOCKS\.register\("([^"]+)"/g : /public static final Supplier<Item>\s+(\w+)\s*=\s*(register\w+)\("([^"]+)"/g;
  const matches = [...source.matchAll(pattern)];
  return matches.map((match, index) => ({ symbol: match[1], id: category === 'blocks' ? match[2] : match[3], helper: category === 'blocks' ? '' : match[2], declaration: source.slice(match.index, matches[index + 1]?.index ?? source.length) }));
};
const blocks = registry('ModBlocks.java', 'blocks');
const items = registry('ModItems.java', 'items');
const blockBySymbol = new Map(blocks.map(block => [block.symbol, block]));
assert.equal(new Set(blocks.map(block => block.id)).size, blocks.length, 'Duplicate block identifier');
assert.equal(new Set(items.map(item => item.id)).size, items.length, 'Duplicate item identifier');
const foodSource = clean(read(path.join(java, 'FoodValues.java')));
const food = new Map([...foodSource.matchAll(/public static final FoodProperties (\w+)\s*=([\s\S]*?);/g)].map(match => {
  const direct = match[2].match(/new FoodProperties\((\d+),\s*(\d+),\s*(true|false)\)/);
  const nutrition = direct ? Number(direct[1]) : Number(match[2].match(/\.nutrition\((\d+)\)/)?.[1] ?? 0);
  const modifier = Number(match[2].match(/\.saturationModifier\(([\d.]+)[fF]?\)/)?.[1] ?? 0);
  return [match[1], { nutrition, saturation: direct ? Number(direct[2]) : Number((nutrition * modifier * 2).toFixed(4)), always: direct ? direct[3] === 'true' : match[2].includes('alwaysEdible') }];
}));
const effects = {
  DEFAULT: 'Ordinary eating; no added effect', FAST_FOOD: 'Eat in 0.8 s; no added effect',
  HUNGER_INDUCING_FOOD: '30% hunger I for 30 s', CHICKEN_CUTS: 'Eat in 0.8 s; 30% hunger I for 30 s',
  NOURISHMENT_BRIEF_DURATION: 'Nourishment I for 30 s', NOURISHMENT_SHORT_DURATION: 'Nourishment I for 60 s',
  NOURISHMENT_MEDIUM_DURATION: 'Nourishment I for 180 s', NOURISHMENT_LONG_DURATION: 'Nourishment I for 300 s',
  BONE_BROTH: 'Drink; Nourishment I for 60 s', HOT_COCOA: 'Drink; remove one random eligible harmful effect',
  MILK_BOTTLE: 'Drink; remove one random eligible effect', MELON_JUICE: 'Drink; heal 2 health points',
  MELON_POPSICLE: 'Eat in 0.8 s; extinguish consumer', APPLE_CIDER: 'Drink; absorption I for 60 s',
  CAKE_SLICE: 'Speed I for 20 s', PIE_SLICE: 'Speed I for 30 s', GLOW_BERRY_CUSTARD: 'Drink; glowing I for 5 s',
  FRUIT_SALAD: 'Regeneration I for 5 s', MIXED_SALAD: 'Regeneration I for 5 s',
  NETHER_SALAD: '30% nausea I for 12 s', KELP_ROLL: 'Eat in 2.4 s; no added effect'
};
const contract = block => {
  const family = block.declaration.match(/new (\w+)\(/)?.[1];
  const families = {
    StoveBlock: 'stove', CookingPotBlock: 'cooking-pot', SkilletBlock: 'skillet', BasketBlock: 'basket', CuttingBoardBlock: 'cutting-board',
    CabinetBlock: 'cabinet', PieBlock: 'pies', FeastBlock: 'feasts', RotatedFeastBlock: 'feasts', GleamingSaladBlock: 'gleaming-salad', RiceRollMedleyBlock: 'rice-roll-medley',
    CabbageBlock: 'cabbage-and-onion', OnionBlock: 'cabbage-and-onion', BuddingTomatoBlock: 'tomatoes', TomatoBlock: 'tomatoes', HangingTomatoBlock: 'tomatoes',
    RiceBlock: 'rice', RicePaniclesBlock: 'rice', WildRiceBlock: 'wild-plants', WildCropBlock: 'wild-plants', SandyShrubBlock: 'wild-plants',
    MushroomColonyBlock: 'mushroom-colonies', OrganicCompostBlock: 'organic-compost', RichSoilBlock: 'rich-soil', RichSoilFarmlandBlock: 'rich-soil',
    RopeBlock: 'rope', SafetyNetBlock: 'safety-net', RopeFenceBlock: 'rope-fences', RopeFenceGateBlock: 'rope-fences',
    CanvasRugBlock: 'canvas-rug', TatamiBlock: 'tatami', TatamiMatBlock: 'tatami', TatamiHalfMatBlock: 'tatami',
    StandingCanvasSignBlock: 'canvas-signs', WallCanvasSignBlock: 'canvas-signs', CeilingHangingCanvasSignBlock: 'canvas-signs', WallHangingCanvasSignBlock: 'canvas-signs',
    RiceBaleBlock: 'storage-blocks', StrawBaleBlock: 'storage-blocks', Block: 'storage-blocks'
  };
  assert(families[family], `Unassigned block family: ${block.id} (${family})`);
  return families[family];
};
const dataResources = new Map();
const assetResources = new Map();
for (const sourceRoot of ['src/generated/resources', 'src/main/resources']) {
  for (const filename of files(path.join(root, sourceRoot))) {
    if (!filename.endsWith('.json')) continue;
    const relative = path.relative(path.join(root, sourceRoot), filename).replaceAll('\\', '/');
    if (relative.startsWith('data/')) dataResources.set(relative, json(filename));
    if (relative.startsWith('assets/')) assetResources.set(relative, json(filename));
  }
}
const english = assetResources.get('assets/farmersdelight/lang/en_us.json') ?? {};
const materialBehavior = block => {
  const declaration = block.declaration;
  const baseline = declaration.match(/ofFullCopy\(Blocks\.(\w+)\)/)?.[1]?.toLowerCase();
  const ownBaseline = declaration.match(/ofFullCopy\(ModBlocks\.(\w+)\.get\(\)\)/)?.[1];
  const strength = declaration.match(/\.strength\(([\d.]+)[fFdD]?(?:,\s*([\d.]+)[fFdD]?)?\)/);
  const sound = declaration.match(/\.sound\(SoundType\.(\w+)\)/)?.[1]?.toLowerCase();
  const piston = declaration.match(/\.pushReaction\(PushReaction\.(\w+)\)/)?.[1]?.toLowerCase();
  const traits = [];
  if (baseline) traits.push(`${title(baseline)} baseline material`);
  if (ownBaseline) traits.push(`${title(blockBySymbol.get(ownBaseline)?.id ?? ownBaseline.toLowerCase())} baseline material`);
  if (strength) traits.push(`break strength ${strength[1]}; blast resistance ${strength[2] ?? strength[1]}`);
  if (sound) traits.push(`${title(sound)} sound family`);
  if (piston) traits.push(`piston response: ${piston}`);
  if (declaration.includes('.noCollision()')) traits.push('no entity collision');
  if (declaration.includes('.instabreak()')) traits.push('instant break');
  if (declaration.includes('.noOcclusion()')) traits.push('non-occluding material');
  if (block.id === 'stove') traits.push('light 13 when lit, otherwise 0');
  if (block.id === 'gleaming_salad_block') traits.push('light = 3 * servings');
  return traits.join('; ') || 'Custom/default material; see behavior and baseline contract';
};
const stateEntries = blocks.map(block => [block.id, assetResources.get(`assets/farmersdelight/blockstates/${block.id}.json`)]);
for (const [id, state] of stateEntries) assert(state, `Missing appearance definition: ${id}`);
const outputs = new Map();
const store = (name, body) => outputs.set(name, body.trimEnd() + '\n');
store('catalog.md', '# Block and Item Catalog\n\nEvery row is normative together with its linked behavior contract. All identifiers below belong to Farmer\'s Delight unless a different namespace is given. Default item stack limit is 64 unless overridden below. Fuel durations are gameplay steps. Foods show saturation points, not a multiplier. Ordinary food/drink duration is an external baseline dependency unless explicitly overridden. Material baselines refer to properties only, not an inherited interaction class; explicit overrides below take precedence.\n\n## Blocks\n\n' + table(['Identifier', 'Display name', 'Behavior contract', 'Inventory form', 'Material behavior and overrides'], blocks.map(block => [code(block.id), english[`block.farmersdelight.${block.id}`] ?? title(block.id), `[${contract(block)}](block-mechanics.md#${contract(block)})`, items.filter(item => item.declaration.includes(`ModBlocks.${block.symbol}.get()`)).map(item => code(item.id)).join(', ') || 'World form only; see harvesting/placement contract', materialBehavior(block)])) + '\n\n## Items\n\n' + table(['Identifier', 'Display name', 'Role / placement', 'Stack limit', 'Hunger / saturation', 'Consumption and effect', 'Remainder', 'Fuel steps'], items.map(item => {
  const blockSymbol = item.declaration.match(/ModBlocks\.(\w+)\.get\(\)/)?.[1];
  const block = blockBySymbol.get(blockSymbol);
  const foodKey = item.declaration.match(/FoodValues\.(?!ConsumableValues)(\w+)/)?.[1];
  const values = food.get(foodKey);
  if (foodKey) assert(values, `Missing food value: ${item.id}/${foodKey}`);
  const effectKey = item.declaration.match(/FoodValues\.ConsumableValues\.(\w+)/)?.[1] ?? 'DEFAULT';
  assert(effects[effectKey], `Missing consumable contract: ${effectKey}`);
  const fuel = item.id === 'rope' ? '200' : item.helper.includes('Fuel') ? item.declaration.match(/,\s*(\d+)\s*\);/)?.[1] : undefined;
  if (item.helper.includes('Fuel')) assert(fuel, `Missing fuel value: ${item.id}`);
  const remainder = item.declaration.match(/\.craftRemainder\(Items\.(\w+)\)/)?.[1]?.toLowerCase() ?? (item.declaration.includes('bowlFoodItem(') ? 'bowl' : item.declaration.includes('drinkItem(') ? 'glass_bottle' : 'none');
  const stack = item.declaration.match(/\.stacksTo\((\d+)\)/)?.[1] ?? (item.id.includes('knife') ? '1' : item.declaration.includes('bowlFoodItem(') || item.declaration.includes('drinkItem(') ? '16' : '64');
  const role = block ? `Places ${code(block.id)}; [${contract(block)}](block-mechanics.md#${contract(block)})` : `[${item.id.includes('knife') ? 'knives' : item.id === 'horse_feed' || item.id === 'dog_food' ? 'pet-food' : item.id === 'rotten_tomato' ? 'rotten-tomato' : values || item.declaration.includes('drinkItem') ? 'consumption' : 'materials'}](item-mechanics.md#${item.id.includes('knife') ? 'knives' : item.id === 'horse_feed' || item.id === 'dog_food' ? 'pet-food' : item.id === 'rotten_tomato' ? 'rotten-tomato' : values || item.declaration.includes('drinkItem') ? 'consumption' : 'materials'})`;
  const placementRole = item.id.includes('canvas_sign') && !block ? '[canvas-signs](block-mechanics.md#canvas-signs)' : role;
  return [code(item.id), english[`item.farmersdelight.${item.id}`] ?? english[`block.farmersdelight.${item.id}`] ?? title(item.id), placementRole + (item.helper === 'registerHidden' ? '; hidden diagnostic item, not normal progression' : ''), stack, values ? `${values.nutrition} / ${values.saturation}${values.always ? '; usable at full hunger' : ''}` : 'Not food', values || item.declaration.includes('drinkItem') ? effects[effectKey] : 'Not consumed as food', remainder, fuel ?? 'No explicit fuel override'];
})));

let predicates = 0;
let alternatives = 0;
const predicateText = value => {
  if (!value) return 'Always';
  if (value.OR) return '(' + value.OR.map(predicateText).join(' OR ') + ')';
  if (value.AND) return '(' + value.AND.map(predicateText).join(' AND ') + ')';
  return Object.entries(value).map(([key, entry]) => `${key} = ${String(entry).split('|').join(' OR ')}`).join(' AND ');
};
const domains = block => {
  const horizontal = ['north', 'east', 'south', 'west'];
  const directions = [...horizontal, 'up', 'down'];
  const boolean = ['false', 'true'];
  const ages = maximum => Array.from({ length: maximum + 1 }, (_, index) => String(index));
  const family = contract(block);
  const choices = {
    stove: { facing: horizontal, lit: boolean }, 'cooking-pot': { facing: horizontal, support: ['none', 'tray', 'handle'], waterlogged: boolean },
    skillet: { facing: horizontal, support: boolean, waterlogged: boolean }, basket: { facing: directions, enabled: boolean, waterlogged: boolean },
    'cutting-board': { facing: horizontal, waterlogged: boolean }, cabinet: { facing: horizontal, open: boolean },
    pies: { facing: horizontal, bites: ages(3) }, feasts: { facing: horizontal, servings: ages(4) }, 'gleaming-salad': { facing: horizontal, servings: ages(4) },
    'rice-roll-medley': { facing: horizontal, servings: ages(8) }, 'cabbage-and-onion': { age: ages(7) },
    'mushroom-colonies': { age: ages(3) }, 'organic-compost': { composting: ages(7) },
    'safety-net': { waterlogged: boolean }, 'canvas-rug': {},
    rope: { north: boolean, east: boolean, south: boolean, west: boolean, tied_to_bell: boolean, waterlogged: boolean }
  };
  if (family === 'tomatoes') return block.id === 'budding_tomatoes' ? { age: ages(4) } : block.id === 'tomatoes' ? { age: ages(3), ropelogged: boolean } : { age: ages(3) };
  if (family === 'rice') return block.id === 'rice' ? { age: ages(3), supporting: boolean } : { age: ages(3) };
  if (family === 'rich-soil') return block.id.endsWith('farmland') ? { moisture: ages(7) } : {};
  if (family === 'wild-plants') return block.id === 'wild_rice' ? { half: ['lower', 'upper'], waterlogged: boolean } : {};
  if (family === 'storage-blocks') return block.id === 'rice_bale' ? { facing: directions } : block.id === 'straw_bale' ? { axis: ['x', 'y', 'z'] } : {};
  if (family === 'tatami') return block.id === 'tatami' ? { facing: directions, paired: boolean } : block.id === 'full_tatami_mat' ? { facing: horizontal, part: ['foot', 'head'] } : { facing: horizontal };
  if (family === 'rope-fences') return block.id.endsWith('gate') ? { facing: horizontal, open: boolean, powered: boolean, in_wall: boolean } : { north: boolean, east: boolean, south: boolean, west: boolean, waterlogged: boolean };
  if (family === 'canvas-signs') return block.id.includes('wall') ? { facing: horizontal, waterlogged: boolean } : block.id.includes('hanging') ? { rotation: ages(15), attached: boolean, waterlogged: boolean } : { rotation: ages(15), waterlogged: boolean };
  assert(choices[family], `Missing state domain: ${block.id}`);
  return choices[family];
};
const appearance = entry => `${title(entry.model.replace(/^farmersdelight:block\//, ''))} (${code(entry.model)}); pitch ${entry.x ?? 0} degrees; yaw ${entry.y ?? 0} degrees; ${entry.uvlock ? 'keep texture aligned to world' : 'rotate texture with shape'}; weight ${entry.weight ?? 1}`;
store('block-states.md', '# Exhaustive Block Appearance Atlas\n\nUse the transition rules in [block-mechanics.md](block-mechanics.md) to determine state values FIRST. Then use this atlas to render them. A selector with omitted properties ignores those properties. Variant selectors select one appearance; an array selects one weighted alternative, not several overlapping shapes. Multipart rules add EVERY matching part. `OR` and `AND` have their ordinary logical meaning; alternatives within a property are OR. Rotation is relative to the unrotated appearance. Shape dimensions and texture bindings are listed in [visual-assets.md](visual-assets.md). Water, displayed inventory items, sign writing, particles and light are independent overlays. State domains stated in the behavior contracts include values not distinguished by a model selector.\n\n' + stateEntries.map(([id, definition]) => {
  const rows = [];
  for (const [selector, entry] of Object.entries(definition.variants ?? {})) {
    const choices = Array.isArray(entry) ? entry : [entry];
    predicates++;
    alternatives += choices.length;
    rows.push([selector ? code(selector) : 'Any state', choices.map(appearance).join('; ALTERNATIVE: ')]);
  }
  for (const part of definition.multipart ?? []) {
    const choices = Array.isArray(part.apply) ? part.apply : [part.apply];
    predicates++;
    alternatives += choices.length;
    rows.push([predicateText(part.when), choices.map(appearance).join('; ALTERNATIVE: ')]);
  }
  const block = blocks.find(entry => entry.id === id);
  const stateDomains = domains(block);
  const combinationCount = Object.values(stateDomains).reduce((total, values) => total * values.length, 1);
  return `## ${id}\n\n[Decision rules](block-mechanics.md#${contract(block)})\n\nLegal state domain: ${Object.entries(stateDomains).map(([name, values]) => `${code(name)}: ${values.map(code).join(', ')}`).join('; ') || 'No state properties'}.\n\n${combinationCount} legal combinations (Cartesian product of the domains; not every combination is reachable by normal play). Every combination selects the appearance below using only the properties named in its predicate. Default/placement/reachable transitions follow the linked decision contract.\n\n${table(['State condition', 'Displayed appearance'], rows)}\n`;
}).join('\n'));

const vocabulary = {
  'neoforge:conditions': 'Feature eligibility gates', 'fabric:load_conditions': 'Feature eligibility gates', vanilla_crates_enabled: 'Ordinary crop-crate crafting enabled',
  type: 'Behavior kind', ingredients: 'Ingredients', ingredient: 'Ingredient', result: 'Result', item: 'Item', id: 'Identifier', count: 'Quantity',
  cookingtime: 'Processing steps', experience: 'Experience per operation', container: 'Serving container', tool: 'Required tool', tag: 'Membership group',
  conditions: 'Required conditions', condition: 'Condition kind', functions: 'Outcome modifiers', function: 'Modifier kind', pools: 'Independent drop pools',
  entries: 'Candidate outcomes', rolls: 'Rolls', bonus_rolls: 'Extra rolls', chance: 'Probability', probability: 'Probability', weight: 'Relative weight',
  min: 'Minimum', max: 'Maximum', pattern: 'Crafting rows', key: 'Symbol ingredients', values: 'Members', replace: 'Replace inherited members',
  block: 'Block', properties: 'State conditions', name: 'Outcome identifier', enchantment: 'Enchantment', formula: 'Formula', parameters: 'Parameters',
  uniform: 'Uniform draw', binomial: 'Binomial draw', n: 'Trials', p: 'Success probability',
  category: 'Display category', group: 'Display group', sound: 'Interaction sound', amount: 'Amount',
  predicate: 'Eligibility predicate', predicates: 'Eligibility predicates', alternatives: 'Alternative outcomes', children: 'Outcomes',
  inverted: 'NOT', match_tool: 'Tool eligibility', block_state_property: 'Block-state eligibility', survives_explosion: 'Survives explosion',
  random_chance: 'Random chance', random_chance_with_enchanted_bonus: 'Chance with enchanted bonus', apply_bonus: 'Enchantment bonus',
  set_count: 'Set quantity', explosion_decay: 'Explosion loss', loot_table: 'Referenced drop table',
  copy_components: 'Preserve stored item state', copy_name: 'Preserve name', set_components: 'Set stored item state',
  smelting: 'Furnace cooking', smoking: 'Fast food cooking', campfire_cooking: 'Open-fire cooking',
  crafting_shaped: 'Shaped crafting', crafting_shapeless: 'Shapeless crafting', cooking: 'Cooking pot', cutting: 'Cutting board',
  compost_chance: 'Compost probability', chance_of_rotten_tomato: 'Rotten tomato probability', harvest_chance: 'Harvest probability'
};
const label = key => vocabulary[key] ?? title(key);
const neutralValue = value => {
  if (typeof value === 'string' && /^[\w]+:[\w]+$/.test(value)) {
    const kind = value.split(':')[1];
    if (vocabulary[kind]) return vocabulary[kind];
  }
  return typeof value === 'string' ? code(value) : String(value);
};
const flatten = (value, prefix = '') => Array.isArray(value) ? value.length ? value.flatMap((entry, index) => flatten(entry, `${prefix} / ${index + 1}`)) : [[prefix, 'Empty list']] : value !== null && typeof value === 'object' ? Object.keys(value).length ? Object.entries(value).flatMap(([key, entry]) => flatten(entry, prefix ? `${prefix} / ${label(key)}` : label(key))) : [[prefix, 'No additional conditions']] : [[prefix, neutralValue(value)]];
const sections = (heading, entries) => `# ${heading}\n\nThese exact gameplay records are normative. Numbered children retain source ordering. Membership groups beginning with # mean any eligible member, not a literal item. Recipe kind is a gameplay activity. Probability defaults and base-game referenced groups must follow the shared dependency contract. Main-resource records override generated records at the same identifier. Optional integration records are labeled and are not core requirements.\n\n` + entries.map(([resource, value]) => {
  const id = resource.replace(/^data\//, '').replace(/\.json$/, '');
  const optional = !resource.startsWith('data/farmersdelight/') && !resource.startsWith('data/minecraft/') && !resource.startsWith('data/c/') && !resource.startsWith('data/neoforge/');
  return `## ${id}\n\n${optional ? 'Optional integration.\n\n' : ''}${table(['Gameplay field', 'Exact value'], flatten(value))}\n`;
}).join('\n');
const recipes = [...dataResources].filter(([resource]) => /\/recipe\//.test(resource));
const loot = [...dataResources].filter(([resource]) => /\/loot_table\/|\/loot_modifiers\//.test(resource));
const memberships = [...dataResources].filter(([resource]) => /\/tags\/|\/data_maps\//.test(resource));
store('recipes.md', sections('Recipe Contracts', recipes));
store('drops.md', sections('Harvest and Drop Contracts', loot));
store('memberships.md', sections('Gameplay Memberships and Eligibility', memberships));
const acquisition = [...dataResources].filter(([resource]) => /\/worldgen\/|\/biome_modifier\/|\/enchantment\/|\/advancement\/|\/damage_type\//.test(resource));
store('acquisition.md', sections('Acquisition, Milestones, and Enchantment Data', acquisition));
const models = [...assetResources].filter(([resource]) => /^assets\/farmersdelight\/models\/block\//.test(resource));
store('visual-assets.md', '# Block Geometry and Visual Bindings\n\nModel names below are appearance identifiers, not a required rendering format. Coordinates are in sixteenths of a block. Each element is an axis-aligned box before its listed rotation; each face binds to the stated texture and texture coordinates. A parent inherits geometry, bindings and display transforms; child bindings override parent bindings. A # texture is a binding reference. External parent geometry and textures are explicit baseline dependencies, not target-engine defaults. Original texture images under the shared assets tree are the pixel-accurate visual reference; Markdown geometry alone cannot reproduce their pixels. No new image assets are generated by this specification.\n\n' + models.map(([resource, model]) => `## ${resource.replace(/^assets\/farmersdelight\/models\//, '').replace(/\.json$/, '')}\n\n${table(['Visual field', 'Exact value'], flatten(model))}\n`).join('\n'));
const evidence = [...files(java), ...files(path.join(root, 'src/main/java/vectorwing/farmersdelight/client/renderer'))].filter(filename => filename.endsWith('.java'));
store('source-map.md', '# Source Traceability\n\nEvidence only: these source paths and hashes are not implementation requirements. Hashes make changes to behavior sources visible to the documentation drift check. This inventory includes inactive helper classes; only actively registered/reached behavior is normative. Reconcile hand-written contracts after any relevant source change before regenerating hashes.\n\n' + table(['Evidence source', 'SHA-256'], evidence.map(filename => {
  const relative = path.relative(root, filename).replaceAll('\\', '/');
  return [`[${relative}](../../${relative})`, code(createHash('sha256').update(read(filename)).digest('hex'))];
})));
const summary = { blocks: blocks.length, items: items.length, appearancePredicates: predicates, modelAlternatives: alternatives, recipes: recipes.length, dropTablesAndModifiers: loot.length, membershipsAndDataMaps: memberships.length, blockModels: models.length, acquisitionRecords: acquisition.length };
store('coverage.md', '# Extraction Coverage\n\n' + table(['Coverage dimension', 'Count'], Object.entries(summary)) + '\n\nThese counts validate enumeration, not the correctness of all hand-written mechanics or external baseline dependencies. Run `node specs/001-gameplay-parity/tools/extract-gameplay.mjs --check` to detect drift without rewriting files. Run without `--check` to regenerate these reference tables after an intentional source change. The extractor itself is documentation tooling, not part of the gameplay specification.\n');
const checking = process.argv.includes('--check');
for (const [name, body] of outputs) {
  const filename = path.join(feature, name);
  if (checking) assert.equal(read(filename), body, `${name} is stale or incomplete`);
  else fs.writeFileSync(filename, body);
}
const spec = read(path.join(feature, 'spec.md'));
for (const section of ['## User Scenarios & Testing', '## Requirements', '## Success Criteria']) assert(spec.includes(section), `Missing Spec Kit section: ${section}`);
if (checking) {
  for (const filename of files(feature).filter(entry => entry.endsWith('.md'))) {
    const body = read(filename);
    for (const match of body.matchAll(/\[[^\]\n]+\]\(([^)]+)\)/g)) {
      if (/^https?:/.test(match[1])) continue;
      const [target, anchor] = match[1].split('#');
      const destination = path.resolve(path.dirname(filename), decodeURIComponent(target || path.basename(filename)));
      assert(fs.existsSync(destination), `Broken document link: ${filename} -> ${match[1]}`);
      if (anchor && destination.endsWith('.md')) {
        const headings = [...read(destination).matchAll(/^#+ (.+)$/gm)].map(heading => heading[1].trim().toLowerCase().replace(/[^a-z0-9 _-]/g, '').replaceAll(' ', '-'));
        assert(headings.includes(anchor), `Broken heading link: ${filename} -> ${match[1]}`);
      }
    }
  }
  const blockContract = read(path.join(feature, 'block-mechanics.md'));
  for (const block of blocks) assert(blockContract.toLowerCase().includes(`## ${contract(block).replaceAll('-', ' ')}`), `Missing block contract: ${block.id}`);
  const atlas = read(path.join(feature, 'block-states.md'));
  for (const block of blocks) assert(atlas.includes(`## ${block.id}\n`), `Missing state atlas: ${block.id}`);
}
console.log(`${checking ? 'Verified' : 'Generated'} ${outputs.size} Markdown references: ${JSON.stringify(summary)}`);