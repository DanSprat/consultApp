var MIN_LENGTH = 8;
var SMALL_LETTERS = new RegExp("[a-z]");
var LARGE_LETTERS = new RegExp("[A-Z]");
var numbers = new RegExp("[0123456789]");
var symbols = new RegExp("[!?.,/<>#№@()^:;]");
$('input#register-password').on("keyup", function() {
   var text = $("#register-password").val();
   if (text.length == 0 ){
       $('#indicator').text("");
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


    $('#indicator').text("Слабо");
    if (count === 0){
        $('#indicator').text("Слабо");
    }
    if (count === 1){
        $('#indicator').text("Все еще слабо");
    }
    if (count === 2){
        $('#indicator').text("Нормально");
    }
    if (count === 3){
        $('#indicator').text("Хорошо");
    }
    if (count === 4){
        $('#indicator').text("Сильно");
    }
   // $(`#log-in-from`).submit();

});