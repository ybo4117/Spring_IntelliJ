<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<div>--%>
<%--    <span>로그인 아이디 : ${sessionScope.loginUser.u_id}</span>--%>
<%--    <span><a href="/user/profile">프로필</a></span>--%>
<%--</div>--%>
<table>

    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>작성일자</th>
    </tr>


    <c:forEach items="${requestScope.list}" var="item">
        <tr class="record" onclick="moveToDetail(${item.iboard});">
            <td>${item.iboard}</td>
            <td><c:choose>
                <c:when test="${param.searchType eq 1 || param.searchType eq 2}">
                    ${item.title.replace(param.searchText, '<mark>' += param.searchText += '</mark>')}
                </c:when>
                <c:otherwise>
                    ${item.title}
                </c:otherwise>
            </c:choose></td>

            <c:choose>
                <c:when test="${empty item.profileImg}">
                    <c:set var="img" value="/res/img/noprofile.jpg"/>

                </c:when>
                <c:otherwise>
                    <c:set var="img" value="/img/${item.i_user}/${item.profileImg}"/>
                </c:otherwise>
            </c:choose>

            <td><c:choose>
                <c:when test="${param.searchType eq 4}">
                    ${item.writerNm.replace(param.searchText,'<mark>' += param.searchText += '</mark>')}
                </c:when>
                <c:otherwise>
                    ${item.writerNm}
                </c:otherwise>
            </c:choose> <img src="${img}" class="profileImg"></td>

            <td>${item.regdt}</td>
        </tr>
    </c:forEach>
</table>

