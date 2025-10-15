package org.example;

import java.util.*;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static MyHashMap<String, String> hashMap = new MyHashMap<>();
    private static MyHashMultiMap<String, String> hashMultiMap = new MyHashMultiMap<>();

    public static void main(String[] args) {
        System.out.println("=== HashMap and HashMultiMap Demo Application ===");

        while (true) {
            printMenu();
            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1 -> demoHashMap();
                case 2 -> demoHashMultiMap();
                case 3 -> demoBothStructures();
                case 0 -> {
                    System.out.println("Выход из приложения.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Демонстрация HashMap");
        System.out.println("2. Демонстрация HashMultiMap");
        System.out.println("3. Демонстрация обеих структур");
        System.out.println("0. Выход");
    }

    private static void demoHashMap() {
        System.out.println("\n=== Демонстрация HashMap ===");

        while (true) {
            System.out.println("\n--- HashMap Operations ---");
            System.out.println("1. Добавить элемент");
            System.out.println("2. Получить элемент");
            System.out.println("3. Проверить наличие ключа");
            System.out.println("4. Удалить элемент");
            System.out.println("5. Показать все ключи");
            System.out.println("6. Показать все значения");
            System.out.println("7. Показать размер");
            System.out.println("8. Очистить HashMap");
            System.out.println("9. Показать внутреннюю структуру");
            System.out.println("0. Назад в главное меню");

            int choice = getIntInput("Выберите операцию: ");

            switch (choice) {
                case 1 -> {
                    String key = getStringInput("Введите ключ: ");
                    String value = getStringInput("Введите значение: ");
                    hashMap.put(key, value);
                    System.out.println("Элемент добавлен: " + key + "=" + value);
                }
                case 2 -> {
                    String key = getStringInput("Введите ключ: ");
                    String value = hashMap.get(key);
                    System.out.println("Значение для ключа '" + key + "': " + value);
                }
                case 3 -> {
                    String key = getStringInput("Введите ключ: ");
                    boolean exists = hashMap.containsKey(key);
                    System.out.println("Ключ '" + key + "' " + (exists ? "существует" : "не существует"));
                }
                case 4 -> {
                    String key = getStringInput("Введите ключ для удаления: ");
                    hashMap.remove(key);
                    System.out.println("Ключ '" + key + "' удален");
                }
                case 5 -> System.out.println("Все ключи: " + hashMap.keySet());
                case 6 -> System.out.println("Все значения: " + hashMap.values());
                case 7 -> System.out.println("Размер HashMap: " + hashMap.size());
                case 8 -> {
                    hashMap.clear();
                    System.out.println("HashMap очищен");
                }
                case 9 -> hashMap.printInternalStructure();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private static void demoHashMultiMap() {
        System.out.println("\n=== Демонстрация HashMultiMap ===");

        while (true) {
            System.out.println("\n--- HashMultiMap Operations ---");
            System.out.println("1. Добавить элемент");
            System.out.println("2. Получить все значения по ключу");
            System.out.println("3. Проверить наличие ключа");
            System.out.println("4. Проверить наличие значения");
            System.out.println("5. Удалить все значения по ключу");
            System.out.println("6. Удалить конкретное значение");
            System.out.println("7. Показать все ключи");
            System.out.println("8. Показать все значения");
            System.out.println("9. Показать размер");
            System.out.println("10. Очистить HashMultiMap");
            System.out.println("11. Показать внутреннюю структуру");
            System.out.println("0. Назад в главное меню");

            int choice = getIntInput("Выберите операцию: ");

            switch (choice) {
                case 1 -> {
                    String key = getStringInput("Введите ключ: ");
                    String value = getStringInput("Введите значение: ");
                    hashMultiMap.put(key, value);
                    System.out.println("Элемент добавлен: " + key + "=" + value);
                }
                case 2 -> {
                    String key = getStringInput("Введите ключ: ");
                    Collection<String> values = hashMultiMap.get(key);
                    System.out.println("Значения для ключа '" + key + "': " + values);
                }
                case 3 -> {
                    String key = getStringInput("Введите ключ: ");
                    boolean exists = hashMultiMap.containsKey(key);
                    System.out.println("Ключ '" + key + "' " + (exists ? "существует" : "не существует"));
                }
                case 4 -> {
                    String value = getStringInput("Введите значение: ");
                    boolean exists = hashMultiMap.containsValue(value);
                    System.out.println("Значение '" + value + "' " + (exists ? "существует" : "не существует"));
                }
                case 5 -> {
                    String key = getStringInput("Введите ключ для удаления: ");
                    hashMultiMap.remove(key);
                    System.out.println("Все значения для ключа '" + key + "' удалены");
                }
                case 6 -> {
                    String key = getStringInput("Введите ключ: ");
                    String value = getStringInput("Введите значение для удаления: ");
                    hashMultiMap.remove(key, value);
                    System.out.println("Значение '" + value + "' для ключа '" + key + "' удалено");
                }
                case 7 -> System.out.println("Все ключи: " + hashMultiMap.keySet());
                case 8 -> System.out.println("Все значения: " + hashMultiMap.values());
                case 9 -> System.out.println("Общее количество значений: " + hashMultiMap.size());
                case 10 -> {
                    hashMultiMap.clear();
                    System.out.println("HashMultiMap очищен");
                }
                case 11 -> hashMultiMap.printInternalStructure();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private static void demoBothStructures() {
        System.out.println("\n=== Сравнительная демонстрация HashMap и HashMultiMap ===");

        // Демонстрация с одинаковыми данными
        System.out.println("\n--- Добавляем одинаковые данные в обе структуры ---");

        String[][] testData = {
                {"user", "Alice"},
                {"user", "Bob"},
                {"admin", "Charlie"},
                {"user", "David"},
                {"guest", "Eve"}
        };

        System.out.println("Тестовые данные:");
        for (String[] pair : testData) {
            System.out.println("  " + pair[0] + " -> " + pair[1]);
            hashMap.put(pair[0], pair[1]); // В HashMap ключ перезаписывается
            hashMultiMap.put(pair[0], pair[1]); // В HashMultiMap ключ хранит коллекцию
        }

        System.out.println("\n--- Результаты в HashMap ---");
        hashMap.printInternalStructure();

        System.out.println("--- Результаты в HashMultiMap ---");
        hashMultiMap.printInternalStructure();

        System.out.println("--- Сравнение получения данных ---");
        String testKey = "user";
        System.out.println("HashMap.get('" + testKey + "') = " + hashMap.get(testKey));
        System.out.println("HashMultiMap.get('" + testKey + "') = " + hashMultiMap.get(testKey));

        // Очищаем структуры для следующей демонстрации
        hashMap.clear();
        hashMultiMap.clear();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите целое число.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}