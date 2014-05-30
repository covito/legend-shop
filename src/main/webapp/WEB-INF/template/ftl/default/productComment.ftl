<table width="100%" class="tables">
	<tr>
	<td style="background-color: #ECECEC;font-weight: bold;"><table width="100%"><tr><td width="80%">评论内容</td><td>评价人</td></tr><tr></table></td>
	<#list productCommentList as comment>
	<tr>
	<td><table width="100%"><tr >
	<td align="left" style="width: 80%">
		<div><span style="color: #8A1F11">&nbsp;[${comment.addtime?string("yyyy-MM-dd HH:mm:ss")}]</span>  &nbsp; ${comment.content} </div>
		<#if comment.replyContent??>
		 <div style="margin-left: 10px">[回复]&nbsp;&nbsp; ${comment.replyContent?default("")}</div>
		<#else>
		</#if>
	</td>
	<td>
		${comment.userName}
		<#if owner??>
			[<a target="_blank" href="admin/productcomment/load/${comment.id}">回复</a>]
		</#if>
	</td>
	</tr></table>
	</tr>
	</#list>
	<tr><td align="right">一共${totalProductComment}条评论 ${toolBar?default("")}</td></tr>
</table>
