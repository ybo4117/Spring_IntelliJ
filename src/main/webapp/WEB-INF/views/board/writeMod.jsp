<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="writeMod" method="post">
  <input type="hidden" name = "iboard" value="">
  <div><input type="text" name="title" placeholder="제목"></div>
  <div><textarea name="ctnt" placeholder="내용"></textarea></div>
  <div>
    <input type="submit" value="등록">
    <input type="reset" value="초기화">
  </div>
</form>

