package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.page.TempletManager;
import com.legendshop.model.template.Templet;
import com.legendshop.model.template.TempletEntity;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.JSONUtil;
import com.legendshop.util.ServiceLocatorIF;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TempletController extends BaseController {

	@Autowired
	private TempletManager templetManager;

	@RequestMapping({ "/common/loadTempletsEntity" })
	public String loadTempletsEntity(HttpServletRequest request,
			HttpServletResponse response) {
		TempletEntity templet = new TempletEntity();
		templet.setFrontEndTempletList(CommonServiceUtil
				.convertKeyValueEntity(this.templetManager
						.getFrontEndTempletList()));
		templet.setBackEndTempletList(CommonServiceUtil
				.convertKeyValueEntity(this.templetManager
						.getBackEndTempletList()));

		Map frontEndTempletMap = this.templetManager.getFrontEndtTempletMap();
		for (Iterator localIterator = frontEndTempletMap.keySet().iterator(); localIterator
				.hasNext();) {
			String templetId = (String) localIterator.next();
			Templet newTemplet = getFrontEndTemplet(templetId);
			convertTemplet(templetId, newTemplet);
			templet.addFrontEndTempletMap(newTemplet);
		}

		Map backEndTempletMap = this.templetManager.getBackEndTempletMap();
		for (Iterator inter = backEndTempletMap.keySet().iterator(); inter
				.hasNext();) {
			String templetId = (String) inter.next();
			Templet newTemplet = getBackEndTemplet(templetId);
			convertTemplet(templetId, newTemplet);
			templet.addBackEndTempletMap(newTemplet);
		}

		request.setAttribute("templetEntity", JSONUtil.getJson(templet));
		return "/pages/backend/default/linkage_templet";
	}

	private void convertTemplet(String templetId, Templet newTemplet) {
		newTemplet.setId(templetId);
		newTemplet.setStyles(CommonServiceUtil.convertKeyValueEntity(newTemplet
				.getStyles()));
		newTemplet.setLanguages(CommonServiceUtil
				.convertKeyValueEntity(newTemplet.getLanguages()));
	}

	private Templet getFrontEndTemplet(String templetId) {
		return ((Templet) ContextServiceLocator.getInstance().getBean(
				this.templetManager.getFrontEndTempletById(templetId)));
	}

	private Templet getBackEndTemplet(String templetId) {
		return ((Templet) ContextServiceLocator.getInstance().getBean(
				this.templetManager.getBackEndTempletById(templetId)));
	}
}