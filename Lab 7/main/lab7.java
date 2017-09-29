//Meet Arora 2016055

package main;

import java.io.*;
import java.util.*;
// import main.Song;

public class lab7 {
	public static final String database="./Database/";
	public static Scanner Reader=new Scanner(System.in);

	public static void add(String playlist, ArrayList<Song> songs) throws IOException {
		System.out.println("Enter song title:");
		String name=Reader.nextLine();
		System.out.println("Enter singer name:");
		String singer=Reader.nextLine();
		System.out.println("Enter duration:");
		String duration=Reader.next();
		Reader.nextLine();
		Song new_song=new Song(name, singer, duration);
		songs.add(new_song);
		serialize(playlist, songs);
	}

	public static void delete(String playlist, ArrayList<Song> songs) throws IOException {
		System.out.println("Enter song title to delete:");
		String del_song=Reader.nextLine();
		int num=songs.size();
		for(Song song:songs) {
			if(song.getTitle().equals(del_song)){
				songs.remove(song);
				break;
			}
		}
		if(songs.size()==num)
			System.out.println("Error: Name Does Not Exist");
		else
			System.out.println("Number of Songs: "+songs.size());
		serialize(playlist, songs);
	}

	public static void search(ArrayList<Song> songs) {
		System.out.println("Enter song title to search: ");
		String search_song=Reader.nextLine();
		int found=-1;
		for(Song song:songs) {
			if(song.getTitle().equals(search_song)) {
				System.out.println(song);
				found=1;
				break;
			}
		}
		if(found!=1)
			System.out.println("Error: Name Does Not Exist");
	}

	public static void serialize(String playlist, ArrayList<Song> songs) throws IOException {
		OutputStream output=new FileOutputStream(database+playlist);
		ObjectOutputStream out=null;
		try {
			out=new ObjectOutputStream(output);
			for(Song song:songs)
				out.writeObject(song);
		} finally {
			if(out!=null)
				out.close();
		}
	}

	public static void deserialize(String playlist, ArrayList<Song> songs) throws IOException, ClassNotFoundException {
		InputStream input=new FileInputStream(database+playlist);
		ObjectInputStream in=null;
		try {
			in=new ObjectInputStream(input);
			while(true) {
				songs.add((Song)in.readObject());
			}
		} catch(EOFException e) {

		}
		finally {
			if(in!=null)
				in.close();
		}
	}

	public static String display(ArrayList<Song> songs) {
		if(songs.size()==0) {
			return("No Song Exists");
		}
		String res="";
		for(Song song:songs){
			// System.out.println(song);
			// System.out.println();
			res+=song+"\n";
		}
		return res;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		File f=new File(database);
		int flag=1;
		while(flag==1) {
			String[] files=f.list();
			System.out.println("Playlists:");
			for(String file:files) {
				System.out.println("\t"+file);
			}
			System.out.println();
			System.out.println("Enter the name of the playlist: ");
			String playlist=Reader.nextLine();
			ArrayList<Song> songs=new ArrayList<Song>();
			deserialize(playlist, songs);
			System.out.println("Number of Songs: "+songs.size());

			while(true) {
				System.out.println();
				System.out.println("Enter your option");
				System.out.println("\t1. Add");
				System.out.println("\t2. Delete");
				System.out.println("\t3. Search");
				System.out.println("\t4. Show");
				System.out.println("\t5. Back to menu");
				System.out.println("\t6. Exit");
				int choice=Reader.nextInt();
				Reader.nextLine();
				System.out.println();
				if(choice==1) {
					add(playlist, songs);
				}
				else if(choice==2) {
					delete(playlist, songs);
				}
				else if(choice==3) {
					search(songs);
				}
				else if(choice==4) {
					System.out.println(display(songs));
				}
				else if(choice==5) {
					break;
				}
				else if(choice==6) {
					flag=0;
					break;
				}
				else {
					System.out.println("Wrong option.");
					continue;
				}
			}
		}
	}
}
