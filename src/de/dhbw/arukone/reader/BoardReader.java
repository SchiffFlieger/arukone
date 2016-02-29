package de.dhbw.arukone.reader;


import de.dhbw.arukone.FastArukoneBoard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.LinkedList;

public class BoardReader {
    private final String ATTRIBUTE_SIZE;
    private final String ATTRIBUTE_ID;
    private final String ATTRIBUTE_CLASS;
    private final String TAG_PATH;
    private final String TAG_POINT;
    private final String TAG_X;
    private final String TAG_Y;
    private final String ATTRIBUTE_VALUE_START;

    public BoardReader () {
        ATTRIBUTE_SIZE = "size";
        ATTRIBUTE_ID = "id";
        ATTRIBUTE_CLASS = "class";
        TAG_PATH = "path";
        TAG_POINT = "point";
        TAG_X = "x";
        TAG_Y = "y";
        ATTRIBUTE_VALUE_START = "start";
    }

    public FastArukoneBoard readBoard (String filePath) {
        Document doc = getXmlDocument(filePath);
        Element root = doc.getDocumentElement();

        FastArukoneBoard board = new FastArukoneBoard(getIdentifierFromPath(filePath), Integer.parseInt(root.getAttribute(ATTRIBUTE_SIZE)));
        this.addAllPaths(root, board);

        return board;
    }

    public ObservableList<FastArukoneBoard> readAllBoardsInDirectory (String path) {
        ObservableList<FastArukoneBoard> boards = FXCollections.observableArrayList();
        for (File file : listFiles(path)) {
            boards.add(readBoard(file.getPath()));
        }
        return boards;
    }

    private String getIdentifierFromPath (String string) {
        String name = new File(string).getName().split("\\.")[0];
        final int version = Integer.parseInt(name.split("_")[1]);
        name = name.split("_")[0];
        return String.format("%s V%d\n", name, version);
    }

    private File[] listFiles (String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            return dir.listFiles();
        }
        return new File[0];
    }

    private void addAllPaths(Element root, FastArukoneBoard board) {
        NodeList pathList = root.getElementsByTagName(TAG_PATH);
        for (int i = 0; i < pathList.getLength(); i++) {
            Node pathNode = pathList.item(i);
            if (pathNode.getNodeType() == Node.ELEMENT_NODE) {
                Element pathElement = (Element) pathNode;
                addSinglePath(pathElement, board);
            }
        }
    }

    private void addSinglePath(Element pathElement, FastArukoneBoard board) {
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

    private Document getXmlDocument (String filePath) {
        Document doc = null;
        try {
            File xml = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(xml);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return doc;
    }
}
