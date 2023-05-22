Feature: Search

#  https://docs.qameta.io/allure/#_cucumber_jvm
  @Regression
  @severity=critical
  @issue=<TT-1>
  @Owner-Santiago_Lataza
  Scenario Outline: Search "<term>" by <search>

    Given User is in search page
    When User does a <search> search with <term>
    Then User is in correct search tab with term <term>
    Examples:
      | search | term     |
      | submit | panda 1  |
      | submit | cucumber |