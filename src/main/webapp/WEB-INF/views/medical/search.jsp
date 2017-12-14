<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12">
	<form:form id="some-form" commandName="${MODEL_NAME}"
		class="form-horizontal" action="${SEARCH_URL}">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Head Office</label>
			<div class="col-sm-3">
				<form:input path="headOffice" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Period</label>
			<div class="col-sm-3">
				<form:input path="period" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">YCMH</label>
			<div class="col-sm-3">
				<form:select path="ycmh" cssClass="col-sm-5 form-control">
					<form:option value="">Choose YCMH</form:option>
					<form:options items="${ycmhMasterList}" itemLabel="ycmh" itemValue="ycmh" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Hospital</label>
			<div class="col-sm-3">
				<form:select path="hospital" cssClass="col-sm-5 form-control">
					<form:option value="">Choose Hospital</form:option>
					<form:options items="${hospitalMasterList}" itemLabel="hospitalName" itemValue="hospitalName" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Dispensaries</label>
			<div class="col-sm-3">
				<form:select path="dispensaries" cssClass="col-sm-5 form-control">
					<form:option value="">Choose Dispensaries</form:option>
					<form:options items="${dispensariesMasterList}" itemLabel="dispensaries" itemValue="dispensaries" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Table Number</label>
			<div class="col-sm-3">
				<form:input path="tableNumber" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">File Number</label>
			<div class="col-sm-3">
				<form:input path="fileNumber" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Keyward</label>
			<div class="col-sm-3">
				<form:input path="keyward" class="col-sm-5 form-control" />
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
								<th><strong>Head Office</strong></th>
								<th><strong>Period</strong></th>
								<th><strong>YCMH</strong></th>
								<th><strong>Hospital</strong></th>
								<th><strong>Dispensaries</strong></th>
								<th><strong>Table Number</strong></th>
								<th><strong>File Number</strong></th>
								<th><strong>Keyward</strong></th>
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
									<td>${ item[5] }</td>
									<td>${ item[6]  }</td>
									<td>${ item[7] }</td>
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










