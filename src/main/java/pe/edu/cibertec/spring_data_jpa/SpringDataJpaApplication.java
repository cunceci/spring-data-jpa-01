package pe.edu.cibertec.spring_data_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.edu.cibertec.spring_data_jpa.entity.Customer;
import pe.edu.cibertec.spring_data_jpa.entity.Film;
import pe.edu.cibertec.spring_data_jpa.repository.CustomerRepository;
import pe.edu.cibertec.spring_data_jpa.repository.FilmRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringDataJpaApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	FilmRepository filmRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}



    public void run(String... args) throws Exception {

		/**
		 * findAll()
		 */
		//List<Customer> customers = (List<Customer>) customerRepository.findAll();
		//customers.forEach(System.out::println);

		/**
		 * entity
		 */
			//Customer customer = new Customer(null, 1, "César", "Santos", "cesar@mail.com", 1, 1, new Date(), new Date());
			//customerRepository.save(customer);


		/**
		 *
		 * findById() - get() //No recomendado
		 */

		//Optional<Customer> optional = customerRepository.findById(600);
		//Customer customer = optional.get();
		//System.out.println(customer);

		/**
		 *
		 * findById() - orElse()
		 */

		//Optional<Customer> optional = customerRepository.findById(601);
		//Customer customer = optional.orElse(null);
		//System.out.println(customer);

		/**
		 *
		 * findById() - orElseGet()
		 */

//		Optional<Customer> optional = customerRepository.findById(601);
//		Customer customer = optional.orElseGet(() -> {
//			LocalDate today = LocalDate.now();
//			System.out.println("Customer not found -> " + today);
//			return new Customer();
//		});
//		System.out.println(customer);

		/**
		 *
		 * findById() - orElseThrow()
		 */
		//Optional<Customer> optional = customerRepository.findById(601);
		//Customer customer = optional.orElseThrow(IllegalArgumentException::new);
		//System.out.println(customer);

		/**
		 *
		 * findById() - isPresent()
		 */
		//Optional<Customer> optional = customerRepository.findById(601);
		//if (optional.isPresent()) {
		//	Customer customer = optional.get();
		//} else {
		//	System.out.println("Customer not found");

		//}

		/**
		 *
		 * findById() - ifPresent()
		 */
		//Optional<Customer> optional = customerRepository.findById(605);
//        optional.ifPresent((Customer cus) -> {
//            LocalDate today = LocalDate.now();
//            System.out.println("Customer not found" + today);
//        });

		/**
		 *
		 * findById() - ifPresentOrElse()


		Optional<Customer> optional = customerRepository.findById(601);
        optional.ifPresentOrElse(
       			(Customer item) -> {
					System.out.println(item);
        		},
				() -> {
					System.out.println("Customer not found");
				}
        );
		 */

		/**
		 * existsById

		if(customerRepository.existsById(600)){
			System.out.println("Customer with id 600 already exists");
		}else{
			System.out.println("Customer not found");
		}
		 */

		/**
		 * findAll
		 */
		//Iterable<Customer> iterable = customerRepository.findAll();

		// Conversión clasica
		//List<Customer> listElements = (List<Customer>) iterable;

		// Otras conversiones
		//List<Customer> listElements2 = List.copyOf((Collection<Customer>) iterable);

		// clasica
		//for (Customer customer : iterable) {
		//	System.out.println(customer);
		//}

		// forEach
		//iterable.forEach(System.out::println);

		/**
		 * findAllById
		 */
		//Iterable<Integer> ids = List.of(1000, 20000, 30000, 400000, 5645642);
		//Iterable<Customer> iterable = customerRepository.findAllById(ids);
		//iterable.forEach(System.out::println);

		/**
		 * deleteById

		Integer id = 60;
		if(customerRepository.existsById(id)){
			customerRepository.deleteById(id);
		}else{
			System.out.println("Customer with id " + id + " does not exist");
		}
		 */

		/**
		 * deleteAllById

		//List<Integer> ids = List.of(600,601,602,603);
		//ids.stream().filter(id -> id % 2 == 0).forEach(id -> {
		//ids.stream().filter((id) -> {return customerRepository.existsById(id); }).forEach(id -> {
		//ids.stream().filter(id -> customerRepository.existsById(id)).collect(Collectors.toList());
		//ids.stream().filter(id -> customerRepository.existsById(id)).collect(Collectors.toList());
		//ids.stream().filter(id -> customerRepository.existsById(id)).toList();

		//if(!ids.isEmpty()){
		//if(ids.size() > 0){
				customerRepository.deleteAllById(ids);
			}else{
				System.out.println("No customers found");
			}
		}
		 */

		/**
		 * deleteAllById

		List<Integer> ids = List.of(600, 601, 602, 603);

		List<Integer> existingIds = ids.stream()
				.filter(id -> customerRepository.existsById(id))
				.collect(Collectors.toList());

		if (!existingIds.isEmpty()) {
			customerRepository.deleteAllById(existingIds);
		} else {
			System.out.println("No customers found");
		}
		 */

		/**
		 * delete

		Optional<Customer> optional = customerRepository.findById(604);
		optional.ifPresentOrElse(
				(cus) -> {
					customerRepository.delete(cus);
				},
				() -> {
					System.out.println("Customer not found");
				}
		);
		 */

		/**
		 * deleteAll
		 */
		Iterable<Customer> customers = customerRepository.findAllById(List.of(605, 606));
		customerRepository.deleteAll(customers);



		/**
		 * findAll - Caching


		System.out.println("----------------------------------------------------");
		System.out.println("findAll -> Primera llamada a MySQL");
		System.out.println("----------------------------------------------------");
		Iterable<Film> iterable = filmRepository.findAll();
		iterable.forEach((film) ->  {
			//String cadena = film.getTitle() + " " + film.getDescription();
			String message = String.format("%s:%s;", film.getFilm_id(), film.getTitle() );
			System.out.println(message);
		});

		System.out.println(" ");
		System.out.println("------------- ---------------------------------------");
		System.out.println("findAll -> Segunda llamada a MySQL");
		System.out.println("----------------------------------------------------");
		Iterable<Film> iterable2 = filmRepository.findAll();
		iterable.forEach((film) ->  {
			//String cadena = film.getTitle() + " " + film.getDescription();
			String message = String.format("%s:%s;", film.getFilm_id(), film.getTitle() );
			System.out.println(message);
		});

		System.out.println(" ");
		System.out.println("------------------------------------------------------");
		System.out.println("save() -> Update del Film");
		System.out.println("------------------------------------------------------");
		Optional<Film> optional = filmRepository.findById(1);
		optional.ifPresentOrElse(
				(item) -> {
					item.setTitle("JURASSIC PARM");
					filmRepository.save(item);
				},
				() -> {
					System.out.println("Film not found");
				}
		);

		System.out.println(" ");
		System.out.println("------------- ---------------------------------------");
		System.out.println("findAll -> Tercera llamada a MySQL");
		System.out.println("----------------------------------------------------");
		Iterable<Film> iterable3 = filmRepository.findAll();
		iterable3.forEach((film) ->  {
			//String cadena = film.getTitle() + " " + film.getDescription();
			String message = String.format("%s:%s;", film.getFilm_id(), film.getTitle() );
			System.out.println(message);
		});
		 */

	}
}
