package com.excel.uploadexceldata.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UploadDataService {

	void uploadExcelData(MultipartFile file);
}
