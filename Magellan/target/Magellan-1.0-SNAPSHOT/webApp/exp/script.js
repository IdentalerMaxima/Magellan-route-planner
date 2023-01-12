$("#loginButton").click(function (event) {
    event.preventDefault();
    var loginInfo = {
        username: $("#user").val(),
        password: $("#pass").val()
    }
    $.ajax({
        url: "http://127.0.0.1:8080/Magellan-1.0-SNAPSHOT/api/user/login",
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(loginInfo),
        succes: function () {
            alert("Siker!");
        },
        error: function () {
            alert("Siker!");
            window.location.replace("index.html");
        }
    });
})

$(document).ready(function () {
    $.get("http://localhost:8080/Magellan-1.0-SNAPSHOT/api/route/all", function (mJSONArray) {

        var htmlString = "";
        var i = 0;
        $.each(mJSONArray, function (_index, item) {
            htmlString = "";
            htmlString += "<img id='uImg' src= " + item.image + `/index${i}.jpg>`;
            htmlString += "<div style='font-weight:bold'; id='uName'>" + item.name + "</div>";
            htmlString += "<div id='ulength'>" + "Az útvonal hossza: " + item.length + " méter</div>";
            htmlString += "<div id='uduration'>" + "Az útvonal időtartama: " + item.duration + " perc</div>";
            htmlString += "<div id='uelevation'>" + "Az útvonal emelkedés: " + item.elevation + " méter</div>";
            i++;
            $(`#rootContainer${i}`).html(htmlString);
        });


    })
})
$(document).ready(function () {
    $.get("http://localhost:8080/Magellan-1.0-SNAPSHOT/api/route/all", function (mJSONArray) {

        var htmlString = "";
        var i = 0; ""
        $.each(mJSONArray, function (_index, item) {
            htmlString = "";
            htmlString += "<img id='uImg' src= " + item.image + `/index${i}.jpg>`;
            htmlString += "<br></br>";
            htmlString += "<div id='uNumber' >" + "Az útvonal száma: " + item.id + "</div>";
            htmlString += "<div id='uName'>" + "Az útvonal neve: " + item.name + "</div>";
            htmlString += "<div id='ulength'>" + "Az útvonal hossza: " + item.length + " méter</div>";
            htmlString += "<div id='uduration'>" + "Az útvonal időtartama: " + item.duration + " perc</div>";
            htmlString += "<div id='uelevation'>" + "Az útvonal emelkedés: " + item.elevation + " méter</div>";
            htmlString += "<div uDif>" + "Az útvonal nehézsége: " + item.difficultyLevel + "</div>";
            htmlString += "<div id='uDesc'>" + "Az útvonal leírása: " + item.description + "</div>";
            htmlString += "<div style='width:80%;' id='uRoutedesc'>" + "Az útvonal útleírása: " + item.routeDescription + "</div>";
            i++;
            $(`#apiClass${i}`).html(htmlString);
        });



    })
})

var links = [
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/1.html",
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/2.html",
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/3.html",
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/4.html",
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/5.html",
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/6.html",];

openStuff = function () {
    var randIdx = Math.random() * links.length;
    randIdx = parseInt(randIdx, 10);
    var link = 'http://' + links[randIdx];
    window.open(link, "_self");
};


