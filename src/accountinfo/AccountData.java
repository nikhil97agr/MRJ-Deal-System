
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
    private String mobile;
    private String email;

    public AccountData(String name, String address, String pan, boolean isSelf, String mobile, String email) {
        this.name = name;
        this.address = address;
        this.pan = pan;
        this.isSelf = isSelf;
        this.id = 1;
        this.email = email;
        this.mobile = mobile;

    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String toString()
    {
        return "{ Name :"+name+", Address :"+address+", Pan :"+pan+", Mobile :"+mobile+", Email :"+email;
    }
    
    

   

}
