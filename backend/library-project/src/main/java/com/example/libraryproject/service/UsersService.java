package com.example.libraryproject.service;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.example.libraryproject.DTO.PublicationDTO;
import com.example.libraryproject.DTO.RegularBookDTO;
import com.example.libraryproject.model.DbUser;
import com.example.libraryproject.model.Lab;
import com.example.libraryproject.model.Publication;
import com.example.libraryproject.model.RegularBook;
import com.example.libraryproject.model.Users;
import com.example.libraryproject.repository.DbUserRepository;
import com.example.libraryproject.repository.LabRepository;
import com.example.libraryproject.repository.PublicationRepository;
import com.example.libraryproject.repository.RegularBookRepository;
import com.example.libraryproject.repository.UsersRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.hibernate.mapping.Set;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@PersistenceContext
	private EntityManager em;
	
	
	private final DbUserRepository dbUserRepository;
	private final LabRepository labRepository;
	private final RegularBookRepository regularBookRepository;
    public UsersService(UsersRepository usersRepository, DbUserRepository dbUserRepository,
			PublicationRepository publicationRepository, LabRepository labRepository,RegularBookRepository regularBookRepository) {
		super();
		this.usersRepository = usersRepository;
		this.dbUserRepository = dbUserRepository;
		this.labRepository = labRepository;
		this.regularBookRepository = regularBookRepository;
		
		this.publicationRepository = publicationRepository;
	}

	private final PublicationRepository publicationRepository;


	public Users registerUser(String login, String password, String email) {
		if(login != null && password != null && email!= null) {
			Users user = new Users();
			user.setLogin(login);
			user.setPassword(password);
			user.setEmail(email);
			return usersRepository.save(user);
			
		}else {
			return null;
		}
	}
	
	public Users authenticate(String login, String password) {
		return usersRepository.findByLoginAndPassword(login,password).orElse(null);
		
	}
	
	public List<PublicationDTO> getPublications() {
        String sql = "SELECT idpub, yearpub, idstatus FROM publication";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PublicationDTO publication = new PublicationDTO();
            publication.setIdPub(rs.getString("idpub"));
            publication.setYearPub(rs.getInt("yearpub"));
            if(rs.getInt("idstatus") == 1) {
            	publication.setStatus("On rack");
            }
            if(rs.getInt("idstatus") == 2) {
            	publication.setStatus("Issued");
            }
            if(rs.getInt("idstatus") == 3) {
            	publication.setStatus("Lost");
            }
            if(rs.getInt("idstatus") == 4) {
            	publication.setStatus("To be bought");
            }
            return publication;
        });
    }

	public List<PublicationDTO> getAccessiblePublicationDTOs(String userEmail) {
        String sql = "SELECT p.idpub, p.yearpub, p.idstatus FROM publication p " +
                     "WHERE p.idlab IN (SELECT ul.idlab FROM userlab ul WHERE ul.email = ?)";
        
        
        
        return jdbcTemplate.query(sql, new Object[]{userEmail}, (rs, rowNum) -> {
            PublicationDTO publication = new PublicationDTO();
            publication.setIdPub(rs.getString("idpub"));
            publication.setYearPub(rs.getInt("yearpub"));
            if(rs.getInt("idstatus") == 1) {
            	publication.setStatus("On rack");
            }
            if(rs.getInt("idstatus") == 2) {
            	publication.setStatus("Issued");
            }
            if(rs.getInt("idstatus") == 3) {
            	publication.setStatus("Lost");
            }
            if(rs.getInt("idstatus") == 4) {
            	publication.setStatus("To be bought");
            }
            return publication;
        });
    }


	public PublicationDTO convertToDTO(Publication publication) {
		
	    PublicationDTO publicationDTO = new PublicationDTO();
	    
	    publicationDTO.setIdPub(publication.getIdpub());
	    publicationDTO.setYearPub(publication.getYearpub());
	    publicationDTO.setIdStatus(publication.getIdstatus());
	   
	    
	    return publicationDTO;
	}

	
	public List<PublicationDTO> getIssuedPublicationDTOs(String userEmail) {
        String sql = "SELECT p.idpub, p.yearpub, p.idstatus FROM publication p " +
                     "WHERE p.issuedemail=?";
        return jdbcTemplate.query(sql, new Object[]{userEmail}, (rs, rowNum) -> {
            PublicationDTO publication = new PublicationDTO();
            publication.setIdPub(rs.getString("idpub"));
            publication.setYearPub(rs.getInt("yearpub"));
            if(rs.getInt("idstatus") == 1) {
            	publication.setStatus("On rack");
            }
            if(rs.getInt("idstatus") == 2) {
            	publication.setStatus("Issued");
            }
            if(rs.getInt("idstatus") == 3) {
            	publication.setStatus("Lost");
            }
            if(rs.getInt("idstatus") == 4) {
            	publication.setStatus("To be bought");
            }
            return publication;
        });
    }
	
	public Publication updateStatus(String publicationId, String userEmail) {
	    Optional<Publication> optionalPublication = publicationRepository.findById(publicationId);
	    if (optionalPublication.isPresent()) {
	        Publication publication = optionalPublication.get();
	        publication.setIdstatus(2);
	        publication.setIssuedemail(userEmail);
	        return publicationRepository.save(publication);
	    } else {
	        throw new NoSuchElementException("Publication not found with ID: " + publicationId);
	    }
	}
	
	public List<Object[]> getBooksByCategory() {
        String sqlQuery = "SELECT c.categoryname, r.idpub, r.title FROM RegularBook r " +
                          "INNER JOIN r.categories c";
        
        Query query = em.createQuery(sqlQuery);
        return query.getResultList();
    }
	
	public List<PublicationDTO> getPublicationsByLabId(Long labId) {
	    List<PublicationDTO> publications = new ArrayList<>();

	    List<Publication> fetchedPublications = publicationRepository.findByIdlab(labId);

	    for (Publication publication : fetchedPublications) {
	        PublicationDTO publicationDTO = new PublicationDTO();
	        publicationDTO.setIdPub(publication.getIdpub());
	        publicationDTO.setYearPub(publication.getYearpub());

	        switch (publication.getIdstatus()) {
	            case 1:
	                publicationDTO.setStatus("On rack");
	                break;
	            case 2:
	                publicationDTO.setStatus("Issued");
	                break;
	            case 3:
	                publicationDTO.setStatus("Lost");
	                break;
	            case 4:
	                publicationDTO.setStatus("To be bought");
	                break;
	            default:
	                publicationDTO.setStatus("Unknown");
	                break;
	        }

	        publications.add(publicationDTO);
	    }

	    return publications;
	}
	
	public List<PublicationDTO> getIssuedPublications(Long labId) {
	    List<PublicationDTO> publications = new ArrayList<>();
	    List<Publication> fetchedPublications = publicationRepository.findByIdlab(labId);
	    for (Publication publication : fetchedPublications) {
	        PublicationDTO publicationDTO = new PublicationDTO();
	        publicationDTO.setIdPub(publication.getIdpub());
	        publicationDTO.setYearPub(publication.getYearpub());
	        publicationDTO.setIssuedEmail(publication.getIssuedemail());

	        switch (publication.getIdstatus()) {
	            case 1:
	                publicationDTO.setStatus("On rack");
	                break;
	            case 2:
	                publicationDTO.setStatus("Issued");
	                break;
	            case 3:
	                publicationDTO.setStatus("Lost");
	                break;
	            case 4:
	                publicationDTO.setStatus("To be bought");
	                break;
	            default:
	                publicationDTO.setStatus("Unknown");
	                break;
	        }

	        publications.add(publicationDTO);
	    }
	    return publications;
	}
	public List<RegularBookDTO> getRegularBooksByPublicationWithStatus2AndLab(Long labId) {
        List<RegularBookDTO> regularBookDTOs = new ArrayList<>();

        List<RegularBook> fetchedRegularBooks = regularBookRepository.findRegularBooksByLabAndStatus2(labId);

        for (RegularBook regularBook : fetchedRegularBooks) {
            RegularBookDTO regularBookDTO = new RegularBookDTO();
            regularBookDTO.setIdPub(regularBook.getIdpub());
            regularBookDTO.setTitle(regularBook.getTitle());
            regularBookDTO.setPublisher(regularBook.getPublisher());

            regularBookDTOs.add(regularBookDTO);
        }

        return regularBookDTOs;
    }
	
	
	public List<Object[]> findPublicationsByAuthorAndYear(String authorName, int year) {
        return publicationRepository.findPublicationsByAuthorAndYear(authorName, year);
    }
	
	public List<Object[]> findPublicationsByPublisher(String publisherName) {
        return publicationRepository.findPublicationsByPublisher(publisherName);
    } 
	
	
	
	public Double calculateTotalPublicationCostByLabId(Integer labId) {
        try {
            Double periodicCost = publicationRepository.calculatePeriodicTotalCostByLabId(labId);
            Double regularBookCost = publicationRepository.calculateRegularBookTotalCostByLabId(labId);
            return (periodicCost != null ? periodicCost : 0.0)+ (regularBookCost != null ? regularBookCost : 0.0);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate total publication cost.", e);
        }
    }
	public List<PublicationDTO> findPublicationsByLabIdCategoryAndPrice(Long labId, String category, Double enteredPrice) {
        
        List<PublicationDTO> publications = new ArrayList<>();
	    List<Publication> fetchedPublications = publicationRepository.findPublicationsByLabIdCategoryAndPrice(labId,category,enteredPrice);;
	    for (Publication publication : fetchedPublications) {
	        PublicationDTO publicationDTO = new PublicationDTO();
	        publicationDTO.setIdPub(publication.getIdpub());
	        publicationDTO.setYearPub(publication.getYearpub());
	        publicationDTO.setIssuedEmail(publication.getIssuedemail());
	        publicationDTO.setDateProposition(publication.getDateproposition());

	        switch (publication.getIdstatus()) {
	            case 1:
	                publicationDTO.setStatus("On rack");
	                break;
	            case 2:
	                publicationDTO.setStatus("Issued");
	                break;
	            case 3:
	                publicationDTO.setStatus("Lost");
	                break;
	            case 4:
	                publicationDTO.setStatus("To be bought");
	                break;
	            default:
	                publicationDTO.setStatus("Unknown");
	                break;
	        }

	        publications.add(publicationDTO);
	    }
	    return publications;
    }
	
	






}
