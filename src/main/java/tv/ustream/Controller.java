package tv.ustream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class Controller {

    private static final String URL = "http://127.0.0.1:8080/";

    private RestTemplate restTemplate = new RestTemplate();

    private InputValidator inputValidator = new InputValidator();

    public void add(String[] args) throws Exception {
        inputValidator.validateAdd(args);
        Repository repository = new RepositoryBuilder().withName(args[1]).withCreator(args[2]).build();
        try {
            ResponseEntity<Void> responseEntity = restTemplate.postForEntity(URL, repository, Void.class);
            if (HttpStatus.CREATED.equals(responseEntity.getStatusCode())) {
                System.out.println("Repository " + args[1] + " successfully addedd by " + args[2] + ".");
            }
        } catch (HttpClientErrorException e) {
            if (HttpStatus.CONFLICT.equals(e.getStatusCode())) {
                System.out.println("Repository name '" + args[1] + "' already in use.");
            } else {
                System.out.println("Unexpected error. Code: " + e.getStatusCode());
            }
        }
    }

    public void getByName(String[] args) throws Exception {
        inputValidator.validateGet(args);
        try {
            ResponseEntity<Repository> responseEntity = restTemplate.getForEntity(URL + "?name={name}", Repository.class, args[1]);
            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                System.out.println(responseEntity.getBody());
            }
        } catch (HttpClientErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                System.out.println("Repository with name '" + args[1] + "' not found.");
            } else {
                System.out.println("Unexpected error. Code: " + e.getStatusCode());
            }
        }
    }

    public void getByAccessCount(String[] args) throws Exception {
        inputValidator.validateGetByAccessCount(args);
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL + "by-count?count={accessCount}", String.class, args[1]);
            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                List<Repository> repositories = Arrays.asList(new ObjectMapper().readValue(responseEntity.getBody(), Repository[].class));
                repositories.forEach(System.out::println);
            }
        } catch (HttpClientErrorException e){
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                System.out.println("Repository with access count >= " + args[1] + " not found.");
            } else {
                System.out.println("Unexpected error. Code: " + e.getStatusCode());
            }
        }
    }

    public void delete(String[] args) throws Exception {
        inputValidator.validateDelete(args);
        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(URL + "?name={name}", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class, args[1]);
            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                System.out.println("Repository with name '" + args[1] + "' deleted.");
            }
        } catch (HttpClientErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                System.out.println("Repository with name '" + args[1] + "' not found.");
            } else {
                System.out.println("Unexpected error. Code: " + e.getStatusCode());
            }
        }
    }
}
