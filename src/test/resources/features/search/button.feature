Feature: Search

#  https://docs.qameta.io/allure/#_cucumber_jvm
  @Regression
  @severity=critical
  @issue=<TT-1>
  Scenario Outline: Search "<term>" by <search>

    #subdivide by step (one to GIVEN TERM and other do do the search

    Given User is in search page
    When User does a <search> search with <term>
    Then User is in correct search tab with term <term>
    Examples:
      | search | term     |
      | button | panda    |
      | button | cucumber |