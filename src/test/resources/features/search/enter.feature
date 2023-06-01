Feature: Enter Search

  Rule: Enter scenarios.
  - Enter is used to submit search

  @Regression
  @severity=critical
  @issue=<TT-1>
  @Owner-Santiago_Lataza
  Scenario Outline: Search "<search_term>" by <search_method>

    Given user is in search page
    When user does a <search_method> search with <search_term>
    Then user is in correct search tab with term <search_term>
    Examples:
      | search_method | search_term |
      | enter         | panda       |
      | enter         | cucumber    |