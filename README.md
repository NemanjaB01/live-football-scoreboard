# Live Football World Cup Scoreboard Library

A simple in-memory library for managing live football match scores during tournaments.

## Overview

This library provides real-time management of ongoing football matches, including starting matches, updating scores, finishing matches, and retrieving ordered summaries.

## Architecture & Design Decisions

### Domain Entities

1. **Match** - Immutable value object representing a single football match
   - Unique identifier (auto-generated)
   - Home team name
   - Away team name
   - Home team score
   - Away team score
   - Creation timestamp (for ordering ties)

2. **Scoreboard** - Main facade managing all active matches
   - In-memory store using LinkedHashMap to maintain insertion order
   - Orchestrates all operations

### Data Structure

**In-Memory Store**: `LinkedHashMap<Integer, Match>`
- **Why LinkedHashMap?** Maintains insertion order, which is critical for tiebreaking in summary ordering
- **Key**: Auto-incremented Match ID
- **Value**: Match object

### Ordering Logic for Summary

Matches are sorted by:
1. **Primary**: Total score (home + away) in descending order
2. **Secondary**: Most recently started match (insertion order) in case of ties

## Assumptions & Design Notes

1. **Team Names Are Immutable**: Once a match is started, team names cannot be changed
2. **Score Validation**: Only non-negative scores are allowed
3. **Match Uniqueness**: No two matches can be started with the same home/away team pair simultaneously
4. **No Concurrent Access**: This library is not thread-safe; external synchronization needed for multi-threaded environments
5. **Match Lifecycle**: A match can only be finished if it's currently active on the scoreboard
6. **Auto-Generated IDs**: Matches receive unique auto-incremented IDs upon creation
7. **Score Updates are Absolute**: Updates receive absolute scores, not deltas
8. **Empty Team Names**: Team names are validated to be non-empty strings
9. **Zero Scores**: Initial scores are always 0-0; negative scores are rejected

## Features Implemented

- [x] Feature 1: Start Match
- [ ] Feature 2: Update Score
- [ ] Feature 3: Finish Match
- [ ] Feature 4: Get Summary
- [ ] Feature 5: Edge Cases

## Development Approach

This project follows **strict Test-Driven Development (TDD)**:
- ✅ Red Phase: Write failing tests first
- ✅ Green Phase: Implement minimal code to pass tests
- ✅ Refactor Phase: Clean up and optimize
- ✅ Commit: Granular commits with semantic versioning

## Testing

Run tests with:
```bash
mvn clean test
```

## Technology Stack

- **Language**: Java 21
- **Build Tool**: Maven
- **Testing Framework**: JUnit 5 (Jupiter)

## Project Structure

```
src/
├── main/java/com/scoreboard/
│   ├── Scoreboard.java
│   ├── Match.java
│   └── MatchId.java
└── test/java/com/scoreboard/
    └── ScoreboardTest.java
```

---

**Last Updated**: Initial setup
**Status**: In Development - Feature 1 in progress

