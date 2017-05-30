<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-4">
	<form:form id="some-form" commandName="propTaxModel"
		class="form-horizontal" action="propTax">
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2327;&#2366;&#2357;&#2366;&#2330;&#2375;
				&#2344;&#2366;&#2357;</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="villageName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2346;&#2381;&#2352;&#2377;&#2346;&#2352;&#2381;&#2335;&#2368;
				&#2325;&#2379;&#2337;</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="propertyCode" class="col-sm-5 form-control" />

			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2346;&#2381;&#2352;&#2377;&#2346;&#2352;&#2381;&#2335;&#2368;&#2330;&#2375;
				&#2350;&#2366;&#2354;&#2325; </label>
			<div class="col-sm-9" style="text-align: left;">
				<%-- <form:input path="propertyHolderName" class="col-sm-5 form-control" /> --%>
				<form:label path="propertyHolderName" class="col-sm-12">
					${ propTaxModel.propertyHolderName }
				</form:label>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">Accupant
				Name</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="accupantName" class="col-sm-5 form-control" />
			</div>
		</div> --%>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-3">
				<a href="./propTax" class="btn btn-sm btn-primary" type="button">
					<i class="ace-icon fa fa-check bigger-110"></i> Back
				</a>

			</div>
		</div>
	</form:form>
</div>

<div class="col-sm-8">
	<c:choose>
		<c:when test="${ sessionScope.print || sessionScope.download }">
			<embed src="./static/images/${ propTaxModel.getImagePath() }.pdf"
				width="800px" height="600px" style="border: solid 2px black" />
		</c:when>
		<c:otherwise>
			<embed
				src="./static/images/${ propTaxModel.getImagePath() }.pdf#toolbar=0"
				width="800px" height="600px" style="border: solid 2px black" />
		</c:otherwise>
	</c:choose>
</div>