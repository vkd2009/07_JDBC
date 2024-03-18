package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.service.TodoService;
import edu.kh.todoList.model.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/delete")
public class ListDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			TodoService service = new TodoServiceImpl();
			
			int listNo = Integer.parseInt(req.getParameter("todoNo"));
			
			int result = service.deleteTodo(listNo);
			
			String path = null;
			String message = null;
			
			if(result > 0) {
				path = "/";
				message = "삭제 되었다.";
				
			}else {
				path = "/todo/detail?todoNo=" + listNo;
				message = "삭제 실패";
			}
			
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(path);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
