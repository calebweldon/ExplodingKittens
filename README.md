![Gradle Build](https://github.com/nu-cs-sqe/course-project-20242510-team-01-20242503/actions/workflows/main.yml/badge.svg)
# Team-01 Exploding Kittens

## Contributors
- Caleb Weldon
- Benjamin Ye
- Eiko Reisz
- Samarth Arul

## Dependencies
- JDK 11
- JUnit 5.10
- Gradle 8.10

## Acknowledgements
REFERENCES, SOURCE OF HELP ETC

# Mutation Testing
 - In order to check whether the shuffle method works, I compare cards at specific indices before and after shuffling. This mutation test may occasionally fail (due to an equivalent mutant) on the off chance that a copy of the same card somehow ends up in the same index.

# Code Coverage
 - The getCardCount() method in TurnController is purely a helper method for testing and arguably does not need code coverage.