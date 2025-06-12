package Projek.GoRide;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

// Asumsi: Anda memiliki kelas-kelas ini dengan constructor dan method yang sesuai.
// import Projek.GoRide.Model.*; // Anda mungkin perlu menyesuaikan ini

public class MainAlternative1 {

    // Gunakan HashMap untuk menyimpan data user, driver, dan pesanan
    private static HashMap<String, User> users = new HashMap<>();
    private static HashMap<String, Driver> drivers = new HashMap<>();
    private static HashMap<String, Order> orders = new HashMap<>();

    // Untuk membuat ID unik secara otomatis
    private static AtomicInteger userCounter = new AtomicInteger(1);
    private static AtomicInteger driverCounter = new AtomicInteger(1);
    private static AtomicInteger orderCounter = new AtomicInteger(1);

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // --- Data Awal (Contoh) ---
        initializeData();

        int menuInput = -1;
        while (menuInput != 0) {
            printHeader();
            System.out.print("=> Pilihan Anda: ");

            try {
                menuInput = Integer.parseInt(scanner.nextLine());

                if (menuInput == 1) {
                    registerUser();
                } else if (menuInput == 2) {
                    registerDriver();
                } else if (menuInput == 3) {
                    displayAllData();
                } else if (menuInput == 4) {
                    orderGoRide();
                } else if (menuInput == 5) {
                    completeOrder();
                } else if (menuInput == 0) {
                    System.out.println("\nTerima kasih telah menggunakan aplikasi GoRide. Sampai jumpa!");
                } else {
                    System.out.println("[Error] Pilihan tidak valid. Silakan coba lagi.");
                }

            } catch (NumberFormatException e) {
                System.out.println("[Error] Input harus berupa angka. Silakan coba lagi.");
            }
            System.out.println(); // Beri spasi untuk tampilan lebih rapi
        }
        scanner.close();
    }

    /**
     * Menampilkan menu utama aplikasi.
     */
    private static void printHeader() {
        System.out.println("======<< Sistem Manajemen GoRide >>======");
        System.out.println("1. Daftar User Baru");
        System.out.println("2. Daftar Driver Baru");
        System.out.println("3. Tampilkan Semua User & Driver");
        System.out.println("4. Pesan Go-Ride");
        System.out.println("5. Selesaikan Pesanan");
        System.out.println("0. Keluar");
        System.out.println("========================================");
    }

    /**
     * Logika untuk mendaftarkan user baru.
     */
    private static void registerUser() {
        try {
            System.out.println("\n--- Pendaftaran User Baru ---");
            System.out.print("Masukkan Nama: ");
            String name = scanner.nextLine();
            System.out.print("Masukkan Email: ");
            String email = scanner.nextLine();
            System.out.print("Masukkan No. Telepon: ");
            long phone = Long.parseLong(scanner.nextLine());

            String id = "USER" + String.format("%03d", userCounter.getAndIncrement());
            User newUser = new User(1, "name", 11112121, "email"); // Asumsi constructor User(id, name, phone, email)
            users.put(id, newUser);

            System.out.println("\n[Sukses] User baru berhasil didaftarkan dengan ID: " + id);
        } catch (Exception e) {
            System.out.println("[Error] Gagal mendaftarkan user: " + e.getMessage());
        }
    }

    /**
     * Logika untuk mendaftarkan driver baru.
     */
    private static void registerDriver() {
        try {
            System.out.println("\n--- Pendaftaran Driver Baru ---");
            System.out.print("Masukkan Nama: ");
            String name = scanner.nextLine();
            System.out.print("Masukkan Email: ");
            String email = scanner.nextLine();
            System.out.print("Masukkan No. Telepon: ");
            long phone = Long.parseLong(scanner.nextLine());

            System.out.println("--- Detail Kendaraan ---");
            System.out.print("Masukkan Plat Nomor: ");
            String plate = scanner.nextLine();
            System.out.print("Masukkan Tipe Kendaraan (misal: Motor): ");
            String type = scanner.nextLine();
            System.out.print("Masukkan Merek Kendaraan (misal: Honda): ");
            String brand = scanner.nextLine();

            String id = "DRV" + String.format("%03d", driverCounter.getAndIncrement());
            // Asumsi constructor Driver(id, name, phone, email, plate, type, brand)
            Driver newDriver = new Driver(1, "Asep", 111113232, "asa", "sasa", "asasas", "asasaa");
            drivers.put(id, newDriver);

            System.out.println("\n[Sukses] Driver baru berhasil didaftarkan dengan ID: " + id);
        } catch (Exception e) {
            System.out.println("[Error] Gagal mendaftarkan driver: " + e.getMessage());
        }
    }

    /**
     * Logika untuk memesan Go-Ride.
     */
    private static void orderGoRide() {
        try {
            System.out.println("\n--- Pesan Go-Ride ---");
            System.out.print("Masukkan ID User Anda: ");
            String userId = scanner.nextLine().toUpperCase();

            if (!users.containsKey(userId)) {
                System.out.println("[Error] User dengan ID '" + userId + "' tidak ditemukan.");
                return;
            }

            // Cari driver yang tersedia (status AVAILABLE)
            Driver availableDriver = null;
            for (Driver driver : drivers.values()) {
                // Asumsi ada method getStatus() di kelas Driver
                if (driver.updateStatus() == DriverStatus.AVAILABLE) { 
                    availableDriver = driver;
                    break;
                }
            }

            if (availableDriver == null) {
                System.out.println("[Info] Maaf, tidak ada driver yang tersedia saat ini.");
                return;
            }

            System.out.print("Masukkan Lokasi Jemput: ");
            String pickup = scanner.nextLine();
            System.out.print("Masukkan Lokasi Tujuan: ");
            String destination = scanner.nextLine();

            String orderId = "ORD" + String.format("%03d", orderCounter.getAndIncrement());
            User user = users.get(userId);
            
            // Asumsi ada constructor Order(id, user, driver, pickup, destination)
            Order newOrder = new Order(orderId, user, availableDriver, new Location(pickup), new Location(destination));
            orders.put(orderId, newOrder);

            // Ubah status driver menjadi BOOKED
            // Asumsi ada method setStatus() di kelas Driver
            availableDriver.setStatus(DriverStatus.BOOKED); 

            System.out.println("\n[Sukses] Pesanan berhasil dibuat!");
            System.out.println("ID Pesanan Anda: " + orderId);
            System.out.println("Driver Anda: " + availableDriver.getName() + " (" + availableDriver.getVehicle().getPlateNumber() + ")");

        } catch (Exception e) {
            System.out.println("[Error] Gagal membuat pesanan: " + e.getMessage());
        }
    }
    
    /**
     * Logika untuk menyelesaikan pesanan dan memberi rating.
     */
    private static void completeOrder() {
        try {
            System.out.println("\n--- Selesaikan Pesanan ---");
            System.out.print("Masukkan ID Pesanan: ");
            String orderId = scanner.nextLine().toUpperCase();

            if (!orders.containsKey(orderId)) {
                System.out.println("[Error] Pesanan dengan ID '" + orderId + "' tidak ditemukan.");
                return;
            }
            
            Order order = orders.get(orderId);
            
            // Asumsi ada method isCompleted() atau getStatus() pada kelas Order
            if (order.isCompleted()) { 
                System.out.println("[Info] Pesanan ini sudah pernah diselesaikan.");
                return;
            }

            System.out.print("Beri rating untuk driver (1-5): ");
            int ratingValue = Integer.parseInt(scanner.nextLine());
            
            // Asumsi ada method complete() dan addRating() pada kelas Order
            order.complete(ratingValue);
            
            // Ubah status driver kembali menjadi AVAILABLE
            order.getDriver().setStatus(DriverStatus.AVAILABLE);

            System.out.println("\n[Sukses] Pesanan " + orderId + " telah diselesaikan.");
            System.out.println("Terima kasih atas rating Anda!");

        } catch (Exception e) {
            System.out.println("[Error] Gagal menyelesaikan pesanan: " + e.getMessage());
        }
    }


    /**
     * Menampilkan semua data user dan driver yang terdaftar.
     */
    private static void displayAllData() {
        System.out.println("\n--- Daftar Semua User ---");
        if (users.isEmpty()) {
            System.out.println("Belum ada user yang terdaftar.");
        } else {
            for (User user : users.values()) {
                System.out.println(user.toString()); // Asumsi method toString() sudah di-override
            }
        }

        System.out.println("\n--- Daftar Semua Driver ---");
        if (drivers.isEmpty()) {
            System.out.println("Belum ada driver yang terdaftar.");
        } else {
            for (Driver driver : drivers.values()) {
                System.out.println(driver.toString()); // Asumsi method toString() sudah di-override
            }
        }
    }

    /**
     * Mengisi data awal untuk testing.
     */
    private static void initializeData() {
        // Data User Awal
        User user1 = new User(322, "Darla", 3233, "darla@gmail.com");
        users.put(user1.getId(), user1);
        userCounter.set(2); // Set counter setelah data awal

        // Data Driver Awal
        Driver driver1 = new Driver(00, "Rafi", 8113991L, "rafi@gmail.com", "D 1234 ABC", "Motor", "Yamaha");
        drivers.put(driver1.getId(), driver1);
        driverCounter.set(2);
    }
}