
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Scanner;

public class DepositTransaction {
    public static void main(String args[]) {
        String serverSettingsFilePath = "Resources\\core.json";
        String terminalSettingsFilePath_1 = "Resources\\terminal1.xml";
        String terminalSettingsFilePath_2 = "Resources\\terminal2.xml";
        String terminalSettingsFilePath_3 = "Resources\\terminal3.xml";
        //input Server Port
        Scanner sc = new Scanner(System.in);
        String serverPort = sc.nextLine();


        String serverIP = sc.nextLine();

        updateTerminalSettingsFile(terminalSettingsFilePath_1, serverPort, serverIP);
        updateTerminalSettingsFile(terminalSettingsFilePath_2, serverPort, serverIP);
        updateTerminalSettingsFile(terminalSettingsFilePath_3, serverPort, serverIP);

        updateServerSettingsFile(serverSettingsFilePath, serverPort);
        BankServer server = new BankServer(serverSettingsFilePath);

        BankTerminal bankTerminal_1 = new BankTerminal(terminalSettingsFilePath_1);
        BankTerminal bankTerminal_2 = new BankTerminal(terminalSettingsFilePath_2);
        BankTerminal bankTerminal_3 = new BankTerminal(terminalSettingsFilePath_3);

        Thread terminalThread_1 = new Thread(bankTerminal_1, "terminal#1");
        Thread terminalThread_2 = new Thread(bankTerminal_2, "terminal#1");
        Thread terminalThread_3 = new Thread(bankTerminal_3, "terminal#1");


       // Thread serverThread = new Thread(server, "server");
        //    serverThread.start();
        terminalThread_1.start();
        terminalThread_2.start();
       // terminalThread_3.start();
        server.serverWait();
    }

    public static void updateServerSettingsFile(String settingsFilePath, String serverPort) {

        try {
            FileReader reader = new FileReader(settingsFilePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            //int port = (int) Integer.parseInt((String) jsonObject.get("port"));
            jsonObject.put("port", serverPort);

            reader.close();
            FileWriter writer = new FileWriter(settingsFilePath, false);
            try {
                writer.write(jsonObject.toJSONString());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writer.flush();
                writer.close();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateTerminalSettingsFile(String settingsFilePath, String serverPort, String serverIP) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            File xmlFile = new File(settingsFilePath);
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document docXML = documentBuilder.parse(xmlFile);
            docXML.getDocumentElement().normalize();

            updateAttributeValue(docXML, serverIP, serverPort);

            docXML.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(docXML);
            StreamResult result = new StreamResult(xmlFile);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
         //   System.out.println("XML file updated successfully");

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (org.xml.sax.SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }

    private static void updateAttributeValue(Document docXML, String ip, String port) {
        NodeList serverNode = docXML.getElementsByTagName("server");
        Element serverElement;
        //loop for each employee
        serverElement = (Element) serverNode.item(0);
        //String serverIP = serverElement.getElementsByTagName("ip").item(0).getFirstChild().getNodeValue();
        serverElement.setAttribute("ip", ip);
        serverElement.setAttribute("port", port);

    }
}
