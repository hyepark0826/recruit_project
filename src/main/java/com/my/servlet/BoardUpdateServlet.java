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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.sql.MyConnection;

@WebServlet("/boardupdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");//ISO_88859_1
		PrintWriter out=response.getWriter();//응답출력스트림 얻기
		HttpSession session=request.getSession();
		
		String boardNo=request.getParameter("boardNo");
		String boardTitle=request.getParameter("boardTitle");
		String boardContent=request.getParameter("boardContent");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result="{\"status\":0}";
		
		
         ObjectMapper mapper = new ObjectMapper();
         Map<String, Object>map = new HashMap<>();
         map.put("boardnum", boardNo);
         map.put("boardtitle", boardTitle);
         map.put("boardText", boardContent);
         result = mapper.writeValueAsString(map);
         
         
		try {
			con=MyConnection.getConnection();
			String selectBoardSQL="UPDATE board_tb SET b_title=?,b_content=?,b_updateday=SYSDATE WHERE b_no=?";
			pstmt=con.prepareStatement(selectBoardSQL);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, boardNo);
			rs=pstmt.executeQuery();
			map.put("status",1);
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
