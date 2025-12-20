import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.*;


class Account implements Serializable {
    int accountId;
    String name;
    String password;
    double balance;
    boolean frozen = false;
    int wrongAttempts = 0;
    boolean locked = false;
    boolean loanRequested = false;
    double loanAmount = 0;
    boolean loanApproved = false;
    ArrayList<String> transactions = new ArrayList<>();

    Account(int id, String name, String password, double balance) {
        this.accountId = id;
        this.name = name;
        this.password = password;
        this.balance = balance;
        log("Account created with balance: " + balance);
    }

    void log(String msg) {
        transactions.add(LocalDateTime.now() + " : " + msg);
        if (transactions.size() > 10) transactions.remove(0);
    }

    void deposit(double amount) {
        balance += amount;
        log("Deposited: " + amount);
    }

    boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            log("Withdrawn: " + amount);
            return true;
        }
        return false;
    }
}


class BankService {
    static final String FILE = "bank_data.dat";
    static HashMap<Integer, Account> accounts = loadData();

    static Account createAccount(String name, String pass, double bal) {
        int id = new Random().nextInt(9000) + 1000;
        Account acc = new Account(id, name, pass, bal);
        accounts.put(id, acc);
        saveData();
        return acc;
    }

    static Account login(int id, String pass) {
        Account acc = accounts.get(id);
        if (acc == null) return null;
        if (acc.locked) {
            JOptionPane.showMessageDialog(null, "Account Locked!");
            return null;
        }
        if (!acc.password.equals(pass)) {
            acc.wrongAttempts++;
            if (acc.wrongAttempts >= 3) {
                acc.locked = true;
                JOptionPane.showMessageDialog(null, "Account locked after 3 attempts!");
            }
            saveData();
            return null;
        }
        acc.wrongAttempts = 0;
        saveData();
        return acc;
    }

    static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(accounts);
        } catch (Exception e) { e.printStackTrace(); }
    }

    static HashMap<Integer, Account> loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (HashMap<Integer, Account>) ois.readObject();
        } catch (Exception e) { return new HashMap<>(); }
    }
}


