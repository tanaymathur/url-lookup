package com.stylight.urllookup.repository;

import org.springframework.stereotype.Component;

@Component
public class ParameterizedUrlTrie extends TrieNode {
    TrieNode root = new TrieNode();

    public void insert(String key) {
        super.insert(key, root);
    }

    public String search(String key) {
        return super.search(key, root);
    }
}
