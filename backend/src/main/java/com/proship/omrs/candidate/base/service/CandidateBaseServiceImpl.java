package com.proship.omrs.candidate.base.service;

import com.proship.omrs.candidate.base.entity.BaseEntity;
import com.proship.omrs.candidate.base.entity.BaseEntityCountry;
import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.candidate.participant.repository.ParticipantRepository;
import com.proship.omrs.user.entity.CustomUser;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.Timestamp;

public class CandidateBaseServiceImpl {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    ParticipantRepository participantRepository;

    private Long userId;

    public void setUserId(){

        String username =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CustomUser user = (CustomUser)userDetailsService.loadUserByUsername(username);

       this.userId = user.getUserId();
    }

    public Long getUserId(){

        if (userId==null)setUserId();

        return this.userId;
    }



   public Participant getParticipant(Long id){

        return participantRepository.findOne(id);
   }

   public BaseEntity getNewBaseEntity(){

       BaseEntity baseEntity = new BaseEntity();

       baseEntity.setCreatorId(getUserId());

       baseEntity.setTransactiontime(new Timestamp(System.currentTimeMillis()));

       return baseEntity;
   }

    public BaseEntityCountry getNewBaseEntityWithCountryId(Long countryId){

        BaseEntityCountry baseEntityCountry = (BaseEntityCountry) getNewBaseEntity();

        baseEntityCountry.setCountryId(countryId);

        return baseEntityCountry;
    }

   public BaseOverrideEntity getNewBaseOverrideEntity(Long id){

       BaseOverrideEntity baseOverrideEntity = new BaseOverrideEntity();

       baseOverrideEntity.setDestroyerId(getUserId());

       baseOverrideEntity.setParticipant(getParticipant(id));

       baseOverrideEntity.setNexttransactiontime(Utils.getInfiniteTimestamp());

       return baseOverrideEntity;
   }
}
