package networkStreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/* Программа создает сокет и объект BufferedReader с помощью других потоков.
 * Затем считывает строку, которую ей передает серверное приложение,
 * работающие на порту 4242
 * */

public class DailyAdviceClient {

    public void go() {
        try {
            //Создаем соединение через сокет к приложению работающему на порту 4242 на localhost
            Socket socket = new Socket("127.0.0.1", 4242);

            //Соединяем InputStreamReader с входящим потоком из сокета
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            //Подключаем BufferedReader к InputStreamReader
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            //Счмтываем строки из сокета
            String advice = bufferedReader.readLine();
            System.out.println("Твой совет на сегодня: " + advice);

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }
}
