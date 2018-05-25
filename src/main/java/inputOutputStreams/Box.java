package inputOutputStreams;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

@Getter @Setter

//Реализуем интерфейс Sirealizable, для сериализации объектов
public class Box implements Serializable {
    private int id;
    private int width;
    private int height;
    private String description;


    public Box( int id, int width, int height, String description) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.description = description;
    }

    public Box() {

    }

    @Override
    public String toString() {
        return "Box " +  id +
                ", height = " + height +
                ", width = " + width +
                ", description = " + description;
    }
}
