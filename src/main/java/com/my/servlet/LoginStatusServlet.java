package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

// import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/loginstatus")
public class LoginStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginInfo");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object>map = new HashMap<>();
		if(loginedId == null) {
			map.put("loginInfo",  "0");
		}else {
			map.put("loginInfo",  "1");
			
		}
		response.setContentType("application/json;charset=UTF-8");;
		PrintWriter out = response.getWriter();
		out.print(mapper.writeValueAsString(map));
	}

}
