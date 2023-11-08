import java.util.Scanner;

public class SongDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nBem-vindo ao Sistema de Gerenciamento de Músicas e Playlists!\n");

        System.out.println("Nome da Playlist: ");
        String nomeUserPlaylist = scanner.next();
        Playlist userPlaylist = new Playlist();
        System.out.println("Playlist " + nomeUserPlaylist + " criada com sucesso!\n");

        Song song1 = new Song("Hotline Bling", "Drake", "Hotline Bing - Single", 267000);
        Song song2 = new Song("What Do You Mean?", "Justin Bieber", "What Do You Mean? - Single", 207000);
        Song song3 = new Song("Watch Me", "Silento", "Watch Me (Whip / Nae Nae) - Single", 185000);
        Song song4 = new Song("679", "Fetty Wap ft. Remy Boyz", "Fetty Wap", 185000);
        Song song5 = new Song("Can't Feel My Face", "The Weekend", "Beauty Behind the Madness", 213000);
        Song song6 = new Song("Good for You", "Selena Gomez ft. A$AP Rocky", "Good for You - Single", 221000);
        Song song7 = new Song("If You", "Big Bang", "MADE", 264000);

        userPlaylist.add(song1);
        userPlaylist.add(song2);
        userPlaylist.add(song3);
        userPlaylist.add(song4);
        userPlaylist.add(song5);
        userPlaylist.add(song6);
        userPlaylist.add(song7);

        int option;
        do {
            System.out.println("Digite o número correspondente a opção desejada:");
            System.out.println("1 - Adicionar música a Playlist " + nomeUserPlaylist);
            System.out.println("2 - Listar músicas da Playlist " + nomeUserPlaylist);
            System.out.println("0 - Sair");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Nome da Música: ");
                    String nomeUserMusica = scanner.next();
                    System.out.println("Nome do Artista: ");
                    String nomeUserArtista = scanner.next();
                    System.out.println("Nome do Álbum: ");
                    String nomeUserAlbum = scanner.next();
                    System.out.println("Duração da Música: ");
                    int duracaoUserMusica = scanner.nextInt();
                    Song musica = new Song (nomeUserMusica, nomeUserArtista, nomeUserAlbum, duracaoUserMusica);
                    userPlaylist.add(musica);
                    break;
                case 2:
                    userPlaylist.print();
                    break;
                case 0:
                    System.out.println("Encerrando Programa!");
                    userPlaylist.clear();
                    break;
                default:
                    System.out.println("ERRO: escolha uma opção existente");
                    break;
            }
        } while (option != 0);
    }

    public static long HOURS = 60 * 60 * 1000;
    public static long MINS = 60 * 1000;
    public static long SECS = 1000;

    public static class Playlist {

        private Song[] songs;
        private int count;
        private String playlistName;

        public Playlist() {
            songs = new Song[20];
            count = 0;
        }

        public String getPlaylistName() {
            return playlistName;
        }

        public void setPlayListName() {
            this.playlistName = playlistName;
        }

        public void add(Song a) {
            if (count == songs.length) {
                System.out.println("ERRO: Playlist cheia.");
            }
            songs[count] = a;
            count++;
        }

        public Song get(int i) {
            if (count > i) {
                return songs[i];
            } else {
                return null;
            }
        }

        public Song remove(String name) {
            boolean found = false;
            int indexToRemove = 0;
            while (indexToRemove < count && !found) {
                if (songs[indexToRemove].getName().equals(name)) {
                    found = true;
                } else {
                    indexToRemove++;
                }
            }
            if (indexToRemove < count) {
                for (int from = indexToRemove + 1; from < count; from++) {
                    songs[from - 1] = songs[from];
                }
                songs[count - 1] = null;
                count--;
            }
            return null;
        }

        public void print() {
            String result = "Número de Músicas = " + count + " / Limite de Músicas = " + songs.length + "\n\n";

            for (int i = 0; i < count; i++) {
                result += ("Música [" + (i+1) + "] = " + songs[i] + "\n");
            }
            result += "\nDuração da Playlist: " + formattedTotalTime() + "\n";
            System.out.println(result.toString());
        }

        public int size() {
            return count;
        }

        public int totalTime() {
            int totalTime = 0;
            for (int i = 0; i < count; i++) {
                totalTime += songs[i].getLength();
            }
            return totalTime;
        }

        public String formattedTotalTime() {
            return formatTime(totalTime());
        }

        public void clear() {
            for (int i = 0; i < songs.length; i++) {
                songs[i] = null;
                count = 0;
            }
            return;
        }
    }

    public static class Song {

        public String name;
        public String artist;
        public String album;
        public int length;

        public Song(String songName, String artistName, String albumName, int trackLength) {
            this.name = songName;
            this.artist = artistName;
            this.album = albumName;
            this.length = trackLength;
        }

        public void setName(String songName) {
            name = songName;
        }

        public String getName() {
            return name;
        }

        public void setArtist(String artistName) {
            artist = artistName;
        }

        public String getArtist() {
            return artist;
        }

        public void setAlbum(String albumName) {
            album = albumName;
        }

        public String getAlbum() {
            return album;
        }

        public void setLength(int h, int m, int s) {
            length = (h * 3600 + m * 60 + s);
            if (h == 0) {
                length = (m * 60 + s);
            }
        }

        public int getLength() {
            return length;
        }

        public String toString() {
            return "Título: " + getName() + ", Artista: " + getArtist() + ", Album: " + getAlbum() + ", Duração: " + formatTime(getLength());
        }

    }

    public static String formatTime(long time) {
        long overflow = time;
        long h = time / HOURS;
        overflow = time % HOURS;
        long m = overflow / MINS;
        overflow = time % MINS;
        long s = overflow / SECS;
        return String.format("%02d:%02d.%02d", h, m, s);

    }
}
