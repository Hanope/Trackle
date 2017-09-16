package com.gas.trackle.api;

import com.gas.trackle.domain.Article;
import com.gas.trackle.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchAPI {

    @Autowired
    private SearchService searchService;

    @RequestMapping(name = "/api/search", method = RequestMethod.GET)
    public List<Article> search(@RequestParam String query) {
        return searchService.search(query);
    }
}
