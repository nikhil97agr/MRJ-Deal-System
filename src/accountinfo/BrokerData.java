
package accountinfo;

/*
 *
 * @author RIDDLE
 * 
 */
public class BrokerData {
    
    
    String brokerName;
    String brokerNet;
    String brokerAmount;
    String brokerInterest;
    String brokerTds;
    String brokerRate;
    String sNo;
    public BrokerData(String sNo,String brokerName, String brokerNet, String brokerAmount, String brokerInterest, String brokerTds, String brokerRate) {
        this.brokerName = brokerName;
        this.brokerNet = brokerNet;
        this.brokerAmount = brokerAmount;
        this.brokerInterest = brokerInterest;
        this.brokerTds = brokerTds;
        this.brokerRate = brokerRate;
        this.sNo = sNo;
    }
    
    public BrokerData()
    {
        
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerNet() {
        return brokerNet;
    }

    public void setBrokerNet(String brokerNet) {
        this.brokerNet = brokerNet;
    }

    public String getBrokerAmount() {
        return brokerAmount;
    }

    public void setBrokerAmount(String brokerAmount) {
        this.brokerAmount = brokerAmount;
    }

    public String getBrokerInterest() {
        return brokerInterest;
    }

    public void setBrokerInterest(String brokerInterest) {
        this.brokerInterest = brokerInterest;
    }

    public String getBrokerTds() {
        return brokerTds;
    }

    public void setBrokerTds(String brokerTds) {
        this.brokerTds = brokerTds;
    }

    public String getBrokerRate() {
        return brokerRate;
    }

    public void setBrokerRate(String brokerRate) {
        this.brokerRate = brokerRate;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }
    
    
    
    
}
