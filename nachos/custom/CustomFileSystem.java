package nachos.custom;

import nachos.machine.FileSystem;
import nachos.machine.Machine;
import nachos.machine.OpenFile;

public class CustomFileSystem {
	
	private FileSystem fs = Machine.stubFileSystem();
	private MyConsole_clean con = new MyConsole_clean();

	public CustomFileSystem() {
		/*
		 * Available methods
		 * write("nama_file.txt");
		 * read("nam_file.txt");
		 */
		con.print("Enter a message : ");
		String message = con.readString();
		write("1nama_file.txt", message);
		read("1nama_file.txt");
	}
	
	public void write(String fileName, String messages) {
		// FileSystem : write
		OpenFile out = fs.open(fileName, false); // membuat file dengan nama yang ditentukan
		
		String input = messages + "\n"; // tambahan string di dalam file
		byte[] data = input.getBytes();
		
		if (out == null) {
			out = fs.open(fileName, true);
			out.write(data, 0, data.length);
		} else {
			// append if exists
			out.write(out.length(), data, 0, data.length);
		}
	}
	
	public void read(String fileName) {
		// FileSystem : open
		OpenFile in = fs.open(fileName, false);
		byte[] data = new byte[9999];
		
		in.read(data, 0, in.length());
		String str = new String(data);
		con.println("Isi dari file : \n" + str);
	}

}
