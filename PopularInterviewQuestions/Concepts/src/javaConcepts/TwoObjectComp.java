package javaConcepts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

public class TwoObjectComp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(2, 3);
		Point p2 = new Point(2, 3);
		
		System.out.println(p1.equals(p2));
		
		String s = "fef"+ 'c';
		System.out.println(s);
		
		HashSet<Point> s1 = new HashSet<Point>();
		s1.add(p1);
		s1.add(p2);

		System.out.println(s1.contains(new Point(2,3)));
		
		System.out.println(p1.equals(p2));
		
		ArrayList<Point>  wew = new ArrayList<Point>();
		wew.add(p1);
		System.out.println(wew.contains(new Point(2,3)));
	}
	

}



class Point {
	int r;
	int c;
	Point(int r, int c){
		this.c = c;
		this.r =r;
	}
	@Override
	 public boolean equals(Object point) {
		Point p = (Point) point;
		return this.r == p.r && this.c == p.c;
	}
	@Override 
	public int hashCode(){
		return r*c;
		
	}
}
