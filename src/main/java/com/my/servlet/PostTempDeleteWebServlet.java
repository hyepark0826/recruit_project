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
@WebServlet("/posttempdelete")
public class PostTempDeleteWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostTempDeleteWebServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * 임시글 삭제 ajax 호출
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("postTempDelete ajax를 doPost로 호출한다. ");

		// ResultDTO란???? ajax 응답결과를 공통 보여주게 한다.
		Result<Integer> resp = new Result<>();

		int result = 0;

		// 글 번호
		String ptempNo = request.getParameter("ptempNo");

		System.out.println("ptempNo : " + ptempNo);

		MyPagePostRepository repository = new MyPagePostRepository();

		MyPageTemp dto = new MyPageTemp();

		dto.setPtempNo(Integer.parseInt(ptempNo));

		try {
			// repository에서 delete sql문이 성공하면 1을 반환한다.
			result = repository.postTempDelete(dto);

			// 에러가 발생시 처리한다. 응답 response에 오류를 알린다.
			resp.setResponse(result);
		} catch (Exception e) {
			resp.setMessage(e.toString());
			resp.setCheck(false);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// Gson 라이브러리로 json을 리턴한다.
		new Gson().toJson(resp, response.getWriter());

	}

}

