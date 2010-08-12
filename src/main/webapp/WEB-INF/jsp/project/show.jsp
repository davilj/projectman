<jsp:directive.include file="/WEB-INF/jsp/includes.jsp" />
<jsp:directive.include file="/WEB-INF/jsp/header.jsp" />
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>
<div dojoType="dijit.TitlePane" style="width: 100%" title="Show Project">
<c:if test="${not empty project}">
	<div><label for="name">Name:</label>
	<div id="name">${project.name}</div>
	</div>
	<br />

	<div><label for="description">Description:</label>
	<div id="description">${project.description}</div>
	</div>
	<br />
	
	<c:forEach items="${project.comments}" var="comment">
    	<div>${comment.text}</div>            
    </c:forEach>
	
	<form:form action="/ProjectManager/project/${project.id}/comment" method="POST" modelAttribute="comment">

		<div><label for="comment">Comment:</label> <form:textarea
			cssStyle="width:250px;height:15em" path="text" /> 
			<form:errors path="text" cssClass="errors" /> <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "text", widgetType: "dijit.form.Textarea", widgetAttrs: {value: ""}})); </script>
		<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "text", widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Enter Description", required : false}})); </script>
		</div>


		<div class="submit"><script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'proceed_project_comment', event:'onclick'}));</script>
		<input id="proceed_project_comment" type="submit" value="Save" /></div>

	</form:form>
</c:if> 
<c:if test="${empty project}">No projects found with this id.</c:if></div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp" />
