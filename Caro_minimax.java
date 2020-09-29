

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;

public class Cocarooff extends JFrame implements MouseListener{

	public static void main(String[] args) {
		new Cocarooff();
	}
	
	int n=15,s=30,m=50;
	Vector<Point> dadanh = new Vector<>();
	Random rand = new Random();
	boolean Isend = false;
	public Cocarooff() {
		this.setTitle("Co Caro");
		this.setSize(m*2+n*s,m*2+n*s);
		this.setDefaultCloseOperation(3);
		this.addMouseListener(this);
		
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.BLACK);
		for (int i=0;i<=n;i++) {
			g.drawLine(m, m + i*s, m+n*s, m + i*s);
			g.drawLine(m + i*s, m, m + i*s,  m+n*s);
		}
		
		g.setFont(new Font("arial", Font.BOLD, s));
		for (int i=0;i<dadanh.size();i++) {
			String st ="o";
			if (i%2!=0) st = "x";
			
			int x = m+ dadanh.get(i).x*s + s/2 - s/4;
			int y = m+ dadanh.get(i).y*s + s/2 + s/4;
			
			g.drawString(st, x, y);
		}
		int v1 = Value(1);
		int v2 = Value(2);
		g.drawString("o:"+Value(1),50,50);
		g.drawString("x:"+Value(2),300,50);
		if(v1==5||v2==5) Isend = true;
		if(Isend) g.drawString("Finish!",200,50);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(Isend) return ;
		int x = e.getX();
		int y = e.getY();
		
		System.out.println(x+","+y);
		if (x<m || x>=m+s*n) return;
		if (y<m || y>=m+s*n) return;
		
		int ix = (x-m)/s;
		int iy = (y-m)/s;
		
		for (Point p : dadanh) {
			if (ix==p.x && iy==p.y) return;
		}
		
		dadanh.add(new Point(ix,iy));
		
		this.repaint();
		// AI
		Point pai = null;
		/*boolean ok = true;
		loop: do {
			pai = new Point(rand.nextInt(n),rand.nextInt(n));
			for(Point point: dadanh) {
				if(pai.x==point.x&&pai.y==point.y) continue loop;
			}
			break;
		} while(ok);*/
		Node node = new Node();
		Minimax(node,4,true);
		for(Node child: node.con) {
			if(child.value == node.value) {
				dadanh.add(child.p);
				break;
			}
		}
		dadanh.add(pai);
		this.repaint();
		
	}
	int Minimax(Node node, int d, boolean MaxP) {
		if(EndNode(node)|| d==0) {
			node.value = Value(2)-Value(1);
			return node.value;
			}
	 // node.con ???
		if(MaxP) {
			int m = Integer.MIN_VALUE;
			for(Node child: node.con)
				m = Math.max(m, Minimax(child,d-1,!MaxP));
				node.value=m;
				return node.value;
		} else {
			int m = Integer.MAX_VALUE;
			for(Node child:node.con)
				m = Math.min(m, Minimax(child,d-1,!MaxP));
				node.value=m;
				return node.value;
		}	
	}
	boolean EndNode(Node node) {
		if(dadanh.size()==n*n) return true;
		if(Value(1)==5||Value(2)==5) return true;
		return false;
		
	}
	int DP(int [][] a,int player, int dx,int dy) {
		int [][] dp = new int [n][n]; 
		int max =0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++) {
				if (a[i][j]==player) { 
					if (i+dx>=0 && i+dx<n && j+dy>=0 && j+dy<n)dp[i][j] = dp[i+dx][j+dy]+1;
				else dp[i][j]=1;
					if(max<dp[i][j]) max = dp[i][j];
				}
			}
		return max;
	}
	Integer Value(int player) {
		int [][] a = new int[n][n];
		for(int i=0;i<dadanh.size();i++) {
			a[dadanh.get(i).x][dadanh.get(i).y] = i%2 +1 ;
		}
		int max = 0;
		max = Math.max(max, DP(a,player,-1,0));
		max = Math.max(max, DP(a,player,0,-1));
		max = Math.max(max, DP(a,player,-1,-1));
		max = Math.max(max, DP(a,player,-1,1));
		// Tinh xem co truong hop thang nao hay khong 
		return max;
	}
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
