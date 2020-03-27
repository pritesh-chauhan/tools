var colHigh = -1;
var rowHigh = -1;

function highlight(e) {
    var selected = table.getElementsByClassName("my-table");
    if (selected[0]) 
        selected[0].className = '';
    e.target.parentNode.className = 'my-table';
}

//Highlist the row that is clicked 
function rowHighlight(i) {    
    var table = document.getElementById('my-table');
            if (!table.rows[i].hilite) {
                table.rows[i].origColor = table.rows[i].style.backgroundColor;
                table.rows[i].style.backgroundColor = '#BCD4EC';
                table.rows[i].hilite = true;
                
                console.log(parseInt(table.rows[i].getElementsByTagName('td')[0].id,8)+1);
                rowHigh = parseInt(table.rows[i].getElementsByTagName('td')[0].id,8)+1;
            }
            else {
                table.rows[i].style.backgroundColor = table.rows[i].origColor;
                table.rows[i].hilite = false;
            }
}
var glo = '';
var ASCIICode;
var x = "", temp ="";
var prev = 'A0';
function setter(id){
    var t = document.getElementById(id).value;
    console.log(t);
    
}

function setter(id){
    var x = document.getElementById(id).value;
    // document.getElementById("demo").innerHTML = "You wrote: " + x;
    document.getElementById(id).setAttribute('value', x);
    document.getElementById(id).innerHTML = x;
    x = "";
}

var prev = 0;
function onlyNumberKey(id, evt) {
    if(prev != id || glo.includes(")"))
        glo = "";
    prev = id; 
    ASCIICode = (evt.which) ? evt.which : evt.keyCode;
    glo += String.fromCharCode(ASCIICode);
    console.log("GLO: "+glo+" Ascii code: "+ASCIICode);
    if(glo.includes("="))
    {
        var temp = glo;
        console.log(glo);
        if(glo.includes(")")){
            // var exp = glo.split(/[\s,SUM,=,(,)]+/);
            // console.log("Expression: "+exp[1]);
            // glo = exp[1].split(":");
            // v0 = glo[0];
            // v1 = glo[1];
            // console.log("Character: "+(v0.charAt(1)));
            
            // var ch = v0.charAt(0);
            // var start_index = v0.charAt(1);
            // var end_index = v1.charAt(1);
            // sum = calculate_sum(ch, start_index, end_index);
            // for(var i=start_index; i<=end_index; i++){
            //     sum += parseInt(document.getElementById(ch+""+i).value,10);
            // }
            sum = calculate_sum(glo, id);
            console.log("Got sum-->", sum)
            document.getElementById(id).setAttribute('dbvalue', "");
            // document.getElementById(v0).setAttribute('value', document.getElementById(v0).value);
            // document.getElementById(v1).setAttribute('value', document.getElementById(v1).value);
            // console.log("Value is: "+parseInt(document.getElementById(v0).value,10)+" "+parseInt(document.getElementById(v1).value,10)+" "+sum);
            oneclick(id, sum);
        }
        // exp = "";
        // glo = "";
    }
    if(document.getElementById(id).value.includes("SUM")){
        console.log(document.getElementById(id).value+"-->"+String.fromCharCode(ASCIICode));
        if(ASCIICode == 13)
            document.getElementById(id).setAttribute("dbvalue", document.getElementById(id).getAttribute("value"));
        console.log("Value updated")
    }

    return true; 
}

function oneclick(id, sum){
    // document.getElementById(id).value = '';
    // document.getElementById(id).value = sum;
    // document.getElementById(id).innerHTML = sum;
    document.getElementById(id).setAttribute('value', sum);
    document.getElementById(id).value = '';
    document.getElementById(id).value = sum;
    document.getElementById(id).innerHTML = sum;
    console.log(id+"Done"+sum);
    document.getElementById(id).setAttribute('ondblclick', "calculate_sum(this.dbvalue, this.id),formula(this.id)");
}

