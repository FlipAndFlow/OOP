package main.java.billing;

import java.util.ArrayList;
import java.util.List;

public class BillHistory {
    private List<Bill> bills;

    public BillHistory() {
        bills = new ArrayList<>();
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public List<Bill> getBills() {
        return bills;
    }

    public String getHistorySummary() {
        StringBuilder historySummary = new StringBuilder();
        for (Bill bill : bills) {
            historySummary.append(bill.getBillSummary()).append("\n\n");
        }
        return historySummary.toString();
    }
}
