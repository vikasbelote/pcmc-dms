<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-4">
	<form:form id="some-form" commandName="${MODEL_NAME}"
		class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">ITI Name</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="itiName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">Sub ITI Name</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="subItiName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">Nasti Name</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="nastiName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">Nasti Number</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="nastiNumber" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-3">
				<a href="./${SEARCH_URL}" class="btn btn-sm btn-primary" type="button">
					<i class="ace-icon fa fa-check bigger-110"></i> Back
				</a>

			</div>
		</div>
	</form:form>
</div>

<div class="col-sm-8">
	<c:choose>
		<c:when test="${ sessionScope.print || sessionScope.download }">
			<embed src="./static/images/${ itiModel.getImagePath() }.pdf"
				width="800px" height="600px" style="border: solid 2px black" />
		</c:when>
		<c:otherwise>
			<embed
				src="./static/images/${ itiModel.getImagePath() }.pdf#toolbar=0"
				width="800px" height="600px" style="border: solid 2px black" />
		</c:otherwise>
	</c:choose>
</div>