function calculate_sum(ip, id){
    console.log("--"+ip);
    var tbl = document.getElementById('my-table'); // table reference
    
    if(document.getElementById(id).hasAttribute("dbvalue"))
        var ip = document.getElementById(id).getAttribute("dbvalue");
    console.log("--"+ip);
    var sumc = 0;
    if(ip.includes("SUM")){
        if(ip.includes("+") || ip.includes(":")){
            console.log("Inside: ",ip);
            var exp = ip.split(/[\s,SUM,=,(,)]+/);
            console.log("Expression: "+exp[1]);
            if(ip.includes(":"))
                ip = exp[1].split(":");
            else
                ip = exp[1].split("+");
            let c0 = ip[0];
            let c1 = ip[1];
            console.log("Character: "+(c0.charAt(1)));
            let ch = c0.charAt(0);
            let start_indexc = c0.charAt(1);
            let end_indexc = c1.charAt(1);
            console.log(start_indexc+" to "+end_indexc+" <> "+tbl.rows.length);
            if(end_indexc < tbl.rows.length-1){
                for(var i=start_indexc; i<=end_indexc; i++){
                    console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));
                    sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                    console.log("in: ",sumc);
                }
                exp = "";
                document.getElementById(id).setAttribute("value", sumc);
                document.getElementById(id).innerHTML = sumc;
            }
            else{
                console.log("Error string")
                sumc = "ERR"
                document.getElementById(id).setAttribute("value", "ERR");
                document.getElementById(id).innerHTML = "ERR";
            }
        }
        else if(ip.includes("-")){
            console.log("Inside: ",ip);
            var exp = ip.split(/[\s,SUM,=,(,)]+/);
            console.log("Expression: "+exp[1]);
            ip = exp[1].split("-");
            let c0 = ip[0];
            let c1 = ip[1];
            console.log("Character: "+(c0.charAt(1)));
            let ch = c0.charAt(0);
            let start_indexc = c0.charAt(1);
            let end_indexc = c1.charAt(1);
            console.log(start_indexc+" to "+end_indexc+" "+ch);
            if(end_indexc < tbl.rows.length-1){
                for(var i=start_indexc; i<=end_indexc; i++){
                    console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));
                    if( i == start_indexc)
                        sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                    else
                    sumc = sumc - parseInt(document.getElementById(ch+""+i).value,10);

                    console.log("in: ",sumc);
                }
                exp = "";
                document.getElementById(id).setAttribute("value", sumc);
                document.getElementById(id).innerHTML = sumc;
            }
            else{
                sumc = "ERR"
                document.getElementById(id).setAttribute("value", "ERR");
                document.getElementById(id).innerHTML = "ERR";
            }
        }
        
        else if(ip.includes("*")){
            console.log("Inside: ",ip);
            var exp = ip.split(/[\s,SUM,=,(,)]+/);
            console.log("Expression: "+exp[1]);
            ip = exp[1].split("*");
            let c0 = ip[0];
            let c1 = ip[1];
            console.log("Character: "+(c0.charAt(1)));
            let ch = c0.charAt(0);
            let start_indexc = c0.charAt(1);
            let end_indexc = c1.charAt(1);
            // var sumc = 0;
            console.log(start_indexc+" to "+end_indexc+" "+ch);
            // sum = calculate_sum(ch, start_index, end_index);
            if(end_indexc < tbl.rows.length-1){
                for(var i=start_indexc; i<=end_indexc; i++){
                    console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));

                    if( i == start_indexc)
                        sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                    else
                    sumc = sumc * parseInt(document.getElementById(ch+""+i).value,10);

                    console.log("in: ",sumc);
                }
                exp = "";
                document.getElementById(id).setAttribute("value", sumc);
                document.getElementById(id).innerHTML = sumc;
            }
            else{
                sumc = "ERR"
                document.getElementById(id).setAttribute("value", "ERR");
                document.getElementById(id).innerHTML = "ERR";
            }
        }
        else if(ip.includes("/")){
            console.log("Inside: ",ip);
            var exp = ip.split(/[\s,SUM,=,(,)]+/);
            console.log("Expression: "+exp[1]);
            ip = exp[1].split("/");
            let c0 = ip[0];
            let c1 = ip[1];
            console.log("Character: "+(c0.charAt(1)));
            let ch = c0.charAt(0);
            let start_indexc = c0.charAt(1);
            let end_indexc = c1.charAt(1);
            console.log(start_indexc+" to "+end_indexc+" "+ch);
            if(end_indexc < tbl.rows.length-1){
                for(var i=start_indexc; i<=end_indexc; i++){
                    console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));

                    if( i == start_indexc)
                        sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                    else
                    sumc = sumc / parseInt(document.getElementById(ch+""+i).value,10);

                    console.log("in: ",sumc);
                }

                exp = "";

                if(parseInt(document.getElementById(ch+""+end_indexc).value,10) == 0){
                    sumc = "ERR";
                    document.getElementById(id).setAttribute("value", "ERR");
                    document.getElementById(id).innerHTML = "ERR";
                }
                else{
                    document.getElementById(id).setAttribute("value", sumc);
                    document.getElementById(id).innerHTML = sumc;
                }
            }
            else{
                sumc = "ERR"
                document.getElementById(id).setAttribute("value", "ERR");
                document.getElementById(id).innerHTML = "ERR";
            }
        }
            
    }
    else if(ip.includes("+") || ip.includes(":")){
        console.log("Inside: ",ip);
        var exp = ip.split(/[\s,SUM,=,(,)]+/);
        console.log("Expression: "+exp[1]);
        if(ip.includes(":"))
            ip = exp[1].split(":");
        else
            ip = exp[1].split("+");
        let c0 = ip[0];
        let c1 = ip[1];
        console.log("Character: "+(c0.charAt(1)));
        let ch = c0.charAt(0);
        let start_indexc = c0.charAt(1);
        let end_indexc = c1.charAt(1);
        console.log(start_indexc+" to "+end_indexc+" <> "+tbl.rows.length);
        if(end_indexc < tbl.rows.length-1){
            for(var i=start_indexc; i<=end_indexc; i++){
                console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));
                sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                console.log("in: ",sumc);
            }
            exp = "";
            document.getElementById(id).setAttribute("value", sumc);
            document.getElementById(id).innerHTML = sumc;
        }
        else{
            console.log("Error string")
            sumc = "ERR"
            document.getElementById(id).setAttribute("value", "ERR");
            document.getElementById(id).innerHTML = "ERR";
        }
    }
    else if(ip.includes("-")){
        console.log("Inside: ",ip);
        var exp = ip.split(/[\s,SUM,=,(,)]+/);
        console.log("Expression: "+exp[1]);
        ip = exp[1].split("-");
        let c0 = ip[0];
        let c1 = ip[1];
        console.log("Character: "+(c0.charAt(1)));
        let ch = c0.charAt(0);
        let start_indexc = c0.charAt(1);
        let end_indexc = c1.charAt(1);
        console.log(start_indexc+" to "+end_indexc+" "+ch);
        if(end_indexc < tbl.rows.length-1){
            for(var i=start_indexc; i<=end_indexc; i++){
                console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));
                if( i == start_indexc)
                    sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                else
                sumc = sumc - parseInt(document.getElementById(ch+""+i).value,10);

                console.log("in: ",sumc);
            }
            exp = "";
            document.getElementById(id).setAttribute("value", sumc);
            document.getElementById(id).innerHTML = sumc;
        }
        else{
            sumc = "ERR"
            document.getElementById(id).setAttribute("value", "ERR");
            document.getElementById(id).innerHTML = "ERR";
        }
    }
    
    else if(ip.includes("*")){
        console.log("Inside: ",ip);
        var exp = ip.split(/[\s,SUM,=,(,)]+/);
        console.log("Expression: "+exp[1]);
        ip = exp[1].split("*");
        let c0 = ip[0];
        let c1 = ip[1];
        console.log("Character: "+(c0.charAt(1)));
        let ch = c0.charAt(0);
        let start_indexc = c0.charAt(1);
        let end_indexc = c1.charAt(1);
        // var sumc = 0;
        console.log(start_indexc+" to "+end_indexc+" "+ch);
        // sum = calculate_sum(ch, start_index, end_index);
        if(end_indexc < tbl.rows.length-1){
            for(var i=start_indexc; i<=end_indexc; i++){
                console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));

                if( i == start_indexc)
                    sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                else
                sumc = sumc * parseInt(document.getElementById(ch+""+i).value,10);

                console.log("in: ",sumc);
            }
            exp = "";
            document.getElementById(id).setAttribute("value", sumc);
            document.getElementById(id).innerHTML = sumc;
        }
        else{
            sumc = "ERR"
            document.getElementById(id).setAttribute("value", "ERR");
            document.getElementById(id).innerHTML = "ERR";
        }
    }
    else if(ip.includes("/")){
        console.log("Inside: ",ip);
        var exp = ip.split(/[\s,SUM,=,(,)]+/);
        console.log("Expression: "+exp[1]);
        ip = exp[1].split("/");
        let c0 = ip[0];
        let c1 = ip[1];
        console.log("Character: "+(c0.charAt(1)));
        let ch = c0.charAt(0);
        let start_indexc = c0.charAt(1);
        let end_indexc = c1.charAt(1);
        console.log(start_indexc+" to "+end_indexc+" "+ch);
        if(end_indexc < tbl.rows.length-1){
            for(var i=start_indexc; i<=end_indexc; i++){
                console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));

                if( i == start_indexc)
                    sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                else
                sumc = sumc / parseInt(document.getElementById(ch+""+i).value,10);

                console.log("in: ",sumc);
            }

            exp = "";

            if(parseInt(document.getElementById(ch+""+end_indexc).value,10) == 0){
                sumc = "ERR";
                document.getElementById(id).setAttribute("value", "ERR");
                document.getElementById(id).innerHTML = "ERR";
            }
            else{
                document.getElementById(id).setAttribute("value", sumc);
                document.getElementById(id).innerHTML = sumc;
            }
        }
        else{
            sumc = "ERR"
            document.getElementById(id).setAttribute("value", "ERR");
            document.getElementById(id).innerHTML = "ERR";
        }
    }
    else if(ip.includes("=")){
        if(ip.includes("+") || ip.includes(":")){
            console.log("Inside: ",ip);
            var exp = ip.split(/[\s,=,(,)]+/);
            console.log("Expression: "+exp[1]);
            if(ip.includes(":"))
                ip = exp[1].split(":");
            else
                ip = exp[1].split("+");
            let c0 = ip[0];
            let c1 = ip[1];
            console.log("Character: "+(c0.charAt(1)));
            let ch = c0.charAt(0);
            let start_indexc = c0.charAt(1);
            let end_indexc = c1.charAt(1);
            console.log(start_indexc+" to "+end_indexc+" <> "+tbl.rows.length);
            if(end_indexc < tbl.rows.length-1){
                for(var i=start_indexc; i<=end_indexc; i++){
                    console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));
                    sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                    console.log("in: ",sumc);
                }
                exp = "";
                document.getElementById(id).setAttribute("value", sumc);
                document.getElementById(id).innerHTML = sumc;
            }
            else{
                console.log("Error string")
                sumc = "ERR"
                document.getElementById(id).setAttribute("value", "ERR");
                document.getElementById(id).innerHTML = "ERR";
            }
        }
        else if(ip.includes("-")){
            console.log("Inside: ",ip);
            var exp = ip.split(/[\s,=,(,)]+/);
            console.log("Expression: "+exp[1]);
            ip = exp[1].split("-");
            let c0 = ip[0];
            let c1 = ip[1];
            console.log("Character: "+(c0.charAt(1)));
            let ch = c0.charAt(0);
            let start_indexc = c0.charAt(1);
            let end_indexc = c1.charAt(1);
            console.log(start_indexc+" to "+end_indexc+" "+ch);
            if(end_indexc < tbl.rows.length-1){
                for(var i=start_indexc; i<=end_indexc; i++){
                    console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));
                    if( i == start_indexc)
                        sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                    else
                    sumc = sumc - parseInt(document.getElementById(ch+""+i).value,10);

                    console.log("in: ",sumc);
                }
                exp = "";
                document.getElementById(id).setAttribute("value", sumc);
                document.getElementById(id).innerHTML = sumc;
            }
            else{
                sumc = "ERR"
                document.getElementById(id).setAttribute("value", "ERR");
                document.getElementById(id).innerHTML = "ERR";
            }
        }
        
        else if(ip.includes("*")){
            console.log("Inside: ",ip);
            var exp = ip.split(/[\s,=,(,)]+/);
            console.log("Expression: "+exp[1]);
            ip = exp[1].split("*");
            let c0 = ip[0];
            let c1 = ip[1];
            console.log("Character: "+(c0.charAt(1)));
            let ch = c0.charAt(0);
            let start_indexc = c0.charAt(1);
            let end_indexc = c1.charAt(1);
            // var sumc = 0;
            console.log(start_indexc+" to "+end_indexc+" "+ch);
            // sum = calculate_sum(ch, start_index, end_index);
            if(end_indexc < tbl.rows.length-1){
                for(var i=start_indexc; i<=end_indexc; i++){
                    console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));

                    if( i == start_indexc)
                        sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                    else
                    sumc = sumc * parseInt(document.getElementById(ch+""+i).value,10);

                    console.log("in: ",sumc);
                }
                exp = "";
                document.getElementById(id).setAttribute("value", sumc);
                document.getElementById(id).innerHTML = sumc;
            }
            else{
                sumc = "ERR"
                document.getElementById(id).setAttribute("value", "ERR");
                document.getElementById(id).innerHTML = "ERR";
            }
        }
        else if(ip.includes("/")){
            console.log("Inside: ",ip);
            var exp = ip.split(/[\s,=,(,)]+/);
            console.log("Expression: "+exp[1]);
            ip = exp[1].split("/");
            let c0 = ip[0];
            let c1 = ip[1];
            console.log("Character: "+(c0.charAt(1)));
            let ch = c0.charAt(0);
            let start_indexc = c0.charAt(1);
            let end_indexc = c1.charAt(1);
            console.log(start_indexc+" to "+end_indexc+" "+ch);
            if(end_indexc < tbl.rows.length-1){
                for(var i=start_indexc; i<=end_indexc; i++){
                    console.log("-->"+parseInt(document.getElementById(ch+""+i).value,10));

                    if( i == start_indexc)
                        sumc = sumc + parseInt(document.getElementById(ch+""+i).value,10);
                    else
                    sumc = sumc / parseInt(document.getElementById(ch+""+i).value,10);

                    console.log("in: ",sumc);
                }

                exp = "";

                if(parseInt(document.getElementById(ch+""+end_indexc).value,10) == 0){
                    sumc = "ERR";
                    document.getElementById(id).setAttribute("value", "ERR");
                    document.getElementById(id).innerHTML = "ERR";
                }
                else{
                    document.getElementById(id).setAttribute("value", sumc);
                    document.getElementById(id).innerHTML = sumc;
                }
            }
            else{
                sumc = "ERR"
                document.getElementById(id).setAttribute("value", "ERR");
                document.getElementById(id).innerHTML = "ERR";
            }
        }
    }

    return sumc;
}

