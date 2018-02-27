var downloadPath = "../builds/dev-versions/";
var inputFiles = ["recommendedFiles.txt", "allFiles.txt"];
var downloads = [];
/*for (var i = 0; i < jdownloads.length; i++) {
  downloads[i] = [jdownloads[i], sdownloads[i]];
};*/


async function pageLoad() {
  inputFiles.forEach(async (file) => {
    console.log(file);
    await fetch(file)
      .then(response => response.text())
      .then(text => {
        intoArray(text);
        return text;
      })
      .then(showAllDownloads(file));
  })
}

async function showAllDownloads(elementId) {
  var index = elementId.indexOf("Files.txt");
  elementId = elementId.substring(0, index);
  var table = await document.getElementById(elementId);

  for (var i = 0; i < downloads.length; i++) {

    var row = document.createElement('tr');
    for (var j = 0; j < downloads[i].length; j++) {

      var cell = document.createElement('td');
      var file = document.createElement('a');

      file.setAttribute('href', downloadPath + downloads[i][j]);
      file.setAttribute('download', downloads[i][j]);
      file.innerHTML = downloads[i][j];

      cell.appendChild(file);
      row.appendChild(cell);
    }
    table.appendChild(row);
  }
}

function intoArray (lines) {
   // splitting all text data into array "\n" is splitting data from each new line
   //and saving each new line as each element*

   var lineArr = lines.split('\n');
   lineArr.pop();

   downloads = new Array(lineArr.length / 2);

   //just to check if it works output lineArr[index] as below
   var j = 0;
   for (var i = 0; i < lineArr.length; i += 2) {
     downloads[j] = [lineArr[i], lineArr[i + 1]];
     j++;
   };
   console.log(downloads);
}
