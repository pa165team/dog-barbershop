<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate title="Dogs">
<jsp:attribute name="body">
    <h1>Dog ${dog.name}</h1>
    <h3>Info about ${dog.name}</h3>
    <div>
        <span>Name: ${dog.name}</span><br/>
        <span>Breed: ${dog.breed}</span><br/>
        <span>Gender: ${dog.gender}</span><br/>
        <span>Date of Birth: ${dog.dateOfBirth.toLocalDate()}</span><br/>
        <span>Discount for upcoming service: ${dog.hasDiscount ? "50% OFF" : "None"}</span><br/>
        <span>
            Owner Info:
            <my:extraTag href="/customers/detail/${dog.owner.id}" class='btn btn-link'>
                ${dog.owner.name}&nbsp;${dog.owner.surname}
            </my:extraTag>
        </span><br/>
    </div>
    <my:extraTag href="/dogs/all" class="btn btn-default">
        <span class="glyphicon glyphicon-hand-left" aria-hidden="true"></span>
        Close Details
    </my:extraTag>

    <h3>Services provided for ${dog.name}:</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Type Of Service</th>
            <th>Date Provided</th>
            <th>Actual Price</th>
            <th>
                <my:extraTag href="/records/create" class="btn btn-primary">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        Order New Service For ${dog.name}
                </my:extraTag>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${serviceRecords}" var="record">
            <tr onclick="window.location='/pa165/records/detail/${record.id}'" style="cursor: pointer;">
                <td><c:out value="${record.serviceType.name}"/></td>
                <td><c:out value="${record.dateProvided}"/></td>
                <td><c:out value="${record.actualPrice}"/></td>
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
