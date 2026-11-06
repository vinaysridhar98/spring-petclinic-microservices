package org.springframework.samples.petclinic.customers.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.customers.model.Owner;
import org.springframework.samples.petclinic.customers.model.OwnerRepository;
import org.springframework.samples.petclinic.customers.web.mapper.OwnerEntityMapper;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerResource.class)
class OwnerResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private OwnerRepository ownerRepository;

    @MockitoBean
    private OwnerEntityMapper ownerEntityMapper;

    @Test
    void shouldAddRewardPointsSuccessfully() throws Exception {
        Owner owner = new Owner();
        owner.setFirstName("Sridhar");
        owner.setLastName("A");

        given(ownerRepository.findById(1)).willReturn(Optional.of(owner));


        mvc.perform(put("/owners/1/reward-points")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"pointsToAdd\": 50}"))
            .andExpect(status().isOk());

        verify(ownerRepository).save(any(Owner.class));
    }
}
