<%--
  Created by IntelliJ IDEA.
  User: 94587
  Date: 2020/7/1
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>

    <style>
        body {
            font: 12px/30px microsoft yahei;
            background: #053364;
        }

        .login_content {
            background: url(images/login.png) no-repeat;
            width: 737px;
            height: 396px;
            margin: 150px auto 0px;
        }

        .login_name {
            float: left;
            margin: 50px 0px 0px 400px;
            color: black;
            font-size: 14px;
            line-height: 35px;
        }

        .login_name h3 {
            font-size: 16px;
            border-bottom: 1px dotted #02356f;
        }

        .login_input {
            padding: 5px;
            height: 20px;
            line-height: 20px;
            color: gray;
            width: 200px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
        }

        .yzm_input {
            width: 100px;
            padding: 5px;
            height: 20px;
            line-height: 20px;
            color: gray;
            border: 1px solid #ccc;
            margin-right: 5px;
        }

        .login_button {
            background: #008ffd;
            border-radius: 5px;
            width: 80px;
            height: 35px;
            line-heihgt: 35px;
            display: block;
            margin-top: 10px;
            color: white;
            border: none;
            font-family: microsoft yahei;
            cursor: pointer;
        }

        .login_button:hover {
            background: #00b8e6;
            color: yellow;
        }
    </style>
    <script type="text/javascript">
        function changeValidateCode() {
            document.getElementById("validateCode").src = "ValidateCodeServlet?rand="
                + Math.random();
        }

        // 表单提交请求验证
        function validate() {
            if (document.getElementById("userLogName").value == "") {
                alert("用户登录名不能为空！");
                document.getElementById("userLogName").focus();
                return false;
            }
            if (document.getElementById("userPwd").value == "") {
                alert("登录密码不能为空！");
                document.getElementById("userPwd").focus();
                return false;
            }
            if (document.getElementById("validateCode").value == "") {
                alert("验证码不能为空！");
                document.getElementById("validateCode").focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="login_content">
    <div class="login_name">
        <h3>用户登录</h3>
        <form action="UserServlet?type=login" method="post" onsubmit="return validate();">
            <div>
                用户名：<input name="userLogName" id="userLogName" type="text"
                           class="login_input"/>
            </div>
            <div>
                密&nbsp;&nbsp;&nbsp;&nbsp;码：<input name="userPwd" id="userPwd"
                                                  type="password" class="login_input"/>
            </div>
            <div>
                验证码：<input name="validateCode" id="validateCode2" type="text"
                           class="yzm_input"/><img id="validateCode" src="/ValidateCodeServlet" title="点击换一换"
                                                   onclick="changeValidateCode()">
                <a href="javascript:changeValidateCode();" style="color: blue;">看不清？</a>

            </div>
            <div align="center">
                <input type="submit" class="login_button" value="登&nbsp;&nbsp;录"></input>
            </div>
        </form>
    </div>
</div>
</body>
</html>
