package edu.kh.dept.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dto.Department;

public interface DepartmentDAO {

	/** 부서 전체 조회
	 * @param conn
	 * @return
	 */
	List<Department> selectAll(Connection conn) throws SQLException;

	/** 부서 추가
	 * @param conn
	 * @param dept
	 * @return result
	 * @thorws SQLException
	 */
	int insertDepartment(Connection conn, Department dept) throws SQLException;

	
	/** 부서 삭제
	 * @param conn
	 * @param deptId
	 * @return
	 * @throws SQLException
	 */
	int DeletDepartment(Connection conn, String deptId) throws SQLException;

	
	
	/** 부서 1행 조회
	 * @param conn
	 * @param deptId
	 * @return dept
	 * @throws SQLException
	 */
	Department selectOne(Connection conn, String deptId) throws SQLException;

	/** 부서수정
	 * @param conn
	 * @param dept
	 * @return result
	 * @throws SQLException
	 */
	int updateDepartment(Connection conn, Department dept) throws SQLException;



	List<Department> search(Connection conn, String keyword) throws SQLException;

	



	

}
