package bai9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable, Comparable<Student>{
	private String name;
	private int id;
	private double gpa;

	public Student(String name, int id, double gpa) {
		this.name = name;
		this.id = id;
		this.gpa = gpa;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public double getGpa() {
		return gpa;
	}

	public int compareTo(Student other) {
		return Double.compare(this.gpa, other.gpa);
	}

	@Override
	public String toString() {
		return "Ten: " + name + ", ID: " + id + ", GPA: " + gpa;
	}
}

public class StudentManager {
	private List<Student> students;
	private Scanner scanner;

	public StudentManager() {
		students = new ArrayList<>();
		scanner = new Scanner(System.in);
	}

	public void addStudent() {
		System.out.println("Nhap ten HS:");
		String name = scanner.nextLine();
		System.out.println("Nhap ID HS:");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Nhap diem GPA:");
		double gpa = Double.parseDouble(scanner.nextLine());

		students.add(new Student(name, id, gpa));
	}
	public void displayStudents() {
		Collections.sort(students);
		for (Student student : students) {
			System.out.println(student);
		}
	}

	public void saveToFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
			oos.writeObject(students);
			System.out.println("Da luu HS.");
		} catch (IOException e) {
			System.err.println("Khong luu duoc: " + e.getMessage());
		}
	}

	public void searchStudentByName() {
		System.out.println("Tim ten HS:");
		String searchName = scanner.nextLine();
		boolean found = false;
		for (Student student : students) {
			if (student.getName().equalsIgnoreCase(searchName)) {
				System.out.println("Da thay HS: " + student);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Khong co HS co ten do.");
		}
	}

	public static void main(String[] args) {
		StudentManager manager = new StudentManager();
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("\nQuan ly Học Sinh");
			System.out.println("1. Them HS");
			System.out.println("2. Hien HS");
			System.out.println("3. Luu HS vao File");
			System.out.println("4. Tim HS bang ten");
			System.out.println("5. Thoat");
			System.out.println("Nhap lua chon:");

			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:
				manager.addStudent();
				break;
			case 2:
				manager.displayStudents();
				break;
			case 3:
				manager.saveToFile();
				break;
			case 4:
				manager.searchStudentByName();
				break;
			case 5:
				exit = true;
				System.out.println("Thoat chuong trinh...");
				break;
			default:
				System.out.println("Fail, hay chon lai.");
			}
		}
	}
}
