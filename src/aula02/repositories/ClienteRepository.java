package aula02.repositories;

import aula02.contracts.IClienteRepository;
import aula02.entities.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements IClienteRepository {
    private Connection con;

    public ClienteRepository(Connection con) {
        this.con = con;
    }

    @Override
    public void create(Cliente obj) throws Exception {
        String query = "INSERT INTO CLIENTE(" +
            "nome, cpf, email) VALUES (?,?,?)";
        PreparedStatement st = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        st.setString(1,obj.getNome());
        st.setString(2,obj.getCpf());
        st.setString(3,obj.getEmail());
        st.execute();
        ResultSet resultClienteObj = st.getGeneratedKeys();
        if(resultClienteObj.next()){
            obj.setIdCliente(resultClienteObj.getInt(1));
        }
        st.close();
    }

    @Override
    public void update(Cliente obj) throws Exception {
        String query = "UPDATE CLIENTE SET nome=?, cpf=?, email=? WHERE idCliente=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,obj.getNome());
        st.setString(2,obj.getCpf());
        st.setString(3,obj.getEmail());
        st.setInt(4,obj.getIdCliente());
        st.execute();
        st.close();
    }

    @Override
    public void delete(Integer id) throws Exception {
        String query = "DELETE FROM CLIENTE WHERE idCliente=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,id);
        st.execute();
        st.close();
    }

    @Override
    public List<Cliente> findAll() throws Exception {
        String query = "SELECT * FROM CLIENTE";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet resultSet = st.executeQuery();
        List<Cliente> clientes = new ArrayList<>();
        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("idCliente"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setCpf(resultSet.getString("cpf"));
            cliente.setEmail(resultSet.getString("email"));
            clientes.add(cliente);
        }
      st.close();
        return clientes;
    }

    @Override
    public Cliente findById(Integer id) throws Exception {
        String query = "SELECT * FROM CLIENTE WHERE idCliente=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,id);
        ResultSet resultSet = st.executeQuery();
        Cliente cliente = null;
        if (resultSet.next()) {
            cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("idCliente"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setCpf(resultSet.getString("cpf"));
            cliente.setEmail(resultSet.getString("email"));
        }
        st.close();
        return cliente;
    }

    @Override
    public Cliente findByCpf(String cpf) throws Exception {
        String query = "SELECT * FROM CLIENTE WHERE cpf=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,cpf);
        ResultSet resultSet = st.executeQuery();
        Cliente cliente = null;
        if (resultSet.next()) {
            cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("idCliente"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setCpf(resultSet.getString("cpf"));
            cliente.setEmail(resultSet.getString("email"));
        }
        st.close();
        return cliente;
    }
}
