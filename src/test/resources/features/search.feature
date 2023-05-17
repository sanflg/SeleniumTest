Feature: Search
#  https://docs.qameta.io/allure/#_cucumber_jvm
  @Regression
  @severity=critical
  @issue=<TT-1>
  Scenario Outline: Search

    Given User is in search page
    When User does a <search> search with term <term>
    Then User is in correct search tab

    Examples:
      | search | term      |
      | button | panda     |
      | button | staircase |
      | submit | panda     |
      | submit | staircase |
      | enter  | panda     |
      | enter  | staircase |
#  @Regression
#  Scenario Outline: Search with enter: <term>
#
#    Given User is in search page
#    When User does an enter search with term <term>
#    Then User is in correct search tab
#
#    Examples:
#      | term  |
#      | panda |