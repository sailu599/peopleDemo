package dataModel.employeeDetails;

public class Employee_leaveModel {
	private String leave_type;
	private int days;
	private int used_days;
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getUsed_days() {
		return used_days;
	}
	public void setUsed_days(int used_days) {
		this.used_days = used_days;
	}

}
