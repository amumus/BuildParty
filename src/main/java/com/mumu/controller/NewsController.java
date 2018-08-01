package com.mumu.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumu.bean.News;
import com.mumu.service.NewsService;
import com.mumu.utils.EasyUIDataGridResult;
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
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}
	
	/**
	 * 前台根据id来获取新闻的详情内容
	 * @param newsId
	 * @return
	 */
	@RequestMapping("/getNews/{newsId}")
	@ResponseBody
	public Result getNewsById(@PathVariable Integer newsId) {
		return newsService.getNewsById(newsId);
	}
	/**
	 * 前台获取新闻列表，不包含新闻内容
	 * @param page 第几页，从0开始
	 * @param rows 每页行数
	 * @return
	 */
	@RequestMapping("/getNews")
	@ResponseBody
	public Result getNews( @RequestParam(value="page",required=false,defaultValue="0") 
	Integer page,  @RequestParam(value="rows",required=false,defaultValue="5") Integer rows) {
		return newsService.getNews(page, rows);
	}
	
	
	/**
	 * 后台管理添加新闻
	 * @param title
	 * @param image
	 * @param content
	 * @return
	 */
	@RequestMapping("/news/add")
	@ResponseBody
	public Result addNews(String title,String image,String content) {
		return newsService.addNews(title, image, content);
	}
	/**
	 * 后台管理修改新闻
	 * @param title
	 * @param image
	 * @param content
	 * @return
	 */
	@RequestMapping("/news/edit")
	@ResponseBody
	public Result editNews(News news) {
		return newsService.editNews(news);
	}
	/**
	 * 后台删除新闻
	 * @param ids
	 * @return
	 */
	@RequestMapping("/news/delete")
	@ResponseBody
	public Result deleteNews(int ids[]) {
		return newsService.deleteNews(ids);
	}
	/**
	 *  后台获取新闻列表
	 * @param page 第几页，从零开始
	 * @param rows 每页几行
	 * @param newsId 搜索新闻id
	 * @param title 搜索标题
	 * @param startCreated 搜索该时间之后的新闻
	 * @param endCreated 搜索该时间之前的新闻
	 * @return
	 */
	@RequestMapping("/news/list")
	@ResponseBody
	public EasyUIDataGridResult getNewsList(Integer page, Integer rows,
			@RequestParam(defaultValue="-1",required=false)Integer newsId,
			@RequestParam(required=false)String title,
			@RequestParam(required=false)Date startCreated,
			@RequestParam(required=false)Date endCreated) {
		return newsService.getNewsList(page, rows, newsId, title, startCreated, endCreated);
	}
	
}
