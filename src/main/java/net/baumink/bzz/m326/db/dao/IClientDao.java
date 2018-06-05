package net.baumink.bzz.m326.db.dao;

import net.baumink.bzz.m326.db.pojo.Client;

import java.util.List;

public interface IClientDao {

    void insert(Client client);

    void update(Client client);

    void delete(Client client);

    Client getById(int id);

    List<Client> getAll();

}
