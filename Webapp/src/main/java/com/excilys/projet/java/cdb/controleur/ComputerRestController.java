package com.excilys.projet.java.cdb.controleur;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceComputer;
 
@RestController
@CrossOrigin
@RequestMapping("computers")
public class ComputerRestController {

	@Autowired
	private ServiceComputer computerService;

	@GetMapping(value = { "", "/" }, produces = "application/json")
	public List<ComputerDTO> listComputers() {
		List<Computer> allComputers = computerService.getComputerList();
		return allComputers.stream().map(c -> ComputerMapper.convertComputertoComputerDTO(c)).collect(Collectors.toList());
	}

	@GetMapping(value = { "/number" }, produces = "application/json")
	public Integer numberComputers() {
		return computerService.getCount();
	}

	@GetMapping(value = "/search/{search}", produces = "application/json")
	public List<ComputerDTO> searchComputer(@PathVariable String search) {
		List<Computer> allComputers = computerService.findComputerByName(search);
		return allComputers.stream().map(c -> ComputerMapper.convertComputertoComputerDTO(c)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ComputerDTO getComputer(@PathVariable Long id) {
		Optional<Computer> computerOpt = Optional.ofNullable(computerService.findComputerById(id));
		if (!computerOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Computer is not found is the database");
		}
		return ComputerMapper.convertComputertoComputerDTO(computerOpt.get());
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<String> deleteComputer(@PathVariable Long id) {
		computerService.deleteComputer(id);
		return ResponseEntity.ok("{ok : true}");
	}

	@PostMapping(value = { "", "/" }, produces = "application/json")
	public ResponseEntity<String> createComputer(@RequestBody ComputerDTO dto) {
		computerService.addComputer(ComputerMapper.convertComputerDTOtoComputer(dto));
		return ResponseEntity.ok("{ok : true}");
	}

	@PutMapping(value = { "", "/" }, produces = "application/json")
	public ResponseEntity<String> updateComputer(@RequestBody ComputerDTO dto) {
		computerService.updateComputer(ComputerMapper.convertComputerDTOtoComputer(dto));
		return ResponseEntity.ok("{ok : true}");
	} 
}