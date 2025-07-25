public class Loan {
    private double annualInterestRate;
    private int    numberOfYears;
    private double loanAmount;

    public Loan() {
        this(2.5, 1, 1000);
    }

    public Loan(double annualInterestRate, int numberOfYears, double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears      = numberOfYears;
        this.loanAmount         = loanAmount;
    }

    public double getAnnualInterestRate() { return annualInterestRate; }
    public int    getNumberOfYears()      { return numberOfYears; }
    public double getLoanAmount()         { return loanAmount; }

    public void setAnnualInterestRate(double rate) { this.annualInterestRate = rate; }
    public void setNumberOfYears(int years)        { this.numberOfYears = years; }
    public void setLoanAmount(double amount)       { this.loanAmount = amount; }

    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        double termInMonths        = numberOfYears * 12;
        return loanAmount * monthlyInterestRate /
               (1 - Math.pow(1 + monthlyInterestRate, -termInMonths));
    }

    public double getTotalPayment() {
        return getMonthlyPayment() * numberOfYears * 12;
    }
}