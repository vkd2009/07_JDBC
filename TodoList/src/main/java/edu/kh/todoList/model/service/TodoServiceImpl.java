package edu.kh.todoList.model.service;

// 지정된 위치의 static을 모두 가져와 사용
import static edu.kh.todoList.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dao.TodoDAO;
import edu.kh.todoList.model.dao.TodoDAOImpl;
import edu.kh.todoList.model.dto.Todo;

// Service : 비지니스 로직 처리
// - 데이터 가공, 트랜잭션 제어 처리 (실제 업무 처리, 공장에서 불량품 검수 같은 것) 
public class TodoServiceImpl implements TodoService {

	// DAO 객체 생성
	private TodoDAO dao = null;
	
	public TodoServiceImpl() {
		dao = new TodoDAOImpl();
	}
	
	// 할 일 목록 + 완료된 할 일 개수 조회
	@Override
	public Map<String, Object> selectAll() throws SQLException {
		
		// 1. 커넥션 얻어오기 (jdbc complite에서 얻어옴)
		Connection conn = getConnection();
		
		// 2. 할 일 목록 조회 DAO 메서드 호출 후 결과 반환 받기
		List<Todo> todoList = dao.selectAll(conn);
		
		// 3. 완료된 할 일 개수 조회 DAO 메서드 호출 후 결과 반환 받기
		int completeCount = dao.getCompleteCount(conn);
		
		// 4. Connection 반환
		close(conn);
		
		// 5. Map을 생성해서 DAO 호출 결과를 한 번에 묶어서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		return map;
		
	}
	
	
	// 할 일 추가
	@Override
	public int addTodo(String todoTitle, String todoContent) throws SQLException {
		
		Connection conn = getConnection(); // 커넥션 생성
		
		int result = dao.addTodo(conn, todoTitle, todoContent); // dao 호출
		
		// 트랜잭션 제어 처리
		if (result > 0) commit(conn);
		else						rollback(conn);
		
		close(conn); // 커넥션 반환
		
		return result;
	}
	
//	할 일 상세 조회
	@Override
	public Todo selectTodo(int todoNo) throws SQLException {
		
		Connection conn = getConnection();
		
		Todo todo = dao.selectTodo(conn, todoNo);
		
		close(conn);
		
		return todo;
	}
	
	// 완료 여부 수정
	@Override
	public int changeComplete(int todoNo, String complete) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.changComplete(conn, todoNo, complete);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	@Override
	public int updateTodo(Todo todo) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.updateTodo(conn, todo);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	@Override
	public int deletTodo(int todoNo) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.deletTodo(conn, todoNo);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
}
