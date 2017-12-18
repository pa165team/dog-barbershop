<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"
         session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="Dogs">
<jsp:attribute name="body">
    <h1>Dog ${dog.name}</h1>
    <h3>Info about ${dog.name}</h3>
    <table class="table">
        <tbody>
        <tr>
            <th>Name</th>
            <td>${dog.name}</td>
        </tr>
        <tr>
            <th>Breed</th>
            <td>${dog.breed}</td>
        </tr>
        <tr>
            <th>Gender</th>
            <td>${dog.gender}</td>
        </tr>
        <tr>
            <th>Date of birth</th>
            <td>
                <fmt:formatDate type="date" value="${dog.dateOfBirth}"/>
            </td>
        </tr>
        <tr>
            <th>Discount for upcoming service</th>
            <td>${dog.hasDiscount ? "50% OFF" : "None"}</td>
        </tr>
        <tr>
            <th>Owner info</th>
            <td>
                <a href="/pa165/customers/detail/${dog.owner.id}">
                        ${dog.owner.name}&nbsp;${dog.owner.surname}
                </a>
            </td>
        </tr>
        </tbody>
    </table>
    <my:extraTag href="/dogs/all" class="btn btn-default">
        <span class="glyphicon glyphicon-hand-left" aria-hidden="true"></span>
        All Dogs
    </my:extraTag>

    <h3>Services provided for ${dog.name}:</h3>
    <my:extraTag href="/records/new/${dog.id}" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add New Service For ${dog.name}
    </my:extraTag>
    <table class="table">
        <thead>
        <tr>
            <th>Type Of Service</th>
            <th>Date Provided</th>
            <th>Actual Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${serviceRecords}" var="record">
            <tr onclick="window.location='/pa165/records/detail/${record.id}'"
                style="cursor: pointer;">
                <td><c:out value="${record.serviceType.name}"/></td>
                <td><fmt:formatDate type="date" value="${record.dateProvided}"/></td>
                <td><c:out value="${record.actualPrice}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
