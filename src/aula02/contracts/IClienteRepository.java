package aula02.contracts;

import aula02.entities.Cliente;

public interface IClienteRepository extends IBaseRepository<Cliente, Integer> {
    Cliente findByCpf(String cpf) throws Exception;
}
