var downloadPath = "../builds/dev-versions/";
var inputFiles = ["devFiles.txt"];  //For possible use later
var downloads = [];
/*for (var i = 0; i < jdownloads.length; i++) {
  downloads[i] = [jdownloads[i], sdownloads[i]];
};*/

function pageLoad() {
  getData().then(showDownloads);
}

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

function getData() {       //this will read file and send information to other function
  return new new Promise(function(resolve) {
       var xmlhttp;

       if (window.XMLHttpRequest) {
           xmlhttp = new XMLHttpRequest();
       }
       else {
           xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
       }

       xmlhttp.onreadystatechange = function () {
           if (xmlhttp.readyState == 4) {
             var lines = xmlhttp.responseText;    //*here we get all lines from text file*
             resolve(xmlhttp.responseText);

             intoArray(lines);     //here we call function with parameter "lines*"
           } else {
             reject(Error(xmlhttp.statusText));
           }
       }

       xmlhttp.open("GET", "devFiles.txt", true);
       xmlhttp.send();
   });
}

function getData2() {
  fetch('devFiles.txt')
    .then(response => response.text())
    .then(text => console.log(text));
    intoArray(text);
}

function intoArray (lines) {
   // splitting all text data into array "\n" is splitting data from each new line
   //and saving each new line as each element*

   var lineArr = lines.split('\n');
   console.log(lineArr);
   lineArr.pop();
   console.log(lineArr);

   downloads = new Array(lineArr.length / 2);

   //just to check if it works output lineArr[index] as below
   var j = 0;
   for (var i = 0; i < lineArr.length; i += 2) {
     downloads[j] = [lineArr[i], lineArr[i + 1]];
     console.log(downloads[j]);
     console.log(i + " " + j);
     j++;
   };
   console.log(downloads);
}
