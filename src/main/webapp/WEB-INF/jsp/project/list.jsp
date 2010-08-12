<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>
<div dojoType="dijit.TitlePane" style="width: 100%" title="List All Projects">
    <c:if test="${not empty projects}"> 
        <c:if test="${not empty param['integrityViolation']}">
    		<span class="errors">${param['integrityViolation']}</span>
    	</c:if>
        <table width="300px">
            <tr>
                <thead>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                   
                    <th/>
                    <th/>
                    <th/>
                </thead>
            </tr>
            <c:forEach items="${projects}" var="project">
                <tr>
                    <td>${project.id}</td>
                    <td>${fn:substring(project.name, 0, 10)}</td>
                    <td>${fn:substring(project.description, 0, 10)}</td>
                   
                    <td>
                        <form:form action="/ProjectManager/project/${project.id}" method="GET">
                            <input alt="Show Project" src="/ProjectManager/static/images/show.png" title="Show Project" type="image" value="Show Project"/>
                        </form:form>
                    </td>
                    <sec:authorize ifAllGranted="ROLE_ADMIN">
                    <td>
                        <form:form action="/ProjectManager/project/${project.id}/form" method="GET">
                            <input alt="Update Project" src="/ProjectManager/static/images/update.png" title="Update Project" type="image" value="Update Project"/>
                        </form:form>
                    </td>
                    <td>
                        <form:form action="/ProjectManager/project/${project.id}" method="DELETE">
                            <input alt="Delete Project" src="/ProjectManager/static/images/delete.png" title="Delete Project" type="image" value="Delete Project"/>
                        </form:form>
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty projects}">No Projects found.</c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
