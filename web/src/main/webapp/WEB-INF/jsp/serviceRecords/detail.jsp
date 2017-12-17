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
    <h1>Service record detail</h1>

    <table class="table">
        <tbody>
        <tr>
            <th>Owner</th>
            <td>${record.dog.owner.name}&nbsp;${record.dog.owner.surname}</td>
        </tr>
        <tr>
            <th>Dog</th>
            <td>${record.dog.name}, ${record.dog.breed}</td>
        </tr>
        <tr>
            <th>Service</th>
            <td>${record.serviceType.name}</td>
        </tr>
        <tr>
            <th>Employee</th>
            <td>${record.employee.name}&nbsp;${record.employee.surname}</td>
        </tr>
        <tr>
            <th>Price</th>
            <td>${record.actualPrice}</td>
        </tr>
        <tr>
            <th>Provided</th>
            <td>${record.dateProvided}</td>
        </tr>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
