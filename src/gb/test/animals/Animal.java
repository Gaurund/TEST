package gb.test.animals;

import java.util.ArrayList;

public class Animal {
    private final String name;
    private final String type;
    private final String specie;
    private final ArrayList<String> tricks;

    public Animal(String name, String type, String specie, ArrayList<String> tricks) {
        this.name = name;
        this.type = type;
        this.specie = specie;
        this.tricks = tricks;
    }

    @Override
    public String toString() {
        return "Кличка: " + name + ", " + type + ", " + specie + ". Умеет исполнять следующие команды:" + tricks;
    }

    public String toStoreString() {
        return name + " " + type + " " + specie + " " + tricks + ";";
    }

    public String getName() {
        return this.name;
    }

    public void putNewTrick(String newTrick){
        tricks.add(newTrick);
    }
}
