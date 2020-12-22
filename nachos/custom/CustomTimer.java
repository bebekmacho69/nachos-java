package nachos.custom;

import nachos.machine.Machine;
import nachos.machine.Timer;
import nachos.threads.Semaphore;

public class CustomTimer {
	
	Timer timer;
	int loops = 0;
	Semaphore sem = new Semaphore(0);

	public CustomTimer() {
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

}
