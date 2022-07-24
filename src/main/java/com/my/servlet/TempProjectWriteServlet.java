package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Project;
import com.my.repository.projectRepository;

/**
 * Servlet implementation class tempprojectwriteServlet
 */
@WebServlet("/tempprojectwrite")
public class TempProjectWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TempProjectWriteServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");

		PrintWriter out = response.getWriter();
		projectRepository repo = new projectRepository();
	
		try {
			//제목 내용 등 데이터를 받아서 project로 만들고 insert에 넘겨주기
			String cate1 = request.getParameter("location");
			String cate2 = request.getParameter("career");
			String cate3 = request.getParameter("field");
			
			List<Integer> list = new ArrayList<Integer>(); 
			list.add(Integer.parseInt(cate1.substring(0,3)));
			list.add(Integer.parseInt(cate2.substring(0,3)));
			list.add(Integer.parseInt(cate3.substring(0,3)));
			//---------------카테고리 리스트 설정
			
			String userno = "A2";
			String title = request.getParameter("p_title");
			String content =  request.getParameter("p_content");
			String deadlineday = request.getParameter("deadlineDay");
			Project p = new Project(userno,title,content,deadlineday);
			System.out.println(userno+" "+title+" "+content);
			int res = 0;
			//int res_cate=0;
			res = repo.tmp_insert(p, list);
			//res_cate = repo.insertpCategory(list);
			// 모집글에 데이터 입력
			if( res!=0) { //insert에 성공하면 성공한 햏 횟수를 반환함
				//out.print("성공");
				response.sendRedirect("http://localhost:8888/recruit_project_final/project_html/myPagePostList.html");
				
			}
			else
				out.print("실패");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	
	}

}
