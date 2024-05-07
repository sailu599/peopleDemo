/**
 * 
 */

 $(document).ready(function(){
	     $.ajax({
	        	 url:"http://localhost:3000/peopleDemo/department/get",
	        	 type:"get",
	        	 success:loadData,
	             error:function(){
	            	 alert("error");
	             }
	         })
 })
 
 function loadData(response)
 {
    let data=JSON.parse(response);
    let tbody=$("#tbody");
    data.forEach(department=>
    {
		addRow(department);
		
	})
 }
 
 function addEmployee()
 {
	alert("working");
 }
 function addDepartment(event)
 {
	event.preventDefault();
	let data=$("#addDepartmentForm").serialize();
	
	$.ajax({
	  url:"http://localhost:3000/peopleDemo/department/add?"+data,
	  success:function(response)
	  {
		addRow(JSON.parse(response));
	  }	,
	  failure:function()
	  {
		alert("error in failure");
	  }
	  })
 }
 function addRow(department)
 {
	    let tbody=$("#tbody");
	    let row=$("<tr>");
		row.append($("<td>").text(department.department_id));
		row.append($("<td>").text(department.department_name));
		$addBtn=$("<button>").text("update");
		$addBtn.on("click",function(){
			showDepartment(department.department_id);
		})
		row.append($addBtn);
		row.append($("<button onclick='deletDepartment()'>delete</button>"));
		tbody.append(row);
 }
 
 
 function showDepartment(department_id)
 {
	localStorage.setItem("department_id",department_id)
	window.location.href="employee_department.html";
 }