function formula(id){

    var temp = document.getElementById(id).value;
    console.log("Came inside"+temp);
    
    if(temp == ""){
        console.log("Came empty");
        if(!temp.includes('=')){
            console.log(glo);
            // document.getElementById(id).value = "=SUM("+glo[0]+":"+glo[1]+")";
            document.getElementById(id).value = glo;
            // document.getElementById(id).innerHTML = "=SUM("+glo[0]+":"+glo[1]+")";
            document.getElementById(id).innerHTML = glo;
            // document.getElementById(id).setAttribute('dbvalue', "=SUM("+glo[0]+":"+glo[1]+")");
            document.getElementById(id).setAttribute('dbvalue', glo);
            calculate_sum(document.getElementById(id).getAttribute("dbvalue") ,id)
            document.getElementById(id).setAttribute('ondblclick', "calculate_sum(this.dbvalue,this.id),formula(this.id)");
        }
        else{
            document.getElementById(id).value = sum;
            document.getElementById(id).innerHTML = sum;
            document.getElementById(id).setAttribute('value', sum); 
        }
    }
    else{
        console.log("Came full");
        if(!temp.includes('=')){
            document.getElementById(id).value = document.getElementById(id).getAttribute("dbvalue");
            document.getElementById(id).innerHTML = document.getElementById(id).getAttribute("dbvalue");
            document.getElementById(id).setAttribute('dbvalue', document.getElementById(id).getAttribute("dbvalue"));
        }
        else{
            document.getElementById(id).value = document.getElementById(id).getAttribute('value').split(")")[0];
            document.getElementById(id).innerHTML = document.getElementById(id).getAttribute('value').split(")")[0];
            document.getElementById(id).setAttribute('value', document.getElementById(id).getAttribute('value').split(")")[0]); 
        }
    }

    exp = "";
    
}

