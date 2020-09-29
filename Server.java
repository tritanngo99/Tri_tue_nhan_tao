

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {

	public static void main(String[] args) {
		new Server();
	}
	Socket s1, s2;
	int n=15;
	Vector<Point> dadanh = new Vector<>();
	public Server(){
		try {
			ServerSocket server = new ServerSocket(5000);
			s1 = server.accept();
			new Xuly(this,s1).start();
			s2 = server.accept();
			new Xuly(this,s2).start();
		}catch (Exception e) {
		}
	}
}

class Xuly extends Thread{
	Server server;
	Socket s;
	public Xuly(Server server, Socket s) {
		this.server = server;
		this.s = s;
	}
	public void run() {
		//Xuly
		loop:while(true) {
			try {
				DataInputStream dis = new DataInputStream(s.getInputStream());	
				int ix = Integer.parseInt(dis.readUTF());
				int iy = Integer.parseInt(dis.readUTF());
				System.out.println(ix+","+iy);
				
				if (server.s1==null || server.s2==null) continue;
				
				if (!((s == server.s1 && server.dadanh.size()%2==0) || 
						(s == server.s2 && server.dadanh.size()%2==1)))
							continue;
				
				//Kiem tra tinh hop le
				//O trung
				for (Point p : server.dadanh) {
					if (ix == p.x && iy == p.y) continue loop;
				}
				
				
				server.dadanh.add(new Point(ix,iy));
				
				//Gui toa do cho ca 2 client
				DataOutputStream dos1 = new DataOutputStream(server.s1.getOutputStream());
				dos1.writeUTF(ix+"");
				dos1.writeUTF(iy+"");
				
				DataOutputStream dos2 = new DataOutputStream(server.s2.getOutputStream());
				dos2.writeUTF(ix+"");
				dos2.writeUTF(iy+"");
			} catch (Exception e) {
			}
		}
	}
}
