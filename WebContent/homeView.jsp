<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Employee.css" />

<meta charset="UTF-8">
<title>Inventory Management</title>
</head>
<body class="body">

	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<h2 class="h2" align="center">Add Supplier Page</h2>

	  
	<br>
	<br>

	<form method="POST" action="AddSupplierServlet" >
		<table align="center">

			<tr>
				<td>Supplier Name</td>
				<td><input type="text" name="supplierName" /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>Supplier Designation</td>
				<td><input type="text" name="designation" /></td>
			</tr>
			<tr>
				<td>Stock Name</td>
				<td><input type="text" name="StockName" /></td>
			</tr>
			<tr>
				<td>Department</td>
				<td><input type="text" name="department" /></td>
			</tr>
			<tr>
				<td>Stock Keeper</td>
				<td><input type="text" name="StockKeeper" /></td>
			</tr>
			<tr>
				<td>Origin</td>
				<td><input type="radio" name="origin" value="local"
					checked="checked" tabindex="1" /> Local</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="origin" value="foreign"
					tabindex="2" /> Foreign</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Supplier" class="add-button" /> </td>
			</tr>
			<tr>	
				<td colspan="2"><input type="reset" value="Reset" class="reset-button" /></td>
			</tr>
		</table>
	</form>

	<form method="POST" action="ListSupplierServlet">
		<table align="center">
			<tr>
				<td colspan="2"><input type="submit" value="List All Supplier" class="list-button" />
				</td>
			</tr>
		</table>
	</form>

	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

</body>
</html>