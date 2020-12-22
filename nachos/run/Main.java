package nachos.run;

import nachos.serialconsole.MyConsole;
import nachos.serialconsole.MyConsole_clean;

public class Main {
	// using configuration 1
	
	MyConsole console = new MyConsole();
	MyConsole_clean consoleClean = new MyConsole_clean();
	
	public Main() {
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
