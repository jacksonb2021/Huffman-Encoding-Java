import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.*;
public class HuffmanEncoder {
    private HashMap<Character,Integer> map;
    private int totalOccurances;
    private Node root;
    private ArrayList<Node> nodes;

    public HuffmanEncoder(String phrase){
        phrase = phrase.trim();
        totalOccurances=0;
        map = new HashMap<>();
        //create frequencytable
        for(int i=0;i<phrase.length();i++){
            char character = phrase.charAt(i);
            if(map.containsKey(character)){
                map.put(character,map.get(character)+1);
            } else {
                map.put(character,1);

            }
            totalOccurances++;
        }
        constructTree();
        setEncoding(root, new ArrayList<>());
    }

    public Node getRoot(){
        return root;
    }
    public HashMap<Character,Integer> getMap(){
        return map;
    }

    public int getTotalOccurances(){
        return totalOccurances;
    }

    private void constructTree(){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(Node::getOccurrences));
        for(Map.Entry<Character,Integer> s: map.entrySet()){
            Node n = new Node(s.getKey(),s.getValue());
            pq.add(n);
            nodes.add(n);
        }

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

    public void setEncoding(Node node, ArrayList<Integer> path) {
        if (node == null) {
            return; // base case: end of tree
        }
        node.setEncoding(path.toString()); // set the encoding for the current node
        ArrayList<Integer> leftPath = new ArrayList<>(path); // create copy of current path for left branch
        leftPath.add(0); // add 0 to path for left branch
        ArrayList<Integer> rightPath = new ArrayList<>(path); // create copy of current path for right branch
        rightPath.add(1); // add 1 to path for right branch
        setEncoding(node.left(), leftPath); // traverse left branch recursively
        setEncoding(node.right(), rightPath); // traverse right branch recursively
    }

    public String findEncoding(char c){
        for(Node n : nodes){
            if(n.getCharacter()==c){
                return n.getEncoding();
            }
        }
        return "character not found";
    }

    public ArrayList<Node> getNodes(){
        return nodes;
    }
//    private ArrayList<Integer> findPath(Node node, char character, ArrayList<Integer> path) {
//        if (node == null) {
//            return null; // base case: node not found
//        }
//        if (node.getCharacter() == character) {
//            return path; // base case: node found
//        }
//        ArrayList<Integer> leftPath = new ArrayList<>(path); // create copy of current path for left branch
//        leftPath.add(0); // add 0 to path for left branch
//        ArrayList<Integer> rightPath = new ArrayList<>(path); // create copy of current path for right branch
//        rightPath.add(1); // add 1 to path for right branch
//        ArrayList<Integer> result = findPath(node.left(), character, leftPath); // search left branch recursively
//        if (result != null) {
//            return result; // node found in left branch
//        }
//        return findPath(node.right(), character, rightPath); // search right branch recursively
//    }





    private class Node{
        private String encoding;
        private Node left;
        private Node right;
        private char character;
        private int occurrences;
        public Node(Character character, int occurrences){
            this.character = character;
            this.occurrences=occurrences;
        }
        public void setEncoding(String str){
            this.encoding = str;
        }
        public String getEncoding(){
            return encoding;
        }

        public char getCharacter(){
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
    }



}
