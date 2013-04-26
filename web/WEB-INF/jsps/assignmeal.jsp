<%@include file="../templates/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="navigation">
    <ul>
        <li><a href="<c:url value="assign.htm"/>">todays meals</a></li>
        <li><a class="current" href="<c:url value="assignmeal.htm"/>">assign meal</a></li>
        <li><a href="<c:url value="addfood.htm"/>">add food</a></li>
        <li><a href="<c:url value="foodlist.htm"/>">show foodlist</a></li>
        <li><a href="<c:url value="mealform.htm"/>">add meal</a></li>
        <li><a href="<c:url value="meallist.htm"/>">show meallist</a></li>
        <li><a href="<c:url value="logout.htm"/>">logout</a></li>
    </ul>
</div>
<div id="content">
    <h2>Assign meal form : </h2>
    <form:form method="post" action="/foodspring/assignMeal">
    <table border="0">
        <tr>
            <td>Meal Package* :</td>
            <td>
                <form:select path="meal_id">
                    <form:option value="0">Select Package</form:option>
                    <form:options items="${meals}" itemLabel="items" itemValue="id"/>
                </form:select>
                &nbsp;&nbsp;<form:errors path="meal_id" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Meal Type* :</td>
            <td>
                <form:select path="meal_time">
                    <form:option value="">Select Option</form:option>
                    <form:option value="Breakfast">Breakfast</form:option>
                    <form:option value="Lunch">Lunch</form:option>
                    <form:option value="Dinner">Dinner</form:option>
                    <form:option value="Other">Other</form:option>
                </form:select>
                &nbsp;&nbsp;<form:errors path="meal_time" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Meal Description :</td>
            <td><form:textarea rows="3" cols="50" path="description"></form:textarea></td>
        </tr>
    </table>
    <input type="submit" value="assign meal">
    </form:form>

<%@include file="../templates/footer.jsp" %>