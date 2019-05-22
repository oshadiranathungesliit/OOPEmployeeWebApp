
package com.oop.service;

import com.oop.model.Supplier;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

public interface ISupplierService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ISupplierService.class.getName());


	/**
	 * Add supplier for supplier table
	 * @param supplier
	 */
	public void addSupplier(Supplier supplier);

	/**
	 * Get a particular Supplier
	 * 
	 * @param supplierID
	 * @return Supplier
	 */
	public Supplier getSupplierByID(String supplierID);
	
	/**
	 * Get all list of Supplier
	 * 
	 * @return ArrayList<Supplier>
	 */
	public ArrayList<Supplier> getSupplier();
	
	/**
	 * Update existing Supplier
	 * @param supplierID
	 * @param supplier
	 * 
	 * @return
	 */
	public Supplier updateSupplier(String supplierID, Supplier supplier);

	/**
	 * Remove existing Supplier
	 * 
	 * @param supplierID
	 */
	public void removeSupplier(String supplierID);

}
