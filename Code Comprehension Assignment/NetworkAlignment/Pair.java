public class Pair<X, Y> {
	
	private X x;
	private Y y;

	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public X fst() {
		return this.x;
	}

	public Y snd() {
		return this.y;
	}
	
	public void setFst(X x){
		this.x = x;
	}
	
	public void setSnd(Y y){
		this.y = y;
	}
	
	public void shallowCopy(Pair<X, Y> p){
		this.setFst(p.fst());
		this.setSnd(p.snd());
	}

	@Override
	public String toString() {
		
		String output = "<";
		
		if (x == null) output += "null";
		else output += x.toString();
		
		output += ", ";
		
		if (y == null) output += "null";
		else output += y.toString();
		
		output += ">";
		
		return output;
	}
}
