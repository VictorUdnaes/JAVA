public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank("National Australia bank");

        bank.addBranch("Adelaide");

        bank.addCustomer("Adelaide", "Tim", 50.05);
        bank.addCustomer("Adelaide", "Mike", 175.34);
        bank.addCustomer("Adelaide", "Percy", 220.12);

        bank.addCustomerTransaction("Adelaide", "Tim", 44.22);
        bank.addCustomerTransaction("Adelaide", "Tim", 12.44);
        bank.addCustomerTransaction("Adelaide", "Mike", 1.65);

        bank.listCustomers("Adelaide", true);
    }
}

import java.util.ArrayList;
import java.util.Objects;

public class Bank {
    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        branches = new ArrayList<Branch>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean addBranch(String branchName) {
        if (findBranch(branchName) == null) {
            Branch branch = new Branch(branchName);
            branches.add(branch);
            return true;
        }
        return false;
    }
    public boolean addCustomer(String branchName, String customerName, double initialTransaction) {
        if (findBranch(branchName) != null) {
            findBranch(branchName).newCustomer(customerName, initialTransaction);
            return true;
        }
        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
        if (findBranch(branchName) != null) {
            findBranch(branchName).addCustomerTransaction(customerName, transaction);
            return true;
        }
        return false;
    }

    public void listCustomers(String branchName, boolean printTransactions) {
        if ((findBranch(branchName) != null) && printTransactions) {
            System.out.println("\nCustomer detail for branch " + branchName);
            int customerListLength = findBranch(branchName).getCustomers().size();
            for (int i = 0; i < customerListLength; i++) {
                System.out.println("\nCustomer: " + findBranch(branchName).getCustomers().get(i));
                System.out.println("\nTransactions");

                for (int x = 0; x < findBranch(branchName).getTransactions().get(i).size(); x++) {
                    System.out.println("\nAmount " + findBranch(branchName).getTransactions().get(i));
                }
            }
        }
    }

    public Branch findBranch (String branchName) {
        if (branches.size() > 0) {
            for (int i = 0; i < branches.size(); i++) {
                if (branchName.equals(branches.get(i).getName())) {
                    return branches.get(i);
                }
            }
            return null;
        }
       else return null;
    }
}

import java.util.ArrayList;
import java.util.Objects;

public class Branch {

    private String name;
    private ArrayList<Customer> customers;

    public Branch (String name) {
        this.name = name;
        customers = new ArrayList<Customer>();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(name, branch.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getCustomers() {
        ArrayList<String> stringArray = new ArrayList<String>();
        for (int i=0;i<customers.size();i++) {
            stringArray.add(customers.get(i).getName());
        }
        return stringArray;
    }

    public ArrayList<ArrayList<Double>> getTransactions() {
        ArrayList<ArrayList<Double>> doubleArray = new ArrayList<ArrayList<Double>>();
        for (int i=0;i<customers.size();i++) {
            doubleArray.add(customers.get(i).getTransactions());
        }
        return doubleArray;
    }

    public boolean newCustomer (String name, double initialTransaction) {
        if(findCustomer(name) == null) {
            Customer newCustomer = new Customer(name, initialTransaction);
            customers.add(newCustomer);
            return true;
        }
        return false;
    }

    public boolean addCustomerTransaction (String name, double transaction) {
        if (findCustomer(name) != null) {
            findCustomer(name).addTransaction(transaction);
            return true;
        }
        return false;
    }

    public Customer findCustomer (String name) {
        if (customers.size() > 0) {
            for (int i = 0; i < customers.size(); i++) {
                if (name.equals(customers.get(i).getName())) {
                    return customers.get(i);
                }
            }
            return null;
        }
        else return null;
    }
}

import java.util.ArrayList;

public class Customer {

    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name, double initialTransaction) {
        this.name = name;
        transactions = new ArrayList<Double>();
        transactions.add(initialTransaction);

    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction (double transaction) {
        transactions.add(transaction);
    }
}



