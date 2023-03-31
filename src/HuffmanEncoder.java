import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.*;
public class HuffmanEncoder {
    private HashMap<String,Integer> map;
    private int totalOccurances;
    private Node root;
    private ArrayList<Node> nodes;

    public HuffmanEncoder(String phrase){
        phrase = phrase.trim();
        totalOccurances=0;
        map = new HashMap<>();
        nodes = new ArrayList<>();
        //create frequencytable
        for(int i=0;i<phrase.length();i++){
            String character = ""+phrase.charAt(i);
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
    public HashMap<String,Integer> getMap(){
        return map;
    }

    public int getTotalOccurances(){
        return totalOccurances;
    }

    private void constructTree(){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getOccurrences));
        for(Map.Entry<String,Integer> s: map.entrySet()){
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
        System.out.println("nodes: "+nodes);

    }

    public void setEncoding(Node node, ArrayList<Integer> path) {
        if (node == null) {
            return; // base case: end of tree
        }
        node.setEncoding(path.toString());
        ArrayList<Integer> leftPath = new ArrayList<>(path);
        leftPath.add(0);
        ArrayList<Integer> rightPath = new ArrayList<>(path);
        rightPath.add(1);
        setEncoding(node.left(), leftPath);
        setEncoding(node.right(), rightPath);
    }

    public String findCharEncoding(String c){
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






    class Node{
        private String encoding;
        private Node left;
        private Node right;
        private String character;
        private int occurrences;
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
    }



}
