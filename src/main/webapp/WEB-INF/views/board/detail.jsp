<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <a href="#" onclick="goBack();">돌아가기</a>
</div>

<c:if test="${loginUser.i_user == data.i_user}">
    <div>
        <a href="del?iboard=${param.iboard}">삭제</a> <a
            href="mod?iboard=${param.iboard}">수정</a>
    </div>
</c:if>

<div>글번호 : ${requestScope.data.iboard}</div>
<div>글쓴이 : ${requestScope.data.writerNm} | 작성일 :
    ${requestScope.data.regdt}</div>
<div>
    <c:out value="${requestScope.data.ctnt}"/>
</div>
<div>
    ${requestScope.boardDomain.ctnt}
</div>

<c:if test="${not empty sessionScope.loginUser}">
    <div>
        <form id="cmtFrm" onsubmit="return false;">
            <input type="text" id="cmt" placeholder="댓글" onkeyup="enterKey();">
            <input type="button" value="댓글" onclick="regCmt();">
        </form>
    </div>
</c:if>

<div id="cmtList" data-login-user-pk="${sessionScope.loginUser.i_user}"
     data-iboard="${param.iboard}"></div>

<div id="modal" class="displayNone">
    <div class="modal_content">
        <form id="cmtModFrm" action="#">
            <input type="hidden" id="icmt">
            <input type="text" id="modCmt">
        </form>
        <input type="button" value="댓글 수정" onclick="modAjax();">
        <input type="button" value="취소" onclick="closeModModal();">
    </div>
</div>


