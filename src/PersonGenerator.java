import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {

        ArrayList<String> persons = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        boolean done = false;

        String record = "";
        String id = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int yob = 0;

        do {
            id = SafeInput.getNonZeroLenString(in, "Enter the ID of the person [6 Digits]" );
            firstName = SafeInput.getNonZeroLenString(in, "Enter the person's first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter the person's last name");
            title = SafeInput.getNonZeroLenString(in, "Enter the person's title");
            yob = SafeInput.getRangedInt(in,"Enter the person's year of birth", 0,9999);

            record = id + ", " + firstName + ", " + lastName + ", " + title + ", " + yob;
            persons.add(record);

            done = SafeInput.getYNConfirm(in, "Do you have any more records?");

        }while(done);

        for( String f: persons)
            System.out.println(f);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        try{
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file,CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec: persons){
                writer.write(rec,0,rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data has been successfully written");

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
