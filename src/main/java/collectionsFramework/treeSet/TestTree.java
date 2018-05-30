package collectionsFramework.treeSet;

import java.util.Comparator;
import java.util.TreeSet;

public class TestTree {

    public static void main(String[] args) {
        new TestTree().go();
    }

    public void go() {
        Book book1 = new Book("Как устроены кошки");
        Book book2 = new Book("Постройте заново свое тело");
        Book book3 = new Book("В поисках эмо");

        /* Реализация интерфейса Comparable*/

        TreeSet<Book> treeSetComparable = new TreeSet<>();

        treeSetComparable.add(book1);
        treeSetComparable.add(book2);
        treeSetComparable.add(book3);

        System.out.println("Реализация интерфейса Comparable:");
        System.out.println(treeSetComparable);

       /* Код для реализации интерфейса Comparator */

       BookCcmpare bookCcmpare = new BookCcmpare();
       //Вызываем конструктор TreeSet, принимающий Comparator
        TreeSet<Book> treeSetComparator = new TreeSet<Book>(bookCcmpare);

        treeSetComparator.add(book1);
        treeSetComparator.add(book2);
        treeSetComparator.add(book3);

        System.out.println("Реализация интерфейса Comparator:");
        System.out.println(treeSetComparator);
    }

    //Вложенный класс реализующий интерфейс Comparator
    //Определяет, как сортировать элементы внутри множества
    public class BookCcmpare implements Comparator<Book> {
        @Override
        public int compare(Book one, Book two) {
            return (one.title.compareTo(two.title));
        }
    }
}
