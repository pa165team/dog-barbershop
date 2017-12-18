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
    <h1>Finance Statistics</h1>
    <h2>Turnover</h2>
    <p>Turnover for last month: <b>${turnover} CZK</b></p>
    <h2>Best Service Type</h2>
    <p>Most valuable service type in earnings up until now: <b>${bestServiceType.name}</b></p>
</jsp:attribute>
</my:pagetemplate>
