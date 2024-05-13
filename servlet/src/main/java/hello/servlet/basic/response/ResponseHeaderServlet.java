package hello.servlet.basic.response;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//[status-line]
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		//[response-headers]
		response.setHeader("Content-Type", "text/plain;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("my-header", "hello");
		cookie(response);
		response.getWriter().println("ok");
	}
	
	private void cookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("myCookie", "good");
		cookie.setMaxAge(600);
		response.addCookie(cookie);
	}
}
