package trochoi8so;

public class Operator {
	// 0: Up
	// 1: Down
	// 2: Left
	// 3: Right
	int i;
	public Operator(int i) {
		this.i=i;
	}
	State Move(State s) {
		switch (i) {
		case 0: return Up(s);
		case 1: return Down(s);
		case 2: return Left(s);
		case 3: return Right(s);
		}
		return null;
	}
	private State Up(State s) {
	    MyPoint zero = Pzero(s);
	    if(zero.i==2) return null;
	    State s1 = new State(s.d);
	    s1.d[zero.i][zero.j] = s1.d[zero.i+1][zero.j];
	    s1.d[zero.i+1][zero.j]=0;
		return s1;
	}
	private State Down(State s) {
	    MyPoint zero = Pzero(s);
	    if(zero.i==0) return null;
	    State s1 = new State(s.d);
	    s1.d[zero.i][zero.j] = s1.d[zero.i-1][zero.j];
	    s1.d[zero.i-1][zero.j]=0;
		return s1;
	}
	private State Left(State s) {
	    MyPoint zero = Pzero(s);
	    if(zero.j==2) return null;
	    State s1 = new State(s.d);
	    s1.d[zero.i][zero.j] = s1.d[zero.i][zero.j+1];
	    s1.d[zero.i][zero.j+1]=0;
		return s1;
	}
	private State Right(State s) {
	    MyPoint zero = Pzero(s);
	    if(zero.j==0) return null;
	    State s1 = new State(s.d);
	    s1.d[zero.i][zero.j] = s1.d[zero.i][zero.j-1];
	    s1.d[zero.i][zero.j-1]=0;
		return s1;
	}
	private MyPoint Pzero(State s) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++) {
				if(s.d[i][j]==0) return new MyPoint(i,j);
			}
		return null;
	}
}
class MyPoint {
	int i,j;
	public MyPoint(int i,int j) {
		this.i=i;
		this.j=j;
	}
}
