function getApi() {
    var url = "http://127.0.0.1:8080/Magellan-1.0-SNAPSHOT/api/route/test";

    var xhr = new XMLHttpRequest();
    xhr.open("GET", url);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            document.write("státuszkód: ", xhr.status);
            document.write("\tGET kérés eredménye: ", xhr.responseText);
        }
    };

    xhr.send();
}