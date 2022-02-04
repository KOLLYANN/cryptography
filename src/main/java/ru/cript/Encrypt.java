package ru.cript;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Encrypt {

    public static void encrypt(Path path, int key, String alphabet) {

        List<Character> characterList = Main.readFileChar(path);

        StringBuilder inputText = new StringBuilder();

        for (Character character : characterList) {
            inputText.append(character);
        }

        String iText = String.valueOf(inputText);

        StringBuilder readyCryptText = new StringBuilder();

        //key 0,...,alphabet.length()
        for (int i = 0; i < iText.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                if (alphabet.charAt(j) == iText.charAt(i)) {
                    if (j + key < alphabet.length()) {
                        readyCryptText.append(alphabet.charAt(j + key));
                    } else {
                        int count = (j + key) - alphabet.length();
                        readyCryptText.append(alphabet.charAt(count));
                    }
                }
            }
        }

        try (FileWriter fileWriter = new FileWriter(String.valueOf(path))) {
            fileWriter.write(readyCryptText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
