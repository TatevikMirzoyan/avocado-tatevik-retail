package com.avocado.tatevik.retail.service.user;

import com.avocado.tatevik.retail.repository.user.UserRepository;
import com.avocado.tatevik.retail.repository.user.entity.UserEntity;
import com.avocado.tatevik.retail.service.user.converter.UserEntityConverter;
import com.avocado.tatevik.retail.service.user.converter.UserModelConverter;
import com.avocado.tatevik.retail.service.user.model.UserCreationModel;
import com.avocado.tatevik.retail.service.user.model.UserModel;
import com.avocado.tatevik.retail.service.user.model.UserUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityConverter userEntityConverter;

    @Autowired
    private UserModelConverter userModelConverter;

    public UserModel get(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User entity with id " + id + " does not exist."));
        return userEntityConverter.convert(entity);
    }

    public UserModel create(UserCreationModel creationModel) {
        UserEntity entity = userModelConverter.convert(creationModel);
        entity = userRepository.save(entity);
        return userEntityConverter.convert(entity);
    }

    public UserModel update(UserUpdateModel updateModel) {
        UserEntity entity = userRepository.findById(updateModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("User entity with id " + updateModel.getId() + " does not exist."));
        entity = userModelConverter.convert(updateModel, entity);
        entity = userRepository.save(entity);
        return userEntityConverter.convert(entity);
    }

    @Transactional
    public Boolean delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User entity with id " + id + " does not exist."));
        userRepository.updateDeleted(id);
        return true;
    }
}
