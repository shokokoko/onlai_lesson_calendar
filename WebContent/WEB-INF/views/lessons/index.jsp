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
        <h2>登録レッスン　一覧</h2>
        <table id="lesson_list">
            <tbody>
                <tr>
                    <th class="lesson_title">タイトル</th>
                    <th class="lesson_target">タイトル</th>
                    <th class="lesson_action">詳細</th>
                </tr>
                <c:forEach var="lesson" items="${lesson}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="lesson_title">${lesson.title}</td>
                        <td class="lesson_target">${lesson.target}</td>
                        <td class="lesson_action"><a href="<c:url value='/lessons/show?id=${lesson.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${lessons_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((lessons_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/lessons/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/lessons/new' />">新規レッスンの登録</a></p>

    </c:param>
</c:import>