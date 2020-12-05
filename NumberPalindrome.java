public class NumberPalindrome {
    public static boolean isPalindrome (int number){
        if (number < 0){ number *= -1;}
        int reverse = 0;
        int lastDigit;
        while (number >= 0) {
            lastDigit = number % 10;
            reverse = reverse * 10;
            reverse += lastDigit;
            number = number/10;
            if (number != reverse) {
                continue;
            }
            return true;
        }
        return false;
    }



    public static void main(String[] args) {System.out.println(isPalindrome(1001));}

}
