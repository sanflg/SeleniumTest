# A # language: header on the first line of a feature file tells Cucumber what spoken language to use.
# for example "# language: fr" for French. If you omit this header, Cucumber will default to English (en).

# Feature name is used to explicit the functionality that we are going to test in the feature file and as output in
# allure, it generates a new suite subdivision for each feature name that inside is going to contain the scenarios.
Feature: Button Search

  Search operation using a button click method with a search term.

  # Rule keyword is used to group a series of scenarios that may apply to a same basic condition, this keyword doesn't have
  # any implication on how the scenarios are going to be executed, is only aimed to help the documentation.
  # any plain text after rule is going to be ignored by allure and cucumber and only serves as code documentation but we
  # encourage the usage of # comment mark for any of this.
  Rule: Button scenarios.
  - Button is used to submit search

    # Rule keyword is used to group a series of scenarios that may apply to a same basic condition, this keyword doesn't have
    # any implication on how the scenarios are going to be executed, is only aimed to help the progression.
    @Regression
    # blocker, critical, normal, minor, trivial.
    @severity=critical
    # An array of issues are going to have each one it's link and will be presented together in allure test report.
    # Check allure properties in "test/resources/allure.properties" to check on how to set issues and tsmLinks links to
    # work.
    @issue=T-1
    @issue=T-2
    # Owner has its own section in report space in allure, this annotation doesn't allow spaces so we encourage to use
    # issue tracking system userNames rather than actual names for this field. we can avoid misspelling marking with
    # camel case, also may we consider to add a link to the user in the issue tracking system.
    @owner=sLataza
    # Every Feature or Scenario can be annotated by following tags: @flaky, @muted, @known but at the moment there is
    # a know defect for this field, in order to implement this annotations please check
    # https://github.com/allure-framework/allure2/issues/1611 and develop a workaround.

    # Scenario outline is used to parametrize inputs using examples (in contrast to "Scenario"). for allure to report
    # each scenario/scenario outline as an individual test and not as retries, parametrize the name also.
    # Take in consideration that "Scenario Outline" and "Scenario Template" are synonym and interchangeable keywords
    # for cucumber Gherkin.
    Scenario Template: Search "<search term>" by <search method>

      # For code to be executed before the actual test case we can use "Background" notation

      # We can define multi code executions in just one line and being more syntactically correct using "And" and "But".
      Given User is in search page
      When User does a <search method> search with <search term>
      Then User is in correct search tab with term <search term>

      # To send information more complex that simple vars like maps and matrices to test cases definitions we can use
      # "Data Tables", check more on https://cucumber.io/docs/gherkin/reference/ and search for Data Tables.
      Examples:
        | search method | search term |
        | button        | panda 1     |
        | button        | cucumber    |