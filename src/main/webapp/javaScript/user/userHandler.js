/**
 * 
 */
$("document").ready(function(){
	validate(2);
	$("#mainDiv").hide();

	if(sessionStorage.getItem('status')==0)
	{
		alert("change your password to proceed further");
		
	}
	else
	{
		$("#mainDiv").show();
		$.ajax({
			url:"http://localhost:3000/peopleDemo/employee/directReportings?employee_id="+sessionStorage.getItem("userId"),
			method:"get",
			success:loadDirectReportings,
			failure:function(){
				alert("error loading the data");
			}
		})
		hidePassword();
		
	}
})

function savePassword()
{
     let password=$("#pass1").val();
     if(password!=$("#pass2").val())
     {
		alert("password must be matched");
	 }
	 else{
		let status=sessionStorage.getItem('status');
		let id=sessionStorage.getItem('userId');
		let queryParams="employee_id="+id+"&status="+status+"&password="+password;
		
		$.ajax({
			url:"http://localhost:3000/peopleDemo/employee/password?"+queryParams,
			type:"post",
			success:function()
			{
			     alert("updated successfully");
			     sessionStorage.clear();
			     window.location.href="../../index.jsp";
			},
			error:function()
			{
				alert("error in updation");
			}
		})
	 }
}

function showPassword()
{
	$("#updatePassword").show();
}
function hidePassword()
{
	$("#updatePassword").hide();
}


function loadDirectReportings(response){
	let data=JSON.parse(response);
    data.forEach(employee=>{
		let tr=$("<tr>");
		 tr.append($("<td>").text(employee.employee_id));
		 tr.append($("<td>").text(employee.employee_name));
		 tr.append($("<td>").text(employee.email));
		 tr.append($("<td>").text(employee.department));
		 tr.append($("<td>").text(employee.role.role_name));
		 $("#tbody").append(tr);
	})
}



function logout(){
                sessionStorage.clear();
			     window.location.href="../../index.jsp";	
}