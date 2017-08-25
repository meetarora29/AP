//Meet Arora 2016055

import java.io.*;
import java.util.*;

class ComparePriority implements Comparator<Animals> {
	@Override
	public int compare(Animals a, Animals b) {
		if(a.getTime()<b.getTime())
			return -1;
		else if(a.getTime()==b.getTime()) {
			if(a.getHealth()>b.getHealth())
				return -1;
			else if(a.getHealth()==b.getHealth()) {
				if(a.getType()==b.getType()) {
					return (int)(a.getDistance(0, 0)-b.getDistance(0, 0));
				}
				else {
					if(a.getType()==1)
						return -1;
					else
						return 1;
				}
			}
			else
				return 1;
		}
		else
			return 1;
	}
}

class World {
	private int time, h_count, c_count;
	private Herbivores h_one, h_two;
	private Carnivores c_one, c_two;
	private Grasslands g_one, g_two;
	PriorityQueue<Animals> pq;

	World(int time, Herbivores h1, Herbivores h2, Carnivores c1, Carnivores c2, Grasslands g1, Grasslands g2) {
		this.time=time;
		h_one=h1;
		h_two=h2;
		c_one=c1;
		c_two=c2;
		g_one=g1;
		g_two=g2;
		pq=new PriorityQueue<Animals>(4, new ComparePriority());
		pq.add(h1);
		pq.add(h2);
		pq.add(c1);
		pq.add(c2);
		h_count=2;
		c_count=2;
	}

	public void simulate(int m) {
		Random random=new Random();
		int turns=time;
		int max_time=m;
		while(!pq.isEmpty() && turns>0) {
			turns--;
			// Object[] a=pq.toArray();
			// System.out.println("MaxTime "+max_time);
			// for(int i=0;i<a.length;i++)
			// 	System.out.println(a[i]);
			// System.out.println();
			Animals head=pq.poll();
			if(head==h_one)
				System.out.println("It is First Herbivore");
			else if(head==h_two)
				System.out.println("It is Second Herbivore");
			else if(head==c_one)
				System.out.println("It is First Carnivore");
			else if(head==c_two)
				System.out.println("It is Second Carnivore");
			boolean dead=false;
			if(head.getType()==1)
				dead=head.takeTurn(g_one, g_two, c_one, c_two, c_count, pq);
			else if(head.getType()==2)
				dead=head.takeTurn(g_one, g_two, h_one, h_two, h_count, pq);
			if(dead) {
				System.out.println("It is dead");
				if(head.getType()==1)
					h_count--;
				else if(head.getType()==2)
					c_count--;
				// System.out.println(h_count+" "+c_count);
			}
			else {
				System.out.println("It's health after taking turn is "+head.getHealth());
				int r=random.nextInt(time-max_time-1)+max_time+1; // Exclusive
				// System.out.println("Random: "+r);
				if(r==time-1){
					System.out.println("It is removed");
					System.out.println();
					continue;
				}
				max_time=Math.max(max_time, r);
				head.setTime(r);
				pq.add(head);
			}
			System.out.println();
		}
	}
}

abstract class Animals {
	protected int x, y, timestamp, health, turn_count;

	Animals() {

	}

	Animals(int x, int y, int timestamp, int health) {
		this.x=x;
		this.y=y;
		this.timestamp=timestamp;
		this.health=health;
		turn_count=0;
	}

