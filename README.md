# CSC345-Group-Project: Huffman Encoder

This is the group project for CSC345 in Spring 2023 with Melanie Lotz.
The HuffmanEncoder class implements an algorithm to encode characters from strings following the Huffman encoding scheme, which encodes the characters using 
unique strings of binary, with the shortest strings being used for the most frequently occuring characters and the longest for the least frequently occurring, 
thus maximizing efficient space use during compression and encoding. The class provides the algorithm for encoding the desired string, getting the root of the
Huffman tree used in encoding, and getting the entire mapping of the Huffman tree to see the binary encoding for each respective character.

Authors:

Jackson Burns

Daniel Cervantes

Amber Quinn

## Table of Contents

- Requirements
- What Is Being Done
- Configuration and Running

## Requirements

No special requirements as necessary additional classes are imported within HuffmanEncoder.java.

## What Is Being Done

HuffmanEncoder.java begins by importing the Comparator, HashMap and PriorityQueue classes from java.util to be used as the backbones of the algorithm. A Node
class is also declared within the file to form the backbone of the Huffman Tree. A private HashMap of String and integer pairs, root Node, and ArrayList of 
Nodes are declared at the creation of the class.

When the constructor is called with the passed String phrase, it is first trimmed of its leading and trailing spaces to make encoding simpler. The HashMap map
and ArrayList nodes are then initialized before processing phrase. The algorithm then iterates through phrase character by character, checking if the character
is already in map, in which case it increments the occurrences integer by one. If the character is not in the tree yet, it adds the character to the map with
the occurrences counter set to 1. Once processed, the constructor calls the constructTree method.

constructTree is a private method that begins building the Huffman Tree by initializing a PriorityQueue of nodes called pq, taking advantage of the Comparator
class to make comparing occurrences possible. It then iterates through map, making a new Node n with the character key and occurrences integer as the two 
values within the Node. The Node is then added to pq and nodes, with pq placing the Node in the correct priority location based on occurrences. Afterwards, the
lowest two priority Nodes are removed from pq, their occurrences added together to make a new Node that is added to pq, with the characters becoming the
respective left and right children Nodes of the newly created parent. This is repeated until one Node is left in pq, which becomes the root of the Huffman Tree.

Once constructTree wraps up, setEncoding is called to recursively process the Huffman Tree and put the encodings into the Node of the character it is associated
with. The base case checks if a leaf's child (null) is reached, in which case it returns. If not, it proceeds to iterate through the ArrayList of integers
called path, concatenating each binary digit together to get the full encoding before adding it to the Node's encoding field, a String. Once the encoding of the
current Node is completed, it creates a new path ArrayList, using the old path list, and adds 0 to the end before recursing to the left child and repeating the 
above. Once the recursion is returned, it removes the 0 from the end of path and adds 1 to it before recursing to the right child and repeating. This is done 
until the entire tree is processed and each node has its proper Huffman encoding. Once here, the constructor's work is done.

The method getRoot simply returns the root Node of the Huffman Tree by returning the private field root.

The method getMap returns the HashMap of characters and occurrences by returning the private field map.

The method getNodes returns the ArrayList containing all of the Nodes of the Huffman tree by returning the private field nodes.

## Configuration and Running

1. Ensure HuffmanEncoder.java is in the same directory as the file you wish to use the encoder in or import the HuffmanEncoder class from the file.
2. Prepare the string you wish to encode. If from a file, use Java's Scanner to get the lines of the string one by one to pass to the constructor.
3. Create a new HuffmanEncoder object by typing "HuffmanEncoder HuffmanEncoderName = new HuffmanEncoder(String to be encoded);", which will immediately begin
  encoding the passed string based on the Huffman encoding scheme.
4. To get the HashMap that displays the frequency of the individual characters in the string, use "HuffmanEncoderName.getMap", which can then be printed
  (such as in HuffmanEncoderTest.java) or analyzed.
5. To view the individual encodings of each character, one can iterate through the individual nodes of the Huffman Tree by using a for loop as shown in
  HuffmanEncoderTest.java as HuffmanEncoderName.getNodes will return the nodes in an ArrayList format where iterating through is possible.
