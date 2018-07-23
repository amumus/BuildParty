package com.mumu.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mumu.bean.Content;
import com.mumu.bean.ContentExample;
import com.mumu.dao.ContentMapper;
import com.mumu.service.ContentService;
import com.mumu.utils.Result;
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
	
}
