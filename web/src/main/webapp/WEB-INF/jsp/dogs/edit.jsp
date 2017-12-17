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
    <h1>Update Dog Information</h1>
    <form:form method="post" action="${pageContext.request.contextPath}/dogs/edit/${dogId}"
               modelAttribute="dogToUpdate">
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

        <button type="submit" class="btn btn-primary" style="margin-top: 10px;">
            <span class="glyphicon glyphicon-edit">
                Update
            </span>
        </button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>