class LoginFrame extends JFrame {
    LoginFrame() {
        setTitle("Bank Management System - Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField idField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Create Account");

        panel.add(new JLabel("Account ID / admin:"));
        panel.add(idField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(loginBtn);
        panel.add(registerBtn);
        add(panel);

        Runnable doLogin = () -> {
            try {
                String idText = idField.getText().trim();
                String pass = new String(passField.getPassword()).trim();
                if (idText.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Please enter ID and Password");
                    return;
                }
               
                if (idText.equalsIgnoreCase("admin") && pass.equals("admin123")) {
                    new AdminDashboard(); dispose(); return;
                }
                int id = Integer.parseInt(idText);
                Account acc = BankService.login(id, pass);
                if (acc != null) { new Dashboard(acc); dispose(); }
                else JOptionPane.showMessageDialog(this, "Invalid Login");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid Input"); }
        };

        
        loginBtn.addActionListener(e -> doLogin.run());
        idField.addActionListener(e -> passField.requestFocus());
        passField.addActionListener(e -> doLogin.run());
        registerBtn.addActionListener(e -> new RegisterFrame());

        setVisible(true);
    }
}


class RegisterFrame extends JFrame {
    RegisterFrame() {
        setTitle("Create New Account");
        setSize(350, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField nameField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JTextField balanceField = new JTextField();
        JButton createBtn = new JButton("Create Account");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(new JLabel("Initial Balance:"));
        panel.add(balanceField);
        panel.add(createBtn);
        add(panel);

        Runnable doCreate = () -> {
            try {
                String name = nameField.getText().trim();
                String pass = new String(passField.getPassword()).trim();
                double bal = Double.parseDouble(balanceField.getText().trim());
                if(name.isEmpty() || pass.isEmpty()) { JOptionPane.showMessageDialog(this,"Fill all fields"); return; }
                Account acc = BankService.createAccount(name, pass, bal);
                JOptionPane.showMessageDialog(this, "Account Created!\nYour Account ID: " + acc.accountId);
                dispose();
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid Data"); }
        };

        
        nameField.addActionListener(e -> passField.requestFocus());
        passField.addActionListener(e -> balanceField.requestFocus());
        balanceField.addActionListener(e -> doCreate.run());
        createBtn.addActionListener(e -> doCreate.run());

        setVisible(true);
    }
}


class Dashboard extends JFrame {
    Dashboard(Account acc) {
        setTitle("Dashboard - " + acc.name);
        setSize(450, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(10,1,10,10));
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton balanceBtn = new JButton("Check Balance");
        JButton transferBtn = new JButton("Fund Transfer");
        JButton loanBtn = new JButton("Apply for Loan");
        JButton detailsBtn = new JButton("Account Details");
        JButton historyBtn = new JButton("Transaction History");
        JButton closeBtn = new JButton("Close Account");
        JButton logoutBtn = new JButton("Logout");

        panel.add(depositBtn); panel.add(withdrawBtn); panel.add(balanceBtn);
        panel.add(transferBtn); panel.add(loanBtn); panel.add(detailsBtn);
        panel.add(historyBtn); panel.add(closeBtn); panel.add(logoutBtn);
        add(panel);

        depositBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(JOptionPane.showInputDialog("Enter amount to deposit"));
                acc.deposit(amt);
                BankService.saveData();
                JOptionPane.showMessageDialog(this,"Deposit Successful! Current Balance: "+acc.balance);
            } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Invalid Amount"); }
        });

        withdrawBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(JOptionPane.showInputDialog("Enter amount to withdraw"));
                if(!acc.withdraw(amt)) JOptionPane.showMessageDialog(this,"Insufficient Balance");
                else JOptionPane.showMessageDialog(this,"Withdraw Successful! Current Balance: "+acc.balance);
                if(acc.balance < 500) JOptionPane.showMessageDialog(this,"⚠ Low Balance Warning!");
                BankService.saveData();
            } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Invalid Amount"); }
        });

        balanceBtn.addActionListener(e -> JOptionPane.showMessageDialog(this,"Current Balance: "+acc.balance));

        transferBtn.addActionListener(e -> {
            try {
                int toId = Integer.parseInt(JOptionPane.showInputDialog("Receiver Account ID"));
                double amt = Double.parseDouble(JOptionPane.showInputDialog("Amount"));
                Account r = BankService.accounts.get(toId);
                if(r != null && acc.balance>=amt) {
                    acc.withdraw(amt); r.deposit(amt);
                    acc.log("Transferred "+amt+" to "+toId);
                    r.log("Received "+amt+" from "+acc.accountId);
                    BankService.saveData();
                    JOptionPane.showMessageDialog(this,"Transfer Successful! Current Balance: "+acc.balance);
                } else JOptionPane.showMessageDialog(this,"Transfer Failed");
            } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Invalid Input"); }
        });

        loanBtn.addActionListener(e -> {
            if(acc.loanRequested) {
                JOptionPane.showMessageDialog(this,"Loan already requested. Waiting for Admin approval.");
            } else {
                try {
                    double amt = Double.parseDouble(JOptionPane.showInputDialog("Loan Amount"));
                    acc.loanAmount = amt; acc.loanRequested = true;
                    acc.log("Loan Requested: "+amt); BankService.saveData();
                    JOptionPane.showMessageDialog(this,"Loan Requested. Admin approval pending.");
                } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Invalid Input"); }
            }
        });

        detailsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "ID: "+acc.accountId+"\nName: "+acc.name+"\nBalance: "+acc.balance+"\nLoan Approved: "+acc.loanApproved));

        historyBtn.addActionListener(e -> JOptionPane.showMessageDialog(this,acc.transactions));

        closeBtn.addActionListener(e -> {
            BankService.accounts.remove(acc.accountId); BankService.saveData();
            JOptionPane.showMessageDialog(this,"Account Closed");
            new LoginFrame(); dispose();
        });

        logoutBtn.addActionListener(e -> { new LoginFrame(); dispose(); });

        setVisible(true);
    }
}


