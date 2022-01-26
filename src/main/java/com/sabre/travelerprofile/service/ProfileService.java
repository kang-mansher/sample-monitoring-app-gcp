package com.sabre.travelerprofile.service;

import com.sabre.travelerprofile.metrics.MetricsHandler;
import com.sabre.travelerprofile.model.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sabre.travelerprofile.utils.Constants.*;

/**
 * Class to handle api requests and process profiles
 */
@Service
public class ProfileService {

    private final MetricsHandler metricsHandler;
    private final Map<String, Profile> profileMap;

    public ProfileService(MetricsHandler metricsHandler) {
        this.metricsHandler = metricsHandler;
        profileMap = new HashMap<>();
    }

    /**
     * Method to create traveler profile
     * @param name - name of traveler
     * @param email - emailId
     * @param phone - contact number
     * @return error message or name of traveler
     */
    public String createProfile(String name, String email, String phone) {
        metricsHandler.incrementCreateRequestCount();
        if(profileExists(email)) {
            return ERROR_EMAIL_ALREADY_REGISTERED;
        }
        Profile profile = new Profile(name, email, phone);
        profileMap.put(email, profile);
        return name;
    }

    /**
     * Method to update traveler profile
     * @param email - emailId
     * @param name - name of traveler
     * @param phone - contact number
     * @return error message or name of traveler
     */
    public String updateProfile(String email, String name, String phone) {
        metricsHandler.incrementUpdateRequestCount();
        if(!profileExists(email)) {
            return ERROR_EMAIL_NOT_REGISTERED;
        } else if(name.isEmpty() && phone.isEmpty()){
            return ERROR_EMPTY_UPDATE_REQUEST;
        }
        profileMap.get(email).update(name, phone);
        return name;
    }

    /**
     * Method to delete traveler profile
     * @param email - emailId
     * @return error message or name of traveler
     */
    public String deleteProfile(String email) {
        metricsHandler.incrementDeleteRequestCount();
        if(!profileExists(email)) {
            return ERROR_EMAIL_NOT_REGISTERED;
        }
        String name = profileMap.get(email).getName();
        profileMap.remove(email);
        return name;
    }

    /**
     * Private method to verify if email is already present
     * @param email - emailId
     * @return boolean
     */
    private boolean profileExists(String email) {
        return profileMap.containsKey(email);
    }

    /**
     * Method to get all existing profiles
     * @return List of profiles
     */
    public List<Profile> getProfiles() {
        return new ArrayList<>(profileMap.values());
    }
}
