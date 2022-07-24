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
import javax.servlet.http.HttpSession;

import com.my.sql.MyConnection;

@WebServlet("/pwdupdate")
public class PwdUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청전달데이터열기
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();

        //아이디 값과 사용자가 입력한 변경할 비밀번호 값을 받아오자 
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		System.out.println("user_id="+id+" "+"user_pwd="+pwd);
		//DB와의 연결
		Connection con=null;
		//SQL송신
		PreparedStatement pstmt=null;
		//응답결과
		ResultSet rs=null;
        //디폴트 값으로 result 0.. 
		String result="{\"status\":0}";
		
        // 1) 비밀번호 변경 레이아웃에서 id값을 입력 받아서 
        //  비밀번호 변경할 때 아이디값을 가져와서 WHERE 절에 해당 아이디 값인 회원정보를 찾아 비밀번호를 UPDATE구문을 이용해 변경
		try {
			con=MyConnection.getConnection();
			String selectPwdSQL="UPDATE customer_tb SET user_pwd=? WHERE user_id=?";//해당 id인 회원정보의 pwd값을 update하자
			pstmt=con.prepareStatement(selectPwdSQL);
			pstmt.setString(1, pwd);
			pstmt.setString(2,id);
			pstmt.executeUpdate();
			result="{\"status\":1}";
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs,pstmt,con);
		}
		out.print(result);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
