<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: tbag
  Date: 2018/3/7
  Time: 上午11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 如果用户名已经存在，说明已经登录，
    String username = (String) request.getParameter("username");
    System.out.println(123);
    PrintWriter writer = response.getWriter();
    if (username.equals("user3")) {
        System.out.println("username"+username);

        writer.write("{tocken:'"+username+"'}");

        writer.write("user3_login_success");
    }
    else if (username.equals("user4")){

        writer.write("user4_login_success");
    }
    else {
        writer.write("{result:'false'}");
    }
%>

