
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
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
        for (int i = 0; i < 3; i++) {
            System.out.println(database[i]);
        }
    }

    public static void AddRecord(BufferedReader in,
            HashMap<String, IRecord>[] database) throws IOException {
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
                database[2].put(
                        String.valueOf(transaction.InventoryId)
                                + String.valueOf(transaction.UserId),
                        transaction);
                break;
            default:
                break;
        }
    }

    public static void UpdateRecord(BufferedReader in,
            HashMap<String, IRecord>[] database) throws IOException {
        System.out.println("What record would you like to modify?");
        System.out.println("0 : Member");
        System.out.println("1 : Warehouse");
        System.out.println("2 : Transaction");
        String currRecordModifyIndex = in.readLine();
        switch (currRecordModifyIndex) {
            case "0":
                System.out.println("Which member do you want to modify by ID?");
                System.out.println(
                        database[Integer.parseInt(currRecordModifyIndex)]);
                int change = Integer.parseInt(in.readLine());
                if (database[Integer.parseInt(currRecordModifyIndex)]
                        .containsKey(String.valueOf(change))) {
                    Member member = new Member(in);
                    if (member.Id == change) {
                        database[0].replace(String.valueOf(change), member);
                    } else {
                        System.out.println("Please enter a valid key");
                    }
                } else {
                    System.out.println("Please enter a valid id");
                }
                break;
            case "1":
                System.out.println(
                        "Which warehouse do you want to modify by address?");
                System.out.println(
                        database[Integer.parseInt(currRecordModifyIndex)]);
                String changeWare = in.readLine();
                if (database[Integer.parseInt(currRecordModifyIndex)]
                        .containsKey(changeWare)) {
                    Warehouse warehouse = new Warehouse(in);
                    if (warehouse.Address == changeWare) {
                        database[0].replace(String.valueOf(changeWare),
                                warehouse);
                    } else {
                        System.out.println("Please enter a valid address");
                    }
                } else {
                    System.out.println("Please enter a valid address");
                }
                break;
            case "2":

                System.out.println(
                        database[Integer.parseInt(currRecordModifyIndex)]);
                System.out.println(
                        "Which transaction do you want to modify by inventory ID?");
                String invenId = in.readLine();
                System.out.println(
                        "Which transaction do you want to modify by user ID?");
                String userId = in.readLine();
                String fullId = invenId + userId;
                if (database[Integer.parseInt(currRecordModifyIndex)]
                        .containsKey(fullId)) {
                    Transaction transaction = new Transaction(in);
                    if ((String.valueOf(transaction.InventoryId)
                            + String.valueOf(transaction.UserId) == fullId)) {
                        database[0].replace(String.valueOf(fullId),
                                transaction);
                    } else {
                        System.out.println("Please enter valid ids");
                    }
                } else {
                    System.out.println("Please enter valid ids");
                }
                break;
            default:
                break;
        }

    }

    public static void DeleteRecord(BufferedReader in,
            HashMap<String, IRecord>[] database) throws IOException {
        System.out.println("From which category would you like to delete?");
        System.out.println("0 : Member");
        System.out.println("1 : Warehouse");
        System.out.println("2 : Transaction");
        String currRecordModifyIndex = in.readLine();
        switch (currRecordModifyIndex) {
            case "0":
                System.out.println("Which member do you want to delete by ID?");
                System.out.println(
                        database[Integer.parseInt(currRecordModifyIndex)]);
                int change = Integer.parseInt(in.readLine());
                if (database[Integer.parseInt(currRecordModifyIndex)]
                        .containsKey(String.valueOf(change))) {
                    database[Integer.parseInt(currRecordModifyIndex)]
                            .remove(String.valueOf(change));
                } else {
                    System.out.println("Please enter a valid id");
                }
                break;
            case "1":
                System.out.println(
                        "Which warehouse do you want to delete by address?");
                System.out.println(
                        database[Integer.parseInt(currRecordModifyIndex)]);
                String changeWare = in.readLine();
                if (database[Integer.parseInt(currRecordModifyIndex)]
                        .containsKey(changeWare)) {
                    database[Integer.parseInt(currRecordModifyIndex)]
                            .remove(changeWare);
                } else {
                    System.out.println("Please enter a valid address");
                }
                break;
            case "2":

                System.out.println(
                        database[Integer.parseInt(currRecordModifyIndex)]);
                System.out.println(
                        "Which transaction do you want to delete by inventory ID?");
                String invenId = in.readLine();
                System.out.println(
                        "Which transaction do you want to delete by user ID?");
                String userId = in.readLine();
                String fullId = invenId + userId;
                if (database[Integer.parseInt(currRecordModifyIndex)]
                        .containsKey(fullId)) {
                    database[Integer.parseInt(currRecordModifyIndex)]
                            .remove(fullId);
                } else {
                    System.out.println("Please enter valid ids");
                }
                break;
            default:
                break;
        }
    }

    public static void SearchRecord(BufferedReader in,
            HashMap<String, IRecord>[] database) throws IOException {
        System.out.println("From which category would you like to search?");
        System.out.println("0 : Member");
        System.out.println("1 : Warehouse");
        System.out.println("2 : Transaction");
        String currRecordModifyIndex = in.readLine();
        switch (currRecordModifyIndex) {
            case "0":
                System.out.println("Which member do you want to search by ID?");
                System.out.println(
                        database[Integer.parseInt(currRecordModifyIndex)]);
                int change = Integer.parseInt(in.readLine());
                if (database[Integer.parseInt(currRecordModifyIndex)]
                        .containsKey(String.valueOf(change))) {
                    System.out.println(
                            database[Integer.parseInt(currRecordModifyIndex)]
                                    .get(String.valueOf(change)).toString());
                } else {
                    System.out.println("Please enter a valid id");
                }
                break;
            case "1":
                System.out.println(
                        "Which warehouse do you want to search by address?");
                System.out.println(
                        database[Integer.parseInt(currRecordModifyIndex)]);
                String changeWare = in.readLine();
                if (database[Integer.parseInt(currRecordModifyIndex)]
                        .containsKey(changeWare)) {
                    System.out.println(
                            database[Integer.parseInt(currRecordModifyIndex)]
                                    .get(changeWare).toString());
                } else {
                    System.out.println("Please enter a valid address");
                }
                break;
            case "2":

                System.out.println(
                        database[Integer.parseInt(currRecordModifyIndex)]);
                System.out.println(
                        "Which transaction do you want to search by inventory ID?");
                String invenId = in.readLine();
                System.out.println(
                        "Which transaction do you want to search by user ID?");
                String userId = in.readLine();
                String fullId = invenId + userId;
                if (database[Integer.parseInt(currRecordModifyIndex)]
                        .containsKey(fullId)) {
                    System.out.println(
                            database[Integer.parseInt(currRecordModifyIndex)]
                                    .get(fullId).toString());
                } else {
                    System.out.println("Please enter valid ids");
                }
                break;
            default:
                break;
        }
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
            this.edit(in);
        } catch (IOException e) {
        }
        ;
    }

    @Override
    public void edit(BufferedReader in) throws IOException {
        System.out.println("Modifying Member: ");
        System.out.println("Id:");
        this.Id = Integer.parseInt(in.readLine());
        System.out.println("Warehouse Address:");
        this.WarehouseAddress = in.readLine();
        System.out.println("Phone Number:");
        this.PhoneNumber = Long.parseLong(in.readLine());
        System.out.println("Start Date:");
        this.StartDate = in.readLine();
        System.out.println("Address:");
        this.Address = in.readLine();
        System.out.println("Email:");
        this.Email = in.readLine();
        System.out.println("First Name:");
        this.FirstName = in.readLine();
        System.out.println("Last Name");
        this.LastName = in.readLine();
    }

    @Override
    public String toString() {
        return "(" + this.Id + "," + this.WarehouseAddress + ","
                + this.PhoneNumber + "," + this.StartDate + "," + this.Address
                + "," + this.Email + "," + this.FirstName + "," + this.LastName
                + ")";
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
            this.edit(in);
        } catch (IOException e) {
        }
        ;
    }

    @Override
    public void edit(BufferedReader in) throws IOException {
        System.out.println("Modifying Warehouse ");
        System.out.println("Warehouse Address:");
        this.Address = in.readLine();
        System.out.println("Storage Capacity:");
        this.StorageCapacity = Integer.parseInt(in.readLine());
        System.out.println("Drone Capacity:");
        this.DroneCapacity = Integer.parseInt(in.readLine());
        System.out.println("Phone Number:");
        this.PhoneNumber = Long.parseLong(in.readLine());
        System.out.println("Manager Name:");
        this.ManagerName = in.readLine();
        System.out.println("City:");
        this.City = in.readLine();
    }

    @Override
    public String toString() {
        return "(" + this.Address + "," + this.StorageCapacity + ","
                + this.DroneCapacity + "," + this.PhoneNumber + ","
                + this.ManagerName + "," + this.City + ")";
    }
}

