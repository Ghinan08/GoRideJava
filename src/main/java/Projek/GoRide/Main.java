package Projek.GoRide;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static HashMap<Integer, User> users = new HashMap<>();
    private static HashMap<Integer, Driver> drivers = new HashMap<>();
    private static HashMap<Integer, Order> orders = new HashMap<>();
    private static HashMap<Integer, DriverStatus> driverStatuses = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int orderIdCounter = 1;

    public static void main(String[] args) {
        initializeData();
        
        System.out.println("=== Selamat datang di Aplikasi GoRide ===");
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Masuk sebagai Pengguna");
            System.out.println("2. Masuk sebagai Driver");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        userMenu();
                        break;
                    case 2:
                        driverMenu();
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Terima kasih telah menggunakan GoRide!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid!");
            }
        }
        
        scanner.close();
    }
    
    private static void initializeData() {
        users.put(1, new User(1, "Darla", 8954227, "darla@gmail.com"));
        users.put(2, new User(2, "Budi", 8112233, "budi@gmail.com"));
        
        drivers.put(101, new Driver(101, "Rafi", 8113991, "rafi@gmail.com", "ER012D", "Motor", "Yamaha"));
        drivers.put(102, new Driver(102, "Andi", 8224455, "andi@gmail.com", "AB123C", "Mobil", "Toyota"));
        
        driverStatuses.put(101, new DriverStatus(101, true, "Bojongsoang"));
        driverStatuses.put(102, new DriverStatus(102, false, "Sukabirus"));
    }
    
    private static void userMenu() {
        System.out.print("\nMasukkan User ID Anda: ");
        try {
            int userId = Integer.parseInt(scanner.nextLine());
            
            if (!users.containsKey(userId)) {
                System.out.println("User ID tidak ditemukan!");
                return;
            }
            
            User user = users.get(userId);
            System.out.println("\nSelamat datang, " + user.getName() + "!");
            
            boolean backToMain = false;
            while (!backToMain) {
                System.out.println("\nMenu Pengguna:");
                System.out.println("1. Pesan GoRide");
                System.out.println("2. Lihat Riwayat Pesanan");
                System.out.println("3. Kembali ke Menu Utama");
                System.out.print("Pilih menu: ");
                
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        orderGoRide(userId);
                        break;
                    case 2:
                        viewOrderHistory(userId);
                        break;
                    case 3:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("User ID harus berupa angka!");
        }
    }
    
    private static void orderGoRide(int userId) {
        System.out.println("\n== Pesan GoRide ==");
        System.out.print("Masukkan lokasi penjemputan: ");
        String pickup = scanner.nextLine();
        
        System.out.print("Masukkan tujuan: ");
        String destination = scanner.nextLine();
        
        System.out.print("Masukkan catatan (opsional): ");
        String notes = scanner.nextLine();
        
        Integer availableDriverId = findAvailableDriverId();
        if (availableDriverId == null) {
            System.out.println("Maaf, tidak ada driver yang tersedia saat ini.");
            return;
        }
        
        Driver availableDriver = drivers.get(availableDriverId);
        
        double distance = 1.0 + random.nextDouble() * 14.0;
        distance = Math.round(distance * 10.0) / 10.0; 
        
        double duration = (distance / 40) * 60; 
        Route route = new Route(distance, duration);
        double fare = route.estimateFare();
        
        Order order = new Order(orderIdCounter++, userId, availableDriverId, fare, route);
        orders.put(order.getId(), order);
        users.get(userId).addOrderToHistory(order);

        System.out.println("\nPesanan berhasil dibuat!");
        System.out.println("Driver: " + availableDriver.getName());
        System.out.println("Kendaraan: " + availableDriver.getModel() + " (" + availableDriver.getplateNumber() + ")");
        System.out.println("Jarak perjalanan: " + distance + " km");
        System.out.println("Perkiraan tarif: Rp" + fare);
        
        driverStatuses.get(availableDriverId).goOffline();
        
        processRide(order.getId(), availableDriverId);
    }
    
    private static Integer findAvailableDriverId() {
        for (DriverStatus status : driverStatuses.values()) {
            if (status.isAvailable()) {
                return status.getDriverId();
            }
        }
        return null;
    }
    
    private static void processRide(int orderId, int driverId) {
        Order order = orders.get(orderId);
        Driver driver = drivers.get(driverId);
        
        System.out.println("\nStatus Pesanan: " + order.getStatus());
        System.out.println("1. Mulai Perjalanan");
        System.out.println("2. Batalkan Pesanan");
        System.out.print("Pilih aksi: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice == 1) {
                order.startRide();
                System.out.println("Perjalanan telah dimulai!");
                
                System.out.println("\nPerjalanan sedang berlangsung...");
                System.out.println("1. Selesaikan Perjalanan");
                System.out.print("Pilih aksi: ");
                
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    order.endRide();
                    System.out.println("Perjalanan telah selesai!");
                    
                    processPayment(order);
                    
                    giveRating(driverId);
                    
                    driverStatuses.get(driverId).goOnline();
                }
            } else if (choice == 2) {
                order.cancel();
                System.out.println("Pesanan telah dibatalkan.");
                driverStatuses.get(driverId).goOnline();
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }
    
    private static void processPayment(Order order) {
        System.out.println("\n== Pembayaran ==");
        System.out.println("Total yang harus dibayar: Rp" + order.getFare());
        
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. GoPay");
        System.out.println("2. Kartu Kredit");
        System.out.println("3. Tunai");
        System.out.print("Pilihan: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            final String method;
            
            switch (choice) {
                case 1:
                    method = "GoPay";
                    break;
                case 2:
                    method = "Kartu Kredit";
                    break;
                case 3:
                    method = "Tunai";
                    break;
                default:
                    System.out.println("Pilihan tidak valid, menggunakan Tunai sebagai default.");
                    method = "Tunai";
            }
            
            PaymentMethod paymentMethod = new PaymentMethod() {
                @Override
                public String getPaymentId() {
                    return "PAY-" + System.currentTimeMillis();
                }

                @Override
                public String getStatus() {
                    return "PAID";
                }

                @Override
                public void makePayment(double amount) {
                    System.out.println("Pembayaran sebesar Rp" + amount + " dengan " + method + " berhasil.");
                }
            };
            
            Payment payment = new Payment(paymentMethod.getPaymentId());
            payment.makePayment(order.getFare());
            
            int paymentId = (int) (System.currentTimeMillis() % 1000000);
            Invoice invoice = new Invoice(order.getId(), paymentId, 
                                        "Pembayaran untuk order #" + order.getId(), 
                                        (int) (System.currentTimeMillis() / 1000));
            System.out.println("Invoice telah dibuat: #" + invoice.getId());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }
    
    private static void giveRating(int driverId) {
        System.out.println("\n== Beri Rating ==");
        System.out.print("Beri rating (1-5): ");
        
        try {
            int rating = Integer.parseInt(scanner.nextLine());
            if (rating < 1 || rating > 5) {
                System.out.println("Rating harus antara 1-5. Rating default 5 diberikan.");
                rating = 5;
            }
            
            System.out.print("Masukkan komentar (opsional): ");
            String comment = scanner.nextLine();
            
            Rating driverRating = new Rating(driverId);
            driverRating.submit(rating, comment);
            
            System.out.println("Terima kasih atas ratingnya!");
        } catch (NumberFormatException e) {
            System.out.println("Rating harus berupa angka!");
        }
    }
    
    private static void viewOrderHistory(int userId) {
        System.out.println("\n== Riwayat Pesanan ==");
        List<Order> userOrders = users.get(userId).getOrderHistory();
        
        if (userOrders.isEmpty()) {
            System.out.println("Belum ada riwayat pesanan.");
            return;
        }
        
        for (Order order : userOrders) {
            Driver driver = drivers.get(order.getDriverId());
            Route route = order.getRoute();
            
            System.out.println("\nID Pesanan: " + order.getId());
            System.out.println("Driver: " + driver.getName());
            System.out.println("Kendaraan: " + driver.getModel() + " (" + driver.getplateNumber() + ")");
            System.out.println("Jarak: " + route.getDistance() + " km");
            System.out.println("Status: " + order.getStatus());
            System.out.println("Tarif: Rp" + order.getFare());
            System.out.println("---------------------");
        }
    }
    
    private static void driverMenu() {
        System.out.print("\nMasukkan Driver ID Anda: ");
        try {
            int driverId = Integer.parseInt(scanner.nextLine());
            
            if (!drivers.containsKey(driverId)) {
                System.out.println("Driver ID tidak ditemukan!");
                return;
            }
            
            Driver driver = drivers.get(driverId);
            DriverStatus status = driverStatuses.get(driverId);
            System.out.println("\nSelamat datang, " + driver.getName() + "!");
            System.out.println("Status Anda saat ini: " + (status.isAvailable() ? "Online" : "Offline"));
            
            boolean backToMain = false;
            while (!backToMain) {
                System.out.println("\nMenu Driver:");
                System.out.println("1. " + (status.isAvailable() ? "Go Offline" : "Go Online"));
                System.out.println("2. Lihat Pesanan");
                System.out.println("3. Update Lokasi");
                System.out.println("4. Kembali ke Menu Utama");
                System.out.print("Pilih menu: ");
                
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        toggleDriverStatus(driverId);
                        status = driverStatuses.get(driverId);
                        System.out.println("Status Anda sekarang: " + (status.isAvailable() ? "Online" : "Offline"));
                        break;
                    case 2:
                        viewDriverOrders(driverId);
                        break;
                    case 3:
                        updateDriverLocation(driverId);
                        break;
                    case 4:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Driver ID harus berupa angka!");
        }
    }
    
    private static void toggleDriverStatus(int driverId) {
        DriverStatus status = driverStatuses.get(driverId);
        if (status.isAvailable()) {
            status.goOffline();
            System.out.println("Anda sekarang Offline.");
        } else {
            status.goOnline();
            System.out.println("Anda sekarang Online dan siap menerima pesanan.");
        }
    }
    
    private static void viewDriverOrders(int driverId) {
        System.out.println("\n== Pesanan Anda ==");
        boolean found = false;
        
        for (Order order : orders.values()) {
            if (order.getDriverId() == driverId) {
                found = true;
                User user = users.get(order.getUserId());
                Route route = order.getRoute();
                
                System.out.println("\nID Pesanan: " + order.getId());
                System.out.println("Pelanggan: " + user.getName());
                System.out.println("Jarak: " + route.getDistance() + " km");
                System.out.println("Status: " + order.getStatus());
                System.out.println("Tarif: Rp" + order.getFare());
                System.out.println("---------------------");
            }
        }
        
        if (!found) {
            System.out.println("Belum ada pesanan.");
        }
    }
    
    private static void updateDriverLocation(int driverId) {
        System.out.print("\nMasukkan lokasi Anda saat ini: ");
        String location = scanner.nextLine();
        
        DriverStatus status = driverStatuses.get(driverId);
        status.updateLocation(location);
        System.out.println("Lokasi berhasil diperbarui: " + location);
    }
}