import java.util.*;

public class BankManagementSystemWithMe {
    static class Account {
        int id;
        String name;
        String email;
        double balance;
        boolean frozen = false;
        String cardNumber = null;
        boolean cardBlocked = false;
        double loanAmount = 0;
        boolean loanApproved = false;
         ArrayList<String> Collectkora = new ArrayList<>();
        int largeLenDen = 0; 
Account(int id, String name, String email, double balance) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.balance = balance;
            Collectkora.add("Account created with deposit " + balance);
        }
    }
    static class Bank {
        HashMap<Integer, Account> accounts = new HashMap<>();
        Random random = new Random();
        int nextId = 1000;

        Account createAccount(String name, String email, double deposit) {
            int id = nextId++;
            Account account = new Account(id, name, email, deposit);
            accounts.put(id, account);
            return account;
        }
         Account get(int id) {
            return accounts.get(id);
        }
 boolean deposit(int id, double amount) {
            Account a = get(id);
            if (a == null) return false;

            a.balance += amount;
            a.Collectkora.add("Deposit: " + amount);
            return true;
           
        }
