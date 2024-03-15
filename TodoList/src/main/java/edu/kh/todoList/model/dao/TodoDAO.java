package edu.kh.todoList.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.todoList.model.dto.Todo;

public interface TodoDAO {

	/** 할 일 목록 조회
	 * @param conn
	 * @return todoList
	 * @throws SQLException
	 */
	List<Todo> selectAll(Connection conn) throws SQLException;
	
	/** 완료된 할 일 개수 조회
	 * @param conn
	 * @return completeCount
	 * @throws SQLException
	 */
	int getCompleteCount(Connection conn) throws SQLException;

	/** 할 일 추가
	 * @param conn
	 * @param todoTitle
	 * @param todoContent
	 * @return result
	 * @throws SQLException
	 */
	int addTodo(Connection conn, String todoTitle, String todoContent) throws SQLException;

	/** 할 일 상세 조회
	 * @param conn
	 * @param todoNo
	 * @return todo(조회 결과가 없으면 null)
	 * @throws SQLException
	 */
	Todo selectTodo(Connection conn, int todoNo) throws SQLException;

	/** 완료 여부 수정
	 * @param conn
	 * @param todoNo
	 * @param complete
	 * @return result
	 * @throws SQLException
	 */
	int changComplete(Connection conn, int todoNo, String complete) throws SQLException;


	/** 할 일 수정
	 * @param conn
	 * @param todo
	 * @return reuslt
	 * @throws SQLException
	 */
	int updateTodo(Connection conn, Todo todo) throws SQLException;

	/** 할 일 삭제
	 * @param conn
	 * @param todoNo
	 * @return result
	 * @throws SQLException
	 */
	int deletTodo(Connection conn, int todoNo) throws SQLException;

	

}
