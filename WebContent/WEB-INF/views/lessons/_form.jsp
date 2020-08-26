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

<label for="thumbnail">メイン画像</label><br />
<input type="file" name="file" value="${lesson.thumbnail}">
<br /><br />


<label for="title">レッスンのタイトル</label><br />
<input type="text" name="title" value="${lesson.title}" />
<br /><br />

<label for="content">レッスンの内容</label><br />
<textarea name="content" rows="10" cols="50">${lesson.content}</textarea>
<br /><br />

<label for="required_time">所要時間</label><br />
<textarea name="required_time" rows="1" cols="50">${lesson.required_time}</textarea>
<br /><br />

<label for="application">使用アプリ</label><br />
<textarea name="application" rows="1" cols="50">${lesson.application}</textarea>
<br /><br />

<label for="charge">レッスン料金</label><br />
<textarea name="charge" rows="1" cols="50">${lesson.charge}</textarea>
<br /><br />

<label for="target">対象者</label><br />
<select name="target">
<option value="0">未選択</option>
<option value="1">はじめてこの先生のレッスンにご参加の方</option>
<option value="2">この先生のレッスンを受けたことがある方</option>
<option value="3">経験者全般</option>
<option value="4">初心者</option>
<option value="5">中級者</option>
<option value="6">上級者</option>
<option value="7">どなたでも</option>
</select>
<br /><br />

<label for="notes">その他・注意事項</label><br />
<textarea name="notes" rows="3" cols="50">${lesson.notes}</textarea>
<br /><br />

<label for="detail">予約先・詳細</label><br />
<textarea name="detail" rows="3" cols="50">${lesson.detail}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>