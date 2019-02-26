package br.com.carina.paifilho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carina.paifilho.modelo.Filho;
import br.com.carina.paifilho.modelo.Pai;
import br.com.carina.paifilho.repository.PaiRepository;

@RestController
@RequestMapping("/api/pai")
public class PaiController {
	
	@Autowired
	private PaiRepository repository;
	
	
	@GetMapping("/list")
	public List<Pai> list(){
		 List<Pai> pais =  repository.findAll();
		return pais;
	}
	
	@GetMapping("/get/{id}")
	public Pai get(@PathVariable Integer id) {
		return repository.findById(id).get();
	}
	
	
	@PostMapping("/save")
	public String save(@RequestBody Pai pai) {
		
		for(Filho filho : pai.getFilhos()) {
			filho.setPai(pai);
		}
		
		repository.save(pai);
		return "Pai adicionado/atualizado";
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		Pai pai = repository.getOne(id);
		repository.delete(pai);
		
		return "Pai deletado com sucesso";
	}
	
}