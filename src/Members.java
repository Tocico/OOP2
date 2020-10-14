import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Toshiko Kuno
 * Date: 2020-10-10
 * Time: 17:40
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class Members {

    private ArrayList<Person> membersList = new ArrayList<>();

    /**
     * Läsa personposter från textfilen.
     *
     */
    public boolean generateMemberListFromFile(String path) {
        Path filePath = Paths.get(path);
        try {
            Scanner in = new Scanner(filePath);
            in.useDelimiter(",|\n");
            while (in.hasNext()) {
                String personalId = in.next().trim();
                String name = in.next().trim();
                LocalDate date = LocalDate.parse(in.next().trim());
                Person member = new Person(name, personalId, date);
                membersList.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Det gick inte att läsa in");
            return false;
        }
        return true;
    }

    public Person getMember(String searchWord) {
       return membersList.stream()
                .filter(member -> member.getName().equalsIgnoreCase(searchWord) ||
                        member.getPersonalId().equals(searchWord))
                .findFirst()
                .orElse(null);
    }

}
