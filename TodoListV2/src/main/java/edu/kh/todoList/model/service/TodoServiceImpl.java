package edu.kh.todoList.model.service;

import static edu.kh.todoList.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dao.TodoDAO;
import edu.kh.todoList.model.dao.TodoDAOImpl;
import edu.kh.todoList.model.dto.Todo;

public class TodoServiceImpl implements TodoService {

	private TodoDAO dao = null;
	
	public TodoServiceImpl() {
		dao = new TodoDAOImpl();
	}
	
	@Override
	public int addlist(String listTitle, String listContent) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.listadd(conn, listTitle, listContent);
		
		if(result > 0) commit(conn);
		else					 rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	@Override
	public Map<String, Object> selectAll() throws SQLException {
		
		Connection conn = getConnection();
		List<Todo> todoList = dao.selectAll(conn);
		int completeCount = dao.getCompleteCount(conn);
		close(conn);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		return map;
	}

	@Override
	public Todo selectTodo(int listNo) throws SQLException {
		
		Connection conn = getConnection();
		
		Todo todo = dao.selectTodo(conn, listNo);
		
		close(conn);
		
		return todo;
	}
	
	@Override
	public int deleteTodo(int listNo) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.deleteTodo(conn, listNo);
		
		if(result > 0) commit(conn);
		else					 rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	@Override
	public Todo selectOne(String listNo) throws SQLException {
		Connection conn = getConnection();
		
		Todo todo = dao.selectOne(conn, listNo);
		
		close(conn);
		
		return todo;
	}
}
