package com.excel.uploadexceldata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.uploadexceldata.service.UploadDataService;

@RestController
public class UploadDataController {

	@Autowired
	private UploadDataService uploadDataService;
	
	@PostMapping(value = "/upload")
	public String uploadExcelData(@RequestParam("file") MultipartFile file) {
		uploadDataService.uploadExcelData(file);
		return "uploaded";
	}
}
