package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoService;
import edu.kh.todoList.model.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/detail")
public class ListDetailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			TodoService service = new TodoServiceImpl();
			
			int listNo = Integer.parseInt(req.getParameter("todoNo"));
			
			Todo todo = service.selectTodo(listNo);
		
			if(todo != null) {
				req.setAttribute("todo", todo);
				String path = "/WEB-INF/views/detail.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
			} else {
				req.getSession().setAttribute("message", "해당 목록이 존재하지 않습니다");
				
				resp.sendRedirect("/");
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
