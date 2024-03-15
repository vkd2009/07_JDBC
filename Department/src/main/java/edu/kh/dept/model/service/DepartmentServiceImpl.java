package edu.kh.dept.model.service;

//JDBCTemplate 클래스의 static 메서드 모두 가져오기
import static edu.kh.dept.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dao.DepartmentDAO;
import edu.kh.dept.model.dao.DepartmentDAOImpl;
import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;

// Service
// - 비즈니스 로직 처리
//  (데이터 가공, 트랜잭션 제어 처리)

// - 트랜잭션 제어 처리
//  -> 하나의 Serivce 메서드가 여러 DAO 메서드를 호출할 수 있다!
//   - 호출한 모드 DAO 메서드가 성공했을 때 Commit
//   - 하나라도 실패하면 Rollback 수행

public class DepartmentServiceImpl implements DepartmentService {

	//DAO 객체 생성
	private DepartmentDAO dao = new DepartmentDAOImpl();

	@Override
	public List<Department> selectAll() throws SQLException {
		
		/* 1. 커넥션 얻어오기*/
		Connection conn = getConnection();
		
		/* 2. DAO 메서드 호출 (매개 변수로 Connection 전달) + 조회 결과 반환 */
		List<Department> deptList = dao.selectAll(conn);
		
		/* 3. SELECT는 트랜잭션 제어 필요 없음*/
		
		/* 4. 사용 완료된 Connection 닫기*/
		close(conn);
		
		/*5. 결과 반환*/
		
		return deptList;
	}

	
	// 부서 추가 서비스
	@Override
	public int insertDepartment(Department dept) throws DepartmentInsertException {
		
		int result = 0; // 결과 저장용 변수
		
		// 1. 커넥션 얻어오기
		Connection conn = getConnection();
		
		try {
		// 2. DAO 메서드 호출 후 결과 반환 받기
		// (DAO 메서드 수행 시 커넥션이 필요하기 때문에 매개변수로 전달!)
		result = dao.insertDepartment(conn, dept);
		
		// 3. DAO 수행 결과에 따라 트랜잭션 제어
		if (result > 0) commit(conn);
		else						rollback(conn);
		
	  } catch(Exception e) {
	  	// PK 제약조건 위배 / NOT NULL 제약 조건 위배
	  	
	  	e.printStackTrace();
	  	
	  	// 예외 발생 시 무조건 rollback
	  	rollback(conn);
	  	
	  	/* 제약조건 위배로 인해서 정상 수행 되지 않음을 표현하기 위해
	  	 * 강제로 예외 발생!!! - 사용자 정의 예외 이용*/
	  	
	  	throw new DepartmentInsertException();
	  	
	  	
	  } finally { // 예외 발생 여부 관계 없이 무조건 커넥션 close
	  	
		// 4. 커넥션 반환 처리
		close(conn);
	  }
		
		// 5. 결과 반환
		return result;
	}
	
	// 여러 부서 추가하기
	@Override
	public int multiInsert(List<Department> deptList) throws DepartmentInsertException{
		
		// 1. Connection 얻어오기
		Connection conn = getConnection();
		
		int result = 0; // 삽입된 행의 개수 누적
		
		String currentDeptId = null; // 현재 삽입하려는 부서 코드를 저장하는 변수

		
		try {
			
			// 2. 절달 받은 deptList의 크기(길이) 만큼 반복하며
			// DB에 INSERT하는 DAO 메서드 호출
			
			for(Department dept : deptList) {
				
				currentDeptId = dept.getDeptId(); // 삽입할 deptId를 변수에 저장
				
				result += dao.insertDepartment(conn, dept);
			}
			
			// 3. 트랜젝션 제어 처리
			// result에 누적된 값(== 삽입 성공한 행의 개수)과
			// deptList의 길이가 같은 경우
			// == 모두 삽입 성공한 경우 -> commit
			
			// result != deptList의 길이
			// == 삽입 실패한 경우가 존재 -> rollback
			
			if(result == deptList.size()) commit(conn);
			else													rollback(conn);
		
			
		}catch(SQLException e) {
			
			// 4. SQL 수행 중 오류 발생 시
			// (PK, NOT NULL 제약조건 위배, 데이터 크기 초과, DB 연결 종료)
			
			e.printStackTrace();
			rollback(conn); // SQL이 하나라도 실패하면 전체 rollback
			
			// 예외가 발생이 되었음을 Controller(Servlet)에 전달 하기
			// -> 사용자 정의 예외 강제 발생
			
			// (PK 제약조건 위배만 생각하고 코드 작성)
			throw new DepartmentInsertException(currentDeptId + "부서 코드가 이미 존재합니다");
			
			
		} finally {
			// 5. 사용 완료된 커넥션 반환
			close(conn);
		}
		
		// 6. 결과 반환
		return result;
	}


	
	
	@Override
	public int DeletDepartment(String deptId) throws SQLException {
		
			
		Connection conn = getConnection();
			
		int result = dao.DeletDepartment(conn, deptId);
		  
		  if (result > 0) commit(conn);
		  else            rollback(conn);
		  
			close(conn);
		
	
		
		return result;
	}
	
	@Override
	public Department selectOne(String deptId) throws SQLException {
		
		// 1. 커넥션 얻어오기
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		Department dept = dao.selectOne(conn, deptId);
		
		// 3. 커넥션 반환
		close(conn);
		
		// 4. 결과 반환
		
		return dept;
	}
	
	
	@Override
	public int updateDepartment(Department dept) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.updateDepartment(conn, dept);
			
			if(result > 0)    commit(conn);
			else							rollback(conn);
			
	
		
			close(conn);
		
		
		return result;
	}
	

	
	// 부서명 검색
	@Override
	public List<Department> search(String keyword) throws SQLException {
			
		// 1. 커넥션 생성
		Connection conn = getConnection();
		
		// 2. dao 메서드 호출 후 결과 반환 받기
		List<Department> deptList = dao.search(conn, keyword);
		
		close(conn);
		
		/*5. 결과 반환*/
		
		return deptList;
		
	}
	
}





