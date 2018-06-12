package net.baumink.bzz.m326.db.dao;

import net.baumink.bzz.m326.db.pojo.Client;

import java.util.List;

/**
 * The interface Client dao.
 */
public interface IClientDao {

    /**
     * Insert Client.
     *
     * @param client the client
     */
    void insert(Client client);

    /**
     * Update Client.
     *
     * @param client the client
     */
    void update(Client client);

    /**
     * Delete Client.
     *
     * @param client the client
     */
    void delete(Client client);

    /**
     * Gets Client by id.
     *
     * @param id the id
     * @return the by id
     */
    Client getById(int id);

    /**
     * Gets all Clients.
     *
     * @return the all
     */
    List<Client> getAll();

}
