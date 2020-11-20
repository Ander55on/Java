package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

	private Point pointA;
	private Point pointB;
	private Point pointC;
	private HashMap<Side, Point> map;
	private double deviation;
	
	public Mountain(Point pointA, Point pointB, Point pointC, double deviation) {
		super();
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
		this.deviation = deviation;
		this.map = new HashMap<>();
		
	}
	
	@Override
	public String getTitle() {
		return  "Mountain fractal";
	}

	@Override
	public void draw(TurtleGraphics g) {
		// TODO Auto-generated method stub
		g.penDown();
		fractalTriangle(g, pointA, pointB, pointC, order, this.deviation);
				
	}
	
	private void fractalTriangle(TurtleGraphics g, Point a, Point b, Point c, int order, double dev) {
		int countSideA = 0;
		int countSideB = 0; 
		int countSideC = 0; 
		
		if(order == 0) {
			g.moveTo(a.getX(), a.getY());
			g.forwardTo(b.getX(), b.getY());
			g.forwardTo(c.getX(), c.getY());
			g.forwardTo(a.getX(), a.getY());
		} else {
		
			Point p1;
			Point p2;
			Point p3;
			
			if(map.containsKey(new Side(a,b))) {
				p1 = map.get(new Side(a,b));
				countSideA++;
				if(countSideA >= 2) 
					map.remove(new Side(a,b));					
			} else {
				p1 = new Point((int)(0.5*(a.getX() + b.getX())), (int)((0.5*(a.getY() + b.getY())) + RandomUtilities.randFunc(dev)));
				map.put(new Side(a,b), p1);
			}
			
			if(map.containsKey(new Side(a,c))) {
				p2 = map.get(new Side(a,c));
				countSideB++;
				if(countSideB >= 2) 
					map.remove(new Side(a,c));			
			} else {
				p2 = new Point((int)(0.5*(a.getX() + c.getX())), (int)((0.5*(a.getY() + c.getY())) + RandomUtilities.randFunc(dev)));
				map.put(new Side(a,c), p2);
			}
		
			if(map.containsKey(new Side(b,c))) {
				p3 = map.get(new Side(b,c));
				countSideC++;
				if(countSideC >= 2)
					map.remove(new Side(b,c));
			} else {
			    p3 = new Point((int)(0.5*(b.getX() + c.getX())), (int)((0.5*(b.getY() + c.getY())) + RandomUtilities.randFunc(dev)));
			    map.put(new Side(b,c), p3);
			}
				

			fractalTriangle(g, a, p1, p2, order - 1, dev / 2);
			fractalTriangle(g, p1, b, p3, order - 1, dev / 2);
			fractalTriangle(g, p2, p3, c, order - 1, dev / 2);
			fractalTriangle(g, p2, p1, p3, order - 1, dev / 2);
		
		
		
		
		
		
			
			
			
			
		}
	}

}
