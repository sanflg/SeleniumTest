Feature: Submit Search

  Rule: Submit scenarios.
  - Submit is used to submit search

  @Regression
  @severity=critical
  @issue=<TT-1>
  @Owner-Santiago_Lataza
  Scenario Outline: Search "<search term>" by <search method>

    Given user is in search page
    When user does a <search method> search with <search term>
    Then user is in correct search tab with term <search term>
    Examples:
      | search method | search term |
      | submit        | panda 1     |
      | submit        | cucumber    |