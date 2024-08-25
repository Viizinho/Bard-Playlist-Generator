import java.util.Scanner;

public class Menu {
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Please select an option:");
            System.out.println("1. Create a new playlist");
            System.out.println("2. Delete a playlist");
            System.out.println("3. Display playlist");
            System.out.println("4. Create music to a playlist");
            System.out.println("5. Delete music of a playlist");
            System.out.println("0. Exit");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Please enter the name of the playlist:");
                    String namePlaylist1 = scanner.next();
                    Functions.createPlaylist(namePlaylist1);
                    break;

                case 2:
                    System.out.println("Please enter the name of the playlist:");
                    String namePlaylist2 = scanner.next();
                    Functions.deletePlaylist(namePlaylist2);
                    break;

                case 3:
                    System.out.println("Please enter the name of the playlist:");
                    String namePlaylist3 = scanner.next();
                    Functions.displayPlaylist(namePlaylist3);
                    break;

                case 4:
                    System.out.println("Please enter the name of the Playlist");
                    String namePlaylist4 = scanner.next();
                    System.out.println("Please enter the name of the Song:");
                    String nameSong4 = scanner.next();
                    Functions.createSong(namePlaylist4, nameSong4);
                    break;

                case 5:
                    System.out.println("Please enter the name of the Playlist");
                    String namePlaylist5 = scanner.next();
                    System.out.println("Please enter the name of the Song:");
                    String nameSong5 = scanner.next();
                    Functions.deleteSong(namePlaylist5, nameSong5);
                    break;
                case 0:
                    System.out.println("Thank you for using Bard Playlist Generator!");
                    break;

                default:
                    System.out.println("Invalid option");
            }
        } while (opcao != 0);
    }
}
