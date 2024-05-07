/**
 * 
 */
$('document').ready(function(){
	$.ajax({
		url:"http://localhost:3000/peopleDemo/employee_leave/get?employee_id="+sessionStorage.getItem('userId'),
		success:function(response){
			let data=JSON.parse(response);
			let tbody=$("#tbody");
			data.forEach(leave=>{
				let row=$("<tr>");
				row.append($("<td>").text(leave.leave_type));
				row.append($("<td>").text(leave.days));
				row.append($("<td>").text(leave.used_days));
				tbody.append(row);
				$("#select_leave").append($(`<option>${leave.leave_type}</option>`).val(leave.leave_id));
			})
		}
	})
})