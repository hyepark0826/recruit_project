var jsonData = JSON.parse('${category}');
console.log(jsonData);

var cateArr = new Array();
var cateObj = new Object();


for(var i=0; i<jsonData.length; i++){
    cateObj = new Object();
    cateObj.cateCode = jsonDaya[i].cateCode;
    cateObj.cateName = jsonData[i].cateName;
    cateArr.push(cateObj);
}

var cateSelect = $("select.category")
for(var i = 0; i<cateArr.length; i++){
    cateSelect.append("<option value='"+cateArr[i].cateCode+"'>"+cateArr[i].cateName+"</option>");
}
// $(document).on("select.category", function(){

// })