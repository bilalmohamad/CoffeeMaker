# Author msmahate
# Author bmohama

Feature: Browse OBP
    As a user
    I want to perform simple navigation on OBP
    So that I can be sure basic testing infrastructure works
    

Scenario: Load CoffeeMaker
When I navigate to the CoffeeMaker site
Then I should be on the CoffeeMaker site

Scenario: Check Home Button Option
When I navigate to the CoffeeMaker site
And Click Add a Recipe
Then Click Home
Then I should be on the CoffeeMaker site



Scenario Outline: Navigate the links
When I navigate to the CoffeeMaker site
And I click on <name>
Then I should be on the page for <title>
Examples:
| name             | title |
| Update Inventory | inventory.html |
| Add a Recipe     | recipe.html |
| Edit Recipe      | editrecipe.html |
| Delete Recipe    | deleterecipe.html |
| Make Coffee      | makecoffee.html |


Scenario Outline: Add valid recipe
When I navigate to the CoffeeMaker site
And Click Add a Recipe
And I submit valid values for name: <recipeName>; cost: <price>; and ingredients: <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, <amtChocolate> chocolate
Then the recipe is successfully added
	
Examples:
| recipeName | price | amtCoffee | amtMilk | amtSugar | amtChocolate |
| MyCoffee     | 1     | 1         | 1       | 1        | 1            |

Scenario Outline: Valid Edit Recipe
Given The Coffeemaker has a recipe named MyCoffee
And Click Edit Recipe
And Click MyCoffee
And I edit valid values for cost: <price>; and ingredients: <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, <amtChocolate> chocolate
Then the recipe is successfully edited

Examples:
| price | amtCoffee | amtMilk | amtSugar | amtChocolate |
| 2     | 1         | 1       | 1        | 1            |

Scenario Outline: Invalid Edit Recipes
Given The Coffeemaker has a recipe named MyCoffee
And Click Edit Recipe
And Click MyCoffee
And I edit valid values for cost: <price>; and ingredients: <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, <amtChocolate> chocolate
Then the recipe is not edited

Examples:
| price   | amtCoffee | amtMilk | amtSugar | amtChocolate |
| 70.382  | 3         | 1       | 1        | 0            |
| 70      | 3.123     | 1       | 1        | 0            |
| 70      | 3         | 1.123   | 1        | 0            |
| 70      | 3         | 1       | 1.123    | 0            |
| 70      | 3         | 1       | 1        | 0.123        |
| -70     | 3         | 1       | 1        | 0            |
| 70      | 3         | -1      | 1        | 0            |
| -70     | 3         | 1       | -1       | 0            |

