<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>You're win !</title>
</head>
<body>
<h1>Congratulations! You are win!</h1>
<a href="index.html">Start again!</a>
<%
    String attribute = (String) session.getAttribute("countOfGames");
    int countOfGames = Integer.parseInt(attribute);
    countOfGames += 1;
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("countOfGames")) {
            cookie.setValue(String.valueOf(countOfGames));
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            response.addCookie(cookie);
        }
    }
%>
</body>
</html>