<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="Create new dog">
<jsp:attribute name="body">
    <h1>Create new dog</h1>
    <form:form method="post" action="${pageContext.request.contextPath}/dogs/create"
               modelAttribute="dogCreate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${breed_error?'has-error':''}">
            <form:label path="breed" cssClass="col-sm-2 control-label">Breed</form:label>
            <div class="col-sm-10">
                <form:input path="breed" cssClass="form-control"/>
                <form:errors path="breed" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${dateOfBirth_error?'has-error':''}">
            <form:label path="dateOfBirth" cssClass="col-sm-2 control-label">Date Of Birth</form:label>
            <div class="col-sm-10">
                <form:input path="dateOfBirth" cssClass="form-control"/>
                <form:errors path="dateOfBirth" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${gender_error?'has-error':''}">
            <form:label path="gender" cssClass="col-sm-2 control-label">Gender</form:label>
            <div class="col-sm-10">
                <form:select path="gender" cssClass="form-control">
                    <form:option value="NONE" label="--- Select Gender ---"/>
                    <form:options items="${genders}"/>
                </form:select>
                <form:errors path="gender" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${ownerId_error?'has-error':''}">
            <form:label path="ownerId" cssClass="col-sm-2 control-label">Owner</form:label>
            <div class="col-sm-10">
                <form:select path="ownerId" cssClass="form-control">
                    <form:option value="NONE" label="--- Select Owner ---"/>
                    <c:forEach items="${allCustomers}" var="cust">
                        <form:option value="${cust.id}">${cust.name} ${cust.surname}, Phone#: ${cust.phoneNumber}</form:option>
                    </c:forEach>
                </form:select>
                        <form:errors path="ownerId" cssClass="help-block"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
