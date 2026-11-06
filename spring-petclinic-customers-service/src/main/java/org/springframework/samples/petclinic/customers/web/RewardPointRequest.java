
package org.springframework.samples.petclinic.customers.web;

import jakarta.validation.constraints.Min;

public record RewardPointRequest(
    @Min(value = 1, message = "Points added must be greater than zero")
    int pointsToAdd
) {}
