package com.my.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.Project;
import com.my.exeception.FindException;
import com.my.sql.MyConnection;


public class projectRepository {

	public int insert(Project p) throws FindException, SQLException {
		Connection con = null;
		int res = 0;
		try {
			con = MyConnection.getConnection();
			res = insertproject(con, p);
//			insertpCategory(con, p.getP_category());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, con);
		}
		return res;
	}	
	// 임시저장글 insert, catogiry도 테이블에 한번에 추가함
	   public int tmp_insert(Project p, List<Integer> list) throws SQLException {
	      int res = 0;
	      Connection con = null;
	      con = MyConnection.getConnection();
	      PreparedStatement pstmt = null;
	      String tmpinsertSQL = "insert into p_temp_tb(ptemp_no, user_no, ptemp_title, ptemp_content,ptemp_createday,\n"
	            + "ptemp_deadlineday,ptemp_updateday, ptemp_deleteday, ptemp_category1, ptemp_category2, ptemp_category3)\n"
	            + "values(sq_ptemp_no.nextval,?,?,?,SYSDATE,?,null,null,?,?,?)";
	      
	      pstmt = con.prepareStatement(tmpinsertSQL);
	      pstmt.setString(1, p.getuser_no());
	      pstmt.setString(2, p.getP_title());
	      pstmt.setString(3, p.getP_content());
	      pstmt.setString(4, p.getP_deadlineday());
	      //카테고리 수정 나중에
	      pstmt.setInt(5, list.get(0));
	      pstmt.setInt(6, list.get(1));
	      pstmt.setInt(7, list.get(2));
	      
	      res = pstmt.executeUpdate();
	      return res;

	   }
	public int insertpCategory(List<Integer> list) throws SQLException {
		Connection con = null;
		// 카테고리 코드가 입력된 만큰 테이블에 추가
		con = MyConnection.getConnection();
		PreparedStatement pstmt_pno = null;
		ResultSet rs = null;
		int res = 0;
		String selectLastPno = "select p_no from "
				+ "(select * from project_tb order by p_no desc) "
				+ "where rownum=1";
		pstmt_pno = con.prepareStatement(selectLastPno); 
		rs = pstmt_pno.executeQuery();//예: 150
		rs.next();
		int p_no = rs.getInt("p_no");
		System.out.println(rs);
		System.out.println(pstmt_pno);
		//리스트에 들어간 카테고리 수 만큼 반복
		for(int i=0; i<list.size(); i++) {
			
			PreparedStatement pstmt = null;
			String insertpCategorySQL = 
					"INSERT INTO p_category VALUES (?, ?)";
			pstmt = con.prepareStatement(insertpCategorySQL);
			
			pstmt.setInt(1, p_no );
			pstmt.setInt(2, list.get(i));
			res = pstmt.executeUpdate(); 
		}
		return res;
		
		
	}
	private int insertproject(Connection con, Project p) throws SQLException {
		int res = 0;
		PreparedStatement pstmt = null;
		String insertInfoSQL = 
				"INSERT INTO project_tb (p_no,user_no,p_title,p_content,p_createday,p_deadlineday) "
				+ "VALUES (sq_tb_no.nextval, ?, ?, ? ,SYSDATE, ?)";
		pstmt = con.prepareStatement(insertInfoSQL);
		pstmt.setString(1, p.getuser_no());
		pstmt.setString(2, p.getP_title());
		pstmt.setString(3, p.getP_content());
		pstmt.setString(4, p.getP_deadlineday());
		res = pstmt.executeUpdate();
		return res;
	}
	
	public List<Project> selectAll() throws FindException {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String selectprojectSQL = "SELECT * FROM project_tb order by p_no desc";
	      try {
	         con = MyConnection.getConnection();
	         pstmt = con.prepareStatement(selectprojectSQL);
	         rs = pstmt.executeQuery();
	         List<Project> list = new ArrayList<>();
	         while(rs.next()) {
	            int p_no = rs.getInt("p_no");
	            String user_no = rs.getString("user_no");
	            String p_title = rs.getString("p_title");
	            String p_content = rs.getString("p_content");
	            int p_views = rs.getInt("p_view");
	            String p_createday = rs.getString("p_createday");
	            String p_deadlineday = rs.getString("p_deadlineday");
	            String p_updateday = rs.getString("p_updateday");
	            String p_deleteday = rs.getString("p_deleteday");
	            Project p = new Project(p_no, user_no, p_title, p_content, p_views, p_createday, p_updateday, p_deadlineday, p_deleteday);
	            list.add(p);
	         }
	         if(list.size()==0) {
	            throw new FindException("아무고토 없습니다");
	         }
	         return list;
	      }catch(SQLException e) {
	         e.printStackTrace();
	         throw new FindException(e.getMessage());
	      } finally {
	         MyConnection.close(rs, pstmt, con);
	      }
	   }
	// 프로젝트 글 제목으로 검색
	public Project selectByptitle(int p_no) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectpnoSQL = "SELECT * FROM project_tb WHERE p_no=?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectpnoSQL);
			pstmt.setLong(1, p_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String userNo = rs.getString("user_no");
				String p_title = rs.getString("p_title");
				String p_content = rs.getString("p_content");
				String createDate = rs.getString("p_createday");
				String deadlineDate = rs.getString("p_deadlineday");
				
				Project p = new Project(p_no, userNo, p_title, p_content, createDate, deadlineDate );
				return p;
			}else {
				throw new FindException("글이 없습니다");
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	
		
	}
	public void updateView(int p_no, int p_viewcount) throws SQLException {
		//조회수 올리기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectpnoSQL = "update project_tb set p_view=? where p_no=?";
		con = MyConnection.getConnection();
		pstmt = con.prepareStatement(selectpnoSQL);
		pstmt.setLong(1, p_viewcount+1);
		pstmt.setLong(2, p_no);
		pstmt.executeQuery();
		System.out.println("update 완료");
		//con.commit();
	}
	public Project selectByprojectNumber(int p_no, int p_viewcount) throws FindException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//@@@@@@@@@@@@@@@@@@@@@
		updateView(p_no, p_viewcount);
		
