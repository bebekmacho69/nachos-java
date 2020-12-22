package nachos.customFileManager;

import java.util.Vector;

import nachos.custom.File;
import nachos.custom.MyConsole_clean;
import nachos.machine.Machine;
import nachos.threads.KThread;

public class CustomFileManager {
	
	private Vector<File> fileList = new Vector<File>();

	MyConsole_clean con = new MyConsole_clean();
	CustomFileManager_NetworkLink cfm_nl = new CustomFileManager_NetworkLink(fileList);
	
	public CustomFileManager() {
		// TODO Auto-generated constructor stub
		int menu = -1;
		do {
			menu();
			menu = con.readInt();
			if (menu == 1) {
				send();
			}
			if (menu == 2) {
				view();
			}
		} while (menu != 3);
		con.println("Ticks of time : " + Machine.timer().getTime());
		Machine.terminate();
	}
	
	private void menu() {
		con.println("Computer Number : " + cfm_nl.getNl().getLinkAddress());
		con.println("File Manager");
		con.println("1. Send File");
		con.println("2. View Existing File(s)");
		con.println("3. Exit");
	}
	
	private void view() {
		if(fileList.isEmpty()) {
			con.println("There is no available file.");
		} else {
			con.println("My File(s)");
			for(int i=0;i < fileList.size(); i++) {
				new KThread(fileList.get(i)).fork();
			}
		}
		con.readString();
	}
	
	private void send() {
		String name, type;
		int size;
		int destination;
		
		do {
			con.print("Enter file name [must contain '.'] : ");
			name = con.readString();
		} while(!name.contains(".") || name.startsWith(".") || name.endsWith("."));
		
		do {
			con.print("Enter file size [1-500 MB]: ");
			size = con.readInt();
		} while(size < 1 || size > 500);
		
		do {
			con.print("Enter file type [Word | Excel | Unspecified] : ");
			type = con.readString();
		} while (!(type.equals("Word") || type.equals("Excel") || type.equals("Unspecified")));
		
		con.print("Send to computer : ");
		destination = con.readInt();
		
		// send file to destination via NetworkLink
		File file = new File(name, type, size);
		cfm_nl.sendPacket(destination, file);
		con.println("File sent!");
		con.readString();
	}

}
