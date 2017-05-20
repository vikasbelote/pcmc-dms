<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12">
	<form:form id="some-form" commandName="propTaxModel"
		class="form-horizontal" action="propTax">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2327;&#2366;&#2357;&#2366;&#2330;&#2375;
				&#2344;&#2366;&#2357;</label>
			<div class="col-sm-3">
				<form:select path="villageName" class="col-sm-5 form-control">
					<form:option value="" label="Choose Record Type" />
					<form:options items="${villageList}" itemLabel="villageName"
						itemValue="villageName" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2346;&#2381;&#2352;&#2377;&#2346;&#2352;&#2381;&#2335;&#2368;
				&#2325;&#2379;&#2337;</label>
			<div class="col-sm-3">
				<form:input path="propertyCode" class="col-sm-5 form-control" />
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2346;&#2381;&#2352;&#2377;&#2346;&#2352;&#2381;&#2335;&#2368;&#2330;&#2375;
				&#2350;&#2366;&#2354;&#2325; </label>
			<div class="col-sm-3">
				<form:input path="propertyHolderName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Accupant Name</label>
			<div class="col-sm-3">
				<form:input path="accupantName" class="col-sm-5 form-control" />
			</div>
		</div> --%>
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
								<th><strong> &#2344;&#2306;&#2348;&#2352;</strong></th>
								<th><strong>&#2327;&#2366;&#2357;&#2366;&#2330;&#2375;
										&#2344;&#2366;&#2357;</strong></th>
								<th><strong>&#2346;&#2381;&#2352;&#2377;&#2346;&#2352;&#2381;&#2335;&#2368;
										&#2325;&#2379;&#2337;</strong></th>
								<th><strong>&#2346;&#2381;&#2352;&#2377;&#2346;&#2352;&#2381;&#2335;&#2368;&#2330;&#2375;
										&#2350;&#2366;&#2354;&#2325; </strong></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ searchList }" var="item">
								<tr>
									<td>${ item[0]  }</td>
									<td>${ item[1]  }</td>
									<td>${ item[2] }</td>
									<td>${ item[3] }</td>
									<td><a href="./viewPropTax?id=${ item[0] }">View</a></td>
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










