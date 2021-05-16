import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String number = "";
    static String iteration = "";

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu(){
        Scanner scan = new Scanner(System.in);
        int g = 0;
        String s = "";
        while (!"4".equals(s)) {
            System.out.println("1. Для ввода с консоли введите 1");
            System.out.println("2. Для ввода из файла введите 2");
            System.out.println("3. Для запуска сервера введите 3");
            System.out.println("4. Для выхода из приложения введите 4");
            System.out.print("->");
            s = scan.next();
            try {
                g = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
            }
            switch (g) {
                case 1:
                    startWord();
                    if (MillerRabin.isPrime(number, Integer.parseInt(iteration))){
                        System.out.println(number + " - Вероятно простое");
                    } else {
                        System.out.println(number + " - Составное");
                    }
                    break;
                case 2:
                    System.out.println("Выберите файл для считывания данных");
                    readFile();
                    break;
                case 3:
                    Server server = new Server();
                    server.startServer();
                    break;
                case 4:
                    System.out.println("Выключаюсь!!!");
                    break;
                default:
                    System.out.println("Комада под таким номером в данный момент - нет.");
                    break;
            }
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

    public static void readFile() {
        File file;
        Scanner fileIn;
        int response;
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        response = chooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            try {
                fileIn = new Scanner(file);
                String number = "";
                String iteration = "";
               // читаем посимвольно
                while (fileIn.hasNextLine()) {
                    String[] array = fileIn.nextLine().split(" ");
                    number = array[0];
                    if (array.length > 1) {
                        iteration = array[1];
                        System.out.print(array[0] + " " + array[1]);
                        if (MillerRabin.isPrime(number, Integer.parseInt(iteration))) {
                            System.out.println(" - возможно простое");
                        } else {
                            System.out.println(" - составное");
                        }
                    } else {
                        iteration = (int) Math.log(Integer.parseInt(number)) + "";
                        System.out.print(array[0]);
                        if (MillerRabin.isPrime(number, Integer.parseInt(iteration))) {
                            System.out.println(" - возможно простое");
                        } else {
                            System.out.println(" - составное");
                        }
                    }

                }
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }
}
