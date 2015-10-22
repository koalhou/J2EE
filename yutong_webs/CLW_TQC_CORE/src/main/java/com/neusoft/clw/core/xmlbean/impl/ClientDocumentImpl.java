/*
 * An XML document type.
 * Localname: client
 * Namespace: 
 * Java type: com.neusoft.clw.core.xmlbean.ClientDocument
 *
 * Automatically generated - do not modify.
 */
package com.neusoft.clw.core.xmlbean.impl;
/**
 * A document containing one client(@) element.
 *
 * This is a complex type.
 */
public class ClientDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.neusoft.clw.core.xmlbean.ClientDocument
{
    private static final long serialVersionUID = 1L;
    
    public ClientDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CLIENT$0 = 
        new javax.xml.namespace.QName("", "client");
    
    
    /**
     * Gets the "client" element
     */
    public com.neusoft.clw.core.xmlbean.ClientDocument.Client getClient()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.neusoft.clw.core.xmlbean.ClientDocument.Client target = null;
            target = (com.neusoft.clw.core.xmlbean.ClientDocument.Client)get_store().find_element_user(CLIENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "client" element
     */
    public void setClient(com.neusoft.clw.core.xmlbean.ClientDocument.Client client)
    {
        generatedSetterHelperImpl(client, CLIENT$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "client" element
     */
    public com.neusoft.clw.core.xmlbean.ClientDocument.Client addNewClient()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.neusoft.clw.core.xmlbean.ClientDocument.Client target = null;
            target = (com.neusoft.clw.core.xmlbean.ClientDocument.Client)get_store().add_element_user(CLIENT$0);
            return target;
        }
    }
    /**
     * An XML client(@).
     *
     * This is a complex type.
     */
    public static class ClientImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.neusoft.clw.core.xmlbean.ClientDocument.Client
    {
        private static final long serialVersionUID = 1L;
        
        public ClientImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName ISAUTH$0 = 
            new javax.xml.namespace.QName("", "isAuth");
        private static final javax.xml.namespace.QName SESSIONID$2 = 
            new javax.xml.namespace.QName("", "sessionid");
        private static final javax.xml.namespace.QName OPERATORID$4 = 
            new javax.xml.namespace.QName("", "operatorid");
        private static final javax.xml.namespace.QName CORPID$6 = 
            new javax.xml.namespace.QName("", "corpid");
        private static final javax.xml.namespace.QName AUTHCODE$8 = 
            new javax.xml.namespace.QName("", "authcode");
        
        
        /**
         * Gets the "isAuth" attribute
         */
        public java.lang.String getIsAuth()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ISAUTH$0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "isAuth" attribute
         */
        public org.apache.xmlbeans.XmlString xgetIsAuth()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ISAUTH$0);
                return target;
            }
        }
        
        /**
         * True if has "isAuth" attribute
         */
        public boolean isSetIsAuth()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(ISAUTH$0) != null;
            }
        }
        
        /**
         * Sets the "isAuth" attribute
         */
        public void setIsAuth(java.lang.String isAuth)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ISAUTH$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ISAUTH$0);
                }
                target.setStringValue(isAuth);
            }
        }
        
        /**
         * Sets (as xml) the "isAuth" attribute
         */
        public void xsetIsAuth(org.apache.xmlbeans.XmlString isAuth)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ISAUTH$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(ISAUTH$0);
                }
                target.set(isAuth);
            }
        }
        
        /**
         * Unsets the "isAuth" attribute
         */
        public void unsetIsAuth()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(ISAUTH$0);
            }
        }
        
        /**
         * Gets the "sessionid" attribute
         */
        public java.lang.String getSessionid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SESSIONID$2);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "sessionid" attribute
         */
        public org.apache.xmlbeans.XmlString xgetSessionid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SESSIONID$2);
                return target;
            }
        }
        
        /**
         * True if has "sessionid" attribute
         */
        public boolean isSetSessionid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(SESSIONID$2) != null;
            }
        }
        
        /**
         * Sets the "sessionid" attribute
         */
        public void setSessionid(java.lang.String sessionid)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SESSIONID$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SESSIONID$2);
                }
                target.setStringValue(sessionid);
            }
        }
        
        /**
         * Sets (as xml) the "sessionid" attribute
         */
        public void xsetSessionid(org.apache.xmlbeans.XmlString sessionid)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SESSIONID$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(SESSIONID$2);
                }
                target.set(sessionid);
            }
        }
        
        /**
         * Unsets the "sessionid" attribute
         */
        public void unsetSessionid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(SESSIONID$2);
            }
        }
        
        /**
         * Gets the "operatorid" attribute
         */
        public java.lang.String getOperatorid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(OPERATORID$4);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "operatorid" attribute
         */
        public org.apache.xmlbeans.XmlString xgetOperatorid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(OPERATORID$4);
                return target;
            }
        }
        
        /**
         * True if has "operatorid" attribute
         */
        public boolean isSetOperatorid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(OPERATORID$4) != null;
            }
        }
        
        /**
         * Sets the "operatorid" attribute
         */
        public void setOperatorid(java.lang.String operatorid)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(OPERATORID$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(OPERATORID$4);
                }
                target.setStringValue(operatorid);
            }
        }
        
        /**
         * Sets (as xml) the "operatorid" attribute
         */
        public void xsetOperatorid(org.apache.xmlbeans.XmlString operatorid)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(OPERATORID$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(OPERATORID$4);
                }
                target.set(operatorid);
            }
        }
        
        /**
         * Unsets the "operatorid" attribute
         */
        public void unsetOperatorid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(OPERATORID$4);
            }
        }
        
        /**
         * Gets the "corpid" attribute
         */
        public java.lang.String getCorpid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CORPID$6);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "corpid" attribute
         */
        public org.apache.xmlbeans.XmlString xgetCorpid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(CORPID$6);
                return target;
            }
        }
        
        /**
         * True if has "corpid" attribute
         */
        public boolean isSetCorpid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(CORPID$6) != null;
            }
        }
        
        /**
         * Sets the "corpid" attribute
         */
        public void setCorpid(java.lang.String corpid)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CORPID$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(CORPID$6);
                }
                target.setStringValue(corpid);
            }
        }
        
        /**
         * Sets (as xml) the "corpid" attribute
         */
        public void xsetCorpid(org.apache.xmlbeans.XmlString corpid)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(CORPID$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(CORPID$6);
                }
                target.set(corpid);
            }
        }
        
        /**
         * Unsets the "corpid" attribute
         */
        public void unsetCorpid()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(CORPID$6);
            }
        }
        
        /**
         * Gets the "authcode" attribute
         */
        public java.lang.String getAuthcode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(AUTHCODE$8);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "authcode" attribute
         */
        public org.apache.xmlbeans.XmlString xgetAuthcode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(AUTHCODE$8);
                return target;
            }
        }
        
        /**
         * True if has "authcode" attribute
         */
        public boolean isSetAuthcode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(AUTHCODE$8) != null;
            }
        }
        
        /**
         * Sets the "authcode" attribute
         */
        public void setAuthcode(java.lang.String authcode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(AUTHCODE$8);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(AUTHCODE$8);
                }
                target.setStringValue(authcode);
            }
        }
        
        /**
         * Sets (as xml) the "authcode" attribute
         */
        public void xsetAuthcode(org.apache.xmlbeans.XmlString authcode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(AUTHCODE$8);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(AUTHCODE$8);
                }
                target.set(authcode);
            }
        }
        
        /**
         * Unsets the "authcode" attribute
         */
        public void unsetAuthcode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(AUTHCODE$8);
            }
        }
    }
}
