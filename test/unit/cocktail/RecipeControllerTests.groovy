package cocktail



import org.junit.*
import grails.test.mixin.*

@TestFor(RecipeController)
@Mock(Recipe)
class RecipeControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/recipe/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.recipeInstanceList.size() == 0
        assert model.recipeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.recipeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.recipeInstance != null
        assert view == '/recipe/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/recipe/show/1'
        assert controller.flash.message != null
        assert Recipe.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/recipe/list'


        populateValidParams(params)
        def recipe = new Recipe(params)

        assert recipe.save() != null

        params.id = recipe.id

        def model = controller.show()

        assert model.recipeInstance == recipe
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/recipe/list'


        populateValidParams(params)
        def recipe = new Recipe(params)

        assert recipe.save() != null

        params.id = recipe.id

        def model = controller.edit()

        assert model.recipeInstance == recipe
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/recipe/list'

        response.reset()


        populateValidParams(params)
        def recipe = new Recipe(params)

        assert recipe.save() != null

        // test invalid parameters in update
        params.id = recipe.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/recipe/edit"
        assert model.recipeInstance != null

        recipe.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/recipe/show/$recipe.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        recipe.clearErrors()

        populateValidParams(params)
        params.id = recipe.id
        params.version = -1
        controller.update()

        assert view == "/recipe/edit"
        assert model.recipeInstance != null
        assert model.recipeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/recipe/list'

        response.reset()

        populateValidParams(params)
        def recipe = new Recipe(params)

        assert recipe.save() != null
        assert Recipe.count() == 1

        params.id = recipe.id

        controller.delete()

        assert Recipe.count() == 0
        assert Recipe.get(recipe.id) == null
        assert response.redirectedUrl == '/recipe/list'
    }
}
