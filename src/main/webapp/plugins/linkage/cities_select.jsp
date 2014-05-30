<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
 <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
<script type="text/javascript">

    $(document).ready(function() {
            var provinceid = $("#provinceid");
            var cityid = $("#cityid");
            var areaid = $("#areaid");
   			// ajaxAddOptions("${pageContext.request.contextPath}/common/loadProvinces", provinceid,true);
   			initSelect(provinceid,cityid,areaid,'${param.provinceid}',  '${param.cityid}', '${param.areaid}');
			$("#provinceid").change(function() {
					changeProvince(cityid,areaid,$(this).children('option:selected').val());
				});
				
			$(cityid).change(function() {
						changeCities(areaid,$(this).children('option:selected').val());
				});
		});
</script>
<input type="hidden"  id="contextPath"  name="contextPath" value="<%= request.getContextPath()%>" /> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/linkage.js"></script>