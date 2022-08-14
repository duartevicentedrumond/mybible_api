package com.mybible.mybible.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAllByOrderByTypeId();
    }

    @Override
    public Type getType(Long typeId) {
        return typeRepository.findByTypeId(typeId);
    }

    @Override
    public Type updateType(Long typeId, Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void deleteType(Long typeId) {
        typeRepository.deleteById(typeId);
    }

}
