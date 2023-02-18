function hideText(){
    document.getElementById("main-text").style.display="none";
}
function showText(){
    document.getElementById("main-text").style.display="";
}
function increaseNumber(){
    var number=Number(document.getElementById("number").innerHTML);
    number++;
    document.getElementById("number").innerHTML=number;
}
function showList(){
    var words=["Spain","Greece","Italy"];
    list=document.getElementById("list")
    list.innerHTML="";
    words.forEach((item)=>{
        var li=document.createElement("li");
        li.innerText=item;
        list.appendChild(li);
    })
}