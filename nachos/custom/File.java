package nachos.custom;

public class File implements Runnable{
	private String name, type;
	private int size;
	
	public File(String name, String type, int size) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("File Name : " + getName());
			System.out.println("File Size : " + getSize());
			System.out.println("File Type : " + getType() + "\n\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
