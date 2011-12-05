import cocktail.Ingredient
import cocktail.Glass

class BootStrap {

    def init = { servletContext ->

		environments {
        	development {
          		[ Tumbler: 'Pour les long drinks.', Pilsner: 'Verre à demi' ].each { key, val ->
            		new Glass(name: key, description: val).save()
          		}

          		[ Rhum: 'Bacardi', 'Citron vert': 'Agrume' ].each { key, val ->
            		new Ingredient(name: key, description: val).save()
          		}
       		}
     	}

    }

    def destroy = {

    }

}
