package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.sql.MyConnection;

/**
 * Servlet implementation class ResumeUpdateServlet
 */
@WebServlet("/resumeupdate")
public class ResumeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");// ISO_88859_1

		PrintWriter out = response.getWriter();
		// DB와의 연결
		Connection con = null;
		// SQL 송신
		PreparedStatement pstmt = null;
		// 송신 결과
		ResultSet rs = null;
		// 응답 결과
		String result = null;
		String resumeNo = request.getParameter("resumeNo");
		String resumeTitle = request.getParameter("resumeTitle");
		String resumeIntroduce = request.getParameter("resumeIntroduce");
		System.out.println(resumeNo);
		ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object>map = new HashMap<>();
	    map.put("resumeNo", resumeNo);
	    map.put("resumeTitle", resumeTitle);
	    map.put("resumeIntroduce", resumeIntroduce);
		result = mapper.writeValueAsString(map);
		try {
			con = MyConnection.getConnection();
			String updateResumeSQL = "UPDATE resume_tb SET resume_title=? , resume_Introduce=? WHERE resume_no=?";
			pstmt = con.prepareStatement(updateResumeSQL);
			pstmt.setString(1, resumeTitle);
			pstmt.setString(2, resumeIntroduce);
			pstmt.setString(3, resumeNo);
			rs = pstmt.executeQuery();
			out.print(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
