<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>プロフィール　一覧</h2>
        <table id="profile_list">
            <tbody>
                <tr>
                    <th class="profile_tname">ティーチャー名</th>
                    <th class="profile_content">プロフィール紹介</th>
                    <th class="profile_mainprogram">主なプログラム</th>
                    <th class="profile_language">対応言語</th>
                    <th class="profile_qualifications">保有資格</th>
                    <th class="profile_snsblog">SNSアカウント・ブログ等</th>
                    <th class="profile_action">詳細</th>
                </tr>
                <c:forEach var="profile" items="${profiles}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="profile_tname"><c:out value="${profile.instructor.tname}" /></td>
                        <td class="profile_content">${profile.content}</td>
                        <td class="profile_mainprogram">${profile.mainprogram}</td>
                        <td class="profile_language">${profile.language}</td>
                        <td class="profile_qualifications">${profile.qualifications}</td>
                        <td class="profile_snsblog">${profile.snsblog}</td>
                        <td class="profile_action"><a href="<c:url value='/profiles/show?id=${profile.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${profiles_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((profiles_count - 1) / 10) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/profiles/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/profiles/new' />">プロフィールの登録・編集</a></p>

    </c:param>
</c:import>