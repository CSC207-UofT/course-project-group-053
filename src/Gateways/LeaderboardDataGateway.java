package Gateways;

import java.io.*;
import java.util.*;

public class LeaderboardDataGateway {
    public static String addWin(String playerName) throws IOException {
        boolean playerInDatabase = false;
        File oldFile = new File("src/Gateways/data/leaderboard.csv");
        File newFile = new File("tempfile.csv");

        BufferedWriter writer = new BufferedWriter(new FileWriter("tempfile.csv", true));
        PrintWriter pw = new PrintWriter(writer);

        BufferedReader reader = new BufferedReader(new FileReader("src/Gateways/data/leaderboard.csv"));
        String line;

        while((line = reader.readLine()) != null){
            if(!line.equals("")){
                String[] currentLine = line.split(",");
                if(currentLine[0].equals(playerName)){
                    int newPlayerWins = Integer.parseInt(currentLine[1]) + 1;
                    currentLine[1] = Integer.toString(newPlayerWins);
                    playerInDatabase = true;
                }
                pw.println(currentLine[0] + "," + currentLine[1]);
            }
        }
        if(!playerInDatabase){
            pw.println(playerName + "," + "1");
        }

        reader.close();
        pw.flush();
        pw.close();
        boolean deleted = oldFile.delete();
        boolean updated = newFile.renameTo(new File("src/Gateways/data/leaderboard.csv"));

        if(deleted & updated){
            return "";
        }
        else{
            return "The leaderboard could not be updated.";
        }
    }

    public static ArrayList<String> getTopPlayers() throws IOException {
        HashMap<String, Integer> topPlayers = new HashMap<>();
        ArrayList<Integer> topPlayersWin = new ArrayList<>();
        ArrayList<String> leaderboard = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/Gateways/data/leaderboard.csv"));
        String line;

        while((line = reader.readLine()) != null){
            String[] currentLine = line.split(",");
            topPlayers.put(currentLine[0], Integer.parseInt(currentLine[1]));
            topPlayersWin.add(Integer.parseInt(currentLine[1]));
        }
        Collections.sort(topPlayersWin);
        Collections.reverse(topPlayersWin);
        for (Integer wins : topPlayersWin) {
            String player = getKeyByValue(topPlayers, wins);
            topPlayers.remove(player);
            leaderboard.add(player + ": " + wins + " wins.");
        }
        return leaderboard;
    }

    private static String getKeyByValue(HashMap<String, Integer> map, int value) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (value == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }
}
