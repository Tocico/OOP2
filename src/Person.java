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
    private LocalDate registeredDate;


    public Person(String name, String personalId, LocalDate registeredDate) {
        this.name = name;
        this.personalId = personalId;
        this.registeredDate = registeredDate;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getName() {
        return name;
    }

    public boolean isActiveMember() {
        Period date = Period.between(registeredDate, LocalDate.now());
        if (date.getYears() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addVisitationDate(String path) throws IOException {
        Path paths = Paths.get(path + personalId);
        if (Files.exists(paths)) {
            try (BufferedWriter ut =
                         Files.newBufferedWriter(paths, StandardOpenOption.APPEND);) {
                ut.append("\n" + LocalDate.now());
                ut.close();
                System.out.println("Uptaderat besökshistoriken");
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
        } else {
            Files.createFile(paths);
            try (BufferedWriter ut =
                         Files.newBufferedWriter(paths, StandardCharsets.UTF_8);) {
                ut.write("Namn: " + name + "\nPersonnummer: " + personalId + "\n\n---------------Besökshistorik-------------\n" + LocalDate.now());
                ut.close();
                System.out.println("Skapat besökshistoriken");
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
        }
        return false;
    }
}
