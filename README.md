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
- In-memory store: `TreeSet<Match>` with a custom comparator for ordering by total score descending, then start time descending.
