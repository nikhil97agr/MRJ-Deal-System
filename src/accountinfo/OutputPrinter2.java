/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountinfo;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 * @author RIDDLE
 */
public class OutputPrinter2 implements Printable{
    ArrayList<DealData> printList;
    ArrayList<AccountData> accList;
    
    public OutputPrinter2(ArrayList<DealData> printList, ArrayList<AccountData> accList)
    {
        this.printList = printList;
        this.accList = accList;
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if(4*pageIndex>=printList.size())
        {
            return NO_SUCH_PAGE;
        }
        DealData data = printList.get(4*pageIndex);
        AccountData fromAccount = accList.stream().filter(acc->acc.getName().equals(data.getFromName())).collect(Collectors.toList()).get(0);
        AccountData toAccount = accList.stream().filter(acc->acc.getName().equals(data.getToName())).collect(Collectors.toList()).get(0);
        AccountData brokerAccount = accList.stream().filter(acc->acc.getName().equals(data.getBrokerName())).collect(Collectors.toList()).get(0);
        Font heading = new Font("Arial Rounded MT", Font.BOLD, 17);
        Font font = new Font("Arial Rounded MT", Font.PLAIN, 10);
        Font font1 = new Font("Arial Rounded MT", Font.BOLD, 10);
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
        String line11 = " Ph. Off. 2701976-77 Resi.2402500 Mob.94250-65542, 7000877576, 99260-10008";
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
        String line3 = "Address:  " + fromAccount.getAddress();
        while (line3.length() < 125) {
            line3 += " ";
        }
        line3 = line3 + "PAN No.: " + toAccount.getPan();
        g.drawString(line3, x, y);
        y += lineHeight + 5;

        String line41 = "Amount \u20B9" + data.getAmount();

        while (line41.length() < 30) {
            line41 += " ";
        }
        String duration[] = calculateDuration(data).split("-");
        System.out.println(duration);
        String line42 = "Month " + duration[0]+"  Days "+duration[1];
        while (line42.length() < 30) {
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
        line5 += "To: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(data.getToDate()));
        while(line5.length()<62)
        {
            line5+=" ";
        }
        line5 += "Broker PAN No.: "+brokerAccount.getPan();
        g.drawString(line5, x, y);

        y += lineHeight + 5;

        String line61 = "Broker: "+data.getBrokerName();
        while (line61.length() < 50) {
            line61 += " ";
        }

        String line62 = "Broker Amount \u20B9" + data.getBrokerNet();


        String line6 = line61 + line62;

        g.drawString(line6, x, y);
        y+=lineHeight+5;
        String line8 = "Broker Address: "+brokerAccount.getAddress();
        g.drawString(line8, x, y);
        y += lineHeight + 5;
        String line7 = "Mr. " + data.getToName();
        g.drawString(line7, x, y);

        g.drawRect(initX, initY - 10, initX + 540, y + 5);
        int height = y+5;
        //receipt 2
        if(4*pageIndex+1>=printList.size())
            return PAGE_EXISTS;
        DealData data1 = printList.get(4*pageIndex+1);
        AccountData fromAccount1 = accList.stream().filter(acc->acc.getName().equals(data.getFromName())).collect(Collectors.toList()).get(0);
        AccountData toAccount1 = accList.stream().filter(acc->acc.getName().equals(data.getToName())).collect(Collectors.toList()).get(0);
        AccountData brokerAccount1 = accList.stream().filter(acc->acc.getName().equals(data.getBrokerName())).collect(Collectors.toList()).get(0);

        x = 0;
        x += 10;
        y += 50;
        initX = x;
        initY = y;
         amount = data1.getAmount();
         interest = data1.getInterest();
         tds = data1.getTds();
         net = data1.getNet();
        amount = applyCommas(amount);
        interest = applyCommas(interest);
        tds = applyCommas(tds);
        net = applyCommas(net);

        x += 5;
        y += 10;
        line1 = "             MRJ";
        g.setFont(heading);
        g.drawString(line1, x, y);
         line11 = " Ph. Off. 2701976-77 Resi.2402500 Mob.94250-65542, 7000877576, 99260-10008";
        g.setFont(font2);

        g.drawString(line11, x + 6 * line1.length(), y);
        g.setFont(font);
        y += 5;
        g.drawLine(initX, y, initX + 545, y);
        y += lineHeight + 5;
        g.setFont(font);
        line2 = "Giver: " + data1.getFromName();
        g.drawString(line2, x, y);
        y += lineHeight + 5;
         line3 = "Address:  " + fromAccount1.getAddress();
        while (line3.length() < 125) {
            line3 += " ";
        }
        line3 = line3 + "PAN No.: " + toAccount1.getPan();
        g.drawString(line3, x, y);
        y += lineHeight + 5;

        line41 = "Amount \u20B9" + data1.getAmount();

        while (line41.length() < 30) {
            line41 += " ";
        }
        duration = calculateDuration(data1).split("-");
         line42 = "Month " + duration[0]+"  Days "+duration[1];
        while (line42.length() < 20) {
            line42 += " ";
        }

         line43 = "Rate " + data1.getRate() + "%";
        while (line43.length() < 20) {
            line43 += " ";
        }

         line44 = "Interest \u20B9" + data1.getInterest();
        while (line44.length() < 30) {
            line44 += " ";
        }

         line45 = "TDS ₹" + data1.getTds();

        line4 = line41 + line42 + line43 + line44 + line45;
        g.drawString(line4, x, y);

        y += lineHeight + 5;

         line5 = "From: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(data1.getFromDate()));
        while (line5.length() < 30) {
            line5 += " ";
        }
        line5 += "To: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(data1.getToDate()));
        while(line5.length()<62)
        {
            line5+=" ";
        }
        line5 += "Broker PAN No.: "+brokerAccount1.getPan();
        g.drawString(line5, x, y);

        y += lineHeight + 5;

         line61 = "Broker: "+data1.getBrokerName();
        while (line61.length() < 50) {
            line61 += " ";
        }

         line62 = "Broker Amount \u20B9" + data1.getBrokerNet();


         line6 = line61 + line62;

        g.drawString(line6, x, y);
        y+=lineHeight+5;
        line8 = "Broker Address: "+brokerAccount1.getAddress();
        g.drawString(line8, x, y);
        y += lineHeight + 5;
         line7 = "Mr. " + data1.getToName();
        g.drawString(line7, x, y);
        g.drawRect(initX, initY - 10, initX + 540,height );
        
        //receipt 3
        if(4*pageIndex+2>=printList.size())
            return PAGE_EXISTS;
        DealData data2 = printList.get(4*pageIndex+2);
        AccountData fromAccount2 = accList.stream().filter(acc->acc.getName().equals(data.getFromName())).collect(Collectors.toList()).get(0);
        AccountData toAccount2 = accList.stream().filter(acc->acc.getName().equals(data.getToName())).collect(Collectors.toList()).get(0);
        AccountData brokerAccount2 = accList.stream().filter(acc->acc.getName().equals(data.getBrokerName())).collect(Collectors.toList()).get(0);

        x = 0;
        x += 10;
        y += 50;
        initX = x;
        initY = y;
         amount = data2.getAmount();
         interest = data2.getInterest();
         tds = data2.getTds();
         net = data2.getNet();
        amount = applyCommas(amount);
        interest = applyCommas(interest);
        tds = applyCommas(tds);
        net = applyCommas(net);

        x += 5;
        y += 10;
        line1 = "             MRJ";
        g.setFont(heading);
        g.drawString(line1, x, y);
         line11 = " Ph. Off. 2701976-77 Resi.2402500 Mob.94250-65542, 7000877576, 99260-10008";
        g.setFont(font2);

        g.drawString(line11, x + 6 * line1.length(), y);
        g.setFont(font);
        y += 5;
        g.drawLine(initX, y, initX + 545, y);
        y += lineHeight + 5;
        g.setFont(font);
        line2 = "Giver: " + data2.getFromName();
        g.drawString(line2, x, y);
        y += lineHeight + 5;
         line3 = "Address:  " + fromAccount2.getAddress();
        while (line3.length() < 125) {
            line3 += " ";
        }
        line3 = line3 + "PAN No.: " + toAccount2.getPan();
        g.drawString(line3, x, y);
        y += lineHeight + 5;

        line41 = "Amount \u20B9" + data2.getAmount();

        while (line41.length() < 30) {
            line41 += " ";
        }
        duration = calculateDuration(data2).split("-");
         line42 = "Month " + duration[0]+"  Days "+duration[1];
        while (line42.length() < 20) {
            line42 += " ";
        }

         line43 = "Rate " + data2.getRate() + "%";
        while (line43.length() < 20) {
            line43 += " ";
        }

         line44 = "Interest \u20B9" + data2.getInterest();
        while (line44.length() < 30) {
            line44 += " ";
        }

         line45 = "TDS ₹" + data2.getTds();

        line4 = line41 + line42 + line43 + line44 + line45;
        g.drawString(line4, x, y);

        y += lineHeight + 5;

         line5 = "From: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(data2.getFromDate()));
        while (line5.length() < 30) {
            line5 += " ";
        }
        line5 += "To: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(data2.getToDate()));
        while(line5.length()<62)
        {
            line5+=" ";
        }
        line5 += "Broker PAN No.: "+brokerAccount2.getPan();
        g.drawString(line5, x, y);

        y += lineHeight + 5;

         line61 = "Broker: "+data2.getBrokerName();
        while (line61.length() < 50) {
            line61 += " ";
        }

         line62 = "Broker Amount \u20B9" + data2.getBrokerNet();


         line6 = line61 + line62;

        g.drawString(line6, x, y);
        y+=lineHeight+5;
        line8 = "Broker Address: "+brokerAccount2.getAddress();
        g.drawString(line8, x, y);
        y += lineHeight + 5;
         line7 = "Mr. " + data2.getToName();
        g.drawString(line7, x, y);
        
        g.drawRect(initX, initY - 10, initX + 540,height );
        

        
        //receipt 4
        if(4*pageIndex+3>=printList.size())
            return PAGE_EXISTS;
        
        DealData data3 = printList.get(4*pageIndex+3);
        AccountData fromAccount3 = accList.stream().filter(acc->acc.getName().equals(data.getFromName())).collect(Collectors.toList()).get(0);
        AccountData toAccount3 = accList.stream().filter(acc->acc.getName().equals(data.getToName())).collect(Collectors.toList()).get(0);
        AccountData brokerAccount3 = accList.stream().filter(acc->acc.getName().equals(data.getBrokerName())).collect(Collectors.toList()).get(0);

        x = 0;
        x += 10;
        y += 50;
        initX = x;
        initY = y;
         amount = data1.getAmount();
         interest = data1.getInterest();
         tds = data1.getTds();
         net = data1.getNet();
        amount = applyCommas(amount);
        interest = applyCommas(interest);
        tds = applyCommas(tds);
        net = applyCommas(net);

        x += 5;
        y += 10;
        line1 = "             MRJ";
        g.setFont(heading);
        g.drawString(line1, x, y);
         line11 = " Ph. Off. 2701976-77 Resi.2402500 Mob.94250-65542, 7000877576, 99260-10008";
        g.setFont(font2);

        g.drawString(line11, x + 6 * line1.length(), y);
        g.setFont(font);
        y += 5;
        g.drawLine(initX, y, initX + 545, y);
        y += lineHeight + 5;
        g.setFont(font);
        line2 = "Giver: " + data3.getFromName();
        g.drawString(line2, x, y);
        y += lineHeight + 5;
         line3 = "Address:  " + fromAccount3.getAddress();
        while (line3.length() < 125) {
            line3 += " ";
        }
        line3 = line3 + "PAN No.: " + toAccount3.getPan();
        g.drawString(line3, x, y);
        y += lineHeight + 5;

        line41 = "Amount \u20B9" + data3.getAmount();

        while (line41.length() < 30) {
            line41 += " ";
        }
        duration = calculateDuration(data3).split("-");
         line42 = "Month " + duration[0]+"  Days "+duration[1];
        while (line42.length() < 20) {
            line42 += " ";
        }

         line43 = "Rate " + data3.getRate() + "%";
        while (line43.length() < 20) {
            line43 += " ";
        }

         line44 = "Interest \u20B9" + data3.getInterest();
        while (line44.length() < 30) {
            line44 += " ";
        }

         line45 = "TDS ₹" + data3.getTds();

        line4 = line41 + line42 + line43 + line44 + line45;
        g.drawString(line4, x, y);

        y += lineHeight + 5;

         line5 = "From: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(data3.getFromDate()));
        while (line5.length() < 30) {
            line5 += " ";
        }
        line5 += "To: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(data3.getToDate()));
        while(line5.length()<62)
        {
            line5+=" ";
        }
        line5 += "Broker PAN No.: "+brokerAccount3.getPan();
        g.drawString(line5, x, y);

        y += lineHeight + 5;

         line61 = "Broker: "+data3.getBrokerName();
        while (line61.length() < 50) {
            line61 += " ";
        }

         line62 = "Broker Amount \u20B9" + data3.getBrokerNet();


         line6 = line61 + line62;

        g.drawString(line6, x, y);
        y+=lineHeight+5;
        line8 = "Broker Address: "+brokerAccount3.getAddress();
        g.drawString(line8, x, y);
        y += lineHeight + 5;
         line7 = "Mr. " + data3.getToName();
        g.drawString(line7, x, y);
       
        g.drawRect(initX, initY - 10, initX + 540,height );


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

    public String calculateDuration(DealData data) {
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

        return String.valueOf(month)+"-"+day;

    }
    
}
