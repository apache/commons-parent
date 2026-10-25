<!--
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

     https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->

# AGENTS.md

Guidance for AI coding agents working in this repository.

## Verification

To run all checks including tests, run:
```
mvn -B -ntp
```

For a quick smoke test that runs non-test checks, run:
```
mvn -B -DskipTests -DskipITs -ntp
```

## Formatting

### Javadoc

1. **Wrap at 160 columns**, not 80.
   Count the leading ` * ` prefix as part of the line.
   Keep wrapping natural (do not break inside a `{@link ...}` or across a tag boundary if it can be avoided).
2. **Getter summaries start with "Gets"; setter summaries start with "Sets".**
   Prefer these verb forms over "Returns".
   For non getter/setter methods keep a short verb phrase (for example "Configures", "Hardens", "Dispatches").
3. **Javadoc style**:
   Key rules:
   - Open on its own line with `/**`,
     close on its own line with ` */`,
     align intermediate lines with a single leading ` * `.
   - The first sentence is a short summary fragment.
     It is the only part that appears in class/method index listings, so keep it self-contained.
     Details (what is secured, thread-safety, edge cases) belong in subsequent paragraphs, not in the summary.
   - Separate paragraphs with a blank `*` line.
     Each paragraph *except the first* is enclosed with paragraph tags (`<p>` and `</p>`) on their own lines.
   - Don't use the tags `@author`, `@version`.
   - Block tags appear in the order `@param`, `@return`, `@throws`, `@since`, `@see`, `@deprecated`. 
     No empty descriptions.
   - Javadoc is present on every public class and every public or protected member,
     with the standard exceptions (overrides, self-explanatory members, trivial `equals`/`hashCode`).

### Prose

- Use US English spelling and grammar.
- Sentences end in a period.
- No em-dashes (`—`) in Javadoc, comments, commit messages, or documentation.
  Use commas, colons, parentheses, or a new sentence instead. This applies to
  HTML entities too (`&mdash;`).

### Commits

- Use the `Assisted-By:` trailer (not `Co-Authored-By:`).

## Security

Security model: [SECURITY.md](./SECURITY.md)

Agents that scan this repository should consult `SECURITY.md` for the
project's threat model, in-scope / out-of-scope declarations, and known
non-findings before reporting issues.
