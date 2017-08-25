//Meet Arora 2016055

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /**
     * call this method to initialize reader for InputStream
     */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    /**
     * get next word
     */
    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}

class Creature {
	private String name;
	private static int power, health, cost, asset;

	Creature() {
		name="";
	}

	Creature(int p, int h, int c, int a) {
		this.name="";
		power=p;
		health=h;
		cost=c;
		asset=a;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getName() {
		return this.name;
	}

	public int getPower() {
		return power;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int h) {
		health=h;
	}

	public int getCost() {
		return cost;
	}

	public int getAsset() {
		return asset;
	}

	public String getType() {
		return "";
	}
}

class Human extends Creature {
	private static final String type="human";
	private static int power, h, cost, asset;
	private int health;

	Human(){
		health=h;
	};

	Human(int p, int he, int c, int a) {
		power=p;
		h=he;
		health=h;
		cost=c;
		asset=a;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int h) {
		health=h;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public int getAsset() {
		return asset;
	}

	@Override
	public String getType() {
		return type;
	}
}

class Dragon extends Creature {
	private static final String type="dragon";
	private static int power, h, cost, asset;
	private int health;

	Dragon(){
		health=h;
	};

	Dragon(int p, int he, int c, int a) {
		power=p;
		h=he;
		health=h;
		cost=c;
		asset=a;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int h) {
		health=h;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public int getAsset() {
		return asset;
	}

	@Override
	public String getType() {
		return type;
	}
}

class Daemon extends Creature {
	private static final String type="daemon";
	private static int power, h, cost, asset;
	private int health;

	Daemon() {
		health=h;
	};

	Daemon(int p, int he, int c, int a) {
		power=p;
		h=he;
		health=h;
		cost=c;
		asset=a;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int h) {
		health=h;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public int getAsset() {
		return asset;
	}

	@Override
	public String getType() {
		return type;
	}
}

class Wolf extends Creature {
	private static final String type="wolf";
	private static int power, h, cost, asset;
	private int health;

	Wolf() {
		health=h;
	};

	Wolf(int p, int he, int c, int a) {
		power=p;
		h=he;
		health=h;
		cost=c;
		asset=a;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int h) {
		health=h;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public int getAsset() {
		return asset;
	}

	@Override
	public String getType() {
		return type;
	}
}

class Ice_Dragon extends Dragon {
	private static final String type="ice-dragon";
	private static int power, h, cost, asset;
	private int health;

	Ice_Dragon() {
		health=h;
	};

	Ice_Dragon(int p, int he, int c, int a) {
		power=p;
		h=he;
		health=h;
		cost=c;
		asset=a;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int h) {
		health=h;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public int getAsset() {
		return asset;
	}

	@Override
	public String getType() {
		return type;
	}
}

class Team {
	private int money;
	protected ArrayList<Creature> a;
	private Creature selected;
	private int index;

	Team() {
		money=0;
		a=new ArrayList<Creature>();
		selected=null;
		index=-1;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money=money;
	}

	public void addMoney(int m) {
		this.money+=m;
	}

	public void subtractMoney(int m) {
		this.money-=m;
	}

	public int size() {
		return a.size();
	}

	public void addCreature(Creature c) {
		a.add(c);
	}

	public void select(String name) {
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getName().equals(name)){
				selected=a.get(i);
				index=i;
			}
		}
	}

	public boolean isCreatureSelected() {
		return index>-1;
	}

	public Creature getSelected() {
		return selected;
	}

	public void remove() {
		a.remove(index);
		index=-1;
	}
}

public class lab3 {
	public static int selectBad(Team a) throws IOException {
		System.out.println("Select Creatures for Team Bad:");
		System.out.println("\t1. Daemon");
		System.out.println("\t2. Ice Dragon");
		System.out.println("\t3. Exit Selection");
		int temp=Reader.nextInt();
		if(temp==1) {
			Daemon h=new Daemon();
			if(a.getMoney()<h.getCost())
				return 1;
			System.out.println("Enter Name Of The Daemon -");
			String name=Reader.next();
			h.setName(name);
			a.addCreature(h);
			a.subtractMoney(h.getCost());
		}
		else if(temp==2) {
			Ice_Dragon d=new Ice_Dragon();
			if(a.getMoney()<d.getCost())
				return 1;
			System.out.println("Enter Name Of The Ice Dragon -");
			String name=Reader.next();
			d.setName(name);
			a.addCreature(d);
			a.subtractMoney(d.getCost());
		}
		else if(temp==3)
			return -1;
		else
			return 1;
		return 0;
	}

