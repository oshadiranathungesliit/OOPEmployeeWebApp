
package com.oop.model;


public class Supplier {

	private String SupplierID;
	
	private String Name;

	private String Designation;

	private String StockName;

	private String Department;

	private String Address;
	
	private String StockKeeper ;
	
	private String Origin;

	private String JobRole;

	/**
	 * @return the SupplierID
	 */
	public String getSupplierID() {
		return SupplierID;
	}

	/**
	 * @param SupplierID the SupplierID to set
	 */
	public void setSupplierID(String supplierID) {
		SupplierID = supplierID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return Designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		Designation = designation;
	}

	/**
	 * @return the facultyName
	 */
	public String getStockName() {
		return StockName;
	}

	/**
	 * @param facultyName the facultyName to set
	 */
	public void setStockName(String stockName) {
		StockName = stockName;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return Department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		Department = department;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}

	/**
	 * @return the qualifications
	 */
	public String getStockKeeper() {
		return StockKeeper;
	}

	/**
	 * @param StockKeeper the StockKeeper to set
	 */
	public void setStockKeeper(String stockKeeper) {
		StockKeeper = stockKeeper;
	}

	/**
	 * @return the Origin
	 */
	public String getOrigin() {
		return Origin;
	}

	/**
	 * @param Origin the Origin to set
	 */
	public void setOrigin(String origin) {
		Origin = origin;
	}

	@Override
	public String toString() {
		
		return "Supplier ID = " + SupplierID + "\n" + "Supplier Name = " + Name + "\n" + "Address = " + Address + "\n"
				+ "Stock Name = " + StockName + "\n" + "Department = " + Department + "\n" + "Designation = "
				+ Designation + "\n" + "Stock Keeper = " + StockKeeper + "\n" + "Origin = " + Origin;
	}
}
