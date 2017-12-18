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
    <h1>Services</h1>
    <a class="btn btn-default" href="/pa165/services/create">
        <span class="glyphicon glyphicon-plus"
              aria-hidden="true"></span>
        Add New Service
    </a>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price per hour</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${services}" var="service">
            <tr>
                <td><c:out value="${service.name}"/></td>
                <td><c:out value="${service.description}"/></td>
                <td><c:out value="${service.pricePerHour}"/></td>
                <td>
                    <my:extraTag href="/services/edit/${service.id}" class='btn btn-primary'>
                        <span class="glyphicon glyphicon-edit">
                        </span> Edit
                    </my:extraTag>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
