/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.File;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;

/**
 *
 * @author RenanDuarte
 */
public class XMLParser {

    private File xml;
    private static Document doc;
    public XMLParser() {
        
    }
    
    public static Document parseFile(File xml){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(xml);
            doc.getDocumentElement().normalize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }
}