function sleep(milliseconds) { 
    let timeStart = new Date().getTime(); 
    while (true) { 
        let elapsedTime = new Date().getTime() - timeStart; 
        if (elapsedTime > milliseconds) { 
            break; 
        } 
    } 
} 

var v0 = 0, v1 = 0, sum = 0;

// append row to the HTML table
function appendRow() {
    var tbl = document.getElementById('my-table'), // table reference
        row = tbl.insertRow(tbl.rows.length),      // append table row
        i;
        console.log("Test"+tbl.rows.length);
        if(tbl.rows.length == 2){
            console.log("Here -->"+tbl.rows.length);
            createRow(row.insertCell(0), 1);
            if( tbl.rows.length > 1){
                for(var i=1; i<tbl.rows.length; i++){
                    console.log(tbl.rows[1]);
                    tbl.rows[1].getElementsByTagName('td')[0].setAttribute('id',(0));
                    tbl.rows[1].getElementsByTagName('td')[0].innerHTML = 0;
                    tbl.rows[1].getElementsByTagName('td')[0]('onclick',"rowHighlight(parseInt(this.id,8)+1)");
                }  
            }
        }
        else{
            index = parseInt(getLastRowHeader('my-table'), 8)+1;
            console.log("Index: ",index);
            var check = -1;
            for(var j=1; j<tbl.rows.length; j++){
                if(tbl.rows[j].hilite){
                    console.log("High Index: "+j);
                    index = j+1;
                    check = 1;
                }
            }
            if(check < 0){
                createRow(row.insertCell(0), index);
                console.log(tbl.rows[index].getElementsByTagName('td')[0].id);
            }
            else{
                var new_id = index;
                createRow(row.insertCell(0), index-1);
                for (i = 1; i < tbl.rows[0].cells.length; i++) {
                    createRow(row.insertCell(i), '');
                    var prev_id_ch = tbl.rows[index].getElementsByTagName('td')[i].querySelector("input").id.charAt(0);
                    console.log("Additional: ",prev_id_ch+""+(index));
                    tbl.rows[tbl.rows.length-1].getElementsByTagName('td')[i].querySelector("input").setAttribute("id",prev_id_ch+""+(index-1));
                    tbl.rows[tbl.rows.length-1].getElementsByTagName('td')[i].querySelector("input").setAttribute('oninput',"setter(this.id)");
                    tbl.rows[tbl.rows.length-1].getElementsByTagName('td')[i].querySelector("input").setAttribute('onkeypress',"return onlyNumberKey(this.id, event)");
                }
                

                console.log("New Row id: ",new_id);
                for(var j=new_id; j<tbl.rows.length-1; j++){
                    // console.log(tbl.rows[j].getElementsByTagName('td')[0]);
                    tbl.rows[j].getElementsByTagName('td')[0].id = j;
                    tbl.rows[j].getElementsByTagName('td')[0].innerHTML = j;
                }
                sortTable();
                for(var j=1; j<tbl.rows.length; j++){
                    for(i=1; i<tbl.rows[0].cells.length; i++){
                        tbl.rows[j].getElementsByTagName('td')[i].querySelector("input").removeAttribute("id");
                    }
                }
                console.log("Total Rows are ",tbl.rows.length);

            }
        }
        if(check<0){
            for (i = 1; i < tbl.rows[0].cells.length; i++) {
                createRow(row.insertCell(i), '');
                var prev_id_ch = tbl.rows[index].getElementsByTagName('td')[i].querySelector("input").id.charAt(0);
                console.log(prev_id_ch+""+(index));
                tbl.rows[index+1].getElementsByTagName('td')[i].querySelector("input").setAttribute("id",prev_id_ch+""+(index));
                tbl.rows[index+1].getElementsByTagName('td')[i].querySelector("input").setAttribute('oninput',"setter(this.id)");
                tbl.rows[index+1].getElementsByTagName('td')[i].querySelector("input").setAttribute('onkeypress',"return onlyNumberKey(this.id, event)");
            }
        }
        else{
            console.log("Highlighted1")
            for(i=1; i<tbl.rows[0].cells.length; i++){
                var ch = tbl.rows[0].getElementsByTagName('td')[i].innerHTML;
                console.log(ch);
                for(var j=1; j<tbl.rows.length; j++){
                    var element = tbl.rows[j].getElementsByTagName('td')[i].querySelector("input");
                    // element.id = ch+""+(j-1);
                    element.setAttribute("id", ch+""+(j-1));
                    console.log(ch+""+(j-1));
                }
            }

            // for(var j= index; j<tbl.rows.length-1; j++){
            //     for (i = 1; i < tbl.rows[0].cells.length; i++) {
            //         // createRow(row.insertCell(i), '');
            //         var prev_id_ch = tbl.rows[j].getElementsByTagName('td')[i].querySelector("input").id.charAt(0);
            //         console.log(prev_id_ch+""+(j));
            //         tbl.rows[j].getElementsByTagName('td')[i].querySelector("input").setAttribute("id",prev_id_ch+""+(j));
            //         tbl.rows[j].getElementsByTagName('td')[i].querySelector("input").setAttribute('oninput',"setter(this.id)");
            //         tbl.rows[j].getElementsByTagName('td')[i].querySelector("input").setAttribute('onkeypress',"return onlyNumberKey(this.id, event)");
            //     }
            // }
        }
}

