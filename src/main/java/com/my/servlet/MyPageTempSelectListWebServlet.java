package com.my.servlet;

import java.io.IOException;
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

import com.google.gson.Gson;
import com.my.dto.MyPageTemp;
import com.my.dto.Result;
import com.my.repository.MyPagePostRepository;

/**
 * Servlet implementation class MyPageWebServlet
 */

//내가 정의한 URL을 입력하면
//request.getRequestDispatcher 여기에 정의한 html로 이동시켜준다.
@WebServlet("/mypagetempselectlist")
public class MyPageTempSelectListWebServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public MyPageTempSelectListWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		doGet(request, response);
//		
//	}
	
	/**
	 * Servlet implementation class AjaxStudy
	 *
	 * 	Ajax란?	(출처 - 위키백과)
	 	기존의 웹 애플리케이션은 폼을 채우고 이를 제출(submit)하면 웹 서버는 요청된 내용에 따라서 데이터를 가공하여 새로운 웹 페이지를 작성하고 응답으로 되돌려준다. 
	 	이때 최초에 폼을 가지고 있던 페이지와 결과물로써 되돌려 받은 페이지는 일반적으로 유사한 내용을 가지고 있는 경우가 많다. 
	 	결과적으로 중복되는 HTML 코드를 다시 한번 전송을 받음으로써 많은 대역폭을 낭비하게 된다. 
	 	이는 금전적 손실을 야기할 수 있으며 사용자와 대화하는 서비스를 만들기 어렵게도 한다.
	 	반면에 Ajax는 필요한 데이터만을 웹 서버에 요청해서 받은 후 클라이언트에서 데이터에 대한 처리를 할 수 있다. 
	 	웹 서버에서 전적으로 처리되던 데이터 처리의 일부분이 클라이언트 쪽에서 처리되므로 웹 브라우저와 웹 서버 사이에 교환되는 
	 	데이터량과 웹 서버의 데이터 처리량도 줄어들기 때문에 애플리케이션의 응답성이 좋아진다.
	 	장점
	  	- 페이지 이동없이 고속으로 화면을 전환할 수 있다.
	  	- 서버 처리를 기다리지 않고, 비동기 요청이 가능하다.
	  	- 수신하는 데이터 양을 줄일 수 있고, 클라이언트에게 처리를 위임할 수도 있다.
	 	단점
	  	- Ajax를 쓸 수 없는 브라우저에 대한 문제가 있다.
	  	- HTTP 클라이언트의 기능이 한정되어 있다.
	  	- 동일-출처 정책으로 인해 다른 도메인과는 통신이 불가능하다.
	 *
	 */

	/**
	 * 나의 임시글 목록을 조회한다.
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ajax를 doPost로 호출한다. ");

		//ResultDTO란???? ajax 응답결과를 공통 보여주게 한다.
		Result<List<MyPageTemp>> resp = new Result<>();
		
		MyPagePostRepository repository =new MyPagePostRepository();

		try {
			//임시저장글 목록을 조회한다.
			List<MyPageTemp> list = repository.tempProjectList();
			// 에러가 발생시 처리한다. 응답 response에 오류를 알린다.
			resp.setResponse(list);
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
