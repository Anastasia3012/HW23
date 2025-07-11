package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.search.SearchService;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    public void searchObjectWhenStorageServiceIsEmpty() {
        String searchPattern = "test";
        //given
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyNavigableSet());

        //when
        Collection<SearchResult> result = searchService.search(searchPattern);

        //then
        assertNotNull(result, "Не должно быть null !");
        assertTrue(result.isEmpty(), "Не должен быть пустым");
    }

    @Test
    public void searchObjectWhenStorageServiceIsNotEmptyButNoSuitableOne() {
        String searchPattern = "Гвоздь";

        //given
        Searchable product1 = mock(Searchable.class);
        when(product1.searchTerm()).thenReturn("Продукт");

        Searchable article1 = mock(Searchable.class);
        when(article1.searchTerm()).thenReturn("Статья");

        Collection<Searchable> searchables = List.of(product1, article1);
        when(storageService.getAllSearchables()).thenReturn(searchables);

        //when
        Collection<SearchResult> result = searchService.search(searchPattern);

        //then
        assertNotNull(result, "Не должно быть null !");
        assertTrue(result.isEmpty(), "Не должен быть пустым");
        verify(product1, atLeastOnce()).searchTerm();
        verify(article1, atLeastOnce()).searchTerm();
    }

    @Test
    public void searchWhenObjectInStorageService() {
        String searchPattern = "Хлеб";
        UUID objectID = UUID.randomUUID();
        String objectName = "Хлеб";

        //given
        Searchable productInStorageService = mock(Searchable.class);
        when(productInStorageService.searchTerm()).thenReturn(objectName);
        when(productInStorageService.getId()).thenReturn(objectID);

        Searchable articleIsNotStorageService = mock(Searchable.class);
        when(articleIsNotStorageService.searchTerm()).thenReturn("Статья");

        when(storageService.getAllSearchables())
                .thenReturn(List.of(productInStorageService, articleIsNotStorageService));

        //when
        Collection<SearchResult> result = searchService.search(searchPattern);

        //then
        assertNotNull(result, "Не должно быть null !");
        assertEquals(1, result.size(), "Один результат");

        SearchResult results = result.iterator().next();
        assertEquals(objectID.toString(), results.getId(), "Должен совпадать ID");
        assertEquals(objectName, results.getName(), "Должно совпадать наименование");

        verify(storageService).getAllSearchables();
        verify(articleIsNotStorageService).searchTerm();
    }

}

