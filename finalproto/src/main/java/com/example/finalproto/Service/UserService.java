package com.example.finalproto.Service;

import com.example.finalproto.Entity.Relation;
import com.example.finalproto.Entity.User;
import com.example.finalproto.dto.RelationDTO;
import com.example.finalproto.dto.userDTO;

import java.util.List;

public interface UserService {

    public boolean login(userDTO dto);
    public List<String> getList();
  abstract   public void regist(userDTO dto);
    abstract  public void make_relation(RelationDTO dto);
    abstract public RelationDTO find_client(String id);




    default userDTO EntityToDTO( User user){

        userDTO dto = userDTO.builder().id(user.getId()).pwd(user.getPwd()).build();

        return dto;
    }

    default User DtoToEntity( userDTO dto){

        User entity = User.builder().id(dto.getId()).pwd(dto.getPwd()).build();

        return entity;
    }


    default RelationDTO rel_EntityToDTO(Relation relation){

        RelationDTO dto = RelationDTO.builder().send(relation.getSend()).accept(relation.getAccept()).build();

        return dto;
    }

    default Relation rel_DtoToEntity( RelationDTO dto){

        Relation entity = Relation.builder().send(dto.getSend()).accept(dto.getAccept()).build();

        return entity;
    }

}
