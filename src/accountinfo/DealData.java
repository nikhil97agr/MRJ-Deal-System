package accountinfo;

/*
 *
 * @author RIDDLE
 * 
 */
public class DealData {

    private String sNo;
    private String date;
    private String fromDate;
    private String toDate;
    private String amount;
    private String rate;
    private String interest;
    private String tds;
    private String net;
    private Status status;
    private int brok;
    private int from;
    private int to;
    private String brokerInterest;
    private String brokerRate;
    private String brokerNet;
    private String brokerTds;
    private String brokerName;
    private String fromName;
    private String toName;
  private boolean isedit;
  

  
    public DealData() {
    }

    public DealData(String sNo, String date, String fromDate, String toDate, String amount, String rate, String interest, String tds, String net, int brok, int from, int to, String brokerName, String fromName, String toName,
                            String brokerRate, String brokerNet, String brokerTds, String brokerInterest) {
        this.sNo = sNo;
        this.date = date;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.amount = amount;
        this.rate = rate;
        this.interest = interest;
        this.tds = tds;
        this.net = net;
        this.status = Status.ACTIVE;
        this.brok = brok;
        this.from = from;
        this.to = to;
        this.brokerName = brokerName;
        this.fromName = fromName;
        this.toName = toName;
        this.brokerRate = brokerRate;
        this.brokerNet =brokerNet;
        this.brokerTds = brokerTds;
        this.brokerInterest = brokerInterest;
    }


    
    

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return sNo + ":" + to + ":" + from + ":" + brok;
    }

    public int getBrok() {
        return brok;
    }

    public void setBrok(int brok) {
        this.brok = brok;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

      public boolean isIsedit() {
        return isedit;
    }

    public void setIsedit(boolean isedit) {
        this.isedit = isedit;
    }

    public String getBrokerInterest() {
        return brokerInterest;
    }

    public void setBrokerInterest(String brokerInterest) {
        this.brokerInterest = brokerInterest;
    }

    public String getBrokerRate() {
        return brokerRate;
    }

    public void setBrokerRate(String brokerRate) {
        this.brokerRate = brokerRate;
    }

    public String getBrokerNet() {
        return brokerNet;
    }

    public void setBrokerNet(String brokerNet) {
        this.brokerNet = brokerNet;
    }

    public String getBrokerTds() {
        return brokerTds;
    }

    public void setBrokerTds(String brokerTds) {
        this.brokerTds = brokerTds;
    }
    
    
}
