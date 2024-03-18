package edu.kh.todoList.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoService;
import edu.kh.todoList.model.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("")
public class MainSerlvet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			TodoService service = new TodoServiceImpl();
				
			Map<String, Object> map = service.selectAll();
			
			List<Todo> todoList = (List<Todo>)map.get("todoList");
			int completeCount = (int)map.get("completeCount");
			
			req.setAttribute("todoList", todoList);
			req.setAttribute("completeCount", completeCount);
			
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		
	}
}
