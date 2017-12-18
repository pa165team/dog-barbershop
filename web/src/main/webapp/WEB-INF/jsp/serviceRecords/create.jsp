<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="Make an order for ${dogName}:">
<jsp:attribute name="body">
    <h1>New service record for ${dogName}</h1>
    <form:form method="post" action="${pageContext.request.contextPath}/records/create/${dogId}"
               modelAttribute="recordCreate" cssClass="form-horizontal">
        <div class="form-group ${lengthMinutes_error?'has-error':''}">
            <form:label path="lengthMinutes" cssClass="col-sm-2 control-label">
                Length of Reservation in Minutes
            </form:label>
            <div class="col-sm-10">
                <form:input path="lengthMinutes" cssClass="form-control"/>
                <form:errors path="lengthMinutes" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${employeeId_error?'has-error':''}">
            <form:label path="employeeId" cssClass="col-sm-2 control-label">
                Employee to Execute This Service
            </form:label>
            <div class="col-sm-10">
                <form:select path="employeeId" cssClass="form-control">
                    <form:option value="NONE" label="--- Select Employee ---"/>
                    <c:forEach items="${allEmployees}" var="emp">
                        <form:option value="${emp.id}">${emp.name} ${emp.surname}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="employeeId" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${serviceTypeId_error?'has-error':''}">
            <form:label path="serviceTypeId" cssClass="col-sm-2 control-label">
                Type of Service
            </form:label>
            <div class="col-sm-10">
                <form:select path="serviceTypeId" cssClass="form-control">
                    <form:option value="NONE" label="--- Select Type ---"/>
                    <c:forEach items="${allServiceTypes}" var="st">
                        <form:option value="${st.id}">${st.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="serviceTypeId" cssClass="help-block"/>
            </div>
            
        </div>
        <button class="btn btn-primary" type="submit">Confirm Order</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
