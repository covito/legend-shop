<%@page import="com.legendshop.util.ContextServiceLocator"%>
<%@page import="com.legendshop.business.service.impl.PayTypeServiceImpl"%>
<%@page import="com.legendshop.model.entity.PayType"%>
<%@page import="com.legendshop.model.entity.Sub"%>
<%@page import="com.legendshop.business.payment.alipay.util.AlipayNotify"%>
<%@page import="com.legendshop.business.dao.impl.SubDaoImpl"%>
<%@page import="com.legendshop.spi.constants.SubStatusEnum"%>
<%@page import="com.legendshop.spi.constants.OrderStatusEnum"%>

<%
	/* *
 功能：支付宝主动通知调用的页面（服务器异步通知页面）
 版本：3.1
 日期：2010-11-24
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
 该页面不能在本机电脑测试，请到服务器上做测试。请确保互联网可以访问该页面。
 该页面调试工具请使用写文本函数log_result，该函数已被默认开启
 WAIT_BUYER_PAY(表示买家已在支付宝交易管理中产生了交易记录，但没有付款);
 WAIT_SELLER_SEND_GOODS(表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货);
 WAIT_BUYER_CONFIRM_GOODS(表示卖家已经发了货，但买家还没有做确认收货的操作);
 TRADE_FINISHED(表示买家已经确认收货，这笔交易完成);
 该服务器异步通知页面面主要功能是：防止订单未更新。如果没有收到该页面打印的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
 
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	String order_no = request.getParameter("out_trade_no");	        //获取订单号
	System.out.println("notify order_no ==== " + order_no);
	if(order_no == null){
		return;
	}
	SubDaoImpl subDao = (SubDaoImpl)ContextServiceLocator.getInstance().getBean("subDao");
	Sub sub = subDao.getSubBySubNumber(order_no);
	PayTypeServiceImpl payTypeService = (PayTypeServiceImpl)ContextServiceLocator.getInstance().getBean("payTypeService");
	PayType payType = payTypeService.getPayTypeById(sub.getPayId());
	if(payType == null){
		return;
	}
	String key = payType.getValidateKey();
	//获取支付宝POST过来反馈信息
	Map params = new HashMap();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
	valueStr = (i == values.length - 1) ? valueStr + values[i]
	: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
		params.put(name, valueStr);
	}
	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	String trade_no = request.getParameter("trade_no");				//支付宝交易号

	String total_fee = request.getParameter("price");		        //获取总金额
	String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");//商品、订单名称
	String body = "";
	if(request.getParameter("body") != null){
		body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");//商品描述、订单备注、描述
	}
	String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
	String receive_name = "";//收货人姓名
	if(request.getParameter("receive_name") != null){
		receive_name = new String(request.getParameter("receive_name").getBytes("ISO-8859-1"), "UTF-8");
	}
	String receive_address = "";//收货人地址
	if(request.getParameter("receive_address") != null){
		receive_address = new String(request.getParameter("receive_address").getBytes("ISO-8859-1"), "UTF-8");
	}
	String receive_zip = "";//收货人邮编
	if(request.getParameter("receive_zip") != null){
		receive_zip = new String(request.getParameter("receive_zip").getBytes("ISO-8859-1"), "UTF-8");
	}
	String receive_phone = "";//收货人电话
	if(request.getParameter("receive_phone") != null){
		receive_phone = new String(request.getParameter("receive_phone").getBytes("ISO-8859-1"), "UTF-8");
	}
	String receive_mobile = "";//收货人手机
	if(request.getParameter("receive_mobile") != null){
		receive_mobile = new String(request.getParameter("receive_mobile").getBytes("ISO-8859-1"), "UTF-8");
	}
	System.out.println(new StringBuffer().append("receive_name=").append(receive_name).append(",buyer_email=").append(buyer_email)
	.append(",receive_zip=").append(receive_zip).append(",receive_phone=").append(receive_phone).append(",receive_mobile=")
	.append(receive_mobile).append("receive_address=").append(receive_address).toString());
	String trade_status = request.getParameter("trade_status");		//交易状态
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	
	if(trade_status != null && AlipayNotify.verify(params,key)){
	    //验证成功
		//请在这里加上商户的业务逻辑程序代码

		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
		if(trade_status.equals("WAIT_BUYER_PAY")){
		//该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
		
	//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
		//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		//如果有做过处理，不执行商户的业务程序
	
	out.println("success");	//请不要修改或删除
		} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
		//该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
		
	//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
		//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		//如果有做过处理，不执行商户的业务程序
	if(!OrderStatusEnum.PADYED.value().equals(sub.getStatus()) &&  !OrderStatusEnum.SUCCESS.value().equals(sub.getStatus())){
		subDao.saveSubHistory(sub,SubStatusEnum.CHANGE_STATUS.value());
		sub.setPayDate(new Date());
		sub.setStatus(OrderStatusEnum.PADYED.value());
		sub.setTradeNo(trade_no);
		subDao.update(sub);	
	}
	out.println("success");	//请不要修改或删除
		} else if(trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")){
		//该判断表示卖家已经发了货，但买家还没有做确认收货的操作
		
	//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
		//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		//如果有做过处理，不执行商户的业务程序
	System.out.println("卖家已经发货， order_no = " + order_no);
	out.println("success");	//请不要修改或删除
		} else if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
		//该判断表示买家已经确认收货，这笔交易完成
		
	//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
		//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		//如果有做过处理，不执行商户的业务程序
	System.out.println("订单完成， order_no = " + order_no);
	if(!OrderStatusEnum.SUCCESS.value().equals(sub.getStatus())){
		subDao.saveSubHistory(sub,SubStatusEnum.CHANGE_STATUS.value());
		sub.setPayDate(new Date());
		sub.setStatus(OrderStatusEnum.SUCCESS.value());
		sub.setSubCheck("Y");//完成订单的标识
		sub.setTradeNo(trade_no);
		//System.out.println(sub.getUserName() +" update sub " + OrderStatusEnum.SUCCESS.value());
		subDao.update(sub);
	}
	out.println("success");	//请不要修改或删除
		}
		else {
		    System.out.println("订单非正常完成， order_no = " + order_no);
	out.println("success");	//请不要修改或删除
		}
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

		//////////////////////////////////////////////////////////////////////////////////////////
	}else{//验证失败
		out.println("fail");
	}
%>
