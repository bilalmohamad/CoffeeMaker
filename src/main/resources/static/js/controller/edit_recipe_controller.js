'use strict';
 
angular.module('myApp').controller('EditRecipeController', ['$scope', 'EditRecipeService', function($scope, EditRecipeService) {
    var self = this;
    self.recipe = {id:'', name:'', price:'', coffee:'', milk:'', sugar:'', chocolate:''};
 
    self.submit = submit;
    self.reset = reset;
 
    function editRecipe(recipe){
    		// console.log("edit recipe controller");
    		$scope.success = false;
    		$scope.failure = false;
    		EditRecipeService.editRecipe(recipe)
            .then(
            		function (response) {
            			$scope.success = true;
            			// updateLocalRecipe($scope.recipes, $scope.recipe);
                    },
            		function (errResponse) {
            			$scope.failure = true;
            			$scope.success = false;
            			console.error('Error while editing recipe');
            		}
            );
    		
    		$scope.success = !($scope.failure);
    }
 
    function submit() {
    	editRecipe(self.recipe);
        // reset();
        // getAllRecipes();
        updateLocalRecipe($scope.recipes, self.recipe);
    }
    
    $scope.getRecipe = function getRecipe(id){
    	for (var i = 0; i < $scope.recipes.length; i++){
    		if (id == $scope.recipes[i].id){
    			self.recipe = {id:$scope.recipes[i].id, 
    					name:$scope.recipes[i].name, 
    					price:$scope.recipes[i].price, 
    					coffee:$scope.recipes[i].coffee, 
    					milk:$scope.recipes[i].milk, 
    					sugar:$scope.recipes[i].sugar, 
    					chocolate:$scope.recipes[i].chocolate
    				};
    			// console.log("ctrl getRecipe");
    			// console.log(self.recipe);
    		}
    	}
    }
    
    function updateLocalRecipe(recipes, recipe) {
    	for (var i = 0; i < recipes.length; i++){
    		if (recipe.id == recipes[i].id){ 
    			recipes[i].price = recipe.price;
    			recipes[i].coffee = recipe.coffee;
    			recipes[i].milk = recipe.milk;
    			recipes[i].sugar = recipe.sugar;
    			recipes[i].chocolate = recipe.chocolate;
    		}
    	}
    }
    
    function getAllRecipes() {
    	EditRecipeService.getAllRecipes()
    	.then(
    		function(response) {
    			$scope.recipes = response;
    			console.log("ctrl getAllRecipes");
    			console.log($scope.recipes);
    		},
    		function(errResponse) {
    			console.error('Error while getting recipes');
    		}
    	);
	}
 
 
    function reset(){
    	self.recipe={name:'', price:'', coffee:'', milk:'', sugar:'', chocolate:''};
        $scope.editRecipeForm.$setPristine(); //reset Form
    }
    
    getAllRecipes();
 
}]);