package com.restassured.stepdef.servicesteps;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassured.setup.ExtentReport;

import io.cucumber.java.DataTableType;
import io.cucumber.java.DocStringType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CucumberExamplesSteps extends ExtentReport
{
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@DataTableType
    public Author authorEntry(Map<String, String> entry) {
        return new Author(
            entry.get("firstName"),
            entry.get("lastName"),
            entry.get("book"));
    }
	
	@ParameterType(".*")
    public Author author(String author) {
    	return new Author(author, "", "");
    }
	
	@DocStringType
    public List<Author> json(String docString) throws IOException {
        return objectMapper.readValue(docString, new TypeReference<List<Author>>() {
        });
    }
	
	@Given("the following animals:")
	public void the_following_animals(io.cucumber.datatable.DataTable dataTable)
	{
		List<String> animals = dataTable.asList();
		for (String animal : animals)
		{
			LogResult(false, "Animal: [<b>" + animal + "</b>]");
		}
	}
	
	@Given("these are my favourite authors")
	public void these_are_my_favourite_authors(List<Author> authors) {
		System.out.println("####################################################");
		for(Author author: authors) 
		{
			System.out.println(author.getFirstName() + " " + author.getLastName() + " likes book: " + author.getBook());
		}
		System.out.println("####################################################");
	}
	
	@Given("My favorite author is {author}")
	public void my_favorite_author_is_nitesh(Author author) {
		System.out.println("####################################################");
		System.out.println("My favourite author is: " + author.getFirstName());
		System.out.println("####################################################");
	}
	
	@Given("some more information")
	public void some_more_information(List<Author> authors) {
		System.out.println("####################################################");
		for(Author author: authors) 
		{
			System.out.println(author.getFirstName() + " " + author.getLastName() + " likes book: " + author.getBook());
		}
		System.out.println("####################################################");
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Author
{
	public String firstName;
	public String lastName;
	public String book;	
}