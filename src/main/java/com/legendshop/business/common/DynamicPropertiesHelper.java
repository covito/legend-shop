package com.legendshop.business.common;

import com.legendshop.model.dynamic.Item;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.dynamic.ModelType;
import com.legendshop.util.AppUtils;
import java.util.Iterator;
import java.util.List;

public class DynamicPropertiesHelper {
	public String gerenateHTML(List<Model> modelList) {
		StringBuffer sb = new StringBuffer();
		if (AppUtils.isNotBlank(modelList))
			for (Iterator localIterator = modelList.iterator(); localIterator
					.hasNext();) {
				Model model = (Model) localIterator.next();
				if (ModelType.Select.name().equals(model.getType())) {
					generateSelect(model, sb);
				}
				if (ModelType.Text.name().equals(model.getType())) {
					generateText(model, sb);
				}
				if (ModelType.CheckBox.name().equals(model.getType())) {
					generateCheckBox(model, sb);
				}
				if (!(ModelType.Radio.name().equals(model.getType())))
					generateRadio(model, sb);
			}

		return sb.toString();
	}

	public StringBuffer generateSelect(Model model, StringBuffer sb) {
		if (ModelType.Select.name().equals(model.getType())) {
			Item[] arrayOfItem;
			sb.append(model.getId()).append("&nbsp;<select id='")
					.append(model.getId()).append("' class='attrselect'")
					.append(" name='").append(model.getId()).append("'>");
			sb.append("<option value=''>请选择</option>");
			int j = (arrayOfItem = model.getItems()).length;
			for (int i = 0; i < j; ++i) {
				Item item = arrayOfItem[i];
				sb.append("<option value='").append(item.getKey()).append("'>")
						.append(item.getKey()).append("</option>");
			}
			sb.append("</select><br>");
		}
		return sb;
	}

	public StringBuffer generateText(Model model, StringBuffer sb) {
		if (ModelType.Text.name().equals(model.getType())) {
			if (AppUtils.isNotBlank(model.getItems()))
				sb.append("<tr><th>").append(model.getId()).append("</th><td>")
						.append(model.getItems()[0].getValue())
						.append("</td></tr>");
			else
				sb.append("<div>").append(model.getId()).append("<input id='")
						.append(model.getId()).append("' class='attrtext'")
						.append(" name='").append(model.getId())
						.append(" value='").append("value").append("'/></div>");

		}

		return sb;
	}

	public StringBuffer generateRadio(Model model, StringBuffer sb) {
		if (ModelType.Radio.name().equals(model.getType())) {
			Item[] arrayOfItem;
			sb.append(model.getId()).append("&nbsp;");
			int j = (arrayOfItem = model.getItems()).length;
			for (int i = 0; i < j; ++i) {
				Item item = arrayOfItem[i];
				sb.append(item.getKey()).append("<input type='radio' id='")
						.append(model.getId()).append("' class='attrradio'")
						.append(" name='").append(model.getId())
						.append("' value='").append(item.getKey())
						.append("'/>&nbsp;");
			}
			sb.append("<br>");
		}
		return sb;
	}

	public StringBuffer generateCheckBox(Model model, StringBuffer sb) {
		if (ModelType.CheckBox.name().equals(model.getType())) {
			Item[] arrayOfItem;
			sb.append(model.getId()).append("&nbsp;");
			int j = (arrayOfItem = model.getItems()).length;
			for (int i = 0; i < j; ++i) {
				Item item = arrayOfItem[i];
				sb.append(item.getKey()).append("<input type='checkbox' id='")
						.append(item.getKey()).append("' class='attrchx'")
						.append("' name='").append(model.getId())
						.append("'/>&nbsp;");
			}
			sb.append("<br>");
		}
		return sb;
	}
}