function sortTable() { 
    var table, i, x, y; 
    table = document.getElementById("my-table"); 
    var switching = true; 
    while (switching) { 
        switching = false; 
        var rows = table.rows; 
        for (i = 1; i < (rows.length-1); i++) { 
            var Switch = false; 
            x = rows[i].getElementsByTagName("td")[0].innerHTML; 
            y = rows[i + 1].getElementsByTagName("td")[0].innerHTML; 
            console.log(x,y);
            if (x > y){ 
                Switch = true; 
                break; 
            } 
        } 
        if (Switch) { 

            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]); 
            // for(var j=0; j<rows[i].cells.length; j++){
            //     rows[i].parentNode.insertBefore(rows[i+1].cells[j], rows[i].cells[j]);
            // }
            switching = true; 
        } 
    } 
} 

//Get the last header of the row
function getLastRowHeader(id) {
    var table = document.getElementById(id);
    var txt = "";
    var i;
    txt = txt + table.rows[table.rows.length-2].cells[0].id;
    return txt;
}


function createRow(cell, text) {
    if(text != ''){
        txt = document.createTextNode(text); // create text node
        cell.appendChild(txt);                    // append text node to the DIV
        cell.setAttribute('id', text);        // set DIV class attribute
        cell.setAttribute('onclick',"rowHighlight(parseInt(this.id,8)+1)");
    }
    else{
        txt = document.createTextNode(text);
        cell.appendChild(txt);                    // append text node to the DIV
        cell.setAttribute('id', text);
        ip = document.createElement("input");
        cell.appendChild(ip);
        ip.setAttribute("type", "text");  
    }          
}

