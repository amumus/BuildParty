package com.mumu.service;

import com.mumu.bean.Content;
import com.mumu.utils.EasyUIDataGridResult;
import com.mumu.utils.Result;

public interface ContentService {
	public Result getContentList();
//	public Result getContentById(Integer contentId);

	public EasyUIDataGridResult listContent(Integer page, Integer rows);

	public Result deleteContent(int[] ids);

	public Result editContent(Content content);

	public Result addContent(Content content);

}
