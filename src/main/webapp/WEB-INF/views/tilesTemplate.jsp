<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>${requestScope.title}</title>
    <link rel="stylesheet" href="/res/css/common.css">
    <link rel="stylesheet" href="/res/css/<tiles:getAsString name="res"/>.css">
    <script defer src="/res/js/common.js"></script>
    <script defer src="<tiles:getAsString name="res"/>.js"></script>
</head>
<body>

        <div id="container">
            <tiles:insertAttribute name="header"/>
            <section>
                <tiles:insertAttribute name="content"/>
            </section>
            <footer>
                Copyright 2021.
            </footer>
        </div>

</body>
</html>
