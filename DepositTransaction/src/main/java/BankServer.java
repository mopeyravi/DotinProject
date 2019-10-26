
import TransactionExceptions.DepositNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class BankServer {
    String IPAddress;
    int port;
    String settingsAndInformationFilePath;
    String outLogFilePath;
    HashMap<String, Deposit> depositsMap;
    ServerSocket serverSocket;
    String depositsOutFilePath = "Resources//deposits.out";
    Map<String, Object> depositLocks;
    Socket requestSocket;


    public BankServer(String settingsAndInformationFilePath) {
        this.settingsAndInformationFilePath = settingsAndInformationFilePath;
        depositLocks = new HashMap<String, Object>();
        depositsMap = new HashMap<String, Deposit>();
        importSettings();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public BankServer(int portNumber, String IPAddress) {

        this.port = portNumber;
        this.IPAddress = IPAddress;
    }


    public BankServer() {
    }

    public BankServer duplicate() {
        BankServer dupServer = new BankServer();
        dupServer.IPAddress = this.IPAddress;
        dupServer.port = this.port;
        dupServer.settingsAndInformationFilePath = this.settingsAndInformationFilePath;
        dupServer.serverSocket = this.serverSocket;
        dupServer.depositsOutFilePath = this.depositsOutFilePath;
        dupServer.depositLocks = this.depositLocks;
        dupServer.outLogFilePath = this.outLogFilePath;
        dupServer.depositsMap=this.depositsMap;
        return dupServer;
    }

    public void importSettings(String settingsFilePath) {
        this.settingsAndInformationFilePath = settingsFilePath;
        depositLocks = new HashMap<String, Object>();
        importSettings();
    }

    public void importSettings() {
        String customer;
        String id;
        BigDecimal initialBalance;
        BigDecimal upperBound;
        try {
            FileReader reader = new FileReader(settingsAndInformationFilePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            port = (int) Integer.parseInt((String) jsonObject.get("port"));


            JSONArray depositsInfo = (JSONArray) jsonObject.get("deposits");
            Iterator depositIterator = depositsInfo.iterator();
            while (depositIterator.hasNext()) {
                JSONObject depositInfo = (JSONObject) depositIterator.next();
                customer = (String) depositInfo.get("customer");
                id = (String) depositInfo.get("id");
                initialBalance = new BigDecimal((String) depositInfo.get("initialBalance"));
                upperBound = new BigDecimal((String) depositInfo.get("upperBound"));
                depositsMap.put(id, new Deposit(customer, id, initialBalance, upperBound));
                depositLocks.put(id, new Object());


            }
            outLogFilePath = (String) jsonObject.get("outLog");


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public void serverWait() {
        FileWriter writer;
        try {
            writer = new FileWriter(this.outLogFilePath, false);
            TerminalRequest request;
            writer.write("Server Starts..\n");
            serverSocket.setSoTimeout(1000);

            writer.close();
            Socket reqSocket;
            while (true) {
                writer = new FileWriter(this.outLogFilePath, true);
                writer.write("Server Is Waiting for Request..\n");
                reqSocket = serverSocket.accept();
                writer.close();

                new Thread(new BankMiniServer(duplicate(), reqSocket), "server").start();

                //new Thread(new BankServer(reqSocket)).start();

            }
        } catch (SocketTimeoutException ex) {
            try {
                Writer writerDeposits = new FileWriter(depositsOutFilePath);
                for (Map.Entry<String, Deposit> depositsEntry : depositsMap.entrySet()) {
                    writerDeposits.write(depositsEntry.getValue().toString());
                }
                writerDeposits.close();
                System.out.println("Server Terminates");
                serverSocket.close();
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    /*
    public void run() {
        FileWriter writer;
        try {
            writer = new FileWriter(this.outLogFilePath, false);
            TerminalRequest request;
            writer.write("Server Starts..\n");
            serverSocket.setSoTimeout(100);
            writer.close();
            Socket reqSocket;
            while (true) {
                writer = new FileWriter(this.outLogFilePath, true);
                writer.write("Server Is Waiting for Request..\n");
                reqSocket = serverSocket.accept();
                writer.write("A Terminal Connect to Server...\t LocalPort : " + reqSocket.getLocalPort() + "\tSocket Info" + reqSocket.toString() + "\n");
                ObjectInputStream socketInLine = new ObjectInputStream(reqSocket.getInputStream());
                request = (TerminalRequest) socketInLine.readObject();
                writer.write("Server Got a Request...\t TerminalID: " + request.getTerminalID() + "\n");
                writer.write("Server Is Doing the Request...\t TerminalID: \t" + request.getTerminalID() + "\tTransactionID:\t" + request.getTransaction().getId() + "\n");
                boolean transDone = doTransaction(request);
                writer.write("Transaction "+ request.getTransaction().getType()+" is Done:\t" + transDone + "\tCorresponding Deposit Info: DepositID: "+request.getTransaction().getDeposit()+"Deposit Current Balance: "+depositsMap.get(request.getTransaction().getDeposit()).getInitialBalance()+"\tTerminalID: " + request.getTerminalID() + "\n");
                ObjectOutputStream socketOutLine = new ObjectOutputStream(reqSocket.getOutputStream());
                socketOutLine.writeObject(new ServerResponse(request.getTransaction(), transDone));
                writer.write("Server Sends Back the Response...\tTerminalID:" + request.getTerminalID() + "\n");
                writer.close();
            }
        } catch (SocketTimeoutException ex) {
            try {
                Writer writerDeposits = new FileWriter(depositsOutFilePath);
                for (Map.Entry<String, Deposit> depositsEntry : depositsMap.entrySet()) {
                    writerDeposits.write(depositsEntry.getValue().toString());
                }
                writerDeposits.close();
                System.out.println("Server Terminates");
                serverSocket.close();
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public boolean doTransaction(TerminalRequest req) {
        Transaction trans=req.getTransaction();
        String depId = trans.getDeposit();
        Random r=new Random();
        if(r.nextInt(10)<5)
        {
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second.
                System.out.println("Terminal ID: " + req.getTerminalID() + " Is Waiting");
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        synchronized (depositLocks.get(depId)) {
            if (depositsMap.containsKey(depId)) {
                Deposit dep = depositsMap.get(depId);

                if (trans.isValid(dep)) {
                    dep = trans.Do(dep);
                    depositsMap.put(depId, dep);
                    return true;
                } else {
                    return false;
                }
            } else {
                try {
                    throw new DepositNotFoundException();

                } catch (DepositNotFoundException ex) {
                    System.out.println(ex.toString());
                    return false;
                }
            }
        }
    }
*/
}
