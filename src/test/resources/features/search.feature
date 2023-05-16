Feature: Search

  Scenario Outline: Search with button

    Given User is in search page
    When User does a button search with term <term>
    Then User is in correct search tab

    Examples:
      | term      |
      | panda     |
      | staircase |