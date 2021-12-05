import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LeaderBoard {

    private static HashMap<String, Integer> board;

    public static void main(String[] args) throws IOException {
        load_file(board, "Leaderboard.csv");
    }

    public static void load_file(HashMap<String, Integer> board, String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line =  null;

        HashMap<String,String> map = new HashMap<String, String>();

        while((line=br.readLine())!=null){
            String[] str = line.split(",");
            for(int i=1;i<str.length;i++){
                String[] arr = str[i].split(":");
                board.put(arr[0], Integer.parseInt(arr[1]));
            }
        }

    }

    public static void print_map(HashMap<String, Integer> board) {
        for (Map.Entry<String, Integer> entry : board.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value : " + entry.getValue());
        }
    }

    public void add_value(String username, HashMap<String, Integer> board){
        if (board.containsKey(username)){
            board.replace(username, board.get(username) + 1);
        }else{
            board.put(username, 1);
        }
        save_file(board);
    }

    public void clear(HashMap<String, Integer> board){
        board.clear();
        save_file(board);
    }

    public void save_file(HashMap<String, Integer> board){

        String eol = System.getProperty("line.separator");

        try (Writer writer = new FileWriter("Leaderboard.csv")) {
            for (Map.Entry<String, Integer> entry : board.entrySet()) {
                writer.append(entry.getKey())
                        .append(',')
                        .append(Character.highSurrogate(entry.getValue()))
                        .append(eol);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }


    }

}
