package nachos.customFileManager;

import java.util.Vector;

import nachos.custom.File;
import nachos.machine.Machine;
import nachos.machine.MalformedPacketException;
import nachos.machine.NetworkLink;
import nachos.machine.Packet;
import nachos.threads.Semaphore;

public class CustomFileManager_NetworkLink {
	
	private NetworkLink NL;
	private Semaphore sm = new Semaphore(0);

	public CustomFileManager_NetworkLink(Vector<File> fileList) {
		NL = Machine.networkLink();
		
		Runnable receive = new Runnable() {
			@Override
			public void run() {
				File f = receivePacket();
				fileList.add(f);
				System.out.println("New file received : " + f.getName());
				System.out.println("Press any key to dismiss");
				sm.V();
			}
		};
		Runnable send = new Runnable() {
			@Override
			public void run() {
				sm.V();
			}
		};
		
		NL.setInterruptHandlers(receive, send);
	}
	
	public File receivePacket() {
		// new file received : fileName
		Packet pkt = NL.receive();
		byte[] contentByte = pkt.contents;	
		String content = new String(contentByte);
		
		// name.txt#150#word
		String[] contentArr = content.split("#", 3);
		String name = contentArr[0];
		int size = Integer.parseInt(contentArr[1]);
		String type = contentArr[2];
		
		File f = new File(name, type, size);
		return f;
	}
	
	public void sendPacket(int destination, File file) {
		// name.txt#150#word
		String str = new String(file.getName() + "#" + file.getSize() + "#" + file.getType());
		// Byte[]
		byte[] strByte = str.getBytes();
		// send packet
		try {
			Packet pkt = new Packet(destination, NL.getLinkAddress(), strByte);
			NL.send(pkt);
		} catch (MalformedPacketException e) {
			e.printStackTrace();
		}
		sm.P();
	}
	
	public NetworkLink getNl() {
		return NL;
	}
	
	public void setNl(NetworkLink NL) {
		this.NL = NL;
	}
	

}
;