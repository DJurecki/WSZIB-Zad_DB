package com.jurecki.gui;

import com.jurecki.db.GameRepository;
import com.jurecki.model.Game;

import java.util.Scanner;

public class GUI {

    private static int menu(){
        while(true){
            System.out.println("1. Show game");
            System.out.println("2. Add game");
            System.out.println("3. Update game");
            System.out.println("4. Delete game");
            System.out.println("5. Exit");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")) {
                return Integer.parseInt(choice);
            }
        }
    }

    public static void showGUI(){
        while(true){
            int choice = menu();
            Scanner scanner = new Scanner(System.in);
            switch (choice){
                case 1:
                    Game game = getGameProperty();
                    game = GameRepository.getGame(game.getTitle());
                    System.out.println(game);
                    break;
                case 2:
                    Game game1 = getAllGameProperties();
                    GameRepository.addGame(game1);
                    break;
                case 3:
                    Game game2 = getGameProperty();
                    GameRepository.updateGame(game2.getTitle());
                    break;
                case 4:
                    Game game3 = getGameProperty();
                    GameRepository.deleteGame(game3.getTitle());
                    break;
                case 5:
                    return;
            }
        }
    }

    private static Game getAllGameProperties(){

        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        System.out.println("Enter id: ");
        game.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Enter title: ");
        game.setTitle(scanner.nextLine());
        System.out.println("Enter publisher: ");
        game.setPublisher(scanner.nextLine());
        return game;
    }

    private static Game getGameProperty(){
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        System.out.println("Enter game title: ");
        game.setTitle(scanner.nextLine());

        return game;
    }
}
