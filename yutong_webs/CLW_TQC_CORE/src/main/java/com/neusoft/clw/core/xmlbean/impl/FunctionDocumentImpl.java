/*
 * An XML document type.
 * Localname: function
 * Namespace: 
 * Java type: com.neusoft.clw.core.xmlbean.FunctionDocument
 *
 * Automatically generated - do not modify.
 */
package com.neusoft.clw.core.xmlbean.impl;
/**
 * A document containing one function(@) element.
 *
 * This is a complex type.
 */
public class FunctionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.neusoft.clw.core.xmlbean.FunctionDocument
{
    private static final long serialVersionUID = 1L;
    
    public FunctionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FUNCTION$0 = 
        new javax.xml.namespace.QName("", "function");
    
    
    /**
     * Gets the "function" element
     */
    public com.neusoft.clw.core.xmlbean.FunctionDocument.Function getFunction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.neusoft.clw.core.xmlbean.FunctionDocument.Function target = null;
            target = (com.neusoft.clw.core.xmlbean.FunctionDocument.Function)get_store().find_element_user(FUNCTION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "function" element
     */
    public void setFunction(com.neusoft.clw.core.xmlbean.FunctionDocument.Function function)
    {
        generatedSetterHelperImpl(function, FUNCTION$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "function" element
     */
    public com.neusoft.clw.core.xmlbean.FunctionDocument.Function addNewFunction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.neusoft.clw.core.xmlbean.FunctionDocument.Function target = null;
            target = (com.neusoft.clw.core.xmlbean.FunctionDocument.Function)get_store().add_element_user(FUNCTION$0);
            return target;
        }
    }
    /**
     * An XML function(@).
     *
     * This is a complex type.
     */
    public static class FunctionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.neusoft.clw.core.xmlbean.FunctionDocument.Function
    {
        private static final long serialVersionUID = 1L;
        
        public FunctionImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName PARAM$0 = 
            new javax.xml.namespace.QName("", "param");
        private static final javax.xml.namespace.QName RESULT$2 = 
            new javax.xml.namespace.QName("", "result");
        private static final javax.xml.namespace.QName NAME$4 = 
            new javax.xml.namespace.QName("", "name");
        private static final javax.xml.namespace.QName SEQ$6 = 
            new javax.xml.namespace.QName("", "seq");
        private static final javax.xml.namespace.QName SYNCHRONIZED$8 = 
            new javax.xml.namespace.QName("", "synchronized");
        private static final javax.xml.namespace.QName TIMEOUT$10 = 
            new javax.xml.namespace.QName("", "timeout");
        private static final javax.xml.namespace.QName NEEDRETURN$12 = 
            new javax.xml.namespace.QName("", "needreturn");
        private static final javax.xml.namespace.QName PARAMENCRYPT$14 = 
            new javax.xml.namespace.QName("", "paramencrypt");
        private static final javax.xml.namespace.QName VALUE$16 = 
            new javax.xml.namespace.QName("", "value");
        private static final javax.xml.namespace.QName RESULTENCRYPT$18 = 
            new javax.xml.namespace.QName("", "resultencrypt");
        
        
        /**
         * Gets the "param" element
         */
        public com.neusoft.clw.core.xmlbean.ParamDocument.Param getParam()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.neusoft.clw.core.xmlbean.ParamDocument.Param target = null;
                target = (com.neusoft.clw.core.xmlbean.ParamDocument.Param)get_store().find_element_user(PARAM$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "param" element
         */
        public boolean isSetParam()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PARAM$0) != 0;
            }
        }
        
        /**
         * Sets the "param" element
         */
        public void setParam(com.neusoft.clw.core.xmlbean.ParamDocument.Param param)
        {
            generatedSetterHelperImpl(param, PARAM$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "param" element
         */
        public com.neusoft.clw.core.xmlbean.ParamDocument.Param addNewParam()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.neusoft.clw.core.xmlbean.ParamDocument.Param target = null;
                target = (com.neusoft.clw.core.xmlbean.ParamDocument.Param)get_store().add_element_user(PARAM$0);
                return target;
            }
        }
        
