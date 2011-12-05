<%@ page import="cocktail.Glass" %>

<head>
	<ckeditor:resources />
</head>


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
	<ckeditor:editor name="description" height="200px" width="80%" toolbar="Basic">
  		${glassInstance?.description}
	</ckeditor:editor>
</div>

