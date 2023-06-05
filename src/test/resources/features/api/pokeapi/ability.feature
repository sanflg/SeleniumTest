Feature: Get ability

    @Regression
      @severity=critical
      @issue=<TT-1>
      @Owner-Santiago_Lataza
    Scenario Outline: Get ability by id = <id>

      When search ability by id: <id>
      Then status code is <statusCode> and ability name is <name>
      Examples:
        | id | statusCode | name |
        | 1  | 200        | pepe |
        | 2  | 200        | juan |