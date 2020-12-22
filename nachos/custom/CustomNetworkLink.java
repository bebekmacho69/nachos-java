package nachos.custom;

import nachos.machine.Machine;
import nachos.machine.MalformedPacketException;
import nachos.machine.NetworkLink;
import nachos.machine.Packet;
import nachos.threads.Semaphore;

public class CustomNetworkLink {
	
	MyConsole_clean con;
	Semaphore sem = new Semaphore(0);
	private NetworkLink nl = Machine.networkLink();

	public CustomNetworkLink() {
		// runnables
		Runnable receive = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Packet receivePkt = nl.receive();
				
				if (receivePkt == null) {
					System.out.println("There is no messages");
					
				} else {
					byte[] data = receivePkt.contents;
					String receiveMsg = new String(data);
					System.out.println("Received message : " + receiveMsg);
				}
			}
		};
		Runnable send = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sem.V();
			}
		};
		nl.setInterruptHandlers(receive, send);
		
		// berfungsi untuk komunikasi antar os
		con = new MyConsole_clean();
		con.println("My Address : " + nl.getLinkAddress());
		int choose = 0;
		do {
			con.println("1. Send");
			con.println("2. Receive");
			con.println("3. Exit");
			
			choose = con.readInt();
			if (choose == 1) {
				con.print("Insert destination [integer] : ");
				int destination = con.readInt();
				con.print("Insert message [string] : ");
				String msg = con.readString();
				
				byte[] data = msg.getBytes();
				try {
					Packet sendPkt = new Packet(destination, nl.getLinkAddress(), data);
					nl.send(sendPkt);
					sem.P();
					/**
					 * while sending = pause this
					 * completed -> Runnable send
					 */
				} catch (MalformedPacketException e) {
					e.printStackTrace();
					System.out.println("Invalid send request");
				}
			}
			
			if (choose == 2) {
				Packet receivePkt = nl.receive();
				
				if (receivePkt == null) {
					con.println("There is no messages");
				} else {
					byte[] data = receivePkt.contents;
					String receiveMsg = new String(data);
					con.println("Received message : " + receiveMsg);
				}
			}
		} while (choose != 3);
	}

}
