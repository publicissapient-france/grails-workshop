<head>
    <meta name="layout" content="main">
</head>
<body>
	<g:form action="list" method="post">
  		Search for : <g:textField name="query" value="${query}"/>
		<g:actionSubmit value="Envoyer" action="go"/>
	</g:form>

	<g:each in="${results}" var="recipe">
		<g:link controller="recipe" action="show" id="${recipe.id}">
			${fieldValue(bean: recipe, field: "name")}
		</g:link>
	</g:each>
</body>
