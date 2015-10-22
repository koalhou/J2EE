/*
 * An XML document type.
 * Localname: value
 * Namespace: 
 * Java type: com.neusoft.clw.core.xmlbean.ValueDocument
 *
 * Automatically generated - do not modify.
 */
package com.neusoft.clw.core.xmlbean;


/**
 * A document containing one value(@) element.
 *
 * This is a complex type.
 */
public interface ValueDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ValueDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s4DC543D452EAF5974070ADFBBC131311").resolveHandle("valueec98doctype");
    
    /**
     * Gets the "value" element
     */
    com.neusoft.clw.core.xmlbean.ValueDocument.Value getValue();
    
    /**
     * Sets the "value" element
     */
    void setValue(com.neusoft.clw.core.xmlbean.ValueDocument.Value value);
    
    /**
     * Appends and returns a new empty "value" element
     */
    com.neusoft.clw.core.xmlbean.ValueDocument.Value addNewValue();
    
    /**
     * An XML value(@).
     *
     * This is a complex type.
     */
    public interface Value extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Value.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s4DC543D452EAF5974070ADFBBC131311").resolveHandle("value8b85elemtype");
        
        /**
         * Gets the "key" attribute
         */
        java.lang.String getKey();
        
        /**
         * Gets (as xml) the "key" attribute
         */
        org.apache.xmlbeans.XmlString xgetKey();
        
        /**
         * Sets the "key" attribute
         */
        void setKey(java.lang.String key);
        
        /**
         * Sets (as xml) the "key" attribute
         */
        void xsetKey(org.apache.xmlbeans.XmlString key);
        
        /**
         * Gets the "class" attribute
         */
        com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class.Enum getClass1();
        
        /**
         * Gets (as xml) the "class" attribute
         */
        com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class xgetClass1();
        
        /**
         * Sets the "class" attribute
         */
        void setClass1(com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class.Enum class1);
        
        /**
         * Sets (as xml) the "class" attribute
         */
        void xsetClass1(com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class class1);
        
        /**
         * Gets the "value" attribute
         */
        java.lang.String getValue();
        
        /**
         * Gets (as xml) the "value" attribute
         */
        org.apache.xmlbeans.XmlString xgetValue();
        
        /**
         * True if has "value" attribute
         */
        boolean isSetValue();
        
        /**
         * Sets the "value" attribute
         */
        void setValue(java.lang.String value);
        
        /**
         * Sets (as xml) the "value" attribute
         */
        void xsetValue(org.apache.xmlbeans.XmlString value);
        
        /**
         * Unsets the "value" attribute
         */
        void unsetValue();
        
        /**
         * An XML class(@).
         *
         * This is an atomic type that is a restriction of com.neusoft.clw.core.xmlbean.ValueDocument$Value$Class.
         */
        public interface Class extends org.apache.xmlbeans.XmlString
        {
            public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
                org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Class.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s4DC543D452EAF5974070ADFBBC131311").resolveHandle("class1e65attrtype");
            
            org.apache.xmlbeans.StringEnumAbstractBase enumValue();
            void set(org.apache.xmlbeans.StringEnumAbstractBase e);
            
            static final Enum BSTR = Enum.forString("bstr");
            static final Enum BYTES = Enum.forString("bytes");
            static final Enum INT = Enum.forString("int");
            static final Enum STR = Enum.forString("str");
            static final Enum BOOL = Enum.forString("bool");
            static final Enum TIME = Enum.forString("time");
            
            static final int INT_BSTR = Enum.INT_BSTR;
            static final int INT_BYTES = Enum.INT_BYTES;
            static final int INT_INT = Enum.INT_INT;
            static final int INT_STR = Enum.INT_STR;
            static final int INT_BOOL = Enum.INT_BOOL;
            static final int INT_TIME = Enum.INT_TIME;
            
            /**
             * Enumeration value class for com.neusoft.clw.core.xmlbean.ValueDocument$Value$Class.
             * These enum values can be used as follows:
             * <pre>
             * enum.toString(); // returns the string value of the enum
             * enum.intValue(); // returns an int value, useful for switches
             * // e.g., case Enum.INT_BSTR
             * Enum.forString(s); // returns the enum value for a string
             * Enum.forInt(i); // returns the enum value for an int
             * </pre>
             * Enumeration objects are immutable singleton objects that
             * can be compared using == object equality. They have no
             * public constructor. See the constants defined within this
             * class for all the valid values.
             */
            static final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase
            {
                /**
                 * Returns the enum value for a string, or null if none.
                 */
                public static Enum forString(java.lang.String s)
                    { return (Enum)table.forString(s); }
                /**
                 * Returns the enum value corresponding to an int, or null if none.
                 */
                public static Enum forInt(int i)
                    { return (Enum)table.forInt(i); }
                
                private Enum(java.lang.String s, int i)
                    { super(s, i); }
                
                static final int INT_BSTR = 1;
                static final int INT_BYTES = 2;
                static final int INT_INT = 3;
                static final int INT_STR = 4;
                static final int INT_BOOL = 5;
                static final int INT_TIME = 6;
                
                public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
                    new org.apache.xmlbeans.StringEnumAbstractBase.Table
                (
                    new Enum[]
                    {
                      new Enum("bstr", INT_BSTR),
                      new Enum("bytes", INT_BYTES),
                      new Enum("int", INT_INT),
                      new Enum("str", INT_STR),
                      new Enum("bool", INT_BOOL),
                      new Enum("time", INT_TIME),
                    }
                );
                private static final long serialVersionUID = 1L;
                private java.lang.Object readResolve() { return forInt(intValue()); } 
            }
            
            /**
             * A factory class with static methods for creating instances
             * of this type.
             */
            
            public static final class Factory
            {
                public static com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class newValue(java.lang.Object obj) {
                  return (com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class) type.newValue( obj ); }
                
                public static com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class newInstance() {
                  return (com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
                
                public static com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class newInstance(org.apache.xmlbeans.XmlOptions options) {
                  return (com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
                
                private Factory() { } // No instance of this class allowed
            }
        }
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static com.neusoft.clw.core.xmlbean.ValueDocument.Value newInstance() {
              return (com.neusoft.clw.core.xmlbean.ValueDocument.Value) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static com.neusoft.clw.core.xmlbean.ValueDocument.Value newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (com.neusoft.clw.core.xmlbean.ValueDocument.Value) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.neusoft.clw.core.xmlbean.ValueDocument newInstance() {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.neusoft.clw.core.xmlbean.ValueDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.neusoft.clw.core.xmlbean.ValueDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
