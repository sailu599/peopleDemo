

$("document").ready(function(){
	
	$.ajax({
	url:" http://localhost:3000/peopleDemo/providedLeave/get",
	method:"get",
	success:function(response){
		let data=JSON.parse(response);
		console.log(data);
			 data.forEach(leave => {
		    addRow(leave);
		});


	},
	error:function(){
		alert("error in loading");
	}	
	})
	
	})
	
	
	function addLeave(){
		let leave_type=$("#leave_type").val();
		let days=$("#leave_days").val();
		if(days<=0||leave_type==null)
		{
			alert("enter a valid values");
		}
		else{
			let form=document.getElementById("leave_form");
			let data=new FormData(form);
			
			for (const entry of data.entries()) {
 				 console.log(entry);
			}

	         $.ajax({
				url:"http://localhost:3000/peopleDemo/providedLeave/add?"+$("#leave_form").serialize(),
				type:"post",
				body:data,
				success:function(response){
				  addRow(JSON.parse(response));
				},
				error:function(){
					alert("failed");
				}
			 })
		}
			
	}
	
	
	function deleteLeave(leave_id){
		
		$.ajax({
			url:"http://localhost:3000/peopleDemo/providedLeave/delete?leave_id="+leave_id,
			method:"post",
			success:function(){
				alert("success");
				$(`#${leave_id}`).remove();
			},
			error:function(){
				alert("error deleting ")
			}
		})
		}
		
	function addRow(leave){
		let tbody=$('#tbody');
		let row = $(`<tr id=${leave.leave_id}>`);
		    row.append($("<td>").text(leave.leave_id));
		    row.append($("<td>").text(leave.leave_type));
		    row.append($("<td>").text(leave.available_days));
		    row.append($(`<button onclick="deleteLeave(${leave.leave_id})">`).text("delete"));
			row.append($(`<button onclick='updateLeave(${leave.leave_id}, "${leave.leave_type}", ${leave.available_days})'>`).text("update"));
			tbody.append(row);
	}
	
	function updateLeave(leave_id,leave_type,available_days){
		$('#updateDays').val(available_days);
		$('#updateId').val(leave_id);
		$('#updateType').val(leave_type);
	}
	
	
	function saveUpdate(){
		$.ajax({
			url:"http://localhost:3000/peopleDemo/providedLeave/update?"+$("#update_form").serialize(),
			body:new FormData(document.getElementById('update_form')),
			method:"post",
			success:function(response){
				 alert("success");
				 window.location.reload();
			},
			error:function(){
				alert("error");
			}			
		})
	}
	
	