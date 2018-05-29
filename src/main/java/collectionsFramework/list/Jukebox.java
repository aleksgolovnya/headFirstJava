package collectionsFramework.list;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* Класс для сортировки списка песен ArrayList
 * Использует метод Collections.sort() интерфейсов Comparable и Comparator
 * Выводит исходный список песен из файла,
 * сортирует по названию и по и по исполнителю
 * */

public class Jukebox {
    ArrayList<Song> songsList = new ArrayList<>();

    public static void main(String[] args) {
        new Jukebox().go();
    }

    //Вложенный класс, реализующий Comparator
    public class ArtistCompare implements Comparator<Song> {

        @Override
        public int compare(Song one, Song two) {
            return one.getArtist().compareTo(two.getArtist());
        }
    }

    public void go() {
        getSongs();
        System.out.println("Изначальная сортировка:");
        //Выводим содержимое списка
        System.out.println(songsList);

        //Вызываем статический метод sort() из класса Collections
        //Затем снова выводим содержимое списка
        //Теперь списсок будет отсортирован в алфавитном порядке
        Collections.sort(songsList);
        System.out.println("Сортировка по названию:");
        System.out.println(songsList);

        //Создаем экземпляр вложенного класса Comparator
        ArtistCompare artistCompare = new ArtistCompare();
        //Вызываем метод sort() и передаем ему список и ссылку на новый объект Comparator
        Collections.sort(songsList, artistCompare);

        System.out.println("Сортировка по исполнителю:");
        System.out.println(songsList);
    }

    //Метод считывает файл и для каждой его строки вызывает метод addSong()
    public void getSongs() {
        try {
            File file = new File("SongsList.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;

            while((line = bufferedReader.readLine()) != null) {
                addSongs(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Метод разбивает строку (содержащую title, artist, rating, bpm)
    //на 4 лексемы с помощью метода split()
    public void addSongs(String lineToParse) {
        String[] tokens = lineToParse.split("/");
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songsList.add(nextSong);
    }
}
