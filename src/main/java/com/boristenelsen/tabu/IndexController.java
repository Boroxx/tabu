package com.boristenelsen.tabu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    @Autowired
    SononymService sononymService;
    @GetMapping("/")
    public String main(Model model) throws FileNotFoundException {
        List<TabuSononym> tabuSononymList =sononymService.init();

        Collections.shuffle(tabuSononymList);
        List<TabuSononym> firstNElementsList = tabuSononymList.stream().limit(100).collect(Collectors.toList());
        System.out.println(tabuSononymList.size());

        model.addAttribute("tabuwords",firstNElementsList);
        return "index";
    }
}
