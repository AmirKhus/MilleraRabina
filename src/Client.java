import java.io.IOException;
import java.util.Scanner;

public class Client {
    private static String number;
    private static String iteration;

    public static void main(String[] args) {
        startWord();
        try (Phone phone = new Phone("127.0.0.1", 8000)) {
            System.out.println(" Клиент подключился к серверу");
            String request = number + " " + iteration;
            phone.writerLine(request);
            String response = phone.readLine();
            System.out.println("Ответ сервера: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void startWord() {
        System.out.print("Введите нужно число для проверки и через ПРОБЕЛ\nколичество итерций(если нет, то напишите \"-\" через пробел) ->");
        Scanner string1 = new Scanner(System.in);
        String string = string1.nextLine();
        iteration = "";
        number = "";
        int count = 0;
        while (string.charAt(count) != ' ') {
            number = number + string.charAt(count);
            count++;
        }
        count++;
        if (string.charAt(count) != '-') {
            for (int i = count; i < string.length(); i++) {
                iteration = iteration + string.charAt(i);
            }
        } else {
            iteration = (int) Math.log(Integer.parseInt(number)) + "";
        }
    }
}
