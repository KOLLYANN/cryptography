package ru.cript;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Nikolay Chityakov
 * @version 21.01.22
 */

public class Main {
    public static Character[] chars = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я','.', ',', '\"', ':', '-', '!', '?', '\u0020'};

    public static void main(String[] args) {

        menu();

    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие");
            System.out.println("1. Зашифровать текст");
            System.out.println("2. Расшифровать текст(Нужен ключ)");
            System.out.println("3. Расшифровать брут форсом");
            System.out.println("4. Статистический анализ");
            System.out.println("5. Exit");
            int what = scanner.nextInt();
            String path;
            String path2;
            int key;
            switch (what) {
                case 1 -> {
                    System.out.println("Напишите путь к файлу для шифрования");
                    path = scanner.next();
                    System.out.println("Введите ключ для шифрования от -40 до 40");
                    key = scanner.nextInt();
                    Encrypt.encrypt(Path.of(path), key, chars);
                    System.out.println("Файл зашифрован\nСмотрите в " + path + "\n*********");
                }
                case 2 -> {
                    System.out.println("Напишите путь к файлу, который нужно расшифровать имея ключ");
                    path = scanner.next();
                    System.out.println("Введите ключ для расшифровки от -40 до 40");
                    int key2 = scanner.nextInt();
                    Decipher.decipher(Path.of(path), key2, chars);
                    System.out.println("Файл успешно расшифрован, НО ЭТО НЕ ТОЧНО)!\nСмотрите в " + path + "\n*********");
                }
                case 3 -> {
                    System.out.println("Напишите путь к файлу для взлома ключа брут форсом");
                    path = scanner.next();
                    BrutForce.decipher(Path.of(path), chars);
                }
                case 4 -> {
                    System.out.println("Напишите путь к зашифрованному файлу");
                    path = scanner.next();
                    System.out.println("Напишите путь к файлу написанному этим же автором");
                    path2 = scanner.next();
                    StatAnalysis.statAnalysis(Path.of(path), Path.of(path2));
                    System.out.println("Расшифрованный файл находится в decipher.txt\n**********");
                }
                default -> {
                    System.out.println("Пока");
                    return;
                }
            }
        }
    }

    public static List<String> readFile(Path path) {
        List<String> ciphertext = new ArrayList<>();

        // Считываем зашифрованный текст
        try (BufferedReader br = Files.newBufferedReader(Paths.get(String.valueOf(path)))) {
            ciphertext = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ciphertext;
    }

    public static List<Character> readFileChar(Path path) {
        List<Character> characterList = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(String.valueOf(path)));
            int c;
            while ((c = reader.read()) != -1) {
                characterList.add((char) c);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return characterList;
    }

}

