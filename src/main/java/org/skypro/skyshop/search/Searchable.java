package org.skypro.skyshop.search;

import java.util.UUID;

public interface Searchable {

    String searchTerm();

    String searchContent();

    default String getStringRepresentation() {
        return searchTerm() + " " + searchContent();
    }

    UUID getId();
}
