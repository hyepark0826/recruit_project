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
import com.my.exeception.FindException;
import com.my.repository.projectRepository;

/**
 * Servlet implementation class projectWrite2Servlet
 */
@WebServlet("/projectwrite2")
public class projectWrite2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public projectWrite2Servlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("application/json; charset=utf-8");
//		HttpSession session = request.getSession();
		//로그인 되었는지 확인 해야되는데 일단 "A001" 데이터 넣어서 테스트함, 외래키 제약조건에 걸리므로 있는 데이터 써야함
				
		PrintWriter out = response.getWriter();
		projectRepository repo = new projectRepository();
//		Project p = new Project();
//		p.setuser_no(loginedId); //로그인정보로 userno 설정
//		List<pCategory> pcategory = new ArrayList<>();

		
		//로그인 된 사용자인가 확인하고 넘어가야함
		// null값이 들어가지 않게 확인필요
		
//		out.print("start");
		try {
			//제목 내용 등 데이터를 받아서 project로 만들고 insert에 넘겨주기
			String cate1 = request.getParameter("location");
			String cate2 = request.getParameter("career");
			String cate3 = request.getParameter("field");
			
			List<Integer> list = new ArrayList<Integer>(); 
			list.add(Integer.parseInt(cate1.substring(0,3)));
			list.add(Integer.parseInt(cate2.substring(0,3)));
			list.add(Integer.parseInt(cate3.substring(0,3)));
			
			String userno = "A2";
			String title = request.getParameter("p_title");
			String content =  request.getParameter("p_content"); 
			String deadlineday = request.getParameter("deadlineDay");
			Project p = new Project(userno,title,content,deadlineday);
			
			int res = 0;
			int res_cate=0;
			res = repo.insert(p);
			res_cate = repo.insertpCategory(list);
			// 모집글에 데이터 입력
			if( res!=0 && res_cate!=0) { //insert에 성공하면 성공한 햏 횟수를 반환함
				//out.print("성공");
				response.sendRedirect("http://localhost:8888/recruit_project_final/project_html/projectList.html");
				
			}
			else
				out.print("실패");

		} catch (FindException | SQLException e) {
			e.printStackTrace();
		}


	}
}
