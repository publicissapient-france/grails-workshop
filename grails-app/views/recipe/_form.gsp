<%@ page import="cocktail.Recipe" %>

<head>
	<ckeditor:resources />
	
	<style>
		.ing {
			margin-left: 5px;
		}
		.ing-button {
			border: 1px dotted #ccc;
		}
	
	</style>
</head>


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
	<ckeditor:editor name="description" height="200px" width="80%" toolbar="Basic">
		${recipeInstance?.description}
	</ckeditor:editor>
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
	<g:textField id="newIngredient" name="newIngredient"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'ingredients', 'error')} ">
	<label for="ingredients2">&nbsp;</label>
	<div id="selectedIngredients" style="display: inline; padding-left: 10px;"></div>
</div>

<r:script>
	$(document).ready(function() {
	  $('#newIngredient').autocomplete({

	    source: '${createLink(controller:'ingredient', action: 'beginsWith')}',
	    
	    minLength: 2,

	    success: function(data) {
	      response({label: 'label', value: 'value'});
	    },

	    select: function(event, ui) {

	      var selectedDiv = $('#selectedIngredients');

	      selectedDiv.append('<input type="button" class="ing ing-button" id="ing-' + ui.item.value + '" value="-"/>');

	      $('#ing-' + ui.item.value).click(function() {
	        $('.' + this.id).remove();
	        $('#' + this.id).remove();
	      });

	      selectedDiv.append('<input type="hidden" class="ing ing-' + ui.item.value + '" name="ingredients" value="' + ui.item.value + '"/>');
	      selectedDiv.append('<span class="ing ing-' + ui.item.value + '">' + ui.item.label + '</span>');

	      $('#newIngredient').val('');

	      event.preventDefault();
	    },

	    focus: function(event, ui) {
	      $('#newIngredient').val(ui.item.label);
	      event.preventDefault();
	    }
	  });
	});
</r:script>
