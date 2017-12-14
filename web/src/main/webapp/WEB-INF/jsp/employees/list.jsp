<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="Employees">
<jsp:attribute name="body">
    <h1>Employees</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Address</th>
            <th>Salary</th>
            <th>Phone Number</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td><c:out value="${employee.name}"/></td>
                <td><c:out value="${employee.surname}"/></td>
                <td>
                    <c:out value="${employee.address.street} ${employee.address.number}, ${employee.address.city}"/>
                </td>
                <td><c:out value="${employee.salary}"/></td>
                <td><c:out value="${employee.phoneNumber}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <my:extraTag href="/employees/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Register Employee
    </my:extraTag>
</jsp:attribute>
</my:pagetemplate>