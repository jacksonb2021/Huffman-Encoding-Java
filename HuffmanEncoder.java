import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.*;


/**
 * this class is used to encode a string using the Huffman algorithm. this ensures a lossless compression of a string
 * by encoding characters based on their frequency, with the most frequent characters being encoded with the fewest bits
 */
public class HuffmanEncoder {
    private HashMap<String,Integer> map;
    private String phrase;
    private Node root;
    private ArrayList<Node> nodes;

    public HuffmanEncoder(String phrase){
        phrase = phrase.trim();
        this.phrase = phrase;
        map = new HashMap<>();
        nodes = new ArrayList<>();
        //create frequencytable as a hashmap
        //character (as string) -> frequency (as int)
        for(int i=0;i<phrase.length();i++){
            String character = ""+phrase.charAt(i);
            if(map.containsKey(character)){
                map.put(character,map.get(character)+1);
            } else {
                map.put(character,1);
            }
        }
        constructTree();
        setEncoding(root, new ArrayList<>());
    }



    /**
     * This method returns the hashmap of the characters and their freq
     * @return - Hashmap<String, Integer>
     */
    public HashMap<String,Integer> getMap(){
        return map;
    }


    /**
     * This method returns the bit length of the standard encoding using 8 bit character lengths
     * @return - int of the length
     */
    public int getEncodingStandardSize(){
        return phrase.length()*8;

    }


    /**
     * This method returns a string of the huffman encoding of the phrase
     * @return - String of the encoding
     */
    public String encodedString(){
        String encoded = "";
        for(int i=0;i<phrase.length();i++){
            String character = ""+phrase.charAt(i);
            for(Node n : nodes){
                if(n.getCharacter().equals(character)){
                    encoded+=n.getEncoding()+" ";
                }
            }
        }
        return encoded;
    }


    /**
     * This method returns a string of the standard encoding of the phrase using 8 bit character lengths
     * @return - String of the encoding
     */
    public String standardEncoding(){
        String encoded = "";
        byte[] bytes = phrase.getBytes();
        for (byte b : bytes) {
            encoded+=(Integer.toBinaryString(b) + " ");
        }
        return encoded;
    }


    /**
     * This method returns the bit length of the huffman encoded string
     * @return - int of the length
     */
    public int getHuffmanEncodingSize(){
        int size = 0;
        for (Node n : nodes){
            size+=n.getOccurrences()*n.getEncoding().length();
        }
        return size;

    }


    /**
     * This method constructs the Huffman tree. It uses a priority queue to store the nodes in order of frequency.
     * It then removes the two nodes with the lowest frequency, creates a new node with the sum of the frequencies,
     * and null as the character. It then sets the left and right children of the new node to the two child nodes.
     * It adds this node back into the priority queue, and repeats until there is only one node left in the queue.
     */
    private void constructTree(){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getOccurrences));
        //add all nodes to priority queue. adds nodes to nodes arraylist, to be used in testing
        for(Map.Entry<String,Integer> s: map.entrySet()){
            Node n = new Node(s.getKey(),s.getValue());
            pq.add(n);
            nodes.add(n);
        }
        //remove two nodes with lowest frequency, create new node with sum of frequencies, set left and right children,
        //then readd the new node to the priority queue
        while(pq.size()>1){
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(null, left.getOccurrences()+right.getOccurrences());
            parent.setLeft(left);
            parent.setRight(right);
            pq.add(parent);
        }
        root = pq.poll();

    }

    /**
     * This method traverses the tree and stores the encoding of each character in the node.
     * @param node - Node object storing the character, encoding, children, and frequency
     * @param path - ArrayList of Integers representing the path to the node/current encoding
     */
    private void setEncoding(Node node, ArrayList<Integer> path) {
        if (node == null) {
            return; // base case: end of tree
        }
        //store current encoding path in node
        String str ="";
        for(int i=0;i<path.size();i++){
            str+=path.get(i);
        }
        node.setEncoding(str);
        //create a new path as a copy of current path, then try traversing both ends of the tree.
        // if the node is a leaf, it will have no children, and the path will be stored in the node.
        ArrayList<Integer> newPath = new ArrayList<>(path);
        newPath.add(0);
        setEncoding(node.left(), newPath);
        //change last element of path to 1, then try traversing right side of tree
        newPath.set(newPath.size()-1,1);
        setEncoding(node.right(), newPath);
    }



    /**
     * this method returns an arraylist of the nodes in the tree
     * @return - ArrayList of Node objects
     */
    public ArrayList<Node> getNodes(){
        return nodes;
    }


    /**
     * This class represents a node in the Huffman tree.
     * it stores the character, the number of occurrences of that character, and the encoding of that character.
     * It also stores the left and right children of the node.
     *
     */
    class Node{
        private String encoding;
        private Node left;
        private Node right;
        private final String character;
        private final int occurrences;
        public Node(String character, int occurrences){
            this.character = character;
            this.occurrences=occurrences;
        }
        public void setEncoding(String str){
            this.encoding = str;
        }
        public String getEncoding(){
            return encoding;
        }
        public String getCharacter(){
            return character;
        }
        public int getOccurrences(){
            return occurrences;
        }
        public Node left(){
            return left;
        }
        public Node right(){
            return right;
        }
        public void setLeft(Node left){
            this.left = left;
        }
        public void setRight(Node right){
            this.right = right;
        }
        public String toString(){
            return character+" "+occurrences+" "+encoding;
        }
    }



}
