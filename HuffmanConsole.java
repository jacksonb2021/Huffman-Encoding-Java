import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanConsole {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a string to encode");
        HuffmanEncoder h = new HuffmanEncoder(s.nextLine().strip());
        System.out.println("\nTesting Frequency Map");
        HashMap<String, Integer> map= h.getMap();
        System.out.println(map);


        System.out.println("\nTesting Encodings");


        ArrayList<HuffmanEncoder.Node> nodes = h.getNodes();
        for(HuffmanEncoder.Node n : nodes){
            System.out.println(n.toString());
        }


        System.out.println("\nSize comparison:");
        System.out.println("Standard encoding: " + h.getEncodingStandardSize() + " bits");
        System.out.println("Huffman encoding: " + h.getHuffmanEncodingSize() + " bits");
        System.out.println("Difference: " + (h.getEncodingStandardSize() - h.getHuffmanEncodingSize()) + " bits");
        double percent = (h.getHuffmanEncodingSize() / (double) h.getEncodingStandardSize()) * 100;
        System.out.println("Percent Compressed: " + Math.round(percent * 100.0) / 100.0+ "%");
        System.out.println();
        System.out.println("Standard encoding:\n" + h.standardEncoding());
        System.out.println("Huffman encoding:\n" + h.encodedString());

    }
}
