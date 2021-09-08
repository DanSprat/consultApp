var MIN_LENGTH = 8;
var SMALL_LETTERS = new RegExp("[a-z]");
var LARGE_LETTERS = new RegExp("[A-Z]");
var numbers = new RegExp("[0123456789]");
var symbols = new RegExp("[!?.,/<>#№@()^:;]");
$('input#register-password').on("keyup", function() {
   var text = $("#register-password").val();
   if (text.length == 0 ){
       $('#indicator').css({"backgroundColor": "white","width":"0%"});
       $('#indicator_text').text("");
       return;
   }
    if (text.length <8 ){
        $('#indicator').css({"backgroundColor": "white","width":"0%"});
        $('#indicator_text').text("Длина пароля должна быть больше 8 символов");

        return;
    }

   var is_SL = false;
   var is_LL = false;
   var is_N = false;
   var is_SM = false;

   if (!is_SL && SMALL_LETTERS.exec(text)){
       is_SL = true;
   }
   if (!is_LL && LARGE_LETTERS.exec(text)){
       is_LL =  true;
   }
   if (!is_N && numbers.exec(text)){
       is_N = true;
   }
   if (!is_SM && symbols.exec(text)){
       is_SM = true;
   }

   let count = 0;
   if (is_SM) count++;
   if (is_N) count++;
   if (is_LL) count++;
   if (is_SL) count++;


    if (count === 0){
        $('#indicator').css({"backgroundColor": "white","width":"0%"});

    }
    if (count === 1){
        $('#indicator').css({"backgroundColor": "red","width":"25%"});
        $('#indicator_text').text("Сложность пароля: Слишком слабый");

    }
    if (count === 2){
        $('#indicator').css({"backgroundColor": "orange","width":"50%"});
        $('#indicator_text').text("Сложность пароля: Слабый");

    }
    if (count === 3){
        $('#indicator').css({"backgroundColor": "yellow","width":"75%"});
        $('#indicator_text').text("Сложность пароля: Нормальный");
    }
    if (count === 4){
        $('#indicator').css({"backgroundColor": "green","width":"100%"});
        $('#indicator_text').text("Сложность пароля: Хороший");
        $(`#register-password`).attr("is","true");
    }
   // $(`#log-in-from`).submit();

});

$(`#register-confirm`).on("keyup",function () {

    var attr = $(`#register-password`).attr("is");
    if (attr == "true"){
        var password = $(`#register-confirm`).val();
        var confirm = $('#register-password').val();
        if (confirm == password){
            $(`.under-submit`).css({"color":"green"});
            $(`.under-submit`).text("Пароли совпадают");
        } else {
            $(`.under-submit`).css({"color":"red"});
            $(`.under-submit`).text("Пароли  не совпадают");
        }
    }



})