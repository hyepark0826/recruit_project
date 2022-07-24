package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.Comment;
import com.my.exeception.FindException;
import com.my.sql.MyConnection;




public class CommentRepository {
	public void insert() throws FindException{
		List<Comment> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectcommentSQL = "Select * from comment";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectcommentSQL);
			pstmt.setString(1, "sq_tb_no.textval");
		}catch(SQLException e) {
		e.printStackTrace();
		throw new FindException(e.getMessage());
	} finally {
		
	}
	}
	public List<Comment> selectAll() throws FindException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectcommentSQL = "SELECT * \r\n"
				+ "FROM comment_test_tb \r\n"
				+ "WHERE p_no = ?\r\n"
				+ "START WITH c_class=0 \r\n"
				+ "CONNECT BY PRIOR c_no = c_class";
				try {
					con = MyConnection.getConnection();
					pstmt = con.prepareStatement(selectcommentSQL);
					rs = pstmt.executeQuery();
					List<Comment> list = new ArrayList<>();
					while(rs.next()) {
						String p_no = rs.getString("p_no");
						int c_no = rs.getInt("c_no");
						String user_no = rs.getString("user_no");
						String c_content = rs.getString("c_content");
						int c_order = rs.getInt("c_order");
						int c_class = rs.getInt("c_class");
						int c_groupno = rs.getInt("c_groupno");
						String c_write_date = rs.getString("c_write_date");
						String c_update_date = rs.getString("c_update_date");
						String c_delete_date = rs.getString("c_delete_date");
						//Comment c = new Comment(p_no, c_no, user_no, c_content, 
						//		c_order, c_class, c_groupno, c_write_date, c_update_date, c_delete_date);
						//ist.add(c);
					}
					if(list.size()==0) {
						throw new FindException("Ha_Ha_Null_List");
					}
					return list;
				} catch(SQLException e){
					e.printStackTrace();
					throw new FindException(e.getMessage());
				} finally {
					MyConnection.close(rs, pstmt, con);
				}
	}
}
	
