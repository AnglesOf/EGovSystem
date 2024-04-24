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

    <!-- 公共样式 开始 -->
    <%
        String cssPath = "/EGov";
    %>
    <link rel="stylesheet" type="text/css" href="<%=cssPath%>/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=cssPath%>/css/iconfont.css">
    <script type="text/javascript" src="<%=cssPath%>/framework/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=cssPath%>/layui/css/layui.css">
    <script type="text/javascript" src="<%=cssPath%>/layui/layui.js"></script>
    <!-- 滚动条插件 -->
    <link rel="stylesheet" type="text/css" href="<%=cssPath%>/css/jquery.mCustomScrollbar.css">
    <script src="<%=cssPath%>/framework/jquery-ui-1.10.4.min.js"></script>
    <script src="<%=cssPath%>/framework/jquery.mousewheel.min.js"></script>
    <script src="<%=cssPath%>/framework/jquery.mCustomScrollbar.min.js"></script>
    <script src="<%=cssPath%>/framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
    <!-- 公共样式 结束 -->
</head>
<body>
<div class="cBody">
    <div class="console">
        <form class="layui-form" action="${pageContext.request.contextPath}/user/searchById" method="get">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="s_id" required placeholder="输入ID" autocomplete="off" class="layui-input">
                </div>
                <button class="layui-btn" type="submit">检索</button>
            </div>
        </form>

        <script>
            layui.use('form', function () {
                var form = layui.form;

                //监听提交
                form.on('submit(formDemo)', function (data) {
                    layer.msg(JSON.stringify(data.field));
                    return false;
                });
            });
        </script>
    </div>

    <table class="layui-table">
        <thead>
        <tr>
            <th>id</th>
            <th>姓名</th>
            <th>电话</th>
            <th>性别</th>
            <th>操作</th>
            <th>操作</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            ArrayList<User> usersList = (ArrayList<User>) request.getAttribute("usersList");
            int len = usersList.size();
            for (User user : usersList) {
        %>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getPhone()%></td>
            <td><%=user.getSex()%></td>
            <td>
                <a href="${pageContext.request.contextPath}/govUser/removeUser?id=<%=user.getId()%>">
                    <button class="layui-btn layui-btn-xs layui-btn-danger">删除</button>
                </a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <!-- layUI 分页模块 -->
    <div id="pages"></div>

    <script>
        layui.use(['laypage', 'layer'], function () {
            var laypage = layui.laypage,
                layer = layui.layer;

            //总页数大于页码总数
            laypage.render({
                elem: 'pages'
                , count: <%=len%>
                , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                , jump: function (obj) {
                    console.log(obj)
                }
            });
        });
    </script>
</div>
</body>
</html>
