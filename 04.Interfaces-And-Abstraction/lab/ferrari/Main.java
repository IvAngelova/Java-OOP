package T04_InterfacesAndAbstraction.lab.ferrari;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);
         String driverName = scan.nextLine();
         Ferrari ferrari = new Ferrari(driverName);
        System.out.println(ferrari.toString());
    }
}
