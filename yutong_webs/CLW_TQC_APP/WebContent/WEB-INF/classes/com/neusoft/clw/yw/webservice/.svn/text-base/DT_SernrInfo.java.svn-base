/**
 * DT_SernrInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.neusoft.clw.yw.webservice;


/**
 * 车工号相关安节通数据
 */
public class DT_SernrInfo  implements java.io.Serializable {
    private com.neusoft.clw.yw.webservice.DT_SernrInfoBus bus;

    public DT_SernrInfo() {
    }

    public DT_SernrInfo(
           com.neusoft.clw.yw.webservice.DT_SernrInfoBus bus) {
           this.bus = bus;
    }


    /**
     * Gets the bus value for this DT_SernrInfo.
     * 
     * @return bus
     */
    public com.neusoft.clw.yw.webservice.DT_SernrInfoBus getBus() {
        return bus;
    }


    /**
     * Sets the bus value for this DT_SernrInfo.
     * 
     * @param bus
     */
    public void setBus(com.neusoft.clw.yw.webservice.DT_SernrInfoBus bus) {
        this.bus = bus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SernrInfo)) return false;
        DT_SernrInfo other = (DT_SernrInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bus==null && other.getBus()==null) || 
             (this.bus!=null &&
              this.bus.equals(other.getBus())));
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
        if (getBus() != null) {
            _hashCode += getBus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SernrInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", "DT_SernrInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Bus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", ">DT_SernrInfo>Bus"));
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
