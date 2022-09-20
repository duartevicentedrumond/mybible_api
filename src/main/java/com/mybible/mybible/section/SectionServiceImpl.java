package com.mybible.mybible.section;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public List<Section> getAllSection() {
        return sectionRepository.findAllByOrderBySectionId();
    }

    @Override
    public Section getSection(Long sectionId) {
        return sectionRepository.findBySectionId(sectionId);
    }

    @Override
    public Section updateSection(Long sectionId, Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void deleteSection(Long sectionId) {
        sectionRepository.deleteById(sectionId);
    }
}
