package com.resttest.controller;

import com.resttest.dto.ParagraphDto;
import com.resttest.dto.ShortView;
import com.resttest.model.Paragraph;
import com.resttest.service.ParagraphService;
import com.resttest.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kvasa on 02.01.2017.
 */
@RestController
@RequestMapping("/prg")
public class ParagraphRestController {

    @Autowired
    private ParagraphService paragraphService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
    public ParagraphDto getParagraph(@PathVariable("id") Long id) {
        return paragraphService.getParagraph(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ShortView createParagraph(@RequestBody ParagraphDto dto) {
        return paragraphService.createParagraph(dto);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ShortView updateParagraph(@RequestBody ParagraphDto dto) {
        return paragraphService.updateParagraph(dto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public RestResult deleteParagraph(@PathVariable("id") Long id) {
        return paragraphService.deleteParagraph(id);
    }

    @RequestMapping(value = "/get/short/{id}", method = RequestMethod.GET, produces = "application/json")
    public ShortView getShortParagraph(@PathVariable("id") Long id) {
        return paragraphService.getShortParagraph(id);
    }

    @RequestMapping(value = "/get/short", method = RequestMethod.GET, produces = "application/json")
    public List<ShortView> getShortParagraphs() {
        return paragraphService.getShortParagraphs();
    }
}
