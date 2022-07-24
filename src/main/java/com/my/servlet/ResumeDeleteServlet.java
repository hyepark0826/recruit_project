package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.sql.MyConnection;

@WebServlet("/resumedelete")
public class ResumeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ResumeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
        System.out.println(resumeNo);
		//repo 선언
		try {
			con=MyConnection.getConnection();
			String deleteBoardSQL="DELETE FROM resume_tb WHERE resume_no=?";//userno=? 이걸로 해야하는데 세션을 받아야해용
			pstmt=con.prepareStatement(deleteBoardSQL); 
			pstmt.setString(1, resumeNo);
			rs=pstmt.executeQuery();
			result="{\"status\":1}";
			out.print(result);
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