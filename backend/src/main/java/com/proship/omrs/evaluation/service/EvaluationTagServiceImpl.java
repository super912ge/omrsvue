package com.proship.omrs.evaluation.service;

import com.proship.omrs.evaluation.param.EvaluationSearchParam;
import com.proship.omrs.evaluation.param.GeneralRatingParam;
import com.proship.omrs.evaluation.param.InstrumentRatingSearchParam;
import com.proship.omrs.evaluation.repository.EvalTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class EvaluationTagServiceImpl implements EvaluationTagService{

    @Autowired
    EvalTagRepository repo;

    @Override
    public Set<Long> findEvaluationByCriteria (EvaluationSearchParam param) {

        Set<Long>totalSearchSet = new HashSet<>();

        int totalCriteria = 0;
        Set<Long>instrumentSet = null;

        if (param.getInstrumentCriteria()!=null&& !param.getInstrumentCriteria().isEmpty()){

            instrumentSet = findInstrumentIds(param.getInstrumentCriteria());

            if (instrumentSet.isEmpty()) return new HashSet<>();

            totalSearchSet.addAll(instrumentSet);

            totalCriteria++;

        }
        Set<Long>classificationSet = null;
        if (param.getClassificationCriteria()!=null&&!param.getClassificationCriteria().isEmpty()){

            classificationSet = findPresentationIdsOrClassificationIds(param.getClassificationCriteria());

            totalSearchSet.addAll(classificationSet);

            if (classificationSet.isEmpty())return new HashSet<>();

            totalCriteria++;
        }

        Set<Long>presentationSet = null;

        if (param.getPresentationCriteria()!=null&&!param.getPresentationCriteria().isEmpty()){

            presentationSet = findPresentationIdsOrClassificationIds(param.getPresentationCriteria());

            if (presentationSet.isEmpty()) return new HashSet<>();

            totalSearchSet.addAll(presentationSet);

            totalCriteria++;



        }

        Set<Long>languageSet = null;

        if (param.getLanguageCriteria()!=null&&!param.getLanguageCriteria().isEmpty()){

           languageSet = findLanguageIds(param.getLanguageCriteria());

           if (languageSet.isEmpty()) return new HashSet<>();

           totalCriteria++;

           totalSearchSet.addAll(languageSet);
        }

        Set<Long> nonInstrumentSet = null;
        if (param.getNonInstrumentCriteria()!=null&&!param.getNonInstrumentCriteria().isEmpty()){

            nonInstrumentSet = repo.findEvalTagIdBySkill(param.getNonInstrumentCriteria(),
                    (long)param.getNonInstrumentCriteria().size());

            totalCriteria++;
            totalSearchSet.addAll(nonInstrumentSet);
        }


        return repo.findEvalTagByAllCriteria(totalSearchSet, (long)totalCriteria);
    }

    private Set<Long>findInstrumentIds(Set<InstrumentRatingSearchParam> param ){
        Set<Long> instrumentSet = new HashSet<>();

        Boolean add = true;

        Set<Long> searchWithoutRatingSet = new HashSet<>();

        for (InstrumentRatingSearchParam p : param){

            if (p.getRatingTypeId()==null||p.getRatingId()==null){

                searchWithoutRatingSet.add(p.getInstrumentTypeId());

                continue;

            }else {
                Set<Long> resultSet = repo.findEvalTagIdByMusicalInstrumentSkillRating
                        (p.getRatingId(), p.getRatingTypeId(), p.getInstrumentTypeId());

                if (add) {

                    instrumentSet.addAll(resultSet);

                    add = false;

                } else instrumentSet.retainAll(resultSet);
            }
        }
        if (!searchWithoutRatingSet.isEmpty()) {
            Set<Long> resultSet = repo.findEvalTagIdBySkill(searchWithoutRatingSet, (long) searchWithoutRatingSet.size());

            if (add) instrumentSet.addAll(resultSet);

            else instrumentSet.retainAll(resultSet);
        }

        return instrumentSet;
    }
    private Set<Long> findPresentationIdsOrClassificationIds(Set<GeneralRatingParam> param){
        Set<Long>classificationSet = new HashSet<>();
        Set<Long> searchWithoutRating = new HashSet<>();
        boolean add = true;
        for (GeneralRatingParam p : param){

            if (p.getRatingId()==null){
                searchWithoutRating.add(p.getSkillTypeId());
                continue;
            }else {
                Set<Long> resultSet = repo.findEvalTagIdByClassificationOrPresentationRating(
                        p.getRatingId(), p.getSkillTypeId()
                );
                if (add) {

                    classificationSet.addAll(resultSet);

                    add = false;

                } else classificationSet.retainAll(resultSet);
            }
        }
        if(!searchWithoutRating.isEmpty()){
            Set<Long> result = repo.findEvalTagIdBySkill(searchWithoutRating,
                (long)searchWithoutRating.size());
            if (add) classificationSet.addAll(result);
            else classificationSet.retainAll(result);
        }
        return classificationSet;
    }
    private Set<Long> findLanguageIds(Set<GeneralRatingParam> param){

        Set<Long>languageSet = new HashSet<>();

        Set<Long> searchWithoutRating = new HashSet<>();

        boolean add = true;

        for (GeneralRatingParam p : param){

            if (p.getRatingId()==null){

                searchWithoutRating.add(p.getSkillTypeId());

                continue;
            }else {

                Set<Long> resultSet = repo.findEvalTagIdByLanguageRating(p.getRatingId(),p.getSkillTypeId());

                if (add) {

                    languageSet.addAll(resultSet);

                    add = false;

                } else languageSet.retainAll(resultSet);
            }
        }
        if (!searchWithoutRating.isEmpty()) {
            Set<Long> result = repo.findEvalTagIdBySkill(searchWithoutRating,
                    (long) searchWithoutRating.size());

            if (add) languageSet.addAll(result);

            else languageSet.retainAll(result);
        }

        return languageSet;
    }

}

