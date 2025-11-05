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
shared across Apache Commons projects.  
They provide a consistent and secure CI setup without duplicating configuration in each repository.

## Java CI (`maven-reusable.yml`)

This reusable workflow performs the standard Java build and test process for Commons components.

### Features

- Builds the project using a matrix of operating systems and JDK versions.
- Caches dependencies for faster builds.
- Uploads test reports for later inspection.
- Supports experimental JDKs via the matrix configuration.

### Usage Example

To include this workflow in a Commons repository, add the following file to `.github/workflows/maven.yml`:

```yaml
name: Java CI

on:
  push:
    branches: [ "master", "release" ]
  pull_request: { }

# Explicitly drop all permissions for security.
permissions: { }

jobs:
  build:
    # Intentionally not pinned: maintained by the same PMC.
    uses: apache/commons-parent/.github/workflows/maven-reusable.yml@master
```