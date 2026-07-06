Assignment Solution: Difference between JPA, Hibernate and Spring Data JPA
Difference between JPA, Hibernate and Spring Data JPA
1. Java Persistence API (JPA)

Definition:
Java Persistence API (JPA) is a Java specification (JSR 338) that defines a standard way to persist, retrieve, and manage data between Java objects and relational databases.

Key Points:

It is only a specification, not an implementation.
Defines standard APIs such as EntityManager, EntityTransaction, and annotations like @Entity, @Id, and @Table.
Requires an implementation (such as Hibernate) to perform actual database operations.
Makes applications independent of a specific ORM provider.

Advantages:

Standard API supported by multiple ORM frameworks.
Database-independent.
Easy to switch between JPA providers.
2. Hibernate

Definition:
Hibernate is a popular Object Relational Mapping (ORM) framework that implements the JPA specification.

Key Points:

Performs the actual mapping between Java objects and database tables.
Automatically generates SQL queries.
Supports advanced features such as:
Caching
Lazy Loading
Dirty Checking
Batch Processing
HQL (Hibernate Query Language)
Requires manual session and transaction management when used without Spring.

Example (Hibernate)

public Integer addEmployee(Employee employee) {

    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;

    try {
        tx = session.beginTransaction();

        employeeID = (Integer) session.save(employee);

        tx.commit();

    } catch (HibernateException e) {

        if (tx != null)
            tx.rollback();

        e.printStackTrace();

    } finally {

        session.close();

    }

    return employeeID;
}
3. Spring Data JPA

Definition:
Spring Data JPA is a Spring framework module built on top of JPA that simplifies database access by reducing boilerplate code.

Key Points:

Does not implement JPA.
Uses a JPA implementation (typically Hibernate) internally.
Provides repository interfaces like JpaRepository.
Automatically implements CRUD operations.
Supports automatic query generation through method names.
Integrates seamlessly with Spring's transaction management.

Repository Interface

public interface EmployeeRepository
extends JpaRepository<Employee, Integer> {

}

Service Class

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void addEmployee(Employee employee) {

        employeeRepository.save(employee);

    }
}