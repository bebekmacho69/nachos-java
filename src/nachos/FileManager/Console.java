package nachos.FileManager;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class Console {
	
	private SerialConsole sc = Machine.console(); 
	private char temp; 
	private Semaphore sem = new Semaphore(0); 
	
	public Console() {
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
				sem.V();
			}
		};
		
		sc.setInterruptHandlers(receive, send);
	}
	
	public String read() {
		String result = "";
		
		do {
			sem.P();
			if(temp != '\n') {
				result += temp;
			}
		}while(temp != '\n'); 
		
		return result; 
	}
	
	public int readInt() {
		int result = -1; 
		
		try {
			result = Integer.parseInt(read());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
		} 
		
		return result; 
	}
	
	public void print(String str) {
		for(int i = 0; i < str.length(); i++) {
			sc.writeByte(str.charAt(i));
			sem.P();
		}
	}
	
	public void println(String str) {
		print(str + "\n"); 
	}
}
