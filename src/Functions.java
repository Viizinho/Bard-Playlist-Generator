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

        int totalDuration = 0; // duração total em segundos

        try (BufferedReader reader = new BufferedReader(new FileReader(playlistFile))) {
            String line;
            System.out.println("Playlist: " + name);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Song song = Song.fromString(line);
                if (song != null) {
                    totalDuration += song.getDuration();
                }
            }

            // Calcula a duração total em horas, minutos e segundos
            int hours = totalDuration / 3600;
            int minutes = (totalDuration % 3600) / 60;
            int seconds = totalDuration % 60;

            System.out.printf("Total duration: %d hours, %d minutes, %d seconds%n", hours, minutes, seconds);
        } catch (IOException e) {
            System.err.println("Error reading from playlist file: " + e.getMessage());
        }
    }

    public static void listPLaylist() {
        File dir = new File(PLAYLISTS_DIR);
        String[] playlists = dir.list((d, name) -> name.endsWith(".txt"));

        if (playlists != null && playlists.length > 0) {
            System.out.println("Playlists available: ");
            for (String playlist : playlists) {
                System.out.println(playlist.replace(".txt", ""));
            }
        } else {
            System.out.println("No playlists found");
        }
    }

    public static void createSong(String playlist, Song song) {
        File playlistFile = new File(PLAYLISTS_DIR + playlist + ".txt");
        if (!playlistFile.exists()) {
            System.out.println("Playlist not found: " + playlist);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playlistFile, true))) {
            writer.write(song.toString());
            writer.newLine();
            System.out.println("Song added to playlist: " + playlist);
        } catch (IOException e) {
            System.err.println("Error writing to playlist file: " + e.getMessage());
        }
    }

    public static void deleteSong(String playlist, String songTitle) {
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
                Song song = Song.fromString(line);
                if (song != null && song.getTitle().equalsIgnoreCase(songTitle)) {
                    found = true; // Marca que a música foi encontrada e será removida
                } else {
                    writer.write(line);
                    writer.newLine();
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

        // Substitui o arquivo original pelo arquivo temporário
        if (!playlistFile.delete()) {
            System.err.println("Could not delete original playlist file");
        }
        if (!tempFile.renameTo(playlistFile)) {
            System.err.println("Could not rename temporary file");
        }
    }
}