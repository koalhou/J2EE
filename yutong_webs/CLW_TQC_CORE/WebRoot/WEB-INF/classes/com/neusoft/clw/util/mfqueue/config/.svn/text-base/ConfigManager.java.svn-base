package com.neusoft.clw.util.mfqueue.config;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: neusoft</p>
 * @author wang-wei@neusoft.com
 * @version 1.0
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.neusoft.clw.util.mfqueue.exception.ConfigurationManagerException;

/**
 * The ConfigurationManager is the do-it-all-in-one utility. The ConfigurationManager allows you to
 * store properties inside an XML file or a properties file. The properties can be retrieved later
 * for use in your applications. <P/> Typical usage: <P/> <CODE> // // first we get an existing
 * property from the category &quot;general&quot; // String debug_file =
 * cfgmgr.getProperty(&quot;debug_file&quot;); System.out.println(&quot;The debug_file is:&quot; +
 * debug_file); // // now we try to get a non-existing property from the category
 * &quot;general&quot; // String neProp = cfgmgr.getProperty(&quot;hello&quot;,&quot;my
 * default&quot;); System.out.println(&quot;The value is:&quot; + neProp ); // // last step is we
 * get a property from the category &quot;JDBC&quot; // String driver =
 * cfgmgr.getProperty(&quot;DRIVER&quot;,&quot;&quot;,&quot;JDBC&quot;);
 * System.out.println(&quot;The driver is:&quot; + driver); </CODE>
 * @author Andreas Mecky andreas.mecky@xcom.de
 * @author Terry Dye terry.dye@xcom.de
 * @author Yong Cheng (chengy@neusoft.com)
 * @created 27. Januar 2002
 * @version $Id: ConfigManager.java,v 1.1 2010/03/15 04:00:50 zhangmq Exp $
 * @since $Id: ConfigurationMananger.java,v 1.2
 */

public class ConfigManager {
    @SuppressWarnings("unchecked")
	private static Hashtable parameter;

    @SuppressWarnings("unchecked")
	private static Vector categories;

    private static ConfigManager cfgmgr = null;

    // private Hashtable parameter;
    // private Vector categories;
    // private ConfigManager cfgmgr = null;

    private boolean validate = false;

    /**
     * The default configuration path as a resource
     */
    @SuppressWarnings("unused")
	private static final String DEFAULT_CONFIG = "/config/proxy_config.xml";

    /**
     * Constructor for the ConfigurationManager object
     */
    // public ConfigManager() {
    @SuppressWarnings("unchecked")
	protected ConfigManager() {
        parameter = new Hashtable();
        categories = new Vector();
        // addCategory("general");
    }

    /**
     * This method returns the value for a particular key The category is "general".
     * @param key the name of the property
     * @return the value as String or null
     */
    public String getProperty(String key) {
        return getProperty(key, null, null);
    }

    /**
     * This method returns the value for a particular key. If this key does not exist then the
     * method returns the defaultValue. The category is "general".
     * @param key the name of the property
     * @param defaultValue the value that will be returned if the property cannot be found
     * @return the value as String or the defaultValue
     */
    public String getProperty(String key, String defaultValue) {
        return getProperty(key, defaultValue, null);
    }

    /**
     * This method returns the value for a particular key in the certain category. If this key does
     * not exist then the method returns the defaultValue
     * @param key the name of the property
     * @param defaultValue the value that will be returned if the property cannot be found
     * @param category the name of the category
     * @return the value as String or the defaultValue
     */
    public String getProperty(String key, String defaultValue, String category) {
        if (key == null) {
            return defaultValue;
        }

        String mykey;
        String generalKey = "general/" + key;
        if (category != null) {
            mykey = category + "/" + key;
        } else {
            mykey = generalKey;
        }
        if (parameter.containsKey((String) mykey)) {
            return (String) parameter.get((String) mykey);
        } else if (parameter.containsKey((String) generalKey)) {
            return (String) parameter.get((String) generalKey);
        } else {
            return defaultValue;
        }
    }

