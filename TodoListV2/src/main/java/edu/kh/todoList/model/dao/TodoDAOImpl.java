package edu.kh.todoList.model.dao;

import static edu.kh.todoList.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.todoList.model.dto.Todo;

public class TodoDAOImpl implements TodoDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	
	public TodoDAOImpl() {
	
		try {
			prop = new Properties();
			String path = TodoDAOImpl.class.getResource("/edu/kh/todoList/sql/sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(path));
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	@Override
	public int listadd(Connection conn, String listTitle, String listContent) throws SQLException {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("listadd");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, listTitle);
			pstmt.setString(2, listContent);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	@Override
	public List<Todo> selectAll(Connection conn) throws SQLException {
		
		// 결과 저장용 변수 선언 / 객체 생성
		List<Todo> todoList = new ArrayList<Todo>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			// SQL 수행 후 결과 반환 받기
			rs = stmt.executeQuery(sql);
			
			// 조회 결과 한 행씩 접근
			while(rs.next()) {
				int listNo = rs.getInt("TODO_NO");
				String listTitle = rs.getString("TODO_TITLE");
				String complete = rs.getString("COMPLETE");
				String regDate = rs.getString("REG_DATE");
				
				// Todo객체를 생성해서 값 세팅 후 todoList에 추가
				Todo todo = new Todo(listNo, listTitle, complete, regDate);
				todoList.add(todo);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return todoList;
	}	

	
//완료된 할 일 조회
	@Override
	public int getCompleteCount(Connection conn) throws SQLException {
	
		int completeCount = 0;
		
		try {
			String sql = prop.getProperty("getCompleteCount");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			// -> GROUP BY가 없는 SELECT의 COUNT 그룹 함수의 결과는 1행!
			// -> if 문을 이용해서 조회 결과 행 접근
			
			if(rs.next()) {
				completeCount = rs.getInt(1); // 1번 컬럼 값
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
	 return completeCount;
	}	
	
	
	@Override
	public Todo selectTodo(Connection conn, int listNo) throws SQLException {

		Todo todo = null;
		
		try {
			String sql = prop.getProperty("selectTodo");
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, listNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				todo = new Todo();
				
				todo.setListNo			(rs.getInt("TODO_NO"));
				todo.setListTitle		(rs.getString("TODO_TITLE"));
				todo.setListContent	(rs.getString("TODO_CONTENT"));
				todo.setComplete		(rs.getString("COMPLETE"));
				todo.setRegDate			(rs.getString("REG_DATE"));
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return todo;
	}
	
	
	
	
	@Override
	public int deleteTodo(Connection conn, int listNo) throws SQLException {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deletTodo");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, listNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	@Override
	public Todo selectOne(Connection conn, String listNo) throws SQLException {
		
		Todo todo = null;
		
		try {
			
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, listNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				   todo = new Todo();
						todo.setListNo			(rs.getInt("TODO_NO"));
						todo.setListTitle		(rs.getString("TODO_TITLE"));
						todo.setListContent	(rs.getString("TODO_CONTENT"));
						todo.setComplete		(rs.getString("COMPLETE"));
						todo.setRegDate			(rs.getString("REG_DATE"));
						
			}
						
				
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return todo;
	}
	
	
	
	
}