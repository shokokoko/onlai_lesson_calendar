<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="code">ユーザー名</label><br />
<input type="text" name="code" value="${instructor.code}" />
<br /><br />

<label for="name">氏名</label><br />
<input type="text" name="name" value="${instructor.name}" />
<br /><br />

<label for="tname">ティーチャー名</label><br />
<input type="text" name="tname" value="${instructor.tname}" />
<br /><br />

<label for="password">パスワード</label><br />
<input type="password" name="password" />
<br /><br />

<label for="mailAdress">メールアドレス</label><br />
<input type="text" name="mailAdress" value="${instructor.mailAdress}" />
<br /><br />

<label for="officialHP">公式HPまたはSNSアカウント</label><br />
<input type="text" name="officialHP" value="${instructor.officialHP}" />
<br /><br />

<label for="admin_flag">権限</label><br />
<select name="admin_flag">
    <option value="0"<c:if test="${instructor.admin_flag == 0}"> selected</c:if>>インストラクター</option>
    <option value="1"<c:if test="${instructor.admin_flag == 1}"> selected</c:if>>管理者</option>
</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>