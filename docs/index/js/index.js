function buildReleases() {
    var json = JSON.parse(releases);

    var output = []
    for (i=0; i < json.length; i++) {
        var href = "javadoc/" + json[i].replace("/.", "_") + "/index.html"
        output.push("<a class=\"dropdown-item\" href=\"" + href + "\">" + json[i] + "</a>");
    }
    console.log(output.join(""))
}