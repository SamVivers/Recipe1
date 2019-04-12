package com.qa.SamsRecipes.service;

import javax.inject.Inject;

import com.qa.SamsRecipes.persistence.repository.RecipeRepo;

public class RecipeServiceImpl implements RecipeService {
	
	@Inject
	private RecipeRepo repo;

	public String getAllRecipe() {
		return repo.getAllRecipe();
	}

	@Override
	public String addRecipe(String recipe) {
		if(recipe.contains("Olives")) {	
			return "Can't Add This Recipe";
		} else {
			return repo.createRecipe(recipe);
		}
	}

	@Override
	public String deleteRecipe(Long id) {
		return repo.deleteRecipe(id);
	}

	public void setRepo(RecipeRepo repo) {
		this.repo = repo;
	}

	@Override
	public int cycleRecipe(String dish) {

		return repo.cycleRecipe(dish);

	}

	@Override
	public String getARecipe(Long id) {
		return repo.getARecipe(id);
		
	}

	@Override
	public String updateRecipe(String recipe, Long id) {
		return repo.updateRecipe(recipe, id);
	}
}