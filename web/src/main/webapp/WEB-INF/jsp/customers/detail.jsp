<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"
         session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="Customers">
<jsp:attribute name="body">
    <h1>Customer ${customer.name} ${customer.surname}</h1>
    <h3>Info about ${customer.name} ${customer.surname}</h3>
    <table class="table">
        <tbody>
        <tr>
            <th>Name</th>
            <td>${customer.name}</td>
        </tr>

        <tr>
            <th>Surname</th>
            <td>${customer.surname}</td>
        </tr>
        <tr>
            <th>Address</th>
            <td>${customer.address.street} ${customer.address.number}, ${customer.address.city}</td>
        </tr>
        <tr>
            <th>Phone number</th>
            <td>${customer.phoneNumber}</td>
        </tr>
        </tbody>
    </table>
    <my:extraTag href="/customers" class="btn btn-default">
        <span class="glyphicon glyphicon-hand-left" aria-hidden="true"></span>
        All Customers
    </my:extraTag>

    <h3>Dogs owned by ${customer.name} ${customer.surname}:</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Breed</th>
            <th>Date Of Birth</th>
            <th>Gender</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dogs}" var="dog">
            <tr onclick="window.location='/pa165/dogs/detail/${dog.id}'" style="cursor: pointer;">
                <td><c:out value="${dog.name}"/></td>
                <td><c:out value="${dog.breed}"/></td>
                <td><fmt:formatDate type="date" value="${dog.dateOfBirth}"/></td>
                <td><c:out value="${dog.gender}"/></td>
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
