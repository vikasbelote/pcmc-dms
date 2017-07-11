<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12">

	<c:if test="${not empty data}">
		<table style="border: 1px solid black; border-collapse: collapse;">
			<c:forEach items="${data}" var="row">
				<tr>
					<c:forEach items="${row.value}" var="cell">
						<td
							style="border:1px solid black;height:20px;width:100px;
                      background-color:${cell.bgColor};color:${cell.textColor};
                      font-weight:${cell.textWeight};font-size:${cell.textSize}pt;">
							${cell.content}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
