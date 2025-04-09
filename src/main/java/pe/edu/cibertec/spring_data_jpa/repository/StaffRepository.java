package pe.edu.cibertec.spring_data_jpa.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.spring_data_jpa.entity.Staff;

public interface StaffRepository extends CrudRepository<Staff, Integer> {
}
