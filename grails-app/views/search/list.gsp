<head>
    <meta name="layout" content="main">
    <style>
    	.search-panel {
    		padding: 20px;
    		padding-left: 50px;
    	}
    	.search-form {
    	}
    	.result-list {
    		padding-top: 30px;
    		padding-left: 30px;
    	}
    </style>
</head>

<body>

	<div class="search-panel">
		<div class="search-form">
			<g:form action="list" method="post">
	  			Search for : <g:textField name="query" value="${query}"/>
				<g:actionSubmit value="Envoyer" action="go"/>
			</g:form>
		</div>
		
		<div class="result-list">
			<g:if test="${!results}">
				No results for search.
			</g:if>
			<g:else>
				<ul>
					<g:each in="${results}" var="recipe">
						<li>
							<g:link controller="recipe" action="show" id="${recipe.id}">
								${fieldValue(bean: recipe, field: "name")}
							</g:link>
						</li>
					</g:each>
				</ul>
			</g:else>
		</div>
	</div>
	
</body>