    /**
     * This method returns an instance of the ConfigurationManager. If there is no instance then it
     * will create a new one. In order to use the ConfigurationManager do the following: private
     * ConfigurationManager cfgmgr = ConfigurationManager.getInstance();
     * @return an instance of the ConfigurationManager
     */
    public static ConfigManager getInstance(InputStream config) throws
    // public ConfigManager getInstance(InputStream config) throws
            ConfigurationManagerException {
        // if (cfgmgr != null) {
        // return cfgmgr;
        // }
        cfgmgr = new ConfigManager();
        InputStream is = config;
        try {
            // get a default config_default.xml, means not need to call method
            // load ( filename )
            // ClassLoader cl = cfgmgr.getClass().getClassLoader();
            // is = cl.getResourceAsStream( "config/proxy_config.xml" );
            if (is != null) {
                cfgmgr.load(is);
            } else {
                throw new ConfigurationManagerException("Can't find config file");
            }
        } catch (Exception e) {
            throw new ConfigurationManagerException("Config file load failed:"+e.getMessage());
        }finally{
        	try {
				if(is != null){
					is.close();
				}
			} catch (Exception e2) {
				;
			}
        }
        return cfgmgr;
    }

    /**
     * This method returns an Enumeration of all categories. The names are stored as Strings inside
     * the Enumeration.
     * @return an enumeratiuon containing all names of all categories
     */
    @SuppressWarnings("unchecked")
	public Enumeration getCategoryNames() {
        return categories.elements();
    }

    /**
     * This method will return a Hashtable that contains all properties for the category "general".
     * The properties are stored as key and value both as strings.
     * @return a Hashtable that contains all properties for the category "general"
     */
    @SuppressWarnings("unchecked")
	public Hashtable getProperties() {
        return getProperties(null);
    }

    /**
     * This method will return a Hashtable that contains all properties for the selected category.
     * The properties are stored as key and value both as strings.
     * @param category the name of the category
     * @return a Hashtable that contains all properties for this category
     */
    @SuppressWarnings("unchecked")
	public Hashtable getProperties(String category) {
        Hashtable retValues = new Hashtable();
        // if no category was passed to us then we take "general"
        if (category == null) {
            category = "general";
        }
        String currentKey = null;
        // get all the keys from our Hashtable
        Enumeration keys = parameter.keys();
        while (keys.hasMoreElements()) {
            currentKey = (String) keys.nextElement();
            // let's see if the category name is inside the key-string
            if (currentKey.indexOf(category + "/") != -1) {
                // now extract the category name from the key and
                // store the name and the value
                String name = currentKey.substring(currentKey.indexOf("/") + 1);
                String value = (String) parameter.get((String) currentKey);
                retValues.put(name, value);
            }
        }
        return retValues;
    }

    /**
     * This method will set the value for a specific property in the category "general". If this
     * property does not exist then it will be created
     * @param name the name of the property
     * @param value the value for this property
     */
    public void setProperty(String name, String value) {
        setProperty(name, value, null);
    }

    /**
     * This method will set the value for a specific property in the selected category. If this
     * property does not exist then it will be created. If the category does not exist it will be
     * created.
     * @param name the name of the property
     * @param value the value for this property
     * @param category the name of the category
     */
    @SuppressWarnings("unchecked")
	public void setProperty(String name, String value, String category) {
        // we use the default one if it is not specified
        if (category == null) {
            category = "general";
        }
        // it is safe to use addCategory since it will check
        // if this category already exists and return quitely
        addCategory(category);
        // now we save the property
        String mykey = category + "/" + name;
        parameter.put(mykey, value);
    }

    /**
     * Adds a feature to the Category attribute of the ConfigurationManager object
     * @param category The feature to be added to the Category attribute
     */
    @SuppressWarnings("unchecked")
	public void addCategory(String category) {
        if (categories.indexOf(category) == -1) {
            categories.add(category);
        }
    }

