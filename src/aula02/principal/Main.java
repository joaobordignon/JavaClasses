package aula02.principal;

import aula02.contracts.IClienteRepository;
import aula02.entities.Cliente;
import aula02.factories.ConectionFactory;
import aula02.repositories.ClienteRepository;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente(2, "Januario", "123.154.988-52","Januario@ze.com.br");
        try {
            IClienteRepository repository =  new ClienteRepository(ConectionFactory.getConnection());
           // ClienteRepository repository = new ClienteRepository(ConectionFactory.getConnection());
            List<Cliente> clientes = repository.findAll();
            System.out.println(cliente1.getIdCliente() + " funcionou");

            for (Cliente c: clientes) {
                System.out.println(c.toString());
                System.out.println("**********");
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }


        }

    }

