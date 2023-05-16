Feature: Search

  Scenario Outline: Search with button

    Given User is in search page
    When User does a button search with term <term>
    Then User is in correct search tab

    Examples:
      | term      |
      | panda     |
      | staircase |

  Scenario Outline: Search with submit

    Given User is in search page
    When User does a submit search with term <term>
    Then User is in correct search tab

    Examples:
      | term      |
      | panda     |
      | staircase |

  Scenario Outline: Search with enter

    Given User is in search page
    When User does an enter search with term <term>
    Then User is in correct search tab

    Examples:
      | term      |
      | panda     |
      | staircase |