package com.mumu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mumu.utils.FtpConfig;
import com.mumu.utils.FtpUtil;
import com.mumu.utils.JsonUtils;
import com.mumu.utils.Result;
import com.mumu.utils.UploadUtils;


@Controller
public class FTPController {
	@Autowired
	private FtpConfig ftpConfig;
	/**
	 * 上传多个图片
	 * @param files
	 * @return
	 */
	@RequestMapping("/uploadFiles")
	@ResponseBody
	public Result uploadFiles(@RequestParam("file") MultipartFile[] files) {
		List<String> imageURLs = new ArrayList<>();
		try {
			// 循环上传多个图片
			for (MultipartFile file : files) {
				String oldName = file.getOriginalFilename();
				String picNewName = UploadUtils.generateRandonFileName(oldName);// 通过工具类产生新图片名称，防止重名
				String picSavePath = UploadUtils.generateRandomDir(picNewName);// 通过工具类把图片目录分级

				// 上传到图片服务器的操作
				imageURLs.add(FtpUtil.pictureUploadByConfig(ftpConfig, picNewName, picSavePath, file.getInputStream()));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return Result.error();
		}
		// 添加到数据库
		// iPhotoService.savePhotoList(photoList);//调用service层方法
		Result result = new Result();
		result.setCode(200);
		Map m = new HashMap<>();
		m.put("url", imageURLs);
		result.setData(m);
		return result;// 上传成功做的操作,我这里返回上传成功的信号
	}

	/**
	 * 上传单个图片
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/uploadImage")
	@ResponseBody
	public String uploadImage(@RequestParam("file") MultipartFile file) {
		String imageURL = "";
		try {
			String oldName = file.getOriginalFilename();
			String picNewName = UploadUtils.generateRandonFileName(oldName);// 通过工具类产生新图片名称，防止重名
			String picSavePath = UploadUtils.generateRandomDir(picNewName);// 通过工具类把图片目录分级
			// 上传到图片服务器的操作
			imageURL = FtpUtil.pictureUploadByConfig(ftpConfig, picNewName, picSavePath, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message","上传出错啦！");
			return JsonUtils.objectToJson(result);
		}
		Map result = new HashMap<>();
		result.put("error", 0);
		result.put("url",imageURL);
		return JsonUtils.objectToJson(result);
	}
}
