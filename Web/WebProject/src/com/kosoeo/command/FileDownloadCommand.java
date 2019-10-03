package com.kosoeo.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FileDownloadCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String realName = request.getParameter("realName");
		String submitName = request.getParameter("submitName");
		
		String filePath = request.getSession().getServletContext().getRealPath("File");
		File file = new File(filePath, realName);
		
		String mimeType = request.getSession().getServletContext().getMimeType(file.toString());
		if (mimeType == null) {
			response.setContentType("application/octet-stream");
		}

		String downName = null;
		if (request.getHeader("user-agent").indexOf("MSIE") == -1) {
			downName = new String(submitName.getBytes("UTF-8"), "8859_1");
		} else {
			downName = new String(submitName.getBytes("EUC-KR"), "8859_1");
		}

		response.setHeader("Content-Disposition", "attachment;filename=\"" + downName + "\";");

		
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();

		byte b[] = new byte[1024];
		int data = 0;

		while ((data = (fileInputStream.read(b, 0, b.length))) != -1) {
			servletOutputStream.write(b, 0, data);
		}

		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();

	}

}
