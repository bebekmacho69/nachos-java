package nachos.customFileManager;

import nachos.threads.Scheduler;
import nachos.threads.ThreadQueue;

public class CustomFileManager_Scheduler extends Scheduler{

	public CustomFileManager_Scheduler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ThreadQueue newThreadQueue(boolean transferPriority) {
		// TODO Auto-generated method stub
		return new CustomFileManager_ThreadQueue();
	}

}
