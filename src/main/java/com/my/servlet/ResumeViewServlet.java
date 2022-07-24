package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Resume;
import com.my.sql.MyConnection;

@WebServlet("/resumeview")
public class ResumeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");//ISO_88859_1
		PrintWriter out=response.getWriter();//응답출력스트림 얻기
		//DB와의 연결
		Connection con=null;
		//SQL송신
		PreparedStatement pstmt=null;
		//송신결과
		ResultSet rs=null;
		//응답결과
		String result=null;
		String resumeNo=request.getParameter("resumeNo");
		List<Resume>list=new ArrayList<>();
		//repo 선언
		try {
			con=MyConnection.getConnection();
			String selectResumeSQL="SELECT * FROM RESUME_tb WHERE resume_no=?";
			pstmt=con.prepareStatement(selectResumeSQL); 
			pstmt.setString(1, resumeNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String resume_title=rs.getString("resume_title");
				int resume_no=rs.getInt("resume_no");  //db에서 가졍ㅎㅁ
				String resume_introduce=rs.getString("resume_introduce");
				int resume_age=rs.getInt("resume_age");
				String resume_major=rs.getString("resume_major");
				//String resume_email=rs.getString("resume_email");
				
				ObjectMapper mapper = new ObjectMapper();
		        Map<String, Object>map = new HashMap<>();
		        //map형식으로 자기소개서 제목을들을 넣어주고
				map.put("resume_no", resume_no);//자기소개서 번호 
	            map.put("resume_title", resume_title);//저가소개서
	            map.put("resume_introduce",resume_introduce);
	            map.put("resume_age", resume_age);
	            map.put("resume_major",resume_major);
				result=mapper.writeValueAsString(map); //map형식을 String 타입으로 변경하고
				//out.print로 바깥으로 출력해준다.
				out.print(result);
			}
			else {
				result="{\"status\":0}";
				out.print(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs,pstmt,con);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
