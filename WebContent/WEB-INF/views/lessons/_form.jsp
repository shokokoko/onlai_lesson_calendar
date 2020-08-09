<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="tname">ティーチャー名</label><br />
<c:out value="${sessionScope.login_instructor.tname}" />
<br /><br />

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${lesson.title}" />
<br /><br />

<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${lesson.content}</textarea>
<br /><br />

<label for="target">対象者</label><br />
<select name="target">
<option value="未選択">未選択</option>
<option value="はじめて先生のレッスンにご参加の方">はじめて先生のレッスンにご参加の方</option>
<option value="このレッスンを受けたことがある方">このレッスンを受けたことがある方</option>
<option value="経験者全般">経験者全般</option>
<option value="初心者">初心者</option>
<option value="中級者">中級者</option>
<option value="上級者">上級者</option>
<option value="どなたでも">どなたでも</option>
</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>