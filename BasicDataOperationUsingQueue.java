import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Long;
import java.time.format.longValueFormatter;
import java.util.Queue;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Клас BasicDataOperationUsingQueue надає методи для виконання основних операцiй з даними типу Long.
 * 
 * <p>Цей клас зчитує данi з файлу "list/Long.data", сортує їх та виконує пошук значення в масивi та черзi.</p>
 * 
 * <p>Основнi методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основнi операцiї з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив Long.</li>
 *   <li>{@link #searchArray()} - Виконує пошук значення в масивi Long.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мiнiмальне та максимальне значення в масивi Long.</li>
 *   <li>{@link #searchQueue()} - Виконує пошук значення в черзi Long.</li>
 *   <li>{@link #findMinAndMaxInQueue()} - Знаходить мiнiмальне та максимальне значення в черзi Long.</li>
 *   <li>{@link #peekAndPollQueue()} - Виконує операцiї peek та poll з чергою Long.</li>
 * </ul>
 * 
 * <p>Конструктор:</p>
 * <ul>
 *   <li>{@link #BasicDataOperationUsingQueue(String[])} - iнiцiалiзує об'єкт з значенням для пошуку.</li>
 * </ul>
 * 
 * <p>Константи:</p>
 * <ul>
 *   <li>{@link #PATH_TO_DATA_FILE} - Шлях до файлу з даними.</li>
 * </ul>
 * 
 * <p>Змiннi екземпляра:</p>
 * <ul>
 *   <li>{@link #longValueValueToSearch} - Значення Long для пошуку.</li>
 *   <li>{@link #longValueArray} - Масив Long.</li>
 *   <li>{@link #longValueQueue} - Черга Long.</li>
 * </ul>
 * 
 * <p>Приклад використання:</p>
 * <pre>
 * {@code
 * java BasicDataOperationUsingQueue "2024-03-16T00:12:38Z"
 * }
 * </pre>
 */
public class BasicDataOperationUsingQueue {
    static final String PATH_TO_DATA_FILE = "list/Long.data";

    Long longValueValueToSearch;
    Long[] longValueArray;
    Queue<Long> longValueQueue;

    public static void main(String[] args) {  
        BasicDataOperationUsingQueue basicDataOperationUsingQueue = new BasicDataOperationUsingQueue(args);
        basicDataOperationUsingQueue.doDataOperation();
    }

    /**
     * Конструктор, який iнiцiалiзує об'єкт з значенням для пошуку.
     * 
     * @param args Аргументи командного рядка, де перший аргумент - значення для пошуку.
     */
    BasicDataOperationUsingQueue(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Вiдсутнє значення для пошуку");
        }

        String valueToSearch = args[0];
        this.longValueValueToSearch = Long.parse(valueToSearch, longValueFormatter.ISO_DATE_TIME);

        longValueArray = Utils.readArrayFromFile(PATH_TO_DATA_FILE);

        longValueQueue = new PriorityQueue<>(Arrays.asList(longValueArray));
    }

    /**
     * Виконує основнi операцiї з даними.
     * 
     * Метод зчитує масив та чергу об'єктiв Long з файлу, сортує їх та виконує пошук значення.
     */
    private void doDataOperation() {
        // операцiї з масивом дати та часу
        searchArray();
        findMinAndMaxInArray();

        sortArray();

        searchArray();
        findMinAndMaxInArray();

        // операцiї з Queue дати та часу
        searchQueue();
        findMinAndMaxInQueue();
        peekAndPollQueue();

        // записати вiдсортований масив в окремий файл
        Utils.writeArrayToFile(longValueArray, PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Сортує масив об'єктiв Long та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     * 
     * Метод виконує наступнi кроки:
     * <li>1. Виводить початковий масив об'єктiв Long.</li>
     * <li>2. Вимiрює час, витрачений на сортування масиву за допомогою Arrays.sort().</li>
     * <li>3. Виводить час, витрачений на сортування масиву в наносекундах.</li>
     * <li>4. Виводить вiдсортований масив об'єктiв Long.</li>
     */
    private void sortArray() {
        // вимiрюємо час, витрачений на сортування масиву дати i часу 
        long startTime = System.nanoTime();

        Arrays.sort(longValueArray);

        Utils.printOperationDuration(startTime, "сортування масиву дати i часу");
    }

    /**
     * Метод для пошуку значення в масивi дати i часу.
     */
    private void searchArray() {
        // вимiрюємо час, витрачений на пошук в масивi дати i часу
        long startTime = System.nanoTime();
        
        int index = Arrays.binarySearch(this.longValueArray, longValueValueToSearch);
        
        Utils.printOperationDuration(startTime, "пошук в масивi дати i часу");

        if (index >= 0) {
            System.out.println("Значення '" + longValueValueToSearch + "' знайдено в масивi за iндексом: " + index);
        } else {
            System.out.println("Значення '" + longValueValueToSearch + "' в масивi не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в масивi Long.
     */
    private void findMinAndMaxInArray() {
        if (longValueArray == null || longValueArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }

        // вимiрюємо час, витрачений на пошук мiнiмальної i максимальної дати i часу
        long startTime = System.nanoTime();

        Long min = longValueArray[0];
        Long max = longValueArray[0];

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в масивi");

        for (Long longValue : longValueArray) {
            if (longValue.isBefore(min)) {
                min = longValue;
            }
            if (longValue.isAfter(max)) {
                max = longValue;
            }
        }

        System.out.println("Мiнiмальне значення в масивi: " + min);
        System.out.println("Максимальне значення в масивi: " + max);
    }

    /**
     * Метод для пошуку значення в черзi дати i часу.
     */
    private void searchQueue() {
        // вимiрюємо час, витрачений на пошук в черзi дати i часу
        long startTime = System.nanoTime();

        boolean isFound = this.longValueQueue.contains(longValueValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в Queue дати i часу");

        if (isFound) {
            System.out.println("Значення '" + longValueValueToSearch + "' знайдено в Queue");
        } else {
            System.out.println("Значення '" + longValueValueToSearch + "' в Queue не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в черзi Long.
     */
    private void findMinAndMaxInQueue() {
        if (longValueQueue == null || longValueQueue.isEmpty()) {
            System.out.println("Queue порожнiй або не iнiцiалiзований.");
            return;
        }

        // вимiрюємо час, витрачений на пошук мiнiмальної i максимальної дати i часу
        long startTime = System.nanoTime();

        Long min = Collections.min(longValueQueue);
        Long max = Collections.max(longValueQueue);

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в Queue");

        System.out.println("Мiнiмальне значення в Queue: " + min);
        System.out.println("Максимальне значення в Queue: " + max);
    }

    /**
     * Виконує операцiї peek та poll з чергою Long.
     */
    private void peekAndPollQueue() {
        if (longValueQueue == null || longValueQueue.isEmpty()) {
            System.out.println("Queue порожнiй або не iнiцiалiзований.");
            return;
        }

        Long firstElement = longValueQueue.peek();
        System.out.println("Перший елемент у черзi: " + firstElement);

        firstElement = longValueQueue.poll();
        System.out.println("Забрати перший елемент у черзi: " + firstElement);

        firstElement = longValueQueue.peek();
        System.out.println("Перший елемент у черзi: " + firstElement);
    }
}

/**
 * Клас Utils мiститить допомiжнi методи для роботи з даними типу Long.
 */
class Utils {
    /**
     * Виводить час виконання операцiї в наносекундах.
     * 
     * @param startTime Час початку операцiї в наносекундах.
     * @param operationName Назва операцiї.
     */
    static void printOperationDuration(long startTime, String operationName) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("\n>>>>>>>>>> Час виконання операцiї '" + operationName + "'': " + duration + " наносекунд");
    }

    /**
     * Зчитує масив об'єктiв Long з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктiв Long.
     */
    static Long[] readArrayFromFile(String pathToFile) {
        longValueFormatter formatter = longValueFormatter.ISO_DATE_TIME;
        Long[] tempArray = new Long[1000];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                Long longValue = Long.parse(line, formatter);
                tempArray[index++] = longValue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Long[] finalArray = new Long[index];
        System.arraycopy(tempArray, 0, finalArray, 0, index);

        return finalArray;
    }

    /**
     * Записує масив об'єктiв Long у файл.
     * 
     * @param longValueArray Масив об'єктiв Long.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(Long[] longValueArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (Long longValue : longValueArray) {
                writer.write(longValue.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}