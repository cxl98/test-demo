$(function () {
    var userFlag = false;
    var pwdFlag = false;
    $('.user-txt').change(function () {
        if (!/^[0-9]{11,18}$/.test($('.user-txt').val().trim())) {
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
    })
    
    $('.pwd-txt').change(function () {
        if (!/^(?!^\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{4,16}$/.test($('.pwd-txt').val().trim())) {
            $(this).addClass('no');
            $(this).removeClass('yes');
            pwdFlag = false;
            alert('请输入正确的格式！(4-16为由数字和字母组成)');
            $('.qrpwd').prop('disabled',true)
        }
        else {
            console.log('yes')
            $(this).removeClass('no')
            pwdFlag = true;
            $(this).addClass('yes');
            $('.qrpwd').prop('disabled',false)
        }
        if($('.pwd-txt').val().trim() == "") {
            $(this).removeClass('yes');
            $(this).removeClass('no');
        }
    });
    $('.qrpwd').change(function () {
        if ($('.pwd-txt').val() !== $(this).val()) {
            pwdFlag = false;
            $(this).addClass('no');
            $(this).removeClass('yes');
            alert('密码不一致，请重新输入！')
        }
        else {
            pwdFlag = true;
            $(this).removeClass('no')
            $(this).addClass('yes');
        }
    });
    $('.sign').click(function() {
        if (pwdFlag == true && userFlag == true && $('.chk').prop('checked') == true) {
            alert('注册成功！');
        }
        else {
            alert('请完善注册!')
        }
    })
})