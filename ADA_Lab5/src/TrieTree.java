import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class TrieTree {
    private TrieNode root;
    private ArrayList<String> prefixWords = new ArrayList<>();

    public void addNode(TrieNode root, String word) {
        TrieNode node = root;
        int characterIdx = 0;
        if (word == null) {
            return;
        }
        while (characterIdx < word.length()) {
            boolean found = false;
            for (TrieNode children : node.getChildren()) {
                if (children.getLetter() == word.charAt(characterIdx)) {
                    node = children;
                    found = true;
                    break;
                }
            }
            if (!found) {
                TrieNode newNode = new TrieNode();
                newNode.setLetter(word.charAt(characterIdx));
                if (characterIdx == word.length() - 1) {
                    newNode.setWord(true);
                }
                node.addChildren(newNode);
                node = newNode;
            }
            characterIdx++;
        }
    }

    public void writeWord(String path, String word, BufferedWriter writer) {
        try {
            writer.append(word);
            writer.append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printTrie(TrieNode root, StringBuilder word, BufferedWriter writer) {
        if (root.isWord()) {
            writeWord("output.txt", word.toString(), writer);
        }

        for (TrieNode child : root.getChildren()) {
            word.append(child.getLetter());
            printTrie(child, word, writer);
            word.deleteCharAt(word.length() - 1);
        }
    }

    public void printPrefixWords(TrieNode root, StringBuilder word) {
        if (root.isWord()) {
            prefixWords.add(word.toString());
        }

        for (TrieNode child : root.getChildren()) {
            word.append(child.getLetter());
            printPrefixWords(child, word);
            word.deleteCharAt(word.length() - 1);
        }
    }

    public void searchPrefix(TrieNode root, String word) {
        int charIdx = 0;
        TrieNode node = root;
        while (charIdx < word.length()) {
            boolean found = false;
            for (TrieNode children : node.getChildren()) {
                if (children.getLetter() == word.charAt(charIdx)) {
                    node = children;
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Prefix is not in trie");
                return;
            }
            charIdx++;
        }
        printPrefixWords(node, new StringBuilder(word));
    }


    public static void main(String[] args) throws IOException {

        TrieTree trie = new TrieTree();

        /*
           READ WORDS FROM INPUT FILE
         */
        addWordsToTrie("input.txt", trie);

        /* PRINT TRIE TO FILE
           BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
           trie.printTrie(trie.root, new StringBuilder(), writer);
           writer.close();
        */


        /* READS ONE WORD AND PRINTS
           WORDS WHICH HAVE THAT PREFIX
           TO SYSTEM.OUT
         */
        System.out.println("Introduce prefix:");
        BufferedReader sysReader = new BufferedReader(new InputStreamReader(System.in));
        String word = sysReader.readLine();
        trie.searchPrefix(trie.root, word);

        trie.prefixWords.sort(Comparator.comparingInt(String::length));
        for(String wrd : trie.prefixWords) {
            System.out.println(wrd);
        }
    }

    private static void addWordsToTrie(String path, TrieTree trie) {
        TrieNode root = new TrieNode();
        trie.root = root;
        File file = new File(path);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String word = reader.nextLine();
                trie.addNode(trie.root, word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