        /**
         * Unsets the "param" element
         */
        public void unsetParam()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PARAM$0, 0);
            }
        }
        
        /**
         * Gets the "result" element
         */
        public com.neusoft.clw.core.xmlbean.ResultDocument.Result getResult()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.neusoft.clw.core.xmlbean.ResultDocument.Result target = null;
                target = (com.neusoft.clw.core.xmlbean.ResultDocument.Result)get_store().find_element_user(RESULT$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "result" element
         */
        public boolean isSetResult()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(RESULT$2) != 0;
            }
        }
        
        /**
         * Sets the "result" element
         */
        public void setResult(com.neusoft.clw.core.xmlbean.ResultDocument.Result result)
        {
            generatedSetterHelperImpl(result, RESULT$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "result" element
         */
        public com.neusoft.clw.core.xmlbean.ResultDocument.Result addNewResult()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.neusoft.clw.core.xmlbean.ResultDocument.Result target = null;
                target = (com.neusoft.clw.core.xmlbean.ResultDocument.Result)get_store().add_element_user(RESULT$2);
                return target;
            }
        }
        
        /**
         * Unsets the "result" element
         */
        public void unsetResult()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(RESULT$2, 0);
            }
        }
        
        /**
         * Gets the "name" attribute
         */
        public java.lang.String getName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$4);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "name" attribute
         */
        public org.apache.xmlbeans.XmlString xgetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$4);
                return target;
            }
        }
        
        /**
         * Sets the "name" attribute
         */
        public void setName(java.lang.String name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$4);
                }
                target.setStringValue(name);
            }
        }
        
        /**
         * Sets (as xml) the "name" attribute
         */
        public void xsetName(org.apache.xmlbeans.XmlString name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NAME$4);
                }
                target.set(name);
            }
        }
        
        /**
         * Gets the "seq" attribute
         */
        public java.lang.String getSeq()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SEQ$6);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "seq" attribute
         */
        public org.apache.xmlbeans.XmlString xgetSeq()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SEQ$6);
                return target;
            }
        }
        
        /**
         * Sets the "seq" attribute
         */
        public void setSeq(java.lang.String seq)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SEQ$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SEQ$6);
                }
                target.setStringValue(seq);
            }
        }
        
        /**
         * Sets (as xml) the "seq" attribute
         */
        public void xsetSeq(org.apache.xmlbeans.XmlString seq)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SEQ$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(SEQ$6);
                }
                target.set(seq);
            }
        }
        
        /**
         * Gets the "synchronized" attribute
         */
        public java.lang.String getSynchronized()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SYNCHRONIZED$8);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "synchronized" attribute
         */
        public org.apache.xmlbeans.XmlString xgetSynchronized()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SYNCHRONIZED$8);
                return target;
            }
        }
        
        /**
         * True if has "synchronized" attribute
         */
        public boolean isSetSynchronized()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(SYNCHRONIZED$8) != null;
            }
        }
        
        /**
         * Sets the "synchronized" attribute
         */
        public void setSynchronized(java.lang.String xsynchronized)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SYNCHRONIZED$8);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SYNCHRONIZED$8);
                }
                target.setStringValue(xsynchronized);
            }
        }
        
        /**
         * Sets (as xml) the "synchronized" attribute
         */
        public void xsetSynchronized(org.apache.xmlbeans.XmlString xsynchronized)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SYNCHRONIZED$8);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(SYNCHRONIZED$8);
                }
                target.set(xsynchronized);
            }
        }
        
        /**
         * Unsets the "synchronized" attribute
         */
        public void unsetSynchronized()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(SYNCHRONIZED$8);
            }
        }
        
        /**
         * Gets the "timeout" attribute
         */
        public java.lang.String getTimeout()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TIMEOUT$10);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "timeout" attribute
         */
        public org.apache.xmlbeans.XmlString xgetTimeout()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TIMEOUT$10);
                return target;
            }
        }
        
        /**
         * True if has "timeout" attribute
         */
        public boolean isSetTimeout()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(TIMEOUT$10) != null;
            }
        }
        
        /**
         * Sets the "timeout" attribute
         */
        public void setTimeout(java.lang.String timeout)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TIMEOUT$10);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TIMEOUT$10);
                }
                target.setStringValue(timeout);
            }
        }
        
        /**
         * Sets (as xml) the "timeout" attribute
         */
        public void xsetTimeout(org.apache.xmlbeans.XmlString timeout)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TIMEOUT$10);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(TIMEOUT$10);
                }
                target.set(timeout);
            }
        }
        
        /**
         * Unsets the "timeout" attribute
         */
        public void unsetTimeout()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(TIMEOUT$10);
            }
        }
        
        /**
         * Gets the "needreturn" attribute
         */
        public java.lang.String getNeedreturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NEEDRETURN$12);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "needreturn" attribute
         */
        public org.apache.xmlbeans.XmlString xgetNeedreturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NEEDRETURN$12);
                return target;
            }
        }
        
        /**
         * True if has "needreturn" attribute
         */
        public boolean isSetNeedreturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(NEEDRETURN$12) != null;
            }
        }
        
        /**
         * Sets the "needreturn" attribute
         */
        public void setNeedreturn(java.lang.String needreturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NEEDRETURN$12);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NEEDRETURN$12);
                }
                target.setStringValue(needreturn);
            }
        }
        
        /**
         * Sets (as xml) the "needreturn" attribute
         */
        public void xsetNeedreturn(org.apache.xmlbeans.XmlString needreturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NEEDRETURN$12);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NEEDRETURN$12);
                }
                target.set(needreturn);
            }
        }
        
        /**
         * Unsets the "needreturn" attribute
         */
        public void unsetNeedreturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(NEEDRETURN$12);
            }
        }
        
        /**
         * Gets the "paramencrypt" attribute
         */
        public java.lang.String getParamencrypt()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PARAMENCRYPT$14);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "paramencrypt" attribute
         */
        public org.apache.xmlbeans.XmlString xgetParamencrypt()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(PARAMENCRYPT$14);
                return target;
            }
        }
        
        /**
         * True if has "paramencrypt" attribute
         */
        public boolean isSetParamencrypt()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(PARAMENCRYPT$14) != null;
            }
        }
        
        /**
         * Sets the "paramencrypt" attribute
         */
        public void setParamencrypt(java.lang.String paramencrypt)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PARAMENCRYPT$14);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(PARAMENCRYPT$14);
                }
                target.setStringValue(paramencrypt);
            }
        }
        
        /**
         * Sets (as xml) the "paramencrypt" attribute
         */
        public void xsetParamencrypt(org.apache.xmlbeans.XmlString paramencrypt)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(PARAMENCRYPT$14);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(PARAMENCRYPT$14);
                }
                target.set(paramencrypt);
            }
        }
        
        /**
         * Unsets the "paramencrypt" attribute
         */
        public void unsetParamencrypt()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(PARAMENCRYPT$14);
            }
        }
        
        /**
         * Gets the "value" attribute
         */
        public java.lang.String getValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VALUE$16);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "value" attribute
         */
        public org.apache.xmlbeans.XmlString xgetValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(VALUE$16);
                return target;
            }
        }
        
        /**
         * True if has "value" attribute
         */
        public boolean isSetValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(VALUE$16) != null;
            }
        }
        
        /**
         * Sets the "value" attribute
         */
        public void setValue(java.lang.String value)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VALUE$16);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(VALUE$16);
                }
                target.setStringValue(value);
            }
        }
        
        /**
         * Sets (as xml) the "value" attribute
         */
        public void xsetValue(org.apache.xmlbeans.XmlString value)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(VALUE$16);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(VALUE$16);
                }
                target.set(value);
            }
        }
        
        /**
         * Unsets the "value" attribute
         */
        public void unsetValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(VALUE$16);
            }
        }
        
        /**
         * Gets the "resultencrypt" attribute
         */
        public java.lang.String getResultencrypt()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RESULTENCRYPT$18);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "resultencrypt" attribute
         */
        public org.apache.xmlbeans.XmlString xgetResultencrypt()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(RESULTENCRYPT$18);
                return target;
            }
        }
        
        /**
         * True if has "resultencrypt" attribute
         */
        public boolean isSetResultencrypt()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(RESULTENCRYPT$18) != null;
            }
        }
        
        /**
         * Sets the "resultencrypt" attribute
         */
        public void setResultencrypt(java.lang.String resultencrypt)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RESULTENCRYPT$18);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(RESULTENCRYPT$18);
                }
                target.setStringValue(resultencrypt);
            }
        }
        
        /**
         * Sets (as xml) the "resultencrypt" attribute
         */
        public void xsetResultencrypt(org.apache.xmlbeans.XmlString resultencrypt)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(RESULTENCRYPT$18);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(RESULTENCRYPT$18);
                }
                target.set(resultencrypt);
            }
        }
        
        /**
         * Unsets the "resultencrypt" attribute
         */
        public void unsetResultencrypt()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(RESULTENCRYPT$18);
            }
        }
    }
}
