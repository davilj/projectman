<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>

<div dojoType="dijit.TitlePane" style="width: 100%" title="Add a comment to a Project">
	${project.id}, ${fn:substring(project.name, 0, 10)}, ${fn:substring(project.description, 0, 10)}

    <form:form action="/ProjectManager/project/comment" method="POST" modelAttribute="comment
            <label for="description">Description:</label>
            <form:textarea cssStyle="width:250px;height:15em" path="description"/>
            <form:errors path="description" cssClass="errors"/>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "description", widgetType: "dijit.form.Textarea", widgetAttrs: {value: ""}})); </script>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "description", widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Enter Description", required : false}})); </script>
		</div>
        
		<br/>
		<div class="submit">
            <script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'proceed_project_comment_add', event:'onclick'}));</script>
            <input id="proceed_project_comment_add" type="submit" value="Save"/>
        </div>

    </form:form>
</div>


<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
