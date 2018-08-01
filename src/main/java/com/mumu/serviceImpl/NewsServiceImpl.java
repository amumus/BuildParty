package com.mumu.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mumu.bean.News;
import com.mumu.bean.NewsExample;
import com.mumu.bean.Student;
import com.mumu.bean.NewsExample.Criteria;
import com.mumu.dao.NewsMapper;
import com.mumu.service.NewsService;
import com.mumu.utils.EasyUIDataGridResult;
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
	public Result getNews(int page, int rows) {
		//设置分页信息
//		PageHelper.startPage(page,rows);	
		Result result = new Result();
		result.setCode(200);
		Map map = new HashMap<>();
		NewsExample newsExample = new NewsExample();
		//修改逆向工程生成的代码，去掉content
//		List<News> list = newsMapper.selectByExample(newsExample);
		Integer start = page*rows;
		List<News> list = newsMapper.selectList(start ,rows);
//		PageInfo<News> pageInfo = new PageInfo<>(list);
//		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		result.setData(map);
		return result;
	}

	@Override
	public Result addNews(String title, String image, String content) {
		News news = new News();
		news.setTitle(title);
		news.setImage(image);
		news.setContent(content);
		news.setCreated(new Date());
		news.setReading(0);
		newsMapper.insert(news);
		return Result.success();
	}

	@Override
	public Result deleteNews(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			newsMapper.deleteByPrimaryKey(ids[i]);
		}
		return Result.success();
	}

	@Override
	public Result editNews(News news) {
		news.setCreated(new Date());
		newsMapper.updateByPrimaryKeySelective(news);
		return Result.success();
	}

	@Override
	public EasyUIDataGridResult getNewsList(Integer page, Integer rows, 
			Integer newsId, String title,
			Date startCreated, Date endCreated) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		NewsExample newsExample = new NewsExample();
		List<News> list = null;
		Criteria criteria = null;
		if(newsId != null && newsId != -1) {
			if(criteria == null) {
				criteria = newsExample.createCriteria();
			}
			criteria.andNewsIdEqualTo(newsId);
		}
		if(title != null && !title.trim().equals("")) {
			if(criteria == null) {
				criteria = newsExample.createCriteria();
			}
			String s = "%"+title+"%";
			criteria.andTitleLike(s);
		}
		if(startCreated !=null) {
			if(criteria == null) {
				criteria = newsExample.createCriteria();
			}
			criteria.andCreatedGreaterThanOrEqualTo(startCreated);
		}
		if(endCreated !=null) {
			if(criteria == null) {
				criteria = newsExample.createCriteria();
			}
			criteria.andCreatedLessThanOrEqualTo(endCreated);
		}
		list = newsMapper.selectByExample(newsExample);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		PageInfo<News> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	} 

	
}