// delete table rows with index greater then 0
function deleteRows() {
    var tbl = document.getElementById('my-table'), // table reference
    lastRow = tbl.rows.length - 1,             // set the last row index
    i;
        console.log(rowHigh);
        if(rowHigh != -1){
            tbl.deleteRow(rowHigh);
            rowHigh = -1;
        }
        else
            tbl.deleteRow(lastRow);
        console.log(tbl.rows.length);
        if( tbl.rows.length > 1){
            for(var i=1; i<tbl.rows.length; i++){
                console.log(tbl.rows[i].getElementsByTagName('td')[0].id)
                tbl.rows[i].getElementsByTagName('td')[0].setAttribute('id',(i-1));
                tbl.rows[i].getElementsByTagName('td')[0].innerHTML = i-1;
                }  
                console.log("check");
            for(var i=1; i<tbl.rows.length; i++){
                for(var j=1; j<tbl.rows[i].cells.length; j++){
                    var ch = tbl.rows[0].getElementsByTagName('td')[j].getAttribute('id');
                    tbl.rows[i].getElementsByTagName('td')[j].querySelector("input").setAttribute("id",ch+""+(i-1));
                    console.log(tbl.rows[i].getElementsByTagName('td')[j].querySelector("input").getAttribute('id')+"   "+ch+""+(i-1));
                }
            }
        }
}

