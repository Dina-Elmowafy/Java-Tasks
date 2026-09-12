package Api.repo;

import Api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    @Query("select e from Employee e where e.name like concat(:name, '%')")
    List<Employee> searchByName(@Param("name")String name);
    @Query(value = "select * from employee where name like concat(:name, '%')", nativeQuery = true)
    List<Employee> searchByNameNative(@Param("name") String name);
}
