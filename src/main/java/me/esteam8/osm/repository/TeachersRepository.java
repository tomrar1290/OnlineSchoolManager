package me.esteam8.osm.repository;

import lombok.extern.slf4j.Slf4j;
import me.esteam8.osm.model.Teacher;

import java.util.HashMap;

@Slf4j
public class TeachersRepository extends BaseRepository<Teacher, TeacherDAO> {

    public TeachersRepository() {
        super(new HashMap<>(), new TeacherDAO());
    }

    public void saveElementAt(String firstname, String lastname, String email, int index) {
        getElementAt(index).setFirstname(firstname);
        getElementAt(index).setLastname(lastname);
        getElementAt(index).setEmail(email);

        modelDAO.save(getElementAt(index));
    }
}
