
import com.microsoft.bankaccount.BankAccount;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BankAccountTest {
    private BankAccount account;

    @BeforeMethod
    public void setUp() {
        account = new BankAccount(100.0, 50.0);
    }

    @Test
    public void testInitialBalance() {
        Assert.assertEquals(account.getBalance(), 100.0, "Initial balance should be 100.0");
    }

    @Test
    public void testDeposit() {
        account.deposit(50.0);
        Assert.assertEquals(account.getBalance(), 150.0, "Balance after deposit should be 150.0");
    }

    @Test
    public void testWithdraw() {
        boolean success = account.withdraw(30.0);
        Assert.assertTrue(success, "Withdrawal should be successful");
        Assert.assertEquals(account.getBalance(), 70.0, "Balance after withdrawal should be 70.0");
    }

    @Test
    public void testWithdrawMoreThanBalance() {
        boolean success = account.withdraw(160.0); // Adjust to test beyond overdraft
        Assert.assertFalse(success, "Withdrawal should fail when exceeding overdraft limit");
        Assert.assertEquals(account.getBalance(), 100.0, "Balance should remain 100.0 after failed withdrawal");
    }

    @Test
    public void testOverdraft() {
        boolean success = account.withdraw(130.0);
        Assert.assertTrue(success, "Withdrawal within overdraft limit should be successful");
        Assert.assertEquals(account.getBalance(), -30.0, "Balance should be -30.0 after overdraft withdrawal");
    }

    @Test
    public void testWithdrawExceedingOverdraft() {
        boolean success = account.withdraw(160.0); // Withdraw more than balance and overdraft
        Assert.assertFalse(success, "Withdrawal exceeding overdraft limit should fail");
        Assert.assertEquals(account.getBalance(), 100.0, "Balance should remain 100.0 after failed overdraft withdrawal");
    }

    @Test
    public void testAddInterest() {
        account.addInterest(10.0);
        Assert.assertEquals(account.getBalance(), 110.0, "Balance after adding interest should be 110.0");
    }

    @Test
    public void testTransactionHistory() {
        account.deposit(50.0);
        account.withdraw(30.0);
        account.addInterest(10.0);
        List<String> history = account.getTransactionHistory();
        Assert.assertEquals(history.size(), 4, "Transaction history should have 4 entries");
        Assert.assertTrue(history.get(1).contains("Deposited: 50.0"), "Second entry should be deposit");
        Assert.assertTrue(history.get(2).contains("Withdrew: 30.0"), "Third entry should be withdrawal");
        Assert.assertTrue(history.get(3).contains("Interest added"), "Fourth entry should be interest");
    }
}
