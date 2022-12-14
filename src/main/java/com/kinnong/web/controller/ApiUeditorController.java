package com.kinnong.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.common.exception.RException;
import com.kinnong.common.utils.DateUtils;
import com.kinnong.web.config.UeditorConfig;

@RestController
@RequestMapping("/api/ueditor")
public class ApiUeditorController {
	
	@Value("${fileupload.filepath}")
	String filepath;
	
	@Autowired
	UeditorConfig ueditorConfig;

	@AuthIgnore
	@RequestMapping("")
	public Object ueditor(String action, MultipartFile upfile, HttpServletRequest req, HttpServletResponse resp){
		if("config".equals(action)){
			Map<String, Object> config = new HashMap<String, Object>();
			config.put("imageActionName", ueditorConfig.getImageActionName());
			config.put("imageUrlPrefix", ueditorConfig.getImageUrlPrefix());
			config.put("imagePath", ueditorConfig.getImagePath());
			config.put("imageFieldName", ueditorConfig.getImageFieldName());
			config.put("imageMaxSize", ueditorConfig.getImageMaxSize());
			config.put("imageAllowFiles", ueditorConfig.getImageAllowFiles());
			return config;
		}else if("uploadimage".equals(action)){
			if (upfile.isEmpty()) {
				throw new RException("上传文件不能为空");
			}
			
			String filename = upfile.getOriginalFilename();
			
			String suffix = filename.substring(filename.lastIndexOf("."), filename.length());
			
			String uuid = UUID.randomUUID().toString();
			
			String currDate = DateUtils.format(new Date(), "yyyyMMdd");
			
			try {
				FileUtils.writeByteArrayToFile(new File(filepath + "/fileupload/" + currDate + "/" + uuid + suffix), upfile.getBytes());
				Map<String, String> result = new HashMap<String, String>();
				result.put("state", "SUCCESS");
				result.put("type", suffix);
				result.put("size", String.valueOf(upfile.getSize()));
				result.put("url", "/" + currDate + "/" + uuid + suffix);
				return result;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@GetMapping("config")
	public void config(){
		
	}
}
