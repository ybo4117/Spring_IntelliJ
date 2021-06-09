<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${empty sessionScope.loginUser.profileImg}">
        <c:set var="img" value="/res/img/noprofile.jpg"/>

    </c:when>
    <c:otherwise>
        <c:set var="img"
               value="/img/${sessionScope.loginUser.i_user}/${sessionScope.loginUser.profileImg}"/>
    </c:otherwise>
</c:choose>
<div>
    <form action="profile" method="post" enctype="multipart/form-data" id="frm" onsubmit="return imgChk();">
        이미지 변경 : <input type="file" name="profileImg" accept="image/*">
        <input type="submit" value="이미지 업로드">
    </form>
</div>
<div>
    <div><img src="${img}"></div>
    <div>PK : ${sessionScope.loginUser.i_user}</div>
    <div>ID : ${sessionScope.loginUser.u_id}</div>
    <div>NAME : ${sessionScope.loginUser.u_nm}</div>

</div>

