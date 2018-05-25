package inputOutputStreams;

import java.io.*;
import java.util.ArrayList;

//Тестовая реализация поток чтения из текстового файла

public class WriteFile {
    public static void main(String[] args) {

        try {
            //Создаем новый файл
            File myFile = new File("MyText.txt");

            //Для эффективного заполнения файла используем буфер
            //Используя только FileWriter объект будет записывать каждый элемент отдельно
            //После присоединения BufferedWriter к FileWriter первый будет хранить все
            //записаные нами элементы пока не заполнится
            //FileWriter начнет записаь в файл на диск, когда буфер заполнится
            BufferedWriter writer = new BufferedWriter(new FileWriter("MyText.txt"));

            //Записываем строку в файл
            writer.write("Сюда можно вставить строку, которая запишется в файл. ");
            writer.write("Не забудь купить на завтра печенек! ");
            writer.close();

            //File Reader - это поток соединения для символов
            //Он подключается к текстовому файлу
            FileReader fileReader = new FileReader(myFile);

            //Для более эффективного чтения соединяем FileReader с BufferedReader
            //FileReader будет обращаться к файлу только есть буфер пуст
            //Потому что программа прочитает все, что там находилось
            BufferedReader reader = new BufferedReader(fileReader);

            //Создаем строковую переменную
            // для временного хранения каждой строки во времяя чтения
            String line = null;

            //Считываем строку текста и присваиваем ее строковой переменной line
            //Пока эта переменная не пуста (пока там есть, что читать)
            //Выводим на экран только что прочтенную строку
            while((line = reader.readLine()) != null) {

                //Создаем список, куда будем записывать строки
                ArrayList<String> fileOutput = new ArrayList<>();
                fileOutput.add(line);

                //Проходим в цикле по массиву и выводим на экран каждую лексему
                for(String s : fileOutput) {
                    System.out.println(s);
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
