class Node {

    constructor(tagName, children, parent) {
        this.parent = parent;
        this.tagName = tagName;
        //children is an arr of node objects.
        this.children = children;
    }
    /**
     Implement below method.  Returns an arr of all descendant nodes matching the selector.
     The selector could be a simple CSS selector or descendant CSS selector.
     Examples    selector = "p" |  selector = "p div"
     return [node1, node2, ...]
     */

    /**

     Implement below method.  Add the node to the current node's parent.

     This method does not return any value.

     */
    
    // In querySelectorAll method I am checking the tagName initially that does it contains space or not 
    querySelectorAll(tagName){
        
      let obj = [];
      // Check for space in the tagName
      if(tagName.includes(" "))
      {
          //Splitting the entered string and storing it in an arr
        let arr  = tagName.split(" ");
        //This condition is to check if the arr length is 2
        if(arr.length == 2 && this.tagName.includes(arr[0])){
          //Checking if there are any childrens
          if(this.children.length > 0){
            for(let i=0; i<this.children.length; i++){
              //Checking if the childrens tagName contains the selector passed
              if(this.children[i].tagName.includes(arr[1])){
                  return this.children[i];
              }
            }
          }
        }
        //This condition is to check if the arr length is 3
        else if(arr.length == 3 && this.tagName.includes(arr[0])){
          //Checking if there are any childrens
          if(this.children.length > 0){
            for(let i=0; i<this.children.length; i++){
              //Checking if the childrens tagName contains the selector passed
              if(this.children[i].tagName.includes(arr[1])){
                //Checking if there are any childrens of children
                if(this.children[i].children.length > 0){
                  for(let j=0; j<this.children[i].children.length; j++){
                    //Checking if the childrens tagName contains the selector passed
                    if(this.children[i].children[j].tagName.includes(arr[2])){
                      obj.push(this.children[i].children[j]);
                    }
                  }
                }
              }
            }
          }
        }
        //This condition is to check if the arr length is 1
        else{
          //Checking if there are any childrens
          if(this.children.length > 0){
            for(let i=0; i<this.children.length; i++){
              //Recursively calling the queryselectorall by updating the object keeping the tagName same as that was passed and pushing the value in the array object 
              obj.push((this.children[i]).querySelectorAll(tagName));
            }
          }
        }
        return obj;
      }
      else
      {
          let mainarr = this.children;
          let childrenarr = [];
          for(let k=0;k<this.children.length;k++)
          {
              // console.log(this.children[k].children.length)
              if(this.children[k].children.length > 0){
                for(let l=0;l<this.children[k].children.length;l++){
                  if(this.children[k].children[l].tagName.includes(tagName)){
                      // console.log("TagName ============>"+this.children[k].children[l].tagName)
                      childrenarr.push(this.children[k].children[l]);
                  }
                  else{
                    
                    for(let j=0; j<this.children[k].children[l].children.length; j++){
                      // console.log("Checking==>"+this.children[k].children[l].children[j].tagName)
                      if(this.children[k].children[l].children[j].tagName.includes(tagName))
                        childrenarr.push(this.children[k].children[l].children[j]);
                    }
                  }
                }
              }
              else if(this.children[k].tagName.includes(tagName)){
                childrenarr.push(this.children[k].querySelectorAll(tagName));
              }
          }
          return childrenarr;
      }
  }

  addSibling(node) {
      if(this === html)
        return null;
      let nodeArr = node.parent.children;
      //This will remove the desired element from the arr i.e. the siblings parent
      nodeArr.splice( nodeArr.indexOf(node), 1);
      //updating the parent of the siblings parent with the parents parent
      node.parent = this.parent;
      this.parent.children.push(node);
  }
}


/*
Test Case DOM:
<html>

  <div> - div1

    <p> - p1
      <a></a> - a1
    </p>

    <p></p> - p2

    <section> - section
      <p> - p3
        <a></a> - a2
      </p>
      <p></p> - p4
    </section>

  </div>

  <div> - div2
    <p> - p5
      <a></a> - a3
    </p>
  </div>

</html>
*/

let html = new Node("html", [], null);
let div1 = new Node("div1", [], html);
let div2 = new Node("div2", [], html);

let arr = html.children;
arr.push(div1);
arr.push(div2);

div1.addSibling(div2);

let p1 = new Node("p1", [], div1);
let a1 = new Node("a1", [], p1);

let p2 = new Node("p2", [], div1);

let section = new Node("section", [], div1);
let p3 = new Node("p3", [], section);
let a2 = new Node("a2", [], p3);
let p4 = new Node("p4", [], section);

let p5 = new Node("p5", [], div2);
let a3 = new Node("a3", [], p5);

let arr1 = div1.children;
arr1.push(p1);
arr1.push(p2);
arr1.push(section);

p1.addSibling(p2);
p2.addSibling(section);

let arr2 = p1.children;
arr2.push(a1);

let arr3 = section.children;
arr3.push(p3);
arr3.push(p4);

p3.addSibling(p4);

let arr4 = p3.children;
arr4.push(a2);

let arr5 = div2.children;
arr5.push(p5);

let arr6 = p5.children;
arr6.push(a3);

let obj = {}


/* Test 1 
Expect: p1, p2, p3, p4, p5
*/
console.log("\n\nTest 1: =================Expect: p1, p2, p3, p4, p5===================== \n\n");
obj = html.querySelectorAll("p")
for(let i=0; i<obj.length; i++){
  if(obj[i] != null | obj[i] != undefined )
    console.log(obj[i])
}

/* Test 2 
Expect: a1, a2, a3
*/
console.log("\n\nTest 2: ================Expect: a1, a2, a3====================== \n\n");
obj = html.querySelectorAll("p a")
for(let i=0; i<obj.length; i++){
  if(obj[i] != null | obj[i] != undefined )
    console.log(obj[i])
}
/* Test 3 
Expect: p3, p4
*/
console.log("\n\nTest 3: ================Expect: p3, p4====================== \n\n");
obj = html.querySelectorAll("div section p")
for(let i=0; i<obj.length; i++){
  if(obj[i] != null | obj[i] != undefined )
    console.log(obj[i])
}


/* Test 4
Expect: a3
*/
console.log("\n\nTest 4: =================Expect: a3===================== \n\n");
obj = div2.querySelectorAll("p a")
for(let i=0; i<obj.length; i++){
  if(obj[i] != null | obj[i] != undefined )
    console.log(obj[i])
}
div1.addSibling(p5);

/* Test 5
Expect: firstline: html
        secondline: div1, div2, p5 
*/
console.log("\n\nTest 5: ================Expect: firstline: html   secondline: div1, div2, p5 ====================== \n\n");
console.log(p5.parent); // p5 is now a sibling of div1, so its parent should be html
console.log("\n");
console.log(html.children); // html now should have p5 as children

/* Test 6
Expect: empty []
*/
console.log("\n\nTest 6: =================Expect: empty []===================== \n\n");
console.log(div2.children); // Since p5 is now html's child, so div2 should have no children now

/* Test 7
Expect: html 
*/
console.log("\n\nTest 7: ===================Expect: html=================== \n\n");
let dumbSibling = new Node("div", [], html);
html.addSibling(dumbSibling);
// Below can't be null! Because html has no parent
console.log(dumbSibling.parent);