class AdminDashboard extends JFrame {
    AdminDashboard() {
        setTitle("Admin Panel"); setSize(400,450); setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(8,1,10,10));

        JButton viewAccountsBtn = new JButton("View All Accounts");
        JButton unlockBtn = new JButton("Unlock Account");
        JButton freezeBtn = new JButton("Freeze/Unfreeze Account");
        JButton approveLoansBtn = new JButton("Approve Loans");
        JButton logoutBtn = new JButton("Logout");

        panel.add(viewAccountsBtn); panel.add(unlockBtn); panel.add(freezeBtn);
        panel.add(approveLoansBtn); panel.add(logoutBtn); add(panel);

        viewAccountsBtn.addActionListener(e -> {
            BankService.accounts = BankService.loadData();
            StringBuilder sb = new StringBuilder();
            for(Account a : BankService.accounts.values()) {
                sb.append("ID: ").append(a.accountId)
                        .append(" | Name: ").append(a.name)
                        .append(" | Balance: ").append(a.balance)
                        .append(" | Locked: ").append(a.locked)
                        .append(" | Frozen: ").append(a.frozen)
                        .append(" | Loan Requested: ").append(a.loanRequested)
                        .append(" | Loan Approved: ").append(a.loanApproved)
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.length()>0?sb:"No Accounts Found");
        });

        unlockBtn.addActionListener(e -> {
            BankService.accounts = BankService.loadData();
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Account ID to Unlock:"));
            Account a = BankService.accounts.get(id);
            if(a != null && a.locked) { a.locked=false; a.wrongAttempts=0; BankService.saveData(); JOptionPane.showMessageDialog(this,"Account Unlocked"); }
            else JOptionPane.showMessageDialog(this,"Account not found or not locked");
        });

        freezeBtn.addActionListener(e -> {
            BankService.accounts = BankService.loadData();
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Account ID:"));
            Account a = BankService.accounts.get(id);
            if(a != null) { a.frozen=!a.frozen; BankService.saveData(); JOptionPane.showMessageDialog(this,"Account Frozen Status: "+a.frozen);}
            else JOptionPane.showMessageDialog(this,"Account not found");
        });

        approveLoansBtn.addActionListener(e -> {
            BankService.accounts = BankService.loadData();
            StringBuilder pendingLoans = new StringBuilder();

            
            for(Account a : BankService.accounts.values()) {
                if(a.loanRequested && !a.loanApproved) {
                    pendingLoans.append("Account ID: ").append(a.accountId)
                            .append(" | Name: ").append(a.name)
                            .append(" | Requested Loan: ").append(a.loanAmount)
                            .append("\n");
                }
            }

            if(pendingLoans.length() > 0) {
                JOptionPane.showMessageDialog(this, "Pending Loan Requests:\n" + pendingLoans);

                // Step 2: Approve loans
                StringBuilder approvedLoans = new StringBuilder();
                for(Account a : BankService.accounts.values()) {
                    if(a.loanRequested && !a.loanApproved) {
                        a.loanApproved = true;
                        a.balance += a.loanAmount;
                        a.log("Loan Approved: " + a.loanAmount);
                        approvedLoans.append("Account ID: ").append(a.accountId)
                                .append(" | Name: ").append(a.name)
                                .append(" | Loan Amount: ").append(a.loanAmount)
                                .append("\n");
                    }
                }

                BankService.saveData();
                JOptionPane.showMessageDialog(this, "Loans Approved:\n" + approvedLoans);

            } else {
                JOptionPane.showMessageDialog(this, "No pending loans to approve.");
            }
        });

        logoutBtn.addActionListener(e -> { new LoginFrame(); dispose(); });

        setVisible(true);
    }
}


public class BankManagementSystem {
    public static void main(String[] args) {
        new LoginFrame();
    }
}


            a.balance += amount;
            a.Collectkora.add("Deposit: " + amount);
            return true;
           
        }
