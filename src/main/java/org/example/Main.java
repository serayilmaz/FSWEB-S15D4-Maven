package org.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    // Challenge I: Palindrome check
    public static boolean checkForPalindrome(String input) {
        if (input == null) return false;

        Deque<Character> deque = new ArrayDeque<>();

        // Sadece harf ve rakamları al, küçült
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                deque.addLast(Character.toLowerCase(ch));
            }
        }

        // İki uçtan karşılaştır
        while (deque.size() > 1) {
            char left = deque.removeFirst();
            char right = deque.removeLast();
            if (left != right) return false;
        }
        return true;
    }

    // Convert Decimal Numbers To Binary
    // En uygun veri yapısı: Stack (Deque ile stack gibi kullanıyoruz)
    public static String convertDecimalToBinary(int number) {
        if (number == 0) return "0";

        boolean isNegative = number < 0;
        int n = Math.abs(number);

        Deque<Integer> stack = new ArrayDeque<>();

        while (n > 0) {
            stack.push(n % 2);
            n /= 2;
        }

        StringBuilder sb = new StringBuilder();
        if (isNegative) sb.append("-");

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    // İstersen test için
    public static void main(String[] args) {
        System.out.println(checkForPalindrome("I did, did I?")); // true
        System.out.println(checkForPalindrome("Racecar")); // true
        System.out.println(checkForPalindrome("hello")); // false
        System.out.println(checkForPalindrome("Was it a car or a cat I saw ?")); // true

        System.out.println(convertDecimalToBinary(5));   // 101
        System.out.println(convertDecimalToBinary(6));   // 110
        System.out.println(convertDecimalToBinary(13));  // 1101
        System.out.println(convertDecimalToBinary(0));   // 0
        System.out.println(convertDecimalToBinary(-13)); // -1101
    }
}