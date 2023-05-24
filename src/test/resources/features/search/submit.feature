Feature: Submit Search

  Rule: Submit scenarios.
  - Submit is used to submit search

  @Regression
  @severity=critical
  @issue=<TT-1>
  @Owner-Santiago_Lataza
  Scenario Outline: Search "<search term>" by <search method>

    Given User is in search page
    When User does a <search method> search with <search term>
    Then User is in correct search tab with term <search term>
    Examples:
      | search method | search term |
      | submit        | panda 1     |
      | submit        | cucumber    |