package nachos.serialconsole;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class MyConsole {
	
	char temp;
	/**
	 * semaphore = pemberi sinyal/tanda jadi program siapa yg jalan duluan
	 * semaphore = mengatur sinkronisasi dari jalan nya urutan proses aplikasi
	 * operations = 
	 * 	P -> pause/wait -> 0 (decrement/--)
	 * 	V -> Resume		-> 1 (increment/++)
	 * contoh dari kasus ini, 
	 * MyConsole sedang membaca char pertama (H), diperlukan semaphore untuk menghold pembacaan char selanjutnya (e) dan seterusnya
	 */
	Semaphore sem = new Semaphore(temp);
	
	
	// initializing built-in objects
	SerialConsole sc = Machine.console();
	// SerialConsole
	// Send -> byte per byte
	// Receive -> byte per byte

	public MyConsole() {
		// interrupts
		Runnable receive = new Runnable() {
			// bakal dipanggin setiap kali byte datang dan di read dengan readByte	
			@Override
			public void run() {
				/**
				 * readByte = membaca satu per satu, jadi jika ada char budi, maka akan dibaca satu per satu (b,u,d,i)
				 * 
				 */
				temp = (char) sc.readByte();
				// Reading char
				sem.V(); // resume process
			}
		};
		
		Runnable send = new Runnable() {
			// kebalikan dari receive
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sem.V();
			}
		};
		sc.setInterruptHandlers(receive, send);
	}
	
	public String readString() {
		String str = "";
		/**
		 * input : Hello\n
		 * H
		 * He
		 * Hel
		 * Hell
		 * Hello
		 * Hello\n <- break;
		 */
		
		// Byte masuk, oper ke runnable receive
		// this -> myConsole() interruptHandlers(receive) -> myConsole() receive.run
		do {
			sem.P(); // trigger pause process
			if (temp == '\n') break;
			str = str + temp; // appending string
		} while(true);
		return str;
	}
	
	public int readInt() {
		// menggunakan fungsi readString() kemudian di typecast dengan try/catch untuk validasi
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
			// menuliskan karakter yang diberikan
			sc.writeByte(str.charAt(i));
			sem.P();
		}
	}
	
	public void println(String str) {
		print(str + "\n");
	}

}
