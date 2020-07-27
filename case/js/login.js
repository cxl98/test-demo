$(function () {
    var userFlag = false;
    var pwdFlag = false;
    $('.user_txt').change(function () {
        if (!/^[0-9]{11,18}$/.test($('.user_txt').val().trim())) {
            $(this).addClass('no');
            $(this).removeClass('yes');
            userFlag = false;
            alert('请输入正确的格式！(11-18位纯数字)');
        }
        else {
            console.log('yes')
            $(this).removeClass('no')
            $(this).addClass('yes');
            userFlag = true;
        }
        if($('.user_txt').val().trim() == "") {
            $(this).removeClass('yes');
            $(this).removeClass('no');
        }
    });
    $('.pwd_txt').change(function () {
        if (!/^(?!^\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{4,16}$/.test($('.pwd_txt').val().trim())) {
            $(this).addClass('no');
            $(this).removeClass('yes');
            pwdFlag = false;
            alert('请输入正确的格式！(4-16为由数字和字母组成)');
        }
        else {
            console.log('yes')
            $(this).removeClass('no')
            pwdFlag = true;
            $(this).addClass('yes');
        }
        if($('.pwd_txt').val().trim() == "") {
            $(this).removeClass('yes');
            $(this).removeClass('no');
        }
    });
    $('.denglu').click(function () {
        Login();
    });
    function Login() {  
        // console.log(pwdFlag); 
        // console.log(userFlag)
        if (pwdFlag == true && userFlag == true) {
            // 获取user 和 pwd 值
            var val = $('.user_txt').val();
            var val1 = $('.pwd_txt').val();
            jsLogin.login(val,val1);

            console.log(val);
            console.log(val1);
        }
        else {
            alert('请按照格式输入正确账户密码！')
        }
    }
});