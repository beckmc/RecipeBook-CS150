# RecipeBook-CS150
A project I wrote for my Data Structures and Algorithms class in the spring of 2015.

## Authors:
Matthew Beck

## How To Run:
Download the zip of this repository, and run InputOutput.java.

## The Project:
The goal for this problem was to design, implement, and evaluate an electronic cookbook, used to organize recipes and prepare meals. This program supports the following operations:
  - Adding recipes
  - Deleting recipes
  - Finding recipes
  - Planning a "meal"

This program is given a text file of recipes which are read in, stored appropriately, and easily accessable when the user is queried for information.

It is worth noting that some aspects, such as cuisine types and ingredients, were randomly generated by my professor and as such this project is focused around those. Similarly, the lists of recipes were randomly generated by him and are included with the code. The goal with the code is to make it as easy as possible to add new categories of food if needed, while being able to achieve the tasks with the given parameters.

### Inputs
- A Text File, recipes_x.txt, where the x is a number indicating the number of recipes in the file. There are two different files included, recipes_10.txt and recipes_60000a.txt. Each recipe is separated from the next by a blank line. A recipe is described by a series of lines, each of which consists of a keyword value pair. The value may consist of multiple words separated by a space. The keywords and legal values are:
  * cuisine: Italian, Chinese, Greek, Turkish, Indian, Pakistan, French, Korean 
  * name: <made up>
  * prepTime: <integer>
  * cookTime: <integer>
  * type: salad, entree, appetizer
     
  All recipes in the file were randomly generated, and have a random type, cuisine, prep and cook times, and ingredients. The ingredients are broken down into three categories: main, addons, and sides. Each type of dish has certain ingredients that fall into those categories:
  * entree:
    * main: beef,chicken,lamb,goat,salmon,tofu,tempeh,shrimp,scallops
    * addons: broccoli,cauliflower,spinach,mushrooms,beans,potatoes,beets,peas,rice
    * sides: eggs,cheese,milk,peas,beans
  * salad
    * main: kale,spinach,lettuce,chard,mesclun,arugula
    * addons: radishes,scallions,eggs,cheese
    * sides: cucumbers,carrots,mushrooms,potatoes
  * appetizer
    * main: beef,chicken,tofu,shrimp,scallops
    * addons: peas,rice,cheese,eggs
    * sides: spinach,mushrooms,cheese,beans
  
- Commands entered through the console by the user. Prompts are given, and only those commands will be accepted. The valid inputs are:
  * add - Add a recipe to the recipe book. The user will be prompted, one line at a time, for the information about the recipe. Blank inputs are acceptable.
  * delete - Delete a recipe from the recipe book. The user is prompted for the name of the recipe to be deleted. That recipe is then displayed, if found, and the user is prompted for confirmation. If the recipe doesn't exist then an error message is displayed.
  * find - Allows the user to find recipes that fulfill the following criteria:
    * total time: The combined time to prep and cook a recipe.
    * required items: Any ingredients listed above, as well as the following categories - seafood, meat, dairy, vegan, vegetarian, and shellfish.
    * excluded items: The same inputs are valid that can be entered in required items.
    * cuisine: Any of the cuisines listed above, as well as the following categories - Asian, Middle Eastern, and South Asian
  * plan - Prompts the user for the same information as finding a recipe, however instead a meal plan is generated that fulfills the criteria and consists of 1 entree, 1 salad, and 2 appetizers. An error message is displayed if a plan cannot be generated. For the meal plan, prep times cannot overlap, however cook times can when taking the total time into consideration. The plan that comes closest (and less than) the total time specified is displayed. 
  * quit - Writes the recipes to the file "newRecipes.txt" and exits the program.
  
  For reference, the additional categories are defined as follows:
    * vegetarian - no meat, no seafood
    * vegan - vegetarian, no eggs, no dairy
    * Asian - Chinese or Korean
    * Middle Eastern - Greek or Turkish
    * South Asian - Indian or Pakistan


### Constraints
The following data structures were required to be implemented in the project:
  - ArrayList
  - List (including Stack or Queue)
  - Hash Map/Set
  - Tree Map/Set
