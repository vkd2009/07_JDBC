package edu.kh.dept.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/department/search")
public class SearchServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			String keyword = req.getParameter("keyword"); // 검색어
			
			DepartmentService service = new DepartmentServiceImpl();
			
		
			
			List<Department> deptList = service.search(keyword);
			
			
			req.setAttribute("deptList", deptList);	
			// forward할 JSP 경로
			String path = "/resources/WEB-INF/views/search.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	
	
}
