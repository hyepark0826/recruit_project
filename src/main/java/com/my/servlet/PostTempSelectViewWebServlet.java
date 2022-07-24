package com.my.servlet;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/posttempselectview")
public class PostTempSelectViewWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostTempSelectViewWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);

	}

	/**
	 * 나의 작성글을 상세 조회한다.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("postTempSelectView ajax를 doPost로 호출한다. ");
		
		//글 번호
		int pNo = Integer.parseInt(request.getParameter("pNo").toString());

		//ResultDTO란???? ajax 응답결과를 공통 보여주게 한다.
		Result<MyPageTemp> resp = new Result<>();
		
		MyPagePostRepository repository =new MyPagePostRepository();

		try {
			//임시저장글 상세를 조회한다.
			MyPageTemp dto = repository.postTempSelectView(pNo);
			// 에러가 발생시 처리한다. 응답 response에 오류를 알린다.
			resp.setResponse(dto);
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