package com.mcda5510.service;

public class TrxnWebServiceProxy implements com.mcda5510.service.TrxnWebService {
  private String _endpoint = null;
  private com.mcda5510.service.TrxnWebService trxnWebService = null;
  
  public TrxnWebServiceProxy() {
    _initTrxnWebServiceProxy();
  }
  
  public TrxnWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initTrxnWebServiceProxy();
  }
  
  private void _initTrxnWebServiceProxy() {
    try {
      trxnWebService = (new com.mcda5510.service.TrxnWebServiceServiceLocator()).getTrxnWebService();
      if (trxnWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)trxnWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)trxnWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (trxnWebService != null)
      ((javax.xml.rpc.Stub)trxnWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.mcda5510.service.TrxnWebService getTrxnWebService() {
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService;
  }
  
  public void test() throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    trxnWebService.test();
  }
  
  public void closeConnection() throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    trxnWebService.closeConnection();
  }
  
  public void setDao(com.mcda5510.dao.MySQLAccess dao) throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    trxnWebService.setDao(dao);
  }
  
  public com.mcda5510.dao.MySQLAccess getDao() throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.getDao();
  }
  
  public boolean updateTransaction(com.mcda5510.entity.Transaction trxn) throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.updateTransaction(trxn);
  }
  
  public boolean removeTransaction(int tid) throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.removeTransaction(tid);
  }
  
  public boolean createTransaction(com.mcda5510.entity.Transaction trxn) throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.createTransaction(trxn);
  }
  
  public com.mcda5510.entity.Transaction getTransaction(int tid) throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.getTransaction(tid);
  }
  
  
}