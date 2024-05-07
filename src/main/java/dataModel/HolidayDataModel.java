package dataModel;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class HolidayDataModel {
	
	private long id;
	private String holiday_name;
	private java.sql.Date date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHoliday_name() {
		return holiday_name;
	}
	public void setHoliday_name(String holiday_name) {
		this.holiday_name = holiday_name;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(String stringDate) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			java.util.Date date=sdf.parse(stringDate);
			this.date=new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
	}

}
