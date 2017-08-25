//Meet Arora 2016055

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;
import java.awt.Point;

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

class Mess {
	double food_availability, food_nutrition, hygeine, food_delivery, rank;

	Mess(double food_availability, double food_nutrition, double hygeine, double food_delivery) {
		this.food_availability=food_availability;
		this.food_nutrition=food_nutrition;
		this.hygeine=hygeine;
		this.food_delivery=food_delivery;
		rank=0;
	}

	double getA() {
		return food_availability;
	}

	double getB() {
		return food_nutrition;
	}

	double getC() {
		return hygeine;
	}

	double getD() {
		return food_delivery;
	}
}

class Hostel {
	double condition, study_facilities, cleanliness, rec_facilities, rank;

	Hostel(double condition, double study_facilities, double cleanliness, double rec_facilities) {
		this.condition=condition;
		this.study_facilities=study_facilities;
		this.cleanliness=cleanliness;
		this.rec_facilities=rec_facilities;
		rank=0;
	}

	double getA() {
		return condition;
	}

	double getB() {
		return study_facilities;
	}

	double getC() {
		return cleanliness;
	}

	double getD() {
		return rec_facilities;
	}
}

class Library {
	double book_availability, digital_facility, sys_efficiency, avg, rank;

	Library(double book_availability, double digital_facility, double sys_efficiency) {
		this.book_availability=book_availability;
		this.digital_facility=digital_facility;
		this.sys_efficiency=sys_efficiency;
		avg=(book_availability+digital_facility+sys_efficiency)/3;
		rank=0;
	}
}

class Academics {
	double knowledge, domains, course_efficiency, sum, rank;

	Academics(double knowledge, double domains, double course_efficiency) {
		this.knowledge=knowledge;
		this.domains=domains;
		this.course_efficiency=course_efficiency;
		sum=knowledge+domains+(2*course_efficiency);
		rank=0;
	}
}

class College {
	String name;
	double fees;
	String grade;
	int grade_point;
	Mess mess;
	Hostel hostel;
	Library lib;
	Academics acad;
	double rank;

	College(String name, Mess mess, Hostel hostel, Library lib, Academics acad, double fees, String grade) {
		this.name=name;
		this.mess=mess;
		this.hostel=hostel;
		this.lib=lib;
		this.acad=acad;
		this.fees=fees;
		this.grade=grade;
		rank=0;
		if(grade.equals("A"))
			grade_point=1;
		else if(grade.equals("B"))
			grade_point=2;
		else if(grade.equals("C"))
			grade_point=3;
		else if(grade.equals("D"))
			grade_point=4;
		else
			grade_point=5;
	}
}

public class lab2 {
	public static void rank_college(College[] c, int n) {
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<n-i-1;j++) {
				if(c[j].rank>c[j+1].rank) {
					College temp=c[j];
					c[j]=c[j+1];
					c[j+1]=temp;
				}
				else if(c[j].rank==c[j+1].rank) {
					if(c[j].fees>c[j+1].fees) {
						College temp=c[j];
						c[j]=c[j+1];
						c[j+1]=temp;
					}
					else if(c[j].fees==c[j+1].fees) {
						if(c[j].grade_point>c[j+1].grade_point) {
							College temp=c[j];
							c[j]=c[j+1];
							c[j+1]=temp;
						}
					}
				}
			}
		}
	}

	public static void sort(Mess[] m, Hostel[] h, Library[] l, Academics[] a, int n) {
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<n-i-1;j++) {
				if(m[j].getA()<m[j+1].getA()) {
					Mess temp=m[j];
					m[j]=m[j+1];
					m[j+1]=temp;
				}
				else if(m[j].getA()==m[j+1].getA()) {
					if(m[j].getB()<m[j+1].getB()) {
						Mess temp=m[j];
						m[j]=m[j+1];
						m[j+1]=temp;
					}
					else if(m[j].getB()==m[j+1].getB()) {
						if(m[j].getC()<m[j+1].getC()) {
							Mess temp=m[j];
							m[j]=m[j+1];
							m[j+1]=temp;
						}
						else if(m[j].getC()==m[j+1].getC()) {
							if(m[j].getD()>m[j+1].getD()) {
								Mess temp=m[j];
								m[j]=m[j+1];
								m[j+1]=temp;
							}
						}
					}
				}
				if(h[j].getA()<h[j+1].getA()) {
					Hostel temp=h[j];
					h[j]=h[j+1];
					h[j+1]=temp;
				}
				else if(h[j].getA()==h[j+1].getA()) {
					if(h[j].getB()<h[j+1].getB()) {
						Hostel temp=h[j];
						h[j]=h[j+1];
						h[j+1]=temp;
					}
					else if(h[j].getB()==h[j+1].getB()) {
						if(h[j].getC()<h[j+1].getC()) {
							Hostel temp=h[j];
							h[j]=h[j+1];
							h[j+1]=temp;
						}
						else if(h[j].getC()==h[j+1].getC()) {
							if(h[j].getD()<h[j+1].getD()) {
								Hostel temp=h[j];
								h[j]=h[j+1];
								h[j+1]=temp;
							}
						}
					}
				}
				if(l[j].avg<l[j+1].avg) {
					Library temp=l[j];
					l[j]=l[j+1];
					l[j+1]=temp;
				}
				if(a[j].sum<a[j+1].sum) {
					Academics temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int n=Reader.nextInt();
		System.out.println();
		Mess[] m=new Mess[n];
		Hostel[] h=new Hostel[n];
		Library[] l=new Library[n];
		Academics[] a=new Academics[n];
		College[] c=new College[n];
		for(int i=0;i<n;i++) {
			String name=Reader.next();
			System.out.print("Mess ");
			double one=Reader.nextDouble();
			double two=Reader.nextDouble();
			double three=Reader.nextDouble();
			double four=Reader.nextDouble();
			m[i]=new Mess(one, two, three, four);
			System.out.print("Hostel ");
			one=Reader.nextDouble();
			two=Reader.nextDouble();
			three=Reader.nextDouble();
			four=Reader.nextDouble();
			h[i]=new Hostel(one, two, three, four);
			System.out.print("Library ");
			one=Reader.nextDouble();
			two=Reader.nextDouble();
			three=Reader.nextDouble();
			l[i]=new Library(one, two, three);
			System.out.print("Academics ");
			one=Reader.nextDouble();
			two=Reader.nextDouble();
			three=Reader.nextDouble();
			a[i]=new Academics(one, two, three);
			System.out.print("Fees ");
			double fees=Reader.nextDouble();
			System.out.print("NAAC Certificate ");
			String cert=Reader.next();
			System.out.println();
			c[i]=new College(name, m[i], h[i], l[i], a[i], fees, cert);
		}
		sort(m, h, l, a, n);
		for(int i=0;i<n;i++) {
			m[i].rank=i+1;
			h[i].rank=i+1;
			l[i].rank=i+1;
			a[i].rank=i+1;
		}
		for(int i=0;i<n;i++) {
			double m_rank=c[i].mess.rank;
			double h_rank=c[i].hostel.rank;
			double l_rank=c[i].lib.rank;
			double a_rank=c[i].acad.rank;
			c[i].rank=(0.25*m_rank)+(0.20*h_rank)+(0.25*l_rank)+(0.30*a_rank);
		}
		rank_college(c, n);
		System.out.println("List of colleges as per their ranking (starting from rank-1) is as following:");
		System.out.println();
		for(int i=0;i<n;i++) {
			System.out.println(c[i].name);
		}
	}
}
