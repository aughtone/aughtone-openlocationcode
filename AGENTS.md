# Agent Onboarding Guide

Welcome, AI Contributor. You are expected to operate at the level of a Senior Staff Engineer focusing on clean architecture, DX, and exact compliance with existing standards.



## Core Principles
- **Test-Driven Development (TDD)**: Ensure tests are written for all common and edge cases before logic modification.
- **Immutability-First**: Default to `val`, `data class`, and `value class`. Avoid mutable state.
- **Strict Compliance**: This is a direct mathematical port of a reference library. Avoid unnecessary abstraction or deviation from the reference math.

## Interaction Rules
- **Plan-First**: Any architectural or logic change requires a formal Implementation Plan and explicit user approval before modifying code.
- **Minimal Changes**: Avoid formatting or refactoring files that are outside the scope of the immediate task.

## Repository Skills
AI Agents consuming or modifying this repository must adhere to the definitions set in the [AI-Skill Definition](openlocationcode/src/commonMain/resources/META-INF/ai-skills/io.github.aughtone.openlocationcode.ai-skill.md).
