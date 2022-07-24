package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Comment;
import com.my.dto.Customer;
import com.my.sql.MyConnection;

/**
 * Servlet implementation class CommentServlet1
 */
@WebServlet("/commentservlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out =response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		Connection con = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		String result = null;
		
		try {
			con = MyConnection.getConnection();
			String selectCommentSQL=
//					"SELECT * \r\n"
//					+ "FROM comment_test_tb \r\n"

"SELECT cm.*, c.user_name \r\n"
+ "FROM comment_test_tb cm JOIN customer_tb c ON  cm.user_no = c.USER_NO "
					+ "WHERE p_no = ?\r\n"
					+ "START WITH c_class=0 \r\n"
					+ "CONNECT BY PRIOR c_no = c_class";
			pstmt = con.prepareStatement(selectCommentSQL);
			pstmt.setInt(1, p_no);
			rs = pstmt.executeQuery();
			
			Map<String, Object>map = new HashMap<>();
			map.put("status", 1);
			List<Comment> comments = new ArrayList<>();
			while(rs.next()) {
//				String p_no = rs.getString("p_no");
				int c_no = rs.getInt("c_no");
				int user_no = rs.getInt("user_no");
				String user_name = rs.getString("user_name");
				Customer c = new Customer();
				c.setUser_no(user_no);	
				c.setUser_name(user_name);
				String c_content = rs.getString("c_content");
//				int c_order = rs.getInt("c_order");
				int c_class = rs.getInt("c_class");
//				int c_groupno = rs.getInt("c_groupno");
				Date c_write_date = rs.getDate("c_write_date");
				Date c_update_date = rs.getDate("c_update_date");
				Date c_delete_date = rs.getDate("c_delete_date");
				System.out.println(p_no);
				System.out.println(c_content);
				System.out.println(c_write_date);
				
				
//				map.put(p_no, map);
//				map.put(c_no, map);
//				map.put(c_content, map);
//				new Comment()
				
//				Comment comment = new Comment(c_no, p_no, user_no, c_content, c_class,  c_write_date, c_update_date, c_delete_date);
				Comment comment = new Comment(c_no, p_no, c, c_content, c_class,  c_write_date, c_update_date, c_delete_date);
				
				comments.add(comment);
//				
			}//end while
			map.put("comments", comments);
			result = mapper.writeValueAsString(map);
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
			Map<String, Object>map = new HashMap<>();
			map.put("status", -1);
			result = mapper.writeValueAsString(map);
			
		}finally {
			MyConnection.close(pstmt, con);
		}
		out.println(result);
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * 
	 * }
	 */

