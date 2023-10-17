package dao;

import java.util.ArrayList;

public interface ClientDAO<Client> {
    void insertClient(Client client);

    void updateClient(Client client);

    void deleteClient(String id);

    ArrayList<Client> selectProduct();

    Client findById(String id);
}
