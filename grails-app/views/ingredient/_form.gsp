<%@ page import="cocktail.Ingredient" %>

<head>
	<ckeditor:resources />
</head>

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
	<ckeditor:editor name="description" height="200px" width="80%" toolbar="Basic">
  		${ingredientInstance?.description}
	</ckeditor:editor>

</div>

