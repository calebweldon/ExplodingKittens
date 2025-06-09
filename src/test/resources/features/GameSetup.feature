Feature: Game Setup
  Checks that game is setup correctly
  for all numbers of players 2-5

  Scenario Outline: All Possible Number of Players
    Given a new game is started with <numberOfPlayers> players
    Then each player starts with the correct hand

    Examples:
      |numberOfPlayers |
      |2               |