	public static int selectGood(Team a) throws IOException {
		System.out.println("Select Creatures for Team Good:");
		System.out.println("\t1. Human");
		System.out.println("\t2. Fire Dragon");
		System.out.println("\t3. Wolf");
		System.out.println("\t4. Exit Selection");
		int temp=Reader.nextInt();
		if(temp==1) {
			Human h=new Human();
			if(a.getMoney()<h.getCost())
				return 1;
			System.out.println("Enter Name Of The Human -");
			String name=Reader.next();
			h.setName(name);
			a.addCreature(h);
			a.subtractMoney(h.getCost());
		}
		else if(temp==2) {
			Dragon d=new Dragon();
			if(a.getMoney()<d.getCost())
				return 1;
			System.out.println("Enter Name Of The Fire Dragon -");
			String name=Reader.next();
			d.setName(name);
			a.addCreature(d);
			a.subtractMoney(d.getCost());
		}
		else if(temp==3) {
			Wolf w=new Wolf();
			if(a.getMoney()<w.getCost())
				return 1;
			System.out.println("Enter Name Of The Wolf -");
			String name=Reader.next();
			w.setName(name);
			a.addCreature(w);
			a.subtractMoney(w.getCost());
		}
		else if(temp==4)
			return -1;
		else
			return 1;
		return 0;
	}

	public static boolean check(Team good, Team bad, int min) {
		if((good.getMoney()<min && good.size()==0) || (bad.getMoney()<min && bad.size()==0))
			return false;
		return true;
	}

	public static String fight(Team good, Team bad) {
		Random random=new Random();
		int g_i_health=good.getSelected().getHealth();
		int b_i_health=bad.getSelected().getHealth();
		int g_damage=random.nextInt(bad.getSelected().getPower()+1);
		good.getSelected().setHealth(good.getSelected().getHealth()-g_damage);
		int b_damage=random.nextInt(good.getSelected().getPower()+1);
		bad.getSelected().setHealth(bad.getSelected().getHealth()-b_damage);
		int r=random.nextInt(100);

		// System.out.println(g_i_health);
		// System.out.println(b_i_health);
		// System.out.println("Random: "+r);
		// System.out.println("Good Damage: "+g_damage);
		// System.out.println("Bad Damage: "+b_damage);

		if(good.getSelected().getType().equals("dragon")) {
			if(r<15) {
				bad.getSelected().setHealth(bad.getSelected().getHealth()-25);
			}
		}
		if(bad.getSelected().getType().equals("daemon")) {
			if(r<50) {
				good.getSelected().setHealth(good.getSelected().getHealth()-g_damage);
			}
		}
		else if(bad.getSelected().getType().equals("ice-dragon")) {
			if(r<15) {
				good.getSelected().setHealth(good.getSelected().getHealth()-25);
			}
			r=random.nextInt(100);
			if(r<5) {
				g_damage=random.nextInt(bad.getSelected().getPower()+1);
				// System.out.println("Extra: "+g_damage);
				good.getSelected().setHealth(good.getSelected().getHealth()-g_damage);
			}
		}
		return end(good, bad, g_i_health, b_i_health);
	}

