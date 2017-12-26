package com.pcmc.dms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.pcmc.dms.helper.FileHelper;

@Controller
public class BaseController {
	
	@Autowired
	protected FileHelper fileHelper;
	
	@Value("${LOCAL_FOLDER_PATH}")
	protected String localFolederPath;
}
