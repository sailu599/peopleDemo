var roleObj={}
$('document').ready(function(){
	
	validate(1);
	$role=$("#role_id");
	$.get('http://localhost:3000/peopleDemo/role/get')
	.done(function(response){
		let data=JSON.parse(response);
		data.forEach(role=>{
			let $option=$('<option>');
			$option.val(role.role_id);
			$option.text(role.role_name);
			$option.attr('role_name',role.role_name);
			$role.append($option);
		})
	})
	

	
	$.get('http://localhost:3000/peopleDemo/employee/get')
	.done(function(response){
		displayEmployee(response);
	})
	.fail()
	hideEmployee();
	
})


function addEmployee()
{
	let data=$("#addEmployeeForm").serialize();
	role=$("#role_id").val();
	var selectedOption = $("#role_id option[value='" + role + "']");
	var role_name = selectedOption.attr("role_name");
	
	if(role==null)
	{
		alert("please select a role");
	}
	else{
		data+="&role_id="+role+"&role_name="+role_name;
		$.ajax({
			    url: "http://localhost:3000/peopleDemo/employee/add?" + data,
			    type: "POST",
			    success: function(response) {
			        alert("success");
			        console.log(response);
			        addRow(JSON.parse(response));
			    },
			    error: function() {
			        alert("failure,make sure you have a unique email address");
			    }
             });

	}
}


function displayEmployee(response)
{
	
	let data=JSON.parse(response);
	data.forEach(employee => {
       addRow(employee)
});
}

function showEmployee()
{
	
	$.ajax({
		url:'http://localhost:3000/peopleDemo/providedLeave/get',
		method:'get',
		success:addLeave,
		failure:function()
		{
			alert("error loading ")
		}
	})
	 $("#leaveDiv").show();
	 $('#addBtn').prop('disabled', true);

}

function hideEmployee()
{
	$("#leaveDiv").hide();
	 $('#addBtn').prop('disabled', false);
	 $("#addEmployeeTable").empty();
}

function addRow(employee)
{
	$row = $("<tr>").attr("id",employee.employee_id);
    $col1 = $("<td>").text(employee.employee_id);
    $col2 =$("<td>").text(employee.employee_name);
    $col3=$("<td>").text(employee.role.role_name !=null ?employee.role.role_name:"nill");
    $col4 =$("<td>").text(employee.email !=null?employee.email:"nill");
    $col5 =$("<td>").text(employee.department_name !=null?employee.department_name:"nill");
   $col6=$(`<button onclick=showUpdateEmployeeForm(${employee.employee_id},'${employee.employee_name}','${employee.email}')>`).text('update');
   $col7=$(`<button onclick="deleteEmployee(${employee.employee_id})">`).text('delete');
    $row.append($col1);
    $row.append($col2);
    $row.append($col3);
    $row.append($col4);
    $row.append($col5);
    $row.append($col6);
    $row.append($col7);
    $("#tableBody").append($row); 
}


function loadLeave(response)
{
	let data=JSON.parse(response);
	let $div=$("#leaveForm");
	
	for(let leave in data){
		$label=$("<label>");
		 $input = $(`<input type='checkbox' name=${data[leave]}>`);
		$input.val(leave);
		$label.text(data[leave]);
		$div.append($label);
		$div.append($input);

	}
	$div.append("<input type='submit' onclick='addLeave(event)' value='add'/>");
}

function addLeave(response)
{
     
       let data=JSON.parse(response);
       let c=1;
	    data.forEach(leave=>{
			
			let row = $("<tr>");
		    let $label = $("<label>");
		    let $input1 = $(`<input type='number'  name='employee_leave${c}' readonly >`);
		    let $input2= $(`<input type='hidden' value='${leave.leave_id}' name='leave_id${c}'>`);
		    $input1.val(leave.available_days);
		    $label.text(leave.leave_type);
		    row.append($("<td>").html($label));
		    row.append($("<td>").html($input1));
		      row.append($input2);
		    $("#addEmployeeTable").append(row);
		    c++;
			
		})
		
	
}



function deleteEmployee(employee_id)
{
	let flag=confirm("are you sure do you want to delete this employee");
	if(flag)
	{
	$.ajax({
		url:"http://localhost:3000/peopleDemo/employee/delete?employee_id="+employee_id,
		success:function(){
			alert("deleted successfully");
			$(`#${employee_id}`).remove();
		}
	})
	}
	
}

function showUpdateEmployeeForm(employee_id,employee_name,email)
{
	$("#employee_name").val(employee_name);
	
	$("#email").val(email);
	$("#updateBtn").on("click",function(){
		updateEmployee(event,employee_id);
	})
	
	$.ajax({
		url:"http://localhost:3000/peopleDemo/employee_leave/get?employee_id="+employee_id,
		success:function(response){
			let data=JSON.parse(response);
			console.log(data);
			let tbody=$("#update_employee_leaveBody")
			tbody.empty();
			data.forEach(leave=>{
				    row=$(`<tr id="row${leave.id}">`);
				    row.append($("<td>").text(leave.leave_name));
				    row.append($(`<td id=days${leave.id}>`).text(leave.days));
				    row.append($(`<input id=leave${leave.id} type="number">`));
				    row.append($(`<button onclick=updateLeave(${leave.id})>`).text("update"));
				    row.append($(`<button onclick=deleteLeave(${leave.id})>`).text("delete"));
				    tbody.append(row)
			})
		},
		error:function(){
			allert("error at fetching employeee leave");
		}
		
		})
}


function updateEmployee(event,employee_id)
{
	event.preventDefault();
	formData=$("#updateEmployee").serialize();

	$.ajax({
		url:"http://localhost:3000/peopleDemo/employee/update?"+formData+"&employee_id="+employee_id,
		success:function(response){
			alert("updated sucessfully");
			let data=JSON.parse(response);
			$(`#${employee_id} td:eq(1)`).text(data.employee_name);
			$(`#${employee_id} td:eq(3)`).text(data.email);
		},
		error:function(){
			alert("error on updation");
		}
	})
}

function activate(){
	$("#updateBtn").prop("disabled",false);
}

function updateLeave(id){
	
	let val=$("#leave"+id).val();
	

    $.ajax({
		url:"http://localhost:3000/peopleDemo/employee_leave/update?"+"employee_leave_id="+id+"&available_days="+val,
		method:"post",
		success:function()
		{
			
			let prev=$("#days"+id).text();
			console.log(prev);
			$("#days"+id).text(Number(prev)+Number(val));
			
		},
		error:function(){
			alert("error on updation");
		}
		
	})     	
}


function deleteLeave(id){
    $.ajax({
		url:"http://localhost:3000/peopleDemo/employee_leave/delete?"+"employee_leave_id="+id,
		method:"post",
		success:function()
		{
			alert("deleted sucessfully");
			$("#row"+id).remove();
			
		},
		error:function(){
			alert("error on updation");
		}
		
	})     	
	
}