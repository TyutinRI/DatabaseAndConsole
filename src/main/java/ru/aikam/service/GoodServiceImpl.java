package ru.aikam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aikam.entity.Good;
import ru.aikam.repository.GoodRepository;

@Service
public class GoodServiceImpl implements GoodService {
    private final GoodRepository goodRepository;

    @Autowired
    public GoodServiceImpl(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    @Override
    public void save(Good good) {
        goodRepository.save(good);
    }
}
