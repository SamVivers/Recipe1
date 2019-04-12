package com.qa.SamsRecipes.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.SamsRecipes.service.RecipeService;
import com.qa.SamsRecipes.persistence.domain.Recipe;
import com.qa.SamsRecipes.util.JSONUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RecipeDBRepo implements RecipeRepo{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	@Transactional(REQUIRED)
	public String createRecipe(String recipe) {
		Recipe aRecipe = util.getObjectForJSON(recipe, Recipe.class);
		manager.persist(aRecipe);
		return "{\"message\": \"recipe has been sucessfully added\"}";
	
	}

	@Override
	public String getAllRecipe() {
		Query query = manager.createQuery("Select r FROM Recipe r");
		Collection<Recipe> recipe =  (Collection<Recipe>) query.getResultList();
		return util.getJSONForObject(recipe);
	}
	
	@Override
	public String getARecipe(Long id) {
		return util.getJSONForObject(manager.find(Recipe.class, id));
	}

	@Override
	public String updateRecipe(String recipe, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteRecipe(Long id) {
		Recipe recipeInDB = util.getObjectForJSON(getARecipe(id), Recipe.class);

		if (manager.contains(manager.find(Recipe.class, id))) {

			manager.remove(manager.find(Recipe.class, id));
		}
		return "{\"message\": \"movie sucessfully deleted\"}";
	}

	@Override
	public int cycleRecipe(String dish) {
		Query query = manager.createQuery("Select a FROM Recipe a");
		Collection<Recipe> recipe = (Collection<Recipe>) query.getResultList();

		List<Recipe> validList = recipe.stream().filter(n -> n.getDish().equals(dish)).collect(Collectors.toList());

		return validList.size();
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}



}
