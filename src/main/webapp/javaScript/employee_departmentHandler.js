
	
	
	$("document").ready(function()
	{
		$.ajax({
		    url:"http://localhost:3000/peopleDemo/employee/get",
		     success:function(response){
				displayEmployee(response,false)
			},
		    failure:function()
		    {
				alert("error loading the page");
			}
		})
		
		$.ajax({
			 url:"http://localhost:3000/peopleDemo/employee_department/get?department_id="+localStorage.getItem("department_id"),
		    success:function(response){
				displayEmployee(response,true)
			},
		    failure:function()
		    {
				alert("error loading the page");
			}
		})
	})

	
function displayEmployee(response,isDepartment_employee)
{
	
	let data=JSON.parse(response);
	data.forEach(employee => {
		if(employee.department_name==null)
             addRow(employee,isDepartment_employee);
});
}

function addRow(employee,isDepartment_employee)
{
	
	$row = $("<tr>");
    $col1 = $("<td>").text(employee.employee_id);
    $col2 =$("<td>").text(employee.employee_name);
    $col3=$("<td>").text(employee.role.role_name !=null ?employee.role.role_name:"nill");
    $col4 =$("<td>").text(employee.email !=null?employee.email:"nill");
    if(!isDepartment_employee)
    {
	    $btn=$("<button>").text("add");
	    $btn.on("click",function(){
			addEmployee_department(employee.employee_id,employee.role.role_id);
		})
	   $col5 =$("<td>").html($btn);
   }
    $row.append($col1);
    $row.append($col2);
    $row.append($col3);
    $row.append($col4);
    
    if(!isDepartment_employee)
    {
          $row.append($col5);
          $("#tableBody").append($row);
    }
    else{
		   $("#employee_department").append($row);
	} 
}


function addEmployee_department(employee_id,role_id)
{
       let department_id=localStorage.getItem("department_id");
       let queryParameter="department_id="+department_id+"&employee_id="+employee_id+"&role_id="+role_id;

       $.ajax({
		url:"http://localhost:3000/peopleDemo/employee_department/add?"+queryParameter,
		method:"post",
		success:function(response){
			alert("added successfully");
			//window.location.reload();
		},
	    failure:function(){
			alert("failed to add");
		}
	   })	
}

function show(){
	$(".addEmployee_department").show();
}

function hide(){
	$(".addEmployee_department").hide();
}