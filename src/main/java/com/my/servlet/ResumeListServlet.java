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

@WebServlet("/resumelist")
public class ResumeListServlet extends HttpServlet {
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
		//String userNo=request.getParameter("userNo");
		List<Resume>list=new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
        Map<String, Object>map = new HashMap<>();
		//repo 선언
		try {
			con=MyConnection.getConnection();
			String selectIdNPwdSQL="SELECT * FROM RESUME_TB";//userno=? 이걸로 해야하는데 세션을 받아야해용
			pstmt=con.prepareStatement(selectIdNPwdSQL); 
			//pstmt.setString(1, userNo);
			rs=pstmt.executeQuery();
			System.out.println(rs);
			while(rs.next()) {
				//list에 있는 자유게시판 1개씩 글을 꺼내 정보를 추출하고 out.print()로 boardList.html에 출력해보자
				int resume_no= rs.getInt("resume_no");
				String resume_title=rs.getString("resume_title");
				Resume b = new Resume();
				b.setResume_no(resume_no);
				b.setResume_title(resume_title);
//			    map.put("boardnum", b_no);//글번호
//			    map.put("boardtitle", b_title);//글제목 넣어주기
				
//			    list.add(map);
				list.add(b);
			}   
			result=mapper.writeValueAsString(list); //스트링으로 바꿔와서 리저ㄹ트-js-
			out.print(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs,pstmt,con);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
