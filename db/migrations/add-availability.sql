Create Sequence participant_contract_availability_id_sequence increment by 1;

Create table participant_contract_availability (id bigint primary key DEFAULT nextval(
    'participant_contract_availability_id_sequence'::regclass),
  participant_id bigint not null,
                                                validtimeStart timestamp, validtimeEnd timestamp,
                                                contract_id_prev bigint, contract_id_next bigint)

