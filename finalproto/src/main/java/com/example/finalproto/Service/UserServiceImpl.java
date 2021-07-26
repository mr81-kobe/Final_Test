package com.example.finalproto.Service;

import com.example.finalproto.Entity.QUser;
import com.example.finalproto.Entity.Relation;
import com.example.finalproto.Entity.User;
import com.example.finalproto.Repository.RelationRepository;
import com.example.finalproto.Repository.UserRepository;
import com.example.finalproto.dto.RelationDTO;
import com.example.finalproto.dto.userDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @PersistenceUnit
    private EntityManagerFactory emf;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository repo;
    @Autowired
    RelationRepository rel_repo;
    @Override
    public boolean login(userDTO dto) {


        QUser quser = new QUser("user");
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression2 = quser.pwd.eq(dto.getPwd());
        BooleanExpression expression = quser.id.eq(dto.getId()).and(expression2);

        JPAQueryFactory query = new JPAQueryFactory(em);

        User user2=query.selectFrom(quser).where(expression).fetchOne();

       if(user2==null){
           return false;
       }
        else{
           return true;
        }







    }

    @Override
    public List<String> getList() {
    List<String> list = new ArrayList<>() ;
       List<User> en_li =repo.findAll();

        for (User user: en_li )
             {
             list.add(user.getId());

             }

    return list;
    }

    @Override
    public void regist(userDTO dto) {
        repo.save(DtoToEntity(dto));
        System.out.println("회원가입 완료");
    }

    public void make_relation(RelationDTO dto){


        rel_repo.save(rel_DtoToEntity(dto));

    }
    public RelationDTO find_client(String id){
        List<RelationDTO> dtoList = new ArrayList<>();
        Relation rel=rel_repo.findBySend(id);

        RelationDTO dto = RelationDTO.builder().accept(rel.getAccept()).send(rel.getSend()).build();



       return dto;

    }


}
