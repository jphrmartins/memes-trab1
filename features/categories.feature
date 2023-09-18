Feature: List Categories

    Scenario: List all
        Given I have a list of categories
        When I list the categories
        Then I should see the following categories:
            | Type |
            | gengiva |
            | labios |
            | dente |
            | lingua |
            | garganta |
            | todos |

    Scenario: List filtered categories
        Given I have a list of categories
        When I filter the list by type "genviva"
        Then I should see the following category:
            | Type |
            | genviva |