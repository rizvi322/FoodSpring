<%@include file="../templates/header.jsp" %>

<div id="navigation">
    <ul>
        <li><a href="<c:url value="assign.htm"/>">todays meals</a></li>
        <li><a href="<c:url value="assignmeal.htm"/>">assign meal</a></li>
        <li><a href="<c:url value="addfood.htm"/>">add food</a></li>
        <li><a class="current" href="<c:url value="foodlist.htm"/>">show foodlist</a></li>
        <li><a href="<c:url value="mealform.htm"/>">add meal</a></li>
        <li><a href="<c:url value="meallist.htm"/>">show meallist</a></li>
        <li><a href="<c:url value="logout.htm"/>">logout</a></li>
    </ul>
</div>
<div id="content">

    <h3>List of available foods : </h3>
    <table width="60%" border="1">
        <tr>
            <th>Serial</th>
            <th>Food ID</th>
            <th>Food Name</th>
            <th>Food Description</th>
            <th>Options</th>
        </tr>
        <c:forEach var="food" items="${foods}" varStatus="serial">
            <tr
                    <c:choose>
                        <c:when test="${serial.count % 2 == 0}">class="x"</c:when>
                        <c:otherwise>class="y"</c:otherwise>
                    </c:choose>>
                <td>${serial.count}</td>
                <td>${food.id}</td>
                <td>${food.name}</td>
                <td>${food.description}</td>
                <td><a href="<c:url value="foodlist.htm"/>?type=remove_food&id=${food.id}">remove</a></td>
            </tr>
        </c:forEach>
    </table>

<%@include file="../templates/footer.jsp" %>