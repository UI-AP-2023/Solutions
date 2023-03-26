public class Main {
    public static void main(String[] args) {
        //Account Type : 1)SavingAcc  2)SalaryAcc  3)NRI
        Account account1 = new Account("Ftm", 124, "Tokyo", 11, 5000, TypeOfAccount.valueOf("SavingAcc"));
        Account account2 = new Account("Ala", 125, "Isfahan", 15, TypeOfAccount.SalaryAcc);
        Account account3 = new Account("Asal", 126, 16, 70000, TypeOfAccount.NRI);
        //--------------------------------------------------------------------------------------------------------------
        account1.checkAccountBalance();
        System.out.println(account1.typeOfAccount);
        account1.withdraw(1000);
        account1.printInfo();
        account1.deposit(1000);
        account1.printInfo();
        account1.profitCalculation(5);
        account1.profitCalculation();
        account1.loanRequest("Ftm", 124, 400);
        System.out.println();
        //--------------------------------------------------------------------------------------------------------------
        account2.checkAccountBalance();
        account2.withdraw(3000);
        account2.printInfo();
        account2.deposit(300);
        account2.printInfo();
        account2.profitCalculation(10);
        account2.profitCalculation();
        account2.loanRequest("Ala", 125, 600);
        System.out.println();
        //--------------------------------------------------------------------------------------------------------------
        account3.checkAccountBalance();
        account3.withdraw(10000);
        account3.printInfo();
        account3.deposit(1000);
        account3.printInfo();
        account3.profitCalculation(2);
        account3.profitCalculation();
        account3.loanRequest("Asal", 126, 3000);
    }
}

enum TypeOfAccount {
    SavingAcc, SalaryAcc, NRI
}

class Account {
    String nameOfUser;
    int ID;
    String nameOfCity;
    int bankBranch;
    float credit;
    TypeOfAccount typeOfAccount;

    //------------------------------------------------------------------------------------------------------------------
    Account(String nameOfUser, int ID, String nameOfCity, int bankBranch, float credit, TypeOfAccount typeOfAccount) {
        this.nameOfUser = nameOfUser;
        this.ID = ID;
        this.nameOfCity = nameOfCity;
        this.bankBranch = bankBranch;
        this.typeOfAccount = typeOfAccount;
        this.credit = credit;
    }

    //------------------------------------------------------------------------------------------------------------------
    Account(String nameOfUser, int ID, String nameOfCity, int bankBranch, TypeOfAccount typeOfAccount) {
        this(nameOfUser, ID, nameOfCity, bankBranch, 2000, typeOfAccount);
    }

    //------------------------------------------------------------------------------------------------------------------
    Account(String nameOfUser, int ID, int bankBranch, float credit, TypeOfAccount typeOfAccount) {
        this(nameOfUser, ID, "NewYork", bankBranch, credit, typeOfAccount);
    }

    //------------------------------------------------------------------------------------------------------------------
    void checkAccountBalance() {
        switch (this.typeOfAccount) {
            case SavingAcc:
                if (this.credit >= 1) {
                    this.credit -= 1;
                    System.out.printf("Your bank account balance: %f\n", this.credit);
                } else {
                    System.out.println("You are very poor :(");
                }
                break;
            case SalaryAcc:
            case NRI:
                if (this.credit >= 2) {
                    this.credit -= 2;
                    System.out.printf("Your bank account balance: %f\n", this.credit);
                } else {
                    System.out.println("You are very poor :(");
                }
                break;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    void withdraw(float withdrawalAmount) {
        if (withdrawalAmount <= 10000 && this.credit >= withdrawalAmount) {
            this.credit = this.credit - withdrawalAmount;
            System.out.println("The transaction was completed successfully.");
        } else if (withdrawalAmount > 10000) {
            System.out.println("The withdrawal limit is $10000.");
        } else if (this.credit < withdrawalAmount) {
            System.out.println("You don't have enough money in your bank account.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    void deposit(float depositAmount) {
        if (depositAmount <= 1000) {
            this.credit = this.credit + depositAmount;
            System.out.println("The operation was done successfully.");
        } else {
            this.credit = (float) (99 / 100) * this.credit;
            System.out.println("The deposit limit is $1000.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    void profitCalculation(float percentOfProfit) {
        if (percentOfProfit >= 1f && percentOfProfit <= 10f) {
            if (this.typeOfAccount == TypeOfAccount.SavingAcc) {
                System.out.println(((this.credit * 3) / 2) * (percentOfProfit / 100));
            } else {
                System.out.println(((this.credit * 11) / 10) * (percentOfProfit / 100));
            }
        } else System.out.println("The Entered number is not in the range of 1 to 10");
    }

    //------------------------------------------------------------------------------------------------------------------
    void profitCalculation() {
        if (this.typeOfAccount == TypeOfAccount.SavingAcc) {
            System.out.println(((this.credit * 10) / 100));
        } else {
            System.out.println(((this.credit * 2) / 100));
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    void loanRequest(String nameOfUser, int ID, float loanMount) {
        if (nameOfUser == this.nameOfUser && ID == this.ID) {
            if (this.credit >= loanMount / 2) {
                if ((this.typeOfAccount == TypeOfAccount.SavingAcc || this.typeOfAccount == TypeOfAccount.SalaryAcc) && loanMount <= 500) {
                    System.out.println("Your loan application was successful.");
                } else if (this.typeOfAccount == TypeOfAccount.NRI) {
                    System.out.println("Your loan application was successful.");
                }
            } else {
                System.out.println("Your credit is not enough for this loan mount ! ");
            }
        } else {
            System.out.println("You have entered your information incorrectly ! ");
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    void printInfo() {
        System.out.println(this.credit);
    }
}