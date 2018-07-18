package com.proship.omrs.evaluation.service;

import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.entity.EvalTagComment;
import com.proship.omrs.evaluation.param.*;
import com.proship.omrs.evaluation.repository.EvalTagCommentRepository;
import com.proship.omrs.evaluation.repository.EvalTagRepository;
import com.proship.omrs.user.entity.CustomUser;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.repository.UserRepository;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class EvaluationTagServiceImpl implements EvaluationTagService{


    @Autowired
    EvalTagRepository repo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EvalTagCommentRepository evalTagCommentRepository;

    @Override
    public Set<Long> findEvaluationByCriteria (EvaluationSearchParam param) {

        Set<Long>totalSearchSet = new HashSet<>();

        int totalCriteria = 0;

        Set<Long>instrumentSet = null;

        if (param.getInstrumentCriteria()!=null && !param.getInstrumentCriteria().isEmpty()){

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

    @Override
    @Transactional
    public EvalTag createNewRating(Long id, NewRatingParam param) {


        String username =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByName(username);

        EvalTag root = repo.getOne(id);

        if (root.getChildren()==null|| root.getChildren().isEmpty()) {

            if (param.getInstrument() != null && param.getInstrument().getInstrumentTypeId()!=null) {

                EvalTag instrumentRoot = setEvalTag(2l, root, user.getId());

                instrumentRoot = repo.save(instrumentRoot);

                EvalTag instrument = setEvalTag(param.getInstrument().getInstrumentTypeId(), instrumentRoot, user.getId());

                instrument = repo.save(instrument);

                if (param.getInstrument().getRatings() != null && !param.getInstrument().getRatings().isEmpty()) {

                    EvalTag primarySkill = setEvalTag(82l, instrument,user.getId());

                    repo.save(primarySkill);

                    for (InstrumentRating ratingParam : param.getInstrument().getRatings()) {

                        EvalTag ratingType = setEvalTag(ratingParam.getRatingTypeId(), instrument, user.getId());

                        ratingType = repo.save(ratingType);

                        EvalTag rating = setEvalTag(ratingParam.getRatingId(), ratingType, user.getId());

                        repo.saveAndFlush(rating);

                        repo.refresh(ratingType);
                    }
                    repo.refresh(instrument);

                    repo.refresh(instrumentRoot);
                }
            }
            if (param.getClassification()!=null&& !param.getClassification().isEmpty()){

                EvalTag classificationRoot = setEvalTag(133l,root,user.getId());

                repo.save(classificationRoot);

                saveAndRefreshTags(repo,classificationRoot,param.getClassification(),user.getId());

                repo.refresh(classificationRoot);
            }
            if (param.getLanguage()!=null&& !param.getLanguage().isEmpty()){

                EvalTag languageRoot = setEvalTag(4l,root,user.getId());

                repo.save(languageRoot);

                saveAndRefreshTags(repo,languageRoot,param.getLanguage(),user.getId());

                repo.refresh(languageRoot);
            }
            if (param.getEvaluation()!=null){

                if (param.getEvaluation().getComment()!=null&&!"".equals(param.getEvaluation().getComment().trim())) {

                    EvalTagComment evalTagComment = new EvalTagComment();

                    evalTagComment.setCreatorId(user.getId());

                    evalTagComment.setNexttransactiontime(Utils.getInfiniteTimestamp());

                    evalTagComment.setTransactiontime(new Timestamp(System.currentTimeMillis()));

                    evalTagComment.setEvalTag(root);

                    evalTagCommentRepository.save(evalTagComment);
                }
            }
        }
        repo.flush();

        repo.refresh(root);

        return root;
    }

    @Override
    public EvalTag findEvalTagById(Long id) {
        return repo.getOne(id);
    }

    private Set<Long>findInstrumentIds(Set<InstrumentRatingSearchParam> param ){

        Set<Long> instrumentSet = new HashSet<>();

        Boolean add = true;

        Set<Long> searchWithoutRatingSet = new HashSet<>();

        for (InstrumentRatingSearchParam irsp : param){

            if (irsp.getRatings()==null||irsp.getRatings().isEmpty()){

                searchWithoutRatingSet.add(irsp.getInstrumentTypeId());

                continue;

            }else {

                Set<Long> resultSet = null;

                Set<Long> eachResultSet = null;

                for(InstrumentRating rating : irsp.getRatings()){

                     eachResultSet = repo.findEvalTagIdByMusicalInstrumentSkillRating

                            (rating.getRatingId(), rating.getRatingTypeId(), irsp.getInstrumentTypeId());

                     if (resultSet==null) resultSet = eachResultSet;

                    else resultSet.retainAll(eachResultSet);
                }

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

                searchWithoutRating.add(p.getId());

                continue;

            }else {
                Set<Long> resultSet = repo.findEvalTagIdByClassificationOrPresentationRating(
                        p.getRatingId(), p.getId()
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

                searchWithoutRating.add(p.getId());

                continue;
            }else {

                Set<Long> resultSet = repo.findEvalTagIdByLanguageRating(p.getRatingId(),p.getId());

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

    EvalTag setEvalTag(Long typeId, EvalTag parent, Long userId){

        EvalTag evalTag = new EvalTag();

        evalTag.setEvalTagTypeId(typeId);
        evalTag.setTransactiontime(new Timestamp(System.currentTimeMillis()));
        evalTag.setNexttransactiontime(Utils.getInfiniteTimestamp());
        evalTag.setParent(parent);
        evalTag.setUuid(UUID.randomUUID().getMostSignificantBits());
        evalTag.setCreatorId(userId);
        evalTag.setDestroyerId(userId);
        return evalTag;
    }

    private void saveAndRefreshTags(EvalTagRepository repo, EvalTag root, Set<GeneralRatingParam> params,Long userId ){

        for (GeneralRatingParam ratingParam: params){

            EvalTag ratingType = setEvalTag(ratingParam.getId(), root, userId);

            ratingType = repo.save(ratingType);

            EvalTag rating = setEvalTag(ratingParam.getRatingId(), ratingType, userId);

            repo.saveAndFlush(rating);

            repo.refresh(ratingType);
        }
    }
}

