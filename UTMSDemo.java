
interface Serviceable {
    void performMaintenance();
    @SuppressWarnings("unused")
    boolean isServiceDue();
}

interface Trackable {
    String getCurrentLocation();
    void updateLocation(String location);
}

interface Schedulable {
    void setSchedule(String schedule);
    String getSchedule();
}
abstract class User {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String userId, String name, String email, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @SuppressWarnings("unused")
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public abstract String requestTransport();

    public void displayUserInfo() {
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }
}

abstract class Vehicle implements Serviceable, Trackable, Schedulable {
    private String vehicleId;
    private String model;
    private int capacity;
    private String currentLocation;
    private String schedule;
    private boolean serviceRequired;

    public Vehicle(String vehicleId, String model, int capacity) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.capacity = capacity;
        this.currentLocation = "Garage";
        this.serviceRequired = false;
    }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    protected void setServiceRequired(boolean required) { this.serviceRequired = required; }
    protected boolean getServiceRequired() { return serviceRequired; }

    protected String getStoredCurrentLocation() { return currentLocation; }
    protected void setStoredCurrentLocation(String location) { this.currentLocation = location; }

    protected String getStoredSchedule() { return schedule; }
    protected void setStoredSchedule(String schedule) { this.schedule = schedule; }

    public abstract String getVehicleType();
}


class Student extends User {
    private String studentId;
    private String course;

    public Student(String userId, String name, String email, String phoneNumber, 
                  String studentId, String course) {
        super(userId, name, email, phoneNumber);
        this.studentId = studentId;
        this.course = course;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String requestTransport() {
        return "Student " + getName() + " (ID: " + studentId + ") requesting transport for academic purposes.\n" +
               "Course: " + course + " | Route: Campus to City Center";
    }
}

class Lecturer extends User {
    private String department;
    private String employeeId;

    public Lecturer(String userId, String name, String email, String phoneNumber,
                   String department, String employeeId) {
        super(userId, name, email, phoneNumber);
        this.department = department;
        this.employeeId = employeeId;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    @Override
    public String requestTransport() {
        return "Lecturer " + getName() + " (Employee ID: " + employeeId + ") from " + department + 
               " department requesting official transport for meetings/conferences";
    }
}

class TransportOfficer extends User {
    private String officerId;
    private String shift;

    public TransportOfficer(String userId, String name, String email, String phoneNumber,
                           String officerId, String shift) {
        super(userId, name, email, phoneNumber);
        this.officerId = officerId;
        this.shift = shift;
    }

    public String getOfficerId() { return officerId; }
    public void setOfficerId(String officerId) { this.officerId = officerId; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    @Override
    public String requestTransport() {
        return "Transport Officer " + getName() + " (Officer ID: " + officerId + ") on " + shift + 
               " shift requesting vehicle for administrative duties";
    }

    public String assignDriver(String vehicleType) {
        return "✓ Driver assigned to " + vehicleType + " vehicle";
    }

    public String assignDriver(String vehicleType, String shiftTime) {
        return "✓ Driver assigned to " + vehicleType + " vehicle for " + shiftTime + " shift";
    }

    public String assignDriver(String vehicleId, String driverId, String route) {
        return "✓ Driver " + driverId + " assigned to vehicle " + vehicleId + " for route: " + route;
    }
}
class Bus extends Vehicle {
    private int numberOfDoors;

    public Bus(String vehicleId, String model, int capacity, int numberOfDoors) {
        super(vehicleId, model, capacity);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() { return numberOfDoors; }
    public void setNumberOfDoors(int numberOfDoors) { this.numberOfDoors = numberOfDoors; }

    @Override
    public String getVehicleType() {
        return "Bus";
    }

    @Override
    public void performMaintenance() {
        setServiceRequired(false);
        System.out.println("🔧 Bus " + getVehicleId() + " (" + getModel() + ") maintenance completed");
    }

    @Override
    @SuppressWarnings("unused")
    public boolean isServiceDue() {
        return getServiceRequired();
    }

    // Interface implementations (Trackable)
    @Override
    public String getCurrentLocation() {
        return getStoredCurrentLocation();
    }

    @Override
    public void updateLocation(String location) {
        setStoredCurrentLocation(location);
        System.out.println("📍 Bus " + getVehicleId() + " location updated to: " + location);
    }

    @Override
    public void setSchedule(String schedule) {
        setStoredSchedule(schedule);
        System.out.println("📅 Bus " + getVehicleId() + " schedule set: " + schedule);
    }

    @Override
    public String getSchedule() {
        return getStoredSchedule();
    }
}

class Van extends Vehicle {
    private boolean hasAirConditioning;

    public Van(String vehicleId, String model, int capacity, boolean hasAirConditioning) {
        super(vehicleId, model, capacity);
        this.hasAirConditioning = hasAirConditioning;
    }

    public boolean hasAirConditioning() { return hasAirConditioning; }
    public void setAirConditioning(boolean hasAirConditioning) { this.hasAirConditioning = hasAirConditioning; }

    @Override
    public String getVehicleType() {
        return "Van";
    }

    @Override
    public void performMaintenance() {
        setServiceRequired(false);
        System.out.println("🔧 Van " + getVehicleId() + " (" + getModel() + ") maintenance completed");
    }

    @Override
    @SuppressWarnings("unused")
    public boolean isServiceDue() {
        return getServiceRequired();
    }

    @Override
    public String getCurrentLocation() {
        return getStoredCurrentLocation();
    }

    @Override
    public void updateLocation(String location) {
        setStoredCurrentLocation(location);
        System.out.println("📍 Van " + getVehicleId() + " location updated to: " + location);
    }