    /**
     * This method will save the current categories and their properties to a file
     * @param file the file that will be generated
     * @exception ConfigurationManagerException Description of the Exception
     */
    @SuppressWarnings("unchecked")
	public void store(File file) throws ConfigurationManagerException {
        // this will store the properties for a certain category
        Hashtable props = new Hashtable();
        try {
            // let's open the file
            FileWriter fw = new FileWriter(file);
            // and write the header
            fw.write("<?xml version=\"1.0\" ?>\n");
            fw.write("<properties>\n");
            // now we walk through all categories
            for (int i = 0; i < categories.size(); i++) {
                fw.write("  <category name='" + (String) categories.get(i) + "'>\n");
                // now we get all properties for this category
                props = getProperties((String) categories.get(i));
                Enumeration lprops = props.keys();
                // here we walk through the properties
                while (lprops.hasMoreElements()) {
                    String currentKey = (String) lprops.nextElement();
                    // and write it to the file
                    fw.write("    <property name='" + currentKey + "' value='"
                            + (String) props.get(currentKey) + "'/>\n");
                }
                fw.write("  </category>\n");
            }
            fw.write("</properties>\n");
            // close the file because we are done
            fw.close();
        } catch (Exception e) {
            throw new ConfigurationManagerException("The file cannot be saved:"+e.getMessage());
        }
    }

