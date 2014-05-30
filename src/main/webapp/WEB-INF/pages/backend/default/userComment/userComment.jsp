<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/WEB-INF/pages/common/back-common-win.jsp"%>
<%@page import="com.legendshop.core.UserManager"%>
<html>
<head>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
<title>回复用户消息</title>
<script type="text/javascript">
$(document).ready(function() {
		//斑马条纹
     	 $("#col1 tr:nth-child(even)").addClass("even");
});
</script>
</head>
<body>
	<table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/userComment/query?status=0">消息管理</a> &raquo; 回复用户消息</th></tr>
    </thead>
    </table>
  <table class="${tableclass}" style="width: 100%" id="col1">
  <thead>
    <tr>
      <th colspan="2"><center>回复用户消息</center></th>
    </tr>
    </thead>
    <tr>
      <td width="20%"><div align="right">评论内容  <input type="hidden" id="id" value="${comment.id}" name="id"/></div></td>
      <td width="80%">
          ${comment.content}
      </td>
    </tr>
     <tr>
      <td width="20%"><div align="right">回复<font color="#ff0000">*</font></div></td>
      <td width="80%">
      		<textarea rows="6" id="answer" name="answer"></textarea>
      </td>
    </tr>  
    <tr>
      <td colspan="2">
                  <div align="center">
                        <auth:auth ifAnyGranted="FA_SHOP">
                            <input type="button" value="回复" onclick="javascript:answerWord();"/>
                         </auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="javasrcript:closeWin();" />
                        </div>
                      </td>
    </tr>
  </table>
  <script type="text/javascript">
  function answerWord() {
		var id = $("#id").val();
		var answer = $("#answer").val();
		if(answer == null || answer.length <5){
				alert("请认真填写回复内容，不能少于5个字");
				return;
			}
		$.post("${pageContext.request.contextPath}/admin/userComment/answerWord" , 
		 {"id": id, "answer": answer},
	        function(retData) {
		          if(retData == null){
						alert("回复成功");
					    window.opener.location.reload(); //关键是这句：刷新父窗口
					    window.close();
		              }else{
		            	 alert("回复失败: " + retData);
		              }	     
			},'json');
		}
	
	function closeWin(){
	    window.opener.location.reload(); //关键是这句：刷新父窗口
	    window.close();
	}
  </script>
 </body>
</html>