    @Override
    public void setSchedule(String schedule) {
        setStoredSchedule(schedule);
        System.out.println("📅 Van " + getVehicleId() + " schedule set: " + schedule);
    }

    @Override
    public String getSchedule() {
        return getStoredSchedule();
    }
}


class Driver {
    private String driverId;
    private String name;
    private String licenseNumber;
    private String licenseCategory;

    public Driver(String driverId, String name, String licenseNumber, String licenseCategory) {
        this.driverId = driverId;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.licenseCategory = licenseCategory;
    }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getLicenseCategory() { return licenseCategory; }
    public void setLicenseCategory(String licenseCategory) { this.licenseCategory = licenseCategory; }

    public void displayDriverInfo() {
        System.out.println("Driver ID: " + driverId + " | Name: " + name + 
                         " | License: " + licenseNumber + " (Category: " + licenseCategory + ")");
    }
}



public class UTMSDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║          VICTORIA UNIVERSITY TRANSPORT MANAGEMENT           ║");
        System.out.println("║                    SYSTEM DEMONSTRATION                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        Student student = new Student("U001", "John Doe", "john@vu.ac.ug",
                                    "0701234567", "STU001", "Computer Science");
        Lecturer lecturer = new Lecturer("U002", "Dr. Sarah Smith", "sarah@vu.ac.ug", 
                                       "0707654321", "IT Department", "EMP001");
        TransportOfficer officer = new TransportOfficer("U003", "Mike Johnson", "mike@vu.ac.ug", 
                                                      "0703456789", "OFF001", "Morning");

        Bus bus = new Bus("VH001", "Toyota Coaster", 30, 2);
        Van van = new Van("VH002", "Toyota HiAce", 14, true);

        Driver driver1 = new Driver("DR001", "James Okello", "DL12345", "D");
        Driver driver2 = new Driver("DR002", "Mary Nakato", "DL67890", "D");


        System.out.println("🔄 POLYMORPHISM DEMONSTRATION (Method Overriding)");
        System.out.println("═".repeat(60));
        System.out.println("1. " + student.requestTransport());
        System.out.println("\n2. " + lecturer.requestTransport());
        System.out.println("\n3. " + officer.requestTransport());
        System.out.println();


        System.out.println("⚙️  METHOD OVERLOADING DEMONSTRATION");
        System.out.println("═".repeat(60));
        System.out.println("1. " + officer.assignDriver("Bus"));
        System.out.println("2. " + officer.assignDriver("Van", "Evening"));
        System.out.println("3. " + officer.assignDriver("VH001", "DR001", "Campus-City Center"));
        System.out.println();


        System.out.println("🔌 INTERFACE IMPLEMENTATION DEMONSTRATION");
        System.out.println("═".repeat(60));

        System.out.println("BUS OPERATIONS:");
        bus.setSchedule("08:00 AM - Campus to City Center");
        bus.updateLocation("City Center");
        System.out.println("📋 Bus Schedule: " + bus.getSchedule());
        System.out.println("📍 Bus Current Location: " + bus.getCurrentLocation());
        bus.performMaintenance();

        System.out.println("\nVAN OPERATIONS:");
        van.setSchedule("02:00 PM - Campus to Airport");
        van.updateLocation("Airport");
        System.out.println("📋 Van Schedule: " + van.getSchedule());
        System.out.println("📍 Van Current Location: " + van.getCurrentLocation());
        van.performMaintenance();
        System.out.println();


        System.out.println("🔐 ENCAPSULATION DEMONSTRATION");
        System.out.println("═".repeat(60));
        System.out.println("Accessing student data through getter methods:");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Student Course: " + student.getCourse());
        System.out.println("Student Email: " + student.getEmail());

        System.out.println("\nModifying data through setter methods:");
        student.setCourse("Information Technology");
        student.setEmail("john.doe@vu.ac.ug");
        System.out.println("Updated Course: " + student.getCourse());
        System.out.println("Updated Email: " + student.getEmail());
        System.out.println();


        System.out.println("🎭 ABSTRACTION DEMONSTRATION");
        System.out.println("═".repeat(60));
        System.out.println("Vehicle Types (Abstract method implementation):");
        System.out.println("Vehicle 1: " + bus.getVehicleType() + " (ID: " + bus.getVehicleId() + 
                         ", Model: " + bus.getModel() + ", Capacity: " + bus.getCapacity() + ")");
        System.out.println("Vehicle 2: " + van.getVehicleType() + " (ID: " + van.getVehicleId() + 
                         ", Model: " + van.getModel() + ", Capacity: " + van.getCapacity() + ")");
        System.out.println();


        System.out.println("👥 USER INFORMATION DISPLAY");
        System.out.println("═".repeat(60));
        System.out.println("STUDENT INFO:");
        student.displayUserInfo();
        System.out.println("\nLECTURER INFO:");
        lecturer.displayUserInfo();
        System.out.println("\nTRANSPORT OFFICER INFO:");
        officer.displayUserInfo();
        System.out.println();

        System.out.println("🚗 DRIVER INFORMATION");
        System.out.println("═".repeat(60));
        driver1.displayDriverInfo();
        driver2.displayDriverInfo();

        System.out.println("\n" + "═".repeat(60));
        System.out.println("✅ UTMS DEMONSTRATION COMPLETED SUCCESSFULLY");
        System.out.println("All OOP principles have been demonstrated:");
        System.out.println("• Abstraction ✓  • Encapsulation ✓  • Inheritance ✓");
        System.out.println("• Polymorphism ✓  • Interfaces ✓  • Method Overloading ✓");
        System.out.println("═".repeat(60));
    }
}
