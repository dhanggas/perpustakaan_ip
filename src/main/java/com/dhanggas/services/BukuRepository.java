
package com.dhanggas.services;

import com.dhanggas.entiry.Buku;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface BukuRepository extends CrudRepository<Buku, String>{
    public List<Buku> findAll();
    
}
