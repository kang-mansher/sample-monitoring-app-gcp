package com.sabre.travelerprofile.controller;

import com.sabre.travelerprofile.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller with all APIs for the application
 */
@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * method points to homePage of the app
     * @return String(html page)
     */
    @GetMapping("/")
    public String init() {
        return "homePage";
    }

    /**
     * API to create traveler profile
     * @return String(html page)
     */
    @GetMapping("/createProfile")
    public String createProfile() {
        return "createProfile";
    }

    /**
     * Post API called by form in createProfile.html
     * @param name - name of traveler(required)
     * @param email - emailId(required)
     * @param phone - contact number(required)
     * @param model - model to attach the attributes that will be passed to html page
     * @return String(html page)
     */
    @PostMapping("/create")
    public String create(@RequestParam(name="name") String name,
                         @RequestParam(name="email") String email,
                         @RequestParam(name="phone") String phone,
                         Model model) {
        String result = profileService.createProfile(name, email, phone);
        return processResult(result, "created", model);
    }

    /**
     * API to update details in traveler profile
     * @return String(html page)
     */
    @GetMapping("/updateProfile")
    public String updateProfile() {
        return "updateProfile";
    }

    /**
     * Post API called by form in updateProfile.html
     * @param email - emailId (required)
     * @param name - name of traveler
     * @param phone - contact number
     * @param model - model to attach the attributes that will be passed to html page
     * @return String(html page)
     */
    @PostMapping("/update")
    public String update(@RequestParam(name="email") String email,
                         @RequestParam(name="name", required = false) String name,
                         @RequestParam(name="phone", required = false) String phone,
                         Model model) {
        String result = profileService.updateProfile(email, name, phone);
        return processResult(result, "updated", model);
    }

    /**
     * API to delete details in traveler profile
     * @return String(html page)
     */
    @GetMapping("/deleteProfile")
    public String deleteProfile() {
        return "deleteProfile";
    }

    /**
     * Post API called by form in deleteProfile.html
     * @param email - emailId (required)
     * @param model - model to attach the attributes that will be passed to html page
     * @return String(html page)
     */
    @PostMapping("/delete")
    public String delete(@RequestParam(name="email") String email, Model model) {
        String result = profileService.deleteProfile(email);
        return processResult(result, "deleted", model);
    }

    /**
     * Get API to list down all profiles in database
     * @param model - model to attach the attributes that will be passed to html page
     * @return String(html page)
     */
    @GetMapping("/listAllProfiles")
    public String list(Model model) {
        model.addAttribute("profiles", profileService.getProfiles());
        return "listProfiles";
    }

    /**
     * Private method to process the results from ProfileService
     * @param result - error or success result
     * @param action - created, updated or deleted
     * @param model - model to attach the attributes that will be passed to html page
     * @return String(html page)
     */
    private String processResult(String result, String action, Model model) {
        if(result.startsWith("Error")) {
            model.addAttribute("error", result);
            return "errorPage";
        }
        model.addAttribute("name", result);
        model.addAttribute("action", action);
        return "successPage";
    }

}