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

<label for="content">プロフィール紹介</label><br />
<textarea name="content" rows="10" cols="50">${profile.content}</textarea>
<br /><br />

<label for="mainprogram">主なプログラム</label><br />
<input type="text" name="mainprogram" value="${profile.mainprogram}" />
<br /><br />

<label for="language">対応言語</label><br />
<input type="text" name="language" value="${profile.language}" />
<br /><br />

<label for="qualifications">保有資格</label><br />
<textarea name="qualifications" rows="5" cols="50">${profile.qualifications}</textarea>
<br /><br />

<label for="snsblog">SNSアカウント・ブログ等</label><br />
<textarea name="snsblog" rows="5" cols="50">${profile.snsblog}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>