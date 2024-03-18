package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.service.TodoService;
import edu.kh.todoList.model.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/list/add")
public class ListAddServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			TodoService service = new TodoServiceImpl();
			
			String listTitle = req.getParameter("listTitle");
			String listContent = req.getParameter("listContent");
			
			int result = service.addlist(listTitle, listContent);
			
			String message = null;
			
			if(result > 0) message = "추가 성공!";
			else    			 message = "추가 실패....";
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			resp.sendRedirect("/");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
