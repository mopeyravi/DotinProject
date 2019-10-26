import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class BankTerminal implements Runnable {
    String id;
    String type;
    String serverPort;
    String serverIP;
    String outLogPath;
    String responseFilePath;
    ArrayList<Transaction> transactions;
    String settingsAndInformationFilePath;


    public BankTerminal(String id, String type, String serverPort, String serverIP, String outLogPath, ArrayList<Transaction> transactions) {
        this.id = id;
        this.type = type;
        this.serverPort = serverPort;
        this.serverIP = serverIP;
        this.outLogPath = outLogPath;
        this.transactions = transactions;
        responseFilePath = "response" + id + ".xml";
    }

    public BankTerminal(String settingsAndInformationFilePath) {
        this.settingsAndInformationFilePath = settingsAndInformationFilePath;
        importSettings();
        responseFilePath = "response" + id + ".xml";
    }

    public void importSettings() {
        SAXHandler saxHandler = new SAXHandler();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(settingsAndInformationFilePath, saxHandler);


            this.id = saxHandler.terminal.id;
            this.type = saxHandler.terminal.type;
            this.outLogPath = saxHandler.terminal.outLogPath;
            this.serverIP = saxHandler.terminal.serverIP;
            this.serverPort = saxHandler.terminal.serverPort;
            this.transactions = saxHandler.terminal.transactions;
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        initialResponseFile();
        try {
            FileWriter writer = new FileWriter(this.outLogPath, false);

            writer.write("Terminal Starts..\n");
            Socket reqSocket;
            ObjectOutputStream reqOutLine;
            ObjectInputStream reqInLine;

            for (int i = 0; i < transactions.size(); i++) {


                reqSocket = new Socket(serverIP, Integer.valueOf(serverPort).intValue());
                writer.write("Terminal Socket Info" + reqSocket.toString() + "\n");
                reqOutLine = new ObjectOutputStream(reqSocket.getOutputStream());
                //    writer.write("Terminal OutLine Info"+reqOutLine.toString());
                reqOutLine.writeObject(new TerminalRequest(transactions.get(i), this.id));

                writer.write("Terminal Sent Request:\tTransactionID: " + transactions.get(i).getId() + "\n");
                try {
                    writer.write("Terminal is Waiting for Server Response" + "\n");

                    reqInLine = new ObjectInputStream(reqSocket.getInputStream());
//                    System.out.println("Terminal InLine Info"+reqInLine.toString());
                    ServerResponse response = (ServerResponse) reqInLine.readObject();
                    writer.write("Terminal Receives Server Response:\tTransactionID: " + response.getTransaction().getId() + "\t" + response.isDoneCorrectly() + "\n");
                    updateTerminalSettingsFile(response);
                    writer.write("Terminal Updates Response File...\n");


//                    System.out.println("Server Transaction\t"+tr.id);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                //        reqSocket.close();
            }
            writer.close();
            //ObjectOutputStream(reqSocket.getOutputStream());
        } catch (IOException ex) {

            ex.printStackTrace();
        }
        System.out.println("No More request From Terminal...");


    }

    public void initialResponseFile() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            Document responseDoc = documentBuilder.newDocument();


            Element terminalElement = responseDoc.createElement("Terminal");
            responseDoc.appendChild(terminalElement);

            Attr terminalAtr = responseDoc.createAttribute("id");
            terminalAtr.setValue(this.id);
            terminalElement.setAttributeNode(terminalAtr);

            terminalAtr = responseDoc.createAttribute("type");
            terminalAtr.setValue(this.type);
            terminalElement.setAttributeNode(terminalAtr);


            Element serverElement = responseDoc.createElement("server");
            // terminalElement.appendChild(serverElement);

            Attr serverAtr = responseDoc.createAttribute("ip");
            serverAtr.setValue(this.serverIP);
            serverElement.setAttributeNode(serverAtr);

            serverAtr = responseDoc.createAttribute("port");
            serverAtr.setValue(this.serverPort);
            serverElement.setAttributeNode(serverAtr);

            terminalElement.appendChild(serverElement);


            Element transactionResponsesElement = responseDoc.createElement("transactionResponses");
            terminalElement.appendChild(transactionResponsesElement);


            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource domsource = new DOMSource(responseDoc);
            StreamResult streamResult = new StreamResult(this.responseFilePath);

            transformer.transform(domsource, streamResult);


        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }

    public void updateTerminalSettingsFile(ServerResponse serverResponse) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            File xmlFile = new File(this.responseFilePath);
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document docXML = documentBuilder.parse(xmlFile);
            docXML.getDocumentElement().normalize();

            addElement(docXML, serverResponse.getTransaction().getId(), serverResponse.isDoneCorrectly());

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

    private void addElement(Document docXML, String transactionID, boolean status) {
        NodeList transactionResponses = docXML.getElementsByTagName("transactionResponses");
        Element transactionResponse = null;

        //loop for each employee
        for (int i = 0; i < transactionResponses.getLength(); i++) {
            transactionResponse = (Element) transactionResponses.item(i);
            Element responseElement = docXML.createElement("responses");


            Attr responseAtr = docXML.createAttribute("transaction");
            responseAtr.setValue(transactionID);
            responseElement.setAttributeNode(responseAtr);

            responseAtr = docXML.createAttribute("status");
            responseAtr.setValue(Boolean.toString(status));
            responseElement.setAttributeNode(responseAtr);

            transactionResponse.appendChild(responseElement);
        }
    }
}
