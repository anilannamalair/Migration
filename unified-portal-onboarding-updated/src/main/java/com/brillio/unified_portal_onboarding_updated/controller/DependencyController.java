package com.brillio.unified_portal_onboarding_updated.controller;


import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.unified_portal_onboarding_updated.service.PackageJsonParser;
import com.brillio.unified_portal_onboarding_updated.service.PomXmlParser;

@RestController
@RequestMapping("/api/dependencies")
public class DependencyController {

    @Autowired
    private PomXmlParser pomXmlParser;

    @Autowired
    private PackageJsonParser packageJsonParser;

    // Endpoint to fetch dependencies from a remote pom.xml URL
    @GetMapping("/pom")
    public List<String> getPomDependencies(@RequestParam String pomFileUrl) throws Exception {
        // Fetch the content of the pom.xml file from the URL
        URL url = new URL(pomFileUrl);
        InputStream inputStream = url.openStream();
        return pomXmlParser.extractDependencies(inputStream);
    }

    // Endpoint to fetch dependencies from package.json
    @GetMapping("/npm")
    public List<String> getNpmDependencies(@RequestParam String packageJsonPath) throws Exception {
        return packageJsonParser.extractDependencies(packageJsonPath);
    }
}
