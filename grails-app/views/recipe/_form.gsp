<%@ page import="cocktail.Recipe" %>



<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="recipe.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${recipeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="recipe.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" required="" value="${recipeInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'glass', 'error')} required">
	<label for="glass">
		<g:message code="recipe.glass.label" default="Glass" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="glass" name="glass.id" from="${cocktail.Glass.list()}" optionKey="id" optionValue="name" required="" value="${recipeInstance?.glass?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'ingredients', 'error')} ">
	<label for="ingredients">
		<g:message code="recipe.ingredients.label" default="Ingredients" />
		
	</label>
	<g:select name="ingredients" from="${cocktail.Ingredient.list()}" multiple="multiple" optionKey="id" size="5" value="${recipeInstance?.ingredients*.id}" class="many-to-many"/>
</div>

