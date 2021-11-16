import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameState {
    /**
     * Class for saving and loading the objects created during gameplay
     */

    /**
     * @param data: the Gateways.data we want to save
     * @param filename: filename with the path where we want to save
     * @throws Exception:
     * TODO: make exception for failure to save
     */
    public static void save(Serializable data, String filename) throws Exception{
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))){
            oos.writeObject(data);
        }
    }

    /**
     *
     * @param filename: filename with the path where we want to load the Gateways.data from
     * @return ois: the save Gateways.data that was loaded
     * @throws Exception
     * TODO: make exception for failure to save
     */
    public static Object load(String filename) throws Exception{
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))){
            return  ois.readObject();
        }
    }
}
