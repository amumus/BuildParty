package com.mumu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 页面跳转Controller
 * @author mumu
 *
 */
@Controller
public class PageController {
	/**
	 * 如果请求为/，就跳转到index页面，不过web.xml可以设置起始页
	 * @return
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	/**
	 * xx请求就跳转到xx.jsp页面，比如/index就跳到index.jsp
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
