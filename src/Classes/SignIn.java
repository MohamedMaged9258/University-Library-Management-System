package Classes;

import java.io.*;

public class SignIn implements Serializable {
//    Student student = new Student("Mohamed", "Mo_mag_17", "medomaged1");


    // Save a single Student object to a file
    public void saveStudentToFile(Student student) {
        try (FileOutputStream fos = new FileOutputStream("Stu.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(student);
            System.out.println("Student saved to file: " + "Students");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load a single Student object from a file
    public Student loadStudentFromFile() {
        Student student = null;
        try (FileInputStream fis = new FileInputStream("Stu.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            student = (Student) ois.readObject();
            System.out.println("Student loaded from file: " + "Students");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return student;
    }
}
