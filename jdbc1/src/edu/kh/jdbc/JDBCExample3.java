package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample3 {
	public static void main(String[] args) {
		// 입력 받은 최소 급여 보다 많이 받고(이상)
		// 입력 받은 최고 급여보단 적게 받는(이하)
		// 사원의  사번, 이름, 급여를 급여 내림차순 조회
		
		// [실행화면]
		// 최소 급여 : 1000000
		// 최대 급여 : 3000000
		
		// (사번) / (이름) / (급여)
		// (사번) / (이름) / (급여)
		// (사번) / (이름) / (급여)
		
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
		
			String type     = "jdbc:oracle:thin:@"; 
			String host     = "localhost";  
			String port     = ":1521";  
			String dbName   = ":xe";   
			String userName = "KH_SIJ"; 
			String pw       = "KH1234"; 
			
			
			conn
			= DriverManager.getConnection(type + host + port + dbName, userName, pw);
			
			Scanner sc = new Scanner(System.in);
			System.out.println("검색할 최소 급여 입력 : ");
			int input = sc.nextInt();
			System.out.println("검색할 최대 급여 입력 : ");
			int inpu = sc.nextInt();
			System.out.println("------------------------------------");
			
			String sql = "SELECT EMP_ID , EMP_NAME , SALARY  "
					+ "FROM EMPLOYEE "
					+ "JOIN JOB USING(JOB_CODE) "
					+ "WHERE   SALARY > "  + input
					+ "AND     SALARY <" + inpu
					+ " ORDER BY SALARY DESC";
			
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			
			while(rs.next()) { 
			
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int    salary  = rs.getInt("SALARY");
				
				
				System.out.printf("%s / %s / %d  \n",
						empId, empName, salary);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
				
				try { 
					if(rs   != null) rs.close();
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		
		
	}
}
