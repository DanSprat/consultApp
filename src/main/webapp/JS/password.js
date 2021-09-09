var level_0_arr = new Uint8Array([208,161,208,187,208,190,208,182,208,189,208,190,209,129,209,130,209,140,32,208,191,208,176,209,128,208,190,208,187,209,143,58,32,208,161,208,187,208,176,208,177,209,139,208,185])
var level_1_arr = new Uint8Array([208,161,208,187,208,190,208,182,208,189,208,190,209,129,209,130,209,140,32,208,191,208,176,209,128,208,190,208,187,209,143,58,32,208,158,209,135,208,181,208,189,209,140,32,208,161,208,187,208,176,208,177,209,139,208,185]);
var level_2_arr = new Uint8Array([208,161,208,187,208,190,208,182,208,189,208,190,209,129,209,130,209,140,32,208,191,208,176,209,128,208,190,208,187,209,143,58,32,208,157,208,190,209,128,208,188,208,176,208,187,209,140,208,189,209,139,208,185])
var level_3_arr = new Uint8Array([208,161,208,187,208,190,208,182,208,189,208,190,209,129,209,130,209,140,32,208,191,208,176,209,128,208,190,208,187,209,143,58,32,208,165,208,190,209,128,208,190,209,136,208,184,208,185])
var level_4_arr = new Uint8Array([208,161,208,187,208,190,208,182,208,189,208,190,209,129,209,130,209,140,32,208,191,208,176,209,128,208,190,208,187,209,143,58,32,208,158,209,130,208,187,208,184,209,135,208,189,209,139,208,185])

let level_0 = new TextDecoder().decode(level_0_arr);
let level_1 = new TextDecoder().decode(level_1_arr);
let level_2 = new TextDecoder().decode(level_2_arr);
let level_3 = new TextDecoder().decode(level_3_arr);
let level_4 = new TextDecoder().decode(level_4_arr);
var MIN_LENGTH = 8;
var SMALL_LETTERS = new RegExp("[a-z]");
var LARGE_LETTERS = new RegExp("[A-Z]");
var numbers = new RegExp("[0123456789]");
var symbols = new RegExp("[!?.,/<>#№@()^:;]");
$('input#register-password').on("keyup", function() {
    $(`#register-password`).attr("is","false");
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
        $('#indicator').css({"backgroundColor": "white","width":"20%"});
        $('#indicator_text').text(level_0);
    }
    if (count === 1){
        $('#indicator').css({"backgroundColor": "red","width":"40%"});
        $('#indicator_text').text(level_1);
        alert(level_1);

    }
    if (count === 2){
        $('#indicator').css({"backgroundColor": "orange","width":"60%"});
        $('#indicator_text').text(level_2);

    }
    if (count === 3){
        $('#indicator').css({"backgroundColor": "yellow","width":"80%"});
        $('#indicator_text').text(level_3);
    }
    if (count === 4){
        $('#indicator').css({"backgroundColor": "green","width":"100%"});
        $('#indicator_text').text(level_4);
        $(`#register-password`).attr("is","true");
    }
   // $(`#log-in-from`).submit();

});

$(`#register-confirm`).on("keyup",function () {
    $(`#register-confirm`).attr("is","false");
    var attr = $(`#register-password`).attr("is");
    if (attr == "true"){
        var password = $(`#register-confirm`).val();
        var confirm = $('#register-password').val();
        if (confirm == password){
            $(`.under-submit`).css({"color":"green"});
            $(`.under-submit`).text("Пароли совпадают");
            $(`#register-confirm`).attr("is","true");
        } else {
            $(`.under-submit`).css({"color":"red"});
            $(`.under-submit`).text("Пароли  не совпадают");
        }
    }



});

$(`#register_submit`).on("click",function () {
    var is_pass = $(`#register-password`).attr("is");
    var is_confirm = $(`#register-confirm`).attr("is");
    if (is_pass && is_confirm){
        alert("hello");
        return true;
    }
    alert("hello");
    return false;
});