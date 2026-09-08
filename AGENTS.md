# Agent Onboarding Guide

Welcome, AI Contributor. You are expected to operate at the level of a Senior Staff Engineer focusing on clean architecture, DX, and exact compliance with existing standards.

# Working in this repository

## This repository is public

Everything committed here is world-readable and permanent — a public git
history cannot be unpublished, only added to. Write for a stranger who
found this repo, not for the maintainer.

**Never commit personal details.** Concretely, that means no real names in
prose or code comments, no personal email addresses, no machine or
hostnames, no absolute home paths, no employer, client or private project
names, no internal tracker keys or instance URLs, and nothing about the
maintainer's business, billing, tax position or working habits. Verbatim
quotes from a working conversation are the most common way all of this
leaks at once — a design discussion is full of them.

The exceptions are deliberate and narrow: the copyright line in `LICENSE`
and `NOTICE`, maintainer attribution in `README.md`, the GitHub handle
and its `users.noreply.github.com` address as vendor in the YouTrack app
manifest, and the published Maven coordinates used as worked examples. Do
not add to that list without being asked, and do not strip what is on it.

Authorship of the tool is fine; identity beyond it is not. A GitHub handle
is the right granularity — it is already public, and it is the name the
project is known by.

**Document the pattern, not the person.** When a design came out of
someone's specific situation — how they work, what tools they pay for, what
their client expects — the reusable content is the *pattern*: this is a
common way developers work, here is why the obvious design fails against
it, here is how this one covers it. Written that way the reasoning survives
intact and nothing traces back to an individual. If a fact only makes sense
as "the maintainer does X", it does not belong here.

This applies hardest to documents an agent generates from a conversation —
RADs, ADRs, design notes, session summaries. Those are written while the
conversation is still in context, which is exactly when quoting feels
natural and is most dangerous.

**Examples use placeholders.** `acme`, `example.com`, `PROJ-123`,
`<instance>.youtrack.cloud`, `owner/repo`. Never a real project, org or
instance, even one that happens to be public — a real name in an example
reads as a live reference and invites someone to go look.

**Check before you commit.** Grep your own additions for names, emails,
hosts, home paths and project names before proposing them. If something is
borderline, leave it out and say so — it is far cheaper to add a detail
later than to remove one from a public history.


## Core Principles
- **Test-Driven Development (TDD)**: Ensure tests are written for all common and edge cases before logic modification.
- **Immutability-First**: Default to `val`, `data class`, and `value class`. Avoid mutable state.
- **Strict Compliance**: This is a direct mathematical port of a reference library. Avoid unnecessary abstraction or deviation from the reference math.

## Interaction Rules
- **Plan-First**: Any architectural or logic change requires a formal Implementation Plan and explicit user approval before modifying code.
- **Minimal Changes**: Avoid formatting or refactoring files that are outside the scope of the immediate task.

## Repository Skills
Do not add `*.ai-skill.md`, `META-INF/ai-skills/` or `META-INF/agents/skills/` to this repository, and do not scan dependencies for them.

This is a rule about AI skills, not about `META-INF` generally. Packaging `META-INF/LICENSE` and `META-INF/NOTICE` into the published jars is expected and is not covered by this ban.

## Apache 2.0 attribution in source headers — done, keep it this way

This library is a derivative work of Google's Open Location Code (Apache 2.0). The
`LICENSE` and `NOTICE` files at the repository root are complete — do not change them
without being asked.

Every derived source file carries three things, and all five current files now do:

1. **§4(c) — the upstream copyright notice, retained verbatim.** `Copyright 2014 Google
   Inc. All rights reserved.` Keep that line first and unchanged.
2. **The port's own copyright line.** `Copyright 2026 The Aught One Authors`, second.
3. **§4(b) — a modification notice.** The `Modifications:` paragraph at the foot of the
   header. That paragraph *is* the prominent notice §4(b) requires; it is satisfied
   per-file, not in one central place.

```kotlin
/*
 * Copyright 2014 Google Inc. All rights reserved.
 * Copyright 2026 The Aught One Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * ... leave the existing licence body exactly as it is ...
 *
 * Modifications: ported from the original Java implementation to Kotlin Multiplatform.
 * The logic, constants and mathematics follow the original; the implementation was
 * rewritten in Kotlin.
 */
```

The upstream line goes **first** and ours second. This matches the sibling
`aughtone-phonenumber` port; the aughtone family uses one ordering.

Files **not** derived from Google's work carry only `Copyright 2026 The Aught One
Authors`, the full licence body, and no modification notice — over-attributing is as
misleading as under-attributing, because it obscures who wrote what.

### Which files are derived, and from what

Upstream's `java/src/main/java/com/google/openlocationcode/` contains exactly one file,
`OpenLocationCode.java`. There is no `PlusCode` class and no standalone `CodeArea` class
upstream, so "corresponds to an upstream Java class" is not the right test. What each
file actually derives from:

| File | Derived from |
|---|---|
| `OpenLocationCode.kt` | `OpenLocationCode.java` |
| `CodeArea.kt` | `CodeArea`, a nested class in `OpenLocationCode.java` |
| `PlusCode.kt` | the instance-method half of `OpenLocationCode.java`; its KDoc tracks the upstream javadoc closely |
| `OpenLocationCodeTest.kt` | the nine upstream Java test classes, consolidated into one |
| `TestData.kt` | upstream `test_data/*.csv`, inlined as Kotlin |

All five are derivative works and all five keep Google's header. Upstream has no Kotlin
implementation — its language directories are c, cpp, dart, garmin, go, java, js,
plpgsql, python, ruby, rust and visualbasic — so this port derives from the Java
implementation, exactly as the README says.

### Trademark

Apache 2.0 §6 grants no trademark rights. Use "Open Location Code" and "Plus Codes"
**nominatively only**, to identify origin, in README and NOTICE prose. The artifactId
`openlocationcode` under the `io.github.aughtone` namespace is descriptive rather than a
coined mark and is fine as-is; do not rename it, and never imply endorsement. This was
settled — do not reopen it without a new reason.

## Open task: list this library in Google's External Implementations

This port stays outside Google's repository deliberately. Google declined to take Kotlin
ports into the main tree in
[google/open-location-code#366](https://github.com/google/open-location-code/pull/366)
(open 2019, closed 2024), where the maintainer wrote:

> This is great work but unfortunately we're struggling to support the implementations we
> already have. If you can publish it to your own repo, please add a link in
> [External_Implementations.md](https://github.com/google/open-location-code/blob/main/Documentation/External_Implementations.md#external-implementations).

Adding that link is the half of the arrangement we have not done yet: a one-line entry in
`Documentation/External_Implementations.md`, submitted as a pull request to
`google/open-location-code`.

**The maintainer owns this task and will do it directly.** Do not open the pull request,
and do not prepare one unless asked. What is needed from this repository's side is only
that the library is published and its details are accurate — the artifact coordinates,
supported targets, and repository URL that the entry will cite.

For context when it happens: `googlebot` will ask for a signed
[CLA](https://cla.developers.google.com/) on the pull request. The maintainer has decided
not to sign one for a one-line documentation change; whether the entry is merged is then
Google's call.
