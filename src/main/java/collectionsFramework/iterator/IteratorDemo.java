package collectionsFramework.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Получение доступа ко всем элементам коллекции, используя Iterator
 * Итератор - это объект, реализующий интерфейсы Iterator или ListIterator
 *
 * Методы:
 *
 * boolean hasNext()
 * Проверяет есть ли в коллекции следующий элемент
 * @return true/false
 *
 * boolean hasPrevious()
 * Проверяет есть ли в коллекции предыдущий элемент
 * @return true/false
 *
 * Object next()
 * @return  следующий элемент коллекции
 * @throws  NoSuchElementException Если отсутствует следующий элемент коллекции
 *
 * Object previous()
 * @return  предыдущий элемент коллекции
 * @throws  NoSuchElementException Если отсутствует предыдущий элемент коллекции
 *
 * void remove()
 * Удаляет текущий элемент коллекции
 * Сначала элемента нужно получить с помощью методов next() или previous()
 * @throws  IllegalStateException   Если вызвать метод remove()
 *                                  до методов next() или previous()
 *
 * void set(Object obj
 * Метод присваивает текущему элементу значение, которое переданно
 * в качестве параметров метода
 * */

public class IteratorDemo {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        /* Добавление элементов в список */
        for(int i = 0; i < 25; i++) {
            list.add((int) (Math.random() * 20));
        }

        /* Удаляем из коллекции элементы больше 10 */
        for (Iterator<Integer> i = list.iterator(); i.hasNext(); ) {
            Integer element = i.next();
            if (element > 10) {
                i.remove();
            }
        }

        /* Получаем объект iterator */
        Iterator<Integer> iterator = list.iterator();

        /* Выводим элементы списка Iterator*/
        System.out.println("Iterator:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        /* Получаем объект ListIterator */
        ListIterator<Integer> listIterator = list.listIterator();

        /* Модифицируем элементы списка  ListIterator */
        while (listIterator.hasNext()) {
            Integer element = (int) listIterator.next();
            listIterator.set(element * 10);
        }

        /* Выводим элементы списка ListIterator */
        System.out.println("\n" + "ListIterator:");
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
    }
}
