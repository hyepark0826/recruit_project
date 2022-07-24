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
import com.my.dto.Board;
import com.my.sql.MyConnection;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/boardlist")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("application/json;charset=UTF-8");//ISO_88859_1
		PrintWriter out=response.getWriter();//응답출력스트림 얻기
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result="{\"status\":0}";
		ObjectMapper mapper = new ObjectMapper();
        Map<String, Object>map = new HashMap<>();
//        List<Map<String,Object>>list=new ArrayList<>();
        List<Board>list=new ArrayList<>();
		try {
			con=MyConnection.getConnection();
			String selectBoardSQL="SELECT * FROM board_tb";
			pstmt=con.prepareStatement(selectBoardSQL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				//list에 있는 자유게시판 1개씩 글을 꺼내 정보를 추출하고 out.print()로 boardList.html에 출력해보자
				int b_no= rs.getInt("b_no");
				String b_title=rs.getString("b_title");
				Board b = new Board();
				b.setB_no(b_no);
				b.setB_title(b_title);
//			    map.put("boardnum", b_no);//글번호
//			    map.put("boardtitle", b_title);//글제목 넣어주기
				
//			    list.add(map);
				list.add(b);
			}   
			 result = mapper.writeValueAsString(list);
			 out.print(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs,pstmt,con);
		}
		
		
//		ProductRepository repository=new ProductOracleRepository();
//		try {
//			Product p=repository.selectByProdNo(prod_no);
//			
//			//map객체 생성
//			Map<String,Object> map=new HashMap<>();
//			map.put("status",1);
//			map.put("p",p);
//			
//			//JSON 라이브러리 활용------
//			//result="{\"status\":1, \"p\":{\"prod_no\": \""+p.getProdNo()+"\"}  }";
//			ObjectMapper mapper=new ObjectMapper();
////			String pJsonValue=mapper.writeValueAsString(p);
////			System.out.println("pJsonValue"+pJsonValue);
//			//result="{\"status\":1, \"p\":"+pJsonValue+"}";
//			
//			//map 객체를 이용 --------
//			String jsonValue=mapper.writeValueAsString(map);
//			System.out.println("jsonValue:"+jsonValue);
//			out.print(jsonValue);
			
//		ObjectMapper mapper=new ObjectMapper();//json문자열 편하게 쓰장
//		BoardRepository repo = new BoardRepository();
//		List<Board> boards;
//		try {
//			boards = repo.selectAll();
//		} catch (FindException e) {
//			e.printStackTrace();
//			boards = new ArrayList<>();
//		}
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
//DB에 자유기시판글이 하나 이상 있다면 
//나는 SELECT문에 있는 board_tb를 모두 html에 넣어주고 싶어요
//map.put("status",1);
//map.put("p",rs);
//
////map 객체를 이용 --------
//String jsonValue=mapper.writeValueAsString(map);
//System.out.println("jsonValue:"+jsonValue);
//out.print(jsonValue);


