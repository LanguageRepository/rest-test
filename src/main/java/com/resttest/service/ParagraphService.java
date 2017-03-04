package com.resttest.service;

import com.resttest.dto.paragraph.ParagraphDto;
import com.resttest.dto.ShortView;
import com.resttest.dto.paragraph.ParagraphDtoForCreate;
import com.resttest.dto.paragraph.ParagraphDtoForTree;
import com.resttest.repository.ParagraphJpaRepository;
import com.resttest.utils.ParagraphUtils;
import com.resttest.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kvasa on 02.01.2017.
 */
@Service
public class ParagraphService {

    @Autowired
    private ParagraphJpaRepository paragraphJpaRepository;

    @Autowired
    private ParagraphUtils paragraphUtils;

    @Transactional
    public ParagraphDto getParagraph(Long id) {
        return paragraphUtils.convertEntityToDto(paragraphJpaRepository.findOne(id));
    }

    @Transactional
    public void createParagraph(ParagraphDtoForCreate dto) {
        paragraphJpaRepository.save(paragraphUtils.convertDtoToEntityForCreateAndUpdate(dto));
    }

    @Transactional
    public ShortView updateParagraph(ParagraphDto dto) {
        return paragraphUtils.convertParagraphToShortParagraph(paragraphJpaRepository.saveAndFlush(paragraphUtils.convertDtoToEntity(dto)));
    }

    @Transactional
    public RestResult deleteParagraph(Long id) {
        paragraphJpaRepository.delete(id);
        RestResult restResult = new RestResult("OK");
        if(paragraphJpaRepository.exists(id)) {
           restResult = new RestResult("ERROR");
        }
        return restResult;
    }

    @Transactional
    public ShortView getShortParagraph(Long id) {
        return paragraphUtils.convertParagraphToShortParagraph(paragraphJpaRepository.findOne(id));
    }

    @Transactional
    public List<ShortView> getShortParagraphs() {
        return paragraphUtils.convertParagraphsToShortViews(paragraphJpaRepository.findAll());
    }

    @Transactional
    public ParagraphDtoForTree getRootNode() {
        return paragraphUtils.convertEntityToDtoFroTree(paragraphJpaRepository.getOne(1l));
    }

}
