abstract class Animal {
	int age;
	abstract void cry();
}

class Dog extends Animal {
	void cry() {
		System.out.println("멍멍");
	}
}

class Cat extends Animal {
	void cry() {
		System.out.println("야옹");
	}
}

public class AbstractClassEx {

	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.cry();
		
		Cat cat = new Cat();
		cat.cry();
	}

}
