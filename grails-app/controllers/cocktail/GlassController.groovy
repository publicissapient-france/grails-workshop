package cocktail

import org.springframework.dao.DataIntegrityViolationException

class GlassController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [glassInstanceList: Glass.list(params), glassInstanceTotal: Glass.count()]
    }

    def create() {
        [glassInstance: new Glass(params)]
    }

    def save() {
        def glassInstance = new Glass(params)
        if (!glassInstance.save(flush: true)) {
            render(view: "create", model: [glassInstance: glassInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'glass.label', default: 'Glass'), glassInstance.id])
        redirect(action: "show", id: glassInstance.id)
    }

    def show() {
        def glassInstance = Glass.get(params.id)
        if (!glassInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'glass.label', default: 'Glass'), params.id])
            redirect(action: "list")
            return
        }

        [glassInstance: glassInstance]
    }

    def edit() {
        def glassInstance = Glass.get(params.id)
        if (!glassInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'glass.label', default: 'Glass'), params.id])
            redirect(action: "list")
            return
        }

        [glassInstance: glassInstance]
    }

    def update() {
        def glassInstance = Glass.get(params.id)
        if (!glassInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'glass.label', default: 'Glass'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (glassInstance.version > version) {
                glassInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'glass.label', default: 'Glass')] as Object[],
                          "Another user has updated this Glass while you were editing")
                render(view: "edit", model: [glassInstance: glassInstance])
                return
            }
        }

        glassInstance.properties = params

        if (!glassInstance.save(flush: true)) {
            render(view: "edit", model: [glassInstance: glassInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'glass.label', default: 'Glass'), glassInstance.id])
        redirect(action: "show", id: glassInstance.id)
    }

    def delete() {
        def glassInstance = Glass.get(params.id)
        if (!glassInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'glass.label', default: 'Glass'), params.id])
            redirect(action: "list")
            return
        }

        try {
            glassInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'glass.label', default: 'Glass'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'glass.label', default: 'Glass'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
