import com.anthonyzhu.project0.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCases {

    @Test
    public void nameTest() {
        Customer c = new Customer();

        c.setFirstName("Louis");
        c.setLastName("Pasteur");

        String expectedResult = "Louis";

        assertEquals(expectedResult, c.getFirstName());

    }

    @Test
    public void depositTest() throws SQLException {

        CustomerDAO custDAO = CustomerDAOFactory.getCustomerDao();
        List<Customer> customerList = custDAO.getCustomers();

        double expectedResult = custDAO.getCurrentBalance(1) + 50;

        custDAO.deposit(1, 50);

        assertEquals(expectedResult, custDAO.getCurrentBalance(1) );

    }

    @Test
    public void withdrawTest() throws SQLException {

        CustomerDAO custDao = CustomerDAOFactory.getCustomerDao();
        List<Customer> customerList = custDao.getCustomers();

        double expectedResult = custDao.getCurrentBalance(1) - 50;

        custDao.withdraw(1, 50);

        assertEquals(expectedResult, custDao.getCurrentBalance(1));

    }

}
