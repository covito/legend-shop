<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建热门商品</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
    	    title: {
                required: true,
                minlength: 5
            },
             seq: {
              number: true
            },
            msg: "required"
        },
        messages: {
        	title: {
                required: "请输入标题",
                minlength: "请认真输入标题，不能少于5个字符"
            },
            seq: {
                number: "请输入数字"
            },
            msg: {
                required: "请输入链接地址"
            }
        }
    });
 			highlightTableRows("col1");
          //斑马条纹
     	   $("#col1 tr:nth-child(even)").addClass("even");	

});
</script>
</head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/hotsearch/save" method="post" id="form1">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
       <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="<ls:url address='/admin/hotsearch/query'/>">热门商品管理</a> &raquo; 创建热门商品</th></tr>
	    </thead>
	    </table>
            <table  align="center" class="${tableclass}" id="col1" style="width: 100%">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建热门商品
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td width="200px">
          <div align="right">商品标题(用于全文检索)<font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="title" id="title" value="${bean.title}" size="50"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">查询内容<font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="msg" id="msg" value="${bean.msg}" size="50"/>
        </td>
      </tr>
       <tr>
        <td>
          <div align="right">商品类型</div>
       </td>
        <td>
	            <lb:sortOption id="sort"   type="P"  selectedValue="${bean.sort}"/>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">状态<font color="ff0000">*</font></div>
       </td>
        <td>
				<select id="status" name="status">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="ONOFF_STATUS" selectedValue="${bean.status}"/>
	            </select>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">次序</div>
       </td>
        <td>
           <input type="text" name="seq" id="seq" value="${bean.seq}"   maxlength="5" size="5"/>
        </td>
      </tr>
       <tr>
                    <td colspan="2">
                        <div align="center">
                        <auth:auth ifAnyGranted="FA_SHOP">
                            <input type="submit" value="提交" />
                         </auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address='/admin/hotsearch/query'/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

