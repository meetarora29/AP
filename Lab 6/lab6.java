//Meet Arora 2016055

import java.io.*;
import java.util.*;

class Generic<T> {
	private T obj;

	Generic(T d) {
		obj=d;
	}

	public String toString() {
		return obj.toString();
	}

	T getObject() {
		return obj;
	}
}

class Knight {
	private String name;
	private Coordinate c;
	private Stack<Generic<?>> s;

	Knight(String name, int x, int y) {
		this.name=name;
		c=new Coordinate(x, y);
		s=new Stack<Generic<?>>();
	}

	String getName() {
		return name;
	}

	Coordinate getLocation() {
		return c;
	}

	void setLocation(Coordinate c_temp) {
		c=c_temp;
	}

	Stack<Generic<?>> getStack() {
		return s;
	}

	private String printStack() {
		String temp="";
		while(!s.empty())
			temp+=s.pop()+"; ";
		return temp;
	}

	public String toString() {
		return "Name: "+name+", Coordinates: "+c+", Stack: "+printStack();
	}
}

class sortKnights implements Comparator<Knight> {
	public int compare(Knight a, Knight b) {
		return a.getName().compareTo(b.getName());
	}
}

class Coordinate {
	private int x, y;

	Coordinate(int a, int b) {
		x=a;
		y=b;
	}

	public String toString() {
		return x+" "+y;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	public boolean equals(Coordinate c) {
		return x==c.getX() && y==c.getY();
	}
}

@SuppressWarnings("serial")
class NonCoordinateException extends Exception {
	public NonCoordinateException(String s) {
		super(s);
	}
}

@SuppressWarnings("serial")
class OverlapException extends Exception {
	public OverlapException(String s) {
		super(s);
	}
}

@SuppressWarnings("serial")
class StackEmptyException extends Exception {
	public StackEmptyException(String s) {
		super(s);
	}
}

@SuppressWarnings("serial")
class QueenFoundException extends Exception {
	public QueenFoundException(String s) {
		super(s);
	}
}

public class lab6 {
	public static int search_move(ArrayList<Knight> knights, int index, Coordinate c, PrintWriter p) throws OverlapException {
		//Change coordinates for current Knight
		knights.get(index).setLocation(c);
		for(int i=0;i<knights.size();i++) {
			if(i==index)
				continue;
			if(c.equals(knights.get(i).getLocation())) {
				String name=knights.get(i).getName();
				knights.remove(i);
				try {
					throw new OverlapException("Knights Overlap Exception "+name);
				}
				catch(OverlapException e) {
					p.println(e);
					return 1;
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) throws NonCoordinateException, OverlapException, StackEmptyException, QueenFoundException {
		Scanner Reader=new Scanner(System.in);
		int n=Reader.nextInt();
		int iterations=Reader.nextInt();
		int q_x=Reader.nextInt();
		int q_y=Reader.nextInt();
		Coordinate q=new Coordinate(q_x, q_y);
		ArrayList<Knight> knights=new ArrayList<Knight>();

		//Read files
		try {
			for(int i=1;i<=n;i++) {
				Scanner file=new Scanner(new FileReader(i+".txt"));
				String name=file.next();
				int x=Integer.parseInt(file.next());
				int y=Integer.parseInt(file.next());
				int m=Integer.parseInt(file.next());
				knights.add(new Knight(name, x, y));
				for(int j=0;j<m;j++){
					Stack<Generic<?>> s=knights.get(i-1).getStack();
					String temp=file.next();
					if(temp.equals("Float")){
						Generic<Double> g=new Generic<Double>(Double.parseDouble(file.next()));
						s.push(g);
					}
					else if(temp.equals("Integer")){
						Generic<Integer> g=new Generic<Integer>(Integer.parseInt(file.next()));
						s.push(g);
					}
					else if(temp.equals("Coordinate")) {
						int c_x=Integer.parseInt(file.next());
						int c_y=Integer.parseInt(file.next());
						Coordinate c=new Coordinate(c_x, c_y);
						Generic<Coordinate> g=new Generic<Coordinate>(c);
						s.push(g);
					}
					else{
						Generic<String> g=new Generic<String>(file.next());
						s.push(g);
					}
				}
			}
		}
		catch(FileNotFoundException f){
			f.printStackTrace();
		}

		//Sort knights
		Collections.sort(knights, new sortKnights());

		//Write file
		try {
			PrintWriter p=new PrintWriter("output.txt", "UTF-8");
			int flag=0;
			int c=0;
			while(c++<iterations && flag==0) {
				if(knights.size()==0)
					break;
				for(int i=0;i<knights.size();i++) {
					p.println(c+" "+knights.get(i).getName()+" "+knights.get(i).getLocation());
					Stack<Generic<?>> s=knights.get(i).getStack();
					if(s.empty()) {
						knights.remove(i);
						try {
							throw new StackEmptyException("Stack Empty exception");
						}
						catch(StackEmptyException e) {
							p.println(e);
						}
					}
					else {
						Generic<?> g=s.pop();
						if(g.getObject() instanceof Coordinate) {
							//Move and Check
							int overlap=search_move(knights, i, (Coordinate)g.getObject(), p);
							if(overlap==0) {
								if(q.equals((Coordinate)g.getObject())) {
									try {
										throw new QueenFoundException("Queen has been Found. Abort!");
									}
									catch(QueenFoundException e) {
										p.println(e);
										flag=1;
									}
								}
								else {
									p.println("No exception "+(Coordinate)g.getObject());
								}
							}
						}
						else {
							try {
								throw new NonCoordinateException("Not a coordinate Exception "+g.getObject());
							}
							catch(NonCoordinateException e) {
								p.println(e);
							}
						}
					}
				}
			}
			p.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
