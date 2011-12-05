package cocktail

class Recipe {

	String name
    String description

    Glass glass

	static searchable = true

    static hasMany = [ingredients: Ingredient]

    static constraints = {
    name blank: false
        description blank: false, size: 1..255
        glass nullable: false
    }

}
