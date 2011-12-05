<%@ page import="cocktail.Ingredient" %>



<div class="fieldcontain ${hasErrors(bean: ingredientInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="ingredient.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${ingredientInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ingredientInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="ingredient.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" required="" value="${ingredientInstance?.description}"/>
</div>

