<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12">
	<table id="simple-table"
		class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th><strong>Report Name</strong></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${reportNameList}" var="reportName">
				<tr>
					<td>${reportName}</td>
					<%-- <td><a href="./viewReport?name=${reportName}">View</a></td> --%>
					<td><a href="./downloadReport?name=${reportName}">Download</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
