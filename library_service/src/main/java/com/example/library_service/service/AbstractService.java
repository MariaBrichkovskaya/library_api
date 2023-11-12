package com.example.library_service.service;

public interface AbstractService<K> {
    void add(K id);
    void delete(K id);
}
