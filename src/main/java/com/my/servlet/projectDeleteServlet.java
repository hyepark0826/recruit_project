package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.repository.projectRepository;

/**
 * Servlet implementation class projectDeleteServlet
 */
@WebServlet("/projectdelete")
public class projectDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public projectDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		projectRepository repo = new projectRepository();
		try {
			//p_no를 받아서 삭제하는 기능?
			String projectNum = request.getParameter("p_no");
			System.out.println(projectNum);
			System.out.println("test");
			int p_no = Integer.parseInt(projectNum); //글번호 받아오기
			
			int res = 0;
			int res2 = 0;

			res2 = repo.deletepcategory(p_no);
			res = repo.deleteproject(p_no);
			System.out.println("res2 : "+res2+" res : "+ res);
			if( res!=0 && res2!=0) //delete에 성공하면 성공한 햏 횟수를 반환함
//				out.print("삭제되었습니다");
				response.sendRedirect("http://localhost:8888/recruit_project_final/project_html/projectList.html");
			else
				out.print("글이 없습니다");

			}catch (SQLException e) {
				e.printStackTrace();
			}

	}
}

