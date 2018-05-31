// this solution onlye make 19/39 cases passed, why?? 

class Solution {
//     DFS
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    
    public void buildMap(List<String> wordList, String beginWord) {     
        for(String word: wordList) {
            List<String> nList = new LinkedList<String>();        
            map.put(word, nList);
            for (String another: wordList) {
                if (diff(word, another) == 1) {
                    // here add to it
                    map.get(word).add(another);
                }
            }           
        }
      // oh very important here!!! 
        if (!map.containsKey(beginWord)) {
            List<String> nList = new LinkedList<String>();
            
            map.put(beginWord, nList);
            for (String another: wordList) {
                if (diff(beginWord, another) == 1) {
                    // here add to it
                    map.get(beginWord).add(another);
                }
            }
        }   
    }
    
    public int diff(String s1, String s2) {
        int counter = 0;
        for (int i = 0; i < s1.length(); i ++) {
            if (s1.charAt(i) != s2.charAt(i)) counter ++;
        }
        return counter;
    }
    // main fucntion here
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // first build this word map, initiate values; then DFS, then return results;
        buildMap(wordList, beginWord);
        // currList here is the list of strings found to bridge from begin to end, of course at first is empty
        List<String> currList = new LinkedList<String>();
        // also inital usedString to track used String, so it wonnt bridge back to cause infinit loop, use a HashSet here cus its simpe and O(1) time
        HashSet<String> usedString = new HashSet<String>();
        // also initiate the results here so we can return duh motherfuka(practically dont care what other people think, working through it); a list of list of string, array of array, a wonderful matrix!! love it, well I never know to used linkedList or arraylist, cus I dont know java datastructure that well, well it seems that LinkedList is ok, fine.. but why, will ArrayList work??? 
        List<List<String>> res = new LinkedList<List<String>>(); // is that all?  can we trigger the DFS?? now??? no I need to add the beginWord in the usedString, very very important
        usedString.add(beginWord);
        // ok here, currList should contain the begin word !!!! cus apprarently 
        currList.add(beginWord);
        DFS(currList, usedString, res, endWord);
        return res;     
    }
    
    public void DFS(List<String> currList, HashSet<String> usedString, List<List<String>> results, String target) {
        String currString = currList.get(currList.size() - 1);
        if (currString.equals(target)) {
            // !!! make a copy of curr string, also consider when result is empty
            if (results.size() == 0 || results.get(0).size() == currList.size()) {
                results.add(new LinkedList<String>(currList));
            } else if (results.get(0).size() > currList.size()) {
                results.clear();
//                  forgot to add <String> in the LinkedList type before
                results.add(new LinkedList<String>(currList));
            }
            // a very smart condition here
        } else if (results.size() == 0 || currList.size() < results.get(0).size()) {
            // when hasnt get to the point yet
            // if (map.get(currString).size() > 0) {
                for (String s: map.get(currString)) {
                    // also check if used!!!! smart
                    if (!usedString.contains(s)) {
                        // !! attend, add, recurse, remove!!! classic move!!!!
                        usedString.add(s);
                        currList.add(s);
                        DFS(currList, usedString, results, target);
                        usedString.remove(s);
                        currList.remove(currList.size() -1);
                    }
                }
            // }
        }
        
    }
    

    
}
