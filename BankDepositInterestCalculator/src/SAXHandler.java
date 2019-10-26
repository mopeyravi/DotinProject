import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class SAXHandler extends DefaultHandler {
    List<Deposit> depList = new ArrayList<Deposit>(); //Array List to store created BankDeposit Objects

    Deposit dep;
    String content;

    /**
     * Variables to store Fields of each BankDeposit object before creating it.
     */
    String customerNumber;
    String depositType;
    BigDecimal depositBalance;
    int durationDays;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("deposit")) {
            dep = new Deposit();
        }
    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("deposit")) {

/*
                depCons = depType.getConstructor(int.class, BigDecimal.class, int.class);
                //depCons = depType.getConstructor();
                Object depTypeObj = depTypeCons.newInstance(customerNumber, depositBalance, durationDays);
                Method method1 = depType.getDeclaredMethod("calculatePayedInterest");
                method1.invoke(depTypeObj);
*/
            if (!isValidDepositType()) {
                try {
                    throw new InvalidDepositTypeException();
                } catch (InvalidDepositTypeException invalidDepositType) {
                    System.out.println(invalidDepositType.toString());
                }
            } else if (depositBalance.compareTo(BigDecimal.ZERO) < 0) {
                try {
                    throw new BellowZeroDepositBalanceException();
                } catch (BellowZeroDepositBalanceException bellowZero) {
                    System.out.println(bellowZero.toString());
                }
            } else if (durationDays <= 0) {
                try {
                    throw new InvalidDurationDaysException();
                } catch (InvalidDurationDaysException invalidDurationDay) {
                    System.out.println(invalidDurationDay.toString());
                }
            } else {
                Class depTypeClass;
                try {
                    //cast the object into the BankDeposit one in order to add it to the ArrayList
                    depTypeClass = Class.forName(depositType);
                    DepositType depType = (DepositType) depTypeClass.newInstance();
                    Deposit dep = new Deposit(customerNumber, depositBalance, durationDays, depType);
                    dep.calculatePayedInterest();
                    depList.add(dep);
                } catch (ClassNotFoundException e) {
                    System.out.println(customerNumber + "# " + depositType + " Is Not a Valid Deposit type!!!");
                    // e.printStackTrace();
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace();
                } catch (InstantiationException ie) {
                    ie.printStackTrace();
                }
            }

        } else if (qName.equals("customerNumber")) {
            customerNumber = content;
        } else if (qName.equals("depositType")) {
            depositType = content;
        } else if (qName.equals("depositBalance")) {
            depositBalance = new BigDecimal(content);

        } else if (qName.equals("durationInDays")) {
            durationDays = Integer.valueOf(content);
        }
    }

    public boolean isValidDepositType() {
        return (depositType.equals("LongTerm") || depositType.equals("Qarz") || depositType.equals("ShortTerm"));
    }
}
