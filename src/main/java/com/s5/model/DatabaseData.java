package com.s5.model;


public class DatabaseData {

	private String databaseIP;
	private String databasePort;
	private String databaseName;
	private String databaseSchema;
	private String databaseUserName;
	private String databasePassword;
	private String dataSourceDriver;
	private int timeout;
	
	public String getDatabaseIP() {
		return databaseIP;
	}
	public void setDatabaseIP(String databaseIP) {
		this.databaseIP = databaseIP;
	}
	public String getDatabasePort() {
		return databasePort;
	}
	public void setDatabasePort(String databasePort) {
		this.databasePort = databasePort;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getDatabaseSchema() {
		return databaseSchema;
	}
	public void setDatabaseSchema(String databaseSchema) {
		this.databaseSchema = databaseSchema;
	}
	public String getDatabaseUserName() {
		return databaseUserName;
	}
	public void setDatabaseUserName(String databaseUserName) {
		this.databaseUserName = databaseUserName;
	}
	public String getDatabasePassword() {
		return databasePassword;
	}
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	public String getDataSourceDriver() {
		return dataSourceDriver;
	}
	public void setDataSourceDriver(String dataSourceDriver) {
		this.dataSourceDriver = dataSourceDriver;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
