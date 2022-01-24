package ru.cript;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class StatAnalysis {

    public static void statAnalysis(Path encryptText, Path text) {

        //Считываем текст автора
        List<Character> arrayText = Main.readFileChar(text);
        StringBuilder stringBuilder = new StringBuilder();
        //Получаем зашифрованный текст
        List<String> list = Main.readFile(encryptText);


        Character[] characters = new Character[list.get(0).length()];
        for (int i = 0; i < list.get(0).length(); i++) {
            characters[i] = list.get(0).charAt(i);
            stringBuilder.append(list.get(0).charAt(i));
        }
        // Смотрим кол-во элементов в зашифрованном тексте
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (Character value : characters) {
            if (!hashMap.containsKey(value)) {
                hashMap.put(value, 1);
            } else {
                hashMap.put(value, hashMap.get(value) + 1);
            }
        }
        // Считаем кол-во элементов в тексте автора
        HashMap<Character, Integer> hashMap1 = new HashMap<>();
        for (Character character : arrayText) {
            if (!hashMap1.containsKey(character)) {
                hashMap1.put(character, 1);
            } else {
                hashMap1.put(character, hashMap1.get(character) + 1);
            }
        }
        // Сортируем элементы в массиве, чтобы найти статистику вхождений
        List<Map.Entry<Character, Integer>> list1 = new LinkedList<>(hashMap.entrySet());
        list1.sort((o1, o2) -> o2.getValue() - o1.getValue());
        List<Map.Entry<Character, Integer>> list2 = new LinkedList<>(hashMap1.entrySet());
        list2.sort((o1, o2) -> o2.getValue() - o1.getValue());

        // Заменяем символы соответствия
        for (int i = 0; i < stringBuilder.length(); i++) {
            for (int j = 0; j < list1.size(); j++) {
                if (list.get(0).charAt(i) == list1.get(j).getKey()) {
                    stringBuilder.setCharAt(i, list2.get(j).getKey());
                }
            }
        }

        try (FileWriter fileWriter = new FileWriter(("decipher.txt"))) {
            fileWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

