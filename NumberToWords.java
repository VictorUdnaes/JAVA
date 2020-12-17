public class NumberToWords {

    public static void main(String[] args) {
        numberToWords(100);
    }
//----------------------------------------------------------------------------------------------------------------------

    public static void numberToWords (int number){
     int NewReverseNumber = reverse(number);
     if (number < 0) { System.out.println("Invalid value");}
     while (NewReverseNumber > 0) {

         int lastDigit = NewReverseNumber%10;
         switch (lastDigit) {
             case 0:
                 System.out.println("Zero");
                 break;
             case 1:
                 System.out.println("One");
                 break;
             case 2:
                 System.out.println("Two");
                 break;
             case 3:
                 System.out.println("Three");
                break;
             case 4:
                 System.out.println("Four");
                 break;
             case 5:
                 System.out.println("Five");
                 break;
             case 6:
                 System.out.println("Six");
                 break;
             case 7:
                 System.out.println("Seven");
                 break;
             case 8:
                 System.out.println("Eight");
                 break;
             case 9:
                 System.out.println("Nine");
                 break;
         }
         NewReverseNumber -=lastDigit;
         NewReverseNumber /= 10;
     }

        if((getDigitCount(number) - getDigitCount(reverseNumber)) != 0){
            for (int i = (getDigitCount(number) - getDigitCount(reverse(number))); i>0; i--) {
                System.out.println("Zero");
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------

    public static int reverse (int number) {
        boolean isNegative = false;
        int sum = 0;
        int lastDigit;

        if (number < 0) {
            isNegative = true;
            number *= -1;

        }
        while (number > 0) {
            sum *=10;
            lastDigit = number %10;
            number -=lastDigit;
            sum += lastDigit;
            number /=10;
        }
        if (isNegative) {
            sum *= -1;
        }
        return sum;
    }
//----------------------------------------------------------------------------------------------------------------------

    public static int getDigitCount (int number) {
        if (number < 0) {return -1;}
        int sum =0;

        while (number > 0) {
            int lastDigit = number % 10;
            switch (lastDigit) {
                case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
                    sum += 1;
            }
            number -=lastDigit;
            number /= 10;
        }
     return sum;
    }
}
//----------------------------------------------------------------------------------------------------------------------