<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: tbag
  Date: 2018/3/7
  Time: 下午2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 如果用户名已经存在，说明已经登录，
    String username = (String) request.getParameter("tocken");
    System.out.println(123);
    PrintWriter writer = response.getWriter();
    if (username.equals("user3_login_success")) {
        System.out.println("user"+username);
        writer.write("我有999999999元");
    }
    else if (username.equals("user4_login_success")){

        writer.write("我有0元");
    }
    else {
        writer.write("{result:'false'}");
    }
%>
