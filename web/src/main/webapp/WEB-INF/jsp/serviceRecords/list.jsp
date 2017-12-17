<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"
         session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate>
<jsp:attribute name="body">
    <h1>Service records</h1>
    <div class="btn-group">
        <a href="/pa165/records" class="btn btn-primary ${all ? '' : 'disabled'}">Last week</a>
        <a href="/pa165/records/all" class="btn btn-primary ${all ? 'disabled' : ''}">All</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>Owner</th>
            <th>Dog</th>
            <th>Price</th>
            <th>Provided</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record">
            <tr onclick="window.location='/pa165/records/detail/${record.id}'"
                style="cursor: pointer;">
                <td><c:out value="${record.dog.owner.surname}, ${record.dog.owner.name}"/></td>
                <td><c:out value="${record.dog.name}"/></td>
                <td><c:out value="${record.actualPrice}"/></td>
                <td><c:out value="${record.dateProvided}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
