package nachos.run;

import java.util.Vector;

import nachos.custom.CustomFileSystem;
import nachos.custom.CustomNetworkLink;
import nachos.custom.CustomTimer;
import nachos.custom.File;
import nachos.custom.MyConsole;
import nachos.custom.MyConsole_clean;
import nachos.customFileManager.CustomFileManager;

public class Main {
	// using configuration 1
	
	// myconsole and timer
	MyConsole console;
	MyConsole_clean consoleClean;
	
	
	public Main() {
		/**
		 * Available methods : 
		 * myTimer();
		 * myConsole();
		 * networkLink();
		 * fileSystem();
		 * fileManager();
		 */
		fileManager();
		
	}
	
	public void fileManager() {
		CustomFileManager cfm = new CustomFileManager();
	}
	
	public void fileSystem() {
		CustomFileSystem cs = new CustomFileSystem();
	}
	
	public void networkLink() {
		CustomNetworkLink nl = new CustomNetworkLink();
	}
	
	public void myTimer() {
		CustomTimer timer = new CustomTimer();
	}
	
	public void myConsole() {
		console = new MyConsole();
		consoleClean = new MyConsole_clean();
		while (true) {
			consoleClean.println("Printout from nachos.run main class");
			consoleClean.print("Insert text : ");
			String string = consoleClean.readString();
			consoleClean.print("Insert Integer : ");
			Integer integer = consoleClean.readInt();
			
			System.out.println("\n\n");
			consoleClean.println("Insert Text : " + string);
			consoleClean.println("Insert Integer : " + integer);
		}
	}

}
