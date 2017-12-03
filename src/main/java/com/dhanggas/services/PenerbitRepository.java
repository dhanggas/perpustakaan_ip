
package com.dhanggas.services;

import com.dhanggas.entiry.Penerbit;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface PenerbitRepository extends CrudRepository<Penerbit, String>{
    public List<Penerbit> findAll();
            
}
