package com.proship.omrs.contract.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.proship.omrs.candidate.name.repository.ParticipantNameTtsRepository;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import com.proship.omrs.candidate.participant.repository.ParticipantActRepository;
import com.proship.omrs.contract.entity.*;
import com.proship.omrs.contract.param.*;
import com.proship.omrs.contract.repository.*;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.repository.GigRepository;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.repository.UserRepository;
import com.proship.omrs.utils.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ContractServiceImpl implements ContractService{

	
	@Autowired
	ContractRepository contractRepo;

	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	ContractStatusRepository contractStatusRepo;

	@Autowired
	ContractEventRepository contractEventRepository;

	@Autowired
	ContractShardRepository contractShardRepo;

	@Autowired
	ContractPeriodShardRepository contractPeriodShardRepository;

	@Autowired
	ParticipantNameTtsRepository participantNameTtsRepository;

	@Autowired
	ParticipantActRepository participantActRepository;

	@Autowired
	GigRepository gigRepository;

	@Autowired
	HibernateContractDao hibernateContractDao;

	@Autowired
	PayRepository payRepository;

	@Override
	public Contract reinstateContract(Long id) {
		
		Contract contract = contractRepo.getOne(id);

		return contract;
	}

	private User user;

	private User getUser(){

		if (user==null) {

			String username =
					(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			this.user =   userRepository.findByName("yiwei_test");
		}
		return this.user;
	}

	@Override
	public Map<String, Object> generateContractAvailability() {

//		Page<Contract> contracts = contractPeriodShardRepository.findContractByValidTime();
		return null;
	}

	@Override
	public ContractSearchResultParam findContractByConditions(ContractSearchParamIn in) {


		return hibernateContractDao.findContractByMultiConditions(in);


	}

	@Override
    @Transactional
	public Long addNewContract(Booking booking) {

		Contract contract = new Contract();

		contract.setNonPs(booking.getNonPs());

		contract.setSpecialtyType(1);

		contract.setUuid(UUID.randomUUID().getMostSignificantBits());

		ContractStatus contractStatus = new ContractStatus();

		contractStatus.setCancel(false);

		contractStatus.setCreator(getUser());

		contractStatus.setNexttransactiontime(Utils.getInfiniteTimestamp());

		contractStatus.setContract(contract);

		contract.setContractStatus(contractStatus);

		ContractRecruiterAttributionTts contractRecruiterAttributionTts = new ContractRecruiterAttributionTts();

		contractRecruiterAttributionTts.setRecruiterId(booking.getRecruiterId());

		contractRecruiterAttributionTts.setNexttransactiontime(Utils.getInfiniteTimestamp());

		contractRecruiterAttributionTts.setCreatorId(getUser().getId());

        contract.setContractRecruiterAttributionTts(contractRecruiterAttributionTts);

		contractRecruiterAttributionTts.setContract(contract);

        ContractPeriodShard contractPeriodShard = new ContractPeriodShard();

        contractPeriodShard.setValidendtime(booking.getEndDate());

        contractPeriodShard.setValidstarttime(booking.getStartDate());

        contractPeriodShard.setNexttransactiontime(Utils.getInfiniteTimestamp());

        contractPeriodShard.setStartConfidence(100);

        contractPeriodShard.setEndConfidence(100);

        contractPeriodShard.setCreatorId(getUser().getId());

        contract.setContractPeriodShard(contractPeriodShard);

        contractPeriodShard.setContract(contract);

        contract = contractRepo.save(contract);

		return contract.getId();
	}





    @Override
    public ContractBriefWithParticipant findContractById(Long id) {
        return new ContractBriefWithParticipant(contractRepo.getOne(id));
    }

    @Override
    public void addContractEventsAndShard(Long contractId, Booking booking) {

	    Gig gig = gigRepository.getOne(booking.getGigId());

	    Contract contract = contractRepo.getOne(contractId);

        booking.getPay().setPayeeId(booking.getParticipantActId());

        Pay pay = payRepository.save(booking.getPay());

        ContractMainShard contractMainShard = new ContractMainShard();

        ParticipantAct act = participantActRepository.getOne(booking.getParticipantActId());

        contractMainShard.setAct(act);

        contractMainShard.setGig(gig);

        contractMainShard.setPay(pay);

        contractMainShard.setValidendtime(booking.getEndDate());

        contractMainShard.setValidstarttime(booking.getStartDate());

        contractMainShard.setNexttransactiontime(Utils.getInfiniteTimestamp());

        contractMainShard.setContract(contract);

        contractMainShard.setPosition(booking.getRank());

        contractMainShard.setCreator(getUser());

        contractShardRepo.save(contractMainShard);

        ContractEvent createContractEvent =
                generateContractEvent(booking.getEventDate(),booking.getFormDate(),
                        contract,ContractEventCaseType.CREATE_BEFORE);

        contractEventRepository.save(createContractEvent);

        ContractEvent asaEvent = generateContractEvent(booking.getASAAcceptDate(),booking.getFormDate(),
                contract,ContractEventCaseType.ASA_ACCEPT);

        contractEventRepository.save(asaEvent);

    }


    private ContractEvent generateContractEvent(Date eventDate, Date formDate, Contract contract, String caseValue){

		ContractEvent createContractEvent = new ContractEvent();

		createContractEvent.setContract(contract);

		createContractEvent.setCreator(getUser());

		createContractEvent.setUuid(UUID.randomUUID().getMostSignificantBits());

		ContractEventCaseTts createContractEventCaseTts = new ContractEventCaseTts();

		createContractEventCaseTts.setContractEvent(createContractEvent);

		createContractEventCaseTts.setValue(caseValue);

		createContractEventCaseTts.setNexttransactiontime(Utils.getInfiniteTimestamp());

		createContractEventCaseTts.setCreatorId(getUser().getId());

		ContractEventFormDateTts createContractEventFormDateTts = new ContractEventFormDateTts();

		createContractEventFormDateTts.setContractEvent(createContractEvent);

		createContractEventFormDateTts.setValue(formDate);

		createContractEventFormDateTts.setCreatorId(getUser().getId());

		createContractEventFormDateTts.setNexttransactiontime(Utils.getInfiniteTimestamp());

		ContractEventDateTts createContractEventDateTts = new ContractEventDateTts();

		createContractEventDateTts.setContractEvent(createContractEvent);

		createContractEventDateTts.setCreatorId(getUser().getId());

		createContractEventDateTts.setNexttransactiontime(Utils.getInfiniteTimestamp());

		createContractEventDateTts.setValue(eventDate);

		createContractEvent.setContractEventDateTts(createContractEventDateTts);

		createContractEvent.setContractEventFormDateTts(createContractEventFormDateTts);

		createContractEvent.setContractEventCaseTts(createContractEventCaseTts);

		return createContractEvent;
	}
}
