/**
 * 
 */

 

function authenticate(e)
{
    e.preventDefault();
    let data=$('#loginForm').serialize();
    let xhr=new XMLHttpRequest();
    xhr.open('GET',"http://localhost:3000/peopleDemo/employee/authenticate?"+data);
    xhr.onload=navigate;
    xhr.onerror=error;
    xhr.send();
}
function navigate()
{
    if(this.status==200)
      {
        let userData=JSON.parse(this.responseText);
        sessionStorage.setItem("role",userData.role);
        sessionStorage.setItem("userId",userData.userId);
        sessionStorage.setItem("status",userData.status);
        switch(parseInt(sessionStorage.getItem("role")))
        {

            case 1:
              window.location.href="views/admin.html";
              break;
             default:
                window.location.href="views/user/user.html";
                break;
        }
      }
    else
     alert("unsucessfull invalid login credintials");
}
function error()
{
    console.log("error occured on server side");
}