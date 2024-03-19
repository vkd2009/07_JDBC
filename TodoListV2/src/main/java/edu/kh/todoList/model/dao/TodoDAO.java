package edu.kh.todoList.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.todoList.model.dto.Todo;

public interface TodoDAO {

	int listadd(Connection conn, String listTitle, String listContent) throws SQLException;

	List<Todo> selectAll(Connection conn) throws SQLException;

	int getCompleteCount(Connection conn) throws SQLException;

	Todo selectTodo(Connection conn, int listNo) throws SQLException;

	int deleteTodo(Connection conn, int listNo) throws SQLException;

	Todo selectOne(Connection conn, String listNo) throws SQLException;


	

	

}
