/**
 * DT_SernrInfoBus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.neusoft.clw.yw.webservice;

public class DT_SernrInfoBus  implements java.io.Serializable {
    private java.lang.String requestSource;

    private java.lang.String requestTarget;

    private java.lang.String requestUser;

    private java.lang.String requestTime;

    private java.lang.String requestType;

    private com.neusoft.clw.yw.webservice.DT_SernrInfoBusDetail[] detail;

    public DT_SernrInfoBus() {
    }

    public DT_SernrInfoBus(
           java.lang.String requestSource,
           java.lang.String requestTarget,
           java.lang.String requestUser,
           java.lang.String requestTime,
           java.lang.String requestType,
           com.neusoft.clw.yw.webservice.DT_SernrInfoBusDetail[] detail) {
           this.requestSource = requestSource;
           this.requestTarget = requestTarget;
           this.requestUser = requestUser;
           this.requestTime = requestTime;
           this.requestType = requestType;
           this.detail = detail;
    }


    /**
     * Gets the requestSource value for this DT_SernrInfoBus.
     * 
     * @return requestSource
     */
    public java.lang.String getRequestSource() {
        return requestSource;
    }


    /**
     * Sets the requestSource value for this DT_SernrInfoBus.
     * 
     * @param requestSource
     */
    public void setRequestSource(java.lang.String requestSource) {
        this.requestSource = requestSource;
    }


    /**
     * Gets the requestTarget value for this DT_SernrInfoBus.
     * 
     * @return requestTarget
     */
    public java.lang.String getRequestTarget() {
        return requestTarget;
    }


    /**
     * Sets the requestTarget value for this DT_SernrInfoBus.
     * 
     * @param requestTarget
     */
    public void setRequestTarget(java.lang.String requestTarget) {
        this.requestTarget = requestTarget;
    }


    /**
     * Gets the requestUser value for this DT_SernrInfoBus.
     * 
     * @return requestUser
     */
    public java.lang.String getRequestUser() {
        return requestUser;
    }


    /**
     * Sets the requestUser value for this DT_SernrInfoBus.
     * 
     * @param requestUser
     */
    public void setRequestUser(java.lang.String requestUser) {
        this.requestUser = requestUser;
    }


    /**
     * Gets the requestTime value for this DT_SernrInfoBus.
     * 
     * @return requestTime
     */
    public java.lang.String getRequestTime() {
        return requestTime;
    }


    /**
     * Sets the requestTime value for this DT_SernrInfoBus.
     * 
     * @param requestTime
     */
    public void setRequestTime(java.lang.String requestTime) {
        this.requestTime = requestTime;
    }


    /**
     * Gets the requestType value for this DT_SernrInfoBus.
     * 
     * @return requestType
     */
    public java.lang.String getRequestType() {
        return requestType;
    }


    /**
     * Sets the requestType value for this DT_SernrInfoBus.
     * 
     * @param requestType
     */
    public void setRequestType(java.lang.String requestType) {
        this.requestType = requestType;
    }


    /**
     * Gets the detail value for this DT_SernrInfoBus.
     * 
     * @return detail
     */
    public com.neusoft.clw.yw.webservice.DT_SernrInfoBusDetail[] getDetail() {
        return detail;
    }


    /**
     * Sets the detail value for this DT_SernrInfoBus.
     * 
     * @param detail
     */
    public void setDetail(com.neusoft.clw.yw.webservice.DT_SernrInfoBusDetail[] detail) {
        this.detail = detail;
    }

    public com.neusoft.clw.yw.webservice.DT_SernrInfoBusDetail getDetail(int i) {
        return this.detail[i];
    }

    public void setDetail(int i, com.neusoft.clw.yw.webservice.DT_SernrInfoBusDetail _value) {
        this.detail[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SernrInfoBus)) return false;
        DT_SernrInfoBus other = (DT_SernrInfoBus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requestSource==null && other.getRequestSource()==null) || 
             (this.requestSource!=null &&
              this.requestSource.equals(other.getRequestSource()))) &&
            ((this.requestTarget==null && other.getRequestTarget()==null) || 
             (this.requestTarget!=null &&
              this.requestTarget.equals(other.getRequestTarget()))) &&
            ((this.requestUser==null && other.getRequestUser()==null) || 
             (this.requestUser!=null &&
              this.requestUser.equals(other.getRequestUser()))) &&
            ((this.requestTime==null && other.getRequestTime()==null) || 
             (this.requestTime!=null &&
              this.requestTime.equals(other.getRequestTime()))) &&
            ((this.requestType==null && other.getRequestType()==null) || 
             (this.requestType!=null &&
              this.requestType.equals(other.getRequestType()))) &&
            ((this.detail==null && other.getDetail()==null) || 
             (this.detail!=null &&
              java.util.Arrays.equals(this.detail, other.getDetail())));
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
        if (getRequestSource() != null) {
            _hashCode += getRequestSource().hashCode();
        }
        if (getRequestTarget() != null) {
            _hashCode += getRequestTarget().hashCode();
        }
        if (getRequestUser() != null) {
            _hashCode += getRequestUser().hashCode();
        }
        if (getRequestTime() != null) {
            _hashCode += getRequestTime().hashCode();
        }
        if (getRequestType() != null) {
            _hashCode += getRequestType().hashCode();
        }
        if (getDetail() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetail());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetail(), i);
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
        new org.apache.axis.description.TypeDesc(DT_SernrInfoBus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", ">DT_SernrInfo>Bus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestSource");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestTarget");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestTarget"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestUser");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Detail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://yutong.com/esb/kypt/ecc/SernrInfo", ">>DT_SernrInfo>Bus>Detail"));
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
