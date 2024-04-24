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

    <!-- 公共样式 开始 -->
    <link rel="stylesheet" type="text/css" href="<%=cssPath%>/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=cssPath%>/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="<%=cssPath%>/layui/css/layui.css">
    <script type="text/javascript" src="<%=cssPath%>/framework/jquery-1.11.3.min.js"></script>
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
<div class="cBody" style="left: 20%;margin-top:6%;">
    <form action="/EGov/messages/modifyMesForGov" method="get">

        <div style="display: flex;margin-top: 20px;">
            <div class="infoText"><label class="mPText" style="width: 42px;text-align: left;">ID</label></div>
            <div class="infoInput">
                <label for="i1">
                    <input type="text" id="i0" name="m_id" style="width:430px;margin-top:4px"
                           value="<%=messages.get(0).getM_id()%>" readonly unselectable="on"></label>
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
                                  style="margin-top:5px;width: 600px;"><%=messages.get(0).getContext()%></textarea>
                </label>
            </div>
        </div>

        <div style="display: flex;margin-top: 20px;">
            <div class="infoText" style="margin-top:2px;"><label class="mPText"
                                                                 style="text-align: left;width:42px">区域</label>
            </div>
            <div class="infoSel">
                <label for="i3">
                    <select name="area" id="i3" style="text-align: center;font-size:15px;margin-top:6px">
                        <option value="null" style="text-align: center;font-size:15px">请选择</option>
                        <option value="A"
                                style="text-align: center;font-size:15px" <%=messages.get(0).getArea().equals("A") ? "selected" : ""%>>
                            A区
                        </option>
                        <option value="B"
                                style="text-align: center;font-size:15px" <%=messages.get(0).getArea().equals("B") ? "selected" : ""%>>
                            B区
                        </option>
                        <option value="C"
                                style="text-align: center;font-size:15px" <%=messages.get(0).getArea().equals("C") ? "selected" : ""%>>
                            C区
                        </option>
                        <option value="D"
                                style="text-align: center;font-size:15px" <%=messages.get(0).getArea().equals("D") ? "selected" : ""%>>
                            D区
                        </option>
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
</div><!--footer/-->
<script>
    function checkModify() {
        var topic = document.getElementById('i1');
        var context = document.getElementById('i2');
        var area = document.getElementById('i3');
        var bq = document.getElementById('i4');
        var state = true;
        if (topic.value == null) {
            layer.msg('标题未填');
            state = false;
        }
        if (context.value == null) {
            layer.msg('正文未填');
            state = false;
        }
        if (selectItemByValue2(area) == null) {
            layer.msg('区域未选');
            state = false;
        }
        if (selectItemByValue2(bq) == null) {
            layer.msg('类别未选');
            state = false;
        }
        if (state !== false) {
            layer.msg("修改成功");
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

    var mesbq = '<%=messages.get(0).getBq()%>';
    loadBQSelect();

</script>
</body>
</html>
