package dataModel;

public class providedLeaveModel {
    private int leave_id;
    private int available_days;
    private String leave_type;
	public int getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}
	public int getAvailable_days() {
		return available_days;
	}
	public void setAvailable_days(int available_days) {
		this.available_days = available_days;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
}
