package com.qa.SamsRecipes.service;

public interface RecipeService {

	//C
	String addRecipe(String movie);
	
	//R
	String getAllRecipe();
	
	String getARecipe(Long id);

	//U
	String updateRecipe(String movie, Long id);

	//D
	String deleteRecipe(Long id);
	
	int cycleRecipe(String dish);

}