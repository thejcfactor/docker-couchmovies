package com.cb.demo;

import com.cb.demo.services.MovieService;
import com.cb.demo.entities.vo.Result;
import com.cb.demo.entities.vo.SearchResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class MovieControllerTest {

    @Autowired
    private MovieService movieService;

    // @Test
    // public void testPing() throws Exception {
    //     when().get("api/movie/ping").then()
    //             .body(is("Pong!"));
    // }

    @Test
   public void testSearchOriginalTitle(){

       Result movies = movieService.searchQuery("Star Trek", "", false);
       System.out.println("Found "+movies.getResults().size()+" movies.");
       //printResults(movies.getResults());
   }

   public void printResults(List<SearchResult> movies){
        int counter = 1;

        for(SearchResult result: movies) {
            System.out.println("");
            System.out.println("");
            System.out.println(counter+") "+result.getMovie().getTitle());
            System.out.println("");
            counter++;
        }
    }

    // @Test
	// public void contextLoads() {
	// }
}