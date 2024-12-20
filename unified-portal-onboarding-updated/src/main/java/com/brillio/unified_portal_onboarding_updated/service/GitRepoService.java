package com.brillio.unified_portal_onboarding_updated.service;
import java.io.File;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class GitRepoService {

    public File cloneRepository(String repoUrl, String localPath) throws GitAPIException {
        CloneCommand cloneCommand = Git.cloneRepository();
        cloneCommand.setURI(repoUrl);
        cloneCommand.setDirectory(new File(localPath));
        Git git = cloneCommand.call();
        return new File(localPath);
    }
}

