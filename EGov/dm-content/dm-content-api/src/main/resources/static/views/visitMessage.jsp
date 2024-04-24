<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dm.content.model.po.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  Created by 李幸阜.
  User: angle
  Date: 2022/3/30
  Time: 21:37
  To change this template use File | Settings | File Templates.
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <title>智能政务系统</title>
    <meta name="keywords" content="智能政务系统"/>
    <meta name="description" content="智能政务系统"/>

    <%
        String cssPath = "/EGov";
        ArrayList<Message> messages = (ArrayList<Message>) request.getAttribute("messagesList");
    %>
    <link href="<%=cssPath%>/css/css.css" rel="stylesheet"/>
    <link href="<%=cssPath%>/css/app.158927d2966ac2f157d8093f899ab95b.css" rel="stylesheet"/>

    <script type="text/javascript" src="<%=cssPath%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=cssPath%>/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=cssPath%>/js/js.js"></script>
</head>

<body style="background:url(../images/bodybg.png);">
<div style="width:100%;height:180px"></div>
<div class="nav">
    <ul>
        <li><a></a></li>
        <li><a href="/EGov">网站首页</a></li>
        <li><a href="/EGov/userHTML/addMessage.html">留   言</a></li>
        <li><a href="/EGov/userHTML/userMessages.html">我的留言</a></li>
        <li><a href="/EGov/userHTML/dataCountForm.html">数据统计</a></li>
        <li><a href="/EGov/login.html" id="login">登录</a></li>
        <li><a href="/EGov/userHTML/register.html" id="register">注册</a></li>
        <li><a></a></li>
        <div class="clears"></div>
    </ul>
</div><!--nav/-->

<div class="insideCont" style="margin-left: 18%;width:890px;">
    <div class="insideLeft">
        <div class="detailsCont" style="font-size:20px;">
            <h3><%=messages.get(0).getTopic()%></h3>
            <time><%=messages.get(0).getTime()%>  ID:<%=messages.get(0).getM_id()%>  来源：<span class="red"><%=messages.get(0).getArea() + "区"%></span></time>
            <br/>
            <br/>
            <div class="detaPar"><%=messages.get(0).getContext()%>
            </div><!--detaPar/-->
        </div><!--detailsCont/-->
    </div><!--insideLeft/-->
    <div style="float:right;">
        <a style="cursor:pointer;font-size:20px;" id="up" onclick="upf()">👍(<%=messages.get(0).getUp()%>)</a>
        <a style="cursor:pointer;font-size:20px;margin-left:40px;" id="down" onclick="downf()">👎(<%=messages.get(0).getDown()%>)</a>
    </div>
    <div class="clears"></div>
</div><!--cont/-->

<div class="footer">
    <div class="footBottom">
        <p>Copyright 2022 湖北省武汉市政府,All right Reserved 版权所有</p>
        <p>未经本网书面授权，请勿转载、摘编或建立镜像，负责视为侵权。 </p>
        <p>沪ICP备0000000号 技术支持：<a href="" target="_blank">xxxx</a></p>
    </div><!--footBottom/-->
</div><!--footer/-->
<script>
    var first=true;
    function upf(){
        up = document.getElementById("up");
        down = document.getElementById("down");
        if(first===true){
            var up_i = parseInt(up.innerText.match("[0-9]+")) + 1;
            up.innerText = '👍(' + up_i + ')';
            up.style.color = 'blue';
            first = false;
        }else{
            var i = parseInt(up.innerText.match("[0-9]+")) + 1;
            var j = parseInt(down.innerText.match("[0-9]+")) - 1;
            if(up.style.color !== 'blue'){
                up.innerText = '👍(' + i + ')';
                up.style.color = 'blue';
                down.innerText = '👎(' + j + ')';
                down.style.color = '#333';
            }
        }
        $.ajax({
            type: "GET",
            url: "/EGov/messages/modifyUpDown",//对应controller的URL
            async: false,
            data: {
                "up": parseInt(up.innerText.match("[0-9]+")),
                "down": parseInt(down.innerText.match("[0-9]+")),
                "m_id": <%=messages.get(0).getM_id()%>,
            },
            success: function (data, status, xhr) {
                if (xhr.getResponseHeader('CONTEXTPATH') != null) {
                    window.location.href = xhr.getResponseHeader('CONTEXTPATH');
                }
            }
        });
    }

    function downf(){
        up = document.getElementById("up");
        down = document.getElementById("down");
        if(first===true){
            var up_i = parseInt(down.innerText.match("[0-9]+")) + 1;
            down.innerText = '👎(' + up_i + ')';
            down.style.color = 'blue';
            first = false;
        }else{
            var i = parseInt(up.innerText.match("[0-9]+")) - 1;
            var j = parseInt(down.innerText.match("[0-9]+")) + 1;
            if(down.style.color !== 'blue'){
                up.innerText = '👍(' + i + ')';
                up.style.color = '#333';
                down.innerText = '👎(' + j + ')';
                down.style.color = 'blue';
            }
        }
        $.ajax({
            type: "GET",
            url: "/EGov/messages/modifyUpDown",//对应controller的URL
            async: false,
            data: {
                "up": parseInt(up.innerText.match("[0-9]+")),
                "down": parseInt(down.innerText.match("[0-9]+")),
                "m_id": <%=messages.get(0).getM_id()%>,
            },
            success: function (data, status, xhr) {
                if (xhr.getResponseHeader('CONTEXTPATH') != null) {
                    window.location.href = xhr.getResponseHeader('CONTEXTPATH');
                }
            }
        });
    }

    function delCookie(){
        $.removeCookie("userId", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userName", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userPhone", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userPassword", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userFlag", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userSex", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userState", {domain:'localhost',path:'/EGov'});
    }
    function setUserName() {
        var userName = $.cookie('userName');
        if (userName != null) {
            var login = document.getElementById('login');
            login.innerText = '欢迎' + userName;
            login.href = '/userHTML/userInfo.html';
            var register = document.getElementById('register');
            register.innerHTML = '退出登录';
            register.href = "EGov/index2.html";
            register.onclick = delCookie;
        }
    }
    setUserName();
</script>
</body>
</html>
