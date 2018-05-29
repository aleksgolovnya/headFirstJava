package networkStreams.simpleChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/* Приложение SimpleChatClient может отправлять сообщение на сервер
 * и считывать входящие сообщения полученные от сервера,
 * отображая их в прокручиваемой текстовой области
 * */

public class SimpleChatClient {

    JTextArea incoming;
    JTextField outgoing;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Socket socket;

    public static void main(String[] args) {
        SimpleChatClient client = new SimpleChatClient();
        client.go();

    }

    public void go() {

        //Графический интерфейс
        JFrame frame = new JFrame("Simple chat client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        setUpNetworking();

        //Мы запускаем новый поток используя вложенный класс в качестве Runnable (задачи)
        //Работа потока заключается в чтении данных с сервера через сокет и выводе входящих сообщений
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(700, 400);
        frame.setVisible(true);

    }

    //Мы используем сокет для получения входящего и исходящего потоков
    //Исходящий поток задействован для отправки данных, также к нему добавлен входящий поток
    //Поэтому наш объект Thread может получать сообщения от сервера
    public void setUpNetworking() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(streamReader);
            printWriter = new PrintWriter(socket.getOutputStream());
            System.out.println("Соединение установленно");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Когда пользователь нажимает кнопку Send, содержимое текстового поля отправляется на сервер
    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                printWriter.println(outgoing.getText());
                printWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    //В метода run() поток входит в цикл, пока ответ от сервера не будет равнятьсяя null
    //Считывает за раз одну строку и добавляет ее в прокручивающуюся область,
    //используя в конце символ переноса строки
    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            String message;

            try {
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println("Read " + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
