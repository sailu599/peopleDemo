$(document).ready(function()
		  {
			console.log("hiiiii");
			 validate(1);
	         $.ajax({
	        	 url:"http://localhost:3000/peopleDemo/role/get",
	        	 type:"get",
	        	 success:loadData,
	             error:function(){
	            	 alert("error");
	             }
	         })
	        hideRoleForm();
		  }
		)
		  
		  
function loadData(response)
{
	        console.log("hiii");
	        data=JSON.parse(response);
	        data.forEach(data=>{
		
				addRow(data)
			})
}

function showRole()
{
	
	$("#addRoleForm").show();
	
}
function hideRoleForm()
{
	 $("#addRoleForm").hide();
}

function addRole()
{
	let data=$("#addRoleForm").serialize();
	$.post("http://localhost:3000/peopleDemo/role/add?"+data)
	.done(function(response){
	  addRow(JSON.parse(response));
	})
	.fail(function()
	{
		alert("failed");
	})
}

function addRow(data)
{
	 console.log(data);
		let $row = $("<tr>").attr("id",data.role_id);
        let $col1 = $("<td>").text(data.role_name);
        let $col2 = $("<td>").text(data.role_level);
	    $row.append($col1, $col2);
	   $row.append($(`<button onclick="showUpdateRole('${data.role_name}', ${data.role_level}, ${data.role_id})">`).text("update"));
         $row.append($(`<button onclick="deleteRole('${data.role_id})">`).text("update"));
	    $("#tableBody").append($row);
}

function showUpdateRole(role_name,role_level,role_id){
	$("#role_name".val(role_name));
	$("#role_level".val(role_level));
	$("#role_id".val(role_id));
}


function deleteRole(role_id){
	
}