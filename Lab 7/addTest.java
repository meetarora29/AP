//Meet Arora 2016055

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import main.*;
import java.util.*;
import java.io.*;

public class addTest {

	@Test
	public void testAdd() throws IOException, ClassNotFoundException {
		ArrayList<Song> songs=new ArrayList<Song>();
		lab7 obj=new lab7();
		lab7.deserialize("Day Night.txt", songs);
		lab7.add("Day Night.txt", songs);
		String result="Title: Day\nSinger: Ed\nDuration: 230\nTitle: Night\nSinger: Ed\nDuration: 210\nTitle: Wildling\nSinger: Jon Snow\nDuration: 25\nTitle: temp\nSinger: temp\nDuration: 10\n";
		assertEquals(result, lab7.display(songs));
	}
}
