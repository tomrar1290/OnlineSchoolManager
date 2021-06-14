package pl.tomaszrarok.osm.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Course;
import pl.tomaszrarok.osm.model.Student;

@Slf4j
public class StudentsRepository extends BaseRepository<Student, StudentDAO>{

    public StudentsRepository() {
        super(new HashMap<>(), new StudentDAO());
    }


    public void saveElementAt(String firstname, String lastname, String email, int index) {
        getElementAt(index).setFirstname(firstname);
        getElementAt(index).setLastname(lastname);
        getElementAt(index).setEmail(email);

        modelDAO.save(getElementAt(index));
    }
}