	public String toString() {
		return "X "+x+" Y "+y+" Time "+timestamp+" Health "+health+" Turn "+turn_count;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getTime() {
		return timestamp;
	}

	public int getHealth() {
		return health;
	}

	public int getTurnCount() {
		return turn_count;
	}

	public void setTurnCount(int c) {
		turn_count=c;
	}

	public void increaseTurnCount() {
		turn_count++;
	}

	public void setHealth(int h) {
		health+=h;
	}

	public void setX(int x) {
		this.x=x;
	}

	public void setY(int y) {
		this.y=y;
	}

	public void setTime(int t) {
		timestamp=t;
	}

	public double getDistance(int h, int k) {
		return Math.sqrt((x-h)*(x-h) + (y-k)*(y-k));
	}

	public boolean isDead() {
		return health<=0;
	}

	public Grasslands nearestGrassland(Grasslands a, Grasslands b) {
		double a_dist=getDistance(a.getX(), a.getY());
		double b_dist=getDistance(b.getX(), b.getY());
		a_dist=Math.abs(a_dist-a.getRadius());
		b_dist=Math.abs(b_dist-b.getRadius());
		if(a_dist<=b_dist)
			return a;
		else
			return b;
	}

	public Grasslands nextnearestGrassland(Grasslands a, Grasslands b) {
		double a_dist=getDistance(a.getX(), a.getY());
		double b_dist=getDistance(b.getX(), b.getY());
		a_dist=Math.abs(a_dist-a.getRadius());
		b_dist=Math.abs(b_dist-b.getRadius());
		if(a_dist<=b_dist)
			return b;
		else
			return a;
	}

	public boolean inGrassland(Grasslands g) {
		if((x-g.getX())*(x-g.getX()) + (y-g.getY())*(y-g.getY()) <= g.getRadius()*g.getRadius())
			return true;
		return false;
	}

	public Animals nearest(Animals a, Animals b) {
		double a_dist=getDistance(a.getX(), a.getY());
		double b_dist=getDistance(b.getX(), b.getY());
		if(a_dist<=b_dist){
			if(a.isDead())
				return b;
			return a;
		}
		else{
			if(b.isDead())
				return a;
			return b;
		}
	}

	public void move(double units, int h, int k) {
		//Using Ratio formula
		double dist=getDistance(h, k);
		double ratio=units/dist;
		x=(int)((1-ratio)*x + ratio*h);
		y=(int)((1-ratio)*y + ratio*k);
	}

	abstract public int getType();
	abstract public boolean takeTurn(Grasslands g_one, Grasslands g_two, Animals one, Animals two, int count, PriorityQueue<Animals> pq);
}

class Herbivores extends Animals {
	private static final int type=1; //1 for Herbivore
	private static int h, capacity;

	Herbivores(int he, int c) {
		h=he;
		capacity=c;
	}

	Herbivores(int x, int y, int timestamp) {
		super(x, y, timestamp, h);
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public boolean takeTurn(Grasslands g_one, Grasslands g_two, Animals one, Animals two, int count, PriorityQueue<Animals> pq) {
		Random random=new Random();
		Grasslands g_nearest=nearestGrassland(g_one, g_two);
		Grasslands nextNearest=nextnearestGrassland(g_one, g_two);
		Animals c_nearest=nearest(one, two);
		int r=random.nextInt(100);
		// System.out.println("g_nearest: x "+g_nearest.getX()+" y "+g_nearest.getY());
		// System.out.println("nearest: x "+c_nearest.getX()+" y "+c_nearest.getY());
		// System.out.println("Count "+count+" Random:"+r);
		if(count==0) {
			// Moves with 50% chance
			if(r<50) {
				if(inGrassland(g_nearest)){
					move(5, nextNearest.getX(), nextNearest.getY());
					setTurnCount(0);
					// System.out.println("In");
				}
				else{
					move(5, g_nearest.getX(), g_nearest.getY());
					increaseTurnCount();
				}
				setHealth(-25);
			}
		}
		else {
			if(inGrassland(g_nearest)) {
				// System.out.println("In");
				setTurnCount(0);
				if(g_nearest.getGrass()>=capacity) {
					// System.out.println("CapLower");
					if(r<90){
						g_nearest.setGrass(g_nearest.getGrass()-capacity);
						setHealth((int)(0.5*getHealth()));
					}
					else {
						//Moves
						r=random.nextInt(100);
						if(r<50)
							move(-2, c_nearest.getX(), c_nearest.getY());
						else
							move(3, nextNearest.getX(), nextNearest.getY());
						setHealth(-25);
					}
				}
				else {
					// System.out.println("CapGreater");
					if(r<20) {
						if(g_nearest.getGrass()>0)
							setHealth((int)(0.2*getHealth()));
						g_nearest.setGrass(0);
					}
					else {
						//Moves
						r=random.nextInt(100);
						if(r<70)
							move(-4, c_nearest.getX(), c_nearest.getY());
						else
							move(2, nextNearest.getX(), nextNearest.getY());
						setHealth(-25);
					}
				}
			}
			else {
				// System.out.println("Out");
				increaseTurnCount();
				if(r<95) {
					r=random.nextInt(100);
					if(r<65)
						move(5, g_nearest.getX(), g_nearest.getY());
					else
						move(-4, c_nearest.getX(), c_nearest.getY());
				}
			}
		}
		if(getTurnCount()>7)
			setHealth(-5);
		return isDead();
	}
}

class Carnivores extends Animals {
	private static final int type=2; //2 for Carnivore
	private static int h;

	Carnivores(int he) {
		h=he;
	}