class Transaction implements IRecord {
    enum Status {
        RENTED, RETURNED, Other;
    }

    public long InventoryId;
    public long UserId;
    public String OrderDate;
    public float Fee;
    public Status EquipmentStatus;

    public Transaction(BufferedReader in) {
        try {
            this.edit(in);
        } catch (IOException e) {
        }
        ;
    }

    @Override
    public void edit(BufferedReader in) throws IOException {
        System.out.println("Modifying Transaction ");
        System.out.println("Inventory Id:");
        this.InventoryId = Long.parseLong(in.readLine());
        System.out.println("User Id:");
        this.UserId = Long.parseLong(in.readLine());
        System.out.println("Order Date:");
        this.OrderDate = in.readLine();
        System.out.println("Fee:");
        this.Fee = Float.parseFloat(in.readLine());
        System.out.println(
                "Equipment Status: 0 = Rented, 1 = Returned, default = Other");
        switch (in.readLine()) {
            case "0":
                this.EquipmentStatus = Status.RENTED;
                break;
            case "1":
                this.EquipmentStatus = Status.RETURNED;
                break;
            default:
                this.EquipmentStatus = Status.Other;
                break;
        }
    }

    @Override
    public String toString() {
        return "(" + this.InventoryId + "," + this.UserId + "," + this.OrderDate
                + "," + this.Fee + "," + this.EquipmentStatus + ")";
    }
}
