package org.skypro.skyshop.model.search;

import org.skypro.skyshop.model.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {
        return storageService.getAllSearchables().stream()
                .filter(searchable -> containsPattern(searchable, pattern))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }

    private boolean containsPattern(Searchable searchable, String pattern) {
        return searchable.searchTerm().toLowerCase().contains(pattern.toLowerCase());
    }
}
