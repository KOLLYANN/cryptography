package ru.cript;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Decipher {

    public static void decipher(Path path, int key, Character[] chars) {

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
                if(key == 0) {
                    System.out.println("Он и не зашифрован");
                    return;}
                if (chars[i].equals(s.charAt(j))) {
                    if (i - key >= 0 && key > 0) {          //key+
                            characters[j] = chars[i - key];
                    } else if (i - key < 0 && key > 0){     // key+
                        int a = key - i;
                        characters[j] = chars[chars.length - a];
                    } else {                                 //key-
                        if(i - key < chars.length) {
                            characters[j] = chars[i - key];
                        } else {
                            int a = chars.length + key;
                            characters[j] = chars[i - a];
                        }
                    }
                }
            }
        }
        for (Character character : characters) {
            stringBuilder2.append(character);
        }

        try (FileWriter fileWriter = new FileWriter(String.valueOf(path))) {
            fileWriter.write(stringBuilder2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
