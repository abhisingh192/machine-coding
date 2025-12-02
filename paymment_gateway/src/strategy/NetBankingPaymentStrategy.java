package strategy;

public class NetBankingPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean pay(double amount) {
        return getTransactionStatus();
    }
}
