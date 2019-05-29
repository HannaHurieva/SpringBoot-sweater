package com.alevel.springMVC.sweater.repository;

import com.alevel.springMVC.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called MessageRepo
// CRUD refers Create, Read, Update, Delete
public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
}
