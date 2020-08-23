<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${lesson != null}">
                <h2>レッスン　編集ページ</h2>
                <br /><br />

                <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
                    <div>
                        <input type="file" name="file">
                    </div>
                    <div>
                        <input type="submit" value="アップロード">
                    </div>
                </form>
                <br />

                <form method="POST" action="<c:url value='/lessons/update' />">
                    <c:import url="_form.jsp" />
                </form>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/lessons/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>