//Highlight the column on click of the heade
function colHighlight(id) {
    var table = document.getElementById('my-table');
    colHigh = id.charCodeAt(0)-64;
    console.log(table.rows[1].cells[colHigh]);
    for (var i = 1; i < table.rows.length; i++) {
        if (!table.rows[i].cells[colHigh] .hilite) {
            table.rows[i].cells[colHigh] .origColor = table.rows[i].cells[colHigh] .style.backgroundColor;
            table.rows[i].cells[colHigh] .style.backgroundColor = '#BCD4EC';
            table.rows[i].cells[colHigh] .hilite = true;
        }
        else {
            table.rows[i].cells[colHigh] .style.backgroundColor = table.rows[i].cells[colHigh] .origColor;
            table.rows[i].cells[colHigh] .hilite = false;
        }
    }
}


//get the headers of the table
function getLastColumnHeader(id, pos, flag) {
    var table = document.getElementById(id);
    var txt = "";
    var i;
    console.log("Pos-->"+pos+" Full-->"+table.rows[0].cells.length);
    if(flag == false || (pos == table.rows[0].cells.length)){
        txt = txt + table.rows[0].cells[table.rows[0].cells.length-2].id;
    }
    else{
        console.log("Checking")
        txt = txt + table.rows[0].cells[pos].id;
    }
    return txt;
}

// return the next char column header
function nextCharacter(c) { 
    return String.fromCharCode(c.charCodeAt(0) + 1); 
} 

// create DIV element and append to the table cell
function createCol(cell, text) {
    if(text != ''){
        txt = document.createTextNode(text); // create text node
        cell.appendChild(txt);                    // append text node to the DIV
        cell.setAttribute('id', text);        // set DIV class attribute
        cell.setAttribute('onclick', 'colHighlight(this.id)');
    }
    else{
        txt = document.createTextNode(text);
        cell.appendChild(txt);                    // append text node to the DIV
        cell.setAttribute('id', text);
        ip = document.createElement("input");
        cell.appendChild(ip);
        ip.setAttribute("type", "text");  
    }
}

