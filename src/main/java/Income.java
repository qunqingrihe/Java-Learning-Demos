import java.util.ArrayList;
import java.util.List;

class Income {
    private String type;
    private double amount;

    public Income(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class TaxCalculator {
    private List<Income> incomes = new ArrayList<>();

    // 添加收入
    public void addIncome(Income income) {
        incomes.add(income);
    }

    // 计算总税收
    public double totalTax() {
        double totalTax = 0;
        for (Income income : incomes) {
            if ("稿费".equals(income.getType())) {
                totalTax += income.getAmount() * 0.3;
            }
        }
        return totalTax;
    }
    public static void main(String[] args) {
        TaxCalculator taxCalculator = new TaxCalculator();

        // 添加工资
        taxCalculator.addIncome(new Income("工资", 4000));

        // 添加稿费
        taxCalculator.addIncome(new Income("稿费", 2000));

        // 计算总税收
        System.out.println("总税收：" + taxCalculator.totalTax());
    }
}


