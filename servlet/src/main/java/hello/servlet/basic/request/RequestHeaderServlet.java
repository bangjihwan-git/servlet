package hello.servlet.basic.request;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "requestHeaderServlet" , urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		request.getHeaderNames().asIterator()
			   .forEachRemaining(headerName -> System.out.println(headerName + ":" + headerName));
	}
}
