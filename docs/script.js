var downloadPath = "../builds/dev-versions/";
var inputFiles = ["recommendedFiles.txt", "allFiles.txt"];
var downloads = [];
/*for (var i = 0; i < jdownloads.length; i++) {
  downloads[i] = [jdownloads[i], sdownloads[i]];
};*/


async function pageLoad() {
  for (const file of inputFiles) {
    console.log(file);
    let response = await fetch(file);
    let text = await response.text();
    let downloadArray = await intoArray(text);
    await showAllDownloads(downloadArray, file);
    console.log("Loaded file: " + file);
  }
}

async function showAllDownloads(array, elementId) {
  console.log("Displaying " + elementId + "...");
  var index = elementId.indexOf("Files.txt");
  elementId = elementId.substring(0, index);
  var table = document.getElementById(elementId);

  for (var i = 0; i < array.length; i++) {

    var row = document.createElement('tr');
    for (var j = 0; j < array[i].length; j++) {

      var cell = document.createElement('td');
      var file = document.createElement('a');

      file.setAttribute('href', downloadPath + array[i][j]);
      file.setAttribute('download', array[i][j]);
      file.innerHTML = array[i][j];

      cell.appendChild(file);
      row.appendChild(cell);
    }
    table.appendChild(row);
  }
  console.log("Finished displaying " + elementId + "...");
}

async function intoArray (lines) {
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
   return downloads;
}
