package cocktail



import org.junit.*
import grails.test.mixin.*

@TestFor(GlassController)
@Mock(Glass)
class GlassControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/glass/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.glassInstanceList.size() == 0
        assert model.glassInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.glassInstance != null
    }

    void testSave() {
        controller.save()

        assert model.glassInstance != null
        assert view == '/glass/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/glass/show/1'
        assert controller.flash.message != null
        assert Glass.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/glass/list'


        populateValidParams(params)
        def glass = new Glass(params)

        assert glass.save() != null

        params.id = glass.id

        def model = controller.show()

        assert model.glassInstance == glass
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/glass/list'


        populateValidParams(params)
        def glass = new Glass(params)

        assert glass.save() != null

        params.id = glass.id

        def model = controller.edit()

        assert model.glassInstance == glass
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/glass/list'

        response.reset()


        populateValidParams(params)
        def glass = new Glass(params)

        assert glass.save() != null

        // test invalid parameters in update
        params.id = glass.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/glass/edit"
        assert model.glassInstance != null

        glass.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/glass/show/$glass.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        glass.clearErrors()

        populateValidParams(params)
        params.id = glass.id
        params.version = -1
        controller.update()

        assert view == "/glass/edit"
        assert model.glassInstance != null
        assert model.glassInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/glass/list'

        response.reset()

        populateValidParams(params)
        def glass = new Glass(params)

        assert glass.save() != null
        assert Glass.count() == 1

        params.id = glass.id

        controller.delete()

        assert Glass.count() == 0
        assert Glass.get(glass.id) == null
        assert response.redirectedUrl == '/glass/list'
    }
}
