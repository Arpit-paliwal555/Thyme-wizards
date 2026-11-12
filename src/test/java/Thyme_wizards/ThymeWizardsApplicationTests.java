package Thyme_wizards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ThymeWizardsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void indexTemplateRendersSidebarFragments() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	void teamsTemplateRendersSidebarFragments() throws Exception {
		mockMvc.perform(get("/teams/all")).andExpect(status().isOk());
	}

	@Test
	void usersTemplateRendersDummyUsers() throws Exception {
		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("lang=\"en\"")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("name=\"lang\" onchange=\"this.form.submit()\"")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Language")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString(">Users</h1>")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Alex Johnson")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("morgan.lee@example.com")));
	}

	@Test
	void usersPageRendersInFrenchWhenLanguageIsSelected() throws Exception {
		mockMvc.perform(get("/users").param("lang", "fr"))
				.andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("lang=\"fr\"")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Utilisateurs")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Tableau de bord")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Nom")));
	}

	@Test
	void selectedLanguagePersistsAcrossRequests() throws Exception {
		MvcResult result = mockMvc.perform(get("/users").param("lang", "fr"))
				.andExpect(status().isOk())
				.andReturn();
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession(false);

		mockMvc.perform(get("/teams/all").session(session))
				.andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Équipes")));
	}
}
