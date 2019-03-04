package usmb.nc.cgi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormRepositoryTest {

    @Autowired
    private FormRepository formRepository;

    @Test
    public void whenFindingFormById_thenCorrect() {
        formRepository.save(new Form());
        Optional<Form> res = formRepository.findById(1L);
        assert res.isPresent();
    }

    @Test
    public void whenFindingAllForms_thenCorrect() {
        Form f1 = new Form();
        Form f2 = new Form();
        formRepository.save(f1);
        formRepository.save(f2);
        ArrayList<Form> res = (ArrayList<Form>)formRepository.findAll();
        assert res.size() == 2;
    }

//    @Test
//    public void whenSavingForm_thenCorrect() {
//        customerRepository.save(new Customer("Bob", "bob@domain.com"));
//        Customer customer = customerRepository.findById(1L).orElseGet(()
//                -> new Customer("john", "john@domain.com"));
//        assertThat(customer.getName()).isEqualTo("Bob");
//    }
}