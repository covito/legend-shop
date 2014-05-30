<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/* *
 功能：付完款后跳转的页面（页面跳转同步通知页面）
 版本：3.1
 日期：2010-11-24
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 该页面可在本机电脑测试
 该页面称作“页面跳转同步通知页面”，是由支付宝服务器同步调用，可当作是支付完成后的提示信息页，如“您的某某某订单，多少金额已支付成功”。
 可放入HTML等美化页面的代码和订单交易完成后的数据库更新程序代码
 WAIT_SELLER_SEND_GOODS(表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货);
 TRADE_FINISHED(表示买家已经确认收货，这笔交易完成);
 
  *************注意******************
 如何判断该笔交易是通过即时到帐方式付款还是通过担保交易方式付款？
 
 担保交易的交易状态变化顺序是：等待买家付款→买家已付款，等待卖家发货→卖家已发货，等待买家收货→买家已收货，交易完成
 即时到帐的交易状态变化顺序是：等待买家付款→交易完成
 
 每当收到支付宝发来通知中，就可以获取到这笔交易的交易状态，并且商户需要利用商户订单号查询商户网站的订单数据，
 得到这笔订单在商户网站中的状态是什么，把商户网站中的订单状态与从支付宝通知中获取到的状态来做对比。
 如果商户网站中目前的状态是等待买家付款，而从支付宝通知获取来的状态是买家已付款，等待卖家发货，那么这笔交易买家是用担保交易方式付款的
 如果商户网站中目前的状态是等待买家付款，而从支付宝通知获取来的状态是交易完成，那么这笔交易买家是用即时到帐方式付款的
 **********************************
 
 //********************************
 * */
%>


<html>
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付成功客户端返回</title>
<style type="text/css">
.font_content{
    font-family:"宋体";
    font-size:14px;
    color:#FF6600;
}
.font_title{
    font-family:"宋体";
    font-size:16px;
    color:#FF0000;
    font-weight:bold;
}
table{
    border: 1px solid #CCCCCC;
}
</style>
  </head>
  <body>
<table align="center" width="450" cellpadding="5" cellspacing="0">
            <tr>
                <td align="center" class="font_title" colspan="2">
                    通知返回<a href="${DOMAIN_NAME}">[返回系统]</a></td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    支付宝交易号：</td>
                <td class="font_content" align="left">${payResult.tradeNo}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    订单号：</td>
                <td class="font_content" align="left">${payResult.outOrderNo}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    付款总金额：</td>
                <td class="font_content" align="left">${payResult.totalFee}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    商品标题：</td>
                <td class="font_content" align="left">${payResult.subject}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    商品描述：</td>
                <td class="font_content" align="left">${payResult.body}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    买家账号：</td>
                <td class="font_content" align="left">${payResult.buyerEmail}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    卖家姓名：</td>
                <td class="font_content" align="left">${payResult.sub.shopName}</td>
            </tr>
           <tr>
                <td class="font_content" align="right">
                    收货人姓名：</td>
                <td class="font_content" align="left">${payResult.sub.userName}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    收货人地址：</td>
                <td class="font_content" align="left">${payResult.sub.subAdds}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    收货人邮编：</td>
                <td class="font_content" align="left">${payResult.sub.subPost}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    收货人电话：</td>
                <td class="font_content" align="left">${payResult.sub.subTel} </td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    交易状态：</td>
                <td class="font_content" align="left">${payResult.tradeStatus}</td>
            </tr>
            <tr>
                <td class="font_content" align="right">
                    验证状态：</td>
                <td class="font_content" align="left">${payResult.verifyStatus}</td>
            </tr>
        </table>
  </body>
</html>
