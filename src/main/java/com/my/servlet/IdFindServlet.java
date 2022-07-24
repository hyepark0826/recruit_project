package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Customer;
import com.my.sql.MyConnection;


@WebServlet("/idfind")
public class IdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see 아이디 찾기 버튼 눌렀을 때
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");//ISO_88859_1
		PrintWriter out=response.getWriter();//응답출력스트림 얻기
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		System.out.println("user_name="+name+"user_email="+email);
		//DB와의 연결
		Connection con=null;
		//SQL송신
		PreparedStatement pstmt=null;
		//응답결과
		ResultSet rs=null;
		String result=null;
		//"{\"status\":0}"
		try {
			con=MyConnection.getConnection();
			String selectIdSQL="SELECT * FROM customer_tb WHERE user_name=? AND user_email=?";
			pstmt=con.prepareStatement(selectIdSQL);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			System.out.println(rs);
			if(rs.next()) {//해당하는 아이가 있는지
				result="{\"status\":1}";//성공했다는 의미
				System.out.println(rs.getString("user_id"));
				String customerId = rs.getString("user_id");//DB에 저장되어 있는 회원 아이디를 찾고
				out.print(customerId);//클라이언트에게 아이디 출력
				
			}else {//입력한 정보와 일치하는 회원정보가 있으면 id값을 알려주자
				result="{\"status\":0}";
			}
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
