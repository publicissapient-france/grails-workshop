package cocktail

import grails.converters.JSON

class IngredientController {

	static scaffold = true

	def hello = {
		render 'Hello'
	}

	def beginsWith() {
    	def allMatching = Ingredient.findByNameIlike("${params.term}%")
    	render allMatching as JSON
	}

}
