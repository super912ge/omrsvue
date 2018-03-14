package com.proship.omrs.candidate.address.service;

import com.proship.omrs.candidate.address.param.CreateAddressParam;
import com.proship.omrs.candidate.address.param.UpdateAddressParam;

public interface AddressService {

    Long addAddress(Long id, CreateAddressParam param);

    Long update(UpdateAddressParam param);

    Long delete(Long id);
}
