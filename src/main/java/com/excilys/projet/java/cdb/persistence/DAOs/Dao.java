package com.excilys.projet.java.cdb.persistence.DAOs;

import java.util.List;
import java.util.Optional; 

public abstract class Dao<T> {

    public abstract List<T> getAll();

    public abstract Optional<T> findById(Long id);

}