	Carnivores(int x, int y, int timestamp) {
		super(x, y, timestamp, h);
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public boolean takeTurn(Grasslands g_one, Grasslands g_two, Animals one, Animals two, int count, PriorityQueue<Animals> pq) {
		Random random=new Random();
		Grasslands g_nearest=nearestGrassland(g_one, g_two);
		Grasslands nextNearest=nextnearestGrassland(g_one, g_two);
		Animals h_nearest=nearest(one, two);
		int r=random.nextInt(100);
		// System.out.println("g_nearest: x "+g_nearest.getX()+" y "+g_nearest.getY());
		// System.out.println("nearest: x "+h_nearest.getX()+" y "+h_nearest.getY());
		// System.out.println("Count "+count+" Random:"+r);
		if(count==0) {
			if(inGrassland(g_nearest))
				setHealth(-30);
			else
				setHealth(-60);
			increaseTurnCount();
		}
		else {
			if(getDistance(h_nearest.getX(), h_nearest.getY())<=5)
				setTurnCount(0);
			else
				increaseTurnCount();
			// System.out.println("TurnCount: "+getTurnCount());
			if(getDistance(h_nearest.getX(), h_nearest.getY())<=1) {
				setHealth(2*h_nearest.getHealth()/3);
				// System.out.println("Dead: "+h_nearest);
				h_nearest.setHealth(0);
				pq.remove(h_nearest);
			}
			else {
				if(inGrassland(g_nearest)) {
					// System.out.println("In");
					if(r<25)
						setHealth(-30);
					else
						move(2, h_nearest.getX(), h_nearest.getY());
				}
				else {
					// System.out.println("Out");
					if(r<92)
						move(4, h_nearest.getX(), h_nearest.getY());
					else
						setHealth(-60);
				}
			}
		}
		if(getTurnCount()>7)
			setHealth(-6);
		return isDead();
	}
}

class Grasslands {
	private int x, y, radius, grass;

	Grasslands(int x, int y, int radius, int grass) {
		this.x=x;
		this.y=y;
		this.radius=radius;
		this.grass=grass;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}

	public int getGrass() {
		return grass;
	}

	public void setGrass(int g) {
		grass=g;
	}
}

public class lab4 {
	public static void main(String[] args) {
		Scanner Reader=new Scanner(System.in);
		int max_time=-1;
		System.out.println("Enter Total Final Time for Simulation:");
		int n=Reader.nextInt();
		System.out.println("Enter x, y centre, radius and Grass Available for First Grassland:");
		int one=Reader.nextInt();
		int two=Reader.nextInt();
		int three=Reader.nextInt();
		int four=Reader.nextInt();
		Grasslands g1=new Grasslands(one, two, three, four);
		System.out.println("Enter x, y centre, radius and Grass Available for Second Grassland:");
		one=Reader.nextInt();
		two=Reader.nextInt();
		three=Reader.nextInt();
		four=Reader.nextInt();
		Grasslands g2=new Grasslands(one, two, three, four);
		System.out.println("Enter Health and Grass Capacity for Herbivores:");
		one=Reader.nextInt();
		two=Reader.nextInt();
		Herbivores htemp=new Herbivores(one, two);
		System.out.println("Enter x, y position and timestamp for First Herbivore:");
		one=Reader.nextInt();
		two=Reader.nextInt();
		three=Reader.nextInt();
		max_time=Math.max(max_time, three);
		Herbivores h1=new Herbivores(one, two, three);
		System.out.println("Enter x, y position and timestamp for Second Herbivore:");
		one=Reader.nextInt();
		two=Reader.nextInt();
		three=Reader.nextInt();
		max_time=Math.max(max_time, three);
		Herbivores h2=new Herbivores(one, two, three);
		System.out.println("Enter Health for Carnivores:");
		one=Reader.nextInt();
		Carnivores ctemp=new Carnivores(one);
		System.out.println("Enter x, y position and timestamp for First Carnivore:");
		one=Reader.nextInt();
		two=Reader.nextInt();
		three=Reader.nextInt();
		max_time=Math.max(max_time, three);
		Carnivores c1=new Carnivores(one, two, three);
		System.out.println("Enter x, y position and timestamp for Second Carnivore:");
		one=Reader.nextInt();
		two=Reader.nextInt();
		three=Reader.nextInt();
		max_time=Math.max(max_time, three);
		Carnivores c2=new Carnivores(one, two, three);
		World w=new World(n, h1, h2, c1, c2, g1, g2);
		System.out.println("The Simulation Begins -");
		System.out.println();
		w.simulate(max_time);
	}
}
