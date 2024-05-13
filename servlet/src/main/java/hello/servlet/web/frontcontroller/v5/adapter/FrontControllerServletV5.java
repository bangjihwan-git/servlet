package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet{
	
	private final Map<String, Object> handlerMappingMap;
	private final List<MyHandlerAdapter> handlerAdapters;
	
	public FrontControllerServletV5() {
		MyHandlerAdapterConfig config = new MyHandlerAdapterConfig();
		handlerMappingMap = config.getHandlerMappingMap();
		handlerAdapters = config.getHandlerAdapters();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object handler = getHandler(request);
		
		if(handler == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		MyHandlerAdapter adapter = getHandlerAdapter(handler);

		
		ModelView mv = adapter.handle(request, response, handler);
		
		String viewName = mv.getViewName();
		
		MyView view = viewResolver(viewName);
		view.render(mv.getModel(), request, response);
	}

	private MyHandlerAdapter getHandlerAdapter(Object handler) {
		for (MyHandlerAdapter adapter : handlerAdapters) {
			if(adapter.supports(handler)) {
				return adapter;
			}
		}
		throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler" + handler);
	}

	private Object getHandler(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return handlerMappingMap.get(requestURI);
	}

	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/"+viewName+".jsp");
	}
	
}
