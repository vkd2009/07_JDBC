package edu.kh.dept.model.dto;

// DTO 객체 하나가 DEPARTMENT4 테이블의 한 행 데이터를 저장하는 용도
public class Department {

	private String deptId;     //부서코드
	private String deptTitle;  // 부서명
	private String locationId; // 지역코드
	
	public Department( ) {} // 기본 생성자

	// 매개 변수 생성자
	public Department(String deptId, String deptTitle, String locationId) {
		super();
		this.deptId = deptId;
		this.deptTitle = deptTitle;
		this.locationId = locationId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptTitle() {
		return deptTitle;
	}

	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptTitle=" + deptTitle + ", locationId=" + locationId + "]";
	}
	
	
	
	
}
