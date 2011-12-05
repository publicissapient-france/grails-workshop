<%@ page import="cocktail.Glass" %>



<div class="fieldcontain ${hasErrors(bean: glassInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="glass.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${glassInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: glassInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="glass.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" required="" value="${glassInstance?.description}"/>
</div>

