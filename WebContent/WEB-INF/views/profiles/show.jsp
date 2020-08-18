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
                <h2>プロフィール　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ティーチャー名</th>
                            <td><c:out value="${profile.instructor.name}" /></td>
                        </tr>
                        <tr>
                            <th>プロフィール紹介</th>
                            <td>
                                <pre><c:out value="${profile.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>主なプログラム</th>
                            <td>
                                <pre><c:out value="${profile.mainprogram}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>対応言語</th>
                            <td>
                                <pre><c:out value="${profile.language}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>保有資格</th>
                            <td>
                                <pre><c:out value="${profile.qualifications}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>SNSアカウント・ブログ等</th>
                            <td>
                                <pre><c:out value="${profile.snsblog}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${profile.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${profile.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_instructor.id == profile.instructor.id}">
                    <p><a href="<c:url value="/profiles/edit?id=${profile.id}" />">プロフィールを登録・編集する</a></p>
                </c:if>

    </c:param>
</c:import>