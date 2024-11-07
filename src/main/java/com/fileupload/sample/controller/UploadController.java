package com.fileupload.sample.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

	
	@GetMapping("upload")
	public ResponseEntity<String> uploadFile(@RequestParam("receivedFile") MultipartFile receivedFile) {
		System.out.println(receivedFile.getOriginalFilename());
		try {
			Files.write(Paths.get("C:\\tmp\\" + receivedFile.getOriginalFilename()), receivedFile.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
	}

	@GetMapping("download") 
	public ResponseEntity<Resource> download(){
		try{
			var path = Paths.get("C:\\tmp\\MOCK_DATA.csv");
			var resource = new ByteArrayResource(Files.readAllBytes(path));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("a", path.toFile().getName());
			headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path));
			return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
