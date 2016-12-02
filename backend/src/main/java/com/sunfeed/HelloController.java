package com.sunfeed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.HTMLReader;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@RestController
public class HelloController {
    
    private ObjectMapper mapper = new ObjectMapper();

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/")
    public List<Map<String, String>> index() throws IllegalArgumentException, FeedException, IOException {
        URL url = new URL("http://www.javaworld.com/index.rss");
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(url));
                
        return (List<Map<String, String>>) feed.getEntries().stream().map( entryObject -> {
            SyndEntryImpl syndEntry = (SyndEntryImpl) entryObject;
            HashMap<String, String> titleToDesc = new HashMap<String, String>();
            titleToDesc.put("title", syndEntry.getTitle());
            titleToDesc.put("description", syndEntry.getDescription().getValue());
            return titleToDesc;
        } ).collect(Collectors.toList());
        
//        Map<String, String> titleToDesc = new HashMap<String, String>();
//        for (Object o : feed.getEntries()) {
//        	SyndEntryImpl entry = (SyndEntryImpl) o;
//        	titleToDesc.put(entry.getTitle(), entry.getDescription().getValue());
//        }
    }
    
    //@CrossOrigin(origins = "http://localhost:3000")
    @CrossOrigin(origins = "*")
    @GetMapping("/api/comments")
    public String getComments() throws IOException {
        byte[] encodedContent = Files.readAllBytes(Paths.get("D:\\Softs\\GitHub\\react-tutorial\\comments.json"));

        return new String(encodedContent, StandardCharsets.UTF_8);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/api/comments")
    public String postComment(Comment comment) throws IOException {
        File commentsFile = new File("D:\\Softs\\GitHub\\react-tutorial\\comments.json");
        byte[] encodedContent = Files.readAllBytes(Paths.get(commentsFile.getAbsolutePath()));
        List<Comment> existingComments = mapper.readValue(new String(encodedContent, StandardCharsets.UTF_8),
                                                          new TypeReference<List<Comment>>() {});

        comment.setId(new Date());
        existingComments.add(comment);

        FileWriter fileWriter = new FileWriter(commentsFile);
        fileWriter.write(mapper.writeValueAsString(existingComments));
        fileWriter.close();
        return "";
    }
}
