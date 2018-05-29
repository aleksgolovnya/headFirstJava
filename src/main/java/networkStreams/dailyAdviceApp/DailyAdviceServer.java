package networkStreams.dailyAdviceApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*  Программа создает ServerSocket и ожидает клиентские запросы.
  * При получениии такого запроса,
  * когда клиент выполнил для этого приложения new Socket(),
  * сервер создает объект Socket и устанавливает соединение с этим клиентом.
  * Сервер создает экземпляр PrintWriter (с помощью исходящего потока символов из сокета)
  * и отправляет клиенту сообщение.
  *
  * Сервер имеет ограничение - он может работать только с одним клиентом одновременно.
  * */

public class DailyAdviceServer {

    //Ежедневные советы берутся из этого списка
    String [] adviceList = {"Скажите своему начальнику все, что вы о нем думаете.",
            "Купите облегающие джинсы. Нет, они не делают вас полнее.",
            "Возможно вам стоит подобрать другую преческу.",
            "Ешьте меньшими порциями.",
            "Развивайте привычки, а не ставьте цели.",
            "Используйте Linux, а не Windows"};

    public void go() {
        try {
            //Благодаря ServerSocket приложение отслеживает клиентские запросы на порту 4242
            ServerSocket serverSocket = new ServerSocket(4242);

            //Сервер входит в постоянный цикл ожидания клиентских подключений и их обслуживания
            while(true) {
                //Метод accept() блокирует приложение до тех пор, пока не поступит запросс
                //После этого возвращает сокет на анонимном порту для взаимодествия с клиентом
                Socket socket = serverSocket.accept();

                //Теперь мы испиользуем соединение объекта Socket с клиентом для создания экземпляра PrintWriter
                //После этгого отправляем с его помомщью (println()) строку с советом
                //Затем мы закрываем сокет, так как работа с клиентом закончена
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                printWriter.write(advice);
                printWriter.close();
                System.out.println(advice);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }

}
