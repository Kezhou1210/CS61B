import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            if (d != null) {
                actual += d.removeFirst();
            }
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testisPalindrome(){
        String A = "happy";
        assertFalse(palindrome.isPalindrome(A));
        String B = null;
        assertTrue(palindrome.isPalindrome(B));
    }

    CharacterComparator offbyone = new OffByOne();
    @Test
    public void testnewisPalindrome(){
        assertFalse(palindrome.isPalindrome("happy",offbyone));
        assertFalse(palindrome.isPalindrome("come",offbyone));
        assertTrue(palindrome.isPalindrome("hapah",offbyone));
        assertTrue(palindrome.isPalindrome(null,offbyone));
    }
}
