package Gateways.data;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class for saving and loading the objects created during gameplay
 */
public class GameState {
    /**
     * @param data: the Gateways.data we want to save
     * @param filename: filename with the path where we want to save
     * @param path: path where we want to save
     * @throws Exceptions.SavedSuccessfully: file saved successfully
     */
    public static void save(Serializable data, String path, String filename) throws Exception{
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))){
            oos.writeObject(data);
        }
    }

    public static void save(Serializable data, String filename) throws Exception{
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))){
            oos.writeObject(data);
        }
    }

    /**
     *
     * @param filename: filename with the path where we want to load the Gateways.data from
     * @return ois: the save Gateways.data that was loaded
     * @throws Exceptions.InvalidSaveFileException: if the file loaded is invalid.
     * @throws Exceptions.LoadedSuccessfully: if the file was loaded.
     */
    public static Object load(String filename) throws Exception{
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))){
            Object o = ois.readObject();
            ois.close();
            return o;
        }
    }
}