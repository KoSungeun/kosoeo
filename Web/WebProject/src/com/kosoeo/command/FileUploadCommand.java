package com.kosoeo.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FileUploadCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			for(Part part : request.getParts()) {
				if(part.getName().equalsIgnoreCase("file")) {
					
					File filePath = new File(request.getSession().getServletContext().getRealPath("File"));
					if(!filePath.exists()) {
						filePath.mkdir();
					}
					String submitFileName = part.getSubmittedFileName();
					String realFileName = "";
					File file = new File(filePath, submitFileName);
					if(file.exists()) {
						realFileName = submitFileName + 1;
						file = new File(filePath, realFileName);
					} else {
						realFileName = submitFileName;
					}
					
					
					long size = part.getSize();
					part.write(file.getPath());
				}
			}
		} catch (Exception e) {
		}
		
	}

}
