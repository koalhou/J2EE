/**
 * DT_SernrInfo_ResponseBus_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.neusoft.clw.yw.webservice;

public class DT_SernrInfo_ResponseBus_Response  implements java.io.Serializable {
    /* 返回状态 */
    private java.lang.String return_Status;

    /* 返回值 */
    private java.lang.String return_Value;

    private com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record[] return_Sernr_Record;

    public DT_SernrInfo_ResponseBus_Response() {
    }

    public DT_SernrInfo_ResponseBus_Response(
           java.lang.String return_Status,
           java.lang.String return_Value,
           com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record[] return_Sernr_Record) {
           this.return_Status = return_Status;
           this.return_Value = return_Value;
           this.return_Sernr_Record = return_Sernr_Record;
    }


    /**
     * Gets the return_Status value for this DT_SernrInfo_ResponseBus_Response.
     * 
     * @return return_Status   * 返回状态
     */
    public java.lang.String getReturn_Status() {
        return return_Status;
    }


    /**
     * Sets the return_Status value for this DT_SernrInfo_ResponseBus_Response.
     * 
     * @param return_Status   * 返回状态
     */
    public void setReturn_Status(java.lang.String return_Status) {
        this.return_Status = return_Status;
    }


    /**
     * Gets the return_Value value for this DT_SernrInfo_ResponseBus_Response.
     * 
     * @return return_Value   * 返回值
     */
    public java.lang.String getReturn_Value() {
        return return_Value;
    }


    /**
     * Sets the return_Value value for this DT_SernrInfo_ResponseBus_Response.
     * 
     * @param return_Value   * 返回值
     */
    public void setReturn_Value(java.lang.String return_Value) {
        this.return_Value = return_Value;
    }


    /**
     * Gets the return_Sernr_Record value for this DT_SernrInfo_ResponseBus_Response.
     * 
     * @return return_Sernr_Record
     */
    public com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record[] getReturn_Sernr_Record() {
        return return_Sernr_Record;
    }


    /**
     * Sets the return_Sernr_Record value for this DT_SernrInfo_ResponseBus_Response.
     * 
     * @param return_Sernr_Record
     */
    public void setReturn_Sernr_Record(com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record[] return_Sernr_Record) {
        this.return_Sernr_Record = return_Sernr_Record;
    }

    public com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record getReturn_Sernr_Record(int i) {
        return this.return_Sernr_Record[i];
    }

    public void setReturn_Sernr_Record(int i, com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record _value) {
        this.return_Sernr_Record[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SernrInfo_ResponseBus_Response)) return false;
        DT_SernrInfo_ResponseBus_Response other = (DT_SernrInfo_ResponseBus_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.return_Status==null && other.getReturn_Status()==null) || 
             (this.return_Status!=null &&
              this.return_Status.equals(other.getReturn_Status()))) &&
            ((this.return_Value==null && other.getReturn_Value()==null) || 
             (this.return_Value!=null &&
              this.return_Value.equals(other.getReturn_Value()))) &&
            ((this.return_Sernr_Record==null && other.getReturn_Sernr_Record()==null) || 
             (this.return_Sernr_Record!=null &&
              java.util.Arrays.equals(this.return_Sernr_Record, other.getReturn_Sernr_Record())));
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
        if (getReturn_Status() != null) {
            _hashCode += getReturn_Status().hashCode();
        }
        if (getReturn_Value() != null) {
            _hashCode += getReturn_Value().hashCode();
        }
        if (getReturn_Sernr_Record() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReturn_Sernr_Record());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReturn_Sernr_Record(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SernrInfo_ResponseBus_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", ">DT_SernrInfo_Response>Bus_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("return_Status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Return_Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("return_Value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Return_Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("return_Sernr_Record");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Return_Sernr_Record"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", ">>DT_SernrInfo_Response>Bus_Response>Return_Sernr_Record"));
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
