$("#loginButton").click(function () {
    $.ajax({
        url: "http://127.0.0.1:8080/Magellan-1.0-SNAPSHOT/api/user/login",
        type: "POST",
        dataType: 'json',
        contentType: 'json',
        data : {
            "username" : "admin",
            "password" : "admin",
         },
         succes: function(){
            alert("siker");
         },
        error: function () {
            alert("Hiba!");
        }
    });
})