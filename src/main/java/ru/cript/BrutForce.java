package ru.cript;

import java.nio.file.Path;
import java.util.List;

public class BrutForce {

    public static void decipher(Path path, Character[] chars) {

        StringBuilder str = new StringBuilder();
        List<String> ciphertext = Main.readFile(path);

        int point = 0;
        int key = 0;
        while (key <= chars.length) {
            for (int i = 0; i < ciphertext.get(0).length(); i++) {
                for (int j = 0; j < chars.length; j++) {
                    if (ciphertext.get(0).charAt(i) == chars[j]) {
                        if (j - key >= 0 && key >= 0) {   //key++ если не выходит в -
                            str.append(chars[j - key]);
                        } else if (key >= 0) {             //если key выходит в -1,...,-n
                            str.append(chars[chars.length - (key - j)]);
                        }
                        if (key < 0) {
                            if (j - key < chars.length) {   //Если не выходит за предел
                                str.append(chars[j - key]); //j+key
                            } else {
                                int a = chars.length + key;
                                str.append(chars[j - a]);
                            }
                        }
                    }
                }
            }

            for (int i = 0, j = 0; i < str.length() - 2; i++, j++) {
                if (str.charAt(i + 1) == '\u0020' && str.charAt(i) == ',') {
                    point++;
                }
                float res = (float) point / str.length();
                    if (res > 0.007) {
                        System.out.println("KEY = " + key);
                        return;
                    }
            }
            key++;
            str.setLength(0);
            point = 0;
        }
    }
}

