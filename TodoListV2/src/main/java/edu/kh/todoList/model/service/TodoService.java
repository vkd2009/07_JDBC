package edu.kh.todoList.model.service;

import java.sql.SQLException;
import java.util.Map;

import edu.kh.todoList.model.dto.Todo;

public interface TodoService {

	/** 할일 추가
	 * @param listTitle
	 * @param listContent
	 * @return
	 * @throws SQLException
	 */
	int addlist(String listTitle, String listContent) throws SQLException;

	Map<String, Object> selectAll() throws SQLException;

	Todo selectTodo(int listNo) throws SQLException;

	int deleteTodo(int listNo) throws SQLException;

	Todo selectOne(String listNo) throws SQLException;

	
}