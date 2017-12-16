<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="Dogs">
<jsp:attribute name="body">
    <h1>Dogs</h1>
    <my:extraTag href="/dogs/new" class="btn btn-default">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add Dog
    </my:extraTag>
    <div style="margin-top: 10px;">
        Show:&nbsp;
        <my:extraTag href="/dogs/all" class='btn btn-primary ${filter.equalsIgnoreCase("all") ? "disabled" : ""}'>
            All Dogs
        </my:extraTag>
        <my:extraTag href="/dogs/males" class='btn btn-primary ${filter.equalsIgnoreCase("males") ? "disabled" : ""}'>
            Males Only
        </my:extraTag>
        <my:extraTag href="/dogs/females" class='btn btn-primary ${filter.equalsIgnoreCase("females") ? "disabled" : ""}'>
            Females Only
        </my:extraTag>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Breed</th>
            <th>Date Of Birth</th>
            <th>Gender</th>
            <th>Owner</th>
            <th><!--Buttons Column--></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allDogs}" var="dog">
            <tr onclick="window.location='/pa165/dogs/detail/${dog.id}'" style="cursor: pointer;">
                <td><c:out value="${dog.name}"/></td>
                <td><c:out value="${dog.breed}"/></td>
                <td>
                    <c:out value="${dog.dateOfBirth.toLocalDate()}"/>
                </td>
                <td><c:out value="${dog.gender}"/></td>
                <td><c:out value="${dog.owner.name} ${dog.owner.surname}"/></td>
                <td>
                    <my:extraTag href="/dogs/edit/${dog.id}" class='btn btn-primary'>
                        <span class="glyphicon glyphicon-edit">
                            Edit
                        </span>
                    </my:extraTag>
                    <my:extraTag href="/dogs/delete/${dog.id}" class='btn btn-danger'>
                         <span class="glyphicon glyphicon-remove">
                            Delete
                         </span>
                    </my:extraTag>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
