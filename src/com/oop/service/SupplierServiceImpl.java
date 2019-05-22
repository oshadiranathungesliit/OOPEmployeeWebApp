
package com.oop.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.oop.model.Supplier;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;


public class SupplierServiceImpl implements ISupplierService {
	

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(SupplierServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createSupplierTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Suppliers table in the database and
	 * recreate table structure to insert supplier entries
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 */
	public static void createSupplierTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE));
			// Create new supplier table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of suppliers for as a batch from the selected supplier List
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * 
	 */
	@Override
	public void addSupplier(Supplier supplier) {

		String supplierID = CommonUtil.generateIDs(getSupplierIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in SupplierQuery.xml file and use
			 * insert_supplier key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_SUPPLIER));
			connection.setAutoCommit(false);
			
			//Generate supplier IDs
			supplier.setSupplierID(supplierID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, supplier.getSupplierID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, supplier.getName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, supplier.getDesignation());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, supplier.getStockName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, supplier.getDepartment());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, supplier.getAddress());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, supplier.getStockKeeper());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, supplier.getOrigin());
			// Add supplier
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * supplier details can be retrieved based on the provided supplier ID
	 * 
	 * @param supplierID
	 *            - Supplier details are filtered based on the ID
	 * 
	 * @see #actionOnSupplier()
	 */
	@Override
	public Supplier getSupplierByID(String supplierID) {

		return actionOnSupplier(supplierID).get(0);
	}
	
	/**
	 * Get all list of suppliers
	 * 
	 * @return ArrayList<Supplier> 
	 * 						- Array of supplier list will be return
	 * 
	 * @see #actionOnSupplier()
	 */
	@Override
	public ArrayList<Supplier> getSupplier() {
		
		return actionOnSupplier(null);
	}

	/**
	 * This method delete an supplier based on the provided ID
	 * 
	 * @param supplierID
	 *            - Delete supplier according to the filtered supplier details
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	@Override
	public void removeSupplier(String supplierID) {

		// Before deleting check whether supplier ID is available
		if (supplierID != null && !supplierID.isEmpty()) {
			/*
			 * Remove supplier query will be retrieved from SupplierQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_SUPPLIER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, supplierID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	/**
	 * This performs GET supplier by ID and Display all suppliers
	 * 
	 * @param supplierID
	 *            ID of the supplier to remove or select from the list

	 * @return ArrayList<Supplier> Array of supplier list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 * @see #getSupplier()
	 * @see #getSupplierByID(String)
	 */
	private ArrayList<Supplier> actionOnSupplier(String supplierID) {

		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching supplier it checks whether supplier ID is
			 * available
			 */
			if (supplierID != null && !supplierID.isEmpty()) {
				/*
				 * Get supplier by ID query will be retrieved from
				 * SupplierQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_SUPPLIER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, supplierID);
			}
			/*
			 * If supplier ID is not provided for get supplier option it display
			 * all supplier
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_SUPPLIER));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Supplier supplier = new Supplier();
				supplier.setSupplierID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				supplier.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				supplier.setAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				supplier.setStockName(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				supplier.setDepartment(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				supplier.setDesignation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				supplier.setStockKeeper(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				supplier.setOrigin(resultSet.getString(CommonConstants.COLUMN_INDEX_EIGHT));
				supplierList.add(supplier);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return supplierList;
	}

	/**
	 * Get the updated supplier
	 * 
	 * @param supplierID
	 *            ID of the supplier to remove or select from the list
	 * 
	 * @return return the Supplier object
	 * 
	 */
	@Override
	public Supplier updateSupplier(String supplierID, Supplier supplier) {

		/*
		 * Before fetching supplier it checks whether supplier ID is available
		 */
		if (supplierID != null && !supplierID.isEmpty()) {
			/*
			 * Update supplier query will be retrieved from SupplierQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_SUPPLIER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, supplier.getName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, supplier.getDesignation());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, supplier.getStockName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, supplier.getDepartment());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, supplier.getAddress());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, supplier.getStockKeeper());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, supplier.getOrigin());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, supplier.getSupplierID());
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated employee
		return getSupplierByID(supplierID);
	}
	
	/**
	 *
	 * @return ArrayList<String> Array of supplier id list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	private ArrayList<String> getSupplierIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of supplier IDs will be retrieved from EmployeeQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_SUPPLIER_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}
}
