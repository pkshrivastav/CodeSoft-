import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a course in the system
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean hasAvailableSlots() {
        return enrolled < capacity;
    }

    public boolean enrollStudent() {
        if (hasAvailableSlots()) {
            enrolled++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    public void displayCourseInfo() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled: " + enrolled);
        System.out.println("Available Slots: " + (capacity - enrolled));
    }
}

// Represents a student in the system
class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (course.hasAvailableSlots() && !registeredCourses.contains(course)) {
            if (course.enrollStudent()) {
                registeredCourses.add(course);
                System.out.println("Successfully registered for " + course.getTitle());
                return true;
            }
        }
        System.out.println("Failed to register for " + course.getTitle() + ". Course might be full or already registered.");
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            if (course.dropStudent()) {
                registeredCourses.remove(course);
                System.out.println("Successfully dropped " + course.getTitle());
                return true;
            }
        }
        System.out.println("Failed to drop " + course.getTitle() + ". You are not registered for this course.");
        return false;
    }

    public void displayRegisteredCourses() {
        System.out.println("Courses registered by " + name + ":");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println("- " + course.getTitle() + " (" + course.getCourseCode() + ")");
            }
        }
    }
}

// Manages the course registration system
public class CourseRegistrationSystem {
    private List<Course> courseCatalog;
    private List<Student> students;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        courseCatalog = new ArrayList<>();
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadSampleData();
    }

    private void loadSampleData() {
        courseCatalog.add(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 30, "Mon/Wed 9:00-10:30"));
        courseCatalog.add(new Course("MA101", "Calculus I", "Introduction to calculus", 25, "Tue/Thu 10:30-12:00"));
        courseCatalog.add(new Course("PHY101", "Physics I", "Basic physics concepts", 20, "Mon/Wed 11:00-12:30"));

        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
        students.add(new Student("S003", "Charlie"));
    }

    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseCatalog) {
            System.out.println("=================================");
            course.displayCourseInfo();
        }
    }

    public Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        System.out.println("Student not found.");
        return null;
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courseCatalog) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        System.out.println("Course not found.");
        return null;
    }

    public void registerForCourse(Student student) {
        displayCourses();
        System.out.print("Enter the course code to register: ");
        String courseCode = scanner.next();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            student.registerCourse(course);
        }
    }

    public void dropCourse(Student student) {
        student.displayRegisteredCourses();
        System.out.print("Enter the course code to drop: ");
        String courseCode = scanner.next();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            student.dropCourse(course);
        }
    }

    public void start() {
        while (true) {
            System.out.println("\nCourse Registration System");
            System.out.println("1. View Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    System.out.print("Enter your student ID: ");
                    String studentID = scanner.next();
                    Student student = findStudentByID(studentID);
                    if (student != null) {
                        registerForCourse(student);
                    }
                    break;
                case 3:
                    System.out.print("Enter your student ID: ");
                    studentID = scanner.next();
                    student = findStudentByID(studentID);
                    if (student != null) {
                        dropCourse(student);
                    }
                    break;
                case 4:
                    System.out.print("Enter your student ID: ");
                    studentID = scanner.next();
                    student = findStudentByID(studentID);
                    if (student != null) {
                        student.displayRegisteredCourses();
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.start();
    }
}

