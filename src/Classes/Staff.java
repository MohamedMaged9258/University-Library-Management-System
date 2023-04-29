package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Staff {
    private String name;
    private String email;
    private String password;
    private String id;

    public Staff() {
    }

    public Staff(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        Random random = new Random();
        this.id = "ST" + String.format("%04d", random.nextInt(10000));
    }

    public Staff(String email, String password, String name, String id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static void saveStaffToFile(Staff staff) {
        try {
            FileWriter writer = new FileWriter("Staff.txt", true);
            writer.write(staff.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Staff> loadStaffFromFile(){
        ArrayList<Staff> Staff = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Staff.txt"));
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split("/");
                Staff staff = new Staff(parts[0], parts[1], parts[2], parts[3]);
                Staff.add(staff);
            }
            br.close();
        }catch (IOException e){
            System.out.println(e);
        }
        return Staff;
    }

    @Override
    public String toString() {
        return name + "/" +
                email + "/" +
                password + "/" +
                id + "\n";
    }
}
