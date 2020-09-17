package game15so;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class SearchA {

	public static void main(String[] args) {
		State Start,Goal;
		State O = null;
		PriorityQueue<State> Open = new PriorityQueue<>(new Comparator<State>() {
			public int compare(State o1, State o2) {
				return (o1.g+o1.h)-(o2.g+o2.h);
			}
		});
		//List<State> Open = new ArrayList<>();
		//List<State> Closed = new ArrayList<>();
		Map<String, State> Closed = new HashMap<>();
		
		Goal = new State();
		Start = new State();
		Random rand = new Random();
		for (int i=0;i<10;i++) {
			Operator op = new Operator(rand.nextInt(4));
			State n = op.Move(Start); 
			if (n!=null) Start = n;
		}
		Goal.print();
		Start.print();
		//1.
		Open.add(Start);
		Closed.put(Start.getKey(), Start);
		//2-6
		int count = 0;
		while (Open.size()!=0) {
			//3
			count++;
			O = Open.remove();
			//Closed.add(O);
			
			
			//4
			if (BangNhau(O,Goal)) break;
			//5
			for (int i=0;i<4;i++) {
				Operator op = new Operator(i);
				State Child = op.Move(O);
				
				if (Child==null) continue;
				//if (Thuoc(Open,Child)) continue;
				if (Thuoc(Closed,Child)) continue;
				Child.cha = O;
				Child.me = op;
				Child.g = O.g+1;
				Child.h = Distances(Child,Goal);
				Open.add(Child);
				Closed.put(Child.getKey(),Child);
			}
		}
		System.out.println(count);
		if (BangNhau(O,Goal)) {
			System.out.println("Tim Kiem thanh cong");
			//PrintToTien(O);
		}
		else System.out.println("Tim Kiem That Bai");
	}

	private static int Distances(State x, State goal) {
		int dis = 0;
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++) {
				int so = x.d[i][j];
				if(so==0) continue;
				int i1=(so-1)/4;
				int j1=(so-1)%4;
				dis+=Math.abs(i-i1)+Math.abs(j-j1);
			}
		return dis;
	}

	private static void PrintToTien(State o) {
		if (o.cha!=null) {
			PrintToTien(o.cha);
			System.out.println(o.me.i);
		}
		o.print();
	}

	private static boolean Thuoc(Map<String,State> open, State child) {
		return open.get(child.getKey())!=null;
	}

	private static boolean BangNhau(State o, State goal) {
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++)
				if (o.d[i][j]!=goal.d[i][j]) return false;
		return true;
	}

}
