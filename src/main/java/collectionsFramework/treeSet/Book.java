package collectionsFramework.treeSet;

/* Класс Book реализует интерфейс Comparable
 * и переопределяет метод compareTo()
 * сравнение объектов ведется по title
 * */

public class Book implements Comparable {
    String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Object b) {
        Book book = (Book) b;
        return (title.compareTo(book.title));
    }
}
