package cocktail

class HomeController {

    def index() {
		redirect(controller:"search", action:"index")	
	}
}
