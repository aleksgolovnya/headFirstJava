package inputOutputStreams;

import java.io.*;

//Тестовая реализация потоков ввода и вывода
//Сериализации и десереализации объектов

public class BoxTest {
    public static void main(String[] args) {
        //Создаем объекты через конструктор
        Box one = new Box(1,100, 300, "Description of the Box 1");
        Box two = new Box(2,111, 333, "Description of the Box 2");
        Box three = new Box(3,666, 666, "Description of the Box 3");

        try {
            //Этот объект знает, как подключиться к файлу и создать его
            FileOutputStream fs = new FileOutputStream("foo.ser");
            //Этот объект умеет записывать объекты, но не умеет напрямую подключиться к файлу
            ObjectOutputStream os = new ObjectOutputStream(fs);

            //Сериализация объектов: запись объектов в файл
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Присваиваем ссылкам null, чтобы мы не могли обратиться к объектам в куче+
        one = null;
        two = null;
        three = null;

        try {
            //Этот объект знает как соединиться с существующим файлом
            FileInputStream fileStream = new FileInputStream("foo.ser");
            //Этот объект умеет читать объекты, но не может напрямую соединиться с файлом
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            //Выполняем приведение типов: из типа Object в тип Box
            //После десереализации всего объекты имеют тип Object
            Box oneRestore = (Box) objectStream.readObject();
            Box twoRestore = (Box) objectStream.readObject();
            Box threeRestore = (Box) objectStream.readObject();

            objectStream.close();

            System.out.println(oneRestore.toString());
            System.out.println(twoRestore.toString());
            System.out.println(threeRestore.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
