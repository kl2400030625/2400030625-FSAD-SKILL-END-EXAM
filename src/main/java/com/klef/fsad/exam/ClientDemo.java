package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Date;
import java.util.Scanner;

public class ClientDemo {
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    /**
     * Insert a new inventory record into the database
     */
    public static void insertInventory(Inventory inventory) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Integer id = (Integer) session.save(inventory);
            transaction.commit();
            System.out.println("\n✓ Inventory record inserted successfully!");
            System.out.println("  Generated ID: " + id);
            System.out.println("  Product: " + inventory.getName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("✗ Error inserting inventory record: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Delete an inventory record based on ID
     */
    public static void deleteInventory(int id) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Inventory inventory = session.get(Inventory.class, id);

            if (inventory != null) {
                session.delete(inventory);
                transaction.commit();
                System.out.println("\n✓ Inventory record with ID " + id + " deleted successfully!");
                System.out.println("  Deleted Product: " + inventory.getName());
            } else {
                transaction.commit();
                System.out.println("\n✗ Inventory record with ID " + id + " not found!");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("✗ Error deleting inventory record: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Display an inventory record by ID
     */
    public static void displayInventory(int id) {
        try (Session session = factory.openSession()) {
            Inventory inventory = session.get(Inventory.class, id);
            if (inventory != null) {
                System.out.println("\n" + inventory);
            } else {
                System.out.println("\nInventory record with ID " + id + " not found!");
            }
        } catch (Exception e) {
            System.out.println("Error retrieving inventory record: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Main method to demonstrate insert and delete operations
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("========================================");
        System.out.println("  Inventory Management System");
        System.out.println("========================================\n");

        while (running) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Insert a new inventory record");
            System.out.println("2. Delete an inventory record");
            System.out.println("3. View an inventory record");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    insertNewInventory(scanner);
                    break;

                case 2:
                    deleteInventoryRecord(scanner);
                    break;

                case 3:
                    viewInventoryRecord(scanner);
                    break;

                case 4:
                    running = false;
                    System.out.println("\nExiting application...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
        HibernateUtil.shutdown();
    }

    /**
     * Helper method to insert new inventory via user input
     */
    private static void insertNewInventory(Scanner scanner) {
        try {
            System.out.println("\n--- Insert New Inventory Record ---");
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter status (e.g., IN_STOCK, OUT_OF_STOCK): ");
            String status = scanner.nextLine();

            System.out.print("Enter supplier: ");
            String supplier = scanner.nextLine();

            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            Inventory inventory = new Inventory(name, description, quantity, price, 
                                              new Date(), status, supplier, category);
            insertInventory(inventory);

        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }

    /**
     * Helper method to delete inventory record via user input
     */
    private static void deleteInventoryRecord(Scanner scanner) {
        try {
            System.out.println("\n--- Delete Inventory Record ---");
            System.out.print("Enter inventory ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            deleteInventory(id);

        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }

    /**
     * Helper method to view inventory record via user input
     */
    private static void viewInventoryRecord(Scanner scanner) {
        try {
            System.out.println("\n--- View Inventory Record ---");
            System.out.print("Enter inventory ID to view: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("\nInventory Details:");
            displayInventory(id);

        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
}
