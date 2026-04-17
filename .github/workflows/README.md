<!---
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

# Reusable Workflows

This directory contains
[reusable GitHub Actions workflows](https://docs.github.com/en/actions/how-tos/reuse-automations/reuse-workflows)
shared across Apache Commons projects. They provide a consistent and secure CI setup without duplicating configuration in each repository.

## CodeQL (`codeql-analysis-reusable.yml`)

Runs a [CodeQL](https://codeql.github.com/) security analysis and uploads the results to GitHub's
code-scanning dashboard. A separate job is created for each analyzed language.

To speed up the Java autobuild step, the workflow tries to restore a Maven dependency cache saved by
a prior build job. For the cache to be found, the build workflow must store it under the key:

```
${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
```

### Inputs

| Input       | Type   | Default                 | Description                                                                                                                                                                          |
|-------------|--------|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `languages` | string | `"['actions', 'java']"` | JSON array of [CodeQL language identifiers](https://docs.github.com/en/code-security/code-scanning/creating-an-advanced-setup-for-code-scanning/codeql-language-support) to analyze. |

### Required permissions

The caller job must grant:

```yaml
permissions:
  actions: read
  contents: read
  security-events: write
```

### Usage example

```yaml
name: CodeQL

on:
  push:
    branches: [ "master" ]
  pull_request: { }
  schedule:
    - cron: '33 9 * * 4'   # Randomize this expression

# Explicitly drop all permissions for security.
permissions: { }

jobs:
  codeql:
    # Intentionally not pinned: maintained by the same PMC.
    uses: apache/commons-parent/.github/workflows/codeql-analysis-reusable.yml@master
    permissions:
      actions: read
      contents: read
      security-events: write
```
