import cocktail.Ingredient
import cocktail.Glass

import cocktail.Account
import cocktail.Role
import cocktail.AccountRole

class BootStrap {
	
    def init = { servletContext ->

		environments {
        	development {
				def roles = ['ROLE_USER', 'ROLE_ADMIN'].inject([:]) { roleMap, authority ->
					roleMap << [ (authority) : createRole(authority) ]
				}
				
				def accounts = ['akinsella', 'amaury'].collect { username ->
					createAccount(username, username)
				}
				
				def stdAccountRoles = ['ROLE_USER']

				accounts.each { account ->
					stdAccountRoles.each { role ->
						createAccountRole(account, roles[role])
					}
				}

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

	private createAccount(String username, String password) {
		Account account = new Account( username: username,
									   password: password,
									   enabled: true,
									   accountLocked: false,
									   accountExpired: false,
									   passwordExpired: false).save(flush: true)

		if (account.hasErrors()) {
			account.errors.each { println it }
		}
		
		account
	}
	
	private createRole(String authority) {
		Role role = new Role( authority: authority ).save(flush: true)

		if (role.hasErrors()) {
			role.errors.each { println it }
		}
		
		role
	}
	
	private createAccountRole(Account account, Role role) {
		AccountRole accountRole = AccountRole.create( account, role, true)

		if (accountRole?.hasErrors()) {
			accountRole.errors.each { println it }
		}
		
		accountRole
	}

}
