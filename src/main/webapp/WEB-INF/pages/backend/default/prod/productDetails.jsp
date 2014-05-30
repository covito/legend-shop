<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<div class="pageFormContent nowrap">
	<FCK:editor instanceName="content" height="600px"
		basePath="/plugins/fckeditor">
		<jsp:attribute name="value">${prod.content}</jsp:attribute>
	</FCK:editor>
</div>
