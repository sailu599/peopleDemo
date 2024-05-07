function validate(role)
{
	let sessionRole=sessionStorage.getItem("role")
    if(sessionStorage.getItem("userId")==null)
             window.location.href="../index.jsp";
    else if(role==1&&sessionRole!=1||role!=1&&sessionRole==1)
               window.location.href="../index.jsp";     

}

function logout(){
                sessionStorage.clear();
			     window.location.href="../index.jsp";	
}