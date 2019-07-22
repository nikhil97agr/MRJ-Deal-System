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
import java.text.SimpleDateFormat;

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
    String brokerAddress;

    public OutputPrinter(DealData data, String brokerageAccount, String address, String brokersPan, String receiversPan, String brokerAddress) {
        this.data = data;
        this.address = address;
        this.brokerageAccount = brokerageAccount;
        this.brokersPan = brokersPan;
        this.receiversPan = receiversPan;
        this.brokerAddress = brokerAddress;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        // Should only have one page, and page # is zero-based.
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        // Calculate the line height
        Font heading = new Font("Arial Rounded MT", Font.BOLD, 17);
        Font font = new Font("Arial Rounded MT", Font.PLAIN, 10);
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
        g.setFont(font2);

        g.drawString(line11, x + 6 * line1.length(), y);
        g.setFont(font);
        y += 5;
        g.drawLine(initX, y, initX + 545, y);
        y += lineHeight + 5;
        g.setFont(font);
        String line2 = "Giver: " + data.getFromName();

        g.drawString(line2, x, y);
        y += lineHeight + 5;
        String line3 = "Address:  " + address;
        while (line3.length() < 125) {
            line3 += " ";
        }
        line3 = line3 + "PAN No.: " + receiversPan;

        g.drawString(line3, x, y);
        y += lineHeight + 5;

        String line41 = "Amount \u20B9" + data.getAmount();

        while (line41.length() < 30) {
            line41 += " ";
        }

        String line42 = "Month " + calculateDuration();
        while (line42.length() < 20) {
            line42 += " ";
        }

        String line43 = "Rate " + data.getRate() + "%";
        while (line43.length() < 20) {
            line43 += " ";
        }

        String line44 = "Interest \u20B9" + data.getInterest();
        while (line44.length() < 30) {
            line44 += " ";
        }

        String line45 = "TDS ₹" + data.getTds();

        String line4 = line41 + line42 + line43 + line44 + line45;
        g.drawString(line4, x, y);

        y += lineHeight + 5;

        String line5 = "From: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(data.getFromDate()));
        while (line5.length() < 30) {
            line5 += " ";
        }
        line5 += "Broker: " + data.getBrokerName();

        g.drawString(line5, x, y);

        y += lineHeight + 5;

        String line61 = "To: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(data.getToDate()));
        while (line61.length() < 32) {
            line61 += " ";
        }

        String line62 = "Broker Amount \u20B9" + data.getBrokerNet();
        while (line62.length() < 40) {
            line62 += " ";
        }
        line62 += "Broker PAN No.: " + brokersPan;

        String line6 = line61 + line62;

        g.drawString(line6, x, y);
        y+=lineHeight+5;
        String line8 = "Broker Address: "+brokerAddress;
        g.drawString(line8, x, y);
        y += lineHeight + 5;
        String line7 = "Mr. " + data.getToName();
        g.drawString(line7, x, y);

        g.drawRect(initX, initY - 10, initX + 540, y + 5);
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
    
}
