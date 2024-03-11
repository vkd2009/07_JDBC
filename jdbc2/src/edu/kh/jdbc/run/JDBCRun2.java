package edu.kh.jdbc.run;

import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.model.dao.Department;
import edu.kh.jdbc.model.dao.DepartmentDAO;

public class JDBCRun2 {
	public static void main(String[] args) {
		
		DepartmentDAO dao = new DepartmentDAO();
		
		try {
			// 1. 부서 추가
			Scanner sc = new Scanner(System.in);
			
			System.out.print("부서 코드 입력 : ");
			String deptId = sc.next();
			
			System.out.print("부서명 입력 : ");
			String deptTitle = sc.next();
			
			System.out.print("지역 코드 입력 : ");
			String locationId = sc.next();
			
			
			// ** DAO 메서드 호출 후 결과 반환 받기 **
			Department dept = new Department(deptId, deptTitle, locationId);
			
			int result = dao.insertDepartment(dept);
			
			if(result > 0) {
				System.out.println("[삽입 성공]");
				
				// 2. 부서 전체 조회
				List<Department> deptList = dao.selectAll();
				
				for(Department d : deptList) {
					System.out.println(d.toString());
				}
				
				
			}else {
				System.out.println("[삽입 실패");
			}
			
			
			
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
