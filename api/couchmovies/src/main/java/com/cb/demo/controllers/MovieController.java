package com.cb.demo.controllers;

import com.cb.demo.entities.vo.Result;
import com.cb.demo.entities.vo.Cover;
import com.cb.demo.services.MovieService;
import com.cb.demo.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
//import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( maxAge = 3600 )
@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/ping")
    String ping() {
        return "Pong!";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result search(@RequestParam("query") String query, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "fuzzy", required = false) Boolean fuzzy) {
        return movieService.searchQuery(query, filters, fuzzy);
    }
    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> autocomplete(@RequestParam("query") String query) {
        return movieService.autocomplete(query);
    }

    @RequestMapping(value = "/getCover", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Cover getCover(@RequestParam("movieName") String query) throws Exception {
        return imageService.getImg(query);
    }

}
