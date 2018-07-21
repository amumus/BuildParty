package com.mumu.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mumu.bean.News;
import com.mumu.bean.NewsExample;
import com.mumu.bean.NewsExample.Criteria;
import com.mumu.dao.NewsMapper;
import com.mumu.service.NewsService;
import com.mumu.utils.Result;



@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsMapper newsMapper;

	@Override
	public Result getNewsById(Integer newsId) {
		Result result = new Result();
		result.setCode(200);
		Map map = new HashMap<>();
		News news = newsMapper.selectByPrimaryKey(newsId);
		map.put("news", news);
		result.setData(map);
		return result;
	}

	@Override
	public Result getNewsList(int page, int rows) {
		//设置分页信息
//		PageHelper.startPage(page,rows);	
		Result result = new Result();
		result.setCode(200);
		Map map = new HashMap<>();
		NewsExample newsExample = new NewsExample();
		//修改逆向工程生成的代码，去掉content
//		List<News> list = newsMapper.selectByExample(newsExample);
		Integer start = page*rows;
		Integer end = (page+1)*rows;
		List<News> list = newsMapper.selectList(start ,end);
//		PageInfo<News> pageInfo = new PageInfo<>(list);
//		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		result.setData(map);
		return result;
	} 

	
}
