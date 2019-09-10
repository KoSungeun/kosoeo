package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.jasper.runtime.JspRuntimeLibrary;


@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MemberDto dto = null;
	PageContext pc = null;

    public FrontCon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo");
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String command = uri.substring(conPath.length());
		System.out.println("command : " + command);
		
		if(command.equals("/loginOk.do")) {
			loginOk(request, response);
		} else if (command.equals("/modifyOk.do")) {
			modifyOk(request, response);
		} else if (command.equals("/joinOk.do")) {
			joinOk(request, response);
		} else if (command.equals("/logout.do")) {
			logout(request, response);
		}
		
	}
	
	private void joinOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		dto = new MemberDto();
		JspRuntimeLibrary.introspect(dto, request);
		HttpSession session = request.getSession();
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		MemberDao dao = MemberDao.getInstance();
		
		if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT) {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"아이디가 이미 존재 합니다.\");");
			out.println("	history.back();");
			out.println("</script>");
			out.println("</body></html>");
			
		} else {
			int ri = dao.insertMember(dto);
			if(ri == MemberDao.MEMBER_JOIN_SUCCESS) {
				session.setAttribute("id", dto.getId());
				
				out.println("<html><head></head><body>");
				out.println("<script>");
				out.println("	alert(\"회원가입을 축하 합니다.\");");
				out.println("	document.location.href=\"login.jsp\";");
				out.println("</script>");
				out.println("</body></html>");
			} else {
				out.println("<html><head></head><body>");
				out.println("<script>");
				out.println("	alert(\"회원가입에 실패했습니다.\");");
				out.println("	history.back();");
				out.println("</script>");
				out.println("</body></html>");	
			}
		}
		out.close();		
	}
	
	private void loginOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDao dao = MemberDao.getInstance();
		int checkNum = dao.userCheck(id, pw);
		if(checkNum == MemberDao.MEMBER_LOGIN_IS_NOT) {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"아이디가 존재하지 않습니다.\");");
			out.println("	history.back();");
			out.println("</script>");
			out.println("</body></html>");
		} else if(checkNum == MemberDao.MEMBER_LOGIN_PW_NO_GOOD) {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"비밀번호가 틀립니다.\");");
			out.println("	history.back();");
			out.println("</script>");
			out.println("</body></html>");
		} else if(checkNum == MemberDao.MEMBER_LOGIN_SUCCESS) {
			MemberDto dto = dao.getMember(id);
			String name = dto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
			
			response.sendRedirect("main.jsp");	
		}
			
	}

	private void modifyOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		dto = new MemberDto();
		JspRuntimeLibrary.introspect(dto, request);
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		dto.setId(id);
		MemberDao dao = MemberDao.getInstance();
		
		int ri = dao.updateMember(dto);
		

		if(ri == 1) {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"정보가 수정되었습니다.\");");
			out.println("	document.location.href=\"main.jsp\";");
			out.println("</script>");
			out.println("</body></html>");

		} else {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"정보수정에 실패했습니다.\");");
			out.println("	history.go(-1);");
			out.println("</script>");
			out.println("</body></html>");
			
		}
			
	}
	
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");		
	}

}
