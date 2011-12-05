package cocktail

import org.springframework.dao.DataIntegrityViolationException

class RecipeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [recipeInstanceList: Recipe.list(params), recipeInstanceTotal: Recipe.count()]
    }

    def create() {
        [recipeInstance: new Recipe(params)]
    }

    def save() {
        def recipeInstance = new Recipe(params)
        if (!recipeInstance.save(flush: true)) {
            render(view: "create", model: [recipeInstance: recipeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'recipe.label', default: 'Recipe'), recipeInstance.id])
        redirect(action: "show", id: recipeInstance.id)
    }

    def show() {
        def recipeInstance = Recipe.get(params.id)
        if (!recipeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])
            redirect(action: "list")
            return
        }

        [recipeInstance: recipeInstance]
    }

    def edit() {
        def recipeInstance = Recipe.get(params.id)
        if (!recipeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])
            redirect(action: "list")
            return
        }

        [recipeInstance: recipeInstance]
    }

    def update() {
        def recipeInstance = Recipe.get(params.id)
        if (!recipeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (recipeInstance.version > version) {
                recipeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'recipe.label', default: 'Recipe')] as Object[],
                          "Another user has updated this Recipe while you were editing")
                render(view: "edit", model: [recipeInstance: recipeInstance])
                return
            }
        }

        recipeInstance.properties = params

        if (!recipeInstance.save(flush: true)) {
            render(view: "edit", model: [recipeInstance: recipeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'recipe.label', default: 'Recipe'), recipeInstance.id])
        redirect(action: "show", id: recipeInstance.id)
    }

    def delete() {
        def recipeInstance = Recipe.get(params.id)
        if (!recipeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])
            redirect(action: "list")
            return
        }

        try {
            recipeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
