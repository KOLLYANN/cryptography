package ru.cript;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BrutForce {

    public static void decipher(Path path, String alphabet) {


        List<Character> ciphertext = Main.readFileChar(path);

        StringBuilder stringBuilder = new StringBuilder();

        for (Character character : ciphertext) {
            stringBuilder.append(character);
        }

        String iText = String.valueOf(stringBuilder);

        StringBuilder readyText = new StringBuilder();

        List<String> stringList = new ArrayList<>();


        int point = 0;
        int key = 0;
        while (key <= alphabet.length() - 1) {
            for (int i = 0; i < iText.length(); i++) {
                for (int j = 0; j < alphabet.length(); j++) {
                    if (alphabet.charAt(j) == iText.charAt(i)) {
                        if (j - key >= 0) {
                            readyText.append(alphabet.charAt(j - key));
                        } else {
                            int count = alphabet.length() - (-j + key);
                            readyText.append(alphabet.charAt(count));
                        }
                    }
                }
            }

            Scanner scanner = new Scanner(readyText.toString()).useDelimiter(" ");
            while (scanner.hasNext()) {
                stringList.add(scanner.next());
            }

            for (String s : stringList) {
                if (s.length() < 11 && s.length() > 4
                        && (s.charAt(s.length() - 1) == ',' || s.charAt(s.length() - 1) == '.')
                        && (s.charAt(0) != ',' && s.charAt(0) != '!')) {
                    point++;
                }
                double res = readyText.length() / (double) point / 1000.0;
                if (res < 0.5) {
                    System.out.println("KEY " + key);
                    return;
                }
            }
            key++;
            readyText.setLength(0);
            stringList.clear();
            point = 0;
        }
    }
}

