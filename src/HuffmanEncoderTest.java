import org.junit.Test;

public class HuffmanEncoderTest {

	@Test
	public void testThing(){
		HuffmanEncoder h = new HuffmanEncoder("hello");
		System.out.println(h.getMap());
		for(HuffmanEncoder.Node n :h.getNodes()){
			System.out.println(n);
		}
		String str = "Hello my name is Jackson Burns.";
		System.out.println(str.length());
		HuffmanEncoder j = new HuffmanEncoder(str);
		for(HuffmanEncoder.Node n :j.getNodes()){
			System.out.println(n);
		}

	}

	@Test
	public void getRoot() {
	}

	@Test
	public void getMap() {
	}


	@Test
	public void setEncoding() {
	}

	@Test
	public void findEncoding() {
		HuffmanEncoder h = new HuffmanEncoder("Hello my name is Jackson Burns");

	}
}