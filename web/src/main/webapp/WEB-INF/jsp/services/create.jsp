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
    <h1>Create new service type</h1>
    <form:form method="post" action="${pageContext.request.contextPath}/services/create"
               modelAttribute="serviceTypeCreate">
        <div class="form-group ${name_error ? 'has-error' : ''}">
            <form:label path="name">Name</form:label>
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name"/>
        </div>
        <div class="form-group ${description_error ? 'has-error' : ''}">
            <form:label path="description">Description</form:label>
            <form:textarea path="description" cssClass="form-control"/>
            <form:errors path="description"/>
        </div>
        <div class="form-group ${pricePerHour_error ? 'has-error' : ''}">
            <form:label path="pricePerHour">Price per hour</form:label>
            <form:input path="pricePerHour" cssClass="form-control"/>
            <form:errors path="pricePerHour"/>
        </div>
        <button class="btn btn-primary" type="submit">Create</button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>
