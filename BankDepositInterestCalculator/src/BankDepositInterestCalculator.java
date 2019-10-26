import java.io.IOException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.RandomAccessFile;
import java.util.Collections;

/**
 * @author Fateme Ahmadi-Fakhr
 */
public class BankDepositInterestCalculator {
    public static void main(String args[]) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXHandler handler = new SAXHandler();
        try {

            //get a new instance of parser
            SAXParser saxParser= saxParserFactory.newSAXParser();
            //parse the file and also register this class for call backs
            saxParser.parse("Resources//deposits.xml", handler);

        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }


        Collections.sort(handler.depList);
        try {
            RandomAccessFile outFile = new RandomAccessFile("Resources//PayedInterest.txt", "rw");
            for (Deposit dep : handler.depList) {
                outFile.writeChars(dep.getCustomerNumber() + "#" + dep.getPayedInterest());
                outFile.writeChars("\n");
                System.out.println(dep.getCustomerNumber() + "#" + dep.getPayedInterest());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
