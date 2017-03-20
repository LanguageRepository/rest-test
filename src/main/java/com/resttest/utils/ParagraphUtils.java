package com.resttest.utils;

import com.resttest.dto.paragraph.ParagraphDto;
import com.resttest.dto.ShortView;
import com.resttest.dto.paragraph.ParagraphDtoForCreate;
import com.resttest.dto.paragraph.ParagraphDtoForTree;
import com.resttest.model.Paragraph;
import com.resttest.model.Test;
import com.resttest.repository.ParagraphJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvasa on 02.01.2017.
 */
@Component
public class ParagraphUtils {

    @Autowired
    private ParagraphUtils paragraphUtils;

    @Autowired
    private ParagraphJpaRepository jpaRepository;

    public ParagraphDto convertEntityToDto(Paragraph entity) {
        ParagraphDto dto = new ParagraphDto();
        List<ShortView> shortChilds = new ArrayList<>();
        List<ShortView> shortTests = new ArrayList<>();
        for(int i = 0 ; i < entity.getChilds().size(); i++) {
            ShortView shortChild = new ShortView();
            shortChild.setId(entity.getChilds().get(i).getId());
            shortChild.setName(entity.getChilds().get(i).getName());
            shortChilds.add(shortChild);
        }
        for(int i = 0; i < entity.getTests().size(); i++) {
            ShortView shortTest = new ShortView();
            shortTest.setId(entity.getTests().get(i).getId());
            shortTest.setName(entity.getTests().get(i).getName());
            shortTests.add(shortTest);
        }
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setParent(entity.getParent());
        dto.setChilds(shortChilds);
        dto.setTests(shortTests);
        return dto;
    }

    public Paragraph convertDtoToEntity(ParagraphDto dto) {
        Paragraph entity = new Paragraph();
        List<Paragraph> childs = new ArrayList<>();
        List<Test> tests = new ArrayList<>();
        for(int i = 0; i < dto.getChilds().size(); i++) {
            Paragraph paragraph = new Paragraph();
            paragraph.setId(dto.getChilds().get(i).getId());
            paragraph.setName(dto.getChilds().get(i).getName());
        }
        for(int i = 0; i < dto.getTests().size(); i++) {
            Test test = new Test();
            test.setId(dto.getTests().get(i).getId());
            test.setName(dto.getTests().get(i).getName());
        }
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setParent(dto.getParent());
        entity.setChilds(childs);
        entity.setTests(tests);
        return entity;
    }

    public ShortView convertParagraphToShortParagraph(Paragraph entity) {
        ShortView shortView = new ShortView();
        shortView.setId(entity.getId());
        shortView.setName(entity.getName());
        return shortView;
    }

    public List<ShortView> convertParagraphsToShortViews(List<Paragraph> entities) {
        List<ShortView> shortViews = new ArrayList<>();
        for (Paragraph entity : entities) {
            ShortView shortView = new ShortView();
            shortView.setId(entity.getId());
            shortView.setName(entity.getName());
            shortViews.add(shortView);
        }
        return shortViews;
    }

    public ParagraphDtoForTree convertEntityToDtoFroTree(Paragraph entity) {
        if(entity != null) {
            ParagraphDtoForTree dto = new ParagraphDtoForTree();
            List<ParagraphDtoForTree> childs = new ArrayList<>();
            for (int i = 0; i < entity.getChilds().size(); i++) {
                childs.add(paragraphUtils.convertEntityToDtoFroTree(entity.getChilds().get(i)));
            }
            dto.setId(entity.getId());
            dto.setChildren(childs);
            dto.setText(entity.getName());
            return dto;
        } else {
            return new ParagraphDtoForTree();
        }
    }

    public Paragraph convertDtoToEntityForCreateAndUpdate(ParagraphDtoForCreate dto) {
        Paragraph entity;
        if(dto.getId() != null) {
            entity = jpaRepository.getOne(dto.getId());
            entity.setName(dto.getName());
        } else {
            entity = new Paragraph();
            entity.setName(dto.getName());
            entity.setParent(jpaRepository.getOne(dto.getParentId()));
        }
        return entity;
    }

}
