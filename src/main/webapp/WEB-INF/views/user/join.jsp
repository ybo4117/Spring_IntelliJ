<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Join</title>
</head>
<body>
<h1>Join</h1>
<form id='frm' action="join" method="post">
    <div>
        <input type="text" name="u_id" placeholder="아이디">
        <input type="button" id="btnChkId" value="중복ID체크">
    </div>
    <div id="chkUidResult"></div>

    <div>
        <input type="password" name="u_pw" placeholder="비밀번호">
    </div>
    <div>
        <input type="password" name="chkUpw" placeholder="비밀번호확인">
    </div>
    <div>
        성별:
        <label>남성 <input type="radio" name="gender" value="0" checked> </label>
        <label>여성 <input type="radio" name="gender" value="1"> </label>
    </div>
    <div>
        <input type="text" name="u_nm" placeholder="이름">
    </div>

    <div>
        <input type="submit" value="회원가입">
        <input type="reset" value="초기화">
    </div>

</form>

</body>
</html>
