import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Toshiko Kuno
 * Date: 2020-10-09
 * Time: 08:58
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class Person {
    private String name;
    private String personalId;
    private LocalDate paymentDate;


    public Person(String name, String personalId, LocalDate paymentDate) {
        this.name = name;
        this.personalId = personalId;
        this.paymentDate = paymentDate;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getName() {
        return name;
    }

    /**
     * Räknar ut hur långt tid skillnad mellan idag och registrerat dag
     *
     * @return om det är aktiv eller inaktiv medlem
     */
    public boolean isActiveMember() {
        Period date = Period.between(paymentDate, LocalDate.now());
        if (date.getYears() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Skriva till fil
     */
    public boolean addVisitationDate(String membersFolderPath) {
        Path path = Paths.get(membersFolderPath + personalId);
        try  {
            if (!Files.exists(path)) {
                Files.createFile(path);
                BufferedWriter ut = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
                ut.write("Namn: " + name + "\nPersonnummer: " + personalId + "\n\n---------------Besökshistorik-------------\n" + LocalDate.now());
                ut.close();
                System.out.println("Skapat besökshistoriken");
            } else {
                BufferedWriter ut = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                ut.append("\n" + LocalDate.now());
                ut.close();
                System.out.println("Uptaderat besökshistoriken");
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Det gick inte att skriva till fil");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }
}
