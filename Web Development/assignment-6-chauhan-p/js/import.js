// import("button.js");
function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    // CSV file
    csvFile = new Blob([csv], {type: "text/csv"});

    // Download link
    downloadLink = document.createElement("a");

    // File name
    downloadLink.download = filename;

    // Create a link to the file
    downloadLink.href = window.URL.createObjectURL(csvFile);

    // Hide download link
    downloadLink.style.display = "none";

    // Add the link to DOM
    document.body.appendChild(downloadLink);

    // Click download link
    downloadLink.click();
}
function exportTableToCSV(filename) {
    var csv = [];
    var tb = document.getElementById("my-table");
     var rows = tb.rows
    //var rows = document.querySelectorAll("td");
    //console.log(rows)

    for (var i = 1; i < rows.length; i++) {
       // console.log(txt);
        var row = [],
            cols = rows[i].querySelectorAll("input");
           // console.log(cols)
        for (var j = 0; j < cols.length; j++)
            //console.log(cols[j].value);
            row.push(cols[j].value);

        csv.push(row.join(","));
    }

    // Download CSV file
    downloadCSV(csv.join("\n"), filename);
}
function reloadMe(){
    var table = document.getElementById("my-table");
    for(var i=1; i<table.rows.length; i++){
        for(var j=1; j<table.rows[0].cells.length; j++){
            table.rows[i].getElementsByTagName('td')[j].querySelector("input").value = ""; 
            table.rows[i].getElementsByTagName('td')[j].querySelector("input").innerHTML = ""; 
            table.rows[i].getElementsByTagName('td')[j].querySelector("input").setAttribute("value", "");
        }
    }
    console.log("Refreshed"); 
}
function Upload() {
    var fileUpload = document.getElementById("fileUpload");
    console.log(fileUpload.value)
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;

    
    if (regex.test(fileUpload.value.toLowerCase())) {
        if (typeof (FileReader) != "undefined") {
            
            var reader = new FileReader();
            reader.onload = function (e) {
                var table = document.getElementById("my-table");
                var rows = table.rows;
                for(var i=1; i< 2; i++){
                    
                    var a = e.target.result.split("\n");
                    console.log("A0: ",table.rows.length,a.length, table.rows[0].cells.length, a[0].length);
                    //rows check is <100
                    var r_len = table.rows.length;
                    while(r_len < a.length){
                        appendRow();
                        r_len++;
                    }
                    r_len--;
                    while(r_len >= a.length){
                        deleteRows();
                        r_len--;
                    }
                    console.log("A1: ",(table.rows.length),a.length, (table.rows[0].cells.length), a[0].length);
                    //columns length is lenss than 27
                    if(a[0].length%2 != 0)
                        var c_len = table.rows[0].cells.length+1;
                    else
                        var c_len = table.rows[0].cells.length;
                    while(c_len <= (a[0].length)/2){
                        appendColumn();
                        c_len++;
                    }
                    c_len--;
                    console.log("A2: ",(table.rows.length),a.length, (table.rows[0].cells.length), a[0].length);
                    while (c_len-1 >= (a[0].length)/2){
                        deleteColumns();
                        c_len--;
                    }
                    console.log("A3: ",(table.rows.length),a.length, (table.rows[0].cells.length), a[0].length);
                    //rows adding
                    for(var j=1; j<= a.length; j++){
                        var b = a[j - 1].split(",");
                        console.log(b);
                        for (var k = 1; k <= b.length; k++) {
                            if(table.rows[j]){
                                var txt = table.rows[j].cells[k].querySelector("input");
                                txt.value = b[k-1];
                                txt.setAttribute("value", b[k-1]);
                            }
                        }
                    
                    }

                }
                // table.appendChild(rows);
            }
            reader.readAsText(fileUpload.files[0]);
        } else {
            alert("This browser does not support HTML5.");
        }
    } else {
        alert("Please upload a valid CSV file.");
    }
}