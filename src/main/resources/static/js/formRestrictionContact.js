

$('#email').on('change', function(){
    if($('#email').val() !== ""){
        $('#phone').removeAttr("required");
        console.log("email changed");
    }
    else{
        $('#phone').attr("required",true)
    }
               
});
 
 $('#phone').on('change', function(){
    if($('#phone').val() !== ""){
        $('#email').removeAttr("required");
        console.log("phone changed");
    }
    else{
        $('#email').attr("required",true)
    }
});