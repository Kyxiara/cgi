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
        int nb_forms = ((ArrayList<Form>)formRepository.findAll()).size();
        Form f1 = new Form();
        Form f2 = new Form();
        formRepository.save(f1);
        formRepository.save(f2);
        ArrayList<Form> res = (ArrayList<Form>)formRepository.findAll();
        assert res.size() == 2 + nb_forms;
    }

    @Test
    public void whenSavingForm_thenCorrect() {
        Form f = new Form();
        String firstNameTest = "Toto";
        f.setFirst_name(firstNameTest);
        formRepository.save(f);
        ArrayList<Form> res = (ArrayList<Form>)formRepository.findAll();

        boolean found = false;
        for(Form aForm : res){
            String firstName = aForm.getFirst_name();
            if(firstName != null && firstName.equals(firstNameTest))
                found = true;
        }
        assert found;
    }
}