// append column to the HTML table
function appendColumn() {
    var tbl = document.getElementById('my-table'), // table reference
        i;  
    // open loop for each row and append cell
    if( tbl.rows[0].cells.length == 1){
        createCol(tbl.rows[0].insertCell(tbl.rows[0].cells.length), 'A');
        for (i = 1; i < tbl.rows.length; i++) {
            createCol(tbl.rows[i].insertCell(tbl.rows[i].cells.length), '');
            
        }
        tbl.rows[0].cells[1].setAttribute('onclick',"colHighlight(this.id)");
    }
    else if(tbl.rows[0].cells.length <= 26){
        console.log(tbl.rows[0].cells.length);
        var x=-1;
        for(var i=0; i<tbl.rows[0].cells.length;i++){
           if(tbl.rows[0].cells.length > 1 && tbl.rows[1].cells[i] .hilite){
                console.log("Yes Highlighted"+i);
                x = i;
                break;
            }
        }
        console.log("-->"+x);
        if(x == -1){
            // Not highlighted
            console.log("Yeah"+(x+1)+" --> "+getLastColumnHeader('my-table',(x+1),false));
            createCol(tbl.rows[0].insertCell(tbl.rows[0].cells.length), nextCharacter(getLastColumnHeader('my-table',(x+1),false)));
            var ch = nextCharacter(getLastColumnHeader('my-table',(x+1),false));
            for (i = 1; i < tbl.rows.length; i++) {
                createCol(tbl.rows[i].insertCell(tbl.rows[i].cells.length), '');
                tbl.rows[i].cells[tbl.rows[i].cells.length-1].querySelector("input").setAttribute("id",ch+""+(i-1));
                tbl.rows[i].cells[tbl.rows[i].cells.length-1].querySelector("input").setAttribute('oninput',"setter(this.id)");
                tbl.rows[i].cells[tbl.rows[i].cells.length-1].querySelector("input").setAttribute('onkeypress',"return onlyNumberKey(this.id, event)");
            }
        }
        else{
            // Highlighted
            var check = -1;
            var i=1;
            if(x == tbl.rows[0].cells.length-1){
                createCol(tbl.rows[0].insertCell(tbl.rows[0].cells.length), nextCharacter(getLastColumnHeader('my-table',(x),true)));
                for (i = 1; i < tbl.rows.length; i++) {
                    createCol(tbl.rows[i].insertCell(tbl.rows[i].cells.length), '');
                }
                var id_val = tbl.rows[0].cells[x+1].innerHTML;
                for (i = 1; i < tbl.rows.length; i++) {
                    var element = tbl.rows[i].cells[x+1].querySelector("input");
                    console.log(tbl.rows[i].cells[x+1]);
                    var z = id_val+""+(i-1);
                    element.setAttribute("id", z);
                    element.setAttribute("oninput","setter(this.id)");
                    element.setAttribute("onkeypress","return onlyNumberKey(this.id, event)");
                }
            }
            else{
                var temp = nextCharacter(getLastColumnHeader('my-table', x, true));
                console.log("Temp value: ",temp," "+(x+1));
                createCol(tbl.rows[0].insertCell(x+1), temp);
                for (i = 1; i < tbl.rows.length; i++) {
                    createCol(tbl.rows[i].insertCell(x+1), '');
                    tbl.rows[i].cells[x+1].querySelector("input").setAttribute("id",temp+""+(i-1));
                }
                tbl.rows[0].cells[1].setAttribute('onclick',"colHighlight(this.id)");
                var i=1;
                // x+=1;
                while( i < tbl.rows[0].cells.length) {
                    temp = nextCharacter(getLastColumnHeader('my-table', x, true));
                    for(var k=x+1; k<tbl.rows[0].cells.length; k++){
                        console.log(x,temp,"ID check: ",k,tbl.rows[0].cells[k].id);
                        if(temp.includes(tbl.rows[0].cells[k].id) || check > 0){
                            check = 1;
                            var itemp = nextCharacter(getLastColumnHeader('my-table',(k-1),true));
                            console.log("itemp: "+itemp+" "+k);
                            tbl.rows[0].cells[k].setAttribute("id", itemp);
                            tbl.rows[0].cells[k].innerHTML = itemp;
                            for (var j = 1; j < tbl.rows.length; j++) {
                                var element = tbl.rows[j].cells[k].querySelector("input");
                                var prev_value = element.getAttribute("value");
                                var prev_dbvalue, prev_dbclick;
                                if(element.hasAttribute("ondbclick")){
                                    prev_dbvalue = element.getAttribute("dbvalue");
                                    prev_dbclick = element.getAttribute("ondbclick");
                                }
                                console.log("Checking the prev values: "+prev_value+" "+prev_dbvalue+" "+prev_dbclick);
                                var z = itemp+""+(j-1);
                                element.setAttribute("id", z);
                            }
                        }
                    }
                    x++;
                    i++;
                }
            }
        }
    }
    else{
        document.getElementById("addcol").disabled = true;
    }
    console.log("Column: "+tbl.rows[0].cells.length);
}
 
// delete table columns with index greater then 0
function deleteColumns() {
    var tbl = document.getElementById('my-table'), // table reference
    lastCol = tbl.rows[0].cells.length - 1,    // set the last column index
    i, j;
    console.log("Col High: "+colHigh);
    // delete cells with index greater then 0 (for each row)
    if(lastCol >= 2){
        if(colHigh != -1){
            for (i = 0; i < tbl.rows.length; i++) {
                    tbl.rows[i].deleteCell(colHigh);
            }
            colHigh = -1;
        }
        else{
            for (i = 0; i < tbl.rows.length; i++) {
                tbl.rows[i].deleteCell(lastCol);
            }
        }
        console.log("Value: "+tbl.rows[0].cells.length);
        if(tbl.rows[0].cells.length > 1){
            var prev = 65;
            console.log("Prev: "+prev);
            for(var i=2; i<=tbl.rows[0].cells.length; i++){
                console.log("Inside For0: "+String.fromCharCode(prev));
                tbl.rows[0].cells[i-1].setAttribute('id', String.fromCharCode(prev));
                tbl.rows[0].cells[i-1].innerHTML = String.fromCharCode(prev);
                prev += 1;
                console.log("Inside For: "+prev);
            }
            for(var j=1; j<tbl.rows[0].cells.length; j++){
                for(var i=1; i<tbl.rows.length; i++){
                    console.log(tbl.rows[0].cells[j].getAttribute('id'));
                    var ch = tbl.rows[0].cells[j].getAttribute('id');
                    tbl.rows[i].cells[j].querySelector("input").setAttribute("id", ch+""+(i-1));
                    console.log("Column delt: "+ch+""+(i-1));
                }
            }
        }
    }
    else{
        alert("Cannot delete columns further");
        console.log("Cannot delete columns further");
    }
}