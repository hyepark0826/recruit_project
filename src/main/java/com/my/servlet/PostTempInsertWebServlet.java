package com.my.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.my.dto.MyPageTemp;
import com.my.dto.Result;
import com.my.repository.MyPagePostRepository;

/**
 * Servlet implementation class MyPageWebServlet
 */
@WebServlet("/posttempinsert")
public class PostTempInsertWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostTempInsertWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
    * 	임시저장 수정페이지 이동
    */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("postTempUpdate ajax를 doPost로 호출한다. ");

		//ResultDTO란???? ajax 응답결과를 공통 보여주게 한다.
		Result<Integer> resp = new Result<>();
		
		int result = 0;
		
		//글 번호
		String ptempNo = request.getParameter("ptempNo");
		//제목
		String ptempTitle = request.getParameter("ptempTitle");
		//내용
		String ptempContent = request.getParameter("ptempContent");
		//사용자
		String userNo = request.getParameter("userNo");
		
		System.out.println("ptempNo : "+ptempNo);
		System.out.println("ptempTitle : "+ptempTitle);
		System.out.println("ptempContent : "+ptempContent);
		System.out.println("userNo : "+userNo);
		
		MyPagePostRepository repository =new MyPagePostRepository();
		
		MyPageTemp dto = new MyPageTemp(Integer.parseInt(ptempNo), userNo, ptempTitle, ptempContent);

		try {
			//Step 1. 임시글의 내용을 모집글로 복사한다.
			result = repository.postTempCopyForProject(dto);
			
			System.out.println("Step1. result : "+result);
			
			if(result > 0) {
				//Step 2. 현재 임시글을 삭제한다.
				result = repository.postTempDelete(dto);		
				System.out.println("Step2. result : "+result);
			}
			resp.setResponse(result);
			
		} catch (Exception e) {
			// 에러가 발생시 처리한다. 응답 response에 오류를 알린다.
			resp.setMessage(e.toString());
			resp.setCheck(false);
		}
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //Gson 라이브러리로 json을 리턴한다.
        new Gson().toJson(resp, response.getWriter());

	}

}
