Feature: File Handling
  This feature tests the correct handling and usage of feature files.


  Scenario: Feature files are used in plugin execution
    Given there are 2 feature files in the test/resources/features directory
    When the plugin checks for files
    Then they should be included in the fileset for later use

  Scenario: The extraction is executed
    Given 2 feature files are included in the plugin execution
    When the test extraction is executed
    Then the content should be JSONized

