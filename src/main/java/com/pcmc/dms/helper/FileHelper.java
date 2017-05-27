package com.pcmc.dms.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FileHelper {
	
	public static final String LOCAL_FOLDER_PATH = "C:\\images\\";
	
	public boolean copyFile(String imageName, String rootPath){
		
		if(StringUtils.isEmpty(imageName))
			return false;
		
		String sourcePath = LOCAL_FOLDER_PATH + imageName + ".pdf";
		File source = new File(sourcePath);
		
		String destPath = rootPath + "/static/images/" + imageName + ".pdf";
		File dest = new File(destPath);
		
		try{
			this.copyFileUsingChannel(source, dest);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	@SuppressWarnings("resource")
	private void copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	   }
	}
	
}
