import java.util.*;

/**
 * Created by Toshiko Kuno
 * Date: 2020-10-09
 * Time: 09:12
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class Gym {

    private Members members = new Members();

    public void run() {
        String membersPath = "data/MembersFolder/";
        Scanner in = new Scanner(System.in);

        while (true) {
            String searchWd = "";
            System.out.println("Hur vill du söka kunden?");
            System.out.println("1: Personnummer  \n2: Namn");
            if (in.hasNextLine()) {
                try {
                    String input = in.nextLine().trim();
                    if (input.length() == 0 || input.isEmpty())
                        throw new NoSuchElementException();
                    else {
                        if (input.equals("1")) {
                            searchWd = readPersonalNummerData(in);
                        } else if (input.equals("2")) {
                            searchWd = readNameData(in);
                        } else {
                            throw new IllegalArgumentException("Vänligen mata in ett nummer, 1 eller 2");
                        }
                    }
                    Person member = members.getMember(searchWd);
                    if (member != null) {
                        System.out.println("Välkommen tillbaka " + member.getName());
                        System.out.println("Status: " + (member.isActiveMember() ? "aktiv" : "inaktiv"));
                        System.out.println("-----------------------------------------------------------");
                        if (member.isActiveMember()) {
                            if (member.addVisitationDate(membersPath))
                                break;
                            else throw new Exception("Något gick fel. Försök igen");
                        }
                    } else {
                        System.out.println("Inte medlem!");
                    }
                    System.out.println("-----------------------------------------------------------");

                } catch (NoSuchElementException e) {
                    System.out.println("Indata saknas, försök igen");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.exit(0);
            }

        }
    }

    public String readPersonalNummerData(Scanner in) {
        while (true) {
            System.out.println("Skriv ett personnummer(ÅÅMMDDNNNN)");
            if (in.hasNextLine()) {
                String input = in.nextLine().trim();
                if (input.length() == 0 || input.isEmpty())
                    throw new NoSuchElementException();
                else if (input.matches("\\d{10}")) { //digit && length == 10
                    return input;
                } else {
                    throw new IllegalArgumentException("Du måste skriva ett personnummer(ÅÅMMDDNNNN)");
                }
            } else System.exit(0);
        }
    }

    public String readNameData(Scanner in) {
        while (true) {
            System.out.println("Skriv kundens fullständiga namn(Förnamn Efternamn)");
            if (in.hasNextLine()) {
                String input = in.nextLine().trim();
                if (input.length() == 0 || input.isEmpty())
                    throw new NoSuchElementException();
                else if (input.matches("^[0-9]+$"))
                    throw new IllegalArgumentException("Felaktig inmatning.");
                else {
                    String[] split = input.split(" ");
                    input = "";
                    for (int i = 0; i < split.length; i++) {
                        if (split[i].length() != 0)
                            input = input.concat(split[i] + " ");
                    }
                    return input.trim();
                }
            } else System.exit(0);
        }
    }


    public Gym() {
        String customersFilePath = "data/customers.txt";
        members.generateMemberListFromFile(customersFilePath);
    }

    public static void main(String[] args) {
        Gym gym = new Gym();
        gym.run();
    }

}
