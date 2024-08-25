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
            System.out.println("4. Exit");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Please enter the name of the playlist:");
                    String name1 = scanner.next();
                    criarPlaylist(name1);
                    break;
                case 2:
                    System.out.println("Please enter the name of the playlist:");
                    String name2 = scanner.next();
                    deletePlaylist(name2);
                    break;
                case 3:
                    System.out.println("Please enter the name of the playlist:");
                    String name3 = scanner.next();
                    displayPlaylist(name3);
                    break;
                case 4:
                    System.out.println("Thank you for using Bard Playlist Generator!");
                    break;

                default:
                    System.out.println("Invalid option");
            }
        } while (opcao != 4);
    }
    private void criarPlaylist(String name) {
        Functions.criarPlaylist(name);
    }

    private void deletePlaylist(String name) {
        Functions.deletePlaylist(name);
    }

    private void displayPlaylist(String name) {
        Functions.displayPlaylist(name);
    }
}
