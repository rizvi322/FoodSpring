<%@include file="../templates/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="navigation">
    <ul>
        <%
            String username = (String) session.getAttribute("username");
            if (username == null || username == "") {
        %>
        <li><a class="current" href="<c:url value="assign.htm"/>">todays meals</a></li>
        <li><a href="<c:url value="login.htm"/>">login</a></li>
        <%
        } else {
        %>
        <li><a class="current" href="<c:url value="assign.htm"/>">todays meals</a></li>
        <li><a href="<c:url value="assignmeal.htm"/>">assign meal</a></li>
        <li><a href="<c:url value="addfood.htm"/>">add food</a></li>
        <li><a href="<c:url value="foodlist.htm"/>">show foodlist</a></li>
        <li><a href="<c:url value="mealform.htm"/>">add meal</a></li>
        <li><a href="<c:url value="meallist.htm"/>">show meallist</a></li>
        <li><a href="<c:url value="logout.htm"/>">logout</a></li>
        <%
            }
        %>
    </ul>
</div>
<div id="content">
    <h2>Meals for the day : </h2>
    <table width="60%" border="1">
        <tr>
            <th>Serial</th>
            <th>Meal Items</th>
            <th>Meal Type</th>
            <th>Meal Description</th>
            <c:if test="${username == 'admin'}">
                <th>Options</th>
            </c:if>
        </tr>
        <c:forEach var="meal" items="${meals}" varStatus="serial">
            <tr
                    <c:choose>
                        <c:when test="${serial.count % 2 == 0}">class="x"</c:when>
                        <c:otherwise>class="y"</c:otherwise>
                    </c:choose>>
                <td>${serial.count}</td>
                <td><c:out value="${meal.meal_items}"/></td>
                <td>${meal.meal_time}</td>
                <td>${meal.description}</td>
                <c:if test="${username == 'admin' }">
                    <td><a href="<c:url value="assign.htm"/>?type=remove_assign&id=${meal.id}">remove</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>

    <h2>Comments : </h2>
    <form:form method="post" action="/foodspring/addComment">
    <table border="0">
        <tr>
            <td>Your Name* :</td>
            <td>
                <form:input type="text" path="name"/>
                &nbsp;&nbsp;<form:errors path="name" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Your Comment* :</td>
            <td>
                <form:textarea rows="3" cols="50" path="comment"></form:textarea>
                &nbsp;&nbsp;<form:errors path="comment" cssClass="error"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="add comment"/>
    </form:form>
    <br/>

    <div>
        <c:forEach var="comment" items="${commentList}">
            <div class="comment">
                &nbsp;&nbsp;&nbsp;Name : ${comment.name}<br>
                &nbsp;&nbsp;&nbsp;Time : ${comment.time}<br>
                &nbsp;&nbsp;&nbsp;Comment : ${comment.comment}<br>
            </div>
        </c:forEach>
    </div>
    <%@include file="../templates/footer.jsp" %>
