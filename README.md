# Live Football World Cup Scoreboard

This is a simple Java library for managing a live football scoreboard.

## Requirements
- Start a new match with initial score 0-0.
- Update score with absolute scores.
- Finish a match.
- Get summary of ongoing matches ordered by total score descending, ties by most recently started.

## Assumptions
- Teams are identified by unique string names.
- No two matches can have the same pair of teams simultaneously.
- Scores are non-negative integers.
- Start time is recorded when the match is started.
- The library uses in-memory storage with collections.
- Ordering for summary: total score (home + away) descending, then start time descending (most recent first).

## Domain Entities
- `Match`: Represents a match with home team, away team, scores, and start time.
- `Scoreboard`: Manages matches and provides operations.

## Data Structure
- In-memory store: Currently `List<Match>` (will evolve to `TreeSet<Match>` with custom comparator for ordering when summary feature is implemented).

## Validations
- Team names: Non-null, non-empty.
- Scores: Non-negative integers.
- Matches: Unique team pairs; updating/finishing non-existent matches throws exceptions (to be implemented).


## Notes
- Data structure evolved from List to TreeSet for ordering.
- TDD process followed with granular commits.
