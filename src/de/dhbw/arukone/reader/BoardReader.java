package de.dhbw.arukone.reader;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

            Element root = doc.getDocumentElement();
            final int size = Integer.parseInt(root.getAttribute("size"));
            System.out.println(root.getNodeName() + " | size:" + size);

            NodeList pathList = root.getElementsByTagName("path");
            for (int i = 0; i < pathList.getLength(); i++) {
                Node pathNode = pathList.item(i);
                if (pathNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element pathElement = (Element) pathNode;
                    final int id = Integer.parseInt(pathElement.getAttribute("id"));
                    NodeList pointList = pathElement.getElementsByTagName("point");
                    for (int j = 0; j < pointList.getLength(); j++) {
                        Node pointNode = pointList.item(j);
                        if (pointNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element pointElement = (Element) pointNode;
                            final String clazz = pointElement.getAttribute("class");
                            final int x = Integer.parseInt(pointElement.getElementsByTagName("x").item(0).getTextContent());
                            final int y = Integer.parseInt(pointElement.getElementsByTagName("y").item(0).getTextContent());
                            System.out.printf("point [%s] [%d|%d]\n", clazz, x, y);
                        } else {
                            System.out.println("point not an element");
                        }
                    }
                } else {
                    System.out.println("path not an element");
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
