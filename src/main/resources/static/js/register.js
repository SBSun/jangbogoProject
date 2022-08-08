function backHome() {
    var btn = document.getElementById('register__form_cancle');

    location.replace("/home/home.html");
}

var isCheckedEmail = false;

function submitRegister(){
    var btn = document.getElementById('register__form_submit');

    if(!isCheckedEmail){
        alert("이메일 중복확인 버튼을 눌러주세요.");
    }
}

function inputEmail(){
    isCheckedEmail = false;

    $("#register__form_submit").attr("type", "button");
}

function emailCheck(){
    var email = document.getElementById('register__email').value;
    var email_regex = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

    if(email == ""){
        alert.log("이메일을 입력해주세요.");
        return;
    }

    if(email_regex.test(email))
    {
        $.ajax({
            async: true,
            type:'POST',
            dataType:'json',
            data:{"email":email},
            url:"/member/emailCheck",

            success: function(data){

                if(data.result == 1){
                    alert("존재하는 이메일입니다.");
                }
                else if(data.result == 0){
                    alert("사용가능한 이메일입니다.");
                    isCheckedEmail = true;
                    $("#register__form_submit").attr("type", "submit");
                }
            },
           error: function (jqXHR, textStatus, errorThrown)
           {
                  alert(errorThrown + " " + textStatus);
           }
        });
    }
    else
    {
         alert.log("이메일 형식으로 작성해주세요.");
    }
}
