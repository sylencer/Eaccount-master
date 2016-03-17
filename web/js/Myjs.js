/**
 * Created by yehao on 16/3/17.
 */
var i = 0;
var m = 0;
function AddProduct() {
    $("#priceError").html("商品单价");
    $("#priceError").css({color:"black"});
    $("#numError").html("商品数量");
    $("#numError").css({color:"black"});
    if ($("#price").val()=="") {
        $("#priceError").html("商品单价 (单价不能为空)");
        $("#priceError").css({color:"red"});
        return;
    }
    if (isNaN($("#price").val())) {
        $("#priceError").html("商品单价 (单价不合法)");
        $("#priceError").css({color:"red"});
        return;
    }

    if ($("#num").val()=="") {
        $("#numError").html("商品数量 (数量不能为空)");
        $("#numError").css({color:"red"});
        return;
    }
    if (isNaN($("#num").val())) {
        $("#numError").html("商品数量 (数量不合法)");
        $("#numError").css({color:"red"});
        return;
    }

    var j = 1;
    for (;j<=i;j++) {
        if ($("#tdName" + j.toString()).html() == $("#productName").val()) {
            if ($("#tdPrice" + j.toString()).html() != $("#price").val()) {
                alert("与之前添加的同类商品价格不符!");
                return;
            }
            var price = $("#tdPrice" + j.toString()).html();
            var num = $("#num").val();
            m = m + price * num;
            var tmp = $("#tdNum" + j.toString()).html();
            num = num * 1 + tmp * 1;
            $("#total_price_seller").val(m);
            $("#tdNum" + j.toString()).html(num)
            return;
        }
    }
    i = i + 1;
    var s = i.toString();
    $("#productDetail").append("<tr id='trId"+s+"' class='even gradeC'><td id='tdName"+s+"'>"+$("#productName").val()
        +"</td> <td id='tdPrice"+s+"'>"+$("#price").val()
        +"</td> <td id='tdNum"+s+"' class='center'>"+$("#num").val()
        +"</td> <td class='center'><a href='javascript:void(0)' onclick='DeleteProduct("+s+")' ;>删除</a></td> </tr>");

    var price = ($("#tdPrice" + s).html());
    var num = ($("#tdNum" + s).html());
    m = m + price * num;
    $("#total_price_seller").val(m);
}

function DeleteProduct(id) {
    var s = id.toString();
    var price = ($("#tdPrice" + s).html());
    var num = ($("#tdNum" + s).html());
    m = m - price * num;
    $("#totalPrice").val(m);
    $("#trId" + s).remove();
}
function AddOrder() {

}