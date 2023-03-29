package com.habibian.service.impl;

import com.habibian.domain.entity.ExampleSentence;
import com.habibian.domain.entity.Vocab;
import com.habibian.dto.example_sentence.ExampleSentenceRequestDTO;
import com.habibian.dto.example_sentence.ExampleSentenceResponseDTO;
import com.habibian.dto.vocab.VocabResponseDTO;
import com.habibian.exception.domain.ResourceNotFoundException;
import com.habibian.repository.ExampleSentenceRepository;
import com.habibian.service.ExampleSentenceService;
import com.habibian.service.VocabService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ExampleSentenceServiceImpl implements ExampleSentenceService {

    private final ExampleSentenceRepository sentenceRepository;
    private final VocabService vocabService;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Override
    public ExampleSentenceResponseDTO create(ExampleSentenceRequestDTO requestDTO) {
        ExampleSentence exampleSentence = new ExampleSentence();

        return getExampleSentenceResponseDTO(requestDTO, exampleSentence);
    }

    @Override
    public ExampleSentenceResponseDTO getById(long id) {
        ExampleSentence response = sentenceRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ExampleSentence", "ID", id));
        return modelMapper.map(response, ExampleSentenceResponseDTO.class);
    }

    @Override
    public List<ExampleSentenceResponseDTO> getAllByVocabId(long vocabId) {
        List<ExampleSentence> responseList = sentenceRepository.findAllByVocab_Id(vocabId);
        return responseList.stream()
                .map((sentence) -> modelMapper.map(sentence, ExampleSentenceResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExampleSentenceResponseDTO update(long id, ExampleSentenceRequestDTO requestDTO) {
        ExampleSentence exampleSentence = sentenceRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ExampleSentence", "ID", id));

        return getExampleSentenceResponseDTO(requestDTO, exampleSentence);
    }

    @Override
    public void delete(long id) {
        ExampleSentence exampleSentence = sentenceRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ExampleSentence", "ID", id));
        sentenceRepository.delete(exampleSentence);
    }

    private ExampleSentenceResponseDTO getExampleSentenceResponseDTO(ExampleSentenceRequestDTO requestDTO, ExampleSentence exampleSentence) {
        VocabResponseDTO vocabResponseDTO = vocabService.getById(requestDTO.getVocabId());
        Vocab vocab = entityManager.merge(modelMapper.map(vocabResponseDTO, Vocab.class));

        exampleSentence.setSentence(requestDTO.getSentence());
        exampleSentence.setVocab(vocab);

        vocab.addExampleSentence(exampleSentence);

        ExampleSentence response = sentenceRepository.save(exampleSentence);
        sentenceRepository.flush();

        return modelMapper.map(response, ExampleSentenceResponseDTO.class);
    }
}
