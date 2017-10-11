//package za.ac.tut.usedbook.usedbook.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import za.ac.tut.usedbook.usedbook.entiy.User;
//import za.ac.tut.usedbook.usedbook.repository.UserRepository;
//import za.ac.tut.usedbook.usedbook.validation.Helper;
//
///**
// * Created by gracem on 2017/09/30.
// */
//@Service
//public class UserService {
//
//    private UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public String base64UUID(String uuid, Long username) {
//        //TODO: Verify the User exist
//        if (userRepository.exists(username)) {
//            User user = userRepository.findByStudent(username);
//
//            //TODO: set and save new UUID
//            setAndSaveUUID(uuid, user);
//        }
//        return Helper.encodeStringToBase64(uuid);
//
//    }
//
//    private void setAndSaveUUID(String uuid, User user) {
//        user.setSessionKey(uuid);
//        userRepository.save(user);
//    }
//
//    public User findBySessionKey(String encodedKey) {
//        String key = Helper.decodeBase64ToString(encodedKey);
//        return userRepository.findBySessionKey(key);
//    }
//
//    public class UnauthorisedUserException extends RuntimeException{
//        private final String message = "User is not authorised";
//        @Override
//        public String getMessage() {
//            return message;
//        }
//    }
//
//}
