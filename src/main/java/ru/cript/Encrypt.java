package ru.cript;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Encrypt {

    public static void encrypt(Path path, int key, Character[] chars) {

        List<Character> characterList = Main.readFileChar(path);

        StringBuilder stringBuilder = new StringBuilder();

        for (Character character : characterList) {
            stringBuilder.append(character);
        }

        String s2 = String.valueOf(stringBuilder);
        String s = s2.toLowerCase();
        Character[] characters = new Character[s2.length()];

        StringBuilder stringBuilder2 = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (chars[i] == (s.charAt(j))) {
                    // key > 0
                    if (key >= 0 || 0 <= key + i) {
                        if (i + key < chars.length) {
                            characters[j] = chars[i + key];
                        } else {
                            int a = chars.length - key;
                            characters[j] = chars[i - a];
                        }
                        // key < 0
                    } else {
                            characters[j] = chars[chars.length + key + i];
                    }
                }
            }
        }
        for (Character character : characters) {
            if (character != null) {
                stringBuilder2.append(character);
            }
        }
        try (FileWriter fileWriter = new FileWriter(String.valueOf(path))) {
            fileWriter.write(stringBuilder2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
