package com.my.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.my.dto.MyPagePost;
import com.my.dto.Result;
import com.my.repository.MyPagePostRepository;

/**
 * Servlet implementation class MyPageWebServlet
 */
@WebServlet("/mypagepostupdate")
public class MyPagePostUpdateWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPagePostUpdateWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
    * 	나의 모집글 수정페이지 이동
    */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/project_html/myPagePostUpdate.html").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("myPagePostUpdate ajax를 doPost로 호출한다. ");

		//ResultDTO란???? ajax 응답결과를 공통 보여주게 한다.
		Result<Integer> resp = new Result<>();
		
		int result = 0;
		
		//글 번호
		String pNo = request.getParameter("pNo");
		//제목
		String pTitle = request.getParameter("pTitle");
		//내용
		String pContent = request.getParameter("pContent");
		//사용자
		String userNo = request.getParameter("userNo");
		
		System.out.println("pNo 		: 	"+pNo);
		System.out.println("pTitle		: "+pTitle);
		System.out.println("pContent	: "+pContent);
		System.out.println("userNo 		: "+userNo);
		
		MyPagePostRepository repository =new MyPagePostRepository();
		
		MyPagePost dto = new MyPagePost(Integer.parseInt(pNo), userNo, pTitle, pContent);

		try {
			//임시저장글 상세를 조회한다.
			result = repository.myPagePostUpdate(dto);
			
			// 에러가 발생시 처리한다. 응답 response에 오류를 알린다.
			resp.setResponse(result);
		} catch (Exception e) {
			resp.setMessage(e.toString());
			resp.setCheck(false);
		}
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //Gson 라이브러리로 json을 리턴한다.
        new Gson().toJson(resp, response.getWriter());

	}

}
