import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // A list of text
        ArrayList<String> names = new ArrayList<>();
        names.add("Ana");
        names.add("Ben");
        names.add("Cara");
        names.add("Will");
        
        for(int i=1; i<=names.size(); i++) {
            System.out.println(i+": "+names.get(i));
        }
        System.out.println(names);          // the whole list
        System.out.println(names.size());   // how many items
        System.out.println(names.get(0));   // item at index 0

        names.remove("Ben");
        System.out.println(names);

        // A list of objects
        ArrayList<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount("Ana", 100));
        accounts.add(new SavingsAccount("Cara", 200, 5));
        accounts.add(new BankAccount("Ben", 50));
        accounts.add(new BankAccount("Will", 150));
        

        int total = 0;
        for (BankAccount acc : accounts) {
            acc.printBalance();
            total = total + acc.getBalance();
        }
        System.out.println("Total: " + total);
    }
}

class BankAccount {
    String owner;
    int balance;

    BankAccount(String owner, int balance) {
        this.owner = owner;
        this.balance = balance;
    }

    void deposit(int amount) {
        balance = balance + amount;
    }

    boolean withdraw(int amount) {
        if(amount<balance) {
            balance = balance - amount;
            return true;
        }
        else {
            System.out.println("Insufficient funds");
            return false;
        }
    }

    void printBalance() {
        System.out.println(owner + ": " + balance);
    }
    
    int getBalance() {
        return balance;
    }
    
    String getName() {
        return owner;
    }
    
    void transfer(BankAccount other, int amount) {
        this.withdraw(amount);
        other.deposit(amount);
        System.out.println("Transferred "+amount+" from "+this.getName()+"'s account to "+other.getName()+"'s account");
    }
    
    static BankAccount richest(ArrayList<BankAccount> list) {
        if (list.isEmpty()) {
            return null;   // null means "no object"
        }       
        BankAccount highest = list.get(0);
        for(int i=1; i<list.size(); i++) {
            if(list.get(i).getBalance() > highest.getBalance()) {
                highest = list.get(i);
            }
        }
        return highest;
    }
}

class SavingsAccount extends BankAccount {
    int ratePercent;

    SavingsAccount(String owner, int balance, int ratePercent) {
        super(owner, balance);
        this.ratePercent = ratePercent;
    }

    void addInterest() {
        balance = balance + balance * ratePercent / 100;
    }

    @Override
    void printBalance() {
        super.printBalance();
        System.out.println("Rate: " + ratePercent + "%");
    }
    
    boolean changeRate(int newRate) {
        if(newRate>=0) {
            ratePercent=newRate;
            return true;
        }
        else{
            System.out.println("New rate cannot be negative.");
            return false;
        }
    }
}

