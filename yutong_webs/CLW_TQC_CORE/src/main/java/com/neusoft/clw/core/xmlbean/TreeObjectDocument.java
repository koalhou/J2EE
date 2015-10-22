/*
 * An XML document type.
 * Localname: tree-object
 * Namespace: 
 * Java type: com.neusoft.clw.core.xmlbean.TreeObjectDocument
 *
 * Automatically generated - do not modify.
 */
package com.neusoft.clw.core.xmlbean;


/**
 * A document containing one tree-object(@) element.
 *
 * This is a complex type.
 */
public interface TreeObjectDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TreeObjectDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s4DC543D452EAF5974070ADFBBC131311").resolveHandle("treeobject0e35doctype");
    
    /**
     * Gets the "tree-object" element
     */
    com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject getTreeObject();
    
    /**
     * Sets the "tree-object" element
     */
    void setTreeObject(com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject treeObject);
    
    /**
     * Appends and returns a new empty "tree-object" element
     */
    com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject addNewTreeObject();
    
    /**
     * An XML tree-object(@).
     *
     * This is a complex type.
     */
    public interface TreeObject extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TreeObject.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s4DC543D452EAF5974070ADFBBC131311").resolveHandle("treeobject617felemtype");
        
        /**
         * Gets array of all "value" elements
         */
        com.neusoft.clw.core.xmlbean.ValueDocument.Value[] getValueArray();
        
        /**
         * Gets ith "value" element
         */
        com.neusoft.clw.core.xmlbean.ValueDocument.Value getValueArray(int i);
        
        /**
         * Returns number of "value" element
         */
        int sizeOfValueArray();
        
        /**
         * Sets array of all "value" element
         */
        void setValueArray(com.neusoft.clw.core.xmlbean.ValueDocument.Value[] valueArray);
        
        /**
         * Sets ith "value" element
         */
        void setValueArray(int i, com.neusoft.clw.core.xmlbean.ValueDocument.Value value);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "value" element
         */
        com.neusoft.clw.core.xmlbean.ValueDocument.Value insertNewValue(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "value" element
         */
        com.neusoft.clw.core.xmlbean.ValueDocument.Value addNewValue();
        
        /**
         * Removes the ith "value" element
         */
        void removeValue(int i);
        
        /**
         * Gets array of all "map" elements
         */
        com.neusoft.clw.core.xmlbean.MapDocument.Map[] getMapArray();
        
        /**
         * Gets ith "map" element
         */
        com.neusoft.clw.core.xmlbean.MapDocument.Map getMapArray(int i);
        
        /**
         * Returns number of "map" element
         */
        int sizeOfMapArray();
        
        /**
         * Sets array of all "map" element
         */
        void setMapArray(com.neusoft.clw.core.xmlbean.MapDocument.Map[] mapArray);
        
        /**
         * Sets ith "map" element
         */
        void setMapArray(int i, com.neusoft.clw.core.xmlbean.MapDocument.Map map);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "map" element
         */
        com.neusoft.clw.core.xmlbean.MapDocument.Map insertNewMap(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "map" element
         */
        com.neusoft.clw.core.xmlbean.MapDocument.Map addNewMap();
        
        /**
         * Removes the ith "map" element
         */
        void removeMap(int i);
        
        /**
         * Gets array of all "list" elements
         */
        com.neusoft.clw.core.xmlbean.ListDocument.List[] getListArray();
        
        /**
         * Gets ith "list" element
         */
        com.neusoft.clw.core.xmlbean.ListDocument.List getListArray(int i);
        
        /**
         * Returns number of "list" element
         */
        int sizeOfListArray();
        
        /**
         * Sets array of all "list" element
         */
        void setListArray(com.neusoft.clw.core.xmlbean.ListDocument.List[] listArray);
        
        /**
         * Sets ith "list" element
         */
        void setListArray(int i, com.neusoft.clw.core.xmlbean.ListDocument.List list);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "list" element
         */
        com.neusoft.clw.core.xmlbean.ListDocument.List insertNewList(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "list" element
         */
        com.neusoft.clw.core.xmlbean.ListDocument.List addNewList();
        
        /**
         * Removes the ith "list" element
         */
        void removeList(int i);
        
        /**
         * Gets the "version" attribute
         */
        java.lang.String getVersion();
        
        /**
         * Gets (as xml) the "version" attribute
         */
        org.apache.xmlbeans.XmlString xgetVersion();
        
        /**
         * True if has "version" attribute
         */
        boolean isSetVersion();
        
        /**
         * Sets the "version" attribute
         */
        void setVersion(java.lang.String version);
        
        /**
         * Sets (as xml) the "version" attribute
         */
        void xsetVersion(org.apache.xmlbeans.XmlString version);
        
        /**
         * Unsets the "version" attribute
         */
        void unsetVersion();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject newInstance() {
              return (com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument newInstance() {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.neusoft.clw.core.xmlbean.TreeObjectDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.neusoft.clw.core.xmlbean.TreeObjectDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
