<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dm.content.model.po.User" %>
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
    <!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
    <meta name="renderer" content="webkit">
    <!--国产浏览器高速模式-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="李幸阜"/>
    <!-- 作者 -->
    <meta name="revised" content="李幸阜"/>
    <!-- 定义页面的最新版本 -->
    <meta name="description" content="修改机房信息"/>
    <!-- 网站简介 -->
    <meta name="keywords" content="搜索关键字，以半角英文逗号隔开"/>
    <title>李幸阜开发</title>

    <%
        String cssPath = "/EGov";
    %>
    <!-- 公共样式 开始 -->
    <link href="<%=cssPath%>/css/base.css" rel="stylesheet" type="text/css" >
    <link href="<%=cssPath%>/layui/css/layui.css" rel="stylesheet" type="text/css" >
    <link href="<%=cssPath%>/css/css.css" rel="stylesheet"/>
    <link href="<%=cssPath%>/css/app.158927d2966ac2f157d8093f899ab95b.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=cssPath%>/layui/layui.js"></script>
    <script type="text/javascript" src="<%=cssPath%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=cssPath%>/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=cssPath%>/js/js.js"></script>
</head>

<body style="background:url(<%=cssPath%>/images/bodybg.png);">
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
    <div class="console">
        <form class="layui-form" action="/EGov/messages/findMessages" method="get">
            <div class="layui-form-item">
                <div class="layui-input-inline" style="margin-left:200px;">
                    <label>
                        <input type="text" name="t" required placeholder="${requestScope.t}" autocomplete="off" class="layui-input" required lay-verify="messagesVar">
                    </label>
                </div>
                <button class="layui-btn" style="margin-left:10px;">检索</button>
            </div>
        </form>

        <script>
            layui.use('form', function () {
                var form = layui.form;
                form.verify({
                    messagesVar: function (value, item) { //value：表单的值、item：表单的DOM对象
                        if (value == null || value == "") {
                            return '请输入关键词';
                        }
                    },
                });
            });
        </script>
    </div>

    <div class="newsCont" id="messageList">
        <dl>
            <dd>
                <h2>标题</h2>
                <time>2022年3月31日 来源：区域</time>
                <div class="newPar">正文
                    <a href="">继续阅读&gt;&gt;</a>
                </div><!--newPar/-->
            </dd>
            <div class="clears"></div>
        </dl>
    </div><!--newsCont/-->

    <!-- layUI 分页模块 -->
    <div id="pages"></div>
    <div class="footer">
        <div class="footBottom">
            <p>Copyright 2022 XX省XX市政府,All right Reserved 版权所有</p>
            <p>未经本网书面授权，请勿转载、摘编或建立镜像，负责视为侵权。 </p>
            <p>沪ICP备0000000号 技术支持：<a href="" target="_blank">xxxx</a></p>
        </div><!--footBottom/-->
    </div><!--footer/-->

    <script>
        window.onload = function () {
            loadData();//请求数据
            getPage();//分页操作
        };
        var page = 0; //设置首页页码
        var limit = 10;//设置一页显示的条数
        var total; //总条数


        function loadData() {
            $.ajax({
                type: "GET",
                url: "/EGov/messages/findMessagesLimit",//对应controller的URL
                async: false,
                data: {
                    "pageIndex": page,
                    "pageSize": limit,
                    "t": '${requestScope.t}',
                },
                success: function (data) {
                    total = data.length;//设置总条数
                    var html = '';
                    for (var i = 0; i < total; i++) {
                        html += '<dl><dd>';
                        html += '<h2>' + data[i].topic + '</h2>';
                        html += '<time>' + data[i].time + ' 来源:' + data[i].area + '区' + '</time>';
                        html += '<div class="newPar">';
                        html += data[i].context.substr(0, 122) + '...';
                        html += '<a href="/EGov/messages/visitMessage?m_id=' + data[i].m_id + '">继续阅读&gt;&gt;</a>';
                        html += '</div></dd><div class="clears"></div></dl>';
                    }
                    // console.log(html)
                    $("#messageList").empty().append(html);
                }
            });
        }

        function getPage() {
            layui.use(['laypage', 'layer'], function () {
                var laypage = layui.laypage;
                //执行一个laypage实例
                laypage.render({
                    elem: 'pages', //注意，这里的 test1 是 ID，不用加 # 号
                    limit: limit,//每页条数设置
                    layout: ['prev', 'page', 'next', 'skip'],
                    jump: function (obj, first) {
                        //obj包含了当前分页的所有参数，比如：
                        page = (obj.curr-1) * limit;//改变当前页码
                        //首次不执行
                        if (!first) {
                            loadData()//加载数据
                        }
                    }
                });
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
        function setUserName(){
            var userName = $.cookie('userName');
            if(userName!=null){
                var login = document.getElementById('login');
                login.innerText = '欢迎' + userName;
                login.href = '/EGov/userHTML/userInfo.html';
                var register = document.getElementById('register');
                register.innerHTML = '退出登录';
                register.href = "index2.html";
                register.onclick = delCookie;
            }
        }
        setUserName();
    </script>
</div>
</body>
</html>
