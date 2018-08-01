package com.mumu.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mumu.bean.Content;
import com.mumu.bean.ContentExample;
import com.mumu.bean.News;
import com.mumu.dao.ContentMapper;
import com.mumu.service.ContentService;
import com.mumu.utils.EasyUIDataGridResult;
import com.mumu.utils.Result;

import net.sf.jsqlparser.statement.create.index.CreateIndex;
@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public Result getContentList() {
		Result result = new Result();
		result.setCode(200);
		Map map = new HashMap();
		List<Content> list = contentMapper.selectByExample(new ContentExample());
		map.put("contents", list);
		result.setData(map);
		return result;
	}

	@Override
	public EasyUIDataGridResult listContent(Integer page, Integer rows) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		ContentExample contentExample = new ContentExample();
		List<Content> list = contentMapper.selectByExample(contentExample);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		PageInfo<Content> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public Result deleteContent(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			contentMapper.deleteByPrimaryKey(ids[i]);
		}
		return Result.success();
	}

	@Override
	public Result editContent(Content content) {
		content.setCreated(new Date());
		contentMapper.updateByPrimaryKey(content);
		return Result.success();
	}

	@Override
	public Result addContent(Content content) {
		content.setCreated(new Date());
		contentMapper.insert(content);
		return Result.success();
	}
	
}
