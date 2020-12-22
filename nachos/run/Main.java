package nachos.run;

import nachos.machine.Machine;
import nachos.machine.Timer;
import nachos.serialconsole.MyConsole;
import nachos.serialconsole.MyConsole_clean;
import nachos.threads.Semaphore;

public class Main {
	// using configuration 1
	
	MyConsole console;
	MyConsole_clean consoleClean;
	Timer timer;
	int loops = 0;
	Semaphore sem = new Semaphore(0);
	
	public Main() {
		// myTimer(); myConsole();
	}
	
	public void myTimer() {
		timer = Machine.timer();
		Runnable timerHandler = new Runnable() {
			@Override
			public void run() {
				loops++;
				System.out.println("timer : " + loops);
				System.out.println("timer.getTime() : " + timer.getTime());
			}
		};
		timer.setInterruptHandler(timerHandler);
		// readString / semaphore to pause 
		sem.P();
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
