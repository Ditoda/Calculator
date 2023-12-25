import java.util.Scanner;

public class Calculator {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите орифметическую операцию");
        String string = scanner.nextLine();
        convertString(string);
    }

    static void convertString(String string){
        int operatorPlace = 0;
        String newString = string.replace(" ", "");

        for(int i = 0;i <= newString.length(); i++ ){
            char c = newString.charAt(i);
            if((c == '+' || c == '-' || c == '*' || c == '/') && i != 0){
                operatorPlace = i;
                break;
            }
        }

        char operator = newString.charAt(operatorPlace);
        String number1 = newString.substring(0,operatorPlace);
        String number2 = newString.substring(operatorPlace + 1);
        if(number1.matches(".*(0|1|2|3|4|5|6|7|8|9|10).*") && (number2.matches(".*(0|1|2|3|4|5|6|7|8|9|10).*"))){
            getAnswer(number1, number2, operator);
        }else{
            getRomanAnswer(number1, number2,operator);
        }
    }

    static void getAnswer(String number1, String number2,char operator){
        int num1 = 0;
        int num2 = 0;
        String oper = String.valueOf(operator);
        try{
            num1 = Integer.parseInt(number1);
            num2 = Integer.parseInt(number2);
            int answer = caclulation(num1, num2, oper);
            System.out.println("Ваш ответ: " + answer);
        }catch(NumberFormatException e){
            System.out.println("Данные введены не корректно1");
        }
    }

    static void getRomanAnswer(String number1, String number2, char operator){
        String oper = String.valueOf(operator);
        Roman num1;
        Roman num2;
        try {
            num1 = Roman.valueOf(number1);
            num2 = Roman.valueOf(number2);
            int answer = caclulation(num1.value, num2.value, oper);
            convertArabicToRoman(answer);
        } catch(IllegalArgumentException ex){
            System.out.println("Введены не корректные данные!");
        }
    }

    public static int caclulation(int num1, int num2, String oper){
        int answer = 0;
        if(num1 < 11 && num1 >= 0 && num2 < 11 && num2 >= 0) {
            switch (oper) {
                case "+":
                    answer = num1 + num2;
                    break;
                case "-":
                    answer = num1 - num2;
                    break;
                case "*":
                    answer = num1 * num2;
                    break;
                case "/":
                    answer = num1 / num2;
                    break;
                default:
                    System.out.println("Данные введнеы не корректно!");
            }
            return answer;
        }else{
            System.out.println("Число не проходит по диапозону!");
        }
        return 0;
    }

    public static String romanDigit(int n, String one, String five, String ten){

        if(n >= 1)
        {
            switch (n) {
                case 1:
                    return one;
                case 2:
                    return one + one;
                case 3:
                    return one + one + one;
                case 4:
                    return one + five;
                case 5:
                    return five;
                case 6:
                    return five + one;
                case 7:
                    return five + one + one;
                case 8:
                    return five + one + one + one;
                case 9:
                    return one + ten;
                default:
                    return "";

            }
        }
        return "";
    }



    public static String convertArabicToRoman(int number) {
        if (number < 0 || number > 3999) {
            System.out.println("Число не подходит диапозону");
            return "This number cannot be converted";
        }

        String romanOnes = romanDigit(number % 10, "I", "V", "X");
        number /= 10;

        String romanTens = romanDigit(number % 10, "X", "L", "C");
        number /= 10;

        String romanHundreds = romanDigit(number % 10, "C", "D", "M");
        number /= 10;

        String romanThousands = romanDigit(number % 10, "M", "", "");
        number /= 10;

        String result = romanThousands + romanHundreds + romanTens + romanOnes;
        System.out.println("Ваш ответ: "+ result);
        return (result);
    }


    public enum Roman{
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10),
        L(50), C(100), XL(40), XC(90);
        private final int value;
        private Roman(int value){
            this.value = value;
        }
        public int toInt() {
            return value;
        }
    }

}