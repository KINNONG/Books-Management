package com.kinnong.web.controller;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.common.exception.RException;
import com.kinnong.common.utils.DateUtils;
import com.kinnong.common.utils.R;

@RestController
@RequestMapping("/api/fileupload")
public class ApiFileUploadController {
	
	@Value("${fileupload.filepath}")
	String filePath;
	
	@Value("${fileupload.server}")
	String serverUrl;
	
	/**
	 * 上传文件
	 */
	@AuthIgnore
	@RequestMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RException("上传文件不能为空");
		}
		
		String filename = file.getOriginalFilename();
		
		String suffix = filename.substring(filename.lastIndexOf("."), filename.length());
		
		String uuid = UUID.randomUUID().toString();
		
		String currDate = DateUtils.format(new Date(), "yyyyMMdd");
		
		FileUtils.writeByteArrayToFile(new File(filePath + "/fileupload/" + currDate + "/" + uuid + suffix), file.getBytes());

		return R.ok().put("url", serverUrl + "/" + currDate + "/" + uuid + suffix);
	}
}
