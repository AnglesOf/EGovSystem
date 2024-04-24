<%@ page import="com.dm.content.model.po.User" %>
Created by 李幸阜.
User: angle
Date: 2022/3/30
Time: 21:37
To change this template use File | Settings | File Templates.
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <title>智能政务系统</title>
    <meta name="keywords" content="智能政务系统"/>
    <meta name="description" content="智能政务系统"/>

    <!-- 公共样式 开始 -->
    <%
        String cssPath = "/EGov";
        User user = (User) request.getAttribute("user");
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
    <style>
        .layui-form {
            margin-right: 30%;
        }

        .layui-form-label {
            width: 100px;
        }

        .layui-input-block {
            margin-left: 130px;
        }
    </style>
</head>

<body>
<div class="cBody" style="left: 28%;margin-top:10%;">
    <form name="form1" class="layui-form" action="/EGov/user/modifyInfoForGov" method="get">
        <div class="layui-form-item">
            <label class="layui-form-label">ID号</label>
            <div class="layui-input-inline">
                <input class="layui-input" type="text" id="i1" name="id" value="<%=user.getId()%>" readonly unselectable="on">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input class="layui-input" type="text" id="i2" name="name" value="<%=user.getName()%>" required lay-verify="name">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-inline">
                <input class="layui-input" type="text" id="i3" name="phone" value="<%=user.getPhone()%>" required lay-verify="phone">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <select name="sex" id="i4">
                    <option value="null" <%=user.getSex()==null?"selected":""%>>请选择</option>
                    <option value="男" <%="男".equals(user.getSex())?"selected":""%>>男</option>
                    <option value="女" <%="女".equals(user.getSex())?"selected":""%>>女</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

    <script>
        layui.use('form', function () {
            var form = layui.form;
            form.verify({
                name: function (value, item) { //value：表单的值、item：表单的DOM对象
                    if (value == null || value == "") {
                        return '请输入姓名！';
                    }
                },
                phone: function (value, item) {
                    if (value == null || value == "") {
                        return '请输入电话号码！';
                    }else {
                        $.ajax({
                            type: "GET",
                            url: "/EGov/user/checkUserByPhone",
                            data: {phone: value},
                            async: false,
                            success: function (data) {
                                if (data.id != null) {
                                    return "该手机号已存在!";
                                }
                            }
                        });
                    }
                }
            });
            //监听提交
            form.on('submit(formDemo)', function (data) {
                layer.msg("修改成功");
                // return false;
            });
        });
    </script>
</div>
</body>
</html>
