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


@WebServlet("/noticeview")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public NoticeViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");//ISO_88859_1
		PrintWriter out=response.getWriter();//응답출력스트림 얻기
		HttpSession session=request.getSession();
		
		String boardNo=request.getParameter("boardNo");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result=null;
		
		try {
			con=MyConnection.getConnection();
			String selectBoardSQL="SELECT * FROM notice_tb WHERE n_no=?";
			pstmt=con.prepareStatement(selectBoardSQL);
			pstmt.setString(1, boardNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
               int board_no= rs.getInt("n_no");
               String title=rs.getString("n_title");
               String content=rs.getString("n_content");
               
               System.out.println(board_no);
               System.out.println(title);
               System.out.println(content);
               
               ObjectMapper mapper = new ObjectMapper();
               Map<String, Object>map = new HashMap<>();
               map.put("boardnum", board_no);
               map.put("boardText", content);
               map.put("boardtitle", title);
               result = mapper.writeValueAsString(map);
               out.print(result);
               
//               out.println("<html><head></head><body>");
//               out.println("<h1>"+board_no+"</h1>");
//               out.println("<h3>"+title+"</h3>");
//               out.println("<p>"+content+"</p>");
//               out.println("</body></html>");

		    }else {//입력한 정보와 일치하는 게시글이 없으면
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}