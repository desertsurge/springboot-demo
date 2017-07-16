package com.liutao.demo.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.HtmlUtils;

import com.liutao.demo.util.editor.DateEditor;

@Configuration
public class BaseController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : HtmlUtils.htmlEscape(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? HtmlUtils.htmlUnescape(value.toString()) : "";
			}
		});
		// Date 类型转换
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
