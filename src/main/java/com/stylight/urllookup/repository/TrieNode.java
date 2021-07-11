package com.stylight.urllookup.repository;

class TrieNode {
    final int ASCII_CHARACTERS = 128;

    TrieNode[] children = new TrieNode[ASCII_CHARACTERS];
    boolean isEndOfWord;
    TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < ASCII_CHARACTERS; i++)
            children[i] = null;
    }

    public void insert(String key,TrieNode root){
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level);
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        pCrawl.isEndOfWord = true;
    }

    public String search(String key,TrieNode root)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;
        String match = null;
        StringBuilder builder = new StringBuilder();

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level);
            if (pCrawl.children[index] == null){
                return match;
            }

            builder.append((char) (index));
            if(pCrawl.children[index].isEndOfWord){
                match = builder.toString();
            }
            pCrawl = pCrawl.children[index];
        }

        return match;
    }
};
