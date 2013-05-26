package testCode;

class A extends B{
	static {
		System.out.println("Load A");
	}

	{
		System.out.println("Load1 A");
	}
	
	public A() {
		System.out.println("Create A");
	}
}