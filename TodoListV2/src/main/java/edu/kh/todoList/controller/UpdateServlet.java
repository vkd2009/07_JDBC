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

@WebServlet("/todoTitle/update")
public class UpdateServlet extends HttpServlet{

	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String listNo = req.getParameter("listTitle");
		
		try {
			TodoService service = new TodoServiceImpl();
			
			Todo todo = service.selectOne(listNo);
			
			if(todo == null) {
				req.setAttribute("todo", todo);
				String path = "/WEB-INF/views/update.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
				
			} else { 
				req.getSession().setAttribute("message", "해당 제목이 존재하지 않습니다");
				resp.sendRedirect("/"); 
				 
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
