<%--
  Created by IntelliJ IDEA.
  User: Jay
  Date: 2018-6-14
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <style scoped="scoped">
        .textbox{
            height:20px;
            margin:0;
            padding:0 2px;
            box-sizing:content-box;
        }
    </style>
    <script>
        $(function () {
            $('#btn').click(function () {
                $('#dd').dialog('open');
            })
            $('#dd').dialog({
                title: '请填写注册信息(*为必填项)',
                width: 400,
                height: 340,
                resizable: false,
                closed: true,
                cache: false,
                modal: true,
                showType:'fade',
                style:{
                    right:'',
                    bottom:''
                },
                buttons: [{
                    text: '注册',
                    handler: function () {
                        $('#ff').form('submit', {
                            url: '${pageContext.request.contextPath}/Servlet',
                            success: function (data) {
                                if(data > 0){
                                    $.messager.alert('恭喜','注册成功,赶紧去登录吧','info');
                                    location.href='${pageContext.request.contextPath}';
                                }else{
                                    $.messager.alert('抱歉','注册出问题了','info');
                                    $('#dd').dialog('open');
                                    $('#ff').form('reset');
                                }
                            }
                        });
                    }
                }, {
                    text: '重置',
                    handler: function () {

                    }
                }]
            });
            //校验框的校验
            $('.validatebox-text').bind('blur', function(){
                $(this).validatebox('enableValidation').validatebox('validate');
            });
            //用户名是否重复的校验
            $("#ff input[name='username']").blur(function () {
                var u =  $("#ff input[name='username']").val();
                $.post('${pageContext.request.contextPath}/Servlet',{'m':'findUserByName','key':u},function (d) {
                    if (d == 'true'){
                        $.messager.alert('提示信息','用户名已被占用','question');
                        $("#ff input[name='username']").val('')
                    }
                })
            })
            //重复密码的验证
            $("input[name='repassword']").blur(function () {
                var rpwd = $("#ff input[name='repassword']").val();
                var pwd = $("#ff input[name='password']").val();
                if(rpwd != pwd){
                    $.messager.alert('提示信息','两次密码填写的不一致','question')
                    $("input[name='repassword']").val('');
                }
            });
        })
    </script>
</head>
<body>
<center>
    <font style="font-size: 40px; color: #ff0000;">欢迎来到学生管理系统</font>
    <div class="easyui-panel" title="用户登录" style="width:400px;padding:30px 70px 20px 70px">
        <form method="post" action="${pageContext.request.contextPath}/Servlet">
            <input type="hidden" name="m" value="loginByNameAndPassword">
            <div style="margin-bottom:10px">
                <input name="username" class="easyui-textbox" style="width:100%;height:40px;padding:12px"
                       data-options="prompt:'请输入用户名',iconCls:'icon-man',iconWidth:38">
            </div>
            <div style="margin-bottom:20px">
                <input name="password" class="easyui-textbox" type="password"
                       style="width:100%;height:40px;padding:12px"
                       data-options="prompt:'请输入密码',iconCls:'icon-lock',iconWidth:38">
            </div>
            <div style="margin-bottom:20px">
                <input type="checkbox" checked="checked">
                <span>记住用户名</span>
            </div>
            <div>
                <input type="submit" value="登录" class="easyui-linkbutton" style="padding:5px 0px;width:100%;">
                </input>
            </div>
            <div><font style="color: red;font-size: 10px">${msg}</font></div>
        </form>
    </div>
    <div>
        还没有账号?去<input type="button" id="btn" class="easyui-linkbutton" style="padding:3px 0px;width:40px;"
                      value="注册"></input>
    </div>
    <div id="dd">
        <center>
            <form id="ff" method="post">
                <input type="hidden" name="m" value="registerUser">
                <table cellpadding="5">
                    <tr>
                        <td>用户名:</td>
                        <td><input name="username" class="easyui-validatebox textbox"
                                   data-options="required:true,validType:'length[2,10]',novalidate:true">
                        <span><font color="red">*</font></span></td>
                    </tr>
                    <tr>
                        <td>密码:</td>
                        <td><input name="password" class="easyui-validatebox textbox" type="password"
                                   data-options="required:true,validType:'length[6,32]',novalidate:true">
                        <span><font color="red">*</font></span></td>
                    </tr>
                    <tr>
                        <td>再次输入密码:</td>
                        <td><input name="repassword" class="easyui-validatebox textbox" type="password"
                                   data-options="required:true,validType:'length[6,32]',novalidate:true">
                        <span><font color="red">*</font></span></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input name="email" class="easyui-validatebox textbox"
                                   data-options="required:true,validType:'email',novalidate:true">
                        <span><font color="red">*</font></span></td>
                    </tr>
                    <tr>
                        <td>性别:</td>
                        <td><input type="radio" name="sex" value="男">男
                        <input type="radio" name="sex" value="女">女</td>
                    </tr>
                    <tr>
                        <td>生日:</td>
                        <td><input name="birthday" class="easyui-datebox textbox" data-options="novalidate:true"></td>
                    </tr>
                    <tr>
                        <td>手机号:</td>
                        <td><input name="phone" class="easyui-validatebox textbox" data-options="validType:'length[11,11]',novalidate:true"></td>
                    </tr>
                </table>
            </form>
        </center>
    </div>
</center>
</body>
</html>
