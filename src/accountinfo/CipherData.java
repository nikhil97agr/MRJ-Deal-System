package accountinfo;

/*
 *
 * @author RIDDLE
 * 
 */

public class CipherData {

    public CipherData() {
    }

    public byte[] encrypter(byte[] dataBytes) {
        for (int i = 0; i < dataBytes.length / 2; i += 2) {
            byte temp = dataBytes[i];
            dataBytes[i] = dataBytes[dataBytes.length - 1 - i];
            dataBytes[dataBytes.length - 1 - i] = temp;
        }
        return dataBytes;
    }

    public String decrypter(byte[] dataBytes) {
        for (int i = 0; i < dataBytes.length / 2; i += 2) {
            byte temp = dataBytes[i];
            dataBytes[i] = dataBytes[dataBytes.length - 1 - i];
            dataBytes[dataBytes.length - 1 - i] = temp;
        }
        String data = new String(dataBytes);
        return data;
    }
}
