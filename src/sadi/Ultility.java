package sadi;

public class Ultility {
        public static void clearscr(){
            // clear the screen
            for(int i = 0; i < 80*300; i++) // Default Height of cmd is 300 and Default width is 80
                System.out.println(""); // Prints a line
        }
    }

