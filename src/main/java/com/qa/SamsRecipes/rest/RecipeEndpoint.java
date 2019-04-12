package com.qa.SamsRecipes.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.SamsRecipes.service.RecipeService;

@Path("/Recipe")
public class RecipeEndpoint {
	
	@Inject
	private RecipeService service;

	@Path("/getAllRecipe")
	@GET
	@Produces({ "application/json" })
	public String getAllRecipe() {
		return service.getAllRecipe();
	}
	
	@Path("/cycleRecipe/{title}")
	@GET
	@Produces({ "application/json" })
	public int cycleRecipe(@PathParam("dish") String dish) {
		return service.cycleRecipe(dish);
	}
	
	@Path("/getARecipe/{id}")
	@GET
	@Produces({ "application/json" })
	public String getARecipe(@PathParam("id") Long id) {
		return service.getARecipe(id);
	}

	@Path("/createRecipe")
	@POST
	@Produces({ "application/json" })
	public String addRecipe(String recipe) {
		return service.addRecipe(recipe);
	}

	@Path("/deleteRecipe/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteRecipe(@PathParam("id") Long id) {
		return service.deleteRecipe(id);
	}

	public void setService(RecipeService service) {
		this.service = service;
	}
}
