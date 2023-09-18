Feature: List Contents

    Scenario: List all Contents
        Given I am at the categories page
        When I click on the "todos" button
        Then I should see all Contents

    Scenario: List filtered content
        Given I am at the categories page
        When I click on the "todos" button
        And I use the search bar to filter by "gengiva"
        Then I should see filtered contents by "gengiva" category