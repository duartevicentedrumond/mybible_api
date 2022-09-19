package com.mybible.mybible.type;

import java.util.List;

public interface TypeService {

    Type saveType(Type type);
    Type getType(Long typeId);
    Type updateType(Long typeId, Type type);
    List<Type> getAllTypes();
    void deleteType(Long typeId);
}
