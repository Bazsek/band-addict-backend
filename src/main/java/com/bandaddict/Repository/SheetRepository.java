package com.bandaddict.Repository;

import com.bandaddict.Entity.Sheet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheetRepository extends CrudRepository<Sheet, Long> {

    /**
     * Get latest 10 sheet
     * @return list of sheets
     */
    List<Sheet> findAll();
}
