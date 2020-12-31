package com.boristenelsen.tabu;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SononymService {

    public List<TabuSononym> init() throws FileNotFoundException {
        String data = "";
        ClassPathResource cpr = new ClassPathResource("static/openthesaurus.txt");
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
            data = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {

        }

        String words [] = data.split("\\r?\\n");
        List<TabuSononym> tabuSononymList = new ArrayList<>();
        for(String w : words){

            List<String> strings  = Arrays.asList((w.replaceAll("\\(.*?\\)","")).split(";"));
            if(strings.size()>=5 ) tabuSononymList.add(new TabuSononym(strings));
        }

        System.out.println(words.length);
        return  tabuSononymList;

    }
}
