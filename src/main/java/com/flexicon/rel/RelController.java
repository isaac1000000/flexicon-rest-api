package com.flexicon.rel;

import com.flexicon.word.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * The REST controller for all requests related to rels.
 *
 * @author isaac1000000
 * @see RelService
 */
@RestController
@RequestMapping("/api/rels")
public class RelController {

    @Autowired
    private RelService relService;

    /**
     * Generic search for rels by base word, strength range, frequency range, index, and quantity.
     *
     * @param base the starting word of the relations
     * @param minStrength the minimum acceptable strength value
     * @param maxStrength the maximum acceptable strength value
     * @param minFrequency the minimum acceptable frequency value
     * @param maxFrequency the maximum acceptable frequency value
     * @param index the starting paging index of the returned list
     * @param quantity the number of entries that should be returned
     * @return a list of the relations as {@link RelDTO} instances
     */
    @GetMapping("/{base}")
    @ResponseStatus(HttpStatus.OK)
    public RelResponse findRelsByBaseStrengthAndFrequency(@PathVariable String base,
                                                            @RequestParam(name="nst", defaultValue="0") Double minStrength,
                                                            @RequestParam(name="xst", defaultValue="1") Double maxStrength,
                                                            @RequestParam(name="nfr", defaultValue="0") Double minFrequency,
                                                            @RequestParam(name="xfr", defaultValue="1") Double maxFrequency,
                                                            @RequestParam(name="i", defaultValue="0") int index,
                                                            @RequestParam(name="qty", defaultValue="1") int quantity) {

        base = base.toLowerCase();

        List<RelDTO> results = relService.findRelsByBaseIdStrengthAndFrequency(Word.toId(base),
                minStrength, maxStrength, minFrequency, maxFrequency, index, quantity);

        return new RelResponse(relService.getLastResponseSize(), results);
    }

    public RelController() { }

}
