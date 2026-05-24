package com.bitcoinlearn.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "glossary")
public class GlossaryTerm {
    @Id
    private String id;
    private String term;
    private String definition;
    private String category;

    public GlossaryTerm() {}

    public GlossaryTerm(String term, String definition, String category) {
        this.term = term;
        this.definition = definition;
        this.category = category;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }
    public String getDefinition() { return definition; }
    public void setDefinition(String definition) { this.definition = definition; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
