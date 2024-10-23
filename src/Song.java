public class Song {
    private String title;
    private String artist;
    private String album;
    private int duration; // duração em segundos

    public Song(String title, String artist, String album, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%s - %s (%s) [%02d:%02d]", title, artist, album, minutes, seconds);
    }

    public static Song fromString(String songString) {
        try {
            String[] parts = songString.split(" - ");
            if (parts.length < 2) return null;

            String title = parts[0];
            String rest = parts[1];

            String artist = rest.split(" \\(")[0].trim();
            String album = rest.split("\\(")[1].split("\\)")[0].trim();
            String durationPart = rest.split("\\[")[1].split("\\]")[0].trim();

            String[] durationParts = durationPart.split(":");
            int minutes = Integer.parseInt(durationParts[0]);
            int seconds = Integer.parseInt(durationParts[1]);
            int duration = minutes * 60 + seconds;

            return new Song(title, artist, album, duration);
        } catch (Exception e) {
            System.err.println("Error parsing song string: " + e.getMessage());
            return null;
        }
    }
}