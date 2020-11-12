package mountain;

public class Side {
	private Point a;
	private Point b;
	
	public Side(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		
		if(!(o instanceof Side)) {
			return false;
		}
		
		Side c = (Side) o;
		
		return a.equals(c.a) && b.equals(c.b) || a.equals(c.b) && b.equals(c.a);
	}
}
