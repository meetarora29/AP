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

// Class of applicants with name, roll_number, course, distance, and index with which they are given in input
class banda {
	public String name, roll, course;
	public int distance, index;

	banda(String name, String roll, String course, int distance, int index) {
		this.name=name;
		this.roll=roll;
		this.course=course;
		this.distance=distance;
		this.index=index;
	}

	// Returns distance
	public int getDistance() {
		return this.distance;
	}

	// Returns index
	public int getIndex() {
		return this.index;
	}

	// To print the objects
	public String toString() {
		return name + " " + roll + " " + course + " " + distance;
	}
}

public class lab1 {
	public static int acc_index=0;

	// Using bubble sort to sort index to display in given order
	public static void sort_index(banda[] bande) {
		int n=acc_index;
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<n-i-1; j++) {
				if(bande[j].getIndex()>bande[j+1].getIndex()) {
					banda temp=bande[j];
					bande[j]=bande[j+1];
					bande[j+1]=temp;
				}
			}
		}
	}

	// Using bubble sort to sort array using the distance in reverse order
	public static void sort(banda[] bande, int n) {
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<n-i-1; j++) {
				if(bande[j].getDistance()<bande[j+1].getDistance()) {
					banda temp=bande[j];
					bande[j]=bande[j+1];
					bande[j+1]=temp;
				}
				else if(bande[j].getDistance()==bande[j+1].getDistance()) {
					if(bande[j].getIndex()>bande[j+1].getIndex()) {
						banda temp=bande[j];
						bande[j]=bande[j+1];
						bande[j+1]=temp;
					}
				}
			}
		}
	}

	// Selects the undergraduate applicants if any rooms available
	public static void undergraduate(banda[] under, int ug, int m, banda[] accepted) {
		sort(under, ug);
		for(int i=0;i<m;i++) {
			if(i+1>ug)
				break;
			accepted[acc_index++]=under[i];
		}
	}

	// Selects the postgraduate applicants with limit of 50%
	public static void postgraduate(banda[] post, int pg, int m, banda[] accepted) {
		if(m>=pg) {
			for(int i=0;i<pg;i++)
				accepted[acc_index++]=post[i];
		}
		else {
			sort(post, pg);
			for(int i=0;i<m;i++)
				accepted[acc_index++]=post[i];
		}
	}

	// Selects the doctorate applicants with limit of 50%
	public static void doctorate(banda[] doc, int phd, int m, banda[] accepted) {
		if(m>=phd) {
			for(int i=0;i<phd;i++)
				accepted[acc_index++]=doc[i];
		}
		else {
			sort(doc, phd);
			for(int i=0;i<m;i++)
				accepted[acc_index++]=doc[i];
		}
	}

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int n=Reader.nextInt();
		int m=Reader.nextInt();
		int pg=0, ug=0, phd=0;
		banda[] bande=new banda[n];
		for(int i=0;i<n;i++) {
			String a=Reader.next();
			String b=Reader.next();
			String c=Reader.next();
			int d=Reader.nextInt();
			bande[i]=new banda(a, b, c, d, i+1);
			if(c.equals("UG"))
				ug++;
			else if(c.equals("PG"))
				pg++;
			else if(c.equals("PhD"))
				phd++;
		}
		banda[] under=new banda[ug];
		banda[] post=new banda[pg];
		banda[] doc=new banda[phd];
		banda[] accepted=new banda[m];
		int index=0;
		for(int i=0;i<n;i++) {
			if(bande[i].course.equals("UG"))
				under[index++]=new banda(bande[i].name, bande[i].roll, bande[i].course, bande[i].distance, bande[i].index);
		}
		index=0;
		for(int i=0;i<n;i++) {
			if(bande[i].course.equals("PG"))
				post[index++]=new banda(bande[i].name, bande[i].roll, bande[i].course, bande[i].distance, bande[i].index);
		}
		index=0;
		for(int i=0;i<n;i++) {
			if(bande[i].course.equals("PhD"))
				doc[index++]=new banda(bande[i].name, bande[i].roll, bande[i].course, bande[i].distance, bande[i].index);
		}
		// If number of rooms is odd, give extra to doctorate students if needed
		if(m%2==1)
			doctorate(doc, phd, m/2+1, accepted);
		else
			doctorate(doc, phd, m/2, accepted);
		postgraduate(post, pg, m/2, accepted);
		undergraduate(under, ug, m-acc_index, accepted);
		sort_index(accepted);
		for(int i=0;i<acc_index;i++)
			System.out.println(accepted[i]);
	}
}
