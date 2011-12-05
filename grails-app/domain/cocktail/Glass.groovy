package cocktail

class Glass {
    String name
    String description
    static constraints = {
        name blank: false
        description blank: false, size: 1..255
    }
}
