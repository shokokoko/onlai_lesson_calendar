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
<label for="code">ユーザー名　ログイン時に使用します(6桁以上の半角英数字)</label><br />
<input type="text" name="code" value="${instructor.code}" />
<br /><br />

<label for="name">氏名　本名フルネームを入力してください※公開されません。</label><br />
<input type="text" name="name" value="${instructor.name}" />
<br /><br />

<label for="tname">ティーチャー名</label><br />
<input type="text" name="tname" value="${instructor.tname}" />
<br /><br />

<label for="password1">パスワード　ログイン時に使用します(8桁以上の半角英数字混在)</label><br />
<input type="password" name="password1" />
<br /><br />

<label for="password2">確認パスワード</label><br />
<input type="password" name="password2" />
<br /><br />

<label for="mailAdress">メールアドレス　※公開されません。</label><br />
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