/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import java.util.HashMap;


public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        HashMap<String, TrieNode> child = children;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            TrieNode t;
            if(child.containsKey(c)){
                t = child.get(c);
            }else{
                t = new TrieNode();
                child.put(String.valueOf(c), t);
            }

            child = t.children;

            //set leaf node
            if(i==s.length()-1)
                t.isWord = true;
        }
    }

    public boolean isWord(String s) {
        TrieNode t = searchNode(s);

        if(t != null && t.isWord)
            return true;
        else
            return false;
    }

    public TrieNode searchNode(String s){
        HashMap<String, TrieNode> child = children;
        TrieNode t = null;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(child.containsKey(c)){
                t = child.get(c);
                child = t.children;
            }else{
                return null;
            }
        }

        return t;
    }

    public String getAnyWordStartingWith(String s) {

        TrieNode t = searchNode(s);
        String output = s +"";
        HashMap<String,TrieNode> child;
        if(t==null){
            return null;
        }
        else{
            while(!t.isWord){
                child = t.children;
                Character nextKey = (Character)child.keySet().toArray()[0];
                output = output + nextKey;
                t = child.get(nextKey);
            }
        }
        return output;
    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }
}
