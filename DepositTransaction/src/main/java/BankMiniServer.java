import TransactionExceptions.DepositNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by DOTIN SCHOOL 3 on 4/8/2015.
 */
public class BankMiniServer implements Runnable {
    BankServer server;
    private Socket requestSocket;

    public BankMiniServer(BankServer server, Socket requestSocket) {
        this.server = server.duplicate();
        this.requestSocket = requestSocket;
    }

    public void synchronizedRun() {
        FileWriter writer;
        try {
            TerminalRequest request;
            synchronized (new Integer(1)) {
                try {

                    writer = new FileWriter(server.outLogFilePath, true);
                    writer.write("A Terminal Connect to Server...\t LocalPort : " + this.requestSocket.getLocalPort() + "\tSocket Info: " + this.requestSocket.toString() + "\n");
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            ObjectInputStream socketInLine = new ObjectInputStream(this.requestSocket.getInputStream());
            request = (TerminalRequest) socketInLine.readObject();

            synchronized (new Integer(1)) {
                try {

                    writer = new FileWriter(server.outLogFilePath, true);
                    writer.write("Server Got a Request...\t TerminalID: " + request.getTerminalID() + "\tTransactionID:\t" + request.getTransaction().getId() + "\n\t" + "Transaction Type: " + request.getTransaction().getType() + " Amount: " + request.getTransaction().getAmount() + "\tCorresponding Deposit Info: DepositID: " + request.getTransaction().getDeposit() + "Deposit Current Balance: " + server.depositsMap.get(request.getTransaction().getDeposit()).getInitialBalance() + "\n");
                    // writer.write("Server Is Doing the Request...\t TerminalID: \t" + request.getTerminalID() + "\tTransactionID:\t" + request.getTransaction().getId() + "\n");
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            synchronized (request.getTerminalID()) {
                boolean transDone = synchronizedDoTransaction(request);

                synchronized (new Integer(1)) {
                    try {

                        writer = new FileWriter(server.outLogFilePath, true);
                        writer.write("Transaction " + request.getTransaction().getType() + " is Done:\t" + transDone + "\tCorresponding Deposit Info: DepositID: " + request.getTransaction().getDeposit() + " Deposit Current Balance: " + server.depositsMap.get(request.getTransaction().getDeposit()).getInitialBalance() + "\tTerminalID: " + request.getTerminalID() + "\n");
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                ObjectOutputStream socketOutLine = new ObjectOutputStream(this.requestSocket.getOutputStream());
                socketOutLine.writeObject(new ServerResponse(request.getTransaction(), transDone));
                synchronized (new Integer(1)) {
                    try {

                        writer = new FileWriter(server.outLogFilePath, true);
                        writer.write("Server Sends Back the Response To TerminalID: " + request.getTerminalID() + "\tThe TransactionID: " + request.getTransaction().getId() + "\n");
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public void unSynchronizedRun() {
        FileWriter writer;
        Calendar cal = Calendar.getInstance();

        try {
            TerminalRequest request;

            writer = new FileWriter(server.outLogFilePath, true);
            writer.write("A Terminal Connect to Server...\t LocalPort : " + this.requestSocket.getLocalPort() + "\tSocket Info: " + this.requestSocket.toString() +"\tTime: "+cal.getTime()+"\n");
            writer.close();


            ObjectInputStream socketInLine = new ObjectInputStream(this.requestSocket.getInputStream());
            request = (TerminalRequest) socketInLine.readObject();

            writer = new FileWriter(server.outLogFilePath, true);
            writer.write("Server Got a Request...\t TerminalID: " + request.getTerminalID() + "\tTransactionID:\t" + request.getTransaction().getId() + "\tTime: "+cal.getTime() + "\n\t" + "Transaction Type: " + request.getTransaction().getType() + " Amount: " + request.getTransaction().getAmount() + "\tCorresponding Deposit Info: DepositID: " + request.getTransaction().getDeposit() + "Deposit Current Balance: " + server.depositsMap.get(request.getTransaction().getDeposit()).getInitialBalance() +"\n");
            // writer.write("Server Is Doing the Request...\t TerminalID: \t" + request.getTerminalID() + "\tTransactionID:\t" + request.getTransaction().getId() + "\n");
            writer.close();

            boolean transDone = unSynchronizedDoTransaction(request);

            writer = new FileWriter(server.outLogFilePath, true);
            writer.write("Transaction " + request.getTransaction().getType() + " is Done:\t" + transDone + "\tCorresponding Deposit Info: DepositID: " + request.getTransaction().getDeposit() + " Deposit Current Balance: " + server.depositsMap.get(request.getTransaction().getDeposit()).getInitialBalance() + "\tTerminalID: " + request.getTerminalID() + "\tTime: "+cal.getTime()+"\n");
            writer.close();

            ObjectOutputStream socketOutLine = new ObjectOutputStream(this.requestSocket.getOutputStream());
            socketOutLine.writeObject(new ServerResponse(request.getTransaction(), transDone));

            writer = new FileWriter(server.outLogFilePath, true);
            writer.write("Server Sends Back the Response To TerminalID: " + request.getTerminalID() + "\tThe TransactionID: " + request.getTransaction().getId() + "\tTime: "+cal.getTime()+"\n");
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        //synchronizedRun();
        unSynchronizedRun();

    }

    public boolean synchronizedDoTransaction(TerminalRequest req) {
        Transaction trans = req.getTransaction();
        String depId = trans.getDeposit();
        Random r = new Random();
        /*
        if (r.nextInt(10) < 5) {
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second.
                System.out.println("Terminal ID: " + req.getTerminalID() + " Is Waiting");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }*/
        synchronized (server.depositLocks.get(depId)) {
            if (server.depositsMap.containsKey(depId)) {
                Deposit dep = server.depositsMap.get(depId);

                if (trans.isValid(dep)) {
                    dep = trans.Do(dep);
                    server.depositsMap.put(depId, dep);
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

    public boolean unSynchronizedDoTransaction(TerminalRequest req) {
        Transaction trans = req.getTransaction();
        String depId = trans.getDeposit();

        if (server.depositsMap.containsKey(depId)) {
            Deposit dep = server.depositsMap.get(depId);

            if (trans.isValid(dep)) {
                dep = trans.Do(dep);
                server.depositsMap.put(depId, dep);
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
