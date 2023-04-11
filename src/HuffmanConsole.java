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
    }
}
