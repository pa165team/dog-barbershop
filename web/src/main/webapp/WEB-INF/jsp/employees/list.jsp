<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"
         session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="Employees">
<jsp:attribute name="body">
    <h1>Employees</h1>
    <my:extraTag href="/employees/new" class="btn btn-default" style="margin-top: 10px;">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Register Employee
    </my:extraTag>
    <div style="margin-top: 10px;">
        <h3>Filter:</h3>
        <div class="btn-group">
            <my:extraTag href="/employees" class='btn btn-primary ${empty filter ? "disabled" : ""}'>
                All Employees
            </my:extraTag>
            <my:extraTag href="/employees/below"
                         class='btn btn-primary ${filter.equals("below") ? "disabled" : ""}'>
            Employees Below Salary
            </my:extraTag>
        </div>
    </div>
    
    <c:if test="${not empty filter}">
        <form:form method="post" style="margin-top: 5px;">
            <input type="number" name="filterValue" step="1000"/>
            <button type="submit" class="btn btn-primary">
                Set Salary Filter
            </button>
         </form:form>
    </c:if>
    
    
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
                    <c:out
                        value="${employee.address.street} ${employee.address.number}, ${employee.address.city}"/>
                </td>
                <td><c:out value="${employee.salary}"/></td>
                <td><c:out value="${employee.phoneNumber}"/></td>
                <td>
                    <my:extraTag href="/employees/edit/${employee.id}" class='btn btn-primary'>
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
