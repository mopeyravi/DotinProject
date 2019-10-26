import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;

class SAXHandler extends DefaultHandler {

    private String terminalID;
    private String terminalType;

    private String serverIP;
    private String serverPort;

    private String outLogPath;

    private ArrayList<Transaction> transactionsList;


    BankTerminal terminal;


    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String transactionID;
        String transactionType;
        BigDecimal transactionAmount;
        String transactionDeposit;

        if (qName.equals("terminal")) {
            terminalID = attributes.getValue("id");
            terminalType = attributes.getValue("type");
        } else if (qName.equals("server")) {
            serverIP = attributes.getValue("ip");
            serverPort = attributes.getValue("port");

        } else if (qName.equals("outLog")) {
            outLogPath = attributes.getValue("path");

        } else if (qName.equals("transactions")) {
            transactionsList = new ArrayList<Transaction>();

        } else if (qName.equals("transaction")) {
            transactionID = attributes.getValue("id");
            transactionType = attributes.getValue("type");
            transactionAmount = new BigDecimal(attributes.getValue("amount"));
            transactionDeposit = attributes.getValue("deposit");
            transactionsList.add(new Transaction(transactionID, transactionType, transactionAmount, transactionDeposit));
        }

    }

    SAXHandler() {
    }

    public void characters(char[] ch, int start, int length) throws SAXException {

        //content = String.copyValueOf(ch, start, length).trim();
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("terminal")) {
            terminal = new BankTerminal(terminalID, terminalType, serverPort, serverIP, outLogPath, transactionsList);
        }
    }

}
