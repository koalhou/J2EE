/**
 * DT_SernrInfo_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.neusoft.clw.yw.webservice;


/**
 * 车工号相关安节通数据返回信息
 */
public class DT_SernrInfo_Response  implements java.io.Serializable {
    private com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_Response bus_Response;

    public DT_SernrInfo_Response() {
    }

    public DT_SernrInfo_Response(
           com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_Response bus_Response) {
           this.bus_Response = bus_Response;
    }


    /**
     * Gets the bus_Response value for this DT_SernrInfo_Response.
     * 
     * @return bus_Response
     */
    public com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_Response getBus_Response() {
        return bus_Response;
    }


    /**
     * Sets the bus_Response value for this DT_SernrInfo_Response.
     * 
     * @param bus_Response
     */
    public void setBus_Response(com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_Response bus_Response) {
        this.bus_Response = bus_Response;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SernrInfo_Response)) return false;
        DT_SernrInfo_Response other = (DT_SernrInfo_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bus_Response==null && other.getBus_Response()==null) || 
             (this.bus_Response!=null &&
              this.bus_Response.equals(other.getBus_Response())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBus_Response() != null) {
            _hashCode += getBus_Response().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SernrInfo_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", "DT_SernrInfo_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bus_Response");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Bus_Response"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", ">DT_SernrInfo_Response>Bus_Response"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
