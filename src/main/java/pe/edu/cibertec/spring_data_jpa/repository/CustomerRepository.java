package pe.edu.cibertec.spring_data_jpa.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.spring_data_jpa.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
