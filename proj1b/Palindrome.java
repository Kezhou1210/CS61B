public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }
    public boolean isPalindrome(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word,CharacterComparator cc){
        if(word == null || word.length() <=1){
            return true;
        }
        for(int i =0; i<word.length()/2; i++){
            if(!cc.equalChars(word.charAt(i), word.charAt(word.length()-1-i))){
                return false;
            }
        }
        return true;
    }
}
