function exportTableToCSV(filename){
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    for (var i = 1; i < rows.length; i++){
        var row = [], 
        cols = rows[i].querySelectorAll("td");
        for (var j = 0; j < cols.length; j++){
            // console.log("Col: "+cols[j]);
            if(cols[j].innerHTML.includes("input")){
                console.log(cols[j].querySelector("input").value);
                row.push(cols[j].querySelector("input").value);
            }
        }
        csv.push(row.join(","));        
    }
    downloadCSV(csv.join("\n"), filename);
}

function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;
    csvFile = new Blob([csv], {type: "text/csv"});
    downloadLink = document.createElement("a");
    downloadLink.download = filename;
    downloadLink.href = window.URL.createObjectURL(csvFile);
    downloadLink.style.display = "none";
    document.body.appendChild(downloadLink);
    downloadLink.click();
}