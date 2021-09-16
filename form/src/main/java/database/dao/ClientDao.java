package database.dao;

import database.DatabaseConnection;
import database.model.ClientEntity;

import java.util.List;

public class ClientDao implements DaoI<ClientEntity> {
    DatabaseConnection connection = new DatabaseConnection();

    @Override
    public ClientEntity get(Long id) {
        /* EMPTY */
        return null;
    }

    @Override
    public void create(ClientEntity clientEntity) {
        connection.executeTransaction(entityManager -> entityManager.persist(clientEntity));
    }

    @Override
    public List<ClientEntity> getAll() {
        /* EMPTY */
        return null;
    }

}
