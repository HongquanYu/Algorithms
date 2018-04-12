package List;
public class UnitTest {
	public static void main(String[] args) {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		
		list.insertFirst("first");
		list.insertFirst("second");
		list.insertFirst("third");
		list.insertFirst("fourth");
		list.insertFirst("fifth");
		list.insertFirst("sixth");
		list.insertFirst("seventh");
		list.insertFirst("eighth");
		list.insertFirst("nineth");
		list.insertFirst("tenth");
		
		System.out.println("Elements in the list:");
		list.traverse();
		
		System.out.println("Size? " + list.size());
		System.out.println("is Empty? " + list.isEmpty());
		System.out.println("Contain fifth? " + list.contains("fifth"));
		System.out.println("Contain fourth? " + list.contains("fourth"));
		System.out.println("Contain first? " + list.contains("first"));
		System.out.println("Contain second? " + list.contains("second"));
		System.out.println("Contain third? " + list.contains("third"));
		System.out.println("Contain tenth? " + list.contains("tenth"));
		System.out.println("Contain nineth? " + list.contains("nineth"));
		System.out.println("Contain eighth? " + list.contains("eighth"));
		System.out.println("Contain seventh? " + list.contains("seventh"));
		System.out.println("Contain sixth? " + list.contains("sixth"));
		
		list.delete("nineth");
		System.out.println("after deletion, Contain nineth? " + list.contains("nineth"));
		
		list.update("second", "2th");
		System.out.println("after update, Contain second? " + list.contains("second"));
		System.out.println("after update, Contain 2th? " + list.contains("2th"));
		
		System.out.println("Elements in the list:");
		list.traverse();
		
	}
}
