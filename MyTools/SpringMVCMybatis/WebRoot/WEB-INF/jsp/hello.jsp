<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>SpringMVC</title>
  </head>
  
  <body>
    进入页面成功，实现SpringMVC功能
   <div>
    <table border="1">
    <tr><td>${user.id }</td><td>${user.username }</td><td>${user.sex }</td><td>${user.birthday }</td><td>${user.address }</td></tr>
    </table>
   </div>
  </body>
</html>
