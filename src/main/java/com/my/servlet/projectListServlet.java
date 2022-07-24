package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Project;
import com.my.exeception.FindException;
import com.my.repository.projectRepository;

/**
 * Servlet implementation class projectListServlet
 */
@WebServlet("/projectlist")
public class projectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public projectListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
//		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		projectRepository repo = new projectRepository();
		List<Project> projects;
		try {
			projects = repo.selectAll();
			
		}catch(FindException e){
			e.printStackTrace();
			projects = new ArrayList<>();
			
		}
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(projects);
//		String result = "[";
//		for(int i=0; i<projects.size(); i++) {
//			if(i>0) {
//				result += ",";
//			}
//			Project p = projects.get(i);
//
//			result +="{";
//			result +="\"p_no\":";  result +="\""+p.getp_no()+"\"";   result +=",";
//			result +="\"p_title\":";    result +="\""+p.getP_title()+"\"";    result +=",";
//			result +="\"p_content\":";  result +="\""+p.getP_content()+"\"";   result +=",";
//			result +="\"p_userno\":";  result +="\""+p.getuser_no()+"\"";   result +=",";
//			result +="\"p_createday\":";  result +="\""+p.getP_createday()+"\"";   result +=",";
//			result +="\"p_view\":";  result +="\""+p.getP_view()+"\"";
////			result +="\"p_deadlineday\":"; result +=p.getP_deadlineday(); //숫자는 \" 필요없음
//			result +="}"; //json문자열
//		}
//	
//		
//		result += "]";
		
		out.print(result); 
		
		

	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
