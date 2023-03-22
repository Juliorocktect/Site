**Site**

> Todo-Liste

- [ ] Fix Nullpointer exception 
```java
    public User(String firstName, String lastName, String userName, String pictureUrl) {  
   this.id = RandomStringUtils.randomAlphanumeric(10);  
   this.firstName = firstName;  
   this.lastName = lastName;  
   this.userName = userName;  
   this.pictureUrl = pictureUrl;  
  }  
  @PostMapping("/createNewUser")  
  private ResponseEntity<?> createNewUser(@RequestParam String firstName, @RequestParam String lastName,@RequestParam String userName,@RequestParam String pictureUrl){  
      User user = new User(firstName, lastName, userName, pictureUrl);  
   this.allUsers.add(user);  
   return ResponseEntity.ok("success");  
  }
  ```
- [ ] REST API for Video Class
- [ ] Implement User creation
- [ ] Mongo DB Intrgration
- [ ] Restructure the Project
- [ ] Implementation of Search function
  
  
