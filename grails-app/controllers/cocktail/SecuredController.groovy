package cocktail

import grails.plugins.springsecurity.Secured

class SecuredController {
	
	   def index = {
	      render 'Anyone can see this. Check if you have rights on action: admin or secured.'
	   }
	   
	   @Secured(['ROLE_ADMIN'])
	   def admin = {
		  render 'You have ROLE_ADMIN'
	   }

	   @Secured(['ROLE_ADMIN', 'ROLE_USER'])
	   def secured = {
	      render 'You have ROLE_ADMIN or ROLE_USER'
	   }
	 
	
}