		String selectpnoSQL = "SELECT * FROM project_tb WHERE p_no=?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectpnoSQL);
			pstmt.setLong(1, p_no);
			rs = pstmt.executeQuery();
			System.out.println(rs);
			if(rs.next()) {
				String user_no = rs.getString("user_no");
				String p_title = rs.getString("p_title");
				String p_content = rs.getString("p_content");
				int p_view = rs.getInt("p_view");
				String p_createDay = rs.getString("p_createday");
				String p_deadlineDay = rs.getString("p_deadlineday");
				String p_updateDay = rs.getString("p_updateday");
				String p_deleteDay = rs.getString("p_updateday");
				Project p = new Project(p_no, user_no, p_title, p_content, p_view, p_createDay, p_deadlineDay, p_updateDay, p_deleteDay );
				return p;
			}else {
				throw new FindException("글이 없습니다");
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	
		
	}
	public int update(Project p) throws SQLException {
		Connection con = null;
		int res = 0;
		PreparedStatement pstmt = null;
		con = MyConnection.getConnection();
		
		String updateprojectSQL = 
				"update project_tb set p_title=?, p_content=?, p_deadlineday=? "
				+ "where p_no=? ";
		pstmt = con.prepareStatement(updateprojectSQL);
		
		pstmt.setString(1, p.getP_title());
		pstmt.setString(2, p.getP_content());
		pstmt.setString(3, p.getP_deadlineday());
		pstmt.setLong(4, p.getp_no());
		res = pstmt.executeUpdate();
		return res;
	
	}


	public int deleteproject(int p_no) throws SQLException {
		Connection con = null;
		int res = 0;
		PreparedStatement pstmt = null;
		String deleteprojectSQL = 
				"delete project_tb where p_no=? ";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(deleteprojectSQL);
			pstmt.setLong(1, p_no);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, con);
		}
		return res;
	}					
	
	public int deletepcategory(int p_no) {
		Connection con = null;
		int res = 0;
		PreparedStatement pstmt = null;
		String deletepcategorySQL = "delete p_category where p_no=?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(deletepcategorySQL);
			pstmt.setLong(1, p_no);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, con);
		}
		return res;
	}
	public List<Project> projectList(int pageNumber) throws SQLException {
		List<Project> list = new ArrayList<Project>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String listSQL = 
					"select * from ( select * from project_tb where p_no<200 and p_deleteday is null order by p_no desc)\n"
					+ "where rownum<=10";
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(listSQL);
			System.out.println(listSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int p_no = rs.getInt("p_no");
				String userNo = rs.getString("user_no");
				String p_title = rs.getString("p_title");
				String p_content = rs.getString("p_content");
				int p_view = rs.getInt("p_view");
				String createDay = rs.getString("p_createday");
				String updateDay = rs.getString("p_updateday");
				String deadlineDay = rs.getString("p_deadlineday");
				String deleteDay = rs.getString("p_deleteday");
				
				Project p = new Project(p_no, userNo, p_title, p_content, p_view, createDay,updateDay, deadlineDay,deleteDay);
				list.add(p);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return list;
	}
//	public ArrayList<Project> getList(int pageNumber) throws SQLException{
//		Connection con = MyConnection.getConnection();
//		ResultSet rs = null;
//		String SQL = "SELECT * FROM PROJECT_TB wher p_no<? and p_deleteday is null "
//				+ "ORDER BY P_NO DESC rownum=10";
//		ArrayList<Project> list = new ArrayList<Project>();
//		try {
//			PreparedStatement pstmt = con.prepareStatement(SQL);
//			pstmt.setInt(1, getNext());
//			rs = pstmt.executeQuery();
//		while(rs.next()) {
//			Project p = new Project();
//			
//		}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return -1;
//		
//	}
	public String getDate() throws SQLException {
		Connection con = MyConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT NOW()";
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {// 결과가 있는 경우
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public int getNext() throws SQLException { //다음글 +1
		Connection con = MyConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * from project_tb order by p_No";
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//첫번쨰 게시글
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	public boolean nextPage(int pageNumber) throws SQLException { // 페이징 처리
		Connection con = MyConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String listSQL = 
				"SELECT * FROM PROJECT_TB wher p_no<? and p_deleteday is null "
				+ "ORDER BY P_NO DESC rownum=10";
		try {
			pstmt = con.prepareStatement(listSQL);
			pstmt.setInt(1, getNext()-(pageNumber -1)*10);
			System.out.println(listSQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return false;
		
	}
}
