package com.richie.pstsql.repository;

import com.richie.pstsql.entity.Person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * pstsql
 * <p>
 * Created by lylaut on 2019-05-17
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "select * from richie.person b where b.tags ->>?1 = ?2 ", nativeQuery = true)
    List<Person> selectMap(String tag, String value);
}
