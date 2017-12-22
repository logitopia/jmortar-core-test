function buildReleases() {
    var json = JSON.parse(releases);

    var output = []
    for (i=0; i < json.length; i++) {
        var href = "javadoc/" + json[i].replace(/\./g,'_') + "/index.html"

        if (i === 0) { output.push("<h6 class='dropdown-header'>Latest Release</h6>"); }
        output.push("<a class=\"dropdown-item\" href=\"" + href + "\">" + json[i] + "</a>");
        if (i == 0) { output.push("<div class='dropdown-divider'></div>") };
    }

    document.getElementById('jdoc-dropdown').innerHTML += output.join('');
}