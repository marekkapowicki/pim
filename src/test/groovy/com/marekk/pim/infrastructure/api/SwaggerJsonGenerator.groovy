package com.marekk.pim.infrastructure.api

import com.marekk.pim.PIMApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

@AutoConfigureRestDocs(outputDir = "build/asciidoc/snippets")
@SpringBootTest(classes = [PIMApplication.class, SwaggerConfiguration.class])
@AutoConfigureMockMvc
@Profile("documentation")
@ActiveProfiles(profiles = "documentation")
class SwaggerJsonGenerator extends Specification {

    @Autowired
    private MockMvc mockMvc

    def "generate swagger json file"() {
        expect:
            String outputDir = System.getProperty("io.springfox.staticdocs.outputDir")
            MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn()

            MockHttpServletResponse response = mvcResult.getResponse()
            String swaggerJson = response.getContentAsString()
            Files.createDirectories(Paths.get(outputDir))

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8)
            writer.write(swaggerJson)
            writer.close()

    }
}
