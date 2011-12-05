
<%@ page import="cocktail.Recipe" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recipe.label', default: 'Recipe')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-recipe" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-recipe" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list recipe">
			
				<g:if test="${recipeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="recipe.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${recipeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recipeInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="recipe.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${recipeInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recipeInstance?.glass}">
				<li class="fieldcontain">
					<span id="glass-label" class="property-label"><g:message code="recipe.glass.label" default="Glass" /></span>
					
						<span class="property-value" aria-labelledby="glass-label"><g:link controller="glass" action="show" id="${recipeInstance?.glass?.id}">${recipeInstance?.glass?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${recipeInstance?.ingredients}">
				<li class="fieldcontain">
					<span id="ingredients-label" class="property-label"><g:message code="recipe.ingredients.label" default="Ingredients" /></span>
					
						<g:each in="${recipeInstance.ingredients}" var="i">
						<span class="property-value" aria-labelledby="ingredients-label"><g:link controller="ingredient" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${recipeInstance?.id}" />
					<g:link class="edit" action="edit" id="${recipeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
