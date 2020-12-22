package nachos.custom;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class MyConsole_clean {
	
	char temp;
	Semaphore sem = new Semaphore(temp);
	SerialConsole sc = Machine.console();

	public MyConsole_clean() {
		Runnable receive = new Runnable() {			
			@Override
			public void run() {
				temp = (char) sc.readByte();
				sem.V();
			}
		};
		
		Runnable send = new Runnable() {
			@Override
			public void run() {
				// TODO
				sem.V();
			}
		};
		sc.setInterruptHandlers(receive, send);
	}
	
	public String readString() {
		String str = "";
		do {
			sem.P();
			if (temp == '\n') break;
			str = str + temp;
		} while (true);
		return str;
	}
	
	public int readInt() {
		Integer parsedInt = 0;
		try {
			parsedInt = Integer.parseInt(readString());
		} catch (Exception e) {
			System.out.println("Input invalid");
		}
		return parsedInt;
	}
	
	public void print(String str) {
		for(int i=0;i< str.length(); i++) {
			sc.writeByte(str.charAt(i));
			sem.P();
		}
	}
	
	public void println(String str) {
		print(str + "\n");
	}
}
