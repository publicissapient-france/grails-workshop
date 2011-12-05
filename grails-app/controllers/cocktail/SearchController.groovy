package cocktail

class SearchController {

    def index() { }

	def searchableService // injection auto

	def go() {
      def searchResult = searchableService.search(params.query as String)

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

      render (view:'list', model: [query: params.query, results: resList.flatten()])
    }
}
