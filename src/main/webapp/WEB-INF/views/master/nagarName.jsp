<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12">
	<form:form id="some-form" commandName="model" class="form-horizontal"
		action="${ postUrl }">

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Nagar Name</label>
			<div class="col-sm-3">
				<form:hidden path="rowId" />
				<form:input path="nagarName" class="col-sm-5 form-control" />
			</div>
		</div>

		<div class="col-md-offset-3 col-md-3">
			<button class="btn btn-sm btn-primary" type="submit">
				<i class="ace-icon fa fa-check bigger-110"></i> Save
			</button>
		</div>
	</form:form>
	<br /> <br /> <br />

	<c:choose>
		<c:when test="${ masterList.size() > 0 }">
			<table id="simple-table"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th><strong>Row Id</strong></th>
						<th><strong>Nagar Name</strong></th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ masterList }" var="item">
						<tr>
							<td>${ item.rowId }</td>
							<td>${ item.nagarName }</td>
							<td><a href="./edit${ postUrl }?id=${ item.rowId }">Edit</a></td>
							<td><a href="./delete${ postUrl }?id=${ item.rowId }">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<div style="text-align: center;">
				<h2>There is no data present for this master.</h2>
			</div>

		</c:otherwise>
	</c:choose>

</div>










