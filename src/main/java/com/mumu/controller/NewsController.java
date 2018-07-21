package com.mumu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumu.service.NewsService;
import com.mumu.utils.Result;

/**
 * 首页新闻的controller
 * @author mumu
 *
 */
@Controller
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/getNews/{newsId}")
	@ResponseBody
	public Result getNewsById(Integer newsId) {
		System.out.println("newsId"+newsId);
		return newsService.getNewsById(newsId);
	}
	
	@RequestMapping("/getNews")
	@ResponseBody
	public Result getNews( @RequestParam(value="page",required=false,defaultValue="0") 
	Integer page,  @RequestParam(value="rows",required=false,defaultValue="1") Integer rows) {
		return newsService.getNewsList(page, rows);
	}
	
}
