//Meet Arora 2016055

import java.io.*;
import java.util.*;

class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
	private T data;
	private Node<T> left;
	private Node<T> right;

	Node(T d) {
		data=d;
		left=null;
		right=null;
	}

	public T getData() {
		return data;
	}

	public Node<T> getLeft() {
		return left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> r) {
		right=r;
	}

	public void setLeft(Node<T> l) {
		left=l;
	}

	public String toString() {
		return "Data: "+data;
	}

	@Override
	public int compareTo(Node<T> other) {
		return data.compareTo(other.getData());
	}
}

class Tree<T extends Comparable<T>> {
	private Node<T> root;
	private Node<T> cur_root;
	private String sum;
	private Integer i_sum;
	private Double d_sum;

	Tree(T d) {
		root=new Node<T>(d);
		cur_root=null;
		sum="";
		i_sum=0;
		d_sum=new Double(0);
	}

	public Node<T> getRoot() {
		return root;
	}

	public Node<T> insert(Node<T> root, T data) {
		cur_root=root;
		Node<T> other=new Node<T>(data);
		if(root==null) {
			root=new Node<T>(data);
			return root;
		}
		if(cur_root.compareTo(other)>0)
			root.setLeft(insert(root.getLeft(), data));
		else
			root.setRight(insert(root.getRight(), data));
		return root;
	}

	public int rootPos(Node<T> node) {
		if(node!=null)
			return rootPos(node.getLeft())+rootPos(node.getRight())+1;
		return 0;
	}

	public String getSum() {
		return sum;
	}

	public void sumTree(Node<T> root) {
		if(root!=null) {
			sumTree(root.getLeft());
			if(root.getData() instanceof String)
				sum+=root.getData().toString();
			else if(root.getData() instanceof Double){
				d_sum+=(Double)root.getData();
				d_sum=Double.parseDouble(String.format("%.4f", d_sum));
				sum=d_sum.toString();
			}
			else if(root.getData() instanceof Integer) {
				i_sum+=(Integer)root.getData();
				sum=i_sum.toString();
			}
			sumTree(root.getRight());
		}
	}

	public void printTree(Node<T> root) {
		if(root!=null) {
			printTree(root.getLeft());
			System.out.println(root.getData());
			printTree(root.getRight());
		}
	}

	public String toString() {
		return "Root: " + root;
	}
}

public class lab5 {
	public static void main(String[] args) {
		Scanner Reader=new Scanner(System.in);
		int x=Reader.nextInt();
		int n=Reader.nextInt();
		ArrayList<Tree<Integer>> i_list=new ArrayList<Tree<Integer>>();
		ArrayList<Tree<String>> s_list=new ArrayList<Tree<String>>();
		ArrayList<Tree<Double>> d_list=new ArrayList<Tree<Double>>();
		try{
			for(int i=1;i<=x;i++){
				Scanner file=new Scanner(new FileReader(i+".txt"));
				int c=0;
				String temp="";
				while(file.hasNext()) {
					c++;
					if(c==1)
						temp=file.next();
					if(c==2)
						file.next();
					if(c==3) {
						if(temp.equals("String")) {
							Tree<String> t=new Tree<String>(file.next());
							while(file.hasNext())
								t.insert(t.getRoot(), file.next());
							s_list.add(t);
						}
						else if(temp.equals("Float")) {
							Tree<Double> t=new Tree<Double>(Double.parseDouble(file.next()));
							while(file.hasNext())
								t.insert(t.getRoot(), Double.parseDouble(file.next()));
							d_list.add(t);
						}
						else if(temp.equals("Integer")) {
							Tree<Integer> t=new Tree<Integer>(Integer.parseInt(file.next()));
							while(file.hasNext())
								t.insert(t.getRoot(), Integer.parseInt(file.next()));
							i_list.add(t);
						}
					}
				}
				// System.out.println();
			}
		}
		catch (FileNotFoundException f) {
			System.out.println("File not found.");
		}

		HashMap<Integer, String> hm=new HashMap<Integer, String>();

		for(int i=0;i<i_list.size();i++) {
			// System.out.println(i_list.get(i));
			// i_list.get(i).printTree(i_list.get(i).getRoot());
			// System.out.println("Rootpos: "+i_list.get(i).rootPos(i_list.get(i).getRoot().getLeft()));
			i_list.get(i).sumTree(i_list.get(i).getRoot());
			int pos=i_list.get(i).rootPos(i_list.get(i).getRoot().getLeft())+1;
			if(hm.containsKey(pos)) {
				String val=hm.get(pos);
				val+=" ";
				val+=i_list.get(i).getSum();
				hm.remove(pos);
				hm.put(pos, val);
			}
			else
				hm.put(pos, i_list.get(i).getSum());
			// System.out.println(i_list.get(i).getSum());
		}
		for(int i=0;i<s_list.size();i++) {
			// System.out.println(s_list.get(i));
			// s_list.get(i).printTree(s_list.get(i).getRoot());
			s_list.get(i).sumTree(s_list.get(i).getRoot());
			int pos=s_list.get(i).rootPos(s_list.get(i).getRoot().getLeft())+1;
			if(hm.containsKey(pos)) {
				String val=hm.get(pos);
				val+=" ";
				val+=s_list.get(i).getSum();
				hm.remove(pos);
				hm.put(pos, val);
			}
			else
				hm.put(pos, s_list.get(i).getSum());
			// System.out.println(s_list.get(i).getSum());
		}
		for(int i=0;i<d_list.size();i++) {
			// System.out.println(d_list.get(i));
			// d_list.get(i).printTree(d_list.get(i).getRoot());
			// System.out.println();
			d_list.get(i).sumTree(d_list.get(i).getRoot());
			int pos=d_list.get(i).rootPos(d_list.get(i).getRoot().getLeft())+1;
			if(hm.containsKey(pos)) {
				String val=hm.get(pos);
				val+=" ";
				val+=d_list.get(i).getSum();
				hm.remove(pos);
				hm.put(pos, val);
			}
			else
				hm.put(pos, d_list.get(i).getSum());
		}
		int choc=n-hm.size();

		try {
			PrintWriter p=new PrintWriter("answer.txt", "UTF-8");
			for(Integer key:hm.keySet()) {
				String value=hm.get(key);
				p.println(key+ " "+value);
			}
			p.println(choc);
			p.close();
		}
		catch(Exception e) {
			System.out.println("No");
		}
	}
}
