package com.naveen.filemultipartUpload.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.io.File;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;



@RestController  
public class controller {
	@RequestMapping("/")  
	public String hello()  
	{  
	return "Hello Naveen";  
	}  

	@RequestMapping("/getContentType")
	
		public String getContenttype()
		{
			
			 Path path = new File("D:\\Naveen\\complete\\Camera\\nc.jpg").toPath();
			    try {
					String mimeType =Files.probeContentType(path);
					return mimeType;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
		}
	
	@RequestMapping(path="/singlefileupload/",method=RequestMethod.POST)
	public ResponseEntity<String> processFile(@RequestParam("file" ) MultipartFile file) throws IOException 
	{
		byte[] bytes =file.getBytes();
	
		System.out.println("File Name: " + file.getOriginalFilename());
        System.out.println("File Content Type: " + file.getContentType());
        //System.out.println("File Content:\n" + new String(bytes));

        return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
		
	}
	
	 @RequestMapping(path = "/multiplefileupload/", method = RequestMethod.POST)
	    public ResponseEntity<String> processFile(@RequestParam("files") List<MultipartFile> files) throws IOException {

	        for (MultipartFile file : files) {
	            byte[] bytes = file.getBytes();
	            
	            
	            System.out.println("File Name: " + file.getOriginalFilename());
	            System.out.println("File Content Type: " + file.getContentType());
	            
	            if(!file.getContentType().contains(".exe")||file.getContentType().contains(".jar")||file.getContentType().contains(".js")||file.getContentType().contains(".py"))
	            {
	            	System.out.println( "File uplaoded is safe : "+file.getOriginalFilename( ));
	            	//return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
	            	
	            }
	            
	            else
	            {
	            	return (new ResponseEntity<>("Failed,Because files uplaoded are not safe", null, HttpStatus.BAD_REQUEST));
	            }
	            
	            //System.out.println("File Content:\n" + new String(bytes));
	        }

	        return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
	    }
	
}
