Feature: List Categories

    Scenario Outline: List all
        Given I have a list of categories
        When I list the categories
        Then I should see the <expectedCategoriesList> categories
        Examples:
            | expectedCategoriesList |
            | "gengiva", "labios", "dente", "lingua", "garganta", "todos" |

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