    /**
     * Gets the variables attribute of the ConfigurationManager object
     * @param doc Description of the Parameter
     * @return The variables value
     */
    @SuppressWarnings("unchecked")
	private Hashtable getVariables(Document doc) {
        Hashtable vars = null;
        NodeList nl = doc.getElementsByTagName("variables");
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            vars = new Hashtable();
            for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
                // get all vraibles
                if (child.getNodeName().equals("variable")) {
                    NamedNodeMap myAtt = child.getAttributes();
                    Node myNode = myAtt.getNamedItem("name");
                    String name = myNode.getNodeValue();
                    myNode = myAtt.getNamedItem("value");
                    String value = myNode.getNodeValue();
                    if (name != null && value != null) {
                        // we store the name as ${name} so we have
                        // it directly the way it is used
                        String rname = "${" + name + "}";
                        vars.put(rname, value);
                    }
                }
            }
        }
        return vars;
    }

    /**
     * Description of the Method
     * @param vars Description of the Parameter
     * @param line Description of the Parameter
     * @return Description of the Return Value
     */
    @SuppressWarnings("unchecked")
	private String replaceVariables(Hashtable vars, String line) {
        if (vars != null) {
            Enumeration keys = vars.keys();
            int pos = 0;
            // walk through all variables
            while (keys.hasMoreElements()) {
                String currentKey = (String) keys.nextElement();
                String value = (String) vars.get((String) currentKey);
                pos = line.indexOf(currentKey);
                // check if we have found a variable
                if (pos != -1) {
                    // cut the line into 2 pieces and put in the
                    // value of the variable
                    String firstPart = line.substring(0, pos);
                    String secondPart = line.substring(pos + currentKey.length());
                    line = firstPart + value + secondPart;
                }
            }
        }
        return line;
    }

    /**
     * Description of the Method
     * @param doc Description of the Parameter
     */
    @SuppressWarnings("unchecked")
	private void processProperties(Document doc) {
        String currentCategory;
        Hashtable myvars = getVariables(doc);
        // first we get all nodes where the element is category
        NodeList nl = doc.getElementsByTagName("category");
        for (int i = 0; i < nl.getLength(); i++) {
            // now we get every node from the list
            Node n = nl.item(i);
            // and get the name attribute for this category
            NamedNodeMap curAtt = n.getAttributes();
            Node curNode = curAtt.getNamedItem("name");
            currentCategory = curNode.getNodeValue();
            // now we process all children for this category
            for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (child.getNodeName().equals("property")) {
                    // we have found a property element and now we grab the name and value
                    // attributes
                    NamedNodeMap myAtt = child.getAttributes();
                    Node myNode = myAtt.getNamedItem("name");
                    String name = myNode.getNodeValue();
                    myNode = myAtt.getNamedItem("value");
                    String value = myNode.getNodeValue();
                    // if we have both then lets store it
                    if (name != null && value != null) {
                        // the key is always category/name
                        // e.g. general/congig_file
                        setProperty(name, replaceVariables(myvars, value), currentCategory);
                    }
                }
            }
        }
    }

    /**
     * This method will set the flag if validation is used for parsing the file/stream from URL or
     * not
     * @param validate true if the content should be validated first
     */
    public void setValidation(boolean validate) {
        this.validate = validate;
    }

    /**
     * This method will read in a file and generate the properties
     * @param file Description of the Parameter
     * @throws ConfigurationManagerException if the file cannot be processed
     */
    public synchronized void load(File file) throws ConfigurationManagerException {
        cfgmgr = new ConfigManager();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // set the validation flag (true if it should be validated)
        dbf.setValidating(validate);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            throw new ConfigurationManagerException(
                    "The parser cannot create a new document builder: " + pce.getMessage());
        }
        // if we have to validate then we need to define an error handler here
        if (validate) {
            try {
                // Set an ErrorHandler before parsing
                OutputStreamWriter errorWriter = new OutputStreamWriter(System.err, "UTF-8");
                db.setErrorHandler(new MyErrorHandler(new PrintWriter(errorWriter, true)));
            } catch (Exception e) {
                throw new ConfigurationManagerException(
                        "The parser cannot set up the error handler:"+e.getMessage());
            }
        }
        Document doc = null;
        try {
            doc = db.parse(file);
        } catch (SAXException se) {
            throw new ConfigurationManagerException("The parser cannot parse the file: "
                    + se.getMessage());
        } catch (IOException ioe) {
            throw new ConfigurationManagerException("The parser cannot open the file: "
                    + ioe.getMessage());
        }
        processProperties(doc);

    }

    /**
     * This method will read in a file and generate the properties
     * @param is The inputstream
     * @throws ConfigurationManagerException if the file cannot be processed
     */
    public synchronized void load(InputStream is) throws ConfigurationManagerException {
        cfgmgr = new ConfigManager();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // set the validation flag (true if it should be validated)
        dbf.setValidating(validate);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            throw new ConfigurationManagerException(
                    "The parser cannot create a new document builder: " + pce.getMessage());
        }
        // if we have to validate then we need to define an error handler here
        if (validate) {
            try {
                // Set an ErrorHandler before parsing
                OutputStreamWriter errorWriter = new OutputStreamWriter(System.err, "UTF-8");
                db.setErrorHandler(new MyErrorHandler(new PrintWriter(errorWriter, true)));
            } catch (Exception e) {
                throw new ConfigurationManagerException(
                        "The parser cannot set up the error handler:"+e.getMessage());
            }
        }
        Document doc = null;
        try {
            doc = db.parse(is);
        } catch (SAXException se) {
            throw new ConfigurationManagerException("The parser cannot parse the file: "
                    + se.getMessage());
        } catch (IOException ioe) {
            throw new ConfigurationManagerException("The parser cannot open the file: "
                    + ioe.getMessage());
        }
        processProperties(doc);

    }

    /**
     * This method will read the content from an URL and generate the properties. If you have the
     * need to use a proxy server, create jconfig.properties file and place it inside your system
     * path. <BR />
     * jconfig.properties example:<BR /> # jconfig.properties file<BR />
     * http.proxyHost=proxy.server.url<BR />
     * http.proxyPort=3128
     * @param theURL Description of the Parameter
     * @throws ConfigurationManagerException if the file cannot be processed
     */
    public synchronized void load(String theURL) throws ConfigurationManagerException {
        cfgmgr = new ConfigManager();
        java.net.URL jcfURL = null;
        InputStream is = null;
        try {
            // get a jconfig.properties in classpath, if it exists
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream jcf = cl.getResourceAsStream("jconfig.properties");

            // it is possible that the jconfig.properties does not exist, we get null
            if (jcf != null) {
                Properties jcfProperties = new Properties();
                jcfProperties.load(jcf);

                // load what is set in system
                Properties prop = System.getProperties();
                // if we see http.proxyHost and/or http.proxyPort inside
                // the jconfig.properties, we can set the System.properties
                // for use by the URLConnection object
                if (jcfProperties.getProperty("http.proxyHost") != null)
                    prop.put("http.proxyHost", jcfProperties.getProperty("http.proxyHost"));
                if (jcfProperties.getProperty("http.proxyPort") != null)
                    prop.put("http.proxyPort", jcfProperties.getProperty("http.proxyPort"));
                
                jcf.close();
            }
            // prepare URL, open the connection and grab stream for parsing
            jcfURL = new java.net.URL(theURL);
            java.net.URLConnection con = jcfURL.openConnection();
            is = con.getInputStream();
        } catch (Exception e) {
            throw new ConfigurationManagerException(
                    "Problem with URL handling/connection/validating: " + e.getMessage());
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(validate);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            throw new ConfigurationManagerException(
                    "The parser cannot create a new document builder: " + pce.getMessage());
        }
        // if we have to validate then we need to define an error handler here
        if (validate) {
            try {
                // Set an ErrorHandler before parsing
                OutputStreamWriter errorWriter = new OutputStreamWriter(System.err, "UTF-8");
                db.setErrorHandler(new MyErrorHandler(new PrintWriter(errorWriter, true)));
            } catch (Exception e) {
                throw new ConfigurationManagerException(
                        "The parser cannot set up the error handler:"+e.getMessage());
            }
        }
        Document doc = null;
        try {
            doc = db.parse(is);
        } catch (SAXException se) {
            throw new ConfigurationManagerException("The parser cannot parse the XML: "
                    + se.getMessage());
        } catch (IOException ioe) {
            throw new ConfigurationManagerException("The parser cannot open the file: "
                    + ioe.getMessage());
        }
        finally{
        	if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					;
				}
			}
        }
        processProperties(doc);

    }

    /**
     * This method will store all categories and properties to a Java-properties file. All
     * properties will be in the category "general". It will override an existing configuration.
     * @param file the file
     * @throws ConfigurationManagerException if the file cannot be processed
     */
    @SuppressWarnings("unchecked")
	public void loadProperties(File file) throws ConfigurationManagerException {
        try {
            FileInputStream input = new FileInputStream(file);
            Properties props = new Properties();
            props.load(input);
            Enumeration propertyNames = props.propertyNames();
            cfgmgr = new ConfigManager();
            while (propertyNames.hasMoreElements()) {
                String name = (String) propertyNames.nextElement();
                String value = props.getProperty(name);
                setProperty(name, value);
            }
            input.close();
        } catch (FileNotFoundException e) {
            throw new ConfigurationManagerException("The file cannot be loaded:"+e.getMessage());
        } catch (Exception e) {
            throw new ConfigurationManagerException("The file cannot be loaded:"+e.getMessage());
        }
    }

    /**
     * This method will generate a Java-properties file. All categories will be saved as comments
     * and only the properties are written to the file. The output is category.property=value
     * @param file the file
     * @throws ConfigurationManagerException if the file cannot be processed
     */
    @SuppressWarnings("unchecked")
	public void storeProperties(File file) throws ConfigurationManagerException {
        // this will store the properties for a certain category

        // alter by chengjin for findbug
        // Hashtable props = new Hashtable();
        // String currentCategory = new String();
        Hashtable props = null;
        String currentCategory = null;
        try {
            // let's open the file
            FileWriter fw = new FileWriter(file);
            // and write the header
            fw.write("#\n");
            fw.write("# automatically generated properties file\n");
            fw.write("#\n");
            // now we walk through all categories
            for (int i = 0; i < categories.size(); i++) {
                currentCategory = (String) categories.get(i);
                // write the category as comment
                fw.write("#\n");
                fw.write("# category: " + currentCategory + "\n");
                fw.write("#\n");
                // now we get all properties for this category
                props = getProperties((String) categories.get(i));
                Enumeration lprops = props.keys();
                // here we walk through the properties
                while (lprops.hasMoreElements()) {
                    String currentKey = (String) lprops.nextElement();
                    // and write it to the file
                    // form category.property=value
                    fw.write(currentCategory + "." + currentKey + "="
                            + (String) props.get(currentKey) + "\n");
                }
            }
            // close the file because we are done
            fw.close();
        } catch (Exception e) {
            throw new ConfigurationManagerException("The file cannot be saved:"+e.getMessage());
        }
    }

    /**
     * This method will write a Java-properties file and fill in the properties for the particular
     * category. The category will be saved as comment and only the properties are written to the
     * file. The output is property=value. The category is not included in the property name.
     * @param file the file
     * @param category Description of the Parameter
     * @throws ConfigurationManagerException if the file cannot be processed
     */
    @SuppressWarnings("unchecked")
	public void storeProperties(File file, String category) throws ConfigurationManagerException {
        // this will store the properties for a certain category
        Hashtable props = new Hashtable();
        try {
            // let's open the file
            FileWriter fw = new FileWriter(file);
            // and write the header
            fw.write("#\n");
            fw.write("# automatically generated properties file");
            fw.write("#\n");
            fw.write("#\n");
            fw.write("# category: " + category + "\n");
            fw.write("#\n");
            // now we get all properties for this category
            props = getProperties(category);
            Enumeration lprops = props.keys();
            // here we walk through the properties
            while (lprops.hasMoreElements()) {
                String currentKey = (String) lprops.nextElement();
                // and write it to the file
                // form category.property=value
                fw.write(currentKey + "=" + (String) props.get(currentKey) + "\n");
            }
            // close the file because we are done
            fw.close();
        } catch (Exception e) {
            throw new ConfigurationManagerException("The file cannot be saved:"+e.getMessage());
        }
    }

    //
    // This is an inner class
    //
    // Error handler to report errors and warnings
    //
    /**
     * Description of the Class
     * @author andreas
     * @created 27. Januar 2002
     */
    private static class MyErrorHandler implements ErrorHandler {

        private PrintWriter out;

        /**
         * Constructor for the MyErrorHandler object
         * @param out Description of the Parameter
         */
        MyErrorHandler(PrintWriter out) {
            this.out = out;
        }

        /**
         * Returns a string describing parse exception details
         * @param spe Description of the Parameter
         * @return The parseExceptionInfo value
         */
        private String getParseExceptionInfo(SAXParseException spe) {
            String systemId = spe.getSystemId();
            if (systemId == null) {
                systemId = "null";
            }
            String info = "URI=" + systemId + " Line=" + spe.getLineNumber() + ": "
                    + spe.getMessage();
            return info;
        }

        // The following methods are standard SAX ErrorHandler methods.
        // See SAX documentation for more info.

        /**
         * Description of the Method
         * @param spe Description of the Parameter
         * @exception SAXException Description of the Exception
         */
        public void warning(SAXParseException spe) throws SAXException {
            out.println("Warning: " + getParseExceptionInfo(spe));
        }

        /**
         * Description of the Method
         * @param spe Description of the Parameter
         * @exception SAXException Description of the Exception
         */
        public void error(SAXParseException spe) throws SAXException {
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }

        /**
         * Description of the Method
         * @param spe Description of the Parameter
         * @exception SAXException Description of the Exception
         */
        public void fatalError(SAXParseException spe) throws SAXException {
            String message = "Fatal Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    }
}
