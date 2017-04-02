package com.resttest.utils;

import com.resttest.dto.QuestionRepresentDto;
import com.resttest.dto.answer.SimpleAnswerDto;
import com.resttest.dto.answerrepresent.AnswerRepresentDto;
import com.resttest.model.Question;
import com.resttest.model.QuestionRepresent;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владислав on 28.03.2017.
 */
@Component
public class QuestionRepresentUtils {

    @Autowired
    private AnswerRepresentUtils answerRepresentUtils;

    public List<QuestionRepresentDto> convertEntitiesToDtos(List<QuestionRepresent> entities) {
        List<QuestionRepresentDto> dtos = new ArrayList<>();
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        entities.forEach(s -> {
            QuestionRepresentDto dto = new QuestionRepresentDto();
            List<AnswerRepresentDto> answerDtos = new ArrayList<>();
            Node node = parser.parse(s.getQuestion().getQuestion());
            s.getAnswers().forEach(j -> {
                answerDtos.add(answerRepresentUtils.convertEntityToDto(j));
            });
            dto.setQuestion(renderer.render(node));
            dto.setId(s.getId());
            dto.setAnswers(answerDtos);
            dto.setSerialNumber(s.getSerialNumber());
            dto.setType(s.getQuestion().getType().toString());
            dtos.add(dto);
        });
        return dtos;
    }

}