	public static String end(Team good, Team bad, int g, int b) {
		int g_health=good.getSelected().getHealth();
		int b_health=bad.getSelected().getHealth();
		int g_damage=g-g_health;
		int b_damage=b-b_health;
		String name="";
		if(g_damage>b_damage)
			name=good.getSelected().getName();
		else if(b_damage>g_damage)
			name=bad.getSelected().getName();
		if(g_health>0 && b_health>0) {
			// System.out.println("None");
		}
		else if(g_health<=0 && b_health<=0) {
			good.remove();
			bad.remove();
		}
		else {
			if(g_health<=0) {
				// System.out.println("G");
				bad.addMoney(bad.getSelected().getAsset());
				name=good.getSelected().getName();
				good.remove();
			}
			if(b_health<=0) {
				// System.out.println("B");
				good.addMoney(good.getSelected().getAsset());
				name=bad.getSelected().getName();
				bad.remove();
			}
		}
		return name;
	}

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		Team good=new Team();
		Team bad=new Team();
		int min=1000000000;
		System.out.println("Enter Team Good's total money");
		int Good_money=Reader.nextInt();
		good.setMoney(Good_money);
		System.out.println("Enter Team Bad's total money");
		int Bad_money=Reader.nextInt();
		bad.setMoney(Bad_money);
		System.out.println("Enter cost, asset, power and health for Human (space-separated) -");
		int cost=Reader.nextInt();
		int asset=Reader.nextInt();
		int power=Reader.nextInt();
		int health=Reader.nextInt();
		min=Math.min(cost, min);
		Human h=new Human(power, health, cost, asset);
		System.out.println("Enter cost, asset, power and health for Fire Dragon (space-separated) -");
		cost=Reader.nextInt();
		asset=Reader.nextInt();
		power=Reader.nextInt();
		health=Reader.nextInt();
		min=Math.min(cost, min);
		Dragon fire=new Dragon(power, health, cost, asset);
		System.out.println("Enter cost, asset, power and health for Ice Dragon (space-separated) -");
		cost=Reader.nextInt();
		asset=Reader.nextInt();
		power=Reader.nextInt();
		health=Reader.nextInt();
		min=Math.min(cost, min);
		Ice_Dragon ice=new Ice_Dragon(power, health, cost, asset);
		System.out.println("Enter cost, asset, power and health for Daemon (space-separated) -");
		cost=Reader.nextInt();
		asset=Reader.nextInt();
		power=Reader.nextInt();
		health=Reader.nextInt();
		min=Math.min(cost, min);
		Daemon d=new Daemon(power, health, cost, asset);
		System.out.println("Enter cost, asset, power and health for Wolf (space-separated) -");
		cost=Reader.nextInt();
		asset=Reader.nextInt();
		power=Reader.nextInt();
		health=Reader.nextInt();
		min=Math.min(cost, min);
		Wolf w=new Wolf(power, health, cost, asset);
		int flag=1, flag1=1, flag2=1;
		int counter=0;
		// System.out.println(min);
		while(check(good, bad, min) || flag==1) {
			counter++;
			while(good.getMoney()>=min || flag1==1) {
				flag1=0;
				int temp=selectGood(good);
				if(good.size()==0)
					temp=1;
				if(temp==1) {
					System.out.println("Choose Again: ");
					flag1=1;
					continue;
				}
				if(temp==-1)
					break;
				// System.out.println("Good money: "+good.getMoney());
			}
			while(bad.getMoney()>=min || flag2==1) {
				flag2=0;
				int temp=selectBad(bad);
				if(bad.size()==0)
					temp=1;
				if(temp==1) {
					System.out.println("Choose Again: ");
					flag2=1;
					continue;
				}
				if(temp==-1)
					break;
				// System.out.println("Bad money: "+bad.getMoney());
			}

			// System.out.println("Good Team:");
			// for(int x=0;x<good.size();x++) {
			// 	System.out.println(good.a.get(x).getName());
			// 	System.out.println(good.a.get(x).getCost()+" "+good.a.get(x).getAsset()+" "+good.a.get(x).getPower()+" "+good.a.get(x).getHealth());
			// 	System.out.println();
			// }
			// System.out.println("Bad Team:");
			// for(int x=0;x<bad.size();x++) {
			// 	System.out.println(bad.a.get(x).getName());
			// 	System.out.println(bad.a.get(x).getCost()+" "+bad.a.get(x).getAsset()+" "+bad.a.get(x).getPower()+" "+bad.a.get(x).getHealth());
			// 	System.out.println();
			// }

			System.out.println();
			if(flag==1)
				System.out.println("The War Begins -");
			flag=0;

			System.out.println("Round-"+counter+":");
			while(!good.isCreatureSelected()) {
				System.out.println("Enter Creature from Good's Team to fight in the war -");
				String temp_name=Reader.next();
				good.select(temp_name);
			}
			while(!bad.isCreatureSelected()) {
				System.out.println("Enter Creature from Bad's Team to fight in the war -");
				String temp_name=Reader.next();
				bad.select(temp_name);
			}
			String loser=fight(good, bad);
			System.out.println();
			if(loser.equals(""))
				loser="No One";
			System.out.println(loser+" Loses In Round-"+counter);
			System.out.println("Money of Bad's Team is "+bad.getMoney());
			System.out.println("Money of Good's Team is "+good.getMoney());
		}
		System.out.println();
		if(good.size()==0 && bad.size()>0)
			System.out.println("Team Bad wins the war. The money the team has is "+bad.getMoney());
		else if(bad.size()==0 && good.size()>0)
			System.out.println("Team Good wins the war. The money the team has is "+good.getMoney());
		else if(bad.size()==0 && good.size()==0) {
			if(bad.getMoney()>good.getMoney())
				System.out.println("Team Bad wins the war. The money the team has is "+bad.getMoney());
			else if(good.getMoney()>bad.getMoney())
				System.out.println("Team Good wins the war. The money the team has is "+good.getMoney());
			else
				System.out.println("No Team wins the war.");
		}
	}
}
