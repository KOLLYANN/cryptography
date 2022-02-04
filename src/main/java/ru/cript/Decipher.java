package ru.cript;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Decipher {

    public static void decipher(Path path, int key, String alphabet) {

        List<Character> characterList = Main.readFileChar(path);

        StringBuilder stringBuilder = new StringBuilder();

        for (Character character : characterList) {
            stringBuilder.append(character);
        }

        String iText = String.valueOf(stringBuilder);

        StringBuilder readyCryptText = new StringBuilder();
        //key 0,...,alphabet.length()
        for (int i = 0; i < iText.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                if (alphabet.charAt(j) == iText.charAt(i)) {
                    if (j - key >= 0) {
                        readyCryptText.append(alphabet.charAt(j - key));
                    } else {
                        int count = alphabet.length() - (-j + key);
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
