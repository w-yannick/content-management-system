$(document).ready(function(){
    
    var role = $('#role').val();
    console.log(role);
    switch(role){
       
        case "ADMIN":
            $('#navbarRight').prepend(
                '<li class="nav-item" th:class="${activePage == "create"} ? "nav-item active" : "nav-item"><a class="nav-link" href="/create">Create</a></li>'
          
                + '<li class="nav-item" th:class="${activePage == "approvalPending"} ? "nav-item active" : "nav-item" ><a class="nav-link" href="/pendingApproval">Manage</a></li>'
            );
            $('#navbarRight').append(
                '<li class="nav-item" th:class="${activePage == "logout"} ? "nav-item active" : "nav-item" ><a class="nav-link" href="/logout">logout</a></li>'
            );
            
            break;
        case "MANAGER":
            $('#navbarRight').prepend(
                '<li class="nav-item" th:class="${activePage == "create"} ? "nav-item active" : "nav-item"><a class="nav-link" href="/create">Create</a></li>'            
            );
            $('#navbarRight').append(
                '<li class="nav-item" th:class="${activePage == "logout"} ? "nav-item active" : "nav-item" ><a class="nav-link" href="/logout">logout</a></li>'
            );
            
        
    }
     
});