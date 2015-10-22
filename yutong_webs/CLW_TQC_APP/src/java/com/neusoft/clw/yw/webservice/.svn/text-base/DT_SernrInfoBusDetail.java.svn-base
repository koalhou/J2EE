/**
 * DT_SernrInfoBusDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.neusoft.clw.yw.webservice;

public class DT_SernrInfoBusDetail  implements java.io.Serializable {
    private java.lang.String SERNR;

    public DT_SernrInfoBusDetail() {
    }

    public DT_SernrInfoBusDetail(
           java.lang.String SERNR) {
           this.SERNR = SERNR;
    }


    /**
     * Gets the SERNR value for this DT_SernrInfoBusDetail.
     * 
     * @return SERNR
     */
    public java.lang.String getSERNR() {
        return SERNR;
    }


    /**
     * Sets the SERNR value for this DT_SernrInfoBusDetail.
     * 
     * @param SERNR
     */
    public void setSERNR(java.lang.String SERNR) {
        this.SERNR = SERNR;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SernrInfoBusDetail)) return false;
        DT_SernrInfoBusDetail other = (DT_SernrInfoBusDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SERNR==null && other.getSERNR()==null) || 
             (this.SERNR!=null &&
              this.SERNR.equals(other.getSERNR())));
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
        if (getSERNR() != null) {
            _hashCode += getSERNR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SernrInfoBusDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", ">>DT_SernrInfo>Bus>Detail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SERNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
