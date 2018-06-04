package collectionsFramework.arrayListHashSet;

/* Класс Song реализует интерфейс Comparable чтобы мы имели возможность
 * применяться метод Collections.sort() для сравнения объектов Song
 * Также мы переопределили метод toString() чтобы он возвращал title,
 * так как мы выполняем сравнение по заголовку песни.
 * */

public class Song implements Comparable<Song> {

    String title;
    String artist;
    String rating;
    String bpm;

    /* Код для работы с hashSet */

    @Override
    //HashSet или кто-нибудь еще, кто вызовет этот метод)
    //передает сюда другой объект Song
    public boolean equals(Object aSong) {
        Song song = (Song) aSong;
        //Спрашиваем у переменной title совпадает ли ее значение
        //с названием переданной песни
        return getTitle().equals(song.getTitle());
    }

    @Override
    public int hashCode() {
        //Вызываем из переменной title переопределенный метод hashCode(),
        //который содержится в классе String
        return title.hashCode();
    }

    /* Конец кода для работы с hashSet */

    @Override
    public int compareTo(Song song) {
        return title.compareTo(song.getTitle());
    }

    public Song(String title, String artist, String rating, String bpm) {
        this.title = title;
        this.artist = artist;
        this.rating = rating;
        this.bpm = bpm;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getRating() {
        return rating;
    }

    public String getBpm() {
        return bpm;
    }
}
