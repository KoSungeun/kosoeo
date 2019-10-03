package com.kosoeo.command;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FileUploadCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		for (Part part : request.getParts()) {
			if (part.getName().equalsIgnoreCase("file")) {

				File filePath = new File(request.getSession().getServletContext().getRealPath("File"));
				if (!filePath.exists()) {
					filePath.mkdir();
				}
				String submitFileName = part.getSubmittedFileName();
				String fileName = submitFileName.substring(0, submitFileName.lastIndexOf("."));
				String extension = submitFileName.substring(submitFileName.lastIndexOf("."));

				int i = 1;
				File file = new File(filePath, submitFileName);
				while (file.exists()) {
					file = new File(filePath, fileName + "(" + i + ")" + extension);
					i++;
				}

				String realFileName = file.getName();
				long size = part.getSize();
				part.write(file.getPath());
			}
		}

	}

}
