$('.datetimepicker').datetimepicker({
    icons: {
        time: "fa fa-clock-o",
        date: "fa fa-calendar",
        up: "fa fa-chevron-up",
        down: "fa fa-chevron-down",
        previous: 'fa fa-chevron-left',
        next: 'fa fa-chevron-right',
        today: 'fa fa-screenshot',
        clear: 'fa fa-trash',
        close: 'fa fa-remove'
    }
});
function accesscontrol() {
    console.log(user);
    console.log(password);
    var user = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if ((user == "grupo12") && (password == "1234")) {
        var url = "dashboard.html";
        $(location).attr('href', url);
    } else {
        alert("User name or password incrorrect");
    }
}
function enter(e) {
    var push = (document.all) ? e.keyCode : e.which;
    if (push == 13) {
        accesscontrol();
    }
}
function loginindex(data) {
    document.getElementById("login").style.display = "none";
    switch (data) {
        case "teacher": {
            document.getElementById("loginStudent").style.display = "block";
            break;
        }
        case "student": {
            document.getElementById("loginTeacher").style.display = "block";
            break;
        }
        case "visitor": {
            //goTo(); -->Irá directamente al dashboard con las opciones de visitante
            break;
        }
    }
}
function goTo(route) {
    //Hide all sections and show the section selected
    document.getElementById("event").style.display = "none";
    document.getElementById("time").style.display = "none";
    document.getElementById("category").style.display = "none";
    document.getElementById("view").style.display = "none";
    document.getElementById(route).style.display = "block";

    //Change css of sidebar (active class)
    document.getElementById("nav_category").className = "nav_item"
    document.getElementById("nav_time").className = "nav_item"
    document.getElementById("nav_event").className = "nav_item"
    document.getElementById("nav_view").className = "nav_item"
    document.getElementById("nav_" + route).className = "nav_item active"
}

document.getElementById('actualidad').onchange = function() {
    document.getElementById('dateDisabled').disabled = this.checked;
};

function registerview(id){
    console.log ("ESTAMOS DENTRO");
    document.getElementById('loginModalContent1').style.display="none";
    document.getElementById('loginModalContent2').style.display="none";
    document.getElementById(id).style.display="block";
}