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
