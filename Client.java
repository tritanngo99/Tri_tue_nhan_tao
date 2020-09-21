import java.io.DataInputStream;
import java.net.Socket;
import java.util.Date;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100000;i++) {
			new Luong(i).start();
		}
	}
}
class Luong extends Thread{
	int i;
	public Luong(int i) {
		this.i=i;
	}
    public void run() {
    	try {
			Socket soc = new Socket("localhost",5000);
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			Date d = new Date();
			System.out.println(i+","+dis.readUTF());
			
		} catch(Exception e) {
			
		}
    }	
}
