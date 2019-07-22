package accountinfo;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;

/*
 *
 * @author RIDDLE
 * 
 */
public class OutputPrinter implements Printable {

    DealData data;
    String brokerageAccount;
    String brokersPan;
    String receiversPan;
    String address;

    public OutputPrinter(DealData data, String brokerageAccount, String address, String brokersPan, String receiversPan) {
        this.data = data;
        this.address = address;
        this.brokerageAccount = brokerageAccount;
        this.brokersPan = brokersPan;
        this.receiversPan = receiversPan;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        // Should only have one page, and page # is zero-based.
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        // Calculate the line height
        Font heading = new Font("Arial Rounded MT", Font.BOLD, 17);
        Font font = new Font("Arial Rounded MT", Font.PLAIN, 14);
        Font font2 = new Font("Arial Rounded MT", Font.PLAIN, 12);
        FontMetrics metrics = graphics.getFontMetrics(font);
        int lineHeight = metrics.getHeight();

        Graphics2D g = (Graphics2D) graphics;
        int x = 0;
        int y = 0;
        x += 10;
        y += 20;
        int initX = x;
        int initY = y;
        g.translate(x, y);
        String amount = data.getAmount();
        String interest = data.getInterest();
        String tds = data.getTds();
        String net = data.getNet();
        amount = applyCommas(amount);
        interest = applyCommas(interest);
        tds = applyCommas(tds);
        net = applyCommas(net);

        x += 5;
        y += 10;
        String line1 = "             MRJ";
        g.setFont(heading);
        g.drawString(line1, x, y);
        String line11 = " Ph. Off. 2701976-77 Resi.2402500 Mob.94250-65542, 99260-10008";
        g.setFont(font);

        g.drawString(line11, x + 6 * line1.length(), y);

        y += 5;
        g.drawLine(initX, y, initX + 540, y);
        y += lineHeight + 5;
        g.setFont(font);
        String line2 = "Receiver: " + data.getToName();

        g.drawString(line2, x, y);
        y += lineHeight + 5;
        String line3 = "Address:  " + address;
        while (line3.length() < 80) {
            line3 += " ";
        }
        line3 = line3 + "PAN No.: " + receiversPan;

        g.drawString(line3, x, y);
        y += lineHeight + 5;

        String line41 = "Amount ₹" + data.getNet();

        while (line41.length() < 22) {
            line41 += " ";
        }

        String line42 = "Month " + calculateDuration();
        while (line42.length() < 15) {
            line42 += " ";
        }

        String line43 = "Rate " + data.getRate() + "%";
        while (line43.length() < 15) {
            line43 += " ";
        }

        String line44 = "Interest ₹" + data.getInterest();
        while (line44.length() < 22) {
            line44 += " ";
        }

        String line45 = "TDS ₹" + data.getTds();

        String line4 = line41 + line42 + line43 + line44 + line45;
        g.drawString(line4, x, y);

        y += lineHeight + 5;

        String line5 = "From: " + data.getFromDate();
        while (line5.length() < 22) {
            line5 += " ";
        }
        line5 += "Broker: " + data.getBrokerName();

        g.drawString(line5, x, y);

        y += lineHeight + 5;

        String line61 = "To: " + data.getToDate();
        while (line61.length() < 24) {
            line61 += " ";
        }

        String line62 = "Broker Amount ₹" + data.getBrokerNet();
        while (line62.length() < 30) {
            line62 += " ";
        }
        line62 += "PAN No.: " + brokersPan;

        String line6 = line61 + line62;

        g.drawString(line6, x, y);

        y += lineHeight + 5;
        String line7 = "Mr. " + data.getFromName();
        g.drawString(line7, x, y);

        g.drawRect(initX, initY - 10, initX + 540, y + 10);
        return PAGE_EXISTS;

    }

    private String applyCommas(String number) {
        String result = "";
        int count = 0;
        boolean first = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            if (number.charAt(i) == '.') {
                first = false;
                if (result.startsWith(",")) {
                    result = result.substring(1);
                }
                result = "." + result;
                count = 0;
                continue;
            }
            count++;
            result = number.charAt(i) + result;
            if (count == 3 && !first) {
                first = true;
                count = 0;
                result = "," + result;
            }
            if (count == 2 && first) {
                count = 0;
                result = "," + result;
            }
        }
        if (result.startsWith(",")) {
            result = result.substring(1);
        }
        return result;
    }

    public String calculateDuration() {
        Date newDate = new Date(data.getToDate());
        Date oldDate = new Date(data.getFromDate());
        if (oldDate == null || newDate == null) {
            return null;
        }
        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();
        fromDate.setTime(oldDate);
        toDate.setTime(newDate);
        int increment = 0;
        int year, month, day;
        if (fromDate.get(Calendar.DAY_OF_MONTH) > toDate.get(Calendar.DAY_OF_MONTH)) {
            increment = fromDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // DAY CALCULATION
        if (increment != 0) {
            day = (toDate.get(Calendar.DAY_OF_MONTH) + increment) - fromDate.get(Calendar.DAY_OF_MONTH);
            increment = 1;
        } else {
            day = toDate.get(Calendar.DAY_OF_MONTH) - fromDate.get(Calendar.DAY_OF_MONTH);
        }

        // MONTH CALCULATION
        if ((fromDate.get(Calendar.MONTH) + increment) > toDate.get(Calendar.MONTH)) {
            month = (toDate.get(Calendar.MONTH) + 12) - (fromDate.get(Calendar.MONTH) + increment);
            increment = 1;
        } else {
            month = (toDate.get(Calendar.MONTH)) - (fromDate.get(Calendar.MONTH) + increment);
            increment = 0;
        }

        // YEAR CALCULATION
        year = toDate.get(Calendar.YEAR) - (fromDate.get(Calendar.YEAR) + increment);

        month = month + year * 12;

        return String.valueOf(month);

    }

    //old format
    //        g.drawString(line1, x + 160, y);    //MRJ
//        y += 5;
//        g.drawLine(initX, y, initX + 410, y);   //separator
//        y += lineHeight + 5;
//        font = new Font("Arial Rounded MT", Font.PLAIN, 12);
//        g.setFont(font);
//        g.drawString(line3, x, y);
//        g.drawString(line5, x + 250, y);
//        y += lineHeight + 5;
//        g.drawString(line2, x+250, y);
//        g.drawString(line10, x, y);
//        y += lineHeight + 5;
//        g.drawString(line12, x, y);
//        font = new Font("Arial Rounded MT", Font.BOLD, 12);
//        g.setFont(font);
//
//   
//        font = new Font("Arial Rounded MT", Font.PLAIN, 12);
//        g.setFont(font);
//        g.drawString(line4, x + 250, y);
//
//        y += lineHeight + 5;
//        g.drawString(line11, x, y);
//        g.drawString(line6, x + 250, y);
//
//        y += lineHeight + 5;
//        g.drawString(line13, x, y);
//        g.drawString(line8, x + 250, y);
//
//        y += lineHeight + 5;
//        g.drawString(line7,x, y);
//        y += 2 * lineHeight;
//
//        g.drawString(line9, x, y);
//        
}
