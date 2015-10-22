/**
 * PBS_KYPT_SI_SernrInfo_OutLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.neusoft.clw.yw.webservice;

public class PBS_KYPT_SI_SernrInfo_OutLocator extends org.apache.axis.client.Service implements com.neusoft.clw.yw.webservice.PBS_KYPT_SI_SernrInfo_Out {

    public PBS_KYPT_SI_SernrInfo_OutLocator() {
    }


    public PBS_KYPT_SI_SernrInfo_OutLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PBS_KYPT_SI_SernrInfo_OutLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HTTPS_Port
    private java.lang.String HTTPS_Port_address = "https://pi.yutong.com/XISOAPAdapter/MessageServlet?senderParty=&senderService=PBS_KYPT&receiverParty=&receiverService=&interface=SI_SernrInfo_Out&interfaceNamespace=http%3A%2F%2Fyutong.com%2Fesb%2Fkypt%2Fecc%2FSernrInfo";

    public java.lang.String getHTTPS_PortAddress() {
        return HTTPS_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HTTPS_PortWSDDServiceName = "HTTPS_Port";

    public java.lang.String getHTTPS_PortWSDDServiceName() {
        return HTTPS_PortWSDDServiceName;
    }

    public void setHTTPS_PortWSDDServiceName(java.lang.String name) {
        HTTPS_PortWSDDServiceName = name;
    }

    public com.neusoft.clw.yw.webservice.SI_SernrInfo_Out getHTTPS_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HTTPS_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHTTPS_Port(endpoint);
    }

    public com.neusoft.clw.yw.webservice.SI_SernrInfo_Out getHTTPS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.neusoft.clw.yw.webservice.SI_SernrInfo_OutBindingStub _stub = new com.neusoft.clw.yw.webservice.SI_SernrInfo_OutBindingStub(portAddress, this);
            _stub.setPortName(getHTTPS_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHTTPS_PortEndpointAddress(java.lang.String address) {
        HTTPS_Port_address = address;
    }


    // Use to get a proxy class for HTTP_Port
    private java.lang.String HTTP_Port_address = "http://pi.yutong.com:8100/XISOAPAdapter/MessageServlet?senderParty=&senderService=PBS_KYPT&receiverParty=&receiverService=&interface=SI_SernrInfo_Out&interfaceNamespace=http%3A%2F%2Fyutong.com%2Fesb%2Fkypt%2Fecc%2FSernrInfo&j_username=huazy&j_password=yt123456";

    public java.lang.String getHTTP_PortAddress() {
        return HTTP_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HTTP_PortWSDDServiceName = "HTTP_Port";

    public java.lang.String getHTTP_PortWSDDServiceName() {
        return HTTP_PortWSDDServiceName;
    }

    public void setHTTP_PortWSDDServiceName(java.lang.String name) {
        HTTP_PortWSDDServiceName = name;
    }

    public com.neusoft.clw.yw.webservice.SI_SernrInfo_Out getHTTP_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HTTP_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHTTP_Port(endpoint);
    }

    public com.neusoft.clw.yw.webservice.SI_SernrInfo_Out getHTTP_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.neusoft.clw.yw.webservice.SI_SernrInfo_OutBindingStub _stub = new com.neusoft.clw.yw.webservice.SI_SernrInfo_OutBindingStub(portAddress, this);
            _stub.setPortName(getHTTP_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHTTP_PortEndpointAddress(java.lang.String address) {
        HTTP_Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.neusoft.clw.yw.webservice.SI_SernrInfo_Out.class.isAssignableFrom(serviceEndpointInterface)) {
                com.neusoft.clw.yw.webservice.SI_SernrInfo_OutBindingStub _stub = new com.neusoft.clw.yw.webservice.SI_SernrInfo_OutBindingStub(new java.net.URL(HTTPS_Port_address), this);
                _stub.setPortName(getHTTPS_PortWSDDServiceName());
                return _stub;
            }
            if (com.neusoft.clw.yw.webservice.SI_SernrInfo_Out.class.isAssignableFrom(serviceEndpointInterface)) {
                com.neusoft.clw.yw.webservice.SI_SernrInfo_OutBindingStub _stub = new com.neusoft.clw.yw.webservice.SI_SernrInfo_OutBindingStub(new java.net.URL(HTTP_Port_address), this);
                _stub.setPortName(getHTTP_PortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("HTTPS_Port".equals(inputPortName)) {
            return getHTTPS_Port();
        }
        else if ("HTTP_Port".equals(inputPortName)) {
            return getHTTP_Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", "PBS_KYPT_SI_SernrInfo_Out");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", "HTTPS_Port"));
            ports.add(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", "HTTP_Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HTTPS_Port".equals(portName)) {
            setHTTPS_PortEndpointAddress(address);
        }
        else 
if ("HTTP_Port".equals(portName)) {
            setHTTP_PortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
