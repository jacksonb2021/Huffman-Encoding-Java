import java.util.HashMap;

public class HuffmanEncoder {
    private HashMap<Character,Integer> map;
    private int totalOccurances;

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
    }

    public HashMap<Character,Integer> getMap(){
        return map;
    }

    public int getTotalOccurances(){
        return totalOccurances;
    }

    private void constructTree(){

    }




    private class Node{
        private Node left;
        private Node right;
        private char character;
        private int occurrences;
        public Node(char character,int occurrences){
            this.character = character;
            this.occurrences=occurrences;
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
