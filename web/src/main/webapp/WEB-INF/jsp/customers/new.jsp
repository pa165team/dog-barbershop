<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="New customer">
<jsp:attribute name="body">
    <h1>Create new customer</h1>
    <form:form method="post" action="${pageContext.request.contextPath}/customers/create"
               modelAttribute="customerCreate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${surname_error?'has-error':''}">
            <form:label path="surname" cssClass="col-sm-2 control-label">Surname</form:label>
            <div class="col-sm-10">
                <form:input path="surname" cssClass="form-control"/>
                <form:errors path="surname" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${address_error?'has-error':''}">
            <form:label path="address" cssClass="col-sm-2 control-label">Address</form:label>
            <div class="col-sm-10">
                <form:input path="address" placeholder="Street 123, City" cssClass="form-control"/>
                <form:errors path="address" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${phoneNumber_error?'has-error':''}">
            <form:label path="phoneNumber" cssClass="col-sm-2 control-label">Phone Number</form:label>
            <div class="col-sm-10">
                <form:input path="phoneNumber" cssClass="form-control"/>
                <form:errors path="phoneNumber" cssClass="help-block"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
