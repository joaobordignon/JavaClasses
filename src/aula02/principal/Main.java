package aula02.principal;

import aula02.contracts.IClienteRepository;
import aula02.entities.Cliente;
import aula02.factories.ConectionFactory;
import aula02.repositories.ClienteRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente(null, "Ze", "123.154.698-52","Zeh@ze.com.br");
        try {
            IClienteRepository repository =  new ClienteRepository(ConectionFactory.getConnection());
           // ClienteRepository repository = new ClienteRepository(ConectionFactory.getConnection());
            repository.create(cliente1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Cliente" + cliente1.getIdCliente() + " cadastrado com sucesso");
    }
}
