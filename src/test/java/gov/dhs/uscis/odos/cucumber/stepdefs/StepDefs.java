package gov.dhs.uscis.odos.cucumber.stepdefs;

import gov.dhs.uscis.odos.Odosiims2ServiceApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = Odosiims2ServiceApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
