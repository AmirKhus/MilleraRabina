import java.io.IOException;
import java.net.ServerSocket;


public class Server {
    public void startServer() {
            try (ServerSocket server = new ServerSocket(8000)) {
                System.out.println("Сервер запущен, ожидает подключения клиента.");
                try (Phone phone = new Phone(server)) {
                    String request = phone.readLine();
                    int count = 0;
                    String iteration = "";
                    String number = "";
                    while (request.charAt(count) != ' ') {
                        number = number + request.charAt(count);
                        count++;
                    }
                    count++;
                    for (int i = count; i < request.length(); i++) {
                        iteration = iteration + request.charAt(i);
                    }
                    if (MillerRabin.isPrime(number, Integer.parseInt(iteration))){
                        phone.writerLine(number + " - Вероятно простое");
                    } else {
                        phone.writerLine(number + " - Составное");
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        System.out.println("Сервер выключился.");
        }
}
