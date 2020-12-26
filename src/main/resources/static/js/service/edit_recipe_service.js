'use strict';
 
angular.module('myApp').factory('EditRecipeService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = '/api/v1/recipes';
 
    var factory = {
    		editRecipe: editRecipe,
    		getAllRecipes: getAllRecipes,
    };
 
    return factory;
 
    function editRecipe(recipe) {
        var deferred = $q.defer();
        // $http.post(REST_SERVICE_URI, recipe)
        console.log('edit recipe service ', recipe.name, recipe.id);
        console.log(recipe);
        $http.put("/api/v1/recipes/" + recipe.id, recipe)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while editing recipe');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    } 
    
    function getAllRecipes() {
    	var deferred = $q.defer();
		$http.get("/api/v1/recipes")
			.then(
			function (response) {
				deferred.resolve(response.data);
			},
            function(errResponse){
                console.error('Error while getting recipes');
                deferred.reject(errResponse);
            }
		);
		
		return deferred.promise;
	}
    
}]);