# Authors and Contributors

We are grateful for all the contributions we have received over the years and wanted to make sure we included all
possible ones.

## Original author

Ben Mazur <bmazur@sev.org>

## Current Maintainer

MegaMek GitHub Organization <https://github.com/MegaMek> with the main [MegaMek](https://megamek.org)

## How we generated this list

This list is taken from the API, filtered to just pull the login name and GitHub URL, sorted, then added here. The
commands that were used to
generate this list are as follows:

```bash
gh api -H "Accept: application/vnd.github+json"  -H "X-GitHub-Api-Version: 2022-11-28" '/repos/megamek/megameklab/stats/contributors' > contributors.json
```

From this list, we used `irb` (Interactive Ruby) to process and output that is below:

```ruby
contrib = JSON.parse(File.read('contributors.json'))
filter = contrib.filter_map { |record| [record['author']['login'], record['author']['html_url']] unless record == nil || record['author'] == nil }
filter.sort_by { |user, _| user }.each { |user_name, url| puts "- #{user_name} <#{url}>\n" }
```

## Contributors

Last updated: 2025-05-19

- AaronGullickson <https://github.com/AaronGullickson>
- Akjosch <https://github.com/Akjosch>
- BLOODWOLF333 <https://github.com/BLOODWOLF333>
- BlueNexus <https://github.com/BlueNexus>
- CarefulLilCassie <https://github.com/CarefulLilCassie>
- Dylan-M <https://github.com/Dylan-M>
- HammerGS <https://github.com/HammerGS>
- HoneySkull <https://github.com/HoneySkull>
- IllianiBird <https://github.com/IllianiBird>
- NickAragua <https://github.com/NickAragua>
- SJuliez <https://github.com/SJuliez>
- Saklad5 <https://github.com/Saklad5>
- Scoppio <https://github.com/Scoppio>
- Sleet01 <https://github.com/Sleet01>
- Taharqa <https://github.com/Taharqa>
- Windchild292 <https://github.com/Windchild292>
- arlith <https://github.com/arlith>
- beerockxs <https://github.com/beerockxs>
- binaryspica <https://github.com/binaryspica>
- dependabot[bot] <https://github.com/apps/dependabot>
- dericpage <https://github.com/dericpage>
- exeea <https://github.com/exeea>
- juk0de <https://github.com/juk0de>
- kuronekochomusuke <https://github.com/kuronekochomusuke>
- mhjacks <https://github.com/mhjacks>
- nderwin <https://github.com/nderwin>
- neoancient <https://github.com/neoancient>
- pakfront <https://github.com/pakfront>
- pavelbraginskiy <https://github.com/pavelbraginskiy>
- pheonixstorm <https://github.com/pheonixstorm>
- pmjdebruijn <https://github.com/pmjdebruijn>
- pokefan548 <https://github.com/pokefan548>
- psikomonkie <https://github.com/psikomonkie>
- raelik <https://github.com/raelik>
- redbatz <https://github.com/redbatz>
- rjhancock <https://github.com/rjhancock>
- sixlettervariables <https://github.com/sixlettervariables>
- vizax <https://github.com/vizax>
- wildj79 <https://github.com/wildj79>
