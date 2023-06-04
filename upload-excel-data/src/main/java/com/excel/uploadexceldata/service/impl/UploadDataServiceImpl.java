package com.excel.uploadexceldata.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.uploadexceldata.entity.User;
import com.excel.uploadexceldata.repository.UserRepository;
import com.excel.uploadexceldata.service.UploadDataService;

@Service
public class UploadDataServiceImpl implements UploadDataService{
	
	@Autowired
	private UserRepository userRepository;

	public static boolean isValidExcelFile(MultipartFile file){
		return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
	}

	@Override
	public void uploadExcelData(MultipartFile file) {
		if(isValidExcelFile(file)){
			try {
				userRepository.saveAll(getUserDataFromExcelSheet(file.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private List<User> getUserDataFromExcelSheet(InputStream inputStream){
		List<User> userList = new ArrayList<>();

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);

			int rowIndex =0;
			for (Row row : sheet){
				if (rowIndex ==0){
					rowIndex++;
					continue;
				}
				Iterator<Cell> cellIterator = row.iterator();
				int cellIndex = 0;
				User user = new User();
				while (cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					switch (cellIndex){
					case 0: 
						user.setName(cell.getStringCellValue());
						break;
					case 1: 
						user.setEmail(cell.getStringCellValue());
						break;
					case 2: 
						user.setAge((int) cell.getNumericCellValue());
						break;
					default: 
						break;
					}
					cellIndex++;
				}
				userList.add(user);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}
}
