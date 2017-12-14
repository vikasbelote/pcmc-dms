<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12">
	<form:form id="some-form" commandName="${MODEL_NAME}"
		class="form-horizontal" action="${SEARCH_URL}">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Nivada Number</label>
			<div class="col-sm-3">
				<form:input path="nivadaNumber" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Work Name</label>
			<div class="col-sm-3">
				<form:input path="workName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Tendor Name</label>
			<div class="col-sm-3">
				<form:input path="tendorName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Budget Tartude number</label>
			<div class="col-sm-3">
				<form:input path="budgetTartudeNumber" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="col-md-offset-3 col-md-3">
			<button class="btn btn-sm btn-primary" type="submit">
				<i class="ace-icon fa fa-check bigger-110"></i> Search
			</button>
			<button class="btn btn-sm" type="reset">
				<i class="ace-icon fa fa-undo bigger-110"></i> Reset
			</button>
		</div>
	</form:form>
	<br /> <br /> <br />
	<c:choose>
		<c:when test="${ showDeptTable }">
			<c:choose>
				<c:when test="${ searchList.size() > 0 }">
					<table id="simple-table"
						class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><strong>Row Number</strong></th>
								<th><strong>Nivada Number</strong></th>
								<th><strong>Work Name</strong></th>
								<th><strong>Tendor Name</strong></th>
								<th><strong>Budget Tartude number</strong></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ searchList }" var="item">
								<tr>
									<td>${ item[0]  }</td>
									<td>${ item[1]  }</td>
									<td>${ item[2] }</td>
									<td>${ item[3]  }</td>
									<td>${ item[4]  }</td>
									<td><a href="./${VIEW_URL}?id=${ item[0] }">View</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<div style="text-align: center;">
						<h2>There is no data present for this search criteria.</h2>
					</div>
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose>
</div>








