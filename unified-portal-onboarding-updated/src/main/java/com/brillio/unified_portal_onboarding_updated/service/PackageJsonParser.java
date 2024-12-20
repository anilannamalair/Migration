package com.brillio.unified_portal_onboarding_updated.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PackageJsonParser {

    // This method dynamically fetches dependencies from package.json
    public List<String> extractDependencies(String packageJsonPath) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(packageJsonPath)));
        JSONObject packageJson = new JSONObject(content);
        
        List<String> dependencies = new ArrayList<>();

        // Check dependencies in the "dependencies" and "devDependencies" sections
        if (packageJson.has("dependencies")) {
            JSONObject deps = packageJson.getJSONObject("dependencies");
            for (String key : deps.keySet()) {
                dependencies.add(key + ": " + deps.getString(key));
            }
        }

        if (packageJson.has("devDependencies")) {
            JSONObject devDeps = packageJson.getJSONObject("devDependencies");
            for (String key : devDeps.keySet()) {
                dependencies.add(key + ": " + devDeps.getString(key));
            }
        }

        return dependencies;
    }
}
