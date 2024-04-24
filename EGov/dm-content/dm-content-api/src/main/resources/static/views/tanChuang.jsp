<%@ page contentType="text/html;charset=UTF-8" language="java" %>
Created by 李幸阜.
  User: angle
  Date: 2020/10/28
  Time: 16:47
  To change this template use File | Settings | File Templates.
<html>
<head>
    <title>提示</title>
</head>
<body>
<script type="text/javascript">
    onload=function(){
        // alert("你好");
        alert("${requestScope.msg}")
        setInterval(go, 500);
    };
    function go(){
        location.href="${requestScope.loc}";
        // location.href="/RR/login.html";
    }
</script>
</body>
</html>
