<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dm.content.model.po.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Method" %>
Created by 李幸阜.
User: angle
Date: 2022/3/30
Time: 21:37
To change this template use File | Settings | File Templates.
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<div class="cont">
    <div style="display: block;/* text-align: center; */position: relative;left: 25%;margin-top: 3%;;margin-bottom:12%">
        <form action="/EGov/messages/modifyMes" method="get">

            <div style="display: flex;margin-top: 20px;">
                <div class="infoText"><label class="mPText" style="width: 42px;text-align: left;">ID</label></div>
                <div class="infoInput">
                    <label for="i1">
                        <input type="text" id="i0" name="m_id" style="width:430px;margin-top:4px"
                               value="<%=messages.get(0).getM_id()%>" readonly  unselectable="on"></label>
                </div>
            </div>

            <div style="display: flex;margin-top: 20px;">
                <div class="infoText"><label class="mPText" style="width: 42px;text-align: left;">标题</label></div>
                <div class="infoInput">
                    <label for="i1">
                        <input type="text" id="i1" name="topic" style="width:430px;margin-top:4px"
                               value="<%=messages.get(0).getTopic()%>"></label>
                </div>
            </div>

            <div style="display: flex;margin-top: 20px;">
                <div class="infoText"><label class="mPText" style="width: 42px;text-align: left;">正文</label></div>
                <div class="infoInput">
                    <label for="i2">
                        <textarea autoFocus type="text" id="i2" name="context"
                                  style="margin-top:5px"><%=messages.get(0).getContext()%></textarea>
                    </label>
                </div>
            </div>

            <div style="display: flex;margin-top: 20px;">
                <div class="infoText" style="margin-top:2px;"><label class="mPText" style="text-align: left;width:42px">区域</label>
                </div>
                <div class="infoSel">
                    <label for="i3">
                        <select name="area" id="i3" style="text-align: center;font-size:15px;margin-top:6px">
                            <option value="null" style="text-align: center;font-size:15px">请选择</option>
                            <option value="A" style="text-align: center;font-size:15px" <%="A".equals(messages.get(0).getArea())?"selected":""%>>A区</option>
                            <option value="B" style="text-align: center;font-size:15px" <%="B".equals(messages.get(0).getArea())?"selected":""%>>B区</option>
                            <option value="C" style="text-align: center;font-size:15px" <%="C".equals(messages.get(0).getArea())?"selected":""%>>C区</option>
                            <option value="D" style="text-align: center;font-size:15px" <%="D".equals(messages.get(0).getArea())?"selected":""%>>D区</option>
                        </select>
                    </label>
                </div>
            </div>

            <div style="display: flex;margin-top: 20px;">
                <div class="infoText" style="margin-top:2px;">
                    <label class="mPText" style="text-align: left;width:42px">类别</label>
                </div>
                <div class="infoSel">
                    <label for="i4">
                        <select name="bq" id="i4" style="text-align: center;font-size:15px;margin-top:6px">
                            <option value="null" style="text-align: center;font-size:15px">请选择</option>
                            <option value="" style="text-align: center;font-size:15px"></option>
                        </select>
                    </label>
                </div>
            </div>

            <div style="display: flex;margin-top: 20px;text-align: center;">
                <div><br><br></div>
                <div class="" style="margin-left:18%;">
                    <button class="infoButton" type="submit" onclick="return checkModify();">修改留言</button>
                </div>
            </div>
        </form>
    </div>
</div><!--footer/-->

<div class="footer">
    <div class="footBottom">
        <p>Copyright 2022 湖北省武汉市政府,All right Reserved 版权所有</p>
        <p>未经本网书面授权，请勿转载、摘编或建立镜像，负责视为侵权。 </p>
        <p>沪ICP备0000000号 技术支持：<a href="" target="_blank">xxxx</a></p>
    </div><!--footBottom/-->
</div><!--footer/-->
<script>
    function checkModify() {
        var topic = document.getElementById('i1');
        var context = document.getElementById('i2');
        var area = document.getElementById('i3');
        var state = true;
        if (topic.value == null) {
            alert('标题未填');
            state = false;
        }
        if (context.value == null) {
            alert('正文未填');
            state = false;
        }
        if (selectItemByValue2(area) == null) {
            alert('区域未选');
            state = false;
        }
        if (selectItemByValue2(bq) == null) {
            layer.msg('类别未选');
            state = false;
        }
        if (state !== false) {
            alert("修改成功");
        }
        return state;
    }

    function selectItemByValue2(objSelect) {
        for (var i = 0; i < objSelect.options.length; i++) {
            if (objSelect.options[i].selected === true) {
                return objSelect.options[i].value;
            }
        }
    }

    function delCookie(){
        $.removeCookie("userId", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userName", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userPhone", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userPassword", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userFlag", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userSex", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userState", {domain:'localhost',path:'/EGov'});
        $.removeCookie("userSection", {domain: 'localhost', path: '/EGov'});
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

    function loadBQSelect() {
        $.ajax({
            type: "GET",
            url: "/EGov/bq/findBq",//对应controller的URL
            async: false,
            data: {},
            success: function (data) {
                total = data.length;  //设置总条数
                var html = '';
                html += '<option value="null" style="text-align: center;font-size:15px">请选择</option>';
                for (var i = 0; i < total; i++) {
                    html += '<option value="' + data[i].bq + '" style="text-align: center;font-size:15px" ';
                    if (mesbq == data[i].bq) {
                        html += 'selected';
                    }
                    html += '>' + data[i].bq;
                    html += '</option>';
                }
                // console.log(html)
                $("#i4").empty().append(html);
            }
        });
    }

    setUserName();
    var mesbq = '<%=messages.get(0).getBq()%>';
    loadBQSelect();
</script>
</body>
</html>
