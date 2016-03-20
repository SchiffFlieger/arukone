package de.dhbw.arukone.reader;


import de.dhbw.arukone.ArukoneBoard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 *  Reader for reading {@link ArukoneBoard} from a xml-file.
 */
public class BoardReader {
    private final String ATTRIBUTE_SIZE = "size";
    private final String ATTRIBUTE_ID = "id";
    private final String ATTRIBUTE_CLASS = "class";
    private final String TAG_PATH = "path";
    private final String TAG_POINT = "point";
    private final String TAG_X = "x";
    private final String TAG_Y = "y";
    private final String ATTRIBUTE_VALUE_START = "start";

    /**
     * Creates a {@link ArukoneBoard} from a given xml-file.
     * @param filePath path to file
     * @return instance of {@link ArukoneBoard}
     */
    public ArukoneBoard readBoard (String filePath) {
        Document doc = getXmlDocument(new File(filePath));
        Element root = doc.getDocumentElement();

        ArukoneBoard board = new ArukoneBoard(getIdentifierFromPath(filePath), Integer.parseInt(root.getAttribute(ATTRIBUTE_SIZE)));
        this.addAllPaths(root, board);

        return board;
    }

    /**
     * Traverses all path-nodes in the xml-file.
     * @param root xml root node
     * @param board instance of {@link ArukoneBoard}
     */
    private void addAllPaths(Element root, ArukoneBoard board) {
        NodeList pathList = root.getElementsByTagName(TAG_PATH);
        for (int i = 0; i < pathList.getLength(); i++) {
            Node pathNode = pathList.item(i);
            if (pathNode.getNodeType() == Node.ELEMENT_NODE) {
                Element pathElement = (Element) pathNode;
                addSinglePath(pathElement, board);
            }
        }
    }

    /**
     * Adds a single path to the {@link ArukoneBoard}.
     * @param pathElement xml node
     * @param board instance of {@link ArukoneBoard}
     */
    private void addSinglePath(Element pathElement, ArukoneBoard board) {
        final int id = Integer.parseInt(pathElement.getAttribute(ATTRIBUTE_ID));
        NodeList pointList = pathElement.getElementsByTagName(TAG_POINT);

        int startX = 0, startY=0, endX=0, endY=0;
        for (int j = 0; j < pointList.getLength(); j++) {
            Node pointNode = pointList.item(j);
            if (pointNode.getNodeType() == Node.ELEMENT_NODE) {
                Element pointElement = (Element) pointNode;
                final String clazz = pointElement.getAttribute(ATTRIBUTE_CLASS);
                final int x = Integer.parseInt(pointElement.getElementsByTagName(TAG_X).item(0).getTextContent());
                final int y = Integer.parseInt(pointElement.getElementsByTagName(TAG_Y).item(0).getTextContent());
                if (clazz.equalsIgnoreCase(ATTRIBUTE_VALUE_START)) {
                    startX = x;
                    startY = y;
                } else {
                    endX = x;
                    endY = y;
                }
            }
        }
        board.addFixedPath(id, startX, startY, endX, endY);
    }

    /**
     * Builds a xml document of the given file.
     * @param file {@link File} to parse
     * @return instance of {@link Document}
     */
    private Document getXmlDocument (File file) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return doc;
    }

    /**
     * Creates an identifier based on the file name.
     * @param path path to file
     * @return identifier
     */
    private String getIdentifierFromPath (String path) {
        return new File(path).getName().split("\\.")[0];
    }
}
