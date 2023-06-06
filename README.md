# Selenium Test

Architectural notes relevant to this project for each dependency and implementation.

## On Cucumber

1. Inheritance.
    1. Classes with Step or Hook notations cannot be inherited, because of this is highly recommended to make them
       FINAL to avoid confusions in the future when trying to inherit them.
    1. Parent classes/interfaces cannot define abstract methods for children classes with Step or Hook notations,
       cucumber doesn't support this and gives an error DuplicateStepDefinition so behavior enforcing is not allowed
       (Follow issue https://github.com/cucumber/cucumber-jvm/issues/2757 to check if this is resolved.)
1. Instantiation and Constructors:
   In order for Cucumber to make usage of the Steps and Hooks in a class, it needs to make an instantiation of it
   not being aware of the required arguments for the constructor, giving an error for classes defined with only one
   constructor with 1 or more arguments. This can be sorted with multiple approaches:
    1. Step Definition classes are wrapper classes/children of our desired pageObjects:
        - Method duplication for each PageObject method (or most of them).
        - Decoupling of cucumber and page object logic.
    1. GlueObjects (check "On GlueObjects" chapter) Init method with ThreadLocal for params and "super" call. Init
       method is going to be called before the instantiation, requiring to be static, because the static environment,
       decided to use a threadLocal to set the desired parameter and call super constructor (can have arguments):
        - Discarded due the excessive verbosity of having a ThreadLocal for each object containing the params for
          each object and having a redundant innit method and constructors.
1. Step Definition classes structure:
   As explained in "GlueObjects" chapter point 6th in this file, Cucumber tends to be stateless, because of this and
   promoting the decoupling of PageObjects and Step Definition classes, we recommend to have separated classes, one directory with
   the pageObjects and another with cucumber steps (specific steps for a page and common steps in a general one but
   don't inherit it, as classes with Step or Hook notations cannot be inherited as told in "GlueObjects" chapter point
   1 in this file). Now, we need to get access to page object and this can be implemented having an instance of the
   class as variable.
1. Having all static methods on Step Definition classes and pageObjects: STATUS = DISCARDED
   If we are using all methods as static, also the WebElements must be, but this arises the problem that web elements
   are obtained by driver in a non-static environment such as each test case, this may generate
   that objects are not present at the moment of the static initialization, for that, we can use lazy initialization
   and lazy getter to obtain such WebElement/List<WebElement> on demand when we know that is available
   in code. In order to achieve this, we also need to call the current instance driver that holds the required context
   to bring the desired object doing a DriverManager call. HOWEVER, THIS ONLY WORKS SEQUENTIALLY DUE THAT IN MULTI
   THREADING, THE LAZY INITIALIZATION ASSIGNS THE VARIABLE VALUE ON THE FIRST CALL, BUT AFTER THAT ALL THE OTHER
   DRIVERS AND TEST WILL FIND THE OBJECT ALREADY INITIALIZED WITH AN OLDER REFERENCE OF ANOTHER DRIVE. In order to make
   this work we need to do highly verbose code to, in each object call or method context, get the current driver from
   DriverManager and instantiate the WebElement. However, this implementation contradicts common Cucumber practices and
   DI (dependency injection) standard libraries like "PicoContainer" where we expect to have the page object
   instantiated in the Step Definition class in order to work. see examples:
   https://www.programsbuzz.com/article/share-webdriver-instance-cucumber-using-picocontainer
1. Set cucumber dependency scope to test to limit where we can create cucumber classes.
1. Take in consideration, once abstraction is allowed on cucumber step definitions, that we could implement DDD approach
   kind of solution with package administration like in the following link:
   http://confessionsofanagilecoach.blogspot.com/2017/05/teaching-cucumbers-about-boundaries.html

Resume of action points on cucumber:
* Make all Step Definition classes FINAL.
* Check issue https://github.com/cucumber/cucumber-jvm/issues/2757 if we can rule behavior by interfaces and 
  abstract classes inheritance on Step Definition classes.
* For each page object class, there should be a Step Definition class that applies the behavior for cucumber.
* In step definitions classes, we need to have page objects instantiated as variable.
* set cucumber scope to test in pom

## On Selenium
1. When declaring a WebElement with @FindBy notation, if we get unused/unassigned warnings, just right-click on the
   variable, click on QuickFixes and click on "Suppress unused warning if annotated by '@FindBy'" since no code
   solution can be provided here (Intellij solution).
1. Make goTo methods in pageObjects static and return a new instance, in this way we can have instantiation and driver
   get at the same time (most usual scenario).

## On PicoContainer
1. Page object class instantiation by dependency injection on Step Definition classes:
   There is a way to avoid doing a "new Class()" call on Step Definition classes for each page object and that is by
   dependency injection with pico container. i.e.:
    * Add PicoContainer to pom.
    * Create a constructor and variable on Step Definition class like:
        ```java
            public class StepDefinition {
                private final SearchPage searchPage;

                public SearchPageSD(SearchPage searchPage){
                    this.searchPage = searchPage;
                }

                @When("User does a {} search with {}")
                public void userDoesASearchWith(String search, String term) {
                    searchPage.searchBy(search, term);
                }
            }
        ```
      Implemented picoContainers with ScenarioContext class. there we can define the format of the data to be stored in
      a pico container, and also we need to add the ScenarioContext class to the stepDefinition classes.
      But since PicoContainer cannot build the parameters for constructors with dependency injection, this is not
      available for classes with default constructors with args. See more in:
      https://github.com/cucumber/cucumber-jvm/issues/1044#issuecomment-239605417

## On Allure
1. In order to allure to avoid consuming each cucumber example as retries, we need to parametrize the step name using
   notation like "Scenario name <variable>" and will generate a new execution for each in the report, however, this will
   take each example as a new test, to have a correct representation of test, we may need to implement Categories.
1. On nested calls of @Step notation, allure generates a hierarchy of calls in the test report such as:
    > Search by enter with 'staircase' term
    * Get Driver
    * Driver.get() url
1. aspectjweaver is necessary as part of an AOP solution for notation injection on report.
1. For new execution ways (jenkins, local or any implemented CI/CD system, add an executor file data in resources).
1. On new projects check the categories you want to implement.
1. In order to check for more documentation for allure report integration with cucumber feature files, check
   test/resources/features/search/button.feature file with detailed info on each field.
1. In order to take allure screenshots and attach them, we need to define a plugging like StepScreenshotPlugin class
   and add it before the Allure plugging (AllureCucumber7Jvm) in runner class.
1. To attach logs to each step, first we define a parametrized log in log4j2.xml file, using the thread name, and later
   we append per test each log generated by thread name (investigating a less controversial way to differentiate logs).

## On GlueObjects (PageObjects with glue notation) Status = Discarded.
Example:
```java
public class PageObject extends BasePage{
    @FindBy(name = "name")
    private WebElement elements;

    @Given("This step")
    public void doThis(){
        doSomething();
    }
}
```
Knowledge:
1. Directory allocation:
   Glue steps can be defined in main directory, just changing the cucumber runner removing the dynamic reference in
   the path like glue = `{"org.seleniumtest"}`
1. Driver Management and runner classes:
   In order to have a testNG suite at the same time, we cannot share the same runner class like BaseTest since:
    * In order to TestNG classes to be executed, said classes need to inherit the BaseTest (where we do the
      BeforeSuite, BeforeMethod and AfterMethod) but if we add the cucumber notations here, as we mentioned before in
      "On Cucumber" chapter point 1, BaseTest cannot be inherited, because of this, decided to move the Before, After
      and BeforeAll cucumber notations to CucumberRunnerTests class since it is going to be there anyway and implements
      AbstractTestNGCucumberTests.
    * All shared logic is managed by DriverManager class and is called by respective runners.
1. All glue steps for the moment requires @Step and @Hook double notation in order to be consumed for allure in testNG
   context and have the cucumber hook (investigation better implementation).
1. Return types on methods:
   Cucumber tends to be stateless, that means that uses Step Definitions but no context information is usually applied
   to te step (it can use textContext or PicoContainer implementations), since this implementations, is usual that most
   steps have "void" return type but, actually, the methods can have a return type and be used on traditional test
   automation scenarios in a testNG test and don't get any kind of warning on the process.

## Conclusion
We discarded this implementation since the high level of coupling between cucumber glue implementations and page object
logic, there may be some different implementations we may need to apply to each one, because of this, PageObject classes
should not have cucumber/testNG logic on them since their responsibility is just to describe the page and operate in it
with drivers. Avoid all testNG and cucumber logic here, make prevail decoupled implementations with clear
responsibilities.