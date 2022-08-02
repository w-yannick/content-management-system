var specials = ["Never set an alarm clock unless you know how to switch it off", 
    "If you find yourself distressed about something, ask yourself if it will still matter tomorrow or next week or next month.",
    "Smile and the world smiles with you. Frown and you're on your own."];
var specialIndex = 0;
var elem = $("#jumbotron-text");
function changeText(){
    $('#jumbotron-text').fadeOut(function(){
        $('#jumbotron-text').html(specials[specialIndex]);
        specialIndex =(specialIndex<( specials.length - 1))? ++specialIndex : 0;
        $('#jumbotron-text').fadeIn();
    })
};
setInterval(changeText, 5000);

const userAction = async () => {
//    const response = await fetch('http://localhost:8080/api/specials');
//    specials = await response.json(); //extract JSON from the http response     
    changeText();
};


userAction();
 

