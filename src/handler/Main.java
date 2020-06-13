package handler;

public class Main {
    public static void main(String[] args) {

        // Check if config files supplied by user
        if (args.length != 2) {
            System.out.println("Invalid command.\n" + "usage: D2LH <import config> <export config>");
            System.exit(1);
        } else {
            System.out.printf("Importing from: %s\nExporting to: %s\n.", args[0], args[1]);
            ConfigHandler ch = new ConfigHandler();
            ch.handleConfigs(args[0], args[1]);
        }
    }
}
