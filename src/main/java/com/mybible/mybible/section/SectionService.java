package com.mybible.mybible.section;

import java.util.List;

public interface SectionService {

    Section saveSection(Section section);
    Section getSection(Long sectionId);
    Section updateSection(Long sectionId, Section section);
    List<Section> getAllSection();
    void deleteSection(Long sectionId);
}
