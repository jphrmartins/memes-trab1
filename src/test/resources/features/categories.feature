Feature: List Categories

    Scenario Outline: List all
        Given I have a list of categories
        When I list the categories
        Then I should see the <expectedCategories> categories
        Examples:
            | expectedCategories |
            | "gengiva,labios,dente,lingua,garganta,todos" |

    Scenario Outline: List filtered categories
        Given I have a list of categories
        When I filter the list by type <filterCategory>
        Then I should only see the filtered category
        Examples:
            | filterCategory |
            | "gengiva" |
            | "labios" |
            | "dente" |
            | "lingua" |
            | "garganta" |
            | "todos" |