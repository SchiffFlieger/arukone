package de.dhbw.arukone.reader;


import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * created by Karsten Köhler on 21.02.2016
 */
public class BoardReader {
    public static void main(String... args) {
        try {
            File xml = new File("res/boards/5x5_1.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);

            System.out.println("root: " + doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
