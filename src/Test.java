import java.util.*;



public class Test {

    static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение: ");
            String input = scanner.nextLine();

            try {
                String result = calc(input);
                System.out.println("Результат: " + result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            scanner.close();
        }

        static String calc(String input) {
            String[] parts = input.split("\\s+");
            if (parts.length != 3)
                throw new IllegalArgumentException("Неправильный формат выражения");

            int a, b;
            try {
                a = RomanToArabic(parts[0]);
                b = RomanToArabic(parts[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Неправильный формат чисел");
            }

            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
            }

            int result = switch (parts[1]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> a / b;


                default -> throw new IllegalArgumentException("Неподдерживаемая операция");

            };


            if (result < 0 || result > 10)
                throw new IllegalArgumentException("Результат выходит за пределы допустимых значений");

            return ArabicToRoman(result);
        }

        static int RomanToArabic(String roman) {
            Map<Character, Integer> map = new HashMap<>();
            map.put('I', 1);
            map.put('V', 5);
            map.put('X', 10);
            map.put('L', 50);
            map.put('C', 100);
            map.put('D', 500);
            map.put('M', 1000);

            int result = 0;
            int prevValue = 0;
            for (int i = roman.length() - 1; i >= 0; i--) {
                int value = map.get(roman.charAt(i));
                if (value < prevValue)
                    result -= value;
                else
                    result += value;
                prevValue = value;
            }
            return result;
        }

        static String ArabicToRoman(int number) {
            if (number < 1)
                throw new IllegalArgumentException("Римские цифры могут быть только положительными числами");

            String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
            int[] arabicValues = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

            StringBuilder roman = new StringBuilder();

            int i = romanSymbols.length - 1;
            while (number > 0) {
                if (number - arabicValues[i] >= 0) {
                    roman.append(romanSymbols[i]);
                    number -= arabicValues[i];
                } else {
                    i--;
                }
            }

            return roman.toString();
        }
    }

}
