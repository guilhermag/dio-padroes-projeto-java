package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private EnderecoRepository enderecoRepository;
  @Autowired
  private ViaCepService viaCepService;

  @Override
  public Iterable<Cliente> buscarTodos() {
    return clienteRepository.findAll();
  }

  @Override
  public Cliente buscarPorId(Long id) {
    Optional<Cliente> cliente = clienteRepository.findById(id);
    return cliente.get();
  }

  @Override
  public void inserir(Cliente cliente) {
    String cep = cliente.getEndereco().getCep();
    Long cepLong = Long.parseLong(cep);
    Endereco endereco = enderecoRepository.findById(cep.orElseGet(() -> {
      Endereco novoEndereco = viaCepService.consultarCep(cep);
      enderecoRepository.save(novoEndereco);
      return null;
    });
    cliente.setEndereco(endereco);
    clienteRepository.save(cliente);
  }

  @Override
  public void atualizar(Long id, Cliente cliente) {

  }

  @Override
  public void deletar(Long id) {

  }
}
