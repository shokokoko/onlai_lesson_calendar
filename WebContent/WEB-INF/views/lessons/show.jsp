<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${lesson != null}">
                <h2>レッスン　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ティーチャー名</th>
                            <td><c:out value="${lesson.instructor.tname}" /></td>
                        </tr>
                        <tr>
                            <th>レッスンのメイン画像</th>
                            <td><c:out value="${lesson.thumbnail}" /></td>
                        </tr>
                        <tr>
                            <th>タイトル</th>
                            <td>
                                <pre><c:out value="${lesson.title}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${lesson.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>所要時間</th>
                            <td>
                                <pre><c:out value="${lesson.required_time}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>使用アプリ</th>
                            <td>
                                <pre><c:out value="${lesson.application}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>レッスン料金</th>
                            <td>
                                <pre><c:out value="${lesson.charge}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>対象者</th>
                            <td>
                                <pre><c:out value="${lesson.target}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>その他・注意事項</th>
                            <td>
                                <pre><c:out value="${lesson.notes}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>予約先・詳細</th>
                            <td>
                                <pre><c:out value="${lesson.detail}" /></pre>
                            </td>
                        </tr>
                        <!--！！サムネがうまくいくまで未実装   <tr>
                            <th>レッスンのイメージ写真</th>
                            <td>
                                <pre><c:out value="${lesson.image}" /></pre>
                            </td>
                        </tr> ここまで！！ -->
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${lesson.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${lesson.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_instructor.id == lesson.instructor.id}">
                    <p><a href="<c:url value="/lessons/edit?id=${lesson.id}" />">このレッスンを編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/lessons/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>