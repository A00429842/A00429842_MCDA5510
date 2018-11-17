/**
 * TrxnWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcda5510.service;

public interface TrxnWebService extends java.rmi.Remote {
    public void test() throws java.rmi.RemoteException;
    public void closeConnection() throws java.rmi.RemoteException;
    public void setDao(com.mcda5510.dao.MySQLAccess dao) throws java.rmi.RemoteException;
    public com.mcda5510.dao.MySQLAccess getDao() throws java.rmi.RemoteException;
    public boolean updateTransaction(com.mcda5510.entity.Transaction trxn) throws java.rmi.RemoteException;
    public boolean removeTransaction(int tid) throws java.rmi.RemoteException;
    public boolean createTransaction(com.mcda5510.entity.Transaction trxn) throws java.rmi.RemoteException;
    public com.mcda5510.entity.Transaction getTransaction(int tid) throws java.rmi.RemoteException;
}
