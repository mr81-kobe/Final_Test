package com.example.finalproto.Repository;

import com.example.finalproto.Entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<Relation,Long> {

    public Relation findBySend(String send);



}
