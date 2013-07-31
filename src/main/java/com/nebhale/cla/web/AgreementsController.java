/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nebhale.cla.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nebhale.cla.Agreement;
import com.nebhale.cla.Version;
import com.nebhale.cla.github.GitHubRestOperations;
import com.nebhale.cla.repository.AgreementRepository;
import com.nebhale.cla.repository.VersionRepository;

@Controller
@RequestMapping("/agreements")
final class AgreementsController extends AbstractController {

    private final AgreementRepository agreementRepository;

    private final GitHubRestOperations gitHubRestOperations;

    private final VersionRepository versionRepository;

    @Autowired
    AgreementsController(GitHubRestOperations gitHubRestOperations, AgreementRepository agreementRepository, VersionRepository versionRepository) {
        super(gitHubRestOperations);
        this.gitHubRestOperations = gitHubRestOperations;
        this.agreementRepository = agreementRepository;
        this.versionRepository = versionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    String listAgreements(ModelMap model) {
        model.put("agreements", this.agreementRepository.find());
        return "agreements";
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    String createAgreement(@RequestParam String name) {
        Agreement agreement = this.agreementRepository.create(name);
        return String.format("redirect:/agreements/%d/versions", agreement.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{agreementId}/versions")
    String listVersions(@PathVariable Long agreementId, ModelMap model) {
        model.put("agreement", this.agreementRepository.read(agreementId));
        model.put("versions", this.versionRepository.find(agreementId));
        return "versions";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{agreementId}/versions")
    String createVersion(@PathVariable Long agreementId, @RequestParam String name, @RequestParam String individualContent,
        @RequestParam String corporateContent) {
        Version version = this.versionRepository.create(agreementId, name, individualContent, corporateContent);
        return String.format("redirect:/agreements/%d/versions/%d", agreementId, version.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{agreementId}/versions/{versionId}")
    String readVersion(@PathVariable Long versionId, ModelMap model) {
        Version version = this.versionRepository.read(versionId);

        model.put("version", version);
        model.put("agreement", this.agreementRepository.read(version.getAgreementId()));
        model.put("individualContent", this.gitHubRestOperations.postForObject("/markdown/raw", version.getIndividualAgreementContent(), String.class));
        model.put("corporateContent", this.gitHubRestOperations.postForObject("/markdown/raw", version.getCorporateAgreementContent(), String.class));

        return "version";
    }

}
