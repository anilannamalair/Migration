package com.brillio.unified_portal_onboarding_updated.service;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;
@Service
public class PomXmlParser {

    // This method dynamically fetches all dependencies from the pom.xml InputStream
    public List<String> extractDependencies(InputStream pomInputStream) throws Exception {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(pomInputStream);
        Element rootElement = document.getRootElement();

        // Get the dependencies section of the XML
        Element dependenciesElement = rootElement.getChild("dependencies");

        List<String> dependencies = new ArrayList<>();

        // If there is a <dependencies> section, iterate over its children
        if (dependenciesElement != null) {
            List<Element> dependencyElements = dependenciesElement.getChildren("dependency");

            // Iterate over each dependency element in the list
            for (Element dependency : dependencyElements) {
                StringBuilder dependencyInfo = new StringBuilder();

                // Iterate over all child elements of a dependency to dynamically extract all attributes/values
                for (Element child : dependency.getChildren()) {
                    String tagName = child.getName();
                    String tagValue = child.getTextTrim();
                    dependencyInfo.append(tagName).append(": ").append(tagValue).append(" | ");
                }

                // Remove trailing separator if exists
                if (dependencyInfo.length() > 0) {
                    dependencyInfo.setLength(dependencyInfo.length() - 3); // Remove last " | "
                }

                // Add the dependency information to the list
                dependencies.add(dependencyInfo.toString());
            }
        }

        return dependencies;
    }
}
