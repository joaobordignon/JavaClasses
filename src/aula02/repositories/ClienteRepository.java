package aula02.repositories;

import aula02.contracts.IClienteRepository;
import aula02.entities.Cliente;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    }

    @Override
    public void delete(Integer integer) throws Exception {

    }

    @Override
    public List<Cliente> findAll() throws Exception {
        return null;
    }

    @Override
    public Cliente findById(Integer integer) throws Exception {
        return null;
    }
}
