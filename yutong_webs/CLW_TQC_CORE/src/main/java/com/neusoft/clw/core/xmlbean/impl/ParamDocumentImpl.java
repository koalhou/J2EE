/*
 * An XML document type.
 * Localname: param
 * Namespace: 
 * Java type: com.neusoft.clw.core.xmlbean.ParamDocument
 *
 * Automatically generated - do not modify.
 */
package com.neusoft.clw.core.xmlbean.impl;
/**
 * A document containing one param(@) element.
 *
 * This is a complex type.
 */
public class ParamDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.neusoft.clw.core.xmlbean.ParamDocument
{
    private static final long serialVersionUID = 1L;
    
    public ParamDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PARAM$0 = 
        new javax.xml.namespace.QName("", "param");
    
    
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
     * An XML param(@).
     *
     * This is a complex type.
     */
    public static class ParamImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.neusoft.clw.core.xmlbean.ParamDocument.Param
    {
        private static final long serialVersionUID = 1L;
        
        public ParamImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TREEOBJECT$0 = 
            new javax.xml.namespace.QName("", "tree-object");
        private static final javax.xml.namespace.QName VALUE$2 = 
            new javax.xml.namespace.QName("", "value");
        
        
        /**
         * Gets the "tree-object" element
         */
        public com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject getTreeObject()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject target = null;
                target = (com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject)get_store().find_element_user(TREEOBJECT$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "tree-object" element
         */
        public boolean isSetTreeObject()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TREEOBJECT$0) != 0;
            }
        }
        
        /**
         * Sets the "tree-object" element
         */
        public void setTreeObject(com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject treeObject)
        {
            generatedSetterHelperImpl(treeObject, TREEOBJECT$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "tree-object" element
         */
        public com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject addNewTreeObject()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject target = null;
                target = (com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject)get_store().add_element_user(TREEOBJECT$0);
                return target;
            }
        }
        
        /**
         * Unsets the "tree-object" element
         */
        public void unsetTreeObject()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TREEOBJECT$0, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VALUE$2);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(VALUE$2);
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
                return get_store().find_attribute_user(VALUE$2) != null;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VALUE$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(VALUE$2);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(VALUE$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(VALUE$2);
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
                get_store().remove_attribute(VALUE$2);
            }
        }
    }
}
