Feature: Game Setup
  Checks that game is setup correctly
  for all numbers of players 2-5

  Scenario Outline: All Possible Number of Players
    Given a new game is started with <numberOfPlayers> players
    Then deck and player hands are correctly initialized

    Examples:
      |numberOfPlayers |
      |2               |
      |3               |
      |4               |
      |5               |