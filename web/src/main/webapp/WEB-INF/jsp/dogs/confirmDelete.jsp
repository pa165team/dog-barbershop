<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="Dogs">
<jsp:attribute name="body">
    <h1>Delete Dog</h1>
    <h2>Are you sure you want to delete ${dogToDelete.name} from the system?</h2>
    <my:extraTag href="/dogs/confirmedDelete/${dogToDelete.id}" class="btn btn-primary">
        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
        Yes, delete!
    </my:extraTag>
    <my:extraTag href="/dogs/all" class="btn btn-danger">
        <span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
        No, let him live!
    </my:extraTag>
</jsp:attribute>
</my:pagetemplate>
