<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${instructor != null}">
                <h2>id : ${instructor.id} のインストラクター情報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ID</th>
                            <td><c:out value="${instructor.id}" /></td>
                        </tr>
                        <tr>
                            <th>ユーザー名</th>
                            <td><c:out value="${instructor.code}" /></td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${instructor.name}" /></td>
                        </tr>
                        <tr>
                            <th>ティーチャー名</th>
                            <td><c:out value="${instructor.tname}" /></td>
                        </tr>
                        <tr>
                            <th>お問い合わせ</th>
                            <td><c:out value="${instructor.mailAdress}" /></td>
                        </tr>
                        <tr>
                            <th>公式HPまたはSNSアカウント</th>
                            <td><c:out value="${instructor.officialHP}" /></td>
                        </tr>
                        <tr>
                            <th>インストラクター</th>
                            <td>
                                <c:choose>
                                    <c:when test="${employee.admin_flag == 1}">管理者</c:when>
                                    <c:otherwise>インストラクター</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${instructor.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${instructor.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/instructors/edit?id=${instructor.id}' />">このインストラクター情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/instructors/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>