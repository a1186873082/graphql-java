package com.example.demo.fetcher;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "HarryPotter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "634",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")

    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "pageCount", "10",
                    "firstName", "chen",
                    "lastName", "li"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "kun",
                    "lastName", "wang"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "pan",
                    "lastName", "xu")
    );

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books.stream().filter(book -> book.get("id").equals(bookId)).findFirst().orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnviroment -> {
            Map<String, String> book = dataFetchingEnviroment.getSource();
            String authorId = book.get("authorId");
            return authors.stream().filter(author -> author.get("id").equals(authorId)).findFirst().orElse(null);
        };
    }

}
