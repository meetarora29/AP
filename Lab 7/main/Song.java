//Meet Arora 2016055

package main;

import java.io.*;
import java.lang.*;

public class Song implements Serializable {
	private String name, singer, duration;
	private static final long serialVersionUID = 33333l;

	public Song(String name, String singer, String duration) {
		this.name=name;
		this.singer=singer;
		this.duration=duration;
	}

	public String toString() {
		return "Title: "+name+"\nSinger: "+singer+"\nDuration: "+duration;
	}

	public String getTitle() {
		return name;
	}
}
