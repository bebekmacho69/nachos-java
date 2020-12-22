package nachos.customFileManager;

import java.util.Vector;

import nachos.threads.KThread;
import nachos.threads.ThreadQueue;

public class CustomFileManager_ThreadQueue extends ThreadQueue{
	
	private Vector<KThread> queueList = new Vector<KThread>();

	public CustomFileManager_ThreadQueue() {
		// TODO Auto-generated constructor stub
	}	

	@Override
	public void waitForAccess(KThread thread) {
		queueList.add(thread);
	}

	@Override
	public KThread nextThread() {
		// FIFO
		if(queueList.isEmpty()) return null;
		// LIFO
		else return queueList.remove(0);
	}

	@Override
	public void acquire(KThread thread) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

}
