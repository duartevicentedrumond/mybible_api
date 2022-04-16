package com.mybible.mybible.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {

    Type saveType(Type type);
    Optional<Type> getType(Long typeId);
    Type updateType(Long typeId, Type type);
    List<Type> getAllTypes();
    void deleteType(Long typeId);
}
