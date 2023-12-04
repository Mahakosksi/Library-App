package com.example.libraryproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.example.libraryproject.DTO.PublicationDTO;
import com.example.libraryproject.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication,String> {
	
	Optional<Publication> findById(String id);
	List<Publication> findByIdlabIn(List<Integer> id);
	List<Publication> findByIdlab(Long idlab);
	
	
	@Query(value = "SELECT p.idpub, a.name, p.yearpub " +
            "FROM publication p " +
            "JOIN regularbookauthor r ON r.idpub = p.idpub " +
            "JOIN author a ON r.idauthor = a.idauthor " +
            "WHERE a.name = :authorName AND p.yearpub > :year " +
            "UNION ALL " +
            "SELECT p.idpub, a.name, p.yearpub " +
            "FROM publication p " +
            "JOIN scientificreportauthor s ON s.idpub = p.idpub " +
            "JOIN author a ON s.idauthor = a.idauthor " +
            "WHERE a.name = :authorName AND p.yearpub > :year " +
            "UNION ALL " +
            "SELECT p.idpub, a.name, p.yearpub " +
            "FROM publication p " +
            "JOIN thesis t ON t.idpub = p.idpub " +
            "JOIN author a ON t.idauthor = a.idauthor " +
            "WHERE a.name = :authorName AND p.yearpub > :year", nativeQuery = true)
    List<Object[]> findPublicationsByAuthorAndYear(@Param("authorName") String authorName, @Param("year") int year);
    
    
    @Query("SELECT rb.title, p.yearpub, p.idpub, rb.edition " +
            "FROM RegularBook rb " +
            "JOIN Publication p ON rb.idpub = p.idpub " +
            "WHERE rb.publisher = :publisher " +
            "ORDER BY p.yearpub ASC")
     List<Object[]> findPublicationsByPublisher(@Param("publisher") String publisher);
     
     @Query("SELECT SUM(p.price * p.currentexchange) " +
             "FROM Periodic p " +
             "WHERE p.idpub IN (SELECT idpub FROM Publication WHERE idlab = :labId)")
      Double calculatePeriodicTotalCostByLabId(@Param("labId") Integer labId);

      @Query("SELECT SUM(p.price * p.currentexchange) " +
             "FROM RegularBook p " +
             "WHERE p.idpub IN (SELECT idpub FROM Publication WHERE idlab = :labId)")
      Double calculateRegularBookTotalCostByLabId(@Param("labId") Integer labId);
      
      @Query(value = "SELECT p.* " +
              "FROM publication p " +
              "JOIN bookcategory b ON p.idpub = b.idpub " +
              "JOIN category c ON b.idcategory = c.idcategory " +
              "WHERE c.categoryname = :category " +
              "AND p.idlab = :labId " +
              "AND p.idpub IN ( " +
              "    SELECT rb.idpub " +
              "    FROM regularbook rb " +
              "    WHERE rb.idpub = p.idpub " +
              "    GROUP BY rb.idpub " +
              "    HAVING SUM(rb.price * rb.currentexchange) < :enteredPrice)", nativeQuery = true)
      List<Publication> findPublicationsByLabIdCategoryAndPrice(
              @Param("labId") Long labId,
              @Param("category") String category,
              @Param("enteredPrice") Double enteredPrice);

}

