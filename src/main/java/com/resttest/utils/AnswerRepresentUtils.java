package com.resttest.utils;

import com.resttest.dto.answerrepresent.AnswerRepresentDto;
import com.resttest.model.AnswerRepresent;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

/**
 * Created by Владислав on 02.04.2017.
 */
@Component
public class AnswerRepresentUtils {

    public AnswerRepresentDto convertEntityToDto(AnswerRepresent entity) {
        AnswerRepresentDto dto = new AnswerRepresentDto();
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node node = parser.parse(entity.getAnswer());
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setAnswer(renderer.render(node));
        dto.setChosen(entity.getChosen());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setSerialNumber(entity.getSerialNumber());
        return dto;
    }

}
