<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>onlai レッスン カレンダー</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <h1><a href="<c:url value='/' />">onlai レッスン カレンダー</a></h1>&nbsp;&nbsp;&nbsp; <br />
                    <c:if test="${sessionScope.login_instructor != null}">
                        <c:if test="${sessionScope.login_instructor.admin_flag == 1}">
                            <a href="<c:url value='/instructors/index' />">インストラクター管理</a>&nbsp;
                        </c:if>
                        <a href="<c:url value='/lessons/index' />">レッスン管理</a>&nbsp;
                        <a href="<c:url value='/calendars/index' />">カレンダー管理</a>&nbsp;
                        <a href="<c:url value='/profiles/index' />">プロフィール管理</a>&nbsp;
                    </c:if>
                </div>
                <c:if test="${sessionScope.login_instructor != null}">
                    <div id="instructor_tname">
                        <c:out value="${sessionScope.login_instructor.tname}" />&nbsp;先生&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by onlai_lesson_calendar.
            </div>
        </div>
    </body>
</html>