package testCode;

class B extends Object{
	static {
		System.out.println("Load B");
	}
	
	{
		System.out.println("Load1 B");
	}
	
	public B() {
		System.out.println("Create B");
	}
}