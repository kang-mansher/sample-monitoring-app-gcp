# **Traveler Profile Application**

`Application to process requests for managing traveler profiles`

Spring Boot Application to create, update and delete the profiles of users.

------------------------------------------------------------------------------------------------------------------------
`Monitoring and Metrics`

Application logs count of create, upload and delete requests and pushes these metrics to gcp monitoring stack.

------------------------------------------------------------------------------------------------------------------------
`API Endpoints for managing travel profiles`

**/createProfile:** pass name, email address and phone number to create traveler profile

**/updateProfile:** pass emailId, and optionally enter name or phone number of traveler to update

**/deleteProfile:** delete profile by emailId

**/listAllProfiles:** list down all the profiles in database




