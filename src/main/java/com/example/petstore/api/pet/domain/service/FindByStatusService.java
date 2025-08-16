package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.domain.repository.PetRepository;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceOutput;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindByStatusService {

  private final PetRepository petRepository;

  public FindByStatusServiceOutput execute(FindByStatusServiceInput input) {

    System.out.println("処理開始しました");
    List<PetEntity> pets = petRepository.findByStatus(input.getStatus());
    return FindByStatusServiceOutput.builder().pets(pets).build();
  }
}
