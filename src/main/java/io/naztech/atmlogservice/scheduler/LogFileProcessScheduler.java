package io.naztech.atmlogservice.scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.naztech.atmlogservice.constant.Values;
import io.naztech.atmlogservice.model.AtmLog;
import io.naztech.atmlogservice.utils.DbService;
import io.naztech.atmlogservice.utils.FileService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogFileProcessScheduler{
	
	@Autowired
	FileService fileService;// = new FileService();
	
	@Value("${all.file.path}")
	String allFilePath;
	
	@Value("${des.path}")
	String desPath;
	
	@Scheduled(fixedRate = 36000000)
	public void logProcess() throws FileNotFoundException {
		
		File abcFile = new File (desPath);
		
		if (!Values.IS_PROCESSING) {
			Values.IS_PROCESSING = true;
			File mainFile[] = fileService.readAllFile(allFilePath);
						
			if (mainFile.length > 0) {
				for(File file : mainFile) {
					
					 Path checkDest = Paths.get(abcFile.getAbsolutePath(), file.getName());
					
					 if (!Files.exists(checkDest)) {
				          try {
				        	  fileService.readFileDataAndSaveIntoDb(file);
							  fileService.moveFile(file, desPath);
				          } catch (IOException e) {
				            System.out.println("Failed to copy the file: " + e.getMessage());
				          }
				        } else {
				          System.out.println("File already exists in the target folder: " + desPath);
				        }
					 
					 
//					fileService.readFileDataAndSaveIntoDb(file);
//					fileService.moveFile(file, desPath);
				}
			}
			 else {
				log.info("No file found in [ " + allFilePath + " ]");
			}
			
			Values.IS_PROCESSING = false;
		}
	}
	
	
}
