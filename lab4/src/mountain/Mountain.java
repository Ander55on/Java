package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

	private Point pointA;
	private Point pointB;
	private Point pointC;
	
	public Mountain(Point pointA, Point pointB, Point pointC) {
		super();
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
		
	}
	
	@Override
	public String getTitle() {
		return  "Mountain fractal";
	}

	@Override
	public void draw(TurtleGraphics g) {
		// TODO Auto-generated method stub
		g.penDown();
		fractalTriangle(g, pointA, pointB, pointC, order, 15);
				
	}
	
	private void fractalTriangle(TurtleGraphics g, Point a, Point b, Point c, int order, double dev) {
		if(order == 0) {
			g.moveTo(a.getX(), a.getY());
			g.forwardTo(b.getX(), b.getY());
			g.forwardTo(c.getX(), c.getY());
			g.forwardTo(a.getX(), a.getY());
		} else {
			Point p1 = new Point((int)(0.5*(a.getX() + b.getX())), (int)((0.5*(a.getY() + b.getY())) + RandomUtilities.randFunc(dev)));
			Point p2 = new Point((int)(0.5*(a.getX() + c.getX())), (int)((0.5*(a.getY() + c.getY())) + RandomUtilities.randFunc(dev)));
			Point p3 = new Point((int)(0.5*(b.getX() + c.getX())), (int)((0.5*(b.getY() + c.getY())) + RandomUtilities.randFunc(dev)));
			fractalTriangle(g, a, p1, p2, order - 1, dev / 2);
			fractalTriangle(g, p1, b, p3, order - 1, dev / 2);
			fractalTriangle(g, p2, p3, c, order - 1, dev / 2);
			fractalTriangle(g, p2, p1, p3, order - 1, dev / 2);
			
		}
	}

}
