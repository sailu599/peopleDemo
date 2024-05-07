package dataModel;

public class employeeDataModel {
	  private String employee_name;
	  private String email;
	  private String password;
	  private String department_name;
	  int employee_id;
	  RoleModel role;
	  DepartmentModel dep;
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public RoleModel getRole() {
		return role;
	}
	public void setRole(RoleModel role) {
		this.role = role;
	}
	public DepartmentModel getDep() {
		return dep;
	}
	public void setDep(DepartmentModel dep) {
		this.dep = dep;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	  
}
