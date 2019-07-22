
package accountinfo;

/*
 *
 * @author RIDDLE
 * 
 */
public class AccountData {

    private String name;
    private String address;
    private String pan;
    private boolean isSelf;
    private int id;
    private int index = -1;

    public AccountData(String name, String address, String pan, boolean isSelf) {
        this.name = name;
        this.address = address;
        this.pan = pan;
        this.isSelf = isSelf;
        this.id = 1;

    }
    
    

    public AccountData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public boolean isIsSelf() {
        return isSelf;
    }

    public void setIsSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    

    @Override
    public String toString() {
        return name + ":" + address + ":" + pan + ":" + isSelf + ":" + id;
    }

}
