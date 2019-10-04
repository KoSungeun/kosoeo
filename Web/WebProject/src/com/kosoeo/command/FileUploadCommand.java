package com.kosoeo.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kosoeo.dao.FileDAO;

public class FileUploadCommand implements Command  {


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
					String realFileName = submitFileName;
					
					String path = request.getContextPath() + "/File/";
					String name = submitFileName.substring(0, submitFileName.lastIndexOf("."));
					String extension = submitFileName.substring(submitFileName.lastIndexOf("."));
					String contentType = part.getContentType();
					
					int boardNo = 0;
					if(request.getAttribute("seq") == null) {
						boardNo = Integer.parseInt(request.getParameter("no"));
					} else {
						boardNo = (int) request.getAttribute("seq");
					}
					
					long fileSize = part.getSize();
					
					File file = new File(filePath, realFileName);
					int i = 1;
					while (file.exists()) {
						realFileName = name + "(" + i + ")" + extension;
						file = new File(filePath, realFileName) ;
						i++;
					}
					
					part.write(file.getPath());
					
					
					com.kosoeo.dto.File dto = new com.kosoeo.dto.File();
					dto.setContentType(contentType);
					dto.setPath(path);
					dto.setFileSize(fileSize);
					dto.setRealName(realFileName);
					dto.setSubmitName(submitFileName);
					dto.setBoardNo(boardNo);
					FileDAO dao = FileDAO.getInstance();
					dao.upload(dto);
				}
			}
		} catch (Exception e) {
		}
		
	}

}
