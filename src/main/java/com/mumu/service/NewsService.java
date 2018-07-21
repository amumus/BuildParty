package com.mumu.service;

import com.mumu.utils.Result;
import com.mumu.bean.News;


public interface NewsService {
	
	public Result getNewsById(Integer newsId);
	public Result getNewsList(int page,int rows);
}
