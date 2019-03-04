package usmb.nc.cgi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindingCustomerById_thenCorrect() {
        customerRepository.save(new Customer("John", "john@domain.com"));
        Optional<Customer> res = customerRepository.findById(1L);
        assert res.isPresent();
    }

//    @Test
//    public void whenFindingAllCustomers_thenCorrect() {
//        customerRepository.save(new Customer("John", "john@domain.com"));
//        customerRepository.save(new Customer("Julie", "julie@domain.com"));
//        assertThat(customerRepository.findAll()).isInstanceOf(List.class);
//    }
//
//    @Test
//    public void whenSavingCustomer_thenCorrect() {
//        customerRepository.save(new Customer("Bob", "bob@domain.com"));
//        Customer customer = customerRepository.findById(1L).orElseGet(()
//                -> new Customer("john", "john@domain.com"));
//        assertThat(customer.getName()).isEqualTo("Bob");
//    }
}