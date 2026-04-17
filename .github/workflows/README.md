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

## Scorecards (`scorecards-analysis-reusable.yml`)

Runs an [OpenSSF Scorecard](https://securityscorecards.dev/) analysis and uploads the results to
GitHub's code-scanning dashboard.
For public repositories, the results are also published to the Scorecard API, enabling the
Scorecard badge.

This workflow has no inputs.

### Required permissions

In addition to uploading results to the code-scanning dashboard (`security-events: write`),
the workflow authenticates with securityscorecards.dev using an OIDC token (`id-token: write`).
The caller job must grant:

```yaml
permissions:
  actions: read
  contents: read
  security-events: write
  id-token: write
```

### Usage example

```yaml
name: Scorecards

on:
  branch_protection_rule:
  schedule:
    - cron: '30 1 * * 6'   # Randomize this expression
  push:
    branches: [ "master" ]

# Explicitly drop all permissions for security.
permissions: { }

jobs:
  scorecards:
    # Intentionally not pinned: maintained by the same PMC.
    uses: apache/commons-parent/.github/workflows/scorecards-analysis-reusable.yml@master
    permissions:
      actions: read
      contents: read
      security-events: write
      id-token: write
```
