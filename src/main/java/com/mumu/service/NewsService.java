package com.mumu.service;

import com.mumu.utils.EasyUIDataGridResult;
import com.mumu.utils.Result;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestParam;

import com.mumu.bean.News;


public interface NewsService {
	
	public Result getNewsById(Integer newsId);
	public Result getNews(int page,int rows);
	public Result addNews(String title,String image,String content);
	public Result deleteNews(int ids[]);
	public Result editNews(News news);
	public EasyUIDataGridResult getNewsList(Integer page, Integer rows,
		Integer newsId,String title,Date startCreated,Date endCreated);
	
	
}
