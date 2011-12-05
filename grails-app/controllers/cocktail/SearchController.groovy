package cocktail

class SearchController {

	def searchableService // injection auto
	
	def index() {
		render(view:'list')
	}

	def list() { 
		render(view:'list')
	}

	def go() {
		
	  String searchQuery = params.query
	  
	  if (!searchQuery) {
		  flash.message = '"query" param cannot be empty'
	      redirect (action: 'list')
	  }
	  else {
	      def searchResult = searchableService.search(searchQuery)
	
	      def resList = [] as Set
	      searchResult.results.each { res ->
	        switch (res) {
	          case Ingredient :
	            resList << Recipe.withCriteria {
	              ingredients {
	                eq 'id', res.id
	              }
	            }
	            break
	
	          case Recipe :
	            resList << res
	            break
	        }
	      }
	
	      render (view:'list', model: [query: searchQuery, results: resList.flatten()])
	  }
	  
    }

}
