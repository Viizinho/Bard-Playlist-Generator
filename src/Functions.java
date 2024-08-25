import java.io.*;
import java.util.*;

public class Functions {
    private static final String PLAYLISTS_DIR = "Playlists/";

    static {
        File dir = new File(PLAYLISTS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void createPlaylist(String name) {
        File playlistFile = new File(PLAYLISTS_DIR + name + ".txt");
        try {
            if (playlistFile.createNewFile()) {
                System.out.println("Playlist created: " + name);
            } else {
                System.out.println("Playlist already exists: " + name);
            }
        } catch (IOException e) {
            System.err.println("Error creating playlist file: " + e.getMessage());
        }
    }

    public static void deletePlaylist(String name) {
        File playlistFile = new File(PLAYLISTS_DIR + name + ".txt");
        if (playlistFile.delete()) {
            System.out.println("Playlist deleted: " + name);
        } else {
            System.out.println("Playlist not found or could not be deleted: " + name);
        }
    }

    public static void displayPlaylist(String name) {
        File playlistFile = new File(PLAYLISTS_DIR + name + ".txt");
        if (!playlistFile.exists()) {
            System.out.println("Playlist not found: " + name);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(playlistFile))) {
            String line;
            System.out.println("Playlist: " + name);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from playlist file: " + e.getMessage());
        }
    }

    public static void createSong(String playlist, String song) {
        File playlistFile = new File(PLAYLISTS_DIR + playlist + ".txt");
        if (!playlistFile.exists()) {
            System.out.println("Playlist not found: " + playlist);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playlistFile, true))) {
            writer.write(song);
            writer.newLine();
            System.out.println("Song added to playlist: " + playlist);
        } catch (IOException e) {
            System.err.println("Error writing to playlist file: " + e.getMessage());
        }
    }

    public static void deleteSong(String playlist, String song) {
        File playlistFile = new File(PLAYLISTS_DIR + playlist + ".txt");
        if (!playlistFile.exists()) {
            System.out.println("Playlist not found: " + playlist);
            return;
        }

        File tempFile = new File(PLAYLISTS_DIR + "temp_" + playlist + ".txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(playlistFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().equals(song)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    found = true;
                }
            }

            if (found) {
                System.out.println("Song removed from playlist: " + playlist);
            } else {
                System.out.println("Song not found in playlist: " + playlist);
            }
        } catch (IOException e) {
            System.err.println("Error processing playlist file: " + e.getMessage());
        }

        if (!playlistFile.delete()) {
            System.err.println("Could not delete original playlist file");
        }
        if (!tempFile.renameTo(playlistFile)) {
            System.err.println("Could not rename temporary file");
        }
    }
}