package com.mumu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumu.bean.Content;
import com.mumu.service.ContentService;
import com.mumu.utils.EasyUIDataGridResult;
import com.mumu.utils.Result;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	/**
	 * 前台获取首页轮播内容
	 * @return
	 */
	@RequestMapping("/getContents")
	@ResponseBody
	public Result getContentsList() {
		return contentService.getContentList();
	}
	
	
	@RequestMapping("/content/list")
	@ResponseBody
	public EasyUIDataGridResult listContent(Integer page, Integer rows) {
		return contentService.listContent( page, rows);
	}
	
	@RequestMapping("/content/delete")
	@ResponseBody
	public Result deleteContent(int ids[]) {
		return contentService.deleteContent(ids);
	}
	
	
	@RequestMapping("/content/edit")
	@ResponseBody
	public Result editContent(Content content) {
		return contentService.editContent(content);
	}
	
	
	@RequestMapping("/content/add")
	@ResponseBody
	public Result addContent(Content content) {
		return contentService.addContent(content);
	}
}
