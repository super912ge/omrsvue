package com.proship.omrs.utils.util;

import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.entity.EvalTagType;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {


    public static <T extends BaseEntity> T getLastElementFromList(List<T> list){


        T element = null;
        if (list!=null && !list.isEmpty()){
            element = list.get(list.size()-1);
        }
        return element;
    }


    public static Timestamp getInfiniteTimestamp(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");

        Date parsedTimeStamp = null;
        try {
            parsedTimeStamp = dateFormat.parse("10000-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Timestamp(parsedTimeStamp.getTime());
    }
    public static EvalTag findPrimarySkill(EvalTag tag){

        if (tag.getType().getId()!=1) return null;

        Collections.sort(tag.getChildren(),(e1,e2)->e1.getType().getId().compareTo(e2.getType().getId()));

        tag = tag.getChildren().get(0);

        if (tag.getType().getId()==2) {

            for (EvalTag instrument : tag.getChildren()){

                if (instrument.getChildren()!=null && !instrument.getChildren().isEmpty()){

                    for (EvalTag rating : instrument.getChildren()) {

                            if (rating.getDiscriminatorId() == 5)
                                return rating.getParent();

                    }
                }
            }
        }
        return null;
    }

    public static List<EvalTagType> findLanguage(EvalTag tag){

        if (tag.getType().getId()!=1) return null;

        for (EvalTag type : tag.getChildren()){

            if (type.getType().getId()==4){
                return type.getChildren().stream().map(EvalTag::getType).collect(Collectors.toList());
            }
        }

        return null;

    }

    public static ResponseEntity<Map<String, Object>> getResponseEntityWithResultMap(Object result){
        Map resultMap = new HashMap();
        resultMap.put("success",true);
        resultMap.put("result", result);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
}
