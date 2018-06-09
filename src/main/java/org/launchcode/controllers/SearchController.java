package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("checked", "all");
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value="results")
     public String search(Model model,String searchType, String searchTerm){

        model.addAttribute("columns", ListController.columnChoices);


        if(searchType.equals("all")){
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
            //added checked attribute to keep the field selected by the user.When searching,
            // if we select a given field to search within and submit, our choice is forgotten.
            // Modify the view template to keep the previous search field selected when
            // displaying results.
            model.addAttribute("checked", searchType);
            model.addAttribute("searchType",searchType);
            model.addAttribute("searchTerm", searchTerm);
        }

        else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
            //added checked attribute to keep the field selected by the user.When searching,
            // if we select a given field to search within and submit, our choice is forgotten.
            // Modify the view template to keep the previous search field selected when displaying results.
            model.addAttribute("checked", searchType);
            model.addAttribute("searchType",searchType);
            model.addAttribute("searchTerm", searchTerm);
        }

        return "search";
    }

}
