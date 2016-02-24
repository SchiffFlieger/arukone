package de.dhbw.arukone.reader;


import de.dhbw.arukone.ArukoneBoard;
import de.dhbw.arukone.Path;
import de.dhbw.arukone.Point;
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
import java.util.List;

public class BoardReader {
    private final String ATTRIBUTE_SIZE;
    private final String ATTRIBUTE_ID;
    private final String ATTRIBUTE_CLASS;
    private final String TAG_PATH;
    private final String TAG_POINT;
    private final String TAG_X;
    private final String TAG_Y;
    private final String ATTRIBUTE_VALUE_START;
    private final String FILE_SEP;

    public BoardReader () {
        ATTRIBUTE_SIZE = "size";
        ATTRIBUTE_ID = "id";
        ATTRIBUTE_CLASS = "class";
        TAG_PATH = "path";
        TAG_POINT = "point";
        TAG_X = "x";
        TAG_Y = "y";
        ATTRIBUTE_VALUE_START = "start";
        FILE_SEP = System.getProperty("file.separator");
    }

    public ArukoneBoard readBoard (String filePath) {
        Document doc = getXmlDocument(filePath);
        Element root = doc.getDocumentElement();

        ArukoneBoard board = new ArukoneBoard(getIdentifierFromPath(filePath), Integer.parseInt(root.getAttribute(ATTRIBUTE_SIZE)));

        List<Path> paths = getAllPaths(root);
        paths.forEach(board::addPath);

        return board;
    }

    private String getIdentifierFromPath (String string) {
        String name = string.split(FILE_SEP)[2].split("\\.")[0];
        final int version = Integer.parseInt(name.split("_")[1]);
        name = name.split("_")[0];
        return String.format("%s V%d\n", name, version);
    }

    public ObservableList<ArukoneBoard> readAllBoardsInDirectory(String path) {
        ObservableList<ArukoneBoard> boards = FXCollections.observableArrayList();
        for (File file : listFiles(path)) {
            boards.add(readBoard(file.getPath()));
        }
        return boards;
    }

    private File[] listFiles (String path) {File dir = new File(path);
        if (dir.isDirectory()) {
            return dir.listFiles();
        }
        return new File[0];
    }

    private List<Path> getAllPaths (Element root) {
        List<Path> paths = new LinkedList<>();
        NodeList pathList = root.getElementsByTagName(TAG_PATH);
        for (int i = 0; i < pathList.getLength(); i++) {
            Node pathNode = pathList.item(i);
            if (pathNode.getNodeType() == Node.ELEMENT_NODE) {
                Element pathElement = (Element) pathNode;
                Path path = getSinglePath(pathElement);
                paths.add(path);
            }
        }
        return paths;
    }

    private Path getSinglePath (Element pathElement) {
        final int id = Integer.parseInt(pathElement.getAttribute(ATTRIBUTE_ID));
        NodeList pointList = pathElement.getElementsByTagName(TAG_POINT);

        Point start = null, end = null;
        for (int j = 0; j < pointList.getLength(); j++) {
            Node pointNode = pointList.item(j);
            if (pointNode.getNodeType() == Node.ELEMENT_NODE) {
                Element pointElement = (Element) pointNode;
                final String clazz = pointElement.getAttribute(ATTRIBUTE_CLASS);
                final int x = Integer.parseInt(pointElement.getElementsByTagName(TAG_X).item(0).getTextContent());
                final int y = Integer.parseInt(pointElement.getElementsByTagName(TAG_Y).item(0).getTextContent());
                if (clazz.equalsIgnoreCase(ATTRIBUTE_VALUE_START)) {
                    start = new Point(x, y);
                } else {
                    end = new Point(x, y);
                }
            }
        }
        return new Path(id, start, end);
    }

    private Document getXmlDocument (String filePath) {
        Document doc = null;
        try {
            File xml = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc =  builder.parse(xml);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return doc;
    }
}
