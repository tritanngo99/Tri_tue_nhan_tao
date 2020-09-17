package game15so;

public class State {
	int g;
	int h;
	State cha;
	Operator me;
	int d[][];
	public State(int d[][]) {
		this.d = new int[4][4];
		for (int i=0;i<4;i++) 
			for (int j=0;j<4;j++){
				this.d[i][j] = d[i][j];
			}
	}
	
	public State() {
		d = new int[4][4];
		for (int i=0;i<4;i++) 
			for (int j=0;j<4;j++){
				d[i][j] = (i*4+j+1)%16;
			}
	}
	
	public void print() {
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++){
				System.out.printf("%3d",d[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------");
	}
	
	String getKey() {
		String s = "";
		for (int i=0;i<4;i++) 
			for (int j=0;j<4;j++)
				s+= d[i][j] +10;
		return s;
	}

}
