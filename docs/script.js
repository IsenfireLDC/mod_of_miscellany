var downloadPath = "../builds/dev-versions/";
var jdownloads = ["misc-0.1.1.jar", "misc-0.1.2-dev.jar", "misc-0.1.2.1-dev.jar", "misc-0.1.3-dev.jar"];
var sdownloads = ["misc-0.1.1.sources.jar", "misc-0.1.2-dev.sources.jar", "misc-0.1.2.1-dev.sources.jar", "misc-0.1.3-dev.sources.jar"];
var downloads = new Array(jdownloads.length);
for (var i = 0; i < jdownloads.length; i++) {
  downloads[i] = [jdownloads[i], sdownloads[i]];
};

function showDownloads() {
  var table = document.getElementById('downloadTable');

  for (var i = 0; i < downloads.length; i++) {
    console.log(i);
    var row = document.createElement('tr');
    for (var j = 0; j < downloads[i].length; j++) {
      console.log(j);
      var cell = document.createElement('td');
      var file = document.createElement('a');
      file.setAttribute('href', downloadPath + downloads[i][j]);
      console.log(downloadPath + downloads[i][j]);
      file.setAttribute('download', downloads[i][j]);
      file.innerHTML = downloads[i][j];

      cell.appendChild(file);
      row.appendChild(cell);
    }
    table.appendChild(row);
  }
}
