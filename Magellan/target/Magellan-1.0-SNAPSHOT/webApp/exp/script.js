function getApi(){
var url = "http://127.0.0.1:8080/Magellan-1.0-SNAPSHOT/api/teszt_get";

var xhr = new XMLHttpRequest();
xhr.open("GET", url);

xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
        document.write(xhr.status);
        document.write(xhr.responseText);
    }};

xhr.send();
}