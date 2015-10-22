/*
 * An XML document type.
 * Localname: client
 * Namespace: 
 * Java type: com.neusoft.clw.core.xmlbean.ClientDocument
 *
 * Automatically generated - do not modify.
 */
package com.neusoft.clw.core.xmlbean;


/**
 * A document containing one client(@) element.
 *
 * This is a complex type.
 */
public interface ClientDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ClientDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s4DC543D452EAF5974070ADFBBC131311").resolveHandle("clientfd84doctype");
    
    /**
     * Gets the "client" element
     */
    com.neusoft.clw.core.xmlbean.ClientDocument.Client getClient();
    
    /**
     * Sets the "client" element
     */
    void setClient(com.neusoft.clw.core.xmlbean.ClientDocument.Client client);
    
    /**
     * Appends and returns a new empty "client" element
     */
    com.neusoft.clw.core.xmlbean.ClientDocument.Client addNewClient();
    
    /**
     * An XML client(@).
     *
     * This is a complex type.
     */
    public interface Client extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Client.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s4DC543D452EAF5974070ADFBBC131311").resolveHandle("client770delemtype");
        
        /**
         * Gets the "isAuth" attribute
         */
        java.lang.String getIsAuth();
        
        /**
         * Gets (as xml) the "isAuth" attribute
         */
        org.apache.xmlbeans.XmlString xgetIsAuth();
        
        /**
         * True if has "isAuth" attribute
         */
        boolean isSetIsAuth();
        
        /**
         * Sets the "isAuth" attribute
         */
        void setIsAuth(java.lang.String isAuth);
        
        /**
         * Sets (as xml) the "isAuth" attribute
         */
        void xsetIsAuth(org.apache.xmlbeans.XmlString isAuth);
        
        /**
         * Unsets the "isAuth" attribute
         */
        void unsetIsAuth();
        
        /**
         * Gets the "sessionid" attribute
         */
        java.lang.String getSessionid();
        
        /**
         * Gets (as xml) the "sessionid" attribute
         */
        org.apache.xmlbeans.XmlString xgetSessionid();
        
        /**
         * True if has "sessionid" attribute
         */
        boolean isSetSessionid();
        
        /**
         * Sets the "sessionid" attribute
         */
        void setSessionid(java.lang.String sessionid);
        
        /**
         * Sets (as xml) the "sessionid" attribute
         */
        void xsetSessionid(org.apache.xmlbeans.XmlString sessionid);
        
        /**
         * Unsets the "sessionid" attribute
         */
        void unsetSessionid();
        
        /**
         * Gets the "operatorid" attribute
         */
        java.lang.String getOperatorid();
        
        /**
         * Gets (as xml) the "operatorid" attribute
         */
        org.apache.xmlbeans.XmlString xgetOperatorid();
        
        /**
         * True if has "operatorid" attribute
         */
        boolean isSetOperatorid();
        
        /**
         * Sets the "operatorid" attribute
         */
        void setOperatorid(java.lang.String operatorid);
        
        /**
         * Sets (as xml) the "operatorid" attribute
         */
        void xsetOperatorid(org.apache.xmlbeans.XmlString operatorid);
        
        /**
         * Unsets the "operatorid" attribute
         */
        void unsetOperatorid();
        
        /**
         * Gets the "corpid" attribute
         */
        java.lang.String getCorpid();
        
        /**
         * Gets (as xml) the "corpid" attribute
         */
        org.apache.xmlbeans.XmlString xgetCorpid();
        
        /**
         * True if has "corpid" attribute
         */
        boolean isSetCorpid();
        
        /**
         * Sets the "corpid" attribute
         */
        void setCorpid(java.lang.String corpid);
        
        /**
         * Sets (as xml) the "corpid" attribute
         */
        void xsetCorpid(org.apache.xmlbeans.XmlString corpid);
        
        /**
         * Unsets the "corpid" attribute
         */
        void unsetCorpid();
        
        /**
         * Gets the "authcode" attribute
         */
        java.lang.String getAuthcode();
        
        /**
         * Gets (as xml) the "authcode" attribute
         */
        org.apache.xmlbeans.XmlString xgetAuthcode();
        
        /**
         * True if has "authcode" attribute
         */
        boolean isSetAuthcode();
        
        /**
         * Sets the "authcode" attribute
         */
        void setAuthcode(java.lang.String authcode);
        
        /**
         * Sets (as xml) the "authcode" attribute
         */
        void xsetAuthcode(org.apache.xmlbeans.XmlString authcode);
        
        /**
         * Unsets the "authcode" attribute
         */
        void unsetAuthcode();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static com.neusoft.clw.core.xmlbean.ClientDocument.Client newInstance() {
              return (com.neusoft.clw.core.xmlbean.ClientDocument.Client) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static com.neusoft.clw.core.xmlbean.ClientDocument.Client newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (com.neusoft.clw.core.xmlbean.ClientDocument.Client) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.neusoft.clw.core.xmlbean.ClientDocument newInstance() {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.neusoft.clw.core.xmlbean.ClientDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.neusoft.clw.core.xmlbean.ClientDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
