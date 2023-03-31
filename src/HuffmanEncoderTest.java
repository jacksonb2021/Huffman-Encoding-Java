import org.junit.Test;

public class HuffmanEncoderTest {

	@Test
	public void testThing(){
		HuffmanEncoder h = new HuffmanEncoder("hello");
		System.out.println(h.getMap());
		for(HuffmanEncoder.Node n :h.getNodes()){
			System.out.println("node: "+n.getCharacter()+" encoding: "+n.getEncoding());
		}
	}

	@Test
	public void getRoot() {
	}

	@Test
	public void getMap() {
	}

	@Test
	public void getTotalOccurances() {
	}

	@Test
	public void setEncoding() {
	}

	@Test
	public void findEncoding() {
		HuffmanEncoder h = new HuffmanEncoder("Hello my name is Jackson Burns");

	}
}