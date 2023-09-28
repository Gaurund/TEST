package gb.test.animals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Registry {
    //    private static final Comparator<Toy> compareID = new Comparator<>() {
//        @Override
//        public int compare(Toy o1, Toy o2) {
//            return o1.getID() - o2.getID();
//        }
//    };
    public final ArrayList<Animal> animals;

    public Registry() {
        this.animals = new ArrayList<Animal>();
    }

    public void storeRegistry(String FILE_NAME) {
        StringBuilder saveString = new StringBuilder();
        for (Animal animal : animals) {
            saveString.append(animal.toStoreString());
        }
        FilesOps.WriteFile(FILE_NAME, saveString.toString());
    }

    public void restoreRegistry(String FILE_NAME) {
        String loadedString = FilesOps.ReadFile(FILE_NAME);
        if (loadedString != null) {
            String[] stringArray = loadedString.split(";");
            for (String string : stringArray) {
                String[] split = string.split(" ");
                String name = split[0];
                String type = split[1];
                String specie = split[2];
                ArrayList<String> tricks = new ArrayList<>();
                if (split.length != 3) {
                    for (int i = 3; i < split.length; i++) {
                        String temp = split[i];
                        temp = temp.replace("[", "");
                        temp = temp.replace("]", "");
                        temp = temp.replace(",", "");
                        tricks.add(temp);
                    }
                }
                put(name, type, specie, tricks);
            }
        }
    }

    public Animal getByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().toLowerCase().matches(name)) return animal;
        }
        return null;
    }

    public void put(String name, String type, String specie, ArrayList<String> tricks) {
        this.animals.add(new Animal(name, type, specie, tricks));
    }

    public void showAll() {
        for (Animal animal : this.animals) {
            System.out.println(animal);
        }
    }

    public boolean isEmpty() {
        return animals.isEmpty();
    }

}
