
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // 0 = Member, 1 = Warehouse, 2 = Transactions
        HashMap<String, IRecord>[] database = new HashMap[3];
        database[0] = new HashMap<>();
        database[1] = new HashMap<>();
        database[2] = new HashMap<>();
        boolean run = true;
        while (run) {
            System.out.println("What would you like to do?");
            System.out.println("1 : Add new record.");
            System.out.println("2 : Edit record.");
            System.out.println("3 : Delete record.");
            System.out.println("4 : Search.");
            System.out.println("5 : Quit.");
            try {
                switch (in.readLine()) {
                    case "1":
                        AddRecord(in, database);
                        break;
                    case "2":
                        UpdateRecord(in, database);
                        break;
                    case "3":
                        DeleteRecord(in, database);
                        break;
                    case "4":
                        SearchRecord(in, database);
                        break;
                    case "5":
                        run = false;
                        break;
                    default:
                        continue;
                }
            } catch (IOException e) {
            }
        }
        for (int i = 0; i < 3; i++)
            System.out.println(database[i]);
    }

    public static void AddRecord(BufferedReader in, HashMap<String, IRecord>[] database) throws IOException {
        System.out.println("What type of record would you like to add?");
        System.out.println("0 : Member");
        System.out.println("1 : Warehouse");
        System.out.println("2 : Transaction");
        switch (in.readLine()) {
            case "0":
                Member member = new Member(in);
                database[0].put(String.valueOf(member.Id), member);
                break;
            case "1":
                Warehouse warehouse = new Warehouse(in);
                database[1].put(String.valueOf(warehouse.Address), warehouse);
                break;
            case "2":
                Transaction transaction = new Transaction(in);
                database[2].put(String.valueOf(transaction.InventoryId) + String.valueOf(transaction.UserId), transaction);
                break;
            default:
                break;
        }
    }

    public static void UpdateRecord(BufferedReader in, HashMap<String, IRecord>[] database) {
        /*
        TODO
        Prompt user
        Switch -> prompt user for key
            database[i].get(key).edit(in);
        * */
    }

    public static void DeleteRecord(BufferedReader in, HashMap<String, IRecord>[] database) {
        /*
        TODO
        Prompt user
        Switch -> prompt user for key
            database[i].get(key).edit(in);
        * */
    }

    public static void SearchRecord(BufferedReader in, HashMap<String, IRecord>[] database) {
    /*
        TODO
        Prompt user
        Switch -> prompt user for key
            database[i].get(key).toString();
        * */
    }
}

interface IRecord {
    void edit(BufferedReader in) throws IOException;
}

class Member implements IRecord {
    public long Id = -1;
    public String WarehouseAddress;
    public long PhoneNumber;
    public String StartDate;
    public String Address;
    public String Email;
    public String FirstName;
    public String LastName;

    public Member(BufferedReader in) {
        try {
            edit(in);
        } catch (IOException e) {
        }
        ;
    }

    public void edit(BufferedReader in) throws IOException {
        System.out.println("Modifying Member: ");
        System.out.println("Id:");
        Id = Integer.parseInt(in.readLine());
        System.out.println("Warehouse Address:");
        WarehouseAddress = in.readLine();
        System.out.println("Phone Number:");
        PhoneNumber = Long.parseLong(in.readLine());
        System.out.println("Start Date:");
        StartDate = in.readLine();
        System.out.println("Address:");
        Address = in.readLine();
        System.out.println("Email:");
        Email = in.readLine();
        System.out.println("First Name:");
        FirstName = in.readLine();
        System.out.println("Last Name");
        LastName = in.readLine();
    }
    public String toString(){
        return "("+Id+","+WarehouseAddress+","+PhoneNumber+","+StartDate+","+Address+","+Email+","+FirstName+","+LastName+")";
    }
}

class Warehouse implements IRecord {
    public String Address;
    public int StorageCapacity;
    public int DroneCapacity;
    public long PhoneNumber;
    public String ManagerName;
    public String City;

    public Warehouse(BufferedReader in) {
        try {
            edit(in);
        } catch (IOException e) {
        }
        ;
    }

    public void edit(BufferedReader in) throws IOException {
        System.out.println("Modifying Warehouse ");
        System.out.println("Warehouse Address:");
        Address = in.readLine();
        System.out.println("Storage Capacity:");
        StorageCapacity = Integer.parseInt(in.readLine());
        System.out.println("Drone Capacity:");
        DroneCapacity = Integer.parseInt(in.readLine());
        System.out.println("Phone Number:");
        PhoneNumber = Long.parseLong(in.readLine());
        System.out.println("Manager Name:");
        ManagerName = in.readLine();
        System.out.println("City:");
        City = in.readLine();
    }
    public String toString(){
        return "("+Address+","+StorageCapacity+","+DroneCapacity+","+PhoneNumber+","+ManagerName+","+City+")";
    }
}

class Transaction implements IRecord {
    enum Status {RENTED, RETURNED, Other;}

    public long InventoryId;
    public long UserId;
    public String OrderDate;
    public float Fee;
    public Status EquipmentStatus;

    public Transaction(BufferedReader in) {
        try {
            edit(in);
        } catch (IOException e) {
        }
        ;
    }

    public void edit(BufferedReader in) throws IOException {
        System.out.println("Modifying Transaction ");
        System.out.println("Inventory Id:");
        InventoryId = Long.parseLong(in.readLine());
        System.out.println("User Id:");
        UserId = Long.parseLong(in.readLine());
        System.out.println("Order Date:");
        OrderDate = in.readLine();
        System.out.println("Fee:");
        Fee = Float.parseFloat(in.readLine());
        System.out.println("Equipment Status: 0 = Rented, 1 = Returned, default = Other");
        switch (in.readLine()) {
            case "0":
                EquipmentStatus = Status.RENTED;
                break;
            case "1":
                EquipmentStatus = Status.RETURNED;
                break;
            default:
                EquipmentStatus = Status.Other;
                break;
        }
    }
    public String toString(){
        return "("+InventoryId+","+UserId+","+OrderDate+","+Fee+","+EquipmentStatus+")";
    }
}
