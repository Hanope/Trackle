package com.gas.trackle.service;

import com.gas.trackle.domain.Article;
import java.util.List;

public interface SearchService {

    List<Article> search(String query);

}
