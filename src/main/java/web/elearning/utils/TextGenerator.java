package web.elearning.utils;

import java.util.Random;

public class TextGenerator {
    public static String genText(String input) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        int length = 6 + random.nextInt(3); // Sinh độ dài từ 6 đến 8

        StringBuilder generatedPart = new StringBuilder();
        for (int i = 0; i < length; i++) {
            generatedPart.append(characters.charAt(random.nextInt(characters.length())));
        }
        return input + generatedPart.toString();
    }

    public static void main(String[] args) {
        String result = genText("PW");
        System.out.println(result); // Ví dụ: Prefix_a8B2zX
    }
}
