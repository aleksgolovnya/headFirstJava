package networkStreams.simpleChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/* Очень-очень простой чат-сервер.
 * Код подходит только для тестирования этого простого приложения и треюует доработок,
 * так как обладает низким уровнем надежности.
 *
 * Для запуска приложения нужно открыть сервер в одной консоли, а клиент в другой.
 * */

public class VerySimpleChatServer {

    //Список для записи полученных сообщений от клиентов
    ArrayList clientOutputStreams;

    public class ClientHandler implements Runnable {

        BufferedReader bufferedReader;
        Socket socket;

        public ClientHandler(Socket clientSocket) {
            try {
                socket = clientSocket;

                //Соединяем InputStreamReader с входящим потоком из сокета
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                //Подключаем BufferedReader к InputStreamReader
                bufferedReader = new BufferedReader(isReader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            //Строка для записи полученного сообщения от клиента
            String message;

            try {
                //Считываем входящее оообщение от клиента пока оно не null
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println("Read " + message);
                    //Пересылаем полученное сообщение всем остальным клиентам
                    tellEveryone(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void go() {
        clientOutputStreams = new ArrayList();

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            //Сервер входит в постоянный цикл ожидания клиентских подключений и их обслуживания
            while (true) {

                //Метод accept() блокирует приложение до тех пор, пока не поступит запросс
                //После этого возвращает сокет на анонимном порту для взаимодествия с клиентом
                Socket clientSocket = serverSocket.accept();

                //Теперь мы испиользуем соединение объекта Socket с клиентом для создания экземпляра PrintWriter
                //После этгого добавляем его в список clientOutputStreams
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(printWriter);

                //Мы запускаем новый поток используя вложенный класс в качестве Runnable (задачи)
                //Задача потока состоим в том, чтобы обрабатывать сообщения от клиентов на сокете clientSocket
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
                System.out.println("Полученно соединение");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: комментарии
    public void tellEveryone(String message) {
        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter printWriter = (PrintWriter) it.next();
                printWriter.println(message);
                printWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }
}
