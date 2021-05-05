package com.gfg.product;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.gfg.product.bdd.steps"},
        features = {"classpath:com.gfg.product/features"}
)
@ActiveProfiles(value = "test")
public class BddCoverage {

}