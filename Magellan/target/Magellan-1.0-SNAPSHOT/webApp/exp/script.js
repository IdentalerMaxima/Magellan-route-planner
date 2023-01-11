
$(document).ready(function () {
    $.get("http://localhost:8080/Magellan-1.0-SNAPSHOT/api/route/all", function (mJSONArray) {

        var htmlString = "";
        var i = 0; ""
        $.each(mJSONArray, function (_index, item) {
            htmlString = "";
            htmlString += "<p>" + "Az útvonal neve: " + item.name + "</p>";
            htmlString += "<img src= " + " item.images" + ">";
            htmlString += "<p>" + "Az útvonal nehézsége: " + item.difficultylevel + "</p>";
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
            htmlString += "<img src= " + " item.images" + ">";
            htmlString += "<br></br>";
            htmlString += "<div id='uNumber' >" + "Az útvonal száma: " + item.id + "</div>";
            htmlString += "<div id='uName'>" + "Az útvonal neve: " + item.name + "</div>";
            htmlString += "<div id='ulength'>" + "Az útvonal hossza: " + item.length + "</div>";
            htmlString += "<div id='uduration'>" + "Az útvonal időtartama: " + item.duration + "</div>";
            htmlString += "<div id='uelevation'>" + "Az útvonal emelkedés: " + item.elevation + "</div>";
            htmlString += "<div id='uDesc'>" + "Az útvonal leírása: " + item.description + "</div>";
            htmlString += "<div id='uRoutedesc'>" + "Az útvonal útleírása: " + item.routeDescription + "</div>";
            htmlString += "<div uDif>" + "Az útvonal nehézsége: " + item.difficultylevel + "</div>";
            i++;
            $(`#apiClass${i}`).html(htmlString);
        });



    })
})

// the used links
var links = [
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/1.html",
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/2.html",
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/3.html",
    "localhost:8080/Magellan-1.0-SNAPSHOT/webApp/4.html"];

openStuff = function () {
    // get a random number between 0 and the number of links
    var randIdx = Math.random() * links.length;
    // round it, so it can be used as array index
    randIdx = parseInt(randIdx, 10);
    // construct the link to be opened
    var link = 'http://' + links[randIdx];
    // open it in a new window / tab (depends on browser setting)
    window.open(link, "_self");
};

