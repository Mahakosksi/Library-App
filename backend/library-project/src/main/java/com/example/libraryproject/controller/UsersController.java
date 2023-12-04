package com.example.libraryproject.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;




import org.springframework.web.bind.annotation.GetMapping;

import com.example.libraryproject.DTO.PublicationDTO;
import com.example.libraryproject.DTO.RegularBookDTO;
import com.example.libraryproject.DTO.UserDTO;
import com.example.libraryproject.model.Publication;
import com.example.libraryproject.model.Users;
import com.example.libraryproject.repository.UsersRepository;
import com.example.libraryproject.service.UsersService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UsersController {

    @Autowired
    private UsersService usersService;
    
    @Autowired
	private UsersRepository usersRepository;
	

    public UsersController(UsersService usersService, UsersRepository usersRepository) {
		super();
		this.usersService = usersService;
		this.usersRepository = usersRepository;
	}

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody UserDTO userDTO) {
    	
        String login = userDTO.getLogin();
        String password = userDTO.getPassword();
        String email = userDTO.getEmail();

        if (login != null && !login.isEmpty() && password != null && !password.isEmpty() && email != null && !email.isEmpty()) {
            Users user = new Users();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            return new ResponseEntity<>(usersRepository.save(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<UserDTO> authenticateUser(@RequestBody UserDTO userDTO) {
    	Users user = usersService.authenticate(userDTO.getLogin(), userDTO.getPassword());
    	
    	if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }else {
        	userDTO.setEmail(user.getEmail());
        	if (determineUserRole(user.getEmail()).equalsIgnoreCase("admin")) {
                userDTO.setRole("admin");
            } else {
            	if(determineUserRole(user.getEmail()).equalsIgnoreCase("lab")) {
            		userDTO.setRole("lab");
            	}else {
            		userDTO.setRole("user");
            	}
                
            }
            return ResponseEntity.ok(userDTO);
        	
        }
    	
    }
    private String determineUserRole(String email) {
        
        if (email.toLowerCase().contains("@admin")) {
            return "admin";
        } else {
        	if(email.toLowerCase().contains("@lab")) {
        		return "lab";
        	}else {
        		return "user";
        	}
            
        }
    }
    
    @GetMapping("/publications")
    public List<PublicationDTO> getPublications() {
        return usersService.getPublications();
    }
    
    @GetMapping("/accessibles-publications/{email}")
    public  List<PublicationDTO> getAccessiblePublications(@PathVariable String email) {
        return usersService.getAccessiblePublicationDTOs(email);
        
    }
    @GetMapping("/issued-publications/{email}")
    public  List<PublicationDTO> getissuedPublications(@PathVariable String email) {
        return usersService.getIssuedPublicationDTOs(email);
        
    }
    
    @PutMapping("/publications/{id}")
    public ResponseEntity<Publication> updatePublicationStatus(
        @PathVariable("id") String publicationId,
        @RequestParam("userEmail") String userEmail
    ) {
        try {
            Publication updatedPublication = usersService.updateStatus(publicationId, userEmail);
            return ResponseEntity.ok(updatedPublication);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/books-by-category")
    public List<Object[]> getBooksByCategory() {
        return usersService.getBooksByCategory();
    }
    
    @GetMapping("/lab/{labId}/accessible-publications")
    public ResponseEntity<List<PublicationDTO>> getAccessiblePublicationsByLabId(@PathVariable Long labId) {
        List<PublicationDTO> publicationDTOs = usersService.getPublicationsByLabId(labId);
        return ResponseEntity.ok(publicationDTOs);
    }
    @GetMapping("/lab/{labId}/issued-publications")
    public ResponseEntity<List<PublicationDTO>> getIssuedPublicationsByLabId(@PathVariable Long labId) {
        List<PublicationDTO> publicationDTOs = usersService.getIssuedPublications(labId);
        return ResponseEntity.ok(publicationDTOs);
    }
    
    @GetMapping("/lab/{labId}/lost-books")
    public ResponseEntity<List<RegularBookDTO>> getLostBooksByLabId(@PathVariable Long labId) {
        List<RegularBookDTO> lostBooksForLab = usersService.getRegularBooksByPublicationWithStatus2AndLab(labId);
        return ResponseEntity.ok(lostBooksForLab);
    }
    
    
    @GetMapping("/search")
    public ResponseEntity<List<Object[]>> getPublicationsByAuthorAndYear(
            @RequestParam("author") String authorName,
            @RequestParam("year") int year) {

        List<Object[]> publications = usersService.findPublicationsByAuthorAndYear(authorName, year);

        if (publications.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(publications);
        }
    }
    
    @GetMapping("/by-publisher")
    public ResponseEntity<List<Object[]>> getPublicationsByPublisher(
    		@RequestParam("publisher") String publisher) {
        List<Object[]> publications = usersService.findPublicationsByPublisher(publisher);
        if (publications.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(publications);
        }
    }
    
   @GetMapping("/total-price")
   public ResponseEntity<Double> getTotalPublicationCost(
		   @RequestParam("labId") Integer labId) {
        try {
            Double totalCost = usersService.calculateTotalPublicationCostByLabId(labId);
            return ResponseEntity.ok(totalCost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
   @GetMapping("/publications-category")
   public List<PublicationDTO> getPublicationsByCategoryAndPrice(
           @RequestParam("labId") Long labId,
           @RequestParam("category") String category,
           @RequestParam("enteredPrice") Double enteredPrice) {
        List<PublicationDTO> publications = usersService.findPublicationsByLabIdCategoryAndPrice(labId, category, enteredPrice);
        return publications;
   }
